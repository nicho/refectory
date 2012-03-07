package com.chinarewards.gwt.elt.server.nominate;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.reward.search.CandidateParam;
import com.chinarewards.elt.model.reward.search.JudgeParam;
import com.chinarewards.elt.model.reward.search.RewardQueryVo;
import com.chinarewards.elt.service.reward.RewardService;
import com.chinarewards.gwt.elt.client.nominate.NominateInitRequest;
import com.chinarewards.gwt.elt.client.nominate.NominateInitResponse;
import com.chinarewards.gwt.elt.model.nominate.CandidateParamVo;
import com.chinarewards.gwt.elt.model.nominate.JudgeParamVo;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * The handler to correspond the HrRegisterRequest.
 * 
 * @author nicho
 * @since 2011年12月7日 09:41:42
 */
public class NominateActionHandler extends
		BaseActionHandler<NominateInitRequest, NominateInitResponse> {

	@InjectLogger
	Logger logger;

	RewardService rewardService;

	@Inject
	public NominateActionHandler(RewardService rewardService) {
		this.rewardService = rewardService;
	}

	@Override
	public NominateInitResponse execute(NominateInitRequest request,
			ExecutionContext response) throws DispatchException {
		// 获取信息
		RewardQueryVo rewardQueryVo = rewardService.fetchEntireRewardQueryVoById(request.getAwardsId());
	//	RewardQueryVo rewardQueryVo =getTestData();
		
		NominateInitResponse Nomresponse = new NominateInitResponse();

		Nomresponse.setRewardId(rewardQueryVo.getRewardId());
		Nomresponse.setRewardName(rewardQueryVo.getRewardName());
		Nomresponse.setRewardItemName(rewardQueryVo.getRewardItemName());
		Nomresponse.setDefinition(rewardQueryVo.getDefinition());
		Nomresponse.setStandard(rewardQueryVo.getStandard());
		Nomresponse.setHeadcountLimit(rewardQueryVo.getHeadcountLimit());
		Nomresponse.setTotalAmtLimit(rewardQueryVo.getTotalAmtLimit());
		Nomresponse.setAwardAmt(rewardQueryVo.getAwardAmt());
		Nomresponse.setCreatedAt(rewardQueryVo.getCreatedAt());
		Nomresponse.setExpectAwardDate(rewardQueryVo.getExpectAwardDate());
		Nomresponse
				.setExpectNominateDate(rewardQueryVo.getExpectNominateDate());
		Nomresponse.setCreatedStaffName(rewardQueryVo.getCreatedStaffName());
		Nomresponse.setAwardMode(rewardQueryVo.getAwardMode());
		Nomresponse.setAwardingStaffName(rewardQueryVo.getAwardingStaffName());

		// List<String> nominateList=new ArrayList<String>();//提名人列表
		// nominateList.add("1号提名人");
		// nominateList.add("2号提名人");
		// List<String> candidateList=new ArrayList<String>();//候选人列表
		// candidateList.add("Jol.liu");
		// candidateList.add("Alice.liu");
		// candidateList.add("Mervyn");
		// candidateList.add("Nevy");
		// candidateList.add("It.Lou");
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

		Nomresponse.setJudgeList(judgeVoList);
		Nomresponse.setCandidateList(candidateVoList);

		return Nomresponse;
	}

	@Override
	public Class<NominateInitRequest> getActionType() {
		return NominateInitRequest.class;
	}

	@Override
	public void rollback(NominateInitRequest request,
			NominateInitResponse response, ExecutionContext context)
			throws DispatchException {
	}
	
	
//	private RewardQueryVo getTestData(){
//		RewardQueryVo rewardQueryVo=new RewardQueryVo();
//		rewardQueryVo.setRewardId("奖励ID");
//		rewardQueryVo.setRewardName("奖励名称2011年12月16日 13:35:58");
//		rewardQueryVo.setRewardItemName("奖项名称2011年12月16日 13:36:13");
//		rewardQueryVo.setDefinition("为激励ELT产品开发团队，提高产品开发效率，设立这个奖项2011年12月16日 14:09:25");
//		rewardQueryVo.setStandard("12月23日前完成1.5版本所有功能并正式上线2011年12月16日 14:09:29");
//		rewardQueryVo.setHeadcountLimit(8);
//		rewardQueryVo.setTotalAmtLimit(8000);
//		rewardQueryVo.setAwardAmt(1000);
//		rewardQueryVo.setCreatedAt(new Date());
//		rewardQueryVo.setExpectAwardDate(new Date());
//		rewardQueryVo.setExpectNominateDate(new Date());
//		rewardQueryVo.setCreatedStaffName("创建人是谁");
//		rewardQueryVo.setAwardMode("奖励类型:自动奖,一次性.");//wanting......
//		rewardQueryVo.setAwardingStaffName("颁奖者是谁");//wanting.......same CreateStaff
//		
//		List<CandidateParam> candidateListParam=new ArrayList<CandidateParam>();
//		for (int i = 0; i < 5; i++) {
//		
//			CandidateParam candparam=new CandidateParam();
//			candparam.setId(i+"");
//			candparam.setName("被提名者"+i+"号");
//			candparam.setNominateCount(i+1);
//			candidateListParam.add(candparam);
//		}
//		//设置被提名人
//		rewardQueryVo.setCandidateList(candidateListParam);
//		
//		
//		List<JudgeParam> JudgeListParam=new ArrayList<JudgeParam>();
//		for (int i = 0; i < 3; i++) {
//			JudgeParam judgeParam=new JudgeParam();
//			judgeParam.setId(i+"");
//			judgeParam.setName("提名者"+i+"号");
//			judgeParam.setIsNominate("NONE");
//			JudgeListParam.add(judgeParam);
//		}
//		
//		//设置提名人
//		rewardQueryVo.setJudgeList(JudgeListParam);
//		return rewardQueryVo;
//	}

}
