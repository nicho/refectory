package com.chinarewards.gwt.elt.client.rewardItem.presenter;

import java.util.Comparator;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.dataprovider.RewardsItemListStaffViewAdapter;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemListStaffPresenter.RewardsItemListStaffDisplay;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsGridClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsGridCriteria;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.ui.HyperLinkCell;
import com.chinarewards.gwt.elt.client.widget.EltNewPager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager.TextLocation;
import com.chinarewards.gwt.elt.client.widget.GetValue;
import com.chinarewards.gwt.elt.client.widget.ListCellTable;
import com.chinarewards.gwt.elt.client.widget.Sorting;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.model.rewards.RewardPageType;
import com.google.gwt.cell.client.TextCell;
import com.google.inject.Inject;

public class RewardsItemListStaffPresenterImpl extends
		BasePresenter<RewardsItemListStaffDisplay> implements
		RewardsItemListStaffPresenter {
	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;

	RewardPageType pageType;

	EltNewPager pager;
	ListCellTable<RewardsGridClient> cellTable;
	RewardsItemListStaffViewAdapter listViewAdapter;

	private final BreadCrumbsPresenter breadCrumbs;

	@Inject
	public RewardsItemListStaffPresenterImpl(EventBus eventBus,
			DispatchAsync dispatch, ErrorHandler errorHandler,
			SessionManager sessionManager, RewardsItemListStaffDisplay display,
			Win win, BreadCrumbsPresenter breadCrumbs) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
		this.breadCrumbs = breadCrumbs;

	}

	@Override
	public void bind() {
		breadCrumbs.loadListPage();
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());

		initWidget();

//		registerHandler(display.getSearchBtnClickHandlers().addClickHandler(
//				new ClickHandler() {
//					public void onClick(ClickEvent paramClickEvent) {
//						initWidget();
//					}
//				}));
	}

	private void initWidget() {
		buildTable();
		doSearch();
	}

	private void buildTable() {
		cellTable = new ListCellTable<RewardsGridClient>();

		initTableColumns();
		pager = new EltNewPager(TextLocation.CENTER);
		pager.setDisplay(cellTable);
		cellTable.setWidth(ViewConstants.page_width);
		cellTable.setPageSize(ViewConstants.per_page_number_in_dialog);
		cellTable.getColumn(0).setCellStyleNames("divTextLeft");
		display.getResultPanel().clear();
		display.getResultPanel().add(cellTable);
		display.getResultpage().clear();
		display.getResultpage().add(pager);

	}

	private void doSearch() {
		RewardsGridCriteria criteria = new RewardsGridCriteria();
//		criteria.setName(display.getName().getValue());
//		criteria.setDefinition(display.getDefinition().getValue());
		
		criteria.setThisAction("RewardsItem_STAFF_PARTAKE");

		listViewAdapter = new RewardsItemListStaffViewAdapter(dispatch,
				criteria, errorHandler, sessionManager, display);
		listViewAdapter.addDataDisplay(cellTable);

	}

	private void initTableColumns() {
		Sorting<RewardsGridClient> ref = new Sorting<RewardsGridClient>() {
			@Override
			public void sortingCurrentPage(
					Comparator<RewardsGridClient> comparator) {
				// listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);
			}
		};

		cellTable.addColumn("奖项名称", new HyperLinkCell(),
				new GetValue<RewardsGridClient, String>() {
					@Override
					public String getValue(RewardsGridClient client) {
						return client.getRewardsItemName();
					}
				}, ref, "name");
		
		cellTable.addColumn("奖励积分", new HyperLinkCell(),
				new GetValue<RewardsGridClient, String>() {
					@Override
					public String getValue(RewardsGridClient client) {
						return client.getAwardAmt();
					}
				}, ref, "awardAmt");
		
		cellTable.addColumn("说明", new HyperLinkCell(),
				new GetValue<RewardsGridClient, String>() {
					@Override
					public String getValue(RewardsGridClient client) {
						return client.getRewardsItemDefinition();
					}
				}, ref, "definition");


		cellTable.addColumn("提名人", new TextCell(),
				new GetValue<RewardsGridClient, String>() {
					@Override
					public String getValue(RewardsGridClient client) {
						return client.getNominateName();
					}
				}, ref, "nominateName");

		cellTable.addColumn("提名次数", new TextCell(),
				new GetValue<RewardsGridClient, String>() {
					@Override
					public String getValue(RewardsGridClient client) {
						return client.getNominateCount()+"";
					}
				}, ref, "nominateCount");

	}

	@Override
	public void initRewardsItemList(RewardPageType pageType) {
		this.pageType = pageType;
	}
}
