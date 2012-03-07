package com.chinarewards.elt.service.reward.nominee;

import java.util.List;

import com.chinarewards.elt.domain.reward.person.Candidate;
import com.chinarewards.elt.domain.reward.person.NomineeLot;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.reward.exception.JudgeException;
import com.chinarewards.elt.model.vo.WinnersRecordQueryVo;

public interface NomineeService {
	public NomineeLot addNomineeLotToReward(String rewardId,List<String> staffIds,String nowUserId) throws JudgeException;
	public PageStore<Candidate> getCandidatesFromReward(String rewardId);
	public PageStore<Candidate> getCandidatesFromRewardAndQueryVo(String rewardId,WinnersRecordQueryVo queryVo);
}
