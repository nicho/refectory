package com.chinarewards.gwt.elt.server.staffHeavenIndex;

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
import com.chinarewards.gwt.elt.client.broadcasting.model.BroadcastingListCriteria.BroadcastingCategory;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.model.StaffHeavenIndexClient;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.request.StaffHeavenIndexRequest;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.request.StaffHeavenIndexResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * The handler to correspond the StaffHeavenIndexRequest.
 * 
 * @author nicho
 * @since 2011年12月7日 09:41:42
 */
public class StaffHeavenIndexActionHandler extends
		BaseActionHandler<StaffHeavenIndexRequest, StaffHeavenIndexResponse> {

	@InjectLogger
	Logger logger;

	BroadcastService broadcastService;


	@Inject
	public StaffHeavenIndexActionHandler(BroadcastService broadcastService) {
		this.broadcastService = broadcastService;
	}

	@Override
	public StaffHeavenIndexResponse execute(StaffHeavenIndexRequest request,
			ExecutionContext response) throws DispatchException {

		StaffHeavenIndexResponse staffResponse = new StaffHeavenIndexResponse();
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
		if(request.getCriteria().getCategory()!=null)
			criteria.setCategory(com.chinarewards.elt.model.information.BroadcastingCategory.valueOf(request.getCriteria().getCategory().toString()));
		if(!StringUtil.isEmpty(request.getCriteria().getQueryKey()))
			criteria.setQueryKey(request.getCriteria().getQueryKey());
		if(!StringUtil.isEmpty(request.getCriteria().getStaffId()))
			criteria.setStaffId(request.getCriteria().getStaffId());
		if(!StringUtil.isEmpty(request.getCriteria().getRecevingStaffId()))
			criteria.setRecevingStaffId(request.getCriteria().getRecevingStaffId());
		
		
		if(request.getCriteria().isNowDate()==true)
		{
			criteria.setNowDate(true);
		}
		UserContext context=new UserContext();
		context.setCorporationId(request.getSession().getCorporationId());
		context.setUserId(request.getSession().getToken());
		context.setLoginName(request.getSession().getLoginName());
		context.setUserRoles(UserRoleTool.adaptToRole(request.getSession().getUserRoles()));
		
		//接收用户ID
		criteria.setReceivingUserId(context.getUserId());
		BroadcastQueryListVo result=broadcastService.queryBroadcastList(criteria);
		
		List<StaffHeavenIndexClient> lt=new ArrayList<StaffHeavenIndexClient>();
		for (Broadcasting broadcast:result.getResultList()) {
			StaffHeavenIndexClient client=new StaffHeavenIndexClient();
			client.setId(broadcast.getId());
			client.setNumber(broadcast.getNumber());
			client.setContent(broadcast.getContent());
			client.setBroadcastingTime(broadcast.getCreatedAt());
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
	public Class<StaffHeavenIndexRequest> getActionType() {
		return StaffHeavenIndexRequest.class;
	}

	@Override
	public void rollback(StaffHeavenIndexRequest request,
			StaffHeavenIndexResponse response, ExecutionContext context)
			throws DispatchException {
	}

}
