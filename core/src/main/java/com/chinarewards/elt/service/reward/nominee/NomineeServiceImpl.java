package com.chinarewards.elt.service.reward.nominee;

import java.util.List;

import com.chinarewards.elt.domain.reward.person.Candidate;
import com.chinarewards.elt.domain.reward.person.NomineeLot;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.reward.exception.JudgeException;
import com.chinarewards.elt.model.vo.WinnersRecordQueryVo;
import com.chinarewards.elt.service.reward.rule.CandidateLogic;
import com.chinarewards.elt.service.reward.rule.NomineeLogic;
import com.chinarewards.elt.service.user.UserLogic;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Transactional
public class NomineeServiceImpl implements NomineeService {
	private final NomineeLogic nomineeLogic;
	private final UserLogic userLogic;
	private final CandidateLogic candidateLogic;

	@Inject
	public NomineeServiceImpl(NomineeLogic nomineeLogic, UserLogic userLogic,
			CandidateLogic candidateLogic) {
		this.nomineeLogic = nomineeLogic;
		this.userLogic = userLogic;

		this.candidateLogic = candidateLogic;

	}

	@Override
	public NomineeLot addNomineeLotToReward(String rewardId,
			List<String> staffIds, String nowUserId) throws JudgeException {

		// if (em.getTransaction().isActive() != true) {
		// em.getTransaction().begin();
		// }
		SysUser caller = userLogic.findUserById(nowUserId);

		NomineeLot lot = nomineeLogic.addNomineeLotToReward(caller, rewardId,
				staffIds);

		// 被提名者,提名次数的调整
		candidateLogic.updateCandidatesCount(staffIds, rewardId);

		// em.getTransaction().commit();

		return lot;
	}

	@Override
	public PageStore<Candidate> getCandidatesFromReward(String rewardId) {

		List<Candidate> candidateList = candidateLogic
				.getCandidatesFromReward(rewardId);

		PageStore<Candidate> storeVo = new PageStore<Candidate>();
		storeVo.setResultCount(candidateList.size());
		storeVo.setResultList(candidateList);

		return storeVo;

	}

	@Override
	public PageStore<Candidate> getCandidatesFromRewardAndQueryVo(
			String rewardId, WinnersRecordQueryVo queryVo) {

		List<Candidate> candidateList = candidateLogic.getCandidatesFromRewardAndQueryVo(rewardId, queryVo);
		queryVo.setPaginationDetail(null);
		int sizeList = candidateLogic.getCandidatesFromRewardAndQueryVo(rewardId, queryVo).size();

		PageStore<Candidate> storeVo = new PageStore<Candidate>();
		storeVo.setResultCount(sizeList);
		storeVo.setResultList(candidateList);

		return storeVo;

	}

}
