package com.chinarewards.gwt.elt.client.enterprise.presenter;

import com.chinarewards.gwt.elt.client.enterprise.model.EnterpriseVo;
import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public interface PeriodPresenter extends
		Presenter<PeriodPresenter.PeriodDisplay> {

	public static interface PeriodDisplay extends Display {

		public HasClickHandlers getSaveClickHandlers();

		public ListBox getPeriod();

		public DateBox getFirstTime();

		public String getEnterpriseId();

		void setBreadCrumbs(Widget breadCrumbs);

	
		void initEditPeriod(EnterpriseVo enterpriseVo);
		
		public HasClickHandlers getBackHandlers();

		public void setSaveVisible(boolean flag);

	}

}
