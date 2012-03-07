package com.chinarewards.gwt.elt.client.broadcastReply.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.broadcastReply.request.BroadcastReplyAddRequest;
import com.chinarewards.gwt.elt.client.broadcastReply.request.BroadcastReplyAddResponse;
import com.chinarewards.gwt.elt.client.broadcasting.plugin.BroadcastingListConstants;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.util.StringUtil;
import com.chinarewards.gwt.elt.client.win.Win;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class BroadcastReplyPresenterImpl extends
		BasePresenter<BroadcastReplyPresenter.BroadcastReplyDisplay> implements
		BroadcastReplyPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;
	String broadcastId = null;
	private final BreadCrumbsPresenter breadCrumbs;

	@Inject
	public BroadcastReplyPresenterImpl(
			EventBus eventBus,
			BroadcastReplyDisplay display,
			DispatchAsync dispatch,
			SessionManager sessionManager,
			Win win,
			BreadCrumbsPresenter breadCrumbs,
			ErrorHandler errorHandler) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler = errorHandler;
		this.win = win;
		this.breadCrumbs = breadCrumbs;

	}

	@Override
	public void bind() {

		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());

		registerHandler(display.getSaveBtnClickHandlers().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						if(StringUtil.isEmpty(display.getContent()))
						{
							win.alert("请填写回复内容");
							return;
						}
						
						dispatch.execute(new BroadcastReplyAddRequest(broadcastId,display.getContent(),sessionManager.getSession()),
								new AsyncCallback<BroadcastReplyAddResponse>() {

									@Override
									public void onFailure(Throwable t) {
										win.alert(t.getMessage());
									}

									@Override
									public void onSuccess(BroadcastReplyAddResponse resp) {
										win.alert("保存成功");
										Platform.getInstance()
										.getEditorRegistry()
										.openEditor(
												BroadcastingListConstants.EDITOR_BROADCASTINGLIST_SEARCH,
												"EDITOR_BROADCASTINGLIST_SEARCH_DO_ID", null);
									}
								});

					}
				}));

	}

	@Override
	public void initBroadcast(String broadcastId) {
		this.broadcastId = broadcastId;
	}

}
