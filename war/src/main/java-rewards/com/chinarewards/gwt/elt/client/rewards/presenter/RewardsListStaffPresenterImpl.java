package com.chinarewards.gwt.elt.client.rewards.presenter;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.dataprovider.RewardsListStaffViewAdapter;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemResponse;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsGridClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsGridCriteria;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemCriteria;
import com.chinarewards.gwt.elt.client.rewards.presenter.RewardsListStaffPresenter.RewardsListStaffDisplay;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.ui.HyperLinkCell;
import com.chinarewards.gwt.elt.client.widget.EltNewPager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager.TextLocation;
import com.chinarewards.gwt.elt.client.widget.GetValue;
import com.chinarewards.gwt.elt.client.widget.ListCellTable;
import com.chinarewards.gwt.elt.client.widget.Sorting;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.model.rewards.RewardPageType;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.Inject;

public class RewardsListStaffPresenterImpl extends
		BasePresenter<RewardsListStaffDisplay> implements
		RewardsListStaffPresenter {
	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;

	RewardPageType pageType;

	EltNewPager pager;
	ListCellTable<RewardsGridClient> cellTable;
	RewardsListStaffViewAdapter listViewAdapter;

	private final BreadCrumbsPresenter breadCrumbs;

	@Inject
	public RewardsListStaffPresenterImpl(EventBus eventBus,
			DispatchAsync dispatch, ErrorHandler errorHandler,
			SessionManager sessionManager, RewardsListStaffDisplay display,
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

		registerHandler(display.getSearchBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						// win.alert(sessionManager.getSession().getLoginName());
						initWidget();
					}
				}));
	}

	private void initWidget() {		
		initRewardsItemSelect("");	

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
		criteria.setStaffName(display.getWinnerName().getValue());
	
		int selectedIndex = display.getRewardsItem().getSelectedIndex();
		if (selectedIndex > -1) {
			String rewardsItemId = display.getRewardsItem().getValue(
					selectedIndex);
			criteria.setRewardsItemId(rewardsItemId);
		}

		criteria.setRewardsDate(display.getRewardsTime().getValue());
		
		criteria.setThisAction("Rewards_STAFF");

		listViewAdapter = new RewardsListStaffViewAdapter(dispatch, criteria,
				errorHandler, sessionManager, display);
		listViewAdapter.addDataDisplay(cellTable);

	}

	private void initRewardsItemSelect(String selectValue) {
		ListBox itemList= display.getRewardsItem();
		
		if (itemList!=null) {
			if(itemList.getItemCount()<1){
				dispatch.execute(new SearchRewardsItemRequest(
						new RewardsItemCriteria(), sessionManager.getSession()),
						new AsyncCallback<SearchRewardsItemResponse>() {
							@Override
							public void onFailure(Throwable arg0) {
								errorHandler.alert("加载奖励异常 !");
							}

							@Override
							public void onSuccess(SearchRewardsItemResponse response) {
								List<RewardsItemClient> itemList = response.getResult();
								if (itemList != null) {
									if (itemList.size() > 0) {
										display.getRewardsItem().clear();
										display.getRewardsItem().addItem("不限", "");
										for (int i = 0; i < itemList.size(); i++) {
											RewardsItemClient item = itemList.get(i);
											display.getRewardsItem().addItem(
													item.getName(), item.getId());
										}
									}
								}
							}
						});
			}
			
		}
		
		
	}

	private void initTableColumns() {
		Sorting<RewardsGridClient> ref = new Sorting<RewardsGridClient>() {
			@Override
			public void sortingCurrentPage(Comparator<RewardsGridClient> comparator) {
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
						return client.getRewardsName();
					}
				}, ref, "rewardsName");

		cellTable.addColumn("奖励积分", new TextCell(),
				new GetValue<RewardsGridClient, String>() {
					@Override
					public String getValue(RewardsGridClient client) {
						return client.getAwardAmt();
					}
				}, ref, "totalAmtLimit");

		cellTable.addColumn("奖励时间",
				new DateCell(DateTimeFormat.getFormat("yyyy-MM-dd")),
				new GetValue<RewardsGridClient, Date>() {
					@Override
					public Date getValue(RewardsGridClient client) {
						return client.getRewardsDate();
					}
				}, ref, "rewardsDate");

		cellTable.addColumn("颁奖人", new TextCell(),
				new GetValue<RewardsGridClient, String>() {
					@Override
					public String getValue(RewardsGridClient client) {
						return client.getAwardName();
					}
				}, ref, "awardName");

		cellTable.addColumn("获奖人", new TextCell(),
				new GetValue<RewardsGridClient, String>() {
					@Override
					public String getValue(RewardsGridClient client) {
						return client.getWinnersName();
					}
				}, ref, "winnersName");

	}

	@Override
	public void initRewardsList(RewardPageType pageType) {
		this.pageType = pageType;
	}
}
