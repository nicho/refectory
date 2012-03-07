package com.chinarewards.gwt.elt.client.staff.presenter;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.chinarewards.gwt.elt.client.staff.model.StaffVo;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

public interface LeadTimePresenter extends
		Presenter<LeadTimePresenter.LeadTimeDisplay> {

	public static interface LeadTimeDisplay extends Display {

		public HasClickHandlers getSaveClickHandlers();

		public HasValue<String> getLeadTime();

		public String getStaffId();
		
		
		void setBreadCrumbs(Widget breadCrumbs);

		void initEditLeadTime(StaffVo staffVo);

		public void clear();

	
	
	}

	
	void initEditor(String id);

}
