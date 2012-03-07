package com.chinarewards.gwt.elt.client.register.presenter;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;


public interface RegisterPresenter extends Presenter<RegisterPresenter.RegisterDisplay> {

	/**
	 * Logical UI structure for the Register Window/Widget
	 * 
	 * @author liwei
	 * 
	 */
	public static interface RegisterDisplay extends Display {
		
		public HasClickHandlers getSaveClickHandlers();
    	public HasValue<String> getEnterpriseName();
    	void setEnterpriseName(String name); 
        public HasValue<String>  getLinkman();
        public void  setLinkman(String man);
		//public HasValue<String> getCorporation();
		//public void setCorporation(String corporation);
		public HasValue<String> getRemark();
		public void setRemark(String remark);
		public HasValue<String> getAddress();
		public void setAddress(String address);
		public HasValue<String> getTell();
		public void setTell(String tell);
		//public HasValue<String> getFax();
		//public void setFax(String fax);
		public HasValue<String> getCellphone();
		public void setCellphone(String cellphone);
		public HasValue<String> getEmail();
		public void setEmail(String email);
		public HasValue<String> getWeb();
		public void setWeb(String web);
		public boolean isCheckSee();
		
	}

}
