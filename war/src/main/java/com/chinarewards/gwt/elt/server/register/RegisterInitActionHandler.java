package com.chinarewards.gwt.elt.server.register;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.chinarewards.elt.domain.org.OrgInit;
import com.chinarewards.elt.service.org.OrgInitService;
import com.chinarewards.gwt.elt.client.register.model.OrgInitVo;
import com.chinarewards.gwt.elt.client.register.request.RegisterInitRequest;
import com.chinarewards.gwt.elt.client.register.request.RegisterInitResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.google.inject.Inject;

public class RegisterInitActionHandler extends
		BaseActionHandler<RegisterInitRequest, RegisterInitResponse> {

	OrgInitService orgInitService;

	@Inject
	public RegisterInitActionHandler(OrgInitService orgInitService) {
		this.orgInitService = orgInitService;
	}

	@Override
	public Class<RegisterInitRequest> getActionType() {
		return RegisterInitRequest.class;
	}

	@Override
	public RegisterInitResponse execute(RegisterInitRequest request,
			ExecutionContext context) throws DispatchException {
		
		 OrgInit init=  orgInitService.getOrgInit();
		 OrgInitVo vo = new OrgInitVo();
		 RegisterInitResponse resp = new RegisterInitResponse();
		 if(init !=null){
			 vo.setId(init.getId());
			 vo.setHrInit(init.getHrInit());
			 vo.setCorpInit(init.getCorpInit());
			 vo.setCorpId(init.getCorpId());
		 }
		 resp.setOrgInitVo(vo);
		 return resp;
				
	}

	@Override
	public void rollback(RegisterInitRequest arg0,
			RegisterInitResponse arg1, ExecutionContext arg2)
			throws DispatchException {
		// TODO Auto-generated method stub

	}

}
