package com.chinarewards.gwt.elt.client.chooseOrganization.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.chooseOrganization.dataprovider.OrganizationChooseAsyncDataProvider;
import com.chinarewards.gwt.elt.client.chooseOrganization.event.ChooseOrganizationEvent;
import com.chinarewards.gwt.elt.client.chooseOrganization.model.OrganSearchCriteria;
import com.chinarewards.gwt.elt.client.chooseOrganization.model.OrganSearchCriteria.OrganType;
import com.chinarewards.gwt.elt.client.chooseOrganization.presenter.ChooseOrganizationListPresenter.ChooseOrganizationListDisplay;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.mvp.BaseDialogPresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager.TextLocation;
import com.chinarewards.gwt.elt.client.widget.GetValue;
import com.chinarewards.gwt.elt.client.widget.ListCellTable;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;

public class ChooseOrganizationListPresenterImpl extends
		BaseDialogPresenter<ChooseOrganizationListDisplay> implements
		ChooseOrganizationListPresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;

	String rewardId;
	final EltNewPager simplePager = new EltNewPager(TextLocation.CENTER);
	ListCellTable<OrganicationClient> resultTable;
	OrganizationChooseAsyncDataProvider listViewAdapter;

	// 为StaffAsyncDataProvider准备的数据。
	boolean isChooseAll = false;
	/**
	 * 候选人(员工或部门)ids,得奖的人需要从中选择
	 */
	List<String> orgIds;

	boolean isLimitByNominee = false;
	// set this presenter search staff only.
	// use for department edit presenter.
	boolean staffOnly = false;

	@Inject
	public ChooseOrganizationListPresenterImpl(EventBus eventBus,
			ChooseOrganizationListDisplay display, DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;

	}

	public Widget asWidget() {
		return display.asWidget();
	}

	private void init() {
		buildTable();
		doSearch(OrganType.STAFF);
	}

	public void bind() {
		init();

		/*** 查询员工 ***/
		registerHandler(display.getSearchBtn().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						buildTable();
						if(display.getRadioDept().getValue())
							doSearch(OrganType.DEPT);
						else if(display.getRadioGroup().getValue())
							doSearch(OrganType.GROUP);
						else if(display.getRadioOrg().getValue())
							doSearch(OrganType.ORG);
						else if(display.getRadioStaff().getValue())
							doSearch(OrganType.STAFF);
					}
				}));

		/*** 重置查询信息 ***/
		registerHandler(display.getResetBtn().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						display.reset();
					}
				}));
		// add
		registerHandler(display.getChooseBtn().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						//
						eventBus.fireEvent(new ChooseOrganizationEvent(getRealStaffs()));
						closeDialog();
					}
				}));
		registerHandler(display.getCancelBtn().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						closeDialog();
					}
				}));
		registerHandler(display.getRadioStaff().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						buildTable();
						doSearch(OrganType.STAFF);
					}
				}));
		registerHandler(display.getRadioGroup().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						buildTable();
						doSearch(OrganType.GROUP);
					}
				}));
		registerHandler(display.getRadioOrg().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						buildTable();
						doSearch(OrganType.ORG);
					}
				}));
		registerHandler(display.getRadioDept().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						buildTable();
						doSearch(OrganType.DEPT);
					}
				}));
	}

	private void buildTable() {

		resultTable = new ListCellTable<OrganicationClient>();
		final SingleSelectionModel<OrganicationClient> selectionModel = setSelectionModel(resultTable);
		initTableColumns(selectionModel);

		simplePager.setDisplay(resultTable);
		resultTable.setWidth(ViewConstants.page_width);
		resultTable.setPageSize(ViewConstants.per_page_number_in_dialog);
		resultTable.setPageStart(0);
		display.getResultPanel().clear();
		display.getResultPanel().add(resultTable);
		display.getResultpage().add(simplePager);
	}

	private SingleSelectionModel<OrganicationClient> setSelectionModel(
			CellTable<OrganicationClient> cellTable) {
		final SingleSelectionModel<OrganicationClient> selectionModel = new SingleSelectionModel<OrganicationClient>();

		SelectionChangeEvent.Handler selectionHandler = new SelectionChangeEvent.Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				OrganicationClient staff = selectionModel.getSelectedObject();
				if (!display.getSpecialTextBox().containsItem(staff)) {
					display.getSpecialTextBox().addItem(staff);
				}
			}

		};
		selectionModel.addSelectionChangeHandler(selectionHandler);
		// cellTable.setSelectionEnabled(true); // FIXME make sure it works
		cellTable.setSelectionModel(selectionModel);

		return selectionModel;
	}

	/**
	 * 初始化table
	 * 
	 * @param selectionModel
	 */
	private void initTableColumns(
			final SelectionModel<OrganicationClient> selectionModel) {

//		Sorting<OrganicationClient> ref = new Sorting<OrganicationClient>() {
//			@Override
//			public void sortingCurrentPage(Comparator<OrganicationClient> comparator) {
//				//listViewAdapter.sortCurrentPage(comparator);
//			}
//
//			@Override
//			public void sortingAll(String sorting, String direction) {
//				listViewAdapter.sortFromDateBase(sorting, direction);
//			}
//		};

		resultTable.addColumn("名称", new TextCell(),
				new GetValue<OrganicationClient, String>() {
					@Override
					public String getValue(OrganicationClient staff) {
						return staff.getName();
					}
				});


	}

	/**
	 * returns value will only get ID and name.
	 * 
	 * @return
	 */
	private List<OrganicationClient> getRealStaffs() {
		List<OrganicationClient> result = new ArrayList<OrganicationClient>();
		for (OrganicationClient organ : display.getSpecialTextBox().getItemList()) {
			result.add(organ);
		}
		return result;
	}

	/**
	 * 查询员工
	 */
	private void doSearch(OrganType organType) {
		OrganSearchCriteria criteriaVo = new OrganSearchCriteria();
		 criteriaVo.setKey(display.getName().getValue());
		 criteriaVo.setOrganType(organType);


		resultTable.setPageStart(0);
		resultTable.setRowCount(0, false);
		listViewAdapter = new OrganizationChooseAsyncDataProvider(dispatch,
				errorHandler, sessionManager, criteriaVo, false);
		listViewAdapter.addDataDisplay(resultTable);
	}





}
