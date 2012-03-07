package com.chinarewards.gwt.elt.server.mail;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.service.org.CorporationService;
import com.chinarewards.elt.service.sendmail.SendMailService;
import com.chinarewards.elt.util.JavaMailSend;
import com.chinarewards.gwt.elt.client.mail.model.MailVo;
import com.chinarewards.gwt.elt.client.mail.request.MailRequest;
import com.chinarewards.gwt.elt.client.mail.request.MailResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

public class MailSendActionHandler extends	BaseActionHandler<MailRequest, MailResponse> {
	@InjectLogger
	Logger logger;
	SendMailService sendMailService;
	
	@Inject
	public MailSendActionHandler(SendMailService sendMailService) {
        this.sendMailService = sendMailService;
	}

	@Override
	public Class<MailRequest> getActionType() {
		return MailRequest.class;
	}

	@Override
	public MailResponse execute(MailRequest request,
			ExecutionContext context) throws DispatchException {
		    MailVo vo = request.getMailvo();
		    String message = sendMailService.sendMail(vo.getContent(), vo.getStaffId());
		    MailResponse resp = new MailResponse();
		    resp.setToken(message);
		return resp;
	}

	@Override
	public void rollback(MailRequest action, MailResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
