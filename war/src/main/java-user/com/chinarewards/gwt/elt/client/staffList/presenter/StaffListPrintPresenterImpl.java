package com.chinarewards.gwt.elt.client.staffList.presenter;

import java.util.Comparator;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.mail.model.MailVo;
import com.chinarewards.gwt.elt.client.mail.request.MailRequest;
import com.chinarewards.gwt.elt.client.mail.request.MailResponse;
import com.chinarewards.gwt.elt.client.mvp.BaseDialogPresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.staffList.dataprovider.StaffListPrintAdapter;
import com.chinarewards.gwt.elt.client.staffList.model.StaffListClient;
import com.chinarewards.gwt.elt.client.staffList.model.StaffListCriteria;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager.TextLocation;
import com.chinarewards.gwt.elt.client.widget.GetValue;
import com.chinarewards.gwt.elt.client.widget.ListCellTable;
import com.chinarewards.gwt.elt.client.widget.Sorting;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.util.PrintOut;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class StaffListPrintPresenterImpl extends
		BaseDialogPresenter<StaffListPrintPresenter.StaffListPrintDisplay> implements
		StaffListPrintPresenter {

	private final DispatchAsync dispatch;
	private final SessionManager sessionManager;
	private final Win win;
	final ErrorHandler errorHandler;
	EltNewPager pager;
	ListCellTable<StaffListClient> cellTable;
	StaffListPrintAdapter listViewAdapter;


	@Inject
	public StaffListPrintPresenterImpl(EventBus eventBus,
			StaffListPrintDisplay display, DispatchAsync dispatch,
			SessionManager sessionManager,Win win,ErrorHandler errorHandler) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.sessionManager = sessionManager;
		this.errorHandler=errorHandler;
		this.win=win;
	
	}

	@Override
	public void bind() {
		init();
		registerHandler(display.getPrintBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						display.getResultPanel().getElement().getFirstChildElement().setAttribute("style", "text-align: center;width:100%");
						PrintOut.it(display.getResultPanel());
					}
				}));
	}
	
	private void init() {	
		buildTable();
		doSearch();
	}

	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<StaffListClient>();

		initTableColumns();
		pager = new EltNewPager(TextLocation.CENTER);
		pager.setDisplay(cellTable);
		cellTable.setWidth(ViewConstants.page_width);
//		cellTable.setPageSize(ViewConstants.per_page_number_in_dialog);
	//	cellTable.getColumn(0).setCellStyleNames("divTextLeft");
		display.getResultPanel().clear();
		display.getResultPanel().add(cellTable);

		
	}

	private void doSearch() {
		StaffListCriteria criteria = new StaffListCriteria();

		listViewAdapter = new StaffListPrintAdapter(dispatch, criteria,
				errorHandler, sessionManager,display);
		listViewAdapter.addDataDisplay(cellTable);

	}

	private void initTableColumns() {
		Sorting<StaffListClient> ref = new Sorting<StaffListClient>() {
			@Override
			public void sortingCurrentPage(Comparator<StaffListClient> comparator) {
				// listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);

			}
		};


		cellTable.addColumn("员工编号", new TextCell(),
				new GetValue<StaffListClient, String>() {
					@Override
					public String getValue(StaffListClient staff) {
						return staff.getStaffNo();
					}
				}, ref, "jobNo");
		cellTable.addColumn("姓名", new TextCell(),
				new GetValue<StaffListClient, String>() {
					@Override
					public String getValue(StaffListClient staff) {
						return staff.getStaffName();
					}
				}, ref, "name");
		cellTable.addColumn("密码", new TextCell(),
				new GetValue<StaffListClient, String>() {
					@Override
					public String getValue(StaffListClient staff) {
						return "123";
					}
				}, ref, "name");
		cellTable.addColumn("所属部门", new TextCell(),
				new GetValue<StaffListClient, String>() {
					@Override
					public String getValue(StaffListClient staff) {
						if(staff.getDepartmentName().indexOf("ROOT")==-1)
						return staff.getDepartmentName();
						else
						return "";
					}
				});
		cellTable.addColumn("职位", new TextCell(),
				new GetValue<StaffListClient, String>() {
					@Override
					public String getValue(StaffListClient staff) {
						return staff.getJobPosition();
					}
				}, ref, "jobPosition");
		cellTable.addColumn("电话", new TextCell(),
				new GetValue<StaffListClient, String>() {
					@Override
					public String getValue(StaffListClient staff) {
						return staff.getPhone();
					}
				}, ref, "phone");
		cellTable.addColumn("员工状态", new TextCell(),
				new GetValue<StaffListClient, String>() {
					@Override
					public String getValue(StaffListClient staff) {
						if(staff.getStaffStatus()!=null)
						return staff.getStaffStatus().getDisplayName();
						else
						return "未知";
					}
				});

	
	}
	public void sendMail(String emailAddress,String staffId)
	   {
		   MailVo mailvo = new MailVo();
		   mailvo.setStaffId(staffId);
		   mailvo.setContent("你的ELT账号是"+emailAddress.substring(0,emailAddress.indexOf("@"))+"初始密码是123");
		   MailRequest request = new MailRequest(mailvo,sessionManager.getSession());
		   dispatch.execute(request,new AsyncCallback<MailResponse>() {

						@Override
						public void onFailure(Throwable t) {
							win.alert(t.getMessage());
						}
	    				@Override
						public void onSuccess(MailResponse resp) {
							win.alert(resp.getToken());
							
						}
					});
	   }

}
