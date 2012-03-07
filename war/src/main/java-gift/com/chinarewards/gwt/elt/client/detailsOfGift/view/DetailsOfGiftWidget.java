package com.chinarewards.gwt.elt.client.detailsOfGift.view;

import com.chinarewards.gwt.elt.client.detailsOfGift.presenter.DetailsOfGiftPresenter.DetailsOfGiftDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class DetailsOfGiftWidget extends Composite implements
		DetailsOfGiftDisplay {

	@UiField
	InlineLabel giftName;
	@UiField
	InlineLabel giftNo;
	@UiField
	InlineLabel brand;
	@UiField
	InlineLabel type;
	@UiField
	InlineLabel stock;
	@UiField
	InlineLabel integral;
	@UiField
	InlineLabel summary;
	@UiField
	InlineLabel explains;
	@UiField
	InlineLabel notes;
	@UiField
	InlineLabel dispatchcycle;
	@UiField
	InlineLabel business;
	@UiField
	InlineLabel servicetell;
	@UiField
	Image giftPhoto;
	@UiField
	Button exchangeBtn;
	@UiField
	Panel shopWindow;
	private static DetailsOfGiftWidgetUiBinder uiBinder = GWT
			.create(DetailsOfGiftWidgetUiBinder.class);

	interface DetailsOfGiftWidgetUiBinder extends
			UiBinder<Widget, DetailsOfGiftWidget> {
	}

	public DetailsOfGiftWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setGiftName(String text) {
		giftName.setText(text);
		
	}

	@Override
	public void setGiftNo(String text) {
		giftNo.setText(text);
		
	}

	@Override
	public void setBrand(String text) {
		brand.setText(text);
		
	}

	@Override
	public void setType(String text) {
		if("1".equals(text))
		{
			type.setText("实物");
		}
		else if("2".equals(text))
		{
			type.setText("虚拟");
		}
		
		
	}

	@Override
	public void setStock(String text) {
		stock.setText(text);
	}

	@Override
	public void setIntegral(String text) {
		integral.setText(text);
	}

	@Override
	public void setSummary(String text) {
		summary.setText(text);
	}

	@Override
	public void setExplains(String text) {
		explains.setText(text);
	}

	@Override
	public void setNotes(String text) {
		notes.setText(text);
	}

	@Override
	public void setDispatchcycle(String text) {
		dispatchcycle.setText(text);
		
	}

	@Override
	public void setBusiness(String text) {
		business.setText(text);
	}

	@Override
	public void setServicetell(String text) {
		servicetell.setText(text);
	}

	@Override
	public void setGiftPhoto(String url) {
		giftPhoto.setUrl("imageshow?imageName="+url);
		
	}

	@Override
	public HasClickHandlers getExchangeBtn() {
		return exchangeBtn;
	}

	@Override
	public HasClickHandlers getPhotoImage() {
		return giftPhoto;
	}

	@Override
	public void setShopWindow(Widget w) {
		this.shopWindow.clear();
		 this.shopWindow.add(w);
	}






	



}
