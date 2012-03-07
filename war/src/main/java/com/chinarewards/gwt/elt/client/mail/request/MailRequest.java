package com.chinarewards.gwt.elt.client.mail.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.enterprise.model.EnterpriseVo;
import com.chinarewards.gwt.elt.client.mail.model.MailVo;
import com.chinarewards.gwt.elt.client.support.UserSession;

public class MailRequest implements Action<MailResponse> {

	private MailVo mailvo;
	private UserSession userSession;

	public MailRequest() {
	}

	public MailRequest(MailVo mailvo, UserSession userSession) {
		this.mailvo = mailvo;
		this.userSession = userSession;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public MailVo getMailvo() {
		return mailvo;
	}

	public void setMailvo(MailVo mailvo) {
		this.mailvo = mailvo;
	}

	
}
