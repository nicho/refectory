package com.chinarewards.gwt.elt.client.chooseOrganization.presenter;

import com.chinarewards.gwt.elt.client.mvp.DialogPresenter;
import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.widget.SpecialTextArea;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

public interface StaffChooseOrganizationListPresenter extends
		DialogPresenter<StaffChooseOrganizationListPresenter.StaffChooseOrganizationListDisplay> {



	public static interface StaffChooseOrganizationListDisplay extends Display {
		HasValue<String> getName();

		HasClickHandlers getSearchBtn();

		HasClickHandlers getResetBtn();

		HasClickHandlers getChooseBtn();

		HasClickHandlers getCancelBtn();

		Widget asWidget();

		/**
		 * 重置查询信息
		 */
		void reset();

		SpecialTextArea<OrganicationClient> getSpecialTextBox();

		Panel getResultPanel();
		Panel getResultpage();

	//	String getDeptId();

		
		void hiddenSpecialBoxPanel();
		void hiddenChooseBtn();
		void hiddenAddLabel();
		
		void setCancelBtnText(String text);
		
		
		
		RadioButton getRadioStaff();
		
		RadioButton getRadioDept();
		
		RadioButton getRadioGroup();
		
		RadioButton getRadioOrg();
	}

}
