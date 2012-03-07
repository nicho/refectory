package com.chinarewards.gwt.elt.client.budget.presenter;

import com.chinarewards.gwt.elt.client.budget.model.CorpBudgetVo;
import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public interface CorpBudgetPresenter extends
		Presenter<CorpBudgetPresenter.CorpBudgetDisplay> {

	public static interface CorpBudgetDisplay extends Display {

		public HasValue<String> getBudgetTitle();
		
		public ListBox getMoneyType();

		public TextBox getBudgetAmount();

		public TextBox getBudgetIntegral();
		
		public Hidden getIntegralPrice();

		public DateBox getBeginDate();

		public DateBox getEndDate();
		
		public Hidden getPeriod();
		public DateBox getPeriodBeginDate();

		public HasClickHandlers getSaveClick();

		public HasClickHandlers getBackClick();

		public HasClickHandlers getPeriodBtnClick();

		public void clear();

		public void initEditCorpBudget(CorpBudgetVo giftVo);

		void setBreadCrumbs(Widget breadCrumbs);
	}

	public void initEditor(String giftId, String thisAction);
}
