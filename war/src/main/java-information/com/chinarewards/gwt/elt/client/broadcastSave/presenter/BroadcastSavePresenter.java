package com.chinarewards.gwt.elt.client.broadcastSave.presenter;

import java.util.Date;
import java.util.List;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.widget.SpecialTextArea;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public interface BroadcastSavePresenter extends
		Presenter<BroadcastSavePresenter.BroadcastSaveDisplay> {

	public void initBroadcastUpdate(String staffId);

	public static interface BroadcastSaveDisplay extends Display {

		public HasClickHandlers getSaveBtnClickHandlers();

		public HasClickHandlers getReturnBtnClickHandlers();
		public HasClickHandlers getChooseBtnClickHandlers();

		void setBreadCrumbs(Widget breadCrumbs);

		
		String getContent();

		DateBox getBroadcastingTimeStart();
		DateBox getBroadcastingTimeEnd();
		
		RadioButton isAllowreplies_true();
		RadioButton isAllowreplies_false();
		
		Panel getBroadcastOrDeptTextAreaPanel();

		void setContent(String text);

		void setBroadcastingTimeStart(Date text);

		void setBroadcastingTimeEnd(Date text);

		void setAllowreplies(boolean fal);
		void setTitleText(String text);
		
		
		// 得到候选人的id,和名称
		List<String[]> getRealOrginzationIds();


		SpecialTextArea<OrganicationClient> getSpecialTextArea();
		 
	}
}
