package com.chinarewards.gwt.elt.server.awardReward;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.reward.search.CandidateParam;
import com.chinarewards.elt.model.reward.search.JudgeParam;
import com.chinarewards.elt.model.reward.search.RewardQueryVo;
import com.chinarewards.elt.service.reward.RewardService;
import com.chinarewards.gwt.elt.client.awardReward.request.AwardRewardInitRequest;
import com.chinarewards.gwt.elt.client.awardReward.request.AwardRewardInitResponse;
import com.chinarewards.gwt.elt.model.nominate.CandidateParamVo;
import com.chinarewards.gwt.elt.model.nominate.JudgeParamVo;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * The handler to correspond the HrRegisterRequest.
 * 
 * @author nicho
 * @since 2011年12月21日 16:16:56
 */
public class AwardRewardActionHandler extends
		BaseActionHandler<AwardRewardInitRequest, AwardRewardInitResponse> {

	@InjectLogger
	Logger logger;

	RewardService rewardService;

	@Inject
	public AwardRewardActionHandler(RewardService rewardService) {
		this.rewardService = rewardService;
	}

	@Override
	public AwardRewardInitResponse execute(AwardRewardInitRequest request,
			ExecutionContext response) throws DispatchException {
		// 获取信息
		RewardQueryVo rewardQueryVo = rewardService
				.fetchEntireRewardQueryVoById(request.getAwardsId());

		AwardRewardInitResponse awardresponse = new AwardRewardInitResponse();

		awardresponse.setRewardId(rewardQueryVo.getRewardId());
		awardresponse.setRewardName(rewardQueryVo.getRewardName());
		awardresponse.setRewardItemName(rewardQueryVo.getRewardItemName());
		awardresponse.setDefinition(rewardQueryVo.getDefinition());
		awardresponse.setStandard(rewardQueryVo.getStandard());
		awardresponse.setHeadcountLimit(rewardQueryVo.getHeadcountLimit());
		awardresponse.setTotalAmtLimit(rewardQueryVo.getTotalAmtLimit());
		awardresponse.setAwardAmt(rewardQueryVo.getAwardAmt());
		awardresponse.setCreatedAt(rewardQueryVo.getCreatedAt());
		awardresponse.setExpectAwardDate(rewardQueryVo.getExpectAwardDate());
		awardresponse.setExpectNominateDate(rewardQueryVo
				.getExpectNominateDate());
		awardresponse.setCreatedStaffName(rewardQueryVo.getCreatedStaffName());
		awardresponse.setAwardMode(rewardQueryVo.getAwardMode());
		awardresponse
				.setAwardingStaffName(rewardQueryVo.getAwardingStaffName());

		List<CandidateParamVo> candidateVoList = new ArrayList<CandidateParamVo>();
		List<JudgeParamVo> judgeVoList = new ArrayList<JudgeParamVo>();

		List<CandidateParam> candidateList = rewardQueryVo.getCandidateList();
		for (int i = 0; i < candidateList.size(); i++) {
			CandidateParamVo cpv = new CandidateParamVo();
			CandidateParam cp = candidateList.get(i);
			cpv.setId(cp.getId());
			cpv.setName(cp.getName());
			cpv.setNominateCount(cp.getNominateCount());
			cpv.setStaffid(cp.getStaffid());
			candidateVoList.add(cpv);
		}
		List<JudgeParam> judgeList = rewardQueryVo.getJudgeList();
		for (int i = 0; i < judgeList.size(); i++) {
			JudgeParamVo jpv = new JudgeParamVo();
			JudgeParam jp = judgeList.get(i);
			jpv.setId(jp.getId());
			jpv.setName(jp.getName());
			jpv.setIsNominate(jp.getIsNominate());
			jpv.setStaffid(jp.getStaffid());
			jpv.setJudgeStatus(jp.getJudgeStatus());
			judgeVoList.add(jpv);

		}

		awardresponse.setJudgeList(judgeVoList);
		awardresponse.setCandidateList(candidateVoList);

		return awardresponse;
	}

	@Override
	public Class<AwardRewardInitRequest> getActionType() {
		return AwardRewardInitRequest.class;
	}

	@Override
	public void rollback(AwardRewardInitRequest request,
			AwardRewardInitResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
