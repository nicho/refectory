package com.chinarewards.gwt.elt.client.colleague.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.colleague.dataprovider.ColleagueListViewAdapter;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.messageSave.dialog.MessageSaveDialog;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.staffList.model.StaffListClient;
import com.chinarewards.gwt.elt.client.staffList.model.StaffListCriteria;
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
import com.google.inject.Provider;

public class ColleagueListPresenterImpl extends
		BasePresenter<ColleagueListPresenter.ColleagueListDisplay> implements
		ColleagueListPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
//	private final Win win;
	final ErrorHandler errorHandler;
	EltNewPager pager;
	ListCellTable<StaffListClient> cellTable;
	ColleagueListViewAdapter listViewAdapter;
	Provider<MessageSaveDialog> messageSaveDialog;

	@Inject
	public ColleagueListPresenterImpl(EventBus eventBus,
			ColleagueListDisplay display, DispatchAsync dispatch,
			SessionManager sessionManager,Win win,ErrorHandler errorHandler,Provider<MessageSaveDialog> messageSaveDialog) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler=errorHandler;
		this.messageSaveDialog=messageSaveDialog;
	//	this.win=win;

	}

	@Override
	public void bind() {

		init();
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
				doSearch(display.getQueryKey().getValue());
			}
		});
	}
	
	private void init() {	
	
		buildTable();
		doSearch(null);
		
		
	}

	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<StaffListClient>();

		
		pager = new EltNewPager(TextLocation.CENTER);
		pager.setDisplay(cellTable);
		cellTable.setWidth(ViewConstants.page_width);
		cellTable.setPageSize(ViewConstants.per_page_number);
	//	cellTable.getColumn(0).setCellStyleNames("divTextLeft");
//		display.getResultPanel().clear();
//		display.getResultPanel().add(cellTable);
		display.getResultpage().clear();
		display.getResultpage().add(pager);
		
	}

	private void doSearch(String key) {
		StaffListCriteria criteria = new StaffListCriteria();
		if(!StringUtil.isEmpty(key))
			criteria.setStaffNameorNo(key);
		listViewAdapter = new ColleagueListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager,display,messageSaveDialog);
		listViewAdapter.addDataDisplay(cellTable);

	}


}
