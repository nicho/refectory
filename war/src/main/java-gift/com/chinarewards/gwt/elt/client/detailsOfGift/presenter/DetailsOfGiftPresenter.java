package com.chinarewards.gwt.elt.client.detailsOfGift.presenter;


import com.chinarewards.gwt.elt.client.detailsOfGift.model.DetailsOfGiftClient;
import com.chinarewards.gwt.elt.client.mvp.Display;
import com.chinarewards.gwt.elt.client.mvp.Presenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;

public interface DetailsOfGiftPresenter extends Presenter<DetailsOfGiftPresenter.DetailsOfGiftDisplay> {

	public void initDetailsOfGift(DetailsOfGiftClient orderVo);
	public static interface DetailsOfGiftDisplay extends Display {

		void setGiftName(String text);
		void setGiftNo(String text);
		void setBrand(String text);
		void setType(String text);
		void setStock(String text);
		void setIntegral(String text);
		void setSummary(String text);
		void setExplains(String text);
		void setNotes(String text);
		void setDispatchcycle(String text);
		void setBusiness(String text);
		void setServicetell(String text);
		void setGiftPhoto(String url);
		HasClickHandlers getExchangeBtn();
		HasClickHandlers getPhotoImage();
		void setShopWindow(Widget w);
	
	
	}
}
