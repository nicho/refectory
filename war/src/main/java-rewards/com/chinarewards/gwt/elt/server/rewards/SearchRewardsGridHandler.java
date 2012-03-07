package com.chinarewards.gwt.elt.server.rewards;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.reward.person.Judge;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.reward.search.RewardGridSearchVo;
import com.chinarewards.elt.model.reward.vo.RewardGridVo;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.reward.RewardGridService;
import com.chinarewards.elt.util.StringUtil;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.SomeoneClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsGridClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsGridCriteria;
import com.chinarewards.gwt.elt.client.rewards.request.SearchRewardsGridRequest;
import com.chinarewards.gwt.elt.client.rewards.request.SearchRewardsGridResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * 加载奖励小控件
 * 
 * @author yanrui
 */
public class SearchRewardsGridHandler extends
		BaseActionHandler<SearchRewardsGridRequest, SearchRewardsGridResponse> {

	@InjectLogger
	Logger logger;

	RewardGridService rewardGridService;

	@Inject
	public SearchRewardsGridHandler(RewardGridService rewardGridService) {
		this.rewardGridService = rewardGridService;
	}

	@Override
	public SearchRewardsGridResponse execute(SearchRewardsGridRequest request,
			ExecutionContext context) throws DispatchException {

		SearchRewardsGridResponse resp = new SearchRewardsGridResponse();

		RewardsGridCriteria criteria = request.getCriteria();
		criteria.setStaffId(request.getSession().getStaffId());//

		UserContext uc = new UserContext();
		uc.setCorporationId(request.getSession().getCorporationId());
		uc.setUserId(request.getSession().getToken());
		uc.setLoginName(request.getSession().getLoginName());
		uc.setUserRoles(UserRoleTool.adaptToRole(request.getSession()
				.getUserRoles()));

		PageStore<RewardGridVo> rewardsPage = executeQuery(uc, criteria);

		if (rewardsPage != null) {

			List<RewardsGridClient> clientList = adapter(rewardsPage
					.getResultList());
			resp.setTotal(clientList.size());
			resp.setResult(clientList);
		}

		return resp;
	}

	private PageStore<RewardGridVo> executeQuery(UserContext uc,
			RewardsGridCriteria criteria) {
		PageStore<RewardGridVo> rewardsPage = null;

		RewardGridSearchVo searchVo = adapterQuery(criteria);//AdapterQuery

		String thisAction = searchVo.getThisAction();

		if (StringUtil.isEmptyString(thisAction)) {
			rewardsPage = new PageStore<RewardGridVo>();
		} else {
			if ("Rewards_STAFF".equals(thisAction)) {
				rewardsPage = rewardGridService
						.fetchRewards_STAFF(uc, searchVo);
			} else if ("Rewards_STAFF_GETED".equals(thisAction)) {
				rewardsPage = rewardGridService.fetchRewards_STAFF_GETED(uc,
						searchVo);
			} else if ("Rewards_ALL".equals(thisAction)) {
				rewardsPage = rewardGridService.fetchRewards_ALL(uc, searchVo);
			} else if ("RewardsItem_STAFF".equals(thisAction)) {
				rewardsPage = rewardGridService.fetchRewardsItem_STAFF(uc,
						searchVo);
			} else if ("RewardsItem_STAFF_PARTAKE".equals(thisAction)) {
				rewardsPage = rewardGridService.fetchRewardsItem_STAFF_PARTAKE(
						uc, searchVo);
			} else if ("RewardsItem_STAFF_RUSH".equals(thisAction)) {
				rewardsPage = rewardGridService.fetchRewardsItem_STAFF_RUSH(uc,
						searchVo);
			} else if ("RewardsItem_COMPANY_OTHER".equals(thisAction)) {
				rewardsPage = rewardGridService.fetchRewardsItem_COMPANY_OTHER(
						uc, searchVo);
			} else if ("RewardsItem_ALL".equals(thisAction)) {
				rewardsPage = rewardGridService.fetchRewardsItem_ALL(uc,
						searchVo);
			}
		}

		return rewardsPage;
	}

	// 从服务端得到的数据到客户端在列表显示的数据
	public static List<RewardsGridClient> adapter(List<RewardGridVo> rewardsList) {
		if (null == rewardsList) {
			return null;
		}

		List<RewardsGridClient> clientList = new ArrayList<RewardsGridClient>();

		for (int i = 0; i < rewardsList.size(); i++) {
			RewardGridVo rewardGridVo = rewardsList.get(i);
			if (rewardGridVo != null) {
				RewardsGridClient client = new RewardsGridClient();
				client.setRewardsId(rewardGridVo.getRewardId());
				client.setRewardsName(rewardGridVo.getRewardName());
				client.setRewardsDate(rewardGridVo.getRewardsDate());
				client.setRewardsItemId(rewardGridVo.getRewardItemId());
				client.setRewardsItemName(rewardGridVo.getRewardItemName());
				client.setRewardsItemCreateBy(rewardGridVo
						.getRewardsItemCreateBy());// 奖项创建人
				client.setAwardAmt(StringUtil.subZeroAndDot(rewardGridVo.getAwardAmt()+""));
				client.setAwardName(rewardGridVo.getAwardName());// 颁奖人
				client.setRewardStatusName(rewardGridVo.getRewardStatusName());

				client.setCorporationId(rewardGridVo.getCorporationId());

				client.setWinnersName(rewardGridVo.getWinnersName());// 获奖人

				client.setNominateName(rewardGridVo.getNominateName());
				// 提名人员
				List<Judge> judges = rewardGridVo.getJudgeList();
				ParticipateInfoClient participate = null;
				List<OrganicationClient> orgs = getOrgsFromJudges(judges);
				participate = new SomeoneClient(orgs);
				client.setTmInfo(participate);

				client.setNominateCount(rewardGridVo.getNominateCount());

				clientList.add(client);
			}
		}
		return clientList;
	}

	// 从奖项查询的VO转为model的VO,主要是传查询的条件
	public static RewardGridSearchVo adapterQuery(RewardsGridCriteria criteria) {
		RewardGridSearchVo searchVo = new RewardGridSearchVo();

		searchVo.setThisAction(criteria.getThisAction());

		searchVo.setCorporationId(criteria.getCorporationId());
		searchVo.setStaffId(criteria.getStaffId());
		searchVo.setStaffName(criteria.getStaffName());
		searchVo.setRewardItemId(criteria.getRewardsItemId());
		searchVo.setRewardsDate(criteria.getRewardsDate());

		searchVo.setRewardsType(criteria.getRewardsType());
		
		if (criteria.getPagination() != null) {
			PaginationDetail paginationDetail = new PaginationDetail();
			paginationDetail.setStart(criteria.getPagination().getStart());
			paginationDetail.setLimit(criteria.getPagination().getLimit());
			searchVo.setPaginationDetail(paginationDetail);
		}

		if (criteria.getSorting() != null) {
			SortingDetail sortingDetail = new SortingDetail();
			sortingDetail.setSort(criteria.getSorting().getSort());
			sortingDetail.setDirection(criteria.getSorting().getDirection());
			searchVo.setSortingDetail(sortingDetail);
		}

		return searchVo;
	}

	private static List<OrganicationClient> getOrgsFromJudges(
			List<Judge> judgeList) {
		List<OrganicationClient> orgs = new ArrayList<OrganicationClient>();
		if (judgeList != null) {
			for (Judge judge : judgeList) {
				if (judge != null) {
					orgs.add(new OrganicationClient(judge.getStaff().getId(),
							judge.getStaff().getName()));
				}
			}
		}

		return orgs;
	}

	@Override
	public Class<SearchRewardsGridRequest> getActionType() {
		return SearchRewardsGridRequest.class;
	}

	@Override
	public void rollback(SearchRewardsGridRequest req,
			SearchRewardsGridResponse resp, ExecutionContext cxt)
			throws DispatchException {
	}
}
