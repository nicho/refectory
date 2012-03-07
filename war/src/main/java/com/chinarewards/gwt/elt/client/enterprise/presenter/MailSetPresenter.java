package com.chinarewards.gwt.elt.client.enterprise.presenter;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;


public interface MailSetPresenter extends Presenter<MailSetPresenter.MailSetDisplay> {

	/**
	 * Logical UI structure for the MailSet Window/Widget
	 * 
	 * @author liwei
	 * 
	 */
	public static interface MailSetDisplay extends Display {
		
		public HasClickHandlers getSaveClickHandlers();
    	public HasValue<String> getEnterpriseName();
    	void setEnterpriseName(String name); 
        public HasValue<String> getPassword();
		public void setPassword(String password);
		public HasValue<String> getSMTP();
		public void setSMTP(String smtp);
		public HasValue<String> getEmail();
		public void setEmail(String email);
		
		public void setEnterpriseId(String enterpriseId);
		public String getEnterpriseId();
		void setBreadCrumbs(Widget breadCrumbs) ;
	}

}
