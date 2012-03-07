package com.chinarewards.gwt.elt.client.core.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.EltGinjector;
import com.chinarewards.gwt.elt.client.breadCrumbs.ui.BreadCrumbsMenu;
import com.chinarewards.gwt.elt.client.core.PluginManager;
import com.chinarewards.gwt.elt.client.core.presenter.StaffPresenter.StaffDisplay;
import com.chinarewards.gwt.elt.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.user.UserRoleVo;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;

public class StaffPresenterImpl extends BasePresenter<StaffDisplay> implements
		StaffPresenter {
	final PluginManager pluginManager;
	final SessionManager sessionManager;
	final EltGinjector injector;
	final MenuProcessor menuProcessor;
	final DispatchAsync dispatchAsync;
	final BreadCrumbsMenu breadCrumbsMenu;

	@Inject
	public StaffPresenterImpl(EventBus eventBus, StaffDisplay display,
			SessionManager sessionManager, PluginManager pluginManager,
			EltGinjector injector, MenuProcessor menuProcessor,
			DispatchAsync dispatchAsync, BreadCrumbsMenu breadCrumbsMenu) {
		super(eventBus, display);
		this.sessionManager = sessionManager;
		this.pluginManager = pluginManager;
		this.injector = injector;
		this.menuProcessor = menuProcessor;
		this.dispatchAsync = dispatchAsync;
		this.breadCrumbsMenu = breadCrumbsMenu;
	}

	public void bind() {
		List<UserRoleVo> roleslt = new ArrayList<UserRoleVo>();
		UserRoleVo[] roles = sessionManager.getSession().getUserRoles();

		if (roles.length > 0) {
			for (UserRoleVo r : roles) {
				roleslt.add(r);
			}
			if (!roleslt.contains(UserRoleVo.CORP_ADMIN)
					&& !roleslt.contains(UserRoleVo.DEPT_MGR)) {
				display.disableManagementCenter();
			}
			if (!roleslt.contains(UserRoleVo.GIFT)) {
				display.disableGiftExchange();
			}
			if (!roleslt.contains(UserRoleVo.STAFF)) {
				display.disableStaffCorner();
			}
		}

		// 设置
		registerHandler(display.getSettingAnchor().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Window.alert("待实现");
					}
				}));

		//

	}

	public StaffDisplay getDisplay() {
		return display;
	}

}
