package com.chinarewards.gwt.elt.client;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.core.ui.event.PlatformInitEvent;
import com.chinarewards.gwt.elt.client.core.ui.event.PlatformInitHandler;
import com.chinarewards.gwt.elt.client.login.event.LoginEvent;
import com.chinarewards.gwt.elt.client.login.event.LoginHandler;
import com.chinarewards.gwt.elt.client.login.presenter.LoginPresenter;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.win.Win;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;

public class MainImpl implements Main, PlatformInitHandler, LoginHandler {

	final EltGinjector injector;
	final SessionManager sessionManager;
	final EventBus eventBus;
	final Win win;
	RootLayoutPanel rootLayoutPanel;
	LoginPresenter login;
	final DispatchAsync dispatchAsync;

	@Inject
	public MainImpl(EltGinjector injector, SessionManager sessionManager,
			EventBus eventBus, Win win, DispatchAsync dispatchAsync) {
		this.injector = injector;
		this.sessionManager = sessionManager;
		this.eventBus = eventBus;
		this.win = win;
		login = injector.getLoginPresenter();
		this.dispatchAsync = dispatchAsync;
	}

	public void init(RootLayoutPanel panel) {
		GWT.log("Main Initializing...");
		this.rootLayoutPanel = panel;
		eventBus.addHandler(PlatformInitEvent.getType(), this);
		eventBus.addHandler(LoginEvent.getType(), this);

		sessionManager.initialize();

	}

	public void onInit(boolean loggedIn) {
		rootLayoutPanel.clear();
		if (!loggedIn) {
			login.bind();
			rootLayoutPanel.add(login.getDisplay().asWidget());
		} else {
			login.unbind();

				injector.getPlatform().initialize(injector.getPluginSetAdmin(),
						rootLayoutPanel);

		}
	}

	public void onLogin(LoginEvent event) {
		switch (event.getStatus()) {
		case LOGIN_OK:
			rootLayoutPanel.clear();
			login.unbind();
			injector.getPlatform().initialize(injector.getPluginSetAdmin(),
					rootLayoutPanel);
			break;
		case LOGIN_FAILED:
			win.alert("登录失败，请重试！");
			break;
		case LOGIN_EXPIRED:
		case LOGOUT:
			// if (!GWT.isScript()) {
			// break;
			// }
			// win.alert("Logout event received");
			// sessionManager.logout();
			sessionManager.resetLogin();
			Window.Location.reload();
			// rootLayoutPanel.clear();
			// login.bind();
			// rootLayoutPanel.add(login.getDisplay().asWidget());
			break;

		}
	}

}
