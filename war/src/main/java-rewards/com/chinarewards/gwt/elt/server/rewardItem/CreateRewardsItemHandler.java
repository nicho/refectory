package com.chinarewards.gwt.elt.server.rewardItem;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.base.RewardItemStore;
import com.chinarewards.elt.domain.reward.rule.DobRule;
import com.chinarewards.elt.model.reward.base.RequireAutoAward;
import com.chinarewards.elt.model.reward.base.RequireAutoGenerate;
import com.chinarewards.elt.model.reward.base.RewardItemParam;
import com.chinarewards.elt.model.reward.frequency.DailyVo;
import com.chinarewards.elt.model.reward.frequency.MonthlyVo;
import com.chinarewards.elt.model.reward.frequency.RewardsFrequency;
import com.chinarewards.elt.model.reward.frequency.WeekDays;
import com.chinarewards.elt.model.reward.frequency.WeeklyVo;
import com.chinarewards.elt.model.reward.frequency.YearlyVo;
import com.chinarewards.elt.model.transaction.TransactionUnit;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.reward.RewardItemService;
import com.chinarewards.gwt.elt.client.rewardItem.request.CreateRewardsItemRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.CreateRewardsItemResponse;
import com.chinarewards.gwt.elt.client.rewards.model.DayFrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.FrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.MonthFrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.EveryoneClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.SomeoneClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.client.rewards.model.SpecialCondition;
import com.chinarewards.gwt.elt.client.rewards.model.WeekFrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.YearFrequencyClient;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

public class CreateRewardsItemHandler extends	BaseActionHandler<CreateRewardsItemRequest, CreateRewardsItemResponse> {

	@InjectLogger
	Logger logger;
	RewardItemService rewardItemService;

	@Inject
	public CreateRewardsItemHandler(RewardItemService rewardItemService) {
		this.rewardItemService = rewardItemService;
	}
	@Override
	public Class<CreateRewardsItemRequest> getActionType() {
		return CreateRewardsItemRequest.class;
	}

	@Override
	public CreateRewardsItemResponse execute(CreateRewardsItemRequest action,
			ExecutionContext context) throws DispatchException {
		logger.debug("CreateRewardsItemResponse , parameter:"+ action.getRewardsItem().toString());
		logger.debug("CreateRewardsItemResponse ,rewardId="	+ action.getRewardsItem().getId());
		RewardsItemClient rewardsItemClient = action.getRewardsItem();
		RewardItemParam param = assembleParameter(rewardsItemClient);
		
		logger.debug("GeneratorRewardsItemModel:startTime="	+ param.getStartTime());
		
		UserContext uc = new UserContext();
		uc.setCorporationId(action.getUserSession().getCorporationId());
		uc.setLoginName(action.getUserSession().getLoginName());
		uc.setUserId(action.getUserSession().getToken());
		uc.setUserRoles(UserRoleTool.adaptToRole(action.getUserSession().getUserRoles()));
		if(action.isItemStore()==false){//不是奖项库就保存到奖项表
		  RewardItem createdItem = rewardItemService.saveRewardItem(uc, param);
		  return new CreateRewardsItemResponse(createdItem.getId());
		}else{ //是奖项库就保存到奖项库表
		   RewardItemStore ItemStore = rewardItemService.saveRewardItemStore(uc, param);
		   return new CreateRewardsItemResponse(ItemStore.getId());
		}
	}

