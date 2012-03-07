package com.chinarewards.gwt.elt.client.password.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.password.request.PasswordRequest;
import com.chinarewards.gwt.elt.client.password.request.PasswordResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.util.StringUtil;
import com.chinarewards.gwt.elt.client.win.Win;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class PasswordPresenterImpl extends	BasePresenter<PasswordPresenter.PasswordDisplay> implements	PasswordPresenter {

	final DispatchAsync dispatcher;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;


	@Inject
	public PasswordPresenterImpl(EventBus eventBus,ErrorHandler errorHandler, SessionManager sessionManager,
			PasswordDisplay display, DispatchAsync dispatcher,Win win	) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
	}

	@Override
	public void bind() {
		display.setUsername(sessionManager.getSession().getLoginName());
		registerHandler(display.getPasswordClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						if (StringUtil.isEmpty(display.getOldPassword().getValue())) {
							win.alert("旧密码不能为空!<br>");
							return;
						}						
						if (StringUtil.isEmpty(display.getNewPassword().getValue())) {
							win.alert("新密码不能为空!<br>");
							return;
						}
						
						if (!display.getNewPassword().getValue().equals(display.getValidatePassword().getValue())) {
							win.alert("新密码和确认密码不一致!<br>");
							return;
						}
						
						doPassword();
					}
				}));
	}

	protected void doPassword() {
	
		if(sessionManager.getSession()!=null){
		dispatcher.execute(new PasswordRequest(sessionManager.getSession().getStaffId(),display.getOldPassword().getValue(),display.getNewPassword().getValue(),sessionManager.getSession()),
				new AsyncCallback<PasswordResponse>() {
					public void onFailure(Throwable t) {
						win.alert("修改失败");
					}

					@Override
					public void onSuccess(PasswordResponse response) {
                        if(response.getMessage().equals("success"))
						   win.alert("修改成功!");
                        else
                        	win.alert("修改失败,旧密码不对");
					}
				});
	    }else{
		win.alert("请重新登录!");
	    }
		
	}
	
}
