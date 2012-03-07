package com.chinarewards.gwt.elt.client.staff.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.staff.model.StaffVo;
import com.chinarewards.gwt.elt.client.staff.request.HrRegisterRequest;
import com.chinarewards.gwt.elt.client.staff.request.HrRegisterResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.user.plugin.UserConstants;
import com.chinarewards.gwt.elt.client.util.StringUtil;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.model.user.UserRoleVo;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class HrRegisterPresenterImpl extends
		BasePresenter<HrRegisterPresenter.HrRegisterDisplay> implements
		HrRegisterPresenter {

	private final DispatchAsync dispatcher;
	private final SessionManager sessionManager;
	private final Win win;
	//private  String instanceId;

	@Inject
	public HrRegisterPresenterImpl(EventBus eventBus,
			HrRegisterDisplay display, DispatchAsync dispatcher,
			SessionManager sessionManager,Win win) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.sessionManager = sessionManager;
		this.win=win;
	}

	@Override
	public void bind() {
		registerHandler(display.getHrRegisterClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						if (StringUtil.isEmpty(display.getEmail().getValue())) {
							win.alert("电子邮件不能为空!<br>");
							return;
						}
						if (StringUtil.isEmpty(display.getName().getValue())) {
							win.alert("名字不能为空!<br>");
							return;
						}
						if (StringUtil.isEmpty(display.getPassword().getValue())) {
							win.alert("密码不能为空!<br>");
							return;
						}
						if (StringUtil.isEmpty(display.getUsername().getValue())) {
							win.alert("用户名不能为空!<br>");
							return;
						}
						if (!display.getPassword().getValue().equals(display.getValidatePassword().getValue())) {
							win.alert("密码和确认密码不一致!<br>");
							return;
						}
						if(display.isCheckAdmin()==false && display.isCheckGift()==false && display.isCheckStaff()==false)
						{
							win.alert("请选择角色");
							return;
						}
						doHrRegister();
					}
				}));
	}

	protected void doHrRegister() {

		StaffVo vo = new StaffVo();
		vo.setName(display.getName().getValue());
		vo.setEmail(display.getEmail().getValue());
		vo.setTell(display.getTell().getValue());
		vo.setPassword(display.getPassword().getValue());
		vo.setUsername(display.getUsername().getValue());
		vo.setCreateUserId(sessionManager.getSession().getToken());
		vo.setDeptId(sessionManager.getSession().getDepartmentId());
		List<UserRoleVo> userRoleVos=new ArrayList<UserRoleVo>();
		if(display.isCheckAdmin()==true)
		{
			userRoleVos.add(UserRoleVo.CORP_ADMIN);
		}
		if(display.isCheckGift()==true)
		{
			userRoleVos.add(UserRoleVo.GIFT);
		}
		if(display.isCheckStaff()==true)
		{
			userRoleVos.add(UserRoleVo.STAFF);
		}
		if(display.isCheckDeptMgr()==true)
		{
			userRoleVos.add(UserRoleVo.DEPT_MGR);
		}
		vo.setUserRoleVos(userRoleVos);
		dispatcher.execute(new HrRegisterRequest(vo),
				new AsyncCallback<HrRegisterResponse>() {
					public void onFailure(Throwable t) {
						win.alert(t.getMessage());
					}

					@Override
					public void onSuccess(HrRegisterResponse response) {

						win.alert("添加成功!");
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(UserConstants.EDITOR_USER_SEARCH,
								"EDITOR_USER_SEARCH_DO_ID", null);}
				});
	}

	@Override
	public void initRegister(String instanceId) {
	//	this.instanceId=instanceId;
		
	}

}
