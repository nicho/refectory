package com.chinarewards.gwt.elt.client.core.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.EltGinjector;
import com.chinarewards.gwt.elt.client.box.plugin.UserBoxConstants;
import com.chinarewards.gwt.elt.client.broadcasting.plugin.BroadcastingListConstants;
import com.chinarewards.gwt.elt.client.budget.plugin.CreateBudgetConstants;
import com.chinarewards.gwt.elt.client.core.PluginManager;
import com.chinarewards.gwt.elt.client.core.presenter.DockPresenter.DockDisplay;
import com.chinarewards.gwt.elt.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.elt.client.core.ui.event.MenuClickEvent;
import com.chinarewards.gwt.elt.client.department.plugin.DepartmentLeaderConstants;
import com.chinarewards.gwt.elt.client.department.plugin.DepartmentListConstants;
import com.chinarewards.gwt.elt.client.enterprise.plugin.EnterpriseConstants;
import com.chinarewards.gwt.elt.client.gift.plugin.GiftListConstants;
import com.chinarewards.gwt.elt.client.hrbox.plugin.HrBoxConstants;
import com.chinarewards.gwt.elt.client.integralManagement.plugin.IntegralManagementConstants;
import com.chinarewards.gwt.elt.client.login.LastLoginRoleRequest;
import com.chinarewards.gwt.elt.client.login.LastLoginRoleResponse;
import com.chinarewards.gwt.elt.client.login.event.LoginEvent;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewardItem.plugin.RewardsItemConstants;
import com.chinarewards.gwt.elt.client.rewards.plugin.RewardsListConstants;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.user.UserRoleVo;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
		List<UserRoleVo> roleslt = new ArrayList<UserRoleVo>();
		UserRoleVo[] roles = sessionManager.getSession().getUserRoles();

		if (roles.length > 0) {
			for (UserRoleVo r : roles) {
				roleslt.add(r);
			}
			if (!roleslt.contains(UserRoleVo.CORP_ADMIN) && !roleslt.contains(UserRoleVo.DEPT_MGR)) {
				display.disableManagementCenter();
			}
			if (!roleslt.contains(UserRoleVo.GIFT)) {
				display.disableGiftExchange();
			}
			if (!roleslt.contains(UserRoleVo.STAFF)) {
				display.disableStaffCorner();
			}
			if(roleslt.contains(UserRoleVo.DEPT_MGR) && !roleslt.contains(UserRoleVo.CORP_ADMIN))
			{
				display.displayDeptMgrMenu();
			}
		}

		registerHandler(display.getlogBtn().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGOUT));
			}
		}));
		registerHandler(display.getBtnCollection().addClickHandler(
				new ClickHandler() {
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
						if(sessionManager.getSession().getLastLoginRole()==UserRoleVo.CORP_ADMIN)
						eventBus.fireEvent(new MenuClickEvent(menuProcessor.getMenuItem(HrBoxConstants.MENU_HRBOX_SEARCH)));
						else if(sessionManager.getSession().getLastLoginRole()==UserRoleVo.DEPT_MGR)
						eventBus.fireEvent(new MenuClickEvent(menuProcessor.getMenuItem(UserBoxConstants.MENU_USERBOX_SEARCH)));
						
					}
				}));
		registerHandler(display.getBtnGb().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				display.setMenuTitle("广播");
				menuProcessor.initrender(display.getMenu(),
						"Broadcasting");
				eventBus.fireEvent(new MenuClickEvent(
						menuProcessor
								.getMenuItem(BroadcastingListConstants.MENU_BROADCASTINGLIST_SEARCH)));
			}
		}));
		registerHandler(display.getBtnRewardItem().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						display.setMenuTitle("奖项");
						menuProcessor.initrender(display.getMenu(),
								"RewardItem");
						eventBus.fireEvent(new MenuClickEvent(
								menuProcessor
										.getMenuItem(RewardsItemConstants.MENU_REWARDSITEM_List)));
					}
				}));
		registerHandler(display.getBtnReward().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						display.setMenuTitle("应用奖项");
						menuProcessor.initrender(display.getMenu(), "Reward");
						eventBus.fireEvent(new MenuClickEvent(
								menuProcessor
										.getMenuItem(RewardsListConstants.MENU_REWARDSLIST_SEARCH)));
					}
				}));
		registerHandler(display.getBtnStaff().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						display.setMenuTitle("员工数据");
						menuProcessor.initrender(display.getMenu(), "Staff");
						if(sessionManager.getSession().getLastLoginRole()==UserRoleVo.CORP_ADMIN)
							eventBus.fireEvent(new MenuClickEvent(menuProcessor.getMenuItem(DepartmentListConstants.MENU_DEPARTMENTLIST_SEARCH)));
						else if(sessionManager.getSession().getLastLoginRole()==UserRoleVo.DEPT_MGR)	
						{
							eventBus.fireEvent(new MenuClickEvent(menuProcessor.getMenuItem(DepartmentLeaderConstants.MENU_DEPARTMENTLEADER_SEARCH)));
							menuProcessor.changItemColor("部门组织结构");
						}
					}
				}));
		registerHandler(display.getBtnSetting().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						display.setMenuTitle("设置");
						menuProcessor.initrender(display.getMenu(), "Setting");
						eventBus.fireEvent(new MenuClickEvent(
								menuProcessor
										.getMenuItem(EnterpriseConstants.MENU_ENTERPRISE_EDIT)));
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
		registerHandler(display.getBtnIntegral().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						display.setMenuTitle("积分管理");
						menuProcessor.initrender(display.getMenu(), "Integral");
						
						if(sessionManager.getSession().getLastLoginRole()==UserRoleVo.CORP_ADMIN)
							eventBus.fireEvent(new MenuClickEvent(menuProcessor.getMenuItem(IntegralManagementConstants.MENU_INTEGRALMANAGEMENT_SEARCH)));
						else if(sessionManager.getSession().getLastLoginRole()==UserRoleVo.DEPT_MGR)	
						{
							eventBus.fireEvent(new MenuClickEvent(menuProcessor.getMenuItem(CreateBudgetConstants.MENU_CREATE_BUDGET)));
							menuProcessor.changItemColor("部门预算");
						}
					}
				}));

		registerHandler(display.getGiftExchange().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						dispatchAsync.execute(new LastLoginRoleRequest(
								sessionManager.getSession().getToken(),
								UserRoleVo.GIFT),
								new AsyncCallback<LastLoginRoleResponse>() {

									@Override
									public void onFailure(Throwable e) {
										// Window.alert("系统切换出错");
									}

									@Override
									public void onSuccess(
											LastLoginRoleResponse resp) {
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
						dispatchAsync.execute(new LastLoginRoleRequest(
								sessionManager.getSession().getToken(),
								UserRoleVo.STAFF),
								new AsyncCallback<LastLoginRoleResponse>() {

									@Override
									public void onFailure(Throwable e) {
										// Window.alert("系统切换出错");
									}

									@Override
									public void onSuccess(
											LastLoginRoleResponse resp) {
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

	public DockDisplay getDisplay() {
		return display;
	}

}
