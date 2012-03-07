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
import com.chinarewards.elt.model.reward.vo.RewardItemVo;
import com.chinarewards.elt.service.reward.RewardItemService;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemViewRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemViewResponse;
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
public class SearchRewardsItemViewHandler
		extends
		BaseActionHandler<SearchRewardsItemViewRequest, SearchRewardsItemViewResponse> {
	@InjectLogger
	Logger logger;
	RewardItemService rewardItemService;

	@Inject
	public SearchRewardsItemViewHandler(RewardItemService rewardItemService) {
		this.rewardItemService = rewardItemService;
	}
	


	@Override
	public SearchRewardsItemViewResponse execute(
			SearchRewardsItemViewRequest request, ExecutionContext context)
			throws DispatchException {
		logger.debug(" parameters:{}", request.getId());
		
		RewardItemVo rewardsItem = rewardItemService.fetchEntireRewardItemById(request.getId());
		return new SearchRewardsItemViewResponse(adapter(rewardItemService,
				rewardsItem));
	}

	

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
				//public CandidateRule findCandidateRuleFromRewardItem(String rewardItemId)
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
			orgs.add(new OrganicationClient(p.getOrg().getId(), p
					.getOrg().getName()));
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
	public Class<SearchRewardsItemViewRequest> getActionType() {
		return SearchRewardsItemViewRequest.class;
	}

	@Override
	public void rollback(SearchRewardsItemViewRequest arg0,
			SearchRewardsItemViewResponse arg1, ExecutionContext arg2)
			throws DispatchException {
	}

}
