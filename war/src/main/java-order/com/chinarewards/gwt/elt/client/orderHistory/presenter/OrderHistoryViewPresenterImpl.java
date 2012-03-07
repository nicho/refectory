package com.chinarewards.gwt.elt.client.orderHistory.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.order.model.OrderSearchVo;
import com.chinarewards.gwt.elt.client.order.model.OrderStatus;
import com.chinarewards.gwt.elt.client.order.model.OrderViewClient;
import com.chinarewards.gwt.elt.client.order.request.SearchOrderByIdRequest;
import com.chinarewards.gwt.elt.client.order.request.SearchOrderByIdResponse;
import com.chinarewards.gwt.elt.client.orderHistory.plugin.OrderHistoryConstants;
import com.chinarewards.gwt.elt.client.orderHistory.presenter.OrderHistoryViewPresenter.OrderHistoryViewDisplay;
import com.chinarewards.gwt.elt.client.orderHistory.request.OrderHistoryViewRequest;
import com.chinarewards.gwt.elt.client.orderHistory.request.OrderHistoryViewResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.win.Win;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class OrderHistoryViewPresenterImpl extends
		BasePresenter<OrderHistoryViewDisplay> implements
		OrderHistoryViewPresenter {

	String orderId;

	final DispatchAsync dispatcher;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;

	

	@Inject
	public OrderHistoryViewPresenterImpl(EventBus eventBus,
			DispatchAsync dispatcher, ErrorHandler errorHandler,
			SessionManager sessionManager, OrderHistoryViewDisplay display,
			Win win) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
		
	}

	@Override
	public void bind() {
		
		initWidget();
	}

	private void initWidget() {

		registerEvent();
	}

	private void registerEvent() {
		display.getConfirmbutton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String status = OrderStatus.NUSHIPMENTS + "";
				dispatcher.execute(new OrderHistoryViewRequest(orderId,
						sessionManager.getSession().getToken(), status),
						new AsyncCallback<OrderHistoryViewResponse>() {
							@Override
							public void onFailure(Throwable e) {
								errorHandler.alert(e.getMessage());
							}

							@Override
							public void onSuccess(
									OrderHistoryViewResponse response) {
								String rs = response.getResult();
								if ("ok".equals(rs)) {
									Platform.getInstance()
											.getEditorRegistry()
											.openEditor(
													OrderHistoryConstants.EDITOR_ORDERHISTORY_SEARCH,
													"EDITOR_ORDERHISTORY_SEARCH",
													null);

								} else {
									win.alert("操作失败!");
								}

							}
						});

			}
		});

		display.getReceivebutton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {// 确认收货
				String status = OrderStatus.AFFIRM + "";
				dispatcher.execute(new OrderHistoryViewRequest(orderId,
						sessionManager.getSession().getToken(), status),
						new AsyncCallback<OrderHistoryViewResponse>() {
							@Override
							public void onFailure(Throwable e) {
								errorHandler.alert(e.getMessage());
							}

							@Override
							public void onSuccess(
									OrderHistoryViewResponse response) {
								String rs = response.getResult();
								if ("ok".equals(rs)) {
									win.alert("操作成功!");
									Platform.getInstance()
											.getEditorRegistry()
											.openEditor(
													OrderHistoryConstants.EDITOR_ORDERHISTORY_SEARCH,
													"EDITOR_ORDERHISTORY_SEARCH",
													null);

								} else {
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
								OrderHistoryConstants.EDITOR_ORDERHISTORY_SEARCH,
								"EDITOR_ORDERHISTORY_SEARCH", null);

			}
		});

	}

	// 查看时初始化数据
	@Override
	public void initInstanceId(String instanceId, OrderSearchVo orderSearchVo) {
		orderId = orderSearchVo.getId();
		{
			dispatcher.execute(new SearchOrderByIdRequest(orderId,
					sessionManager.getSession().getStaffId()),
					new AsyncCallback<SearchOrderByIdResponse>() {
						private OrderViewClient orderVo;

						@Override
						public void onFailure(Throwable arg0) {
							errorHandler.alert("查询出错!");

						}

						@Override
						public void onSuccess(SearchOrderByIdResponse response) {
							this.orderVo = response.getOrderViewClient();
							display.showOrderHistory(orderVo);
							orderId = orderVo.getOrderId();
						}

					});
		}
	}

}
