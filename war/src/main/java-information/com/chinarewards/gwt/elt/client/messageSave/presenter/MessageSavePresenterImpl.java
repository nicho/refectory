package com.chinarewards.gwt.elt.client.messageSave.presenter;

import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.broadcastSave.dialog.StaffChooseOrganizationListDialog;
import com.chinarewards.gwt.elt.client.broadcastSave.request.BroadcastUpdateRequest;
import com.chinarewards.gwt.elt.client.broadcastSave.request.BroadcastUpdateResponse;
import com.chinarewards.gwt.elt.client.chooseOrganization.event.ChooseOrganizationEvent;
import com.chinarewards.gwt.elt.client.chooseOrganization.handler.ChooseOrganizationHandler;
import com.chinarewards.gwt.elt.client.chooseOrganization.model.OrganSearchCriteria.OrganType;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.ui.DialogCloseListener;
import com.chinarewards.gwt.elt.client.messageSave.request.MessageSaveRequest;
import com.chinarewards.gwt.elt.client.messageSave.request.MessageSaveResponse;
import com.chinarewards.gwt.elt.client.mvp.BaseDialogPresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.util.StringUtil;
import com.chinarewards.gwt.elt.client.win.Win;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class MessageSavePresenterImpl extends
		BaseDialogPresenter<MessageSavePresenter.MessageSaveDisplay> implements
		MessageSavePresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;
	String broadcastId = null;
	String staffId=null;

	private final Provider<StaffChooseOrganizationListDialog> chooseOrganizationDialogProvider;

	@Inject
	public MessageSavePresenterImpl(
			EventBus eventBus,
			MessageSaveDisplay display,
			DispatchAsync dispatch,
			SessionManager sessionManager,
			Win win,
			ErrorHandler errorHandler,
			Provider<StaffChooseOrganizationListDialog> chooseOrganizationDialogProvider) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler = errorHandler;
		this.win = win;

		this.chooseOrganizationDialogProvider = chooseOrganizationDialogProvider;
	}

	@Override
	public void bind() {

		init();
		registerHandler(display.getSaveBtnClickHandlers().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						if (StringUtil.isEmpty(display.getContent())) {
							win.alert("请填写信息内容!");
							return;
						}

						MessageSaveRequest request = new MessageSaveRequest();
						if (broadcastId != null)
							request.setBroadcastId(broadcastId);
						request.setSession(sessionManager.getSession());

						request.setContent(display.getContent());

						request.setOrganList(display.getRealOrginzationIds());

						dispatch.execute(request,
								new AsyncCallback<MessageSaveResponse>() {

									@Override
									public void onFailure(Throwable t) {
										win.alert(t.getMessage());
									}

									@Override
									public void onSuccess(
											MessageSaveResponse resp) {
										win.alert("保存成功");
										display.setContent("");
										closeDialog();
									}
								});
					}
				}));

		registerHandler(display.getChooseBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						final StaffChooseOrganizationListDialog dialog = chooseOrganizationDialogProvider
								.get();
						final HandlerRegistration registration = eventBus
								.addHandler(ChooseOrganizationEvent.getType(),
										new ChooseOrganizationHandler() {
											@Override
											public void chosenOrganization(
													List<OrganicationClient> list) {
												for (OrganicationClient r : list) {
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
		registerHandler(display.getReturnBtnClickHandlers().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						closeDialog();

					}
				}));

	}

	private void init() {
		if (broadcastId != null) {
			// // 修改加载数据
			dispatch.execute(new BroadcastUpdateRequest(broadcastId),
					new AsyncCallback<BroadcastUpdateResponse>() {
						@Override
						public void onFailure(Throwable e) {
							errorHandler.alert(e.getMessage());
						}

						@Override
						public void onSuccess(BroadcastUpdateResponse response) {

							display.setContent(response.getContent());
							if (response.getReceivingObject().size() > 0) {
								for (OrganicationClient client : response
										.getReceivingObject()) {
									display.getSpecialTextArea()
											.addItem(client);
								}
							}

						}

					});

		}

	}

	@Override
	public void initBroadcastStaff(String staffId,String staffName) {
		this.staffId=staffId;
		OrganicationClient client=new OrganicationClient();
		client.setId(staffId);
		client.setName(staffName);
		client.setType(OrganType.STAFF);
		display.getSpecialTextArea().addItem(client);
	}

	@Override
	public void displaySelectStaff() {
		display.displaySelectStaff();
		
	}



}
