package com.chinarewards.elt.service.reward.rule;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.chinarewards.elt.dao.reward.CandidateDao;
import com.chinarewards.elt.dao.reward.RewardDao;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.reward.person.Candidate;
import com.chinarewards.elt.domain.reward.rule.CandidateRule;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.vo.WinnersRecordQueryVo;
import com.chinarewards.elt.util.DateUtil;
import com.google.inject.Inject;

/**
 * The implementation of {@link CandidateLogic}
 * 
 * @author yanxin
 * @since 1.0
 */
public class CandidateLogicImpl implements CandidateLogic {

	CandidateRuleLogic candidateRuleLogic;
	CandidateDao candidateDao;
	RewardDao rewardDao;

	@Inject
	public CandidateLogicImpl(CandidateRuleLogic candidateRuleLogic,
			CandidateDao candidateDao, RewardDao rewardDao) {
		this.candidateRuleLogic = candidateRuleLogic;
		this.candidateDao = candidateDao;
		this.rewardDao = rewardDao;
	}

	@Override
	public void AddCandidateToReward(SysUser caller, String rewardId,
			CandidateRule candidateRule) {
		Reward reward = rewardDao.findById(Reward.class, rewardId);
		Date now = DateUtil.getTime();
		Set<Staff> staffs = candidateRuleLogic
				.getQualifiedStaffsFromCandidateRuleId(candidateRule.getId());
		for (Staff staff : staffs) {
			if(staff.isDeleted()!=1)
			{
				Candidate candidate = new Candidate();
				candidate.setReward(reward);
				candidate.setStaff(staff);
				candidate.setCreatedAt(now);
				candidate.setCreatedBy(caller);
				candidate.setLastModifiedAt(now);
				candidate.setLastModifiedBy(caller);
				candidateDao.save(candidate);
			}
		}
	}

	@Override
	public List<Candidate> getCandidatesFromReward(String rewardId) {
		return candidateDao.findCandidatesByRewardId(rewardId);
	}

	@Override
	public void updateCandidatesCount(List<String> staffIds, String rewardId) {
		List<Candidate> candList=candidateDao.findCandidatesByRewardIdandStaffIds(rewardId, staffIds);
		for (Candidate cand :candList) {
			cand.setNominatecount(cand.getNominatecount()+1);
			candidateDao.update(cand);
		}
//		int xxx=candidateDao.updateCandidatesNominateCount(rewardId, staffIds);
//		System.out.println(xxx);


	}

	@Override
	public List<Candidate> getCandidatesFromRewardAndQueryVo(String rewardId,
			WinnersRecordQueryVo queryVo) {
		return candidateDao.findCandidatesByRewardIdAndQueryVo(rewardId,queryVo);

	}

}
