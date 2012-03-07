package com.chinarewards.gwt.elt.client.department.presenter;

import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.ui.DialogCloseListener;
import com.chinarewards.gwt.elt.client.department.dataprovider.DepartmentListViewAdapter;
import com.chinarewards.gwt.elt.client.department.model.DepartmentClient;
import com.chinarewards.gwt.elt.client.department.model.DepartmentNode;
import com.chinarewards.gwt.elt.client.department.plugin.DepartmentConstants;
import com.chinarewards.gwt.elt.client.department.plugin.DepartmentListConstants;
import com.chinarewards.gwt.elt.client.department.presenter.DepartmentListPresenter.DepartmentListDisplay;
import com.chinarewards.gwt.elt.client.department.request.DeleteDepartmentRequest;
import com.chinarewards.gwt.elt.client.department.request.DeleteDepartmentResponse;
import com.chinarewards.gwt.elt.client.department.request.DepartmentManageRequest;
import com.chinarewards.gwt.elt.client.department.request.DepartmentManageResponse;
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
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class DepartmentListPresenterImpl extends
		BasePresenter<DepartmentListDisplay> implements DepartmentListPresenter {

	String departmentIds = "";

	final DispatchAsync dispatcher;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;

	EltNewPager pager;
	ListCellTable<DepartmentClient> cellTable;
	DepartmentListViewAdapter listViewAdapter;

	private final Provider<MergeDepartmentWinDialog> mergeDialogProvider;

	private final BreadCrumbsPresenter breadCrumbs;

	@Inject
	public DepartmentListPresenterImpl(EventBus eventBus,
			DispatchAsync dispatcher, ErrorHandler errorHandler,
			SessionManager sessionManager, DepartmentListDisplay display,
			Win win, BreadCrumbsPresenter breadCrumbs,
			Provider<MergeDepartmentWinDialog> mergeDialogProvider) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
		this.breadCrumbs = breadCrumbs;
		this.mergeDialogProvider = mergeDialogProvider;
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
		dispatcher.execute(new DepartmentManageRequest(corporationId),
				new AsyncCallback<DepartmentManageResponse>() {
					@Override
					public void onFailure(Throwable e) {
						win.alert(e.getMessage());
					}

					@Override
					public void onSuccess(DepartmentManageResponse response) {
						// display.setBudgetIntegral((int)response.getBudgetIntegral()+"");
						// display.setUseIntegeral((int)response.getBudgetIntegral()+"");
						List<DepartmentNode> nodeList = response.getResult();
						display.loadTreeData(nodeList, corporationId);
					}
				});
	}

	private String getDepartmentIds() {
		departmentIds = display.getCurrentDepartmentId().getValue();

		return departmentIds;
	}

	private void registerEvent() {
		// 增加同级部门
		registerHandler(display.getAddSameLevelBtnClickHandlers()
				.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						String departmentIds = getDepartmentIds();
						if (departmentIds != null) {
							String[] ids = StringUtil.getSplitString(
									departmentIds, ",");
							if (ids != null) {
								if (ids.length > 1) {
									win.alert("只能选择一部门");
								} else {
									departmentIds = departmentIds.replace(",",
											"");
									// win.alert(departmentIds+"=="+ids);
									DepartmentClient client = new DepartmentClient();
									client.setId(departmentIds);
									client.setThisAction(DepartmentConstants.ACTION_DEPARTMENT_ADD_SAMELEVEL);

									Platform.getInstance()
											.getEditorRegistry()
											.openEditor(
													DepartmentConstants.EDITOR_DEPARTMENT_EDIT,
													DepartmentConstants.ACTION_DEPARTMENT_ADD_SAMELEVEL,
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

		// 增加子部门
		registerHandler(display.getAddChildBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						String departmentIds = getDepartmentIds();
						// win.alert("功能建设中");
						if (departmentIds != null
								&& "".equals(departmentIds) == false) {
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
						if (departmentIds != null
								&& "".equals(departmentIds) == false) {
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
						if (departmentIds != null
								&& "".equals(departmentIds) == false) {
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
									client.setThisAction(DepartmentConstants.ACTION_DEPARTMENT_EDIT_CORP);
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
		// 合并部门
		registerHandler(display.getMergeBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						// win.alert("功能建设中");
						String departmentIds = getDepartmentIds();
						if (departmentIds != null
								&& "".equals(departmentIds) == false) {
							String[] ids = StringUtil.getSplitString(
									departmentIds, ",");
							if (ids != null) {
								if (ids.length < 2) {
									win.alert("请至少选择两个需要合并的部门");
								} else {
									departmentIds = departmentIds.replace(",",
											"");

									openMergeWinDialog(departmentIds);
								}
							} else {
								win.alert("请选择需要合并的部门");
							}
						} else {
							win.alert("请选择需要合并的部门");
						}
					}
				}));

		// 同步公司组织机构
		registerHandler(display.getSynchBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						// win.alert("功能建设中");
						// win.alert("currentDepartmentId()----" +
						// departmentIds);
						Element element = display.getCellTree().getElement();
						win.alert(element.getInnerHTML());
//						win.alert(element.get);
//						element.get
						
						System.out.println(element.getChildNodes());
//						element.
//						display.loadTreeData(nodeList, corporationId);
					}
				}));

	}

	private void openMergeWinDialog(String departmentIds) {

		final MergeDepartmentWinDialog dialog = mergeDialogProvider.get();
		dialog.initDialog(departmentIds);
		//
		
		final HandlerRegistration registration=registerMergeEvent();
		
		Platform.getInstance().getSiteManager()
				.openDialog(dialog, new DialogCloseListener() {
					public void onClose(String dialogId, String instanceId) {
						registration.removeHandler();
					}
				});
	}
	
	//合并部门窗口处理事件
	private HandlerRegistration registerMergeEvent(){
		final HandlerRegistration registration = eventBus.addHandler(
				MergeDepartmentEvent.getType(), new MergeDepartmentHandler() {
					@Override
					public void mergeDepartment(String departmentIds,
							String departmentName, String leaderId) {
						win.alert(" mergeDepartment ===="+departmentIds);
						
						
						
						
					}
				});
		return registration;
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
						openDepartmentManagePage();
					}
				});
	}

	private void openDepartmentManagePage() {
		Platform.getInstance()
				.getEditorRegistry()
				.openEditor(
						DepartmentListConstants.EDITOR_DEPARTMENTLIST_SEARCH,
						"DepartmentListConstants.EDITOR_DEPARTMENTLIST_SEARCH",
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
