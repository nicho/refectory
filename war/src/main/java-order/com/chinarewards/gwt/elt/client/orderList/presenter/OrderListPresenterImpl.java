package com.chinarewards.gwt.elt.client.orderList.presenter;

import java.util.Comparator;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.orderList.dataprovider.OrderListViewAdapter;
import com.chinarewards.gwt.elt.client.orderList.model.OrderListClient;
import com.chinarewards.gwt.elt.client.orderList.model.OrderListCriteria;
import com.chinarewards.gwt.elt.client.orderList.model.OrderListCriteria.StaffStatus;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager.TextLocation;
import com.chinarewards.gwt.elt.client.widget.GetValue;
import com.chinarewards.gwt.elt.client.widget.ListCellTable;
import com.chinarewards.gwt.elt.client.widget.Sorting;
import com.chinarewards.gwt.elt.client.win.Win;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class OrderListPresenterImpl extends
		BasePresenter<OrderListPresenter.OrderListDisplay> implements
		OrderListPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;
	EltNewPager pager;
	ListCellTable<OrderListClient> cellTable;
	OrderListViewAdapter listViewAdapter;

	private final BreadCrumbsPresenter breadCrumbs;
	@Inject
	public OrderListPresenterImpl(EventBus eventBus,
			OrderListDisplay display, DispatchAsync dispatch,
			SessionManager sessionManager,Win win,BreadCrumbsPresenter breadCrumbs,ErrorHandler errorHandler) {
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



	}
	
	private void init() {	
		display.initStaffStatus();
		buildTable();
		doSearch();
	}

	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<OrderListClient>();

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
		OrderListCriteria criteria = new OrderListCriteria();
		criteria.setStaffNameorNo(display.getStaffNameorNo().getValue());
		if(!"ALL".equals(display.getSttaffStatus()))
			criteria.setStaffStatus(StaffStatus.valueOf(display.getSttaffStatus()));

		listViewAdapter = new OrderListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager,display);
		listViewAdapter.addDataDisplay(cellTable);

	}

	private void initTableColumns() {
		Sorting<OrderListClient> ref = new Sorting<OrderListClient>() {
			@Override
			public void sortingCurrentPage(Comparator<OrderListClient> comparator) {
				// listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);

			}
		};


		cellTable.addColumn("订单编号", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient staff) {
						return staff.getStaffNo();
					}
				}, ref, "jobNo");
		cellTable.addColumn("就餐时间", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient staff) {
						return staff.getStaffName();
					}
				}, ref, "name");
		cellTable.addColumn("就餐人数", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient staff) {
						return staff.getStaffName();
					}
				}, ref, "name");
		cellTable.addColumn("包间要求", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient staff) {
						return staff.getStaffName();
					}
				}, ref, "name");
		cellTable.addColumn("下单时间", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient staff) {
						return staff.getStaffName();
					}
				}, ref, "name");
		cellTable.addColumn("订单状态", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient staff) {
						return staff.getStaffName();
					}
				}, ref, "name");
		cellTable.addColumn("处理结果", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient staff) {
						return staff.getStaffName();
					}
				}, ref, "name");
		cellTable.addColumn("处理时间", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient staff) {
						return staff.getStaffName();
					}
				}, ref, "name");
		cellTable.addColumn("修改时间", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient staff) {
						return staff.getStaffName();
					}
				}, ref, "name");
		cellTable.addColumn("完成时间", new TextCell(),
				new GetValue<OrderListClient, String>() {
					@Override
					public String getValue(OrderListClient staff) {
						return staff.getStaffName();
					}
				}, ref, "name");
	}
	

}
