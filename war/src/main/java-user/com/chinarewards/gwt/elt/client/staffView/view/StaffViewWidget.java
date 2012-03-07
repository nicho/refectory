package com.chinarewards.gwt.elt.client.staffView.view;

import com.chinarewards.gwt.elt.client.staffList.model.StaffListCriteria.StaffStatus;
import com.chinarewards.gwt.elt.client.staffView.presenter.StaffViewPresenter.StaffViewDisplay;
import com.chinarewards.gwt.elt.client.view.constant.CssStyleConstants;
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

public class StaffViewWidget extends Composite implements StaffViewDisplay {

	@UiField
	InlineLabel staffNo;
	@UiField
	InlineLabel staffName;

	@UiField
	InlineLabel departmentName;
	@UiField
	InlineLabel jobPosition;
	@UiField
	InlineLabel leadership;
	@UiField
	InlineLabel phone;
	@UiField
	InlineLabel email;
	@UiField
	InlineLabel dob;
	@UiField
	InlineLabel staffStatus;
	@UiField
	InlineLabel staffRoles;
	
	@UiField
	Button updateBtn;

	@UiField
	Panel breadCrumbs;
	@UiField
	Image staffImage;
	@UiField
	InlineLabel dataCount;
	@UiField
	Panel resultPanel;
	@UiField
	Panel resultpage;
	private static StaffViewWidgetUiBinder uiBinder = GWT
			.create(StaffViewWidgetUiBinder.class);

	interface StaffViewWidgetUiBinder extends UiBinder<Widget, StaffViewWidget> {
	}

	public StaffViewWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);

	}

	@Override
	public HasClickHandlers getupadateBtnClickHandlers() {
		return updateBtn;
	}

	@Override
	public void setStaffNo(String text) {
		this.staffNo.setText(text);
	}

	@Override
	public void setStaffName(String text) {
		this.staffName.setText(text);
	}

	@Override
	public void setDepartmentName(String text) {
		this.departmentName.setText(text);
	}

	@Override
	public void setJobPosition(String text) {
		this.jobPosition.setText(text);
	}

	@Override
	public void setLeadership(String text) {
		this.leadership.setText(text);
	}

	@Override
	public void setPhone(String text) {
		this.phone.setText(text);
	}

	@Override
	public void setEmail(String text) {
		this.email.setText(text);
	}

	@Override
	public void setDob(String text) {
		this.dob.setText(text);
	}

	@Override
	public void setStaffImage(String url) {
		this.staffImage.setUrl("imageshow?imageName=" + url);
	}

	@Override
	public void setDataCount(String text) {
		this.dataCount.setText(text);
		
	}

	@Override
	public Panel getResultPanel() {
		return this.resultPanel;
	}

	@Override
	public Panel getResultpage() {
		return this.resultpage;
	}

	@Override
	public void setStaffStatus(String text) {
		this.staffStatus.setText(StaffStatus.valueOf(text).getDisplayName());
	}

	@Override
	public void displayUpdateBtn(boolean colleague) {
		if(colleague==true)
		{
			if(updateBtn.getElement().getParentElement()!=null)
				updateBtn.getElement().getParentElement().setClassName(CssStyleConstants.hidden);
		}
	}

	@Override
	public InlineLabel getStaffRoles() {
		return staffRoles;
	}

}
