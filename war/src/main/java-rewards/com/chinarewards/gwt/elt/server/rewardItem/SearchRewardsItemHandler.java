/**
 * 
 */
package com.chinarewards.gwt.elt.server.rewardItem;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.reward.base.RequireAutoAward;
import com.chinarewards.elt.model.reward.base.RequireAutoGenerate;
import com.chinarewards.elt.model.reward.search.RewardItemSearchVo;
import com.chinarewards.elt.model.reward.vo.RewardItemVo;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.reward.RewardItemService;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemResponse;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemCriteria;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * @author Cream
 * @since 0.2.0 2010-12-26
 */
public class SearchRewardsItemHandler extends	BaseActionHandler<SearchRewardsItemRequest, SearchRewardsItemResponse> {

	@InjectLogger
	Logger logger;
	RewardItemService rewardItemService;

	@Inject
	public SearchRewardsItemHandler(RewardItemService rewardItemService) {
		this.rewardItemService = rewardItemService;
	}
	

	@Override
	public SearchRewardsItemResponse execute(SearchRewardsItemRequest request,
			ExecutionContext context) throws DispatchException {
		logger.debug(" request parameters: {}", request);

		SearchRewardsItemResponse resp = new SearchRewardsItemResponse();
     
		RewardsItemCriteria rewardsItemClient = request.getRewardsItem();
		RewardItemSearchVo model = adapter(rewardsItemClient);
		UserContext uc = new UserContext();
		uc.setCorporationId(request.getUserSession().getCorporationId());
		uc.setLoginName(request.getUserSession().getLoginName());
		uc.setUserRoles(UserRoleTool.adaptToRole(request.getUserSession().getUserRoles()));
		uc.setUserId(request.getUserSession().getToken());
		PageStore<RewardItemVo> items = rewardItemService.fetchRewardItems(uc,model);

		resp.setTotal(items.getResultCount());
		resp.setResult(adapter(items.getResultList(), rewardItemService));

		return resp;
	}

	// Convert from RewardsItemSearchCriteria to GeneratorRewardsItemModel.
	//从奖项查询的VO转为model的VO,主要是传查询的条件
	private RewardItemSearchVo adapter(RewardsItemCriteria criteria) {
		RewardItemSearchVo model = new RewardItemSearchVo();
		model.setAccountDeptName(criteria.getAccountDeptName());
		model.setBuildDeptName(criteria.getBuildDeptName());
		model.setCorporationId(criteria.getCorporationId());
		model.setDefinition(criteria.getDefinition());
		model.setDeptIds(criteria.getDeptIds());
		model.setId(criteria.getId());
		model.setName(criteria.getName());
		model.setDepartmentId(criteria.getDepartmentId());
        model.setEnabled(criteria.isEnabled());
		model.setCreateTime(criteria.getCreateTime());
		model.setCreateTimeEnd(criteria.getCreateTimeEnd());
		model.setStandard(criteria.getStandard());
		model.setStartTime(criteria.getStartTime());
		model.setTypeId(criteria.getTypeId());
		model.setTypeName(criteria.getTypeName());

		if (criteria.getPagination() != null) {
			PaginationDetail paginationDetail = new PaginationDetail();
			paginationDetail.setStart(criteria.getPagination().getStart());
			paginationDetail.setLimit(criteria.getPagination().getLimit());
			model.setPaginationDetail(paginationDetail);
		}

		if (criteria.getSorting() != null) {
			SortingDetail sortingDetail = new SortingDetail();
			sortingDetail.setSort(criteria.getSorting().getSort());
			sortingDetail.setDirection(criteria.getSorting().getDirection());
			model.setSortingDetail(sortingDetail);
		}
		return model;
	}
    //从服务端得到的数据到客户端在列表显示的数据
	private List<RewardsItemClient> adapter(List<RewardItemVo> items,RewardItemService rewardsItemService) {
		List<RewardsItemClient> resultList = new ArrayList<RewardsItemClient>();

		for (RewardItemVo item : items) {
			RewardsItemClient client = new RewardsItemClient();
			client.setId(item.getId());
			client.setName(item.getName());
			client.setAuto(item.getAutoAward() == RequireAutoAward.requireAutoAward);//自动奖
			client.setDegree(item.getItem().getDegree());
			client.setPeriodEnable(item.getAutoGenerate()==RequireAutoGenerate.requireCyclic);//周期性
			client.setStartTime(item.getItem().getStartTime());
			client.setCreateAt(item.getItem().getCreatedAt());
			
			client.setNextPublishTime(item.getExpectAwardDate());
			client.setEnabled(item.isEnabled());
			resultList.add(client);
		}

		return resultList;
	}

	@Override
	public Class<SearchRewardsItemRequest> getActionType() {
		return SearchRewardsItemRequest.class;
	}

	@Override
	public void rollback(SearchRewardsItemRequest arg0,
			SearchRewardsItemResponse arg1, ExecutionContext arg2)
			throws DispatchException {
	}

}
