package com.chinarewards.gwt.elt.client.gift.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.gift.presenter.GiftViewPresenter;
import com.chinarewards.gwt.elt.client.gift.plugin.GiftConstants;
import com.chinarewards.gwt.elt.client.gift.request.SearchGiftByIdRequest;
import com.chinarewards.gwt.elt.client.gift.request.SearchGiftByIdResponse;
import com.chinarewards.gwt.elt.client.gift.model.GiftClient;
import com.chinarewards.gwt.elt.client.gift.model.GiftVo;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class GiftViewPresenterImpl extends
		BasePresenter<GiftViewPresenter.GiftViewDisplay> implements
		GiftViewPresenter {
	String instanceId;// 修改时传过来的ID

	private final DispatchAsync dispatcher;
	private final ErrorHandler errorHandler;
	String giftId;
	GiftClient giftClient = new GiftClient();
	

	private final BreadCrumbsPresenter breadCrumbs;

	@Inject
	public GiftViewPresenterImpl(EventBus eventBus, GiftViewDisplay display,
			DispatchAsync dispatcher, ErrorHandler errorHandler,
			SessionManager sessionManager,BreadCrumbsPresenter breadCrumbs) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.errorHandler = errorHandler;
		this.breadCrumbs=breadCrumbs;
	}

	@Override
	public void bind() {
		breadCrumbs.loadChildPage("查看礼品");
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		
		registerHandler(display.getBackClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						breadCrumbs.getGoHistory();
					}
				}));

		registerHandler(display.getUpdateClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						giftClient.setId(giftId);
						giftClient
								.setThisAction(GiftConstants.ACTION_GIFT_EDIT);
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(GiftConstants.EDITOR_GIFT_EDIT,
										GiftConstants.ACTION_GIFT_EDIT,
										giftClient);
					}
				}));
	}

	// 查看时初始化数据
	@Override
	public void initInstanceId(String instanceId, GiftClient giftClient) {
		this.instanceId = instanceId;
		this.giftClient = giftClient;
		initDataToViewGift(giftClient, instanceId);
	}

	private void initDataToViewGift(final GiftClient giftClient,
			final String instanceId) {
		giftId = giftClient.getId();
		dispatcher.execute(new SearchGiftByIdRequest(giftId),
				new AsyncCallback<SearchGiftByIdResponse>() {
					@Override
					public void onFailure(Throwable arg0) {
						errorHandler.alert("查询礼品出错!");
						Platform.getInstance()
								.getEditorRegistry()
								.closeEditor(GiftConstants.EDITOR_GIFT_VIEW,
										instanceId);
					}

					@Override
					public void onSuccess(SearchGiftByIdResponse response) {
						GiftVo item = response.getGift();
						display.showGift(item);
					}

				});

	}

}
