package com.chinarewards.gwt.elt.client.shopWindow.presenter;


import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Panel;

public interface ShopWindowPresenter extends Presenter<ShopWindowPresenter.ShopWindowDisplay> {

	public void initShopWindow(int row,int col);
	public static interface ShopWindowDisplay extends Display {

		Panel getResultPanel();
		void setPageTitle(String text);
		HasClickHandlers getMore();
	}
}
