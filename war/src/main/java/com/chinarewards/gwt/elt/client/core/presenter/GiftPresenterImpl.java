package com.chinarewards.gwt.elt.client.core.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.EltGinjector;
import com.chinarewards.gwt.elt.client.core.PluginManager;
import com.chinarewards.gwt.elt.client.core.presenter.GiftPresenter.GiftDisplay;
import com.chinarewards.gwt.elt.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.elt.client.core.ui.event.MenuClickEvent;
import com.chinarewards.gwt.elt.client.gift.plugin.GiftListConstants;
import com.chinarewards.gwt.elt.client.login.LastLoginRoleRequest;
import com.chinarewards.gwt.elt.client.login.LastLoginRoleResponse;
import com.chinarewards.gwt.elt.client.login.event.LoginEvent;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.order.plugin.OrderViewConstants;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.user.UserRoleVo;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class GiftPresenterImpl extends BasePresenter<GiftDisplay> implements
		GiftPresenter {
	final PluginManager pluginManager;
	final SessionManager sessionManager;
	final EltGinjector injector;
	final MenuProcessor menuProcessor;
	final DispatchAsync dispatchAsync;
	@Inject
	public GiftPresenterImpl(EventBus eventBus, GiftDisplay display,
			SessionManager sessionManager, PluginManager pluginManager,
			EltGinjector injector, MenuProcessor menuProcessor,DispatchAsync dispatchAsync) {
		super(eventBus, display);
		this.sessionManager = sessionManager;
		this.pluginManager = pluginManager;
		this.injector = injector;
		this.menuProcessor = menuProcessor;
		this.dispatchAsync=dispatchAsync;
	}

	public void bind() {
		List <UserRoleVo> roleslt = new ArrayList<UserRoleVo>();
		UserRoleVo [] roles=sessionManager.getSession().getUserRoles();

		if(roles.length>0)
		{
			for (UserRoleVo r:roles) {
				roleslt.add(r);
			}
			if(!roleslt.contains(UserRoleVo.CORP_ADMIN) && !roleslt.contains(UserRoleVo.DEPT_MGR))
			{
				display.disableManagementCenter();
			}
			if(!roleslt.contains(UserRoleVo.GIFT))
			{
				display.disableGiftExchange();
			}
			if(!roleslt.contains(UserRoleVo.STAFF))
			{
				display.disableStaffCorner();
			}
		}
		registerHandler(display.getlogBtn().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGOUT));
			}
		}));
		registerHandler(display.getBtnCollection().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("收藏");
			}
		}));
		registerHandler(display.getBtnEmail().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						display.setMenuTitle("收件箱");
						menuProcessor.initrender(display.getMenu(), "Box");
						eventBus.fireEvent(new MenuClickEvent(
								menuProcessor
										.getMenuItem(OrderViewConstants.MENU_ORDERBOX_SEARCH)));
					}
				}));
		registerHandler(display.getBtnGb().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				display.setMenuTitle("广播");
				display.setMenu(null);
			}
		}));
		
		registerHandler(display.getBtnGift().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						display.setMenuTitle("兑换管理");
						menuProcessor.initrender(display.getMenu(), "Gift");
						eventBus.fireEvent(new MenuClickEvent(
								menuProcessor
										.getMenuItem(GiftListConstants.MENU_GIFTLIST_SEARCH)));
					}
				}));
		registerHandler(display.getManagementCenter().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						UserRoleVo role=UserRoleVo.DEPT_MGR;
						
						for (int i = 0; i < sessionManager.getSession().getUserRoles().length; i++) {
							if(UserRoleVo.CORP_ADMIN==sessionManager.getSession().getUserRoles()[i])
							{
								role=UserRoleVo.CORP_ADMIN;
							}
						}
						
						dispatchAsync.execute(new LastLoginRoleRequest(sessionManager.getSession().getToken(),role),
								new AsyncCallback<LastLoginRoleResponse>() {
	
									@Override
									public void onFailure(Throwable e) {
									//	Window.alert("系统切换出错");
									}
	
									@Override
									public void onSuccess(LastLoginRoleResponse resp) {
										// 成功
										if ("success".equals(resp.getFal()))
											Window.Location.reload();
										else
											Window.alert("系统切换出错");
										
									}
								});
					}
				}));
		registerHandler(display.getStaffCorner().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						dispatchAsync.execute(new LastLoginRoleRequest(sessionManager.getSession().getToken(),UserRoleVo.STAFF),
								new AsyncCallback<LastLoginRoleResponse>() {
	
									@Override
									public void onFailure(Throwable e) {
									//	Window.alert("系统切换出错");
									}
	
									@Override
									public void onSuccess(LastLoginRoleResponse resp) {
										// 成功
										if ("success".equals(resp.getFal()))
											Window.Location.reload();
										else
											Window.alert("系统切换出错");
										
									}
								});
					}
				}));



	}

	public GiftDisplay getDisplay() {
		return display;
	}

}
