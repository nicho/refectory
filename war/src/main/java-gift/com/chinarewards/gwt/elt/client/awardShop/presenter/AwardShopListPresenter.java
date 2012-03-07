package com.chinarewards.gwt.elt.client.awardShop.presenter;


import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public interface AwardShopListPresenter extends Presenter<AwardShopListPresenter.AwardShopListDisplay> {


	public static interface AwardShopListDisplay extends Display {

		public HasClickHandlers getSearchBtnClickHandlers();

		void setDataCount(String text);
		HasValue<String> getKeyName();

		Panel getResultPanel();
		Panel getResultpage();
		void setBreadCrumbs(Widget breadCrumbs);

	}
}
