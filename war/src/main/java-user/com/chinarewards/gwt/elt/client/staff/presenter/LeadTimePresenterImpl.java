package com.chinarewards.gwt.elt.client.staff.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.staff.model.StaffVo;
import com.chinarewards.gwt.elt.client.staff.presenter.LeadTimePresenter.LeadTimeDisplay;
import com.chinarewards.gwt.elt.client.staff.request.LeadTimeRequest;
import com.chinarewards.gwt.elt.client.staff.request.LeadTimeResponse;
import com.chinarewards.gwt.elt.client.staffView.request.StaffViewRequest;
import com.chinarewards.gwt.elt.client.staffView.request.StaffViewResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.win.Win;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

/**
 * 
 * @author lw
 * 颁奖提前通知时间设置
 */
public class LeadTimePresenterImpl extends
		BasePresenter<LeadTimeDisplay> implements LeadTimePresenter {
	
	final DispatchAsync dispatcher;
	final Win win;
	private final SessionManager sessionManager;
	private final BreadCrumbsPresenter breadCrumbs;

	@Inject
	public LeadTimePresenterImpl(final EventBus eventBus,
			LeadTimeDisplay display, BreadCrumbsPresenter breadCrumbs,
			DispatchAsync dispatcher, SessionManager sessionManager, Win win) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.sessionManager = sessionManager;
		this.win = win;
		this.breadCrumbs = breadCrumbs;
	}

	@Override
	public void bind() {
		breadCrumbs.loadChildPage("接收短信或邮件时间");
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());

		initialization();

		registerHandler(display.getSaveClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						doSaveLeadTime();
					}
				}));

	}

	protected void doSaveLeadTime() {
		if (display.getLeadTime().getValue() == null|| "".equals(display.getLeadTime().getValue().trim())) {
			win.alert("提前通知天数不能为空");
			display.clear();
			return;
		} else {
			try {
				int d = Integer.parseInt(display.getLeadTime().getValue().trim());
				
			} catch (Exception e) {
				win.alert("提前天数为数字");
				display.clear();
				return;
			}
		}
		StaffVo vo = getStaffVo();
		sendService(vo);
	}

	/**
	 * 得到客户端传来的信息放在VO
	 * 
	 * @return
	 */
	public StaffVo getStaffVo() {
		StaffVo staffVo = new StaffVo();
		staffVo.setId(display.getStaffId().trim());
		staffVo.setLeadTime(Integer.valueOf(display.getLeadTime().getValue()));

		return staffVo;

	}

	public void sendService(StaffVo staffVo) {

		LeadTimeRequest req = new LeadTimeRequest(staffVo.getId(),Integer.parseInt(display.getLeadTime().getValue()));
		
		dispatcher.execute(req, new AsyncCallback<LeadTimeResponse>() {
			public void onFailure(Throwable caught) {

				win.alert("操作失败");
			}

			@Override
			public void onSuccess(LeadTimeResponse arg0) {
				if(!arg0.getStaffVo().getId().equals(""))
				 win.alert("操作成功");

			}
		});
	}

	/**
	 * 加载初始化数据
	 */
	private void initialization() {		
//		 String corporationId = sessionManager.getSession().getCorporationId();
		 
		dispatcher.execute(new StaffViewRequest(sessionManager.getSession().getStaffId()), new AsyncCallback<StaffViewResponse>() {
			public void onFailure(Throwable caught) {

				win.alert("初始化失败");
			}

			@Override
			public void onSuccess(StaffViewResponse response) {
				if (response != null) {
					StaffVo vo = new StaffVo();
					vo.setId(response.getStaffId());
					vo.setLeadTime(response.getLeadTime());
					clear();
					display.initEditLeadTime(vo);
				}
			}

		});

	}
	

	private void clear() {
		display.clear();
	}


	@Override
	public void initEditor(String id) {		
	
	}
}