	// Convert from RewardsItemClient to GeneratorRewardsItemModel.
	private RewardItemParam assembleParameter(RewardsItemClient client) {
			// 奖项参数
		RewardItemParam parameter = new RewardItemParam();
		parameter.setId(client.getId());
		parameter.setName(client.getName());
		parameter.setTypeId(client.getType().getId());
		parameter.setDefinition(client.getDefinition());
		parameter.setStandard(client.getStandard());
		parameter.setHeadcountLimit(client.getSizeLimit());//人数
		parameter.setTotalAmtLimit(client.getTotalJF());//总积分
		parameter.setAwardAmt(client.getRewardsFrom());//个人要得的积分
		parameter.setBuilderDeptId(client.getBuilderDept());
		parameter.setAccountDeptId(client.getAccountDept());
		parameter.setNominateAheadDays(client.getTmdays());//提名提前的天数
		parameter.setPublishDate(client.getNextPublishTime());	
		parameter.setStartTime(client.getStartTime());
		parameter.setExpectAwardDate(client.getExpectAwardDate());
		
		
		if(client.isPeriodEnable()==true){//如果是周期性的
			// 奖项的频率
			parameter.setFrequency(adapterFrequency( client.getFrequency()));
			
		   parameter.setAutoGenerate(RequireAutoGenerate.requireCyclic);//循环
		}else{
			parameter.setAutoGenerate(RequireAutoGenerate.requireOneOff);//只生成一次
			// 奖项的频率没有
			parameter.setFrequency(null);
		}
		System.out.println("parameter.getFrequency()"+parameter.getFrequency());
		// 奖励员工的单位（元/缤纷）,如果没值默认BINFEN
		if (StringUtil.isEmpty(client.getRewardsUnit())) {
			parameter.setAwardUnit(TransactionUnit.BEANPOINTS);
		} else {
			parameter.setAwardUnit(TransactionUnit.valueOf(client.getRewardsUnit()));
		}
		
		

		
		
		// 入围者
		// -- 所有人candidateList
		ParticipateInfoClient participate = client.getParticipateInfo();
		List<String> orgIds = new ArrayList<String>();
		if (participate instanceof EveryoneClient) {
			orgIds.add(((EveryoneClient) participate).getCorporationId());
		} else if (participate instanceof SomeoneClient) {
			List<OrganicationClient> orgs = ((SomeoneClient) participate)
					.getOrganizations();
			for (OrganicationClient org : orgs) {
				orgIds.add(org.getId());
			}
		}
        parameter.setCandidateList(orgIds);
        
     // 提名者

        ParticipateInfoClient nominater = client.getTmInfo();
     	List<String> orgId = new ArrayList<String>();
     	List<OrganicationClient> orgs = ((SomeoneClient) nominater).getOrganizations();
     		for (OrganicationClient org : orgs) {
     			
     			orgId.add(org.getId());
     		}
     	parameter.setJudgeIds(orgId);        //提名人列表
            
		
		// 生日奖
		if (client.isHasSpecialCondition()
				&& client.getCondition() == SpecialCondition.birth) {
			DobRule dobRule = new DobRule();// 有疑问，为什么没有用到，如何用
			parameter.setDob(true);
			
		}
		// 自动奖项
		if (client.isAuto()) {
			parameter.setAutoAward(RequireAutoAward.requireAutoAward);
		}else if(orgId.size()>0)
		{
			parameter.setAutoAward(RequireAutoAward.requireNominate);//要求提名
		}else {
			parameter.setAutoAward(RequireAutoAward.requireNone);//没有要求
		}
		
		return parameter;
	}

	

	private RewardsFrequency adapterFrequency(FrequencyClient frequency) {

		RewardsFrequency ret = null;

		if (frequency instanceof DayFrequencyClient) {
			// daily
			ret = new DailyVo(((DayFrequencyClient) frequency).getInterval());
		} else if (frequency instanceof WeekFrequencyClient) {
			// weekly
			
			WeekDays[] days = new WeekDays[7];
			List<Integer> weekDays = ((WeekFrequencyClient) frequency).getWeekDays();
			List<WeekDays> newWeekDays = new ArrayList<WeekDays>();
			for (int i : weekDays) {
				newWeekDays.add(getWeekConstbyInt(i));
			}

			WeekFrequencyClient weekFrequencyClient = (WeekFrequencyClient) frequency;

			ret = new WeeklyVo(weekFrequencyClient.getInterval(),newWeekDays.toArray(new WeekDays[0]));
			
	      

		} else if (frequency instanceof MonthFrequencyClient) {

			MonthFrequencyClient casted = (MonthFrequencyClient) frequency;
			ret = new MonthlyVo(casted.getInterval(), casted.getMonthDay());

		} else if (frequency instanceof YearFrequencyClient) {

			YearFrequencyClient casted = (YearFrequencyClient) frequency;
			ret = new YearlyVo(casted.getInterval(), casted.getYearMonth(),
					casted.getYearMonthDay());

		}

		return ret;
	}

	private WeekDays getWeekConstbyInt(int i) {
		WeekDays wc = null;
		switch (i) {
		case 1:
			wc = WeekDays.MON;
			break;
		case 2:
			wc = WeekDays.TUS;
			break;
		case 3:
			wc = WeekDays.WEN;
			break;
		case 4:
			wc = WeekDays.THUR;
			break;
		case 5:
			wc = WeekDays.FRI;
			break;
		case 6:
			wc = WeekDays.SAT;
			break;
		case 7:
			wc = WeekDays.SUN;
		}

		return wc;
	}

	@Override
	public void rollback(CreateRewardsItemRequest action,
			CreateRewardsItemResponse result, ExecutionContext context)
			throws DispatchException {

	}

}
