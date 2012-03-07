package com.chinarewards.gwt.elt.client.core.impl;

import java.util.List;

import com.chinarewards.gwt.elt.client.core.AbstractPlugin;
import com.chinarewards.gwt.elt.client.core.Extension;
import com.chinarewards.gwt.elt.client.core.MenuRoleStore;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.google.gwt.core.client.GWT;

public class CorePlugin extends AbstractPlugin {

	final MenuProcessor menuProcessor;
	boolean activated = false;

	final SessionManager sessionManager;
	final MenuRoleStore menuRoleStore;

	CorePlugin(PluginDescriptor pluginDesc, MenuProcessor menuProc,
			SessionManager sessionManager, MenuRoleStore menuRoleStore) {
		super(pluginDesc);
		this.menuProcessor = menuProc;
		this.sessionManager = sessionManager;
		this.menuRoleStore = menuRoleStore;
	}

	protected void onActivate() {
		GWT.log("Starting Core...");
		processMenus(getPlatform());
		processEditors(getPlatform());
	}

	
	private void processMenus(Platform platform) {
		List<Extension> exts = platform.getPluginManager().getExtensions(
				"core.menu");
		// UserRoleVo userRoles[] = sessionManager.getSession().getUserRoles();
		for (Extension e : exts) {
			MenuItem item = (MenuItem) e.getInstance();
			// if (!GWT.isScript()) {
			menuProcessor.add(item);
			continue;
			// }
			// for (UserRoleVo userRole : userRoles) {
			// if (menuRoleStore.isMenuVisibleToRole(item.getMenuId(),
			// userRole.name())) {
			// menuProcessor.add(item);
			// }
			// }
		}
		// --- render menu just set ---
		// set the actual container
		platform.getSiteManager().setMenuProcessor(menuProcessor);
		// menuProcessor.render(platform.getSiteManager().getMenuArea());
	}

	private void processEditors(Platform platform) {
		List<Extension> exts = platform.getPluginManager().getExtensions(
				"core.editor");
		for (Extension e : exts) {
			EditorDescriptor item = (EditorDescriptor) e.getInstance();
			platform.getEditorRegistry().registerEditor(item);
		}
		
		// open editor by default.



		// List<UserRoleVo> roles = new ArrayList<UserRoleVo>();
		// {
		// if (!GWT.isScript()) {
		// roles.add(UserRoleVo.CUSTOMER_SERVICE);
		// roles.add(UserRoleVo.CORP_ADMIN);
		// roles.add(UserRoleVo.DEPT_MGR);
		// roles.add(UserRoleVo.SENIOR_CS);
		// roles.add(UserRoleVo.STAFF);
		// roles.add(UserRoleVo.SUB_CORP_ADMIN);
		// roles.add(UserRoleVo.SYSOP);
		// } else {
		// UserRoleVo[] rs = sessionManager.getSession().getUserRoles();
		// for (UserRoleVo r : rs) {
		// roles.add(r);
		// }
		// }
		// }
		//
		// if (roles.contains(UserRoleVo.SENIOR_CS)
		// || roles.contains(UserRoleVo.CUSTOMER_SERVICE)) {
		// // go to HRUSEr maintenance screen.
		//
		// } else if (roles.contains(UserRoleVo.CORP_ADMIN)
		// || roles.contains(UserRoleVo.SUB_CORP_ADMIN)
		// || roles.contains(UserRoleVo.DEPT_MGR)) {
		//
		// Platform.getInstance()
		// .getEditorRegistry()
		// .openEditor(RewardsConstants.EDITOR_REWARDS_ENTRY,
		// "EDITOR_REWARDS_ENTRY_ID", null);
		// }
	}
}
