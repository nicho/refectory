package com.chinarewards.elt.service.reward.rule;

import java.util.Date;
import java.util.List;

import com.chinarewards.elt.dao.org.StaffDao;
import com.chinarewards.elt.dao.reward.JudgeDao;
import com.chinarewards.elt.dao.reward.NomineeDao;
import com.chinarewards.elt.dao.reward.NomineeLotDao;
import com.chinarewards.elt.dao.reward.RewardDao;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.reward.person.Judge;
import com.chinarewards.elt.domain.reward.person.Nominee;
import com.chinarewards.elt.domain.reward.person.NomineeLot;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.reward.base.JudgeStatus;
import com.chinarewards.elt.model.reward.base.RewardStatus;
import com.chinarewards.elt.model.reward.exception.JudgeException;
import com.chinarewards.elt.util.DateUtil;
import com.google.inject.Inject;

/**
 * The implementation of {@link NomineeLogic}
 * 
 * @author yanxin
 * @since 1.0
 */
public class NomineeLogicImpl implements NomineeLogic {

	private final NomineeLotDao nomineeLotDao;
	private final NomineeDao nomineeDao;
	private final JudgeDao judgeDao;
	private final RewardDao rewardDao;
	private final StaffDao staffDao;


	@Inject
	public NomineeLogicImpl(NomineeLotDao nomineeLotDao, NomineeDao nomineeDao,
			JudgeDao judgeDao, RewardDao rewardDao, StaffDao staffDao,CandidateLogic candidateLogic) {
		this.nomineeLotDao = nomineeLotDao;
		this.nomineeDao = nomineeDao;
		this.judgeDao = judgeDao;
		this.rewardDao = rewardDao;
		this.staffDao = staffDao;

	}

	@Override
	public NomineeLot addNomineeLotToReward(SysUser caller, String rewardId,
			List<String> staffIds) throws JudgeException {
		Date now = DateUtil.getTime();
		Reward reward = rewardDao.findById(Reward.class, rewardId);
		Judge judge = judgeDao.findJudgeByStaffIdAndRewardId(caller.getStaff().getId(), rewardId);
		if (judge == null) {
			throw new JudgeException(
					"当前用户不是提名人!");
		}

		if (JudgeStatus.NONE != judge.getStatus()) {
			throw new JudgeException("当前用户已经提名,不允许重复提名!");
		}

		// update judge status to Nominated
		judge.setStatus(JudgeStatus.NOMINATED);
		judge.setLastModifiedAt(now);
		judge.setLastModifiedBy(caller);
		judgeDao.update(judge);

		NomineeLot lot = new NomineeLot();
		lot.setJudge(judge);
		lot.setReward(reward);
		lot.setCreatedAt(now);
		lot.setCreatedBy(caller);
		lot.setLastModifiedAt(now);
		lot.setLastModifiedBy(caller);
		nomineeLotDao.save(lot);

		for (String staffId : staffIds) {
			Nominee nominee = new Nominee();
			nominee.setNomineeLot(lot);
			Staff staff = staffDao.findById(Staff.class, staffId);
			nominee.setStaff(staff);
			nominee.setCreatedAt(now);
			nominee.setCreatedBy(caller);
			nominee.setLastModifiedAt(now);
			nominee.setLastModifiedBy(caller);
			nomineeDao.save(nominee);
		}
		
		//提名..修改状态
		reward.setStatus(RewardStatus.NEW);
		rewardDao.update(reward);
		
		return lot;
	}

	@Override
	public List<NomineeLot> getNomineeLotsFromReward(String rewardId) {
		List<NomineeLot> lots = nomineeLotDao
				.findNomineeLotsByRewardId(rewardId);
		for (NomineeLot lot : lots) {
			List<Nominee> nominees = nomineeDao.findNomineesByNomineeLotId(lot
					.getId());
			lot.setNominees(nominees);
		}
		return lots;
	}
}
