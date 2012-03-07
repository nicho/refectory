package com.chinarewards.gwt.elt.client.broadcastSave.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chinarewards.gwt.elt.client.broadcastSave.presenter.BroadcastSavePresenter.BroadcastSaveDisplay;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.view.OrganizationSpecialTextArea;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.widget.SpecialTextArea;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class BroadcastSaveWidget extends Composite implements
		BroadcastSaveDisplay {
	@UiField
	InlineLabel titleText;
	@UiField
	TextArea content;
	@UiField
	DateBox broadcastingTimeStart;
	@UiField
	DateBox broadcastingTimeEnd;
	@UiField
	RadioButton isAllowreplies_true;
	@UiField
	RadioButton isAllowreplies_false;

	@UiField
	Button saveBtn;
	@UiField
	Button returnBtn;
	@UiField
	Button chooseBtn;
	
	@UiField
	Panel breadCrumbs;
	@UiField
	Panel staffOrDeptTextAreaPanel;

	SpecialTextArea<OrganicationClient> staffTextArea;
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);
	private static BroadcastSaveWidgetUiBinder uiBinder = GWT
			.create(BroadcastSaveWidgetUiBinder.class);

	interface BroadcastSaveWidgetUiBinder extends
			UiBinder<Widget, BroadcastSaveWidget> {
	}

	public BroadcastSaveWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		broadcastingTimeStart.setFormat(new DateBox.DefaultFormat(dateFormat));
		broadcastingTimeEnd.setFormat(new DateBox.DefaultFormat(dateFormat));
		staffTextArea = new OrganizationSpecialTextArea();
		staffOrDeptTextAreaPanel.add(staffTextArea);
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);

	}

	@Override
	public HasClickHandlers getSaveBtnClickHandlers() {
		return saveBtn;
	}

	@Override
	public HasClickHandlers getReturnBtnClickHandlers() {
		return returnBtn;
	}

	@Override
	public String getContent() {
		return content.getValue();
	}

	@Override
	public DateBox getBroadcastingTimeStart() {
		return broadcastingTimeStart;
	}

	@Override
	public DateBox getBroadcastingTimeEnd() {
		return broadcastingTimeEnd;
	}

	@Override
	public RadioButton isAllowreplies_true() {
		return isAllowreplies_true;
	}

	@Override
	public RadioButton isAllowreplies_false() {
		return isAllowreplies_false;
	}

	@Override
	public Panel getBroadcastOrDeptTextAreaPanel() {
		return staffOrDeptTextAreaPanel;
	}

	@Override
	public void setContent(String text) {
		content.setText(text);
	}

	@Override
	public void setBroadcastingTimeStart(Date text) {
		broadcastingTimeStart.setValue(text);
	}

	@Override
	public void setBroadcastingTimeEnd(Date text) {
		broadcastingTimeEnd.setValue(text);
	}

	@Override
	public void setAllowreplies(boolean fal) {
		if (fal == true) {
			isAllowreplies_true.setValue(true);
		} else {
			isAllowreplies_false.setValue(true);
		}
	}

	@Override
	public void setTitleText(String text) {
		titleText.setText(text);
	}

	@Override
	public HasClickHandlers getChooseBtnClickHandlers() {
		return chooseBtn;
	}

	@Override
	public List<String[]> getRealOrginzationIds() {
		List<String[]> realOrginzationIds = new ArrayList<String[]>();
		List<OrganicationClient> existKeys = staffTextArea.getItemList();
		for (OrganicationClient key : existKeys) {
			String[] nameAndId = new String[3];
			nameAndId[0] = key.getId();
			nameAndId[1] = key.getName();
			nameAndId[2] = key.getType().toString();
			realOrginzationIds.add(nameAndId);
		}
		return realOrginzationIds;
	}

	@Override
	public SpecialTextArea<OrganicationClient> getSpecialTextArea() {
		return staffTextArea;
	}

}
