package com.chinarewards.gwt.elt.client.order.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.order.model.OrderSearchVo;
import com.chinarewards.gwt.elt.client.order.model.OrderStatus;
import com.chinarewards.gwt.elt.client.order.model.OrderViewClient;
import com.chinarewards.gwt.elt.client.order.plugin.OrderListConstants;
import com.chinarewards.gwt.elt.client.order.presenter.OrderViewPresenter.OrderViewDisplay;
import com.chinarewards.gwt.elt.client.order.request.OrderViewRequest;
import com.chinarewards.gwt.elt.client.order.request.OrderViewResponse;
import com.chinarewards.gwt.elt.client.order.request.SearchOrderByIdRequest;
import com.chinarewards.gwt.elt.client.order.request.SearchOrderByIdResponse;
import com.chinarewards.gwt.elt.client.orderHistory.plugin.OrderHistoryConstants;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.model.ChoosePanel.InitChoosePanelParam;
import com.chinarewards.gwt.elt.util.SimpleDateTimeProvider;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class OrderViewPresenterImpl extends BasePresenter<OrderViewDisplay>
		implements OrderViewPresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;
	String orderId;
	private final BreadCrumbsPresenter breadCrumbs;
	@Inject
	public OrderViewPresenterImpl(EventBus eventBus, DispatchAsync dispatch,BreadCrumbsPresenter breadCrumbs,
			ErrorHandler errorHandler, SessionManager sessionManager,OrderViewDisplay display, Win win) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
        this.breadCrumbs = breadCrumbs;
	}

	@Override
	public void bind() {
		breadCrumbs.loadChildPage("订单详细");
		InitChoosePanelParam initChooseParam = new InitChoosePanelParam();
		initChooseParam.setTopName("订单详细：");
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		init();
		

	}

	private void init() {
		display.getConfirmbutton().addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {//确定发货
			  String status =OrderStatus.SHIPMENTS+"";
				dispatch.execute(new OrderViewRequest(orderId,sessionManager.getSession().getToken(),status),
						new AsyncCallback<OrderViewResponse>() {
							@Override
							public void onFailure(Throwable e) {
								errorHandler.alert("操作失败");
							}

							@Override
							public void onSuccess(OrderViewResponse response) {
								display.getConfirmbuttonObj().setEnabled(false);
								String rs=response.getResult();
								if("ok".equals(rs))
								{
									win.alert("操作成功!");
									Platform.getInstance()
									.getEditorRegistry()
									.openEditor(OrderListConstants.EDITOR_ORDERLIST_SEARCH,	"EDITOR_ORDERLIST_SEARCH", null);
		
									
								}
								else
								{
									win.alert("操作失败!");
								}
								
				
							}

						});


			
		}
	});
		display.getBackbutton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {//问题定单
				  String status =OrderStatus.ERRORORDER+"";
					dispatch.execute(new OrderViewRequest(orderId,sessionManager.getSession().getToken(),status),
							new AsyncCallback<OrderViewResponse>() {
								@Override
								public void onFailure(Throwable e) {
									errorHandler.alert("操作失败");
									Platform.getInstance()
									.getEditorRegistry()
									.openEditor(OrderListConstants.EDITOR_ORDERLIST_SEARCH,	"EDITOR_ORDERLIST_SEARCH", null);
		
								}

								@Override
								public void onSuccess(OrderViewResponse response) {
									display.getConfirmbuttonObj().setEnabled(false);
									String rs=response.getResult();
									if("ok".equals(rs))
									{
										win.alert("操作成功!");
										Platform.getInstance()
										.getEditorRegistry()
										.openEditor(OrderListConstants.EDITOR_ORDERLIST_SEARCH,	"EDITOR_ORDERLIST_SEARCH", null);
			
										
									}
									else
									{
										win.alert("操作失败!");
									}
									
					
								}

							});


				
			}
		});	
	display.getReturnbutton().addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			Platform.getInstance()
			.getEditorRegistry()
			.openEditor(
					OrderListConstants.EDITOR_ORDERLIST_SEARCH,
					"EDITOR_ORDERLIST_SEARCH", null);
			
		}
	});
	}

	@Override
	public void initOrderView(OrderSearchVo orderVo) {
		String orderid = orderVo.getId();
		{
			dispatch.execute(new SearchOrderByIdRequest(orderid,sessionManager.getSession().getStaffId()),
					new AsyncCallback<SearchOrderByIdResponse>() {
						private OrderViewClient orderVo;

						@Override
						public void onFailure(Throwable arg0) {
							errorHandler.alert("查询出错!");
							
						}

						@Override
						public void onSuccess(SearchOrderByIdResponse response) {
							this.orderVo = response.getOrderViewClient();
							display.getShopImage().setUrl("imageshow?imageName="+orderVo.getGiftImage());
							display.setShopText(orderVo.getGiftName());
							display.setTotal(orderVo.getTotal()+"");
							display.setUnitprice(orderVo.getIntegral()+"");
							display.setSource(orderVo.getSource()+"");
							display.setNumber(orderVo.getNumber()+"");
							orderId=orderVo.getOrderId();
							display.setName(orderVo.getName());
							display.setPhone(orderVo.getPhone());
							display.setZipCode(orderVo.getZipCode());
							display.setAddress(orderVo.getAddress());
							display.setOrderCode(orderVo.getOrdercode());
							display.setOrderStatus(orderVo.getOrderStatus().toString());
							display.setExchangeDate(SimpleDateTimeProvider.formatData("", orderVo.getExchangeDate()));
							display.setOrderDefinition(orderVo.getOrderDefinition());
							
						}

					});
		}
		//
		
	}



}
