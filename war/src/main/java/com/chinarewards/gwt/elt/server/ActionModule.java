package com.chinarewards.gwt.elt.server;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

import com.chinarewards.gwt.elt.client.core.request.StaffInitRequest;
import com.chinarewards.gwt.elt.client.login.LoginRequest;
import com.chinarewards.gwt.elt.client.login.TokenValidRequest;
import com.chinarewards.gwt.elt.server.core.StaffInitActionHandler;
import com.chinarewards.gwt.elt.server.login.LoginActionHandler;
import com.chinarewards.gwt.elt.server.login.TokenValidActionHandler;

/**
 * 
 * @author cyril
 * 
 */
public class ActionModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		// login module
		bindHandler(LoginRequest.class, LoginActionHandler.class);

		// 登录验证token
		bindHandler(TokenValidRequest.class, TokenValidActionHandler.class);

		// 员工天地,员工数据加载
		bindHandler(StaffInitRequest.class, StaffInitActionHandler.class);

	}
}
