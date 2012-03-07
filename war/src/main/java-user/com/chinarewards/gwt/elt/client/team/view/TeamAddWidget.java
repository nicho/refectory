package com.chinarewards.gwt.elt.client.team.view;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.SomeoneClient;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.team.model.TeamVo;
import com.chinarewards.gwt.elt.client.team.plugin.TeamConstants;
import com.chinarewards.gwt.elt.client.team.presenter.TeamAddPresenter.TeamAddDisplay;
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
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class TeamAddWidget extends Composite implements TeamAddDisplay {

	// --------vo
	@UiField
	TextBox name;
		@UiField
	TextArea explains;
		
	@UiField
	Button back;

	@UiField
	Button save;
	
	@UiField
	Panel breadCrumbs;
	//选负责人的按钮
	@UiField
	Button chooseBtn;
	//显示负责人的面板
	@UiField
	Panel staffAreaPanel;
	//选成员的按钮
	@UiField
	Button chooseBtns;
	//显示成员的面板
	@UiField
	Panel staffsAreaPanel;
	SpecialTextArea<OrganicationClient> staffArea;
	SpecialTextArea<OrganicationClient> staffsArea;
	DateTimeFormat dateFormat = DateTimeFormat
			.getFormat(ViewConstants.date_format);

	interface TeamAddWidgetBinder extends UiBinder<Widget, TeamAddWidget> {

	}

	private static TeamAddWidgetBinder uiBinder = GWT
			.create(TeamAddWidgetBinder.class);

	@Inject
	public TeamAddWidget(DispatchAsync dispatch, ErrorHandler errorHandler,	SessionManager sessionManager) {
		initWidget(uiBinder.createAndBindUi(this));
		staffArea = new OrganizationSpecialTextArea();
		staffAreaPanel.add(staffArea);//负责人面板
		
		staffsArea = new OrganizationSpecialTextArea();
		staffsAreaPanel.add(staffsArea);//成员面板
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void initEditTeam(TeamVo teamVo,String instanceId) {//修改时显示得到的数据
		    name.setText(teamVo.getName());
			explains.setText(teamVo.getDescription());
			showManagerInfo(teamVo);
			showMemberInfo(teamVo);
			if(instanceId.equals(TeamConstants.ACTION_TEAM_VIEW)){
				name.setEnabled(false);
				explains.setEnabled(false);
				save.setVisible(false);
				chooseBtns.setVisible(false);
				chooseBtn.setVisible(false);
				
			}
		
	}
	// 显示管理人
	@Override
	public void showManagerInfo(TeamVo teamVo){
			staffArea.clear();
			ParticipateInfoClient participateInfo = teamVo.getFzInfo();
			 if (participateInfo instanceof SomeoneClient) {
					for (OrganicationClient org : ((SomeoneClient) participateInfo)	.getOrganizations()) {
						if (!staffArea.containsItem(org)) {
							staffArea.addItem(org);
						}
					}
			}
	}
	// 显示成员人
	@Override
	public void showMemberInfo(TeamVo teamVo){
			staffsArea.clear();
			ParticipateInfoClient participateInfo = teamVo.getMemberInfo();
			 if (participateInfo instanceof SomeoneClient) {
					for (OrganicationClient org : ((SomeoneClient) participateInfo)	.getOrganizations()) {
						if (!staffsArea.containsItem(org)) {
							staffsArea.addItem(org);
						}
					}
			}
	}
	@Override
	public void setBreadCrumbs(Widget breadCrumbs) {
		this.breadCrumbs.clear();
		this.breadCrumbs.add(breadCrumbs);
	}

	@Override
	public HasValue<String> getName() {
		return name;
	}
	@Override
	public HasClickHandlers getChooseStaffBtnClick() {//负责人选择事件
		return chooseBtn;
	}
	@Override
	public SpecialTextArea<OrganicationClient> getSpecialTextArea() {
		return staffArea;
	}
	@Override
	public HasClickHandlers getChooseStaffBtnsClick() {//成员人选择事件
		return chooseBtns;
	}
	@Override
	public SpecialTextArea<OrganicationClient> getSpecialTextAreas() {
		return staffsArea;
	}
	@Override
	public List<String> getManagerIds() {//保存时要得到负责人的ID列表
		List<String> manager = new ArrayList<String>();
		List<OrganicationClient> existKeys = staffArea.getItemList();
		for (OrganicationClient key : existKeys) {
			manager.add(key.getId());
			}
		return manager;
	}
	@Override
	public List<String> getMemberIds() {//保存时要得到成员的ID列表
		List<String> staff = new ArrayList<String>();
		List<OrganicationClient> existKeys = staffsArea.getItemList();
		 for (OrganicationClient key : existKeys) {
				staff.add(key.getId());
			}
		return staff;
	}
	@Override
	public void clear() {

	}

	@Override
	public HasValue<String> getExplains() {
		return explains;
	}

	
	
	@Override
	public HasClickHandlers getSaveClick() {
		return save;
	}
	
	@Override
	public HasClickHandlers getBackClick() {
		return back;
	}

	
	
}
