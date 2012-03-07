package com.chinarewards.gwt.elt.client.chooseStaff.presenter;

import java.util.ArrayList;
import java.util.List;

import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.ui.DialogCloseListener;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewardItem.dialog.ChooseStaffListDialog;
import com.chinarewards.gwt.elt.client.rewardItem.event.ChooseStaffEvent;
import com.chinarewards.gwt.elt.client.rewardItem.handler.ChooseStaffHandler;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.SomeoneClient;
import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.chinarewards.gwt.elt.model.ChoosePanel.InitChooseListParam;
import com.chinarewards.gwt.elt.model.ChoosePanel.InitChoosePanelParam;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ChooseStaffPanelPresenterImpl extends
		BasePresenter<ChooseStaffPanelPresenter.ChooseStaffPanelDisplay>
		implements ChooseStaffPanelPresenter {

	private final Provider<ChooseStaffListDialog> chooseStaffDialogProvider;
	// private final SessionManager sessionManager;
	// private final DispatchAsync dispatcher;

	String rewardId;
	String topName;
	InitChooseListParam initChooseListParam;

	@Inject
	public ChooseStaffPanelPresenterImpl(EventBus eventBus,
			ChooseStaffPanelDisplay display,
			Provider<ChooseStaffListDialog> chooseStaffDialogProvider) {
		super(eventBus, display);
		this.chooseStaffDialogProvider = chooseStaffDialogProvider;
		// this.dispatcher = dispatcher;DispatchAsync dispatcher
		// this.sessionManager = sessionManager;, SessionManager sessionManager
	}

	public void bind() {
		registerHandler(display.getChooseStaffBtnClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						final ChooseStaffListDialog dialog = chooseStaffDialogProvider
								.get();
						if (initChooseListParam != null)
							dialog.initChooseList(initChooseListParam);
						dialog.setRewardId(rewardId);
						dialog.setNominee(false, true, null);// The key is the
																// first
																// parameter(false).
						final HandlerRegistration registration = eventBus
								.addHandler(ChooseStaffEvent.getType(),
										new ChooseStaffHandler() {
											@Override
											public void chosenStaff(
													List<StaffClient> list) {
												for (StaffClient r : list) {
													System.out.println("ds=="
															+ r);
													if (!display
															.getSpecialTextArea()
															.containsItem(r)) {
														display.getSpecialTextArea()
																.addItem(r);
													}
												}
											}
										});

						Platform.getInstance().getSiteManager()
								.openDialog(dialog, new DialogCloseListener() {
									public void onClose(String dialogId,
											String instanceId) {
										registration.removeHandler();
									}
								});
					}
				}));
	}

	@Override
	public ParticipateInfoClient getparticipateInfo() {
		ParticipateInfoClient participateInfo = null;

		List<OrganicationClient> orgs = new ArrayList<OrganicationClient>();

		for (String[] orgId : display.getRealOrginzationIds()) {
			orgs.add(new OrganicationClient(orgId[0], orgId[1]));
		}
		participateInfo = new SomeoneClient(orgs);

		return participateInfo;
	}

	@Override
	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;

	}

	@Override
	public void initChoosePanel(InitChoosePanelParam initChooseParam) {
		if (initChooseParam.getTopName() != null)
			display.setTopName(initChooseParam.getTopName());
		if (initChooseParam.getChooseBtnName() != null)
			display.setChooseBtnName(initChooseParam.getChooseBtnName());
		if (initChooseParam.getIscleanStaffTextAreaPanel())
			display.cleanStaffTextAreaPanel();
		if (initChooseParam.getInitChooseListParam() != null)
			this.initChooseListParam = initChooseParam.getInitChooseListParam();

	}
}
