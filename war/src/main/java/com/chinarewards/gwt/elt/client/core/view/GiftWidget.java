package com.chinarewards.gwt.elt.client.core.view;

import java.util.Date;

import com.chinarewards.gwt.elt.client.core.presenter.GiftPresenter.GiftDisplay;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class GiftWidget extends Composite implements GiftDisplay {

	@UiField
	DockLayoutPanel dock;
	@UiField
	FlowPanel menu;

	@UiField
	Anchor logBtn;

	@UiField
	InlineLabel message;


	@UiField
	Anchor collectionBtn;
	

	
	@UiField
	Anchor managementCenter;
	@UiField
	Anchor giftExchange;
	@UiField
	Anchor staffCorner;
	@UiField
	Anchor btnEmail;

	@UiField
	Anchor btnGb;
	@UiField
	Anchor btnGift;
	@UiField
	InlineLabel menuTitle;
	// Set the format of datepicker.
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format_chinese);

	interface GiftWidgetBinder extends UiBinder<Widget, GiftWidget> {
	}

	private static GiftWidgetBinder uiBinder = GWT
			.create(GiftWidgetBinder.class);

	public GiftWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		init();
	}
	String styleOn="";
	 String styleNo="";
		private void init() {
			  styleOn=btnGift.getStyleName();
			  styleNo=btnEmail.getStyleName();

			btnEmail.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					btnEmail.setStyleName(styleOn);
					btnGb.setStyleName(styleNo);
					btnGift.setStyleName(styleNo);
				}
			});
			btnGb.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					btnEmail.setStyleName(styleNo);
					btnGb.setStyleName(styleOn);
					btnGift.setStyleName(styleNo);
				}
			});

			btnGift.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					btnEmail.setStyleName(styleNo);
					btnGb.setStyleName(styleNo);
					btnGift.setStyleName(styleOn);
				}
			});
		}

	
	
	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getlogBtn() {
		return logBtn;
	}


	@Override
	public DockLayoutPanel getDock() {
		return dock;
	}






	@Override
	public HasClickHandlers getBtnCollection() {
		return collectionBtn;
	}

	@Override
	public HasClickHandlers getManagementCenter() {

		return managementCenter;
	}

	@Override
	public HasClickHandlers getGiftExchange() {
		return giftExchange;
	}

	@Override
	public HasClickHandlers getStaffCorner() {
		return staffCorner;
	}
	@Override
	public void disableManagementCenter() {
		managementCenter.setVisible(false);
		
	}

	@Override
	public void disableGiftExchange() {
		giftExchange.setVisible(false);
		
	}

	@Override
	public void disableStaffCorner() {
		staffCorner.setVisible(false);
		
	}

	@Override
	public HasClickHandlers getBtnEmail() {
		return this.btnEmail;
	}

	@Override
	public HasClickHandlers getBtnGb() {
		return this.btnGb;
	}

	@Override
	public HasClickHandlers getBtnGift() {
		return this.btnGift;
	}


	@Override
	public void setMessage(String userName) {
		String time = dateFormat.format(new Date());
		String msg = "欢迎你，" + userName + "！今天是:" + time;
		message.setText(msg);
	}
	@Override
	public void setMenu(Panel panel) {
		menu.clear();

	}

	@Override
	public void setMenuTitle(String title) {
		menuTitle.setText(title);
	}

	@Override
	public Panel getMenu() {
		return menu;
	}
}
