package com.chinarewards.gwt.elt.client.userList.presenter;

import java.util.Comparator;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.userList.dataprovider.UserListViewAdapter;
import com.chinarewards.gwt.elt.client.userList.model.UserListClient;
import com.chinarewards.gwt.elt.client.userList.model.UserListCriteria;
import com.chinarewards.gwt.elt.client.userList.model.UserListCriteria.StaffStatus;
import com.chinarewards.gwt.elt.client.widget.EltNewPager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager.TextLocation;
import com.chinarewards.gwt.elt.client.widget.GetValue;
import com.chinarewards.gwt.elt.client.widget.ListCellTable;
import com.chinarewards.gwt.elt.client.widget.Sorting;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.model.user.UserRoleVo;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;

public class UserListPresenterImpl extends
		BasePresenter<UserListPresenter.UserListDisplay> implements
		UserListPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;
	EltNewPager pager;
	ListCellTable<UserListClient> cellTable;
	UserListViewAdapter listViewAdapter;

	private final BreadCrumbsPresenter breadCrumbs;
	@Inject
	public UserListPresenterImpl(EventBus eventBus,
			UserListDisplay display, DispatchAsync dispatch,
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


		registerHandler(display.getSynchronousStaffBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						win.alert("同步");
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
		cellTable = new ListCellTable<UserListClient>();

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
		UserListCriteria criteria = new UserListCriteria();
		criteria.setStaffNameorNo(display.getStaffNameorNo().getValue());
		if(!"ALL".equals(display.getSttaffStatus()))
			criteria.setStaffStatus(StaffStatus.valueOf(display.getSttaffStatus()));
		if(!"ALL".equals(display.getSttaffRole()))
			criteria.setStaffRole(UserRoleVo.valueOf(display.getSttaffRole()));

		listViewAdapter = new UserListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager,display);
		listViewAdapter.addDataDisplay(cellTable);

	}

	private void initTableColumns() {
		Sorting<UserListClient> ref = new Sorting<UserListClient>() {
			@Override
			public void sortingCurrentPage(Comparator<UserListClient> comparator) {
				// listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);

			}
		};


		cellTable.addColumn("用户名", new TextCell(),
				new GetValue<UserListClient, String>() {
					@Override
					public String getValue(UserListClient staff) {
						return staff.getStaffName();
					}
				}, ref, "userName");
		cellTable.addColumn("角色", new TextCell(),
				new GetValue<UserListClient, String>() {
					@Override
					public String getValue(UserListClient staff) {
						return staff.getStaffName();
					}
				}, ref, "role");
	
		
	}
	

}
