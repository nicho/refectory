package com.chinarewards.gwt.elt.client.budget.presenter;


import java.util.Map;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.chinarewards.gwt.elt.client.order.model.OrderStatus;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public interface CreateBudgetPresenter extends Presenter<CreateBudgetPresenter.CreateBudgetDisplay> {

      
	public static interface CreateBudgetDisplay extends Display {

		public HasClickHandlers getSaveBtnClickHandlers();
		public HasClickHandlers getSearchBtnClickHandlers();
		public HasChangeHandlers getJfChangeHandlers();
		void setTotalCount(String text);
		void setRemainCount(String text);
		HasValue<String> getJF();
		String getYear();
		
		Panel getResultPanel();
		Panel getResultpage();
		public void initYear(Map<String, String> map);
		public void initDepart(Map<String, String> map);
		String getDepart();
		void setBreadCrumbs(Widget breadCrumbs);
	}
}
