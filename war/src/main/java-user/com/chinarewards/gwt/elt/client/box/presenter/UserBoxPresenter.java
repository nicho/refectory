package com.chinarewards.gwt.elt.client.box.presenter;


import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface UserBoxPresenter extends Presenter<UserBoxPresenter.UserBoxDisplay> {

	public void initUserBox();
	public static interface UserBoxDisplay extends Display {
		
		void setUserBoxNominate(String text);
		void setUserSend(String text);
			
		
		public void setMessage(String week);
		public HasClickHandlers getView() ;
		public HasClickHandlers getOperate(); 
	}
}
