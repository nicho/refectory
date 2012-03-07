package com.chinarewards.gwt.elt.client.staff.view;

import com.chinarewards.gwt.elt.client.staff.model.StaffVo;
import com.chinarewards.gwt.elt.client.staff.presenter.LeadTimePresenter.LeadTimeDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LeadTimeWidget extends Composite implements LeadTimeDisplay {

	private static LeadTimeWidgetUiBinder uiBinder = GWT.create(LeadTimeWidgetUiBinder.class);
	@UiField
	TextBox leadTime;

	@UiField
	Button saveButton;
	@UiField
	Hidden staffId;

	
	@UiField
	Panel breadCrumbs;

	interface LeadTimeWidgetUiBinder extends UiBinder<Widget, LeadTimeWidget> {
	}

	public LeadTimeWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public void initEditLeadTime(StaffVo staffVo) {
		staffId.setValue(staffVo.getId());

		leadTime.setText(staffVo.getLeadTime() + "");

		
	}

	
	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
	}

	public HasClickHandlers getSaveClickHandlers() {
		return saveButton;
	}

	@Override
	public String getStaffId() {
		return this.staffId.getValue();
	}

	@Override
	public HasValue<String> getLeadTime() {
		return leadTime;
	}

	

	@Override
	public void clear() {
	   leadTime.setText("");

	}
}
