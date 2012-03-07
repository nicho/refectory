package com.chinarewards.gwt.elt.client.rewardItem.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewardItem.plugin.RewardsItemConstants;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemByIdRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemByIdResponse;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class RewardsItemViewStaffPresenterImpl
		extends
		BasePresenter<RewardsItemViewStaffPresenter.RewardsItemViewStaffDisplay>
		implements RewardsItemViewStaffPresenter {

	private final DispatchAsync dispatcher;
	private final ErrorHandler errorHandler;
	private final BreadCrumbsPresenter breadCrumbs;
	String rewardId;
	RewardsItemClient param = new RewardsItemClient();

	@Inject
	public RewardsItemViewStaffPresenterImpl(EventBus eventBus,
			RewardsItemViewStaffDisplay display, DispatchAsync dispatcher,
			BreadCrumbsPresenter breadCrumbs, ErrorHandler errorHandler,
			SessionManager sessionManager) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.errorHandler = errorHandler;
		this.breadCrumbs = breadCrumbs;
	}

	@Override
	public void bind() {
		breadCrumbs.loadChildPage("奖项详细");
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

						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										RewardsItemConstants.EDITOR_REWARDSITEM_ADD,
										"EDITOR_REWARDS_ITEM_ADD" + rewardId,
										param);
					}

				}));

	}

	// 查看时初始化数据
	@Override
	public void initWidget(RewardsItemClient item) {
		param = item;// 把查看得到的VO保存下来给修改时做为参数用
		initDataToEditRewardsItem(item);
	}

	private void initDataToEditRewardsItem(final RewardsItemClient item) {
		rewardId = item.getId();

		dispatcher.execute(new SearchRewardsItemByIdRequest(rewardId, false),
				new AsyncCallback<SearchRewardsItemByIdResponse>() {
					@Override
					public void onFailure(Throwable arg0) {
						errorHandler.alert("查询奖项出错!");
					}

					@Override
					public void onSuccess(SearchRewardsItemByIdResponse response) {
						RewardsItemClient item = response.getRewardsItem();

						display.showRewardsItem(item);
					}
				});

	}
}
