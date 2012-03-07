package com.chinarewards.gwt.elt.client.rewardItem.view;

import java.util.ArrayList;
import java.util.List;

import com.chinarewards.gwt.elt.client.rewardItem.presenter.ChooseStaffBlockPresenter.ChooseStaffBlockDisplay;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.EveryoneClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.SomeoneClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsBaseInfo;
import com.chinarewards.gwt.elt.client.view.OrganizationSpecialTextArea;
import com.chinarewards.gwt.elt.client.view.constant.CssStyleConstants;
import com.chinarewards.gwt.elt.client.widget.SpecialTextArea;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

/**
 * 选择员工或部门小模块-- auto-complete (候选人)
 * 
 * @author yanxin
 * 
 */
public class ChooseStaffBlockWidget extends Composite implements ChooseStaffBlockDisplay {

	@UiField
	RadioButton everyoneRbtn;

	@UiField
	RadioButton someoneRbtn;

	@UiField
	HorizontalPanel suggestBoxPanel;

	@UiField
	Button chooseBtn;

	@UiField
	Panel staffTextAreaPanel;
	@UiField HTMLPanel select1;

	SpecialTextArea<OrganicationClient> staffTextArea;

	/**
	 * 符合条件的员工集合 StaffReacher and DepartmentReacher
	 */
	// Map<String, OrganicationClient> staffMap = new HashMap<String,
	// OrganicationClient>();

	public ChooseStaffBlockWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		init();
	}

	interface ChooseStaffBlockBinder extends
			UiBinder<Widget, ChooseStaffBlockWidget> {
	}

	private static ChooseStaffBlockBinder uiBinder = GWT
			.create(ChooseStaffBlockBinder.class);

	void init() {
		
		 staffTextArea = new OrganizationSpecialTextArea();
		
		 staffTextAreaPanel.add(staffTextArea);
		 everyoneRbtn.setValue(true);
		 //隐藏多人选择
//		 chooseBtn.getElement().getParentElement().getParentElement().getParentElement()
//		   .getParentElement().getParentElement()
//			.addClassName(CssStyleConstants.hidden);
		 chooseBtn.setEnabled(false);
		 everyoneRbtn.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (event.getValue()) {
					chooseBtn.setEnabled(false);
					
//					chooseBtn.getElement().getParentElement()
//							.getParentElement().getParentElement()
//							.getParentElement().getParentElement()
//							.addClassName(CssStyleConstants.hidden);
				} else {
					//RootPanel.get("noPaddingTd").setVisible(true);
					//chooseBtn.setEnabled(true);
				
//					chooseBtn.getElement().getParentElement()
//							.getParentElement().getParentElement()
//							.getParentElement().getParentElement()
//							.removeClassName(CssStyleConstants.hidden);
//					
					
				}
			}
		});
		someoneRbtn.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (event.getValue()) {
					chooseBtn.setEnabled(true);
					
//					chooseBtn.getElement().getParentElement()
//							.getParentElement().getParentElement()
//							.getParentElement().getParentElement()
//							.removeClassName(CssStyleConstants.hidden);
				} else {
					//chooseBtn.setEnabled(false);
					
//					chooseBtn.getElement().getParentElement()
//							.getParentElement().getParentElement()
//							.getParentElement().getParentElement()
//							.addClassName(CssStyleConstants.hidden);
				}
			}

		});
	}
    
 
	/*
	 * @Override public Map<String, OrganicationClient> getStaffMap() { return
	 * staffMap; }
	 */
	@Override
	public SpecialTextArea<OrganicationClient> getSpecialTextArea() {
		return staffTextArea;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public List<String> getRealOrginzationIds() {
		List<String> realOrginzationIds = new ArrayList<String>();
		List<OrganicationClient> existKeys = staffTextArea.getItemList();
		for (OrganicationClient key : existKeys) {
			// if (staffMap.containsKey(key.getId())) {
			// OrganicationClient org = staffMap.get(key.getId());
			realOrginzationIds.add(key.getId());
			// }
		}
		return realOrginzationIds;
	}

	@Override
	public HasValue<Boolean> isEveryone() {
		return everyoneRbtn;
	}

	@Override
	public HasValue<Boolean> isSomeone() {
		return someoneRbtn;
	}

	@Override
	public void showParticipateInfo(RewardsBaseInfo info) {
		// staffMap.clear();
		staffTextArea.clear();

		ParticipateInfoClient participateInfo = info.getParticipateInfo();
		if (participateInfo instanceof EveryoneClient) {
			everyoneRbtn.setValue(true, true);
		} else if (participateInfo instanceof SomeoneClient) {
			someoneRbtn.setValue(true, true);
			for (OrganicationClient org : ((SomeoneClient) participateInfo)
					.getOrganizations()) {
				if (!staffTextArea.containsItem(org)) {
					// staffMap.put(org.getId(), org);
					staffTextArea.addItem(org);
				}
			}
		}
	}

	
	
	@Override
	public HasClickHandlers getChooseStaffBtnClick() {
		return chooseBtn;
	}

	@Override
	public Panel getSuggestBoxPanel() {
		return suggestBoxPanel;
	}
}
