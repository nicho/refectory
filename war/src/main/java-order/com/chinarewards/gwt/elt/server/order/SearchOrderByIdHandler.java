/**
 * 
 */
package com.chinarewards.gwt.elt.server.order;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.gift.Gift;
import com.chinarewards.elt.domain.order.Orders;
import com.chinarewards.elt.service.gift.GiftService;
import com.chinarewards.elt.service.order.OrderService;
import com.chinarewards.elt.service.staff.IStaffService;
import com.chinarewards.gwt.elt.client.order.model.OrderViewClient;
import com.chinarewards.gwt.elt.client.order.request.SearchOrderByIdRequest;
import com.chinarewards.gwt.elt.client.order.request.SearchOrderByIdResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.google.inject.Inject;

/**
 * @author lw
 */
public class SearchOrderByIdHandler extends
		BaseActionHandler<SearchOrderByIdRequest, SearchOrderByIdResponse> {
	@InjectLogger
	Logger logger;
	OrderService orderService;
	GiftService giftService;
    IStaffService staffSerivce;
	@Inject
	public SearchOrderByIdHandler(OrderService orderService,GiftService giftService,IStaffService staffSerivce) {
		this.orderService = orderService;
		this.giftService = giftService;
		this.staffSerivce = staffSerivce;
	}

	@Override
	public SearchOrderByIdResponse execute(SearchOrderByIdRequest request,
			ExecutionContext context) throws DispatchException {
		logger.debug(" parameters:{}", request.getId());
		Orders order = orderService.findOrderById(request.getId());
		Gift gift=giftService.findGiftById(order.getGiftId());
		double userBalance = staffSerivce.getBalance(request.getStaffId());
		return new SearchOrderByIdResponse(adapter(orderService, order,gift, userBalance));

	}

	private OrderViewClient adapter(OrderService orderService, Orders order,Gift gift,double userBalance) {
		OrderViewClient orderVo = new OrderViewClient();
		orderVo.setOrderId(order.getId());
		orderVo.setName(order.getReceiver());
		orderVo.setAddress(order.getAddress());
		orderVo.setGiftId(order.getGiftId());
		orderVo.setPhone(order.getTel());
		orderVo.setAddress(order.getAddress());
		orderVo.setZipCode(order.getPostcode());
		orderVo.setTotal(order.getIntegral());//定单的积分
		orderVo.setOrderDefinition(order.getRemarks());
		orderVo.setNumber(order.getAmount());
		orderVo.setUserBalance(userBalance);
		orderVo.setGiftImage(gift.getPhoto());
		orderVo.setGiftName(gift.getName());
		orderVo.setIntegral(gift.getIntegral());//礼品的积分
		orderVo.setBussiness(gift.getBusiness());
		orderVo.setServicetell(gift.getServicetell());
		orderVo.setSource(toSource(gift.getSource()));
		orderVo.setOrdercode(order.getOrderCode());
		orderVo.setOrderStatus(order.getStatus().toString());
		orderVo.setExchangeDate(order.getExchangeDate());
		
		return orderVo;
	}
    public String toSource(String source){
    	if (StringUtil.trim(source).equals("inner")) {
			return "内部直接提供";
		}
		if (StringUtil.trim(source).equals("outter")) {
			return "外部货品公司提供";
		}
		return "";
    }
	@Override
	public Class<SearchOrderByIdRequest> getActionType() {
		return SearchOrderByIdRequest.class;
	}

	@Override
	public void rollback(SearchOrderByIdRequest arg0,
			SearchOrderByIdResponse arg1, ExecutionContext arg2)
			throws DispatchException {
	}

}
