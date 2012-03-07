package com.chinarewards.gwt.elt.client.corpBroadcast.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.broadcasting.model.BroadcastingListCriteria.BroadcastingCategory;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.corpBroadcast.dataprovider.CorpBroadcastViewAdapter;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.model.StaffHeavenIndexClient;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.model.StaffHeavenIndexCriteria;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager.TextLocation;
import com.chinarewards.gwt.elt.client.widget.ListCellTable;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.inject.Inject;

public class CorpBroadcastPresenterImpl extends
		BasePresenter<CorpBroadcastPresenter.CorpBroadcastDisplay>
		implements CorpBroadcastPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;
	EltNewPager pager;
	ListCellTable<StaffHeavenIndexClient> cellTable;
	CorpBroadcastViewAdapter listViewAdapter;
	String staffId;
	BroadcastingCategory broadcastingCategory=BroadcastingCategory.COMPANYBROADCAST;
	@Inject
	public CorpBroadcastPresenterImpl(EventBus eventBus,
			CorpBroadcastDisplay display, DispatchAsync dispatch,
			SessionManager sessionManager, ErrorHandler errorHandler, Win win) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler = errorHandler;
		this.win = win;

	}

	@Override
	public void bind() {

		init();

	}


	private void init() {
		
		buildTable();
		doSearch(broadcastingCategory,null);
		
		display.getQueryKey().addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				if("输入关键词".equals(display.getQueryKey().getValue()))
					display.getQueryKey().setText("");
				
			}
 
		});
		display.getQueryBtn().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				doSearch(broadcastingCategory,display.getQueryKey().getValue());
			}
		});
	}

	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<StaffHeavenIndexClient>();

		// initTableColumns();
		pager = new EltNewPager(TextLocation.CENTER);
		pager.setDisplay(cellTable);
		cellTable.setWidth(ViewConstants.page_width);
		cellTable.setPageSize(ViewConstants.per_page_number_in_dialog);
		// cellTable.getColumn(0).setCellStyleNames("divTextLeft");
		// display.getResultPanel().clear();
		// display.getResultPanel().add(cellTable);
		display.getResultpage().clear();
		display.getResultpage().add(pager);

	}

	private void doSearch(BroadcastingCategory category,String key) {
		StaffHeavenIndexCriteria criteria = new StaffHeavenIndexCriteria();
		if(category != null)
			criteria.setCategory(category);
		if(!StringUtil.isEmpty(key))
			criteria.setQueryKey(key);
		if(!StringUtil.isEmpty(staffId))
			criteria.setStaffId(staffId);
		listViewAdapter = new CorpBroadcastViewAdapter(dispatch, criteria,
				errorHandler, sessionManager, display, win);
		listViewAdapter.addDataDisplay(cellTable);

	}

	@Override
	public void initStaffBroadcast(String staffId) {
		this.staffId=staffId;
		this.broadcastingCategory=BroadcastingCategory.STAFFBROADCAST;
		display.setTitleName("员工广播");
		
	}

}
