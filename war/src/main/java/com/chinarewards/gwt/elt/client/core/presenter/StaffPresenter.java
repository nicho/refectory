package com.chinarewards.gwt.elt.client.core.presenter;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Panel;

public interface StaffPresenter extends Presenter<StaffPresenter.StaffDisplay> {

	public static interface StaffDisplay extends Display {
		
		HasClickHandlers getlogBtn();

		HasClickHandlers getBtnCollection();
		HasClickHandlers getPassword();
		HasClickHandlers getManagementCenter();
		HasClickHandlers getGiftExchange();
		HasClickHandlers getStaffCorner();
		Panel getDock();
		HasClickHandlers getStaffInfo() ;

		
		void setMessage(String userName);
		void disableManagementCenter();
		void disableGiftExchange();
		void disableStaffCorner();

		HasClickHandlers getAwardShop();

		HasClickHandlers getViewPoints();
		HasClickHandlers getWinninghistory();
		HasClickHandlers getParticipationAwards();
		HasClickHandlers getOtherAwards();
		HasClickHandlers getExchangeHistory();
		HasClickHandlers getMyMessage();
		HasClickHandlers getStaffHeavenIndex();
		HasClickHandlers getStaffAnchor();
		HasClickHandlers getCorpBroadcastAnchor();
		HasClickHandlers getGloryAnchor();
		HasClickHandlers getSettingAnchor();
		HasClickHandlers getMore();
		
		
		HasClickHandlers getMyWinReward();
		HasClickHandlers getAllReward();
		HasClickHandlers getEffortRewardItem();
		HasClickHandlers getAllRewardItem();		

		
		void setPhoto(String photo);
		void setStaffName(String staffName);
		void setStation(String station);
		void setDeptName(String deptName);
		void setIntegral(int integral);
		Panel getSmaillShopWindow();
		Panel getRewardPanel();
		Panel getRewardItemPanel();
		
	}
	
	
}
