package com.chinarewards.gwt.elt.client.gift.presenter;


import java.util.Map;

import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.chinarewards.gwt.elt.client.gift.presenter.GiftListPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public interface GiftListPresenter extends Presenter<GiftListPresenter.GiftListDisplay> {


	public static interface GiftListDisplay extends Display {

		public HasClickHandlers getSearchBtnClickHandlers();
		public HasClickHandlers getAddBtnClickHandlers();
		public HasClickHandlers getimportingBtnClickHandlers();
		void setDataCount(String text);
		HasValue<String> getKeyName();
		String getStatus();
		Panel getResultPanel();
		Panel getResultpage();
		public void initGiftStatus(Map<String, String> map);
		
		void setBreadCrumbs(Widget breadCrumbs);

	}
}
