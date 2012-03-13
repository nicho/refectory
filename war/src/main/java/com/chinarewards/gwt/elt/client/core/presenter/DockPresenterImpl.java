package com.chinarewards.gwt.elt.client.core.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.EltGinjector;
import com.chinarewards.gwt.elt.client.core.PluginManager;
import com.chinarewards.gwt.elt.client.core.presenter.DockPresenter.DockDisplay;
import com.chinarewards.gwt.elt.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.elt.client.core.ui.event.MenuClickEvent;
import com.chinarewards.gwt.elt.client.dishesList.plugin.DishesListConstants;
import com.chinarewards.gwt.elt.client.login.event.LoginEvent;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.orderList.plugin.OrderListConstants;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.userList.plugin.UserListConstants;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class DockPresenterImpl extends BasePresenter<DockDisplay> implements
		DockPresenter {
	final PluginManager pluginManager;
	final SessionManager sessionManager;
	final EltGinjector injector;
	final MenuProcessor menuProcessor;
	final DispatchAsync dispatchAsync;

	@Inject
	public DockPresenterImpl(EventBus eventBus, DockDisplay display,
			SessionManager sessionManager, PluginManager pluginManager,
			EltGinjector injector, MenuProcessor menuProcessor,
			DispatchAsync dispatchAsync) {
		super(eventBus, display);
		this.sessionManager = sessionManager;
		this.pluginManager = pluginManager;
		this.injector = injector;
		this.menuProcessor = menuProcessor;
		this.dispatchAsync = dispatchAsync;
	}

	public void bind() {
	

		registerHandler(display.getlogBtn().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGOUT));
			}
		}));
		registerHandler(display.getbtnUser().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						display.setMenuTitle("用户管理");
						menuProcessor.initrender(display.getMenu(),
								"User");
						eventBus.fireEvent(new MenuClickEvent(
								menuProcessor
										.getMenuItem(UserListConstants.MENU_USERLIST_SEARCH)));
					}
				}));
		registerHandler(display.getbtnOrder().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						display.setMenuTitle("订单处理");
						menuProcessor.initrender(display.getMenu(),
								"Order");
						eventBus.fireEvent(new MenuClickEvent(
								menuProcessor
										.getMenuItem(OrderListConstants.MENU_ORDERLIST_SEARCH)));
					}
				}));
		registerHandler(display.getbtnDishesMenu().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						display.setMenuTitle("菜单管理");
						menuProcessor.initrender(display.getMenu(),
								"Menu");
						eventBus.fireEvent(new MenuClickEvent(
								menuProcessor
										.getMenuItem(DishesListConstants.MENU_DISHESLIST_SEARCH)));
					}
				}));

	}

	public DockDisplay getDisplay() {
		return display;
	}

}
