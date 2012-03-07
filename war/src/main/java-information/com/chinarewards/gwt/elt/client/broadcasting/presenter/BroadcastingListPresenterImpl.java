package com.chinarewards.gwt.elt.client.broadcasting.presenter;

import java.util.Comparator;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.broadcastReply.plugin.BroadcastReplyConstants;
import com.chinarewards.gwt.elt.client.broadcastSave.plugin.BroadcastSaveConstants;
import com.chinarewards.gwt.elt.client.broadcasting.dataprovider.BroadcastingListViewAdapter;
import com.chinarewards.gwt.elt.client.broadcasting.model.BroadcastingListClient;
import com.chinarewards.gwt.elt.client.broadcasting.model.BroadcastingListCriteria;
import com.chinarewards.gwt.elt.client.broadcasting.model.BroadcastingListCriteria.BroadcastingStatus;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.detailsOfBroadcast.plugin.DetailsOfBroadcastConstants;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.ui.HyperLinkCell;
import com.chinarewards.gwt.elt.client.widget.EltNewPager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager.TextLocation;
import com.chinarewards.gwt.elt.client.widget.GetValue;
import com.chinarewards.gwt.elt.client.widget.ListCellTable;
import com.chinarewards.gwt.elt.client.widget.Sorting;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.util.DateTool;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class BroadcastingListPresenterImpl extends
		BasePresenter<BroadcastingListPresenter.BroadcastingListDisplay> implements
		BroadcastingListPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;
	EltNewPager pager;
	ListCellTable<BroadcastingListClient> cellTable;
	BroadcastingListViewAdapter listViewAdapter;

	private final BreadCrumbsPresenter breadCrumbs;
	@Inject
	public BroadcastingListPresenterImpl(EventBus eventBus,
			BroadcastingListDisplay display, DispatchAsync dispatch,
			SessionManager sessionManager,BreadCrumbsPresenter breadCrumbs,ErrorHandler errorHandler,Win win) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler=errorHandler;
		this.win=win;
		this.breadCrumbs=breadCrumbs;
	}

	@Override
	public void bind() {
		breadCrumbs.loadListPage();
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		init();
		registerHandler(display.getSearchBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						doSearch();
					}
				}));

		registerHandler(display.getAddBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								BroadcastSaveConstants.EDITOR_BROADCASTSAVE_SEARCH,
								"EDITOR_BROADCASTSAVE_SEARCH_DO_ID", null);
					}
				}));
	
	}
	
	private void init() {	
		display.initStatus();
		buildTable();
		doSearch();
	}

	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<BroadcastingListClient>();

		initTableColumns();
		pager = new EltNewPager(TextLocation.CENTER);
		pager.setDisplay(cellTable);
		cellTable.setWidth(ViewConstants.page_width);
		cellTable.setPageSize(ViewConstants.per_page_number_in_dialog);
	//	cellTable.getColumn(0).setCellStyleNames("divTextLeft");
		display.getResultPanel().clear();
		display.getResultPanel().add(cellTable);
		display.getResultpage().clear();
		display.getResultpage().add(pager);
		
	}

	private void doSearch() {
		BroadcastingListCriteria criteria = new BroadcastingListCriteria();
		if(!"ALL".equals(display.getStatus()))
			criteria.setStatus(BroadcastingStatus.valueOf(display.getStatus()));
		if(!StringUtil.isEmpty(display.getCreateUser()))
			criteria.setCreatedByUserName(display.getCreateUser());
		if(display.getBroadcastingTime()!=null)
			criteria.setBroadcastingTimeStart(display.getBroadcastingTime());
		if(display.getBroadcastingTimeEnd()!=null)
			criteria.setBroadcastingTimeEnd(display.getBroadcastingTimeEnd());
		
		listViewAdapter = new BroadcastingListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager,display);
		listViewAdapter.addDataDisplay(cellTable);

	}

	private void initTableColumns() {
		Sorting<BroadcastingListClient> ref = new Sorting<BroadcastingListClient>() {
			@Override
			public void sortingCurrentPage(Comparator<BroadcastingListClient> comparator) {
				// listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);

			}
		};


		cellTable.addColumn("广播编号", new TextCell(),
				new GetValue<BroadcastingListClient, String>() {
					@Override
					public String getValue(BroadcastingListClient staff) {
						return staff.getNumber();
					}
				}, ref, "number");
		cellTable.addColumn("广播内容", new TextCell(),
				new GetValue<BroadcastingListClient, String>() {
					@Override
					public String getValue(BroadcastingListClient staff) {
						if(staff.getContent().length()>30)
							return staff.getContent().substring(0,30)+"...";
						else
							return staff.getContent();
					}
				}, ref, "content");
		cellTable.addColumn("状态", new TextCell(),
				new GetValue<BroadcastingListClient, String>() {
					@Override
					public String getValue(BroadcastingListClient staff) {
						return staff.getStatus().getDisplayName();
					}
				});
		cellTable.addColumn("创建人", new TextCell(),
				new GetValue<BroadcastingListClient, String>() {
					@Override
					public String getValue(BroadcastingListClient staff) {
						return staff.getCreatedByUserName();
					}
				});
		cellTable.addColumn("广播类别", new TextCell(),
				new GetValue<BroadcastingListClient, String>() {
					@Override
					public String getValue(BroadcastingListClient staff) {
						return staff.getCategory().getDisplayName();
					}
				});
		cellTable.addColumn("广播时间", new TextCell(),
				new GetValue<BroadcastingListClient, String>() {
					@Override
					public String getValue(BroadcastingListClient staff) {
						return DateTool.dateToString(staff.getBroadcastingTime());
					}
				});
		cellTable.addColumn("回复数", new TextCell(),
				new GetValue<BroadcastingListClient, String>() {
					@Override
					public String getValue(BroadcastingListClient staff) {
						return staff.getReplyNumber()+"";
					}
				});
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<BroadcastingListClient, String>() {
					@Override
					public String getValue(BroadcastingListClient rewards) {
						return "查看详细";
					}
				}, new FieldUpdater<BroadcastingListClient, String>() {

					@Override
					public void update(int index, final BroadcastingListClient o,
							String value) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								DetailsOfBroadcastConstants.EDITOR_DETAILSOFBROADCAST_SEARCH,
								"EDITOR_DETAILSOFBROADCAST_SEARCH_DO_ID", o.getId());
					}

				});
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<BroadcastingListClient, String>() {
					@Override
					public String getValue(BroadcastingListClient rewards) {
						return "回复";
					}
				}, new FieldUpdater<BroadcastingListClient, String>() {

					@Override
					public void update(int index, final BroadcastingListClient o,
							String value) {
						if(o.isAllowreplies())
						{
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								BroadcastReplyConstants.EDITOR_BROADCASTREPLY_SEARCH,
								"EDITOR_BROADCASTREPLY_SEARCH_DO_ID",  o.getId());
						}
						else
						{
							win.alert("不允许回复!");
						}
					}

				});
	}

}
