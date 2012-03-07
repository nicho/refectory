package com.chinarewards.gwt.elt.client.register.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.enterprise.model.EnterpriseVo;
import com.chinarewards.gwt.elt.client.register.model.OrgInitVo;

public class RegisterRequest implements Action<RegisterResponse> {

	private EnterpriseVo enterprisevo;
	
   
	public RegisterRequest() {
	}

	public RegisterRequest(EnterpriseVo enterprisevo) {
		this.enterprisevo = enterprisevo;
				
	}

	public EnterpriseVo getEnterprise() {
		return enterprisevo;
	}

	public void setEnterprise(EnterpriseVo enterprisevo) {
		this.enterprisevo = enterprisevo;
	}
}
