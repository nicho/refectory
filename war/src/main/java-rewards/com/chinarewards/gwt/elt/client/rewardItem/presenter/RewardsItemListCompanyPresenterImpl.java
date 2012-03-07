package com.chinarewards.gwt.elt.client.rewardItem.presenter;

import java.util.Comparator;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.dataprovider.RewardsItemListCompanyViewAdapter;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewardItem.plugin.RewardsItemConstants;
import com.chinarewards.gwt.elt.client.rewardItem.presenter.RewardsItemListCompanyPresenter.RewardsItemListCompanyOtherDisplay;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsGridClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsGridCriteria;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.ui.HyperLinkCell;
import com.chinarewards.gwt.elt.client.widget.EltNewPager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager.TextLocation;
import com.chinarewards.gwt.elt.client.widget.GetValue;
import com.chinarewards.gwt.elt.client.widget.ListCellTable;
import com.chinarewards.gwt.elt.client.widget.Sorting;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.model.rewards.RewardPageType;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.Inject;

public class RewardsItemListCompanyPresenterImpl extends
		BasePresenter<RewardsItemListCompanyOtherDisplay> implements
		RewardsItemListCompanyPresenter {
	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;

	RewardPageType pageType;

	EltNewPager pager;
	ListCellTable<RewardsGridClient> cellTable;
	RewardsItemListCompanyViewAdapter listViewAdapter;

	private final BreadCrumbsPresenter breadCrumbs;

	@Inject
	public RewardsItemListCompanyPresenterImpl(EventBus eventBus,
			DispatchAsync dispatch, ErrorHandler errorHandler,
			SessionManager sessionManager,
			RewardsItemListCompanyOtherDisplay display, Win win,
			BreadCrumbsPresenter breadCrumbs) {
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

		iniWidget();

		registerHandler(display.getSearchBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						// win.alert(sessionManager.getSession().getLoginName());
						iniWidget();
					}
				}));
	}

	private void iniWidget() {
		initRewardsTypeSelect("");
		buildTable();
		doSearch();
	}

	private void initRewardsTypeSelect(String selectValue) {
		ListBox rewardsType = display.getRewardsType();
		if (rewardsType != null) {
			if (rewardsType.getItemCount() < 1) {
				display.getRewardsType().clear();
				display.getRewardsType().addItem("全部奖项", "ALL");
				display.getRewardsType().addItem("努力冲奖项", "RUSH");
			}
		}
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
		// criteria.setName(display.getName().getValue());
		// criteria.setDefinition(display.getDefinition().getValue());

		int selectedIndex = display.getRewardsType().getSelectedIndex();
		String rewardsType = display.getRewardsType().getValue(selectedIndex);
		criteria.setRewardsType(rewardsType);

		criteria.setThisAction("RewardsItem_COMPANY_OTHER");

		listViewAdapter = new RewardsItemListCompanyViewAdapter(dispatch,
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

		// cellTable.addColumn("奖项编号", new HyperLinkCell(),
		// new GetValue<RewardsGridClient, String>() {
		// @Override
		// public String getValue(RewardsGridClient rewards) {
		// return rewards.getName();
		// }
		// }, ref, "itemNo");

		cellTable.addColumn("奖项名称", new HyperLinkCell(),
				new GetValue<RewardsGridClient, String>() {
					@Override
					public String getValue(RewardsGridClient client) {
						return client.getRewardsItemName();
					}
				}, ref, "name");

		cellTable.addColumn("奖励状态", new TextCell(),
				new GetValue<RewardsGridClient, String>() {
					@Override
					public String getValue(RewardsGridClient client) {
						return client.getRewardStatusName();
					}
				}, ref, "status");

		cellTable.addColumn("创建人", new TextCell(),
				new GetValue<RewardsGridClient, String>() {
					@Override
					public String getValue(RewardsGridClient client) {
						return client.getRewardsItemCreateBy();
					}
				}, ref, "createdBy");

		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<RewardsGridClient, String>() {
					@Override
					public String getValue(RewardsGridClient client) {
						return "查看奖项详细";
					}
				}, new FieldUpdater<RewardsGridClient, String>() {
					@Override
					public void update(int index,
							final RewardsGridClient object, String value) {
						RewardsItemClient client = new RewardsItemClient();
						client.setId(object.getRewardsItemId());
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										RewardsItemConstants.EDITOR_REWARDSITEM_VIEW_STAFF,
										RewardsItemConstants.EDITOR_REWARDSITEM_VIEW_STAFF,
										client);

					}
				});

	}

	@Override
	public void initRewardsItemList(RewardPageType pageType) {
		this.pageType = pageType;
	}
}
