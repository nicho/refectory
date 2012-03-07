package com.chinarewards.gwt.elt.client.team.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.ui.DialogCloseListener;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewardItem.dialog.ChooseStaffWinDialog;
import com.chinarewards.gwt.elt.client.rewardItem.event.ChooseStaffEvent;
import com.chinarewards.gwt.elt.client.rewardItem.handler.ChooseStaffHandler;
import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient;
import com.chinarewards.gwt.elt.client.rewards.model.ParticipateInfoClient.SomeoneClient;
import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.team.model.TeamSearchVo;
import com.chinarewards.gwt.elt.client.team.model.TeamVo;
import com.chinarewards.gwt.elt.client.team.plugin.TeamConstants;
import com.chinarewards.gwt.elt.client.team.request.SearchTeamByIdRequest;
import com.chinarewards.gwt.elt.client.team.request.SearchTeamByIdResponse;
import com.chinarewards.gwt.elt.client.team.request.TeamAddRequest;
import com.chinarewards.gwt.elt.client.team.request.TeamAddResponse;
import com.chinarewards.gwt.elt.client.win.Win;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class TeamAddPresenterImpl extends BasePresenter<TeamAddPresenter.TeamAddDisplay>
		implements TeamAddPresenter {
	String instanceId;// 修改时传过来操作型号参数
	private String teamId;
	private final DispatchAsync dispatcher;
	private final ErrorHandler errorHandler;
	private final SessionManager sessionManager;
	private final Win win;
	private final BreadCrumbsPresenter breadCrumbs;
	private final Provider<ChooseStaffWinDialog> chooseStaffDialogProvider;
	@Inject
	public TeamAddPresenterImpl(EventBus eventBus, TeamAddDisplay display,
			DispatchAsync dispatcher, ErrorHandler errorHandler,
			SessionManager sessionManager, Win win,Provider<ChooseStaffWinDialog> chooseStaffDialogProvider,
			BreadCrumbsPresenter breadCrumbs) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
		this.breadCrumbs = breadCrumbs;
		this.chooseStaffDialogProvider= chooseStaffDialogProvider;
	}

	@Override
	public void bind() {
		// 绑定事件
		init();
		
		if(instanceId.equals(TeamConstants.ACTION_TEAM_ADD))
		{
			breadCrumbs.loadChildPage("新建小组");
			
		} else if(instanceId.equals(TeamConstants.ACTION_TEAM_EDIT)) {
			initEdit();
			breadCrumbs.loadChildPage("编辑小组");
		} else{
			initEdit();
			breadCrumbs.loadChildPage("查看小组");	
		}

		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
	}

	private void init() {
		// 保存事件
		registerHandler(display.getSaveClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						if (!validateSubmit()) {
							return;
						}
						
     					 TeamVo teamVo=getTeamVo();//得到界面的数据
						
						if(instanceId.equals(TeamConstants.ACTION_TEAM_ADD)) {
							teamVo.setId(null);
							doSave(teamVo);
						} if(instanceId.equals(TeamConstants.ACTION_TEAM_EDIT)) {
							teamVo.setId(teamId);
							doSave(teamVo);
						} 
					}
                   
			private void doSave(TeamVo teamVo) {
					dispatcher.execute(new TeamAddRequest(teamVo,sessionManager.getSession()),
							new AsyncCallback<TeamAddResponse>() {
								@Override
								public void onFailure(Throwable t) {
									errorHandler.alert(t.toString());
								}

								@Override
								public void onSuccess(
										TeamAddResponse response) {
									win.alert("操作成功");
									// if(instanceId!=null||!instanceId.equals(""))
									Platform.getInstance()
											.getEditorRegistry()
											.openEditor(
											TeamConstants.EDITOR_TEAMLIST_SEARCH,
											TeamConstants.ACTION_TEAM_LIST,	instanceId);
							}
						});
				}

					
		}));

		//负责人按钮事件
		registerHandler(display.getChooseStaffBtnClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						final ChooseStaffWinDialog dialog = chooseStaffDialogProvider.get();
						dialog.setNominee(false, true, null);
						final HandlerRegistration registration = eventBus.addHandler(ChooseStaffEvent.getType(),new ChooseStaffHandler() {
											@Override
											public void chosenStaff(List<StaffClient> list) {
												for (StaffClient r : list) {
													
													if (!display.getSpecialTextArea().containsItem(r)) {
														
														display.getSpecialTextArea().addItem(r);
													}
												}
											}
										});

						       Platform.getInstance().getSiteManager()
								.openDialog(dialog, new DialogCloseListener() {
									public void onClose(String dialogId,String instanceId) {
										registration.removeHandler();
									}
								});
					}
				}));
		        //成员选人按钮事件
				registerHandler(display.getChooseStaffBtnsClick().addClickHandler(
						new ClickHandler() {
							@Override
							public void onClick(ClickEvent arg0) {
								final ChooseStaffWinDialog dialog = chooseStaffDialogProvider.get();
								dialog.setNominee(false, true, null);
								final HandlerRegistration registration = eventBus.addHandler(ChooseStaffEvent.getType(),new ChooseStaffHandler() {
													@Override
													public void chosenStaff(List<StaffClient> list) {
														for (StaffClient r : list) {
															
															if (!display.getSpecialTextAreas().containsItem(r)) {
																
																display.getSpecialTextAreas().addItem(r);
															}
														}
													}
												});

								       Platform.getInstance().getSiteManager()
										.openDialog(dialog, new DialogCloseListener() {
											public void onClose(String dialogId,String instanceId) {
												registration.removeHandler();
											}
										});
							}
						}));

		registerHandler(display.getBackClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										TeamConstants.EDITOR_TEAMLIST_SEARCH,
										TeamConstants.ACTION_TEAM_LIST,	instanceId);
					}
				}));		
	
	}
	private TeamVo getTeamVo(){
		 TeamVo teamVo =new TeamVo();
		 teamVo.setName(display.getName().getValue().trim());
	     teamVo.setDescription(display.getExplains().getValue());
	     teamVo.setFzInfo(getManagerInfo());
	     teamVo.setMemberInfo(getMembersInfo());
	     return teamVo;
	}
	public ParticipateInfoClient getManagerInfo(){
		ParticipateInfoClient managerInfo = null;
		List<OrganicationClient> orgs = new ArrayList<OrganicationClient>();
	     
			for (String orgId : display.getManagerIds()) {
				 orgs.add(new OrganicationClient(orgId, ""));
			
	       }
			managerInfo = new SomeoneClient(orgs);
		return managerInfo;
	 }
	public ParticipateInfoClient getMembersInfo(){
		ParticipateInfoClient members = null;
		List<OrganicationClient> orgs = new ArrayList<OrganicationClient>();
	     
			for (String orgId : display.getMemberIds()) {
				 orgs.add(new OrganicationClient(orgId, ""));
			
	       }
			members = new SomeoneClient(orgs);
		return members;
	 }
	// 验证方法
	private boolean validateSubmit() {
		boolean flag = true;
		StringBuilder errorMsg = new StringBuilder();
		if (display.getName().getValue() == null
				|| "".equals(display.getName().getValue().trim())) {
			errorMsg.append("请填写礼品名称!<br>");
			flag = false;
		}

		

		if (!flag) {
			win.alert(errorMsg.toString());
		}

		return flag;
	}

	private void initEdit() {
		dispatcher.execute(new SearchTeamByIdRequest(teamId),
				new AsyncCallback<SearchTeamByIdResponse>() {
					@Override
					public void onFailure(Throwable arg0) {
						errorHandler.alert("查询出错!");
						Platform.getInstance()
								.getEditorRegistry()
								.closeEditor(TeamConstants.EDITOR_TEAM_ADD,instanceId);
					}

					@Override
					public void onSuccess(SearchTeamByIdResponse response) {
						TeamVo giftVo = response.getTeamVoClient();
						clear();
						display.initEditTeam(giftVo ,instanceId);
					}
				});
	}

	

	private void clear() {
		display.clear();
	}


	@Override
	public void initEditor(String instanceId,TeamSearchVo vo) {
		this.instanceId = instanceId;
		this.teamId = vo.getId();
		
	}

}
