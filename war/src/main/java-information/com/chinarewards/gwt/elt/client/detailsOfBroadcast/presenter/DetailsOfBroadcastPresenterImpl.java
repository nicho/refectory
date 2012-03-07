package com.chinarewards.gwt.elt.client.detailsOfBroadcast.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.broadcastReply.dataprovider.ReplyListViewAdapter;
import com.chinarewards.gwt.elt.client.broadcastReply.model.ReplyListClient;
import com.chinarewards.gwt.elt.client.broadcastReply.model.ReplyListCriteria;
import com.chinarewards.gwt.elt.client.broadcastSave.plugin.BroadcastSaveConstants;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.detailsOfBroadcast.request.DetailsOfBroadcastRequest;
import com.chinarewards.gwt.elt.client.detailsOfBroadcast.request.DetailsOfBroadcastResponse;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.view.constant.CssStyleConstants;
import com.chinarewards.gwt.elt.client.widget.EltNewPager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager.TextLocation;
import com.chinarewards.gwt.elt.client.widget.ListCellTable;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class DetailsOfBroadcastPresenterImpl extends
		BasePresenter<DetailsOfBroadcastPresenter.DetailsOfBroadcastDisplay>
		implements DetailsOfBroadcastPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	//private final Win win;
	final ErrorHandler errorHandler;
	String broadcastId = null;
	private final BreadCrumbsPresenter breadCrumbs;

	EltNewPager pager;
	ListCellTable<ReplyListClient> cellTable;
	ReplyListViewAdapter listViewAdapter;

	@Inject
	public DetailsOfBroadcastPresenterImpl(EventBus eventBus,
			DetailsOfBroadcastDisplay display, DispatchAsync dispatch,
			SessionManager sessionManager,// Win win,
			BreadCrumbsPresenter breadCrumbs, ErrorHandler errorHandler) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler = errorHandler;
//		this.win = win;
		this.breadCrumbs = breadCrumbs;

	}

	@Override
	public void bind() {
		breadCrumbs.loadChildPage("广播详细");
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		init();

		registerHandler(display.getUpdateBtnClickHandlers().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										BroadcastSaveConstants.EDITOR_BROADCASTSAVE_SEARCH,
										"EDITOR_BROADCASTSAVE_SEARCH_DO_ID",
										broadcastId);

					}
				}));

	}

	private void init() {
		dispatch.execute(new DetailsOfBroadcastRequest(broadcastId),
				new AsyncCallback<DetailsOfBroadcastResponse>() {
					@Override
					public void onFailure(Throwable e) {
						errorHandler.alert(e.getMessage());
					}

					@Override
					public void onSuccess(DetailsOfBroadcastResponse response) {
						display.setAllowreplies(response.getAllowreplies());
						display.setBroadcastingTime(response
								.getBroadcastingTime());
						display.setContent(response.getContent());
						display.setReceivingObject(response
								.getReceivingObject());
						display.setCreateUser(response.getCreateUser());
						if("允许回复".equals(response.getAllowreplies()))
						{
							buildTable();
							doSearch();
						}
						else
						{
							display.getResultpage().getElement().getParentElement().getParentElement().addClassName(CssStyleConstants.hidden);
							display.getDataCount().getElement().getParentElement().addClassName(CssStyleConstants.hidden);
						}
					}

				});

	}

	@Override
	public void initBroadcastDetails(String broadcastId) {
		this.broadcastId = broadcastId;
	}

	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<ReplyListClient>();

		
		pager = new EltNewPager(TextLocation.CENTER);
		pager.setDisplay(cellTable);
		cellTable.setWidth(ViewConstants.page_width);
		cellTable.setPageSize(ViewConstants.per_page_number_in_entry);
		// cellTable.getColumn(0).setCellStyleNames("divTextLeft");
//		display.getResultPanel().clear();
//		display.getResultPanel().add(cellTable);
		display.getResultpage().clear();
		display.getResultpage().add(pager);

	}

	private void doSearch() {
		ReplyListCriteria criteria = new ReplyListCriteria();
		criteria.setBroadcastId(broadcastId);
		listViewAdapter = new ReplyListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager, display);
		listViewAdapter.addDataDisplay(cellTable);

	}


}
