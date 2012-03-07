package com.chinarewards.gwt.elt.server.register;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.OrgInit;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.org.CorporationVo;
import com.chinarewards.elt.service.org.CorporationService;
import com.chinarewards.elt.service.org.OrgInitService;
import com.chinarewards.elt.service.user.UserService;
import com.chinarewards.gwt.elt.client.enterprise.model.EnterpriseVo;
import com.chinarewards.gwt.elt.client.register.request.RegisterRequest;
import com.chinarewards.gwt.elt.client.register.request.RegisterResponse;
import com.chinarewards.gwt.elt.model.ClientException;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.google.inject.Inject;

public class RegisterActionHandler extends	BaseActionHandler<RegisterRequest, RegisterResponse> {

	CorporationService corporationService;
	UserService userService;
	OrgInitService orgInitSerivce;
	@Inject
	public RegisterActionHandler(CorporationService corporationService,UserService userService,OrgInitService orgInitSerivce) {
		this.corporationService = corporationService;
		this.userService = userService;
		this.orgInitSerivce = orgInitSerivce;
	}

	@Override
	public Class<RegisterRequest> getActionType() {
		return RegisterRequest.class;
	}

	@Override
	public RegisterResponse execute(RegisterRequest request,
			ExecutionContext context) throws DispatchException {
		 CorporationVo model = new CorporationVo();
		 EnterpriseVo vo = request.getEnterprise();
		
		 model.setAddress(vo.getAddress());
		 model.setCellphone(vo.getCellphone());
		 model.setCorporation(vo.getCorporation());
		 model.setEmailAddress(vo.getEmailAddress());
		 model.setName(vo.getName());
		 model.setFax(vo.getFax());
		 model.setLinkman(vo.getLinkman());
		 model.setDescription(vo.getDescription());
		 model.setTell(vo.getTell());
		 model.setWeb(vo.getWeb());
		 model.setId(vo.getId());
		 SysUser caller = null;
		 Corporation coporation = corporationService.saveCorporation(caller, model);
		 OrgInit init = new OrgInit();
		  init.setCorpInit(1);
		  init.setHrInit(0);
		  init.setCorpId(coporation.getId());
		  orgInitSerivce.save(init);
		 if (coporation.getId() != null) {
		 RegisterResponse resp = new RegisterResponse();
		 resp.setToken("注册成功");
		 resp.setCorpId(coporation.getId());
		 return resp;
		 } else {
		 throw new ClientException("注册失败!");
		 }
		
	}

	@Override
	public void rollback(RegisterRequest action, RegisterResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
