package com.chinarewards.gwt.elt.server;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

import com.chinarewards.gwt.elt.client.login.LoginRequest;
import com.chinarewards.gwt.elt.client.login.TokenValidRequest;
import com.chinarewards.gwt.elt.client.orderList.request.SearchOrderListRequest;
import com.chinarewards.gwt.elt.client.server.order.SearchOrderListActionHandler;
import com.chinarewards.gwt.elt.client.server.user.SearchUserListActionHandler;
import com.chinarewards.gwt.elt.client.userList.request.SearchUserListRequest;
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
		bindHandler(SearchUserListRequest.class, SearchUserListActionHandler.class);
		bindHandler(SearchOrderListRequest.class, SearchOrderListActionHandler.class);



	}
}
