/**
 * 
 */
package com.chinarewards.gwt.elt.server.rewardItem;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.reward.frequency.Frequency;
import com.chinarewards.elt.domain.reward.person.Judge;
import com.chinarewards.elt.domain.reward.rule.CandidateRule;
import com.chinarewards.elt.domain.reward.rule.DirectCandidateData;
import com.chinarewards.elt.domain.reward.rule.DirectCandidateRule;
import com.chinarewards.elt.domain.reward.rule.DobRule;
import com.chinarewards.elt.model.reward.base.RequireAutoAward;
import com.chinarewards.elt.model.reward.base.RequireAutoGenerate;
import com.chinarewards.elt.model.reward.vo.RewardItemStoreVo;
import com.chinarewards.elt.model.reward.vo.RewardItemVo;
import com.chinarewards.elt.service.reward.RewardItemService;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemByIdRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemByIdResponse;
import com.chinarewards.gwt.elt.client.rewards.model.FrequencyClient;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.EveryoneClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.SomeoneClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.client.rewards.model.SpecialCondition;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author Cream
 * @since 0.2.0 2010-12-26
 */
public class SearchRewardsItemByIdHandler
		extends
		BaseActionHandler<SearchRewardsItemByIdRequest, SearchRewardsItemByIdResponse> {
	@InjectLogger
	Logger logger;
	RewardItemService rewardItemService;

	@Inject
	public SearchRewardsItemByIdHandler(RewardItemService rewardItemService) {
		this.rewardItemService = rewardItemService;
	}
	


	@Override
	public SearchRewardsItemByIdResponse execute(
			SearchRewardsItemByIdRequest request, ExecutionContext context)
			throws DispatchException {
		logger.debug(" parameters:{}", request.getId());
		if(request.isItemStore()==false){//是查奖项
		  RewardItemVo rewardsItem = rewardItemService.fetchEntireRewardItemById(request.getId());
		  return new SearchRewardsItemByIdResponse(adapter(rewardItemService,	rewardsItem));
		}else{//是查奖项库
			RewardItemStoreVo rewardsStoreItem = rewardItemService.fetchEntireRewardItemStoreById(request.getId());
			return new SearchRewardsItemByIdResponse(adapterItemStore(rewardItemService,	rewardsStoreItem));
		}
	}

	
	//把奖项的数据转化
	private RewardsItemClient adapter(RewardItemService rewardsItemService,
			RewardItemVo item) {
		RewardsItemClient client = new RewardsItemClient();
		client.setId(item.getId());
		client.setName(item.getName());
		client.setDefinition(item.getDefinition());
		client.setStandard(item.getStandard());
	//	client.setRewardsUnit(item.getRewardsUnit().toString());
		client.setStartTime(item.getItem().getStartTime());
		client.setTmdays(item.getItem().getNominateAheadDays());
		//client.setHasSpecialCondition(item.getItem().g)
		client.setNextPublishTime(item.getItem().getPublishDate());
		
		client.setEnabled(item.getItem().isEnabled());
		client.setTotalJF((int)item.getItem().getTotalAmtLimit());
		client.setRewardsFrom((int)item.getItem().getAwardAmt());
		client.setSizeLimit(item.getItem().getHeadcountLimit());
		client.setExpectAwardDate(item.getItem().getExpectAwardDate());
		client.setNextTime(item.getItem().getExpectAwardDate());
		client.setNextPublishTime(item.getItem().getPublishDate());
		
		
		RequireAutoAward autoEnum = item.getAutoAward();
		if (autoEnum == RequireAutoAward.requireAutoAward) {
			client.setAuto(true);
		} else if (autoEnum == RequireAutoAward.requireNominate) {
			client.setAuto(false);
		}
		
		RequireAutoGenerate period = item.getAutoGenerate();
		if (period == RequireAutoGenerate.requireCyclic) {
			client.setPeriodEnable(true);
		} else if (period == RequireAutoGenerate.requireOneOff) {
			client.setPeriodEnable(false);
		}

		FrequencyHelper helper = new FrequencyHelper();
		
		Frequency units = item.getFrequency();
		FrequencyClient frequency = helper.getFrequencyFromUnitList(rewardsItemService, units);
		client.setFrequency(frequency);

		

		// 候选人员
		CandidateRule itemRule = item.getCandidateRule();
			if (itemRule instanceof DobRule) {
				client.setHasSpecialCondition(true);
				client.setCondition(SpecialCondition.birth);
			} else if (itemRule instanceof DirectCandidateRule) {
				List<DirectCandidateData> directRuleSelecteds = rewardsItemService.findDirectCandidateDataListByDirectRuleId(itemRule.getId());
				logger.debug("itemRuleId:{},directRuleSelecteds size:{}",new Object[] { itemRule.getId(),directRuleSelecteds.size() });
				ParticipateInfoClient participate = null;
				if (isChooseAll(directRuleSelecteds)) {
					participate = new EveryoneClient();
				} else {
					List<OrganicationClient> orgs = getOrgsFromParticipants(directRuleSelecteds);
					participate = new SomeoneClient(orgs);
				}
				client.setParticipateInfo(participate);
			}
			
			// 提名人员
			List<Judge> judges = item.getJudgeList();
			ParticipateInfoClient participate = null;
			List<OrganicationClient> orgs = getOrgsFromJudges(judges);
			participate = new SomeoneClient(orgs);
			client.setTmInfo(participate);
		
		return client;
	}
	//把奖项库的数据转化
	private RewardsItemClient adapterItemStore(RewardItemService rewardsItemService,
			RewardItemStoreVo item) {
		RewardsItemClient client = new RewardsItemClient();
		client.setId(item.getId());
		client.setName(item.getName());
		client.setDefinition(item.getDefinition());
		client.setStandard(item.getStandard());
	//	client.setRewardsUnit(item.getRewardsUnit().toString());
		client.setStartTime(item.getItemStore().getStartTime());
		client.setTmdays(item.getItemStore().getNominateAheadDays());
		//client.setHasSpecialCondition(item.getItem().g)
		client.setNextPublishTime(item.getItemStore().getPublishDate());
		
		client.setTotalJF((int)item.getItemStore().getTotalAmtLimit());
		client.setRewardsFrom((int)item.getItemStore().getAwardAmt());
		client.setSizeLimit(item.getItemStore().getHeadcountLimit());
		client.setExpectAwardDate(item.getItemStore().getExpectAwardDate());
		client.setNextTime(item.getItemStore().getExpectAwardDate());
		client.setNextPublishTime(item.getItemStore().getPublishDate());
			
		RequireAutoAward autoEnum = item.getAutoAward();
		if (autoEnum == RequireAutoAward.requireAutoAward) {
			client.setAuto(true);
		} else if (autoEnum == RequireAutoAward.requireNominate) {
			client.setAuto(false);
		}
		
		RequireAutoGenerate period = item.getAutoGenerate();
		if (period == RequireAutoGenerate.requireCyclic) {
			client.setPeriodEnable(true);
		} else if (period == RequireAutoGenerate.requireOneOff) {
			client.setPeriodEnable(false);
		}

		FrequencyHelper helper = new FrequencyHelper();
		
		Frequency units = item.getFrequency();
		FrequencyClient frequency = helper.getFrequencyFromUnitList(rewardsItemService, units);
		client.setFrequency(frequency);
		// 候选人员
		CandidateRule itemRule = item.getCandidateRule();
			if (itemRule instanceof DobRule) {
				client.setHasSpecialCondition(true);
				client.setCondition(SpecialCondition.birth);
			} else if (itemRule instanceof DirectCandidateRule) {
				List<DirectCandidateData> directRuleSelecteds = rewardsItemService.findDirectCandidateDataListByDirectRuleId(itemRule.getId());
				logger.debug("itemRuleId:{},directRuleSelecteds size:{}",new Object[] { itemRule.getId(),directRuleSelecteds.size() });
				ParticipateInfoClient participate = null;
				if (isChooseAll(directRuleSelecteds)) {
					participate = new EveryoneClient();
				} else {
					List<OrganicationClient> orgs = getOrgsFromParticipants(directRuleSelecteds);
					participate = new SomeoneClient(orgs);
				}
				client.setParticipateInfo(participate);
			}
			
			// 提名人员
			List<Judge> judges = item.getJudgeList();
			ParticipateInfoClient participate = null;
			List<OrganicationClient> orgs = getOrgsFromJudges(judges);
			participate = new SomeoneClient(orgs);
			client.setTmInfo(participate);
		
		return client;
	}

	private boolean isChooseAll(List<DirectCandidateData> participants) {
		for (DirectCandidateData p : participants) {
			if (p.getOrg() instanceof Corporation) {
				return true;
			}
		}

		return false;
	}

	private List<OrganicationClient> getOrgsFromParticipants(List<DirectCandidateData> participants) {
		List<OrganicationClient> orgs = new ArrayList<OrganicationClient>();
		for (DirectCandidateData p : participants) {
			orgs.add(new OrganicationClient(p.getOrg().getId(), p.getOrg().getName()));
		}
		return orgs;
	}
	
	private List<OrganicationClient> getOrgsFromJudges(List<Judge> judge) {
		List<OrganicationClient> orgs = new ArrayList<OrganicationClient>();
		for (Judge p : judge) {
			orgs.add(new OrganicationClient(p.getStaff().getId(), p.getStaff().getName()));
		}
		return orgs;
	}

	@Override
	public Class<SearchRewardsItemByIdRequest> getActionType() {
		return SearchRewardsItemByIdRequest.class;
	}

	@Override
	public void rollback(SearchRewardsItemByIdRequest arg0,
			SearchRewardsItemByIdResponse arg1, ExecutionContext arg2)
			throws DispatchException {
	}

}
