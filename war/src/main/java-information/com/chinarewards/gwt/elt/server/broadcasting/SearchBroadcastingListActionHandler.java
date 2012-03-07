package com.chinarewards.gwt.elt.server.broadcasting;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.information.Broadcasting;
import com.chinarewards.elt.model.broadcast.BroadcastQueryListCriteria;
import com.chinarewards.elt.model.broadcast.BroadcastQueryListVo;
import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.information.BroadcastingStatus;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.broadcast.BroadcastService;
import com.chinarewards.gwt.elt.client.broadcasting.model.BroadcastingListClient;
import com.chinarewards.gwt.elt.client.broadcasting.model.BroadcastingListCriteria.BroadcastingCategory;
import com.chinarewards.gwt.elt.client.broadcasting.request.SearchBroadcastingListRequest;
import com.chinarewards.gwt.elt.client.broadcasting.request.SearchBroadcastingListResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * The handler to correspond the SearchBroadcastingListRequest.
 * 
 * @author nicho
 * @since 2011年12月7日 09:41:42
 */
public class SearchBroadcastingListActionHandler extends
		BaseActionHandler<SearchBroadcastingListRequest, SearchBroadcastingListResponse> {

	@InjectLogger
	Logger logger;

	BroadcastService broadcastService;


	@Inject
	public SearchBroadcastingListActionHandler(BroadcastService broadcastService) {
		this.broadcastService = broadcastService;
	}

	@Override
	public SearchBroadcastingListResponse execute(SearchBroadcastingListRequest request,
			ExecutionContext response) throws DispatchException {

		SearchBroadcastingListResponse staffResponse = new SearchBroadcastingListResponse();
		BroadcastQueryListCriteria criteria=new BroadcastQueryListCriteria();
		if (request.getCriteria().getPagination() != null) {
			PaginationDetail detail = new PaginationDetail();
			detail.setLimit(request.getCriteria().getPagination().getLimit());
			detail.setStart(request.getCriteria().getPagination().getStart());

			criteria.setPaginationDetail(detail);
		}

		if (request.getCriteria().getSorting() != null) {
			SortingDetail sortingDetail = new SortingDetail();
			sortingDetail.setSort(request.getCriteria().getSorting().getSort());
			sortingDetail.setDirection(request.getCriteria().getSorting().getDirection());
			criteria.setSortingDetail(sortingDetail);
		}
		if(!StringUtil.isEmpty(request.getCriteria().getCorporationId()))
			criteria.setCorporationId(request.getCriteria().getCorporationId());
		if(!StringUtil.isEmpty(request.getCriteria().getCreatedByUserName()))
			criteria.setCreatedByUserName(request.getCriteria().getCreatedByUserName());
		if(request.getCriteria().getBroadcastingTimeStart()!=null)
			criteria.setBroadcastingTimeStart(request.getCriteria().getBroadcastingTimeStart());
		if(request.getCriteria().getBroadcastingTimeEnd()!=null)
			criteria.setBroadcastingTimeEnd(request.getCriteria().getBroadcastingTimeEnd());
		if(request.getCriteria().getStatus()!=null)
			criteria.setStatus(BroadcastingStatus.valueOf(request.getCriteria().getStatus().toString()));
		
		UserContext context=new UserContext();
		context.setCorporationId(request.getSession().getCorporationId());
		context.setUserId(request.getSession().getToken());
		context.setLoginName(request.getSession().getLoginName());
		context.setUserRoles(UserRoleTool.adaptToRole(request.getSession().getUserRoles()));
		

		BroadcastQueryListVo result=broadcastService.queryBroadcastList(criteria);
		
		List<BroadcastingListClient> lt=new ArrayList<BroadcastingListClient>();
		for (Broadcasting broadcast:result.getResultList()) {
			BroadcastingListClient client=new BroadcastingListClient();
			client.setId(broadcast.getId());
			client.setNumber(broadcast.getNumber());
			client.setContent(broadcast.getContent());
			client.setBroadcastingTime(broadcast.getBroadcastingTimeStart());
			client.setCreatedByUserName(broadcast.getCreatedBy().getStaff().getName());
			client.setReplyNumber(broadcast.getReplyNumber());
			client.setStatus(com.chinarewards.gwt.elt.client.broadcasting.model.BroadcastingListCriteria.BroadcastingStatus.valueOf(broadcast.getStatus().toString()));
			client.setCategory(BroadcastingCategory.valueOf(broadcast.getCategory().toString()));
			client.setAllowreplies(broadcast.isAllowreplies());
			lt.add(client);
		}
		staffResponse.setResult(lt);
		staffResponse.setTotal(result.getTotal());
		
		return staffResponse;
	}

	@Override
	public Class<SearchBroadcastingListRequest> getActionType() {
		return SearchBroadcastingListRequest.class;
	}

	@Override
	public void rollback(SearchBroadcastingListRequest request,
			SearchBroadcastingListResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
