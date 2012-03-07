package com.chinarewards.gwt.elt.client.department.presenter;

import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.ui.DialogCloseListener;
import com.chinarewards.gwt.elt.client.department.model.DepartmentClient;
import com.chinarewards.gwt.elt.client.department.model.DepartmentVo;
import com.chinarewards.gwt.elt.client.department.plugin.DepartmentConstants;
import com.chinarewards.gwt.elt.client.department.plugin.DepartmentListConstants;
import com.chinarewards.gwt.elt.client.department.request.EditDepartmentRequest;
import com.chinarewards.gwt.elt.client.department.request.EditDepartmentResponse;
import com.chinarewards.gwt.elt.client.department.request.SearchDepartmentByIdRequest;
import com.chinarewards.gwt.elt.client.department.request.SearchDepartmentByIdResponse;
import com.chinarewards.gwt.elt.client.department.util.DepartmentAdapterClient;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.client.win.confirm.ConfirmHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class DepartmentPresenterImpl extends
		BasePresenter<DepartmentPresenter.DepartmentDisplay> implements
		DepartmentPresenter {
	String instanceId;// 修改时传过来的ID

	private String thisAction;
	private String departmentId;
	//
	private final DispatchAsync dispatcher;
	private final ErrorHandler errorHandler;
	private final SessionManager sessionManager;

	private final Provider<ChooseLeaderWinDialog> chooseLeaderDialogProvider;

	private final Win win;

	private final BreadCrumbsPresenter breadCrumbs;

	@Inject
	public DepartmentPresenterImpl(EventBus eventBus,
			DepartmentDisplay display, DispatchAsync dispatcher,
			ErrorHandler errorHandler, SessionManager sessionManager, Win win,
			BreadCrumbsPresenter breadCrumbs,
			Provider<ChooseLeaderWinDialog> chooseLeaderDialogProvider) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
		this.breadCrumbs = breadCrumbs;
		this.chooseLeaderDialogProvider = chooseLeaderDialogProvider;
	}

	@Override
	public void bind() {
		registerEvent();

		if (DepartmentConstants.ACTION_DEPARTMENT_ADD.equals(thisAction)) {
			breadCrumbs.loadChildPage("新建部门");
			initSave();
		} else if (DepartmentConstants.ACTION_DEPARTMENT_ADD_SAMELEVEL
				.equals(thisAction)) {
			breadCrumbs.loadChildPage("新建同级部门");
			initSaveSameLevel();
		} else if (DepartmentConstants.ACTION_DEPARTMENT_ADD_CHILD
				.equals(thisAction)) {
			breadCrumbs.loadChildPage("新建子部门");
			initSaveChild();
		} else if (DepartmentConstants.ACTION_DEPARTMENT_EDIT_CORP
				.equals(thisAction)) {
			initEdit();
			breadCrumbs.loadChildPage("编辑部门");
		} else if (DepartmentConstants.ACTION_DEPARTMENT_EDIT_DEPT
				.equals(thisAction)) {
			initEdit();
			breadCrumbs.loadChildPage("编辑部门");
		} else {
			win.alert("未定义的方法");
		}

		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
	}

	// 绑定事件
	private void registerEvent() {
		// 保存事件
		registerHandler(display.getSaveClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						if (!validateSubmit()) {
							return;
						}

						DepartmentVo departmentVo = DepartmentAdapterClient
								.adapterDisplay(display);
						departmentVo.setThisAction(thisAction);

						if (DepartmentConstants.ACTION_DEPARTMENT_ADD
								.equals(thisAction)) {
							departmentVo.setId(null);
							doSave(departmentVo);
						} else if (DepartmentConstants.ACTION_DEPARTMENT_ADD_SAMELEVEL
								.equals(thisAction)) {
							departmentVo.setId(null);
							doSave(departmentVo);
						} else if (DepartmentConstants.ACTION_DEPARTMENT_ADD_CHILD
								.equals(thisAction)) {
							departmentVo.setId(null);
							doSave(departmentVo);
						} else if (DepartmentConstants.ACTION_DEPARTMENT_EDIT_CORP
								.equals(thisAction)) {
							departmentVo.setId(departmentId);
							doEdit(departmentVo);
						} else if (DepartmentConstants.ACTION_DEPARTMENT_EDIT_DEPT
								.equals(thisAction)) {
							departmentVo.setId(departmentId);
							doEdit(departmentVo);
						} else {
							win.alert("未定义的方法");
						}
					}

					private void doSave(DepartmentVo departmentVo) {
						dispatcher.execute(new EditDepartmentRequest(
								departmentVo, sessionManager.getSession()),
								new AsyncCallback<EditDepartmentResponse>() {
									@Override
									public void onFailure(Throwable t) {
										errorHandler.alert(t.toString());
									}

									@Override
									public void onSuccess(
											EditDepartmentResponse response) {
										win.alert("添加成功");
										// if(instanceId!=null||!instanceId.equals(""))
										openDepartmentManagePage();
									}
								});
					}

					private void doEdit(final DepartmentVo department) {

						win.confirm("确认", "确定修改？", new ConfirmHandler() {
							@Override
							public void confirm() {
								dispatcher.execute(
										new EditDepartmentRequest(department,sessionManager.getSession()),
										new AsyncCallback<EditDepartmentResponse>() {
											@Override
											public void onFailure(Throwable t) {
												win.alert("修改失败");
												closeEditPage();
											}

											@Override
											public void onSuccess(
													EditDepartmentResponse arg0) {
												win.alert("修改成功");
												openDepartmentManagePage();
											}
										});
							}
						});	
					}
				}));

		registerHandler(display.getBackClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						openDepartmentManagePage();
					}
				}));

		registerChooseLeader();

	}

	// 选择Leader
	private void registerChooseLeader() {
		registerHandler(display.getChooseLeaderBtnClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						final HandlerRegistration registration = eventBus
								.addHandler(ChooseLeaderEvent.getType(),
										new ChooseLeaderHandler() {
											@Override
											public void chosenLeader(
													List<StaffClient> list) {
												for (StaffClient item : list) {
													if (display.getLeaderArea() != null
															&& item != null) {
														if (!display
																.getLeaderArea()
																.containsItem(
																		item)) {
															// display.getLeaderArea().clear();
															display.getLeaderArea()
																	.addItem(
																			item);
														}
													}

												}
											}
										});
						//
						final ChooseLeaderWinDialog dialog = chooseLeaderDialogProvider
								.get();
						dialog.setNominee(false, true, null);
						//
						Platform.getInstance().getSiteManager()
								.openDialog(dialog, new DialogCloseListener() {
									public void onClose(String dialogId,
											String instanceId) {
										registration.removeHandler();
									}
								});
					}
				}));
	}

	// 验证方法
	private boolean validateSubmit() {
		boolean flag = true;
		StringBuilder errorMsg = new StringBuilder();
		// if (display.getName().getValue() == null
		// || "".equals(display.getName().getValue().trim())) {
		// errorMsg.append("请填写部门名称!<br>");
		// flag = false;
		// }

		if (!flag) {
			win.alert(errorMsg.toString());
		}

		return flag;
	}

	private void initEdit() {
		if (departmentId != null) {
			dispatcher.execute(new SearchDepartmentByIdRequest(departmentId),
					new AsyncCallback<SearchDepartmentByIdResponse>() {
						@Override
						public void onFailure(Throwable arg0) {
							errorHandler.alert("查询出错!");
							closeEditPage();
						}

						@Override
						public void onSuccess(
								SearchDepartmentByIdResponse response) {
							DepartmentVo departmentVo = response
									.getDepartment();
							departmentVo.setThisAction(thisAction);
							clear();
							display.initEditDepartment(departmentVo);
						}
					});
		} else {
			System.err.println("------------缺少departmentId----------");
		}
	}

	private void initSave() {
		display.initAddDepartment(new DepartmentVo());
	}

	private void initSaveSameLevel() {
		if (departmentId != null) {
			dispatcher.execute(new SearchDepartmentByIdRequest(departmentId),
					new AsyncCallback<SearchDepartmentByIdResponse>() {
						@Override
						public void onFailure(Throwable arg0) {
							errorHandler.alert("查询结果异常!");
							openDepartmentManagePage();
						}

						@Override
						public void onSuccess(
								SearchDepartmentByIdResponse response) {
							DepartmentVo departmentVo = response
									.getDepartment();
							clear();
							display.initSaveSameLevelDepartment(departmentVo);
						}
					});
		} else {
			System.err.println("------------缺少departmentId----------");
		}
	}

	private void initSaveChild() {
		if (departmentId != null) {
			dispatcher.execute(new SearchDepartmentByIdRequest(departmentId),
					new AsyncCallback<SearchDepartmentByIdResponse>() {
						@Override
						public void onFailure(Throwable arg0) {
							errorHandler.alert("查询结果异常!");
							openDepartmentManagePage();
						}

						@Override
						public void onSuccess(
								SearchDepartmentByIdResponse response) {
							DepartmentVo departmentVo = response
									.getDepartment();
							clear();
							display.initSaveChildDepartment(departmentVo);
						}
					});
		} else {
			System.err.println("------------缺少departmentId----------");
		}
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

	private void closeEditPage() {
		Platform.getInstance()
				.getEditorRegistry()
				.closeEditor(DepartmentConstants.EDITOR_DEPARTMENT_EDIT,
						instanceId);
	}

	private void clear() {
		display.clear();
	}

	public void setId(String id) {
		this.departmentId = id;
	}

	@Override
	public void initEditor(String departmentId, String thisAction) {
		this.departmentId = departmentId;
		this.thisAction = thisAction;
	}

}
