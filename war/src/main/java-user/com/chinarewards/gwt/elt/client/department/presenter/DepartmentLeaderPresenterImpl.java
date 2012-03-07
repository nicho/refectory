package com.chinarewards.gwt.elt.client.department.presenter;

import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.department.dataprovider.DepartmentListViewAdapter;
import com.chinarewards.gwt.elt.client.department.model.DepartmentClient;
import com.chinarewards.gwt.elt.client.department.model.DepartmentNode;
import com.chinarewards.gwt.elt.client.department.plugin.DepartmentConstants;
import com.chinarewards.gwt.elt.client.department.plugin.DepartmentLeaderConstants;
import com.chinarewards.gwt.elt.client.department.presenter.DepartmentLeaderPresenter.DepartmentLeaderDisplay;
import com.chinarewards.gwt.elt.client.department.request.DeleteDepartmentRequest;
import com.chinarewards.gwt.elt.client.department.request.DeleteDepartmentResponse;
import com.chinarewards.gwt.elt.client.department.request.DepartmentLeaderRequest;
import com.chinarewards.gwt.elt.client.department.request.DepartmentLeaderResponse;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager;
import com.chinarewards.gwt.elt.client.widget.ListCellTable;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class DepartmentLeaderPresenterImpl extends
		BasePresenter<DepartmentLeaderDisplay> implements
		DepartmentLeaderPresenter {

	String departmentIds = "";

	final DispatchAsync dispatcher;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;

	EltNewPager pager;
	ListCellTable<DepartmentClient> cellTable;
	DepartmentListViewAdapter listViewAdapter;

	private final BreadCrumbsPresenter breadCrumbs;

	@Inject
	public DepartmentLeaderPresenterImpl(EventBus eventBus,
			DispatchAsync dispatcher, ErrorHandler errorHandler,
			SessionManager sessionManager, DepartmentLeaderDisplay display,
			Win win, BreadCrumbsPresenter breadCrumbs) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
		this.breadCrumbs = breadCrumbs;
	}

	@Override
	public void bind() {
		breadCrumbs.loadListPage();
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());

		registerEvent();

		initTreeTable();

	}

	private void initTreeTable() {
		final String corporationId = sessionManager.getSession()
				.getCorporationId();
		
		final String leaderId = sessionManager.getSession().getStaffId();
		dispatcher.execute(new DepartmentLeaderRequest(leaderId,corporationId),new AsyncCallback<DepartmentLeaderResponse>() {
					@Override
					public void onFailure(Throwable e) {
						win.alert(e.getMessage()+"<br>"+e.getLocalizedMessage()+"<br>"+e);
					}

					@Override
					public void onSuccess(DepartmentLeaderResponse response) {
						// display.setBudgetIntegral((int)response.getBudgetIntegral()+"");
						// display.setUseIntegeral((int)response.getBudgetIntegral()+"");
						List<DepartmentNode> nodeList = response.getResult();
						if(nodeList!=null){
//							System.out.println("=================DepartmentLeaderPresentImpl:"+nodeList.size());
						}
						
						display.loadTreeData(nodeList, corporationId);
					}
				});
	}

	private String getDepartmentIds() {
		departmentIds = display.getCurrentDepartmentId().getValue();

		return departmentIds;
	}

	private void registerEvent() {
		// 增加子部门
		registerHandler(display.getAddChildBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						String departmentIds = getDepartmentIds();
						// win.alert("功能建设中");
						if (departmentIds != null&&"".equals(departmentIds)==false) {
							String[] ids = StringUtil.getSplitString(
									departmentIds, ",");
							if (ids != null) {
								if (ids.length > 1) {
									win.alert("只能选择一部门");
								} else {
									departmentIds = departmentIds.replace(",",
											"");

									DepartmentClient client = new DepartmentClient();
									client.setId(departmentIds);
									client.setThisAction(DepartmentConstants.ACTION_DEPARTMENT_ADD_CHILD);

									Platform.getInstance()
											.getEditorRegistry()
											.openEditor(
													DepartmentConstants.EDITOR_DEPARTMENT_EDIT,
													DepartmentConstants.ACTION_DEPARTMENT_ADD_CHILD,
													client);
								}
							} else {
								win.alert("请至少选择一个部门");
							}
						} else {
							win.alert("请至少选择一个部门");
						}
					}
				}));
		// 删除部门
		registerHandler(display.getDeleteBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						// win.alert("功能建设中");
						String departmentIds = getDepartmentIds();
						if (departmentIds != null&&"".equals(departmentIds)==false) {
							String[] ids = StringUtil.getSplitString(
									departmentIds, ",");
							if (ids != null) {
								if (ids.length > 1) {
									win.alert("只能选择一个需要删除的部门");
								} else {
									departmentIds = departmentIds.replace(",",
											"");
									delteDepartment(departmentIds);
								}
							} else {
								win.alert("请至少选择一个需要删除的部门");
							}
						} else {
							win.alert("请至少选择一个需要删除的部门");
						}
					}
				}));
		// 编辑部门
		registerHandler(display.getEditBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						// win.alert("功能建设中");
						String departmentIds = getDepartmentIds();
						if (departmentIds != null&&"".equals(departmentIds)==false) {
							String[] ids = StringUtil.getSplitString(
									departmentIds, ",");
							if (ids != null) {
								if (ids.length > 1) {
									win.alert("只能选择一个需要编辑的部门");
								} else {
									departmentIds = departmentIds.replace(",",
											"");
									DepartmentClient client = new DepartmentClient();
									client.setId(departmentIds);
									client.setThisAction(DepartmentConstants.ACTION_DEPARTMENT_EDIT_DEPT);
									openEditPage(client);
								}
							} else {
								win.alert("请至少选择一个需要编辑的部门");
							}
						} else {
							win.alert("请至少选择一个需要编辑的部门");
						}
					}
				}));

	}

	private void delteDepartment(String departmentId) {
		dispatcher.execute(new DeleteDepartmentRequest(departmentId,
				sessionManager.getSession().getToken()),
				new AsyncCallback<DeleteDepartmentResponse>() {

					@Override
					public void onFailure(Throwable t) {
						win.alert(t.getMessage());
					}

					@Override
					public void onSuccess(DeleteDepartmentResponse resp) {
						win.alert("删除成功");
						openDepartmentLeaderPage();
					}
				});
	}

	private void openDepartmentLeaderPage() {
		Platform.getInstance()
				.getEditorRegistry()
				.openEditor(
						DepartmentLeaderConstants.EDITOR_DEPARTMENTLEADER_SEARCH,
						"DepartmentLeaderConstants.EDITOR_DEPARTMENTLEADER_SEARCH",
						null);
	}

	private void openEditPage(DepartmentClient client) {
		Platform.getInstance()
				.getEditorRegistry()
				.openEditor(DepartmentConstants.EDITOR_DEPARTMENT_EDIT,
						"EDITOR_DEPARTMENT_EDIT", client);
	}

	@Override
	public void initEditor() {
	}
}
