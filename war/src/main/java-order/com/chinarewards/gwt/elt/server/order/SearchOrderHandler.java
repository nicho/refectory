package com.chinarewards.gwt.elt.server.order;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.gift.search.GiftListVo;
import com.chinarewards.elt.model.order.search.OrderListVo;
import com.chinarewards.elt.model.order.search.OrderStatus;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.order.OrderService;
import com.chinarewards.gwt.elt.adapter.order.OrderAdapter;
import com.chinarewards.gwt.elt.client.order.model.OrderSearchVo;
import com.chinarewards.gwt.elt.client.order.request.SearchOrderRequest;
import com.chinarewards.gwt.elt.client.order.request.SearchOrderResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.chinarewards.gwt.elt.util.UserRoleTool;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2012年1月10日 16:09:07
 */
public class SearchOrderHandler extends
		BaseActionHandler<SearchOrderRequest, SearchOrderResponse> {

	@InjectLogger
	Logger logger;

	OrderService orderService;

	@Inject
	public SearchOrderHandler(OrderService orderService) {
		this.orderService = orderService;

	}

	@Override
	public SearchOrderResponse execute(SearchOrderRequest request,
			ExecutionContext context) throws DispatchException {

		SearchOrderResponse resp = new SearchOrderResponse();

		OrderSearchVo orderSeacherVo = request.getOrderSearchVo();
		OrderListVo orderListVo = adapter(orderSeacherVo);//从客户端转到model
		PageStore<OrderListVo> orderPage = null;

		UserContext uc = new UserContext();
		uc.setCorporationId(request.getUserSession().getCorporationId());
		uc.setLoginName(request.getUserSession().getLoginName());
		uc.setUserRoles(UserRoleTool.adaptToRole(request.getUserSession().getUserRoles()));
		uc.setUserId(request.getUserSession().getToken());
		orderPage = orderService.OrderList(uc, orderListVo);
		resp.setTotal(orderPage.getResultCount());
		resp.setResult(OrderAdapter.adapterToClient(orderPage.getResultList()));//从服务端转为客户端

		return resp;
	}

	private OrderListVo adapter(OrderSearchVo criteria) {
		OrderListVo vo = new OrderListVo();
		GiftListVo giftvo = new GiftListVo();
		if (criteria.getName() != null) {
			vo.setName(criteria.getName());
		}
		if (criteria.getExchangeDate() != null&&criteria.getExchangeDateEnd() != null) {
			vo.setExchangeDate(criteria.getExchangeDate());
			vo.setExchangeDateEnd(criteria.getExchangeDateEnd());
		}
		if (criteria.getStatus() != null) {
			vo.setStatus(OrderStatus.valueOf(criteria.getStatus().toString()));
		}
		
		if(!StringUtil.isEmpty(criteria.getGiftvo().getSource())){
			giftvo.setSource(criteria.getGiftvo().getSource());
			
		}
		vo.setGiftvo(giftvo);
		
		if (criteria.getPagination() != null) {
			PaginationDetail detail = new PaginationDetail();
			detail.setLimit(criteria.getPagination().getLimit());
			detail.setStart(criteria.getPagination().getStart());

			vo.setPaginationDetail(detail);
		}

		if (criteria.getSorting() != null) {
			SortingDetail sortingDetail = new SortingDetail();
			sortingDetail.setSort(criteria.getSorting().getSort());
			sortingDetail.setDirection(criteria.getSorting().getDirection());
			vo.setSortingDetail(sortingDetail);
		}
		return vo;
	}
	 
	@Override
	public Class<SearchOrderRequest> getActionType() {
		return SearchOrderRequest.class;
	}

	@Override
	public void rollback(SearchOrderRequest req, SearchOrderResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
