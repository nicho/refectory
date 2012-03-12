package com.chinarewards.gwt.elt.client.core.view;

import java.util.Date;

import com.chinarewards.gwt.elt.client.core.presenter.DockPresenter.DockDisplay;
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

public class DockWidget extends Composite implements DockDisplay {

	@UiField
	DockLayoutPanel dock;

	@UiField
	FlowPanel menu;

	@UiField
	Anchor logBtn;

	@UiField
	InlineLabel message;

	@UiField
	Anchor btnUser;

	@UiField
	Anchor btnOrder;
	@UiField
	Anchor btnDishesMenu;

	@UiField
	Anchor collectionBtn;

	@UiField
	InlineLabel menuTitle;

	@UiField
	Anchor managementCenter;
	@UiField
	Anchor giftExchange;
	@UiField
	Anchor staffCorner;

	// Set the format of datepicker.
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format_chinese);

	interface DockWidgetBinder extends UiBinder<Widget, DockWidget> {
	}

	private static DockWidgetBinder uiBinder = GWT
			.create(DockWidgetBinder.class);

	public DockWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		init();
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getlogBtn() {
		return logBtn;
	}

	String styleOn = "";
	String styleNo = "";

	private void init() {
		styleOn = btnOrder.getStyleName();
		styleNo = btnUser.getStyleName();

		btnUser.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btnUser.setStyleName(styleOn);
				btnOrder.setStyleName(styleNo);
				btnDishesMenu.setStyleName(styleNo);

			}
		});
		btnOrder.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				btnUser.setStyleName(styleNo);
				btnOrder.setStyleName(styleOn);
				btnDishesMenu.setStyleName(styleNo);
			}
		});
		btnDishesMenu.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				btnUser.setStyleName(styleNo);
				btnOrder.setStyleName(styleNo);
				btnDishesMenu.setStyleName(styleOn);

			}
		});

	}

	@Override
	public DockLayoutPanel getDock() {
		return dock;
	}

	@Override
	public Panel getMenu() {
		return menu;
	}

	@Override
	public void setMessage(String userName) {
		String time = dateFormat.format(new Date());
		String msg = "欢迎你，" + userName + "！今天是:" + time;
		message.setText(msg);
	}

	@Override
	public HasClickHandlers getbtnUser() {
		return btnUser;
	}

	@Override
	public void setMenu(Panel panel) {
		menu.clear();

	}

	@Override
	public HasClickHandlers getbtnOrder() {
		return btnOrder;
	}

	@Override
	public void setMenuTitle(String title) {
		menuTitle.setText(title);
	}

	@Override
	public HasClickHandlers getbtnDishesMenu() {
		return btnDishesMenu;
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
	public void changeTopMenu(String key) {

		if ("User".equals(key)) {

			btnUser.setStyleName(styleOn);
			btnOrder.setStyleName(styleNo);
			btnDishesMenu.setStyleName(styleNo);

		}
		if ("Order".equals(key)) {

			btnUser.setStyleName(styleNo);
			btnOrder.setStyleName(styleOn);
			btnDishesMenu.setStyleName(styleNo);

		}
		if ("Dishes".equals(key)) {
			btnUser.setStyleName(styleNo);
			btnOrder.setStyleName(styleNo);
			btnDishesMenu.setStyleName(styleOn);
		}

	}

}
