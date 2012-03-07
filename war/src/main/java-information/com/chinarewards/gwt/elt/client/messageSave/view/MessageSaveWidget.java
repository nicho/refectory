package com.chinarewards.gwt.elt.client.messageSave.view;

import java.util.ArrayList;
import java.util.List;

import com.chinarewards.gwt.elt.client.messageSave.presenter.MessageSavePresenter.MessageSaveDisplay;
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
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class MessageSaveWidget extends Composite implements MessageSaveDisplay {

	@UiField
	TextArea content;

	@UiField
	Button saveBtn;
	@UiField
	Button returnBtn;
	@UiField
	Button chooseBtn;

	@UiField
	Panel staffOrDeptTextAreaPanel;


	
	SpecialTextArea<OrganicationClient> staffTextArea;
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);
	private static MessageSaveWidgetUiBinder uiBinder = GWT
			.create(MessageSaveWidgetUiBinder.class);

	interface MessageSaveWidgetUiBinder extends
			UiBinder<Widget, MessageSaveWidget> {
	}

	public MessageSaveWidget() {
		initWidget(uiBinder.createAndBindUi(this));

		staffTextArea = new OrganizationSpecialTextArea();
		staffTextArea.getElement().getFirstChildElement().setClassName("token-input-list-facebook2");
		staffOrDeptTextAreaPanel.add(staffTextArea);
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
	public Panel getBroadcastOrDeptTextAreaPanel() {
		return staffOrDeptTextAreaPanel;
	}

	@Override
	public void setContent(String text) {
		content.setText(text);
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

	@Override
	public void displaySelectStaff() {
		chooseBtn.setVisible(false);
	
	}

}
