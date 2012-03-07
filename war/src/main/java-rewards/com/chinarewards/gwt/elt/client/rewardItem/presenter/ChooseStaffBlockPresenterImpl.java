package com.chinarewards.gwt.elt.client.rewardItem.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.ui.DialogCloseListener;
import com.chinarewards.gwt.elt.client.dataprovider.OrganizationSuggestOracle;
import com.chinarewards.gwt.elt.client.dataprovider.OrganizationSuggestOracle.OrganizationSuggest;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewardItem.dialog.ChooseStaffWinDialog;
import com.chinarewards.gwt.elt.client.rewardItem.event.ChooseStaffEvent;
import com.chinarewards.gwt.elt.client.rewardItem.handler.ChooseStaffHandler;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.EveryoneClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.SomeoneClient;
import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.chinarewards.gwt.elt.client.rewards.model.StaffOrDepartmentAC;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ChooseStaffBlockPresenterImpl extends
		BasePresenter<ChooseStaffBlockPresenter.ChooseStaffBlockDisplay>
		implements ChooseStaffBlockPresenter {

	private final Provider<ChooseStaffWinDialog> chooseStaffDialogProvider;
	private final SessionManager sessionManager;
	private final DispatchAsync dispatcher;
	final TextBox suggestTextBox = new TextBox();
	@Inject
	public ChooseStaffBlockPresenterImpl(EventBus eventBus,
			ChooseStaffBlockDisplay display,
			Provider<ChooseStaffWinDialog> chooseStaffDialogProvider,
			DispatchAsync dispatcher, SessionManager sessionManager) {
		super(eventBus, display);
		this.chooseStaffDialogProvider = chooseStaffDialogProvider;
		this.dispatcher = dispatcher;
		this.sessionManager = sessionManager;
		
	}

	private void initSuggestion() {
		
		if(display.isEveryone().getValue()){
			suggestTextBox.setEnabled(false);
		}else{
			suggestTextBox.setEnabled(true);
		}
		SuggestOracle suggest = new OrganizationSuggestOracle(dispatcher,sessionManager);
		final SuggestBox box = new SuggestBox(suggest, suggestTextBox);
		box.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
			@Override
			public void onSelection(SelectionEvent<Suggestion> selectionEvent) {
				// restore data.
				OrganizationSuggest suggest = (OrganizationSuggest) selectionEvent.getSelectedItem();
				StaffOrDepartmentAC sd = suggest.getSd();
				OrganicationClient org = new OrganicationClient(sd.getId(), sd.getName());
				if (!display.getSpecialTextArea().containsItem(org)) {
					// display.getStaffMap().put(org.getId(), org);
					display.getSpecialTextArea().addItem(org);
				}
				suggestTextBox.setValue("");
				suggestTextBox.setFocus(true);
			}
		});
		box.setFocus(true);
		box.setStyleName("searchStaff");
		Label label1 = new Label("快速选择：");
		display.getSuggestBoxPanel().add(label1);
		display.getSuggestBoxPanel().add(box);
		Label label = new Label("输入（员工，部门，小组）名称");
		//label.getElement().setAttribute("style", "float:left;");
		display.getSuggestBoxPanel().add(label);
	}

	public void bind() {
		initSuggestion();
		//display.isEveryone().setValue(true, true);
		display.isEveryone().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
				@Override
				public void onValueChange(ValueChangeEvent<Boolean> event) {
					if (event.getValue()) {
						suggestTextBox.setEnabled(false);
					} 
				}
			});
		display.isSomeone().addValueChangeHandler(new ValueChangeHandler<Boolean>() {
				@Override
				public void onValueChange(ValueChangeEvent<Boolean> event) {
					if (event.getValue()) {
						suggestTextBox.setEnabled(true);
					}
				}

			});
		registerHandler(display.getChooseStaffBtnClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						final ChooseStaffWinDialog dialog = chooseStaffDialogProvider.get();
						dialog.setNominee(false, true, null);
						final HandlerRegistration registration = eventBus.addHandler(ChooseStaffEvent.getType(),new ChooseStaffHandler() {
											@Override
											public void chosenStaff(List<StaffClient> list) {
												for (StaffClient r : list) {
													
													if (!display.getSpecialTextArea().containsItem(r)) {
														
														display.getSpecialTextArea().addItem(r);
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
		if (display.isEveryone().getValue()) {
			participateInfo = new EveryoneClient(sessionManager.getSession().getCorporationId());//登录人所在的企业的ID
		} else if (display.isSomeone().getValue()) {
			List<OrganicationClient> orgs = new ArrayList<OrganicationClient>();
			for (String orgId : display.getRealOrginzationIds()) {
				orgs.add(new OrganicationClient(orgId, ""));
			}
			participateInfo = new SomeoneClient(orgs);
		}
		return participateInfo;
	}

	
}
