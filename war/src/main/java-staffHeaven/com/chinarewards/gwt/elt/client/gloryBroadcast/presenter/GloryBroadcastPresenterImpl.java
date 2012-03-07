package com.chinarewards.gwt.elt.client.gloryBroadcast.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.broadcasting.model.BroadcastingListCriteria.BroadcastingCategory;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.gloryBroadcast.dataprovider.GloryBroadcastViewAdapter;
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
import com.google.inject.Inject;

public class GloryBroadcastPresenterImpl extends
		BasePresenter<GloryBroadcastPresenter.GloryBroadcastDisplay>
		implements GloryBroadcastPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;
	EltNewPager pager;
	ListCellTable<StaffHeavenIndexClient> cellTable;
	GloryBroadcastViewAdapter listViewAdapter;
	String staffId;
	@Inject
	public GloryBroadcastPresenterImpl(EventBus eventBus,
			GloryBroadcastDisplay display, DispatchAsync dispatch,
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
		doSearch(BroadcastingCategory.REWARDBROADCAST);
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

	private void doSearch(BroadcastingCategory category) {
		StaffHeavenIndexCriteria criteria = new StaffHeavenIndexCriteria();
		if (category != null)
			criteria.setCategory(category);
		if(!StringUtil.isEmpty(staffId))
			criteria.setRecevingStaffId(staffId);
		listViewAdapter = new GloryBroadcastViewAdapter(dispatch, criteria,
				errorHandler, sessionManager, display, win);
		listViewAdapter.addDataDisplay(cellTable);

	}

	@Override
	public void initGloryBroadcast(String staffId) {
		this.staffId=staffId;
	}

}
