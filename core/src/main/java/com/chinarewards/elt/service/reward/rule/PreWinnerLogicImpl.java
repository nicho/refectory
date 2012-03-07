package com.chinarewards.elt.service.reward.rule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chinarewards.elt.dao.org.StaffDao;
import com.chinarewards.elt.dao.reward.CandidateDao;
import com.chinarewards.elt.dao.reward.PreWinnerDao;
import com.chinarewards.elt.dao.reward.PreWinnerLotDao;
import com.chinarewards.elt.dao.reward.RewardDao;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.reward.person.Candidate;
import com.chinarewards.elt.domain.reward.person.PreWinner;
import com.chinarewards.elt.domain.reward.person.PreWinnerLot;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.reward.base.PreWinnerLotStatus;
import com.chinarewards.elt.model.reward.exception.NoEffectivePreWinnerException;
import com.chinarewards.elt.util.DateUtil;
import com.google.inject.Inject;

/**
 * The implementation of {@link PreWinnerLogic}
 * 
 * @author yanxin
 * @since 1.0
 */
public class PreWinnerLogicImpl implements PreWinnerLogic {

	private final CandidateDao candidateDao;
	private final RewardDao rewardDao;
	private final PreWinnerLotDao preWinnerLotDao;
	private final PreWinnerDao preWinnerDao;
	private final StaffDao staffDao;

	@Inject
	public PreWinnerLogicImpl(CandidateDao candidateDao, RewardDao rewardDao,
			PreWinnerLotDao preWinnerLotDao, PreWinnerDao preWinnerDao,
			StaffDao staffDao) {
		this.candidateDao = candidateDao;
		this.rewardDao = rewardDao;
		this.preWinnerLotDao = preWinnerLotDao;
		this.preWinnerDao = preWinnerDao;
		this.staffDao = staffDao;
	}

	@Override
	public String addPreWinnerFromCandidateOfReward(SysUser caller,
			String rewardId) {
		Date now = DateUtil.getTime();
		Reward reward = rewardDao.findById(Reward.class, rewardId);
		List<Candidate> candidates = candidateDao
				.findCandidatesByRewardId(rewardId);
		PreWinnerLot preWinnerLot = new PreWinnerLot();
		preWinnerLot.setReward(reward);
		preWinnerLot.setStatus(PreWinnerLotStatus.NEW);
		preWinnerLot.setCreatedAt(now);
		preWinnerLot.setCreatedBy(caller);
		preWinnerLot.setLastModifiedAt(now);
		preWinnerLot.setLastModifiedBy(caller);
		preWinnerLotDao.save(preWinnerLot);

		for (Candidate candidate : candidates) {
			PreWinner preWinner = new PreWinner();
			preWinner.setPreWinnerLot(preWinnerLot);
			preWinner.setReason("自动生成");
			preWinner.setUnit(reward.getRewardUnit());
			preWinner.setAmt(reward.getAwardAmt());
			preWinner.setStaff(candidate.getStaff());
			preWinner.setCreatedAt(now);
			preWinner.setCreatedBy(caller);
			preWinner.setLastModifiedAt(now);
			preWinner.setLastModifiedBy(caller);
			preWinnerDao.save(preWinner);
		}

		return preWinnerLot.getId();
	}

	@Override
	public String addPreWinnerFromOuter(SysUser caller, String rewardId,
			List<String> staffIds) {
		Date now = DateUtil.getTime();
		Reward reward = rewardDao.findById(Reward.class, rewardId);
		PreWinnerLot preWinnerLot = new PreWinnerLot();
		preWinnerLot.setReward(reward);
		preWinnerLot.setStatus(PreWinnerLotStatus.NEW);
		preWinnerLot.setCreatedAt(now);
		preWinnerLot.setCreatedBy(caller);
		preWinnerLot.setLastModifiedAt(now);
		preWinnerLot.setLastModifiedBy(caller);
		preWinnerLotDao.save(preWinnerLot);

		for (String staffId : staffIds) {
			PreWinner preWinner = new PreWinner();
			preWinner.setPreWinnerLot(preWinnerLot);
			// XXX reason ?
			preWinner.setReason(reward.getName());
			preWinner.setUnit(reward.getRewardUnit());
			preWinner.setAmt(reward.getAwardAmt());
			Staff staff = staffDao.findById(Staff.class, staffId);
			preWinner.setStaff(staff);
			preWinner.setCreatedAt(now);
			preWinner.setCreatedBy(caller);
			preWinner.setLastModifiedAt(now);
			preWinner.setLastModifiedBy(caller);
			preWinnerDao.save(preWinner);
		}

		return preWinnerLot.getId();
	}

	@Override
	public PreWinnerLot getUntreatedPreWinnerLotOfReward(String rewardId)
			throws NoEffectivePreWinnerException {
		List<PreWinnerLotStatus> statusList = new ArrayList<PreWinnerLotStatus>();
		statusList.add(PreWinnerLotStatus.NEW);
		List<PreWinnerLot> lotList = preWinnerLotDao
				.getPreWinnerLotsByRewardIdAndStatusList(rewardId, statusList);
		if (lotList == null || lotList.isEmpty() || lotList.size() > 1) {
			throw new NoEffectivePreWinnerException();
		} else {
			return lotList.get(0);
		}
	}

	@Override
	public PreWinnerLot getPassedPreWinnerOfReward(String rewardId)
			throws NoEffectivePreWinnerException {
		List<PreWinnerLotStatus> statusList = new ArrayList<PreWinnerLotStatus>();
		statusList.add(PreWinnerLotStatus.PASS);
		List<PreWinnerLot> lotList = preWinnerLotDao
				.getPreWinnerLotsByRewardIdAndStatusList(rewardId, statusList);
		if (lotList == null || lotList.isEmpty() || lotList.size() > 1) {
			throw new NoEffectivePreWinnerException();
		} else {
			return lotList.get(0);
		}
	}

	@Override
	public void denyPreWinnerLot(SysUser caller, String lotId, String reason) {
		Date now = DateUtil.getTime();
		PreWinnerLot lot = preWinnerLotDao.findById(PreWinnerLot.class, lotId);
		lot.setStatus(PreWinnerLotStatus.DENY);
		lot.setReason(reason);
		lot.setLastModifiedAt(now);
		lot.setLastModifiedBy(caller);

		preWinnerLotDao.update(lot);
	}

	@Override
	public List<PreWinner> getPreWinnerListFromLot(String lotId) {
		return preWinnerDao.findPreWinnerByPreWinnerLotId(lotId);
	}

	@Override
	public List<PreWinnerLot> getPreWinnerLotsFromReward(String rewardId) {
		List<PreWinnerLot> lots = preWinnerLotDao
				.getPreWinnerLotsByRewardId(rewardId);
		for (PreWinnerLot lot : lots) {
			List<PreWinner> preWinners = preWinnerDao
					.findPreWinnerByPreWinnerLotId(lot.getId());
			lot.setPreWinners(preWinners);
		}
		return lots;
	}
}
