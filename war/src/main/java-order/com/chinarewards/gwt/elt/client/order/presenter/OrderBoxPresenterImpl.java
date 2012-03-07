package com.chinarewards.gwt.elt.client.order.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.presenter.DockPresenter;
import com.chinarewards.gwt.elt.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.order.model.OrderStatus;
import com.chinarewards.gwt.elt.client.order.model.OrderViewClient;
import com.chinarewards.gwt.elt.client.order.plugin.OrderListConstants;
import com.chinarewards.gwt.elt.client.order.presenter.OrderBoxPresenter.OrderBoxDisplay;
import com.chinarewards.gwt.elt.client.order.request.OrderBoxRequest;
import com.chinarewards.gwt.elt.client.order.request.OrderBoxResponse;
import com.chinarewards.gwt.elt.client.order.request.SearchOrderByIdResponse;
import com.chinarewards.gwt.elt.client.orderHistory.plugin.OrderHistoryConstants;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.model.ChoosePanel.InitChoosePanelParam;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class OrderBoxPresenterImpl extends BasePresenter<OrderBoxDisplay>
		implements OrderBoxPresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;
	String orderId;
	private final BreadCrumbsPresenter breadCrumbs;
	final MenuProcessor menuProcessor;
	final DockPresenter dockPresenter;
	@Inject
	public OrderBoxPresenterImpl(EventBus eventBus, DispatchAsync dispatch,BreadCrumbsPresenter breadCrumbs,MenuProcessor menuProcessor,DockPresenter dockPresenter,
			ErrorHandler errorHandler, SessionManager sessionManager,OrderBoxDisplay display, Win win) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
        this.breadCrumbs = breadCrumbs;
        this.dockPresenter=dockPresenter;
		this.menuProcessor=menuProcessor;
	}

	@Override
	public void bind() {
		breadCrumbs.loadListPage();
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		
		registerHandler(display.getView().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dockPresenter.getDisplay().changeTopMenu("Gift");
				dockPresenter.getDisplay().setMenuTitle("兑换管理");
				menuProcessor.initrender(dockPresenter.getDisplay().getMenu(), "Gift");
				Platform.getInstance()
				.getEditorRegistry()
				.openEditor(
						OrderListConstants.EDITOR_ORDERLIST_SEARCH,
						"EDITOR_ORDERLIST_SEARCH", OrderStatus.INITIAL);
			}
		}));
		registerHandler(display.getOperate().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dockPresenter.getDisplay().changeTopMenu("Gift");
				dockPresenter.getDisplay().setMenuTitle("兑换管理");
				menuProcessor.initrender(dockPresenter.getDisplay().getMenu(), "Gift");
				Platform.getInstance()
				.getEditorRegistry()
				.openEditor(
						OrderListConstants.EDITOR_ORDERLIST_SEARCH,
						"EDITOR_ORDERLIST_SEARCH", OrderStatus.NUSHIPMENTS);
			}
		}));

	}

	

	@Override
	public void initOrderBox() {
		
		{
			//没有付积分的
			 dispatch.execute(new OrderBoxRequest(sessionManager.getSession(),OrderStatus.INITIAL+""),
					new AsyncCallback<OrderBoxResponse>() {
						@Override
						public void onFailure(Throwable arg0) {
							errorHandler.alert("查询出错!");
							
						}
						@Override
						public void onSuccess(OrderBoxResponse response) {
							
							display.setOrderNew(response.getTotal()+"");
						}

					});
			//要发货的
			 dispatch.execute(new OrderBoxRequest(sessionManager.getSession(),OrderStatus.NUSHIPMENTS+""),
					new AsyncCallback<OrderBoxResponse>() {
						@Override
						public void onFailure(Throwable arg0) {
							errorHandler.alert("查询出错!");
							
						}
						@Override
						public void onSuccess(OrderBoxResponse response) {
							
							display.setOrderSend(response.getTotal()+"");
						}

					});
		    }		
	}



}
