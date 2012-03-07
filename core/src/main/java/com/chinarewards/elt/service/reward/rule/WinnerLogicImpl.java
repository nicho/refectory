package com.chinarewards.elt.service.reward.rule;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.dao.org.StaffDao;
import com.chinarewards.elt.dao.reward.PreWinnerDao;
import com.chinarewards.elt.dao.reward.PreWinnerLotDao;
import com.chinarewards.elt.dao.reward.RewardDao;
import com.chinarewards.elt.dao.reward.WinnerDao;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.reward.person.PreWinner;
import com.chinarewards.elt.domain.reward.person.PreWinnerLot;
import com.chinarewards.elt.domain.reward.person.Winner;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.reward.base.PreWinnerLotStatus;
import com.chinarewards.elt.model.reward.base.WinnerProcessFlag;
import com.chinarewards.elt.tx.exception.BalanceLackException;
import com.chinarewards.elt.tx.service.TransactionService;
import com.chinarewards.elt.util.DateUtil;
import com.google.inject.Inject;

/**
 * The implementation of {@link WinnerLogic}
 * 
 * @author yanxin
 * @since 1.0
 */
public class WinnerLogicImpl implements WinnerLogic {

	PreWinnerLotDao preWinnerLotDao;
	PreWinnerDao preWinnerDao;
	WinnerDao winnerDao;
	RewardDao rewardDao;
	StaffDao staffDao;
	TransactionService transactionService;

	Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	public WinnerLogicImpl(PreWinnerLotDao preWinnerLotDao,
			PreWinnerDao preWinnerDao, WinnerDao winnerDao,
			RewardDao rewardDao, TransactionService transactionService,StaffDao staffDao) {
		this.preWinnerLotDao = preWinnerLotDao;
		this.preWinnerDao = preWinnerDao;
		this.winnerDao = winnerDao;
		this.rewardDao = rewardDao;
		this.transactionService = transactionService;
		this.staffDao=staffDao;
	}

	@Override
	public void approveWinnerFromEffectivePreWinnerLotOfReward(SysUser caller,
			String lotId, String reason) {
		Date now = DateUtil.getTime();
		PreWinnerLot lot = preWinnerLotDao.findById(PreWinnerLot.class, lotId);
		List<PreWinner> preWinners = preWinnerDao
				.findPreWinnerByPreWinnerLotId(lotId);
		for (PreWinner preWinner : preWinners) {
			Winner winner = new Winner();
			winner.setPreWinner(preWinner);
			winner.setReward(lot.getReward());
			winner.setAmt(preWinner.getAmt());
			winner.setReason(preWinner.getReason());
			winner.setProcessFlag(WinnerProcessFlag.UNPROCESS);
			winner.setUnit(preWinner.getUnit());
			winner.setReward(preWinner.getPreWinnerLot().getReward());
			winner.setStaff(preWinner.getStaff());
			winner.setWinTime(now);
			winner.setCreatedAt(now);
			winner.setCreatedBy(caller);
			winner.setLastModifiedAt(now);
			winner.setLastModifiedBy(caller);
			winnerDao.save(winner);
		}

		lot.setStatus(PreWinnerLotStatus.PASS);
		lot.setReason(reason);
		lot.setLastModifiedAt(now);
		lot.setLastModifiedBy(caller);
		preWinnerLotDao.update(lot);
	}

	@Override
	public void processWinnerAward(String rewardId) {
		List<Winner> untreatedWinners = winnerDao
				.findUntreatedWinnersByRewardId(rewardId);
		String fromAccountId = rewardDao
				.findCorporationTxIdByRewardId(rewardId);
		for (Winner w : untreatedWinners) {
			String toAccountId = w.getStaff().getTxAccountId();
			logger.debug("toAccountId={}", w.getStaff().getTxAccountId());
			String unitCode = w.getUnit().toString();
			double amt = w.getAmt();
			try {
				String txId = transactionService.transaction(fromAccountId,
						toAccountId, unitCode, amt);
				w.setProcessFlag(WinnerProcessFlag.PROCESS_SUCCESS);
				w.setRefTransactionId(txId);
				
				Staff staff=w.getStaff();
				staff.setHistoryIntegral(staff.getHistoryIntegral()+amt);
				staffDao.update(staff);
			} catch (BalanceLackException e) {
				logger.warn("No enough balance!", e);
				w.setProcessFlag(WinnerProcessFlag.PROCESS_FAIL);
			} finally {
				winnerDao.update(w);
			}
		}
	}

	@Override
	public List<Winner> getWinnersOfReward(String rewardId) {
		return winnerDao.findWinnersByRewardId(rewardId);
	}

}
