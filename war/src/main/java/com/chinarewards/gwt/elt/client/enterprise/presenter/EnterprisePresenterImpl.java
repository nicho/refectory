package com.chinarewards.gwt.elt.client.enterprise.presenter;


import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.enterprise.model.EnterpriseVo;
import com.chinarewards.gwt.elt.client.enterprise.presenter.EnterprisePresenter.EnterpriseDisplay;
import com.chinarewards.gwt.elt.client.enterprise.request.EnterpriseInitRequest;
import com.chinarewards.gwt.elt.client.enterprise.request.EnterpriseInitResponse;
import com.chinarewards.gwt.elt.client.enterprise.request.EnterpriseRequest;
import com.chinarewards.gwt.elt.client.enterprise.request.EnterpriseResponse;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.util.StringUtil;
import com.chinarewards.gwt.elt.client.win.Win;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
/**
 * 
 * @author lw
 * 企业注册
 */
public class EnterprisePresenterImpl extends BasePresenter<EnterpriseDisplay> implements
		EnterprisePresenter {

	
	final DispatchAsync dispatchAsync;
	final Win  win;
	private final SessionManager sessionManager;
	List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
	private final BreadCrumbsPresenter breadCrumbs;
	EnterpriseVo enterpriseVo = new EnterpriseVo();
	
	@Inject
	public EnterprisePresenterImpl(final EventBus eventBus, EnterpriseDisplay display,BreadCrumbsPresenter breadCrumbs,
			DispatchAsync dispatchAsync,SessionManager sessionManager,Win  win) {
		super(eventBus, display);
		this.dispatchAsync = dispatchAsync;
		this.sessionManager = sessionManager;
		this.win =win;
		this.breadCrumbs = breadCrumbs;
	}

	@Override
	public void bind() {
		breadCrumbs.loadChildPage("企业信息注册");
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		initialization();
		registerHandler(display.getSaveClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						doSaveEnterprise();
					}
				}));
		
		
	}

	protected void doSaveEnterprise() {
		EnterpriseVo enterprise = getEnterprise();
	    sendService(enterprise);
	}
	/**
	 * 得到客户端传来的信息放在VO
	 * @return
	 */
    public EnterpriseVo getEnterprise(){
    	
    	enterpriseVo.setAddress(display.getAddress().getValue());
    	enterpriseVo.setCellphone(display.getCellphone().getValue());
    	enterpriseVo.setCorporation(display.getCorporation().getValue());
    	enterpriseVo.setEmailAddress(display.getEmail().getValue());
    	enterpriseVo.setName(display.getEnterpriseName().getValue());
    	enterpriseVo.setFax(display.getFax().getValue());
    	enterpriseVo.setLinkman(display.getLinkman().getValue());
    	enterpriseVo.setDescription(display.getRemark().getValue());
    	enterpriseVo.setTell(display.getTell().getValue());
    	enterpriseVo.setWeb(display.getWeb().getValue());
    	enterpriseVo.setId(display.getEnterpriseId().trim());
    	return enterpriseVo;
    }
    
    public void sendService(EnterpriseVo enterprise) {

		if (null == enterprise.getName() || enterprise.getName() .trim().equals("")) {
			win.alert("企业名称不能为空!");
			return;
		}
		if (StringUtil.isEmpty(display.getEmail().getValue())) {
			win.alert("邮箱不能为空");
		}else if(!StringUtil.isValidEmail(display.getEmail().getValue())){
			win.alert("邮箱格式不正确");
		}
		EnterpriseRequest req = new EnterpriseRequest(enterprise,sessionManager.getSession());
		dispatchAsync.execute(req, new AsyncCallback<EnterpriseResponse>() {
					public void onFailure(Throwable caught) {
						
						win.alert("操作失败");
					}
					@Override
					public void onSuccess(EnterpriseResponse arg0) {
						win.alert("操作成功");
						
					}
				});
			}
    
    /**
	 * 加载初始化数据
	 */
	private void initialization() {
		
		EnterpriseInitRequest req = new EnterpriseInitRequest(sessionManager.getSession());
		dispatchAsync.execute(req, new AsyncCallback<EnterpriseInitResponse>() {
			public void onFailure(Throwable caught) {
				
				win.alert("初始化失败");
			}
			@Override
			public void onSuccess(EnterpriseInitResponse response) {
				
			if(response !=null){
				  enterpriseVo = response.getEnterprise();
		          display.setAddress(enterpriseVo.getAddress());
				  display.setCellphone(enterpriseVo.getCellphone());
				  display.setCorporation(enterpriseVo.getCorporation());
				  display.setEmail(enterpriseVo.getEmailAddress());
				  display.setEnterpriseName(enterpriseVo.getName());
				  display.setFax(enterpriseVo.getFax());
				  display.setLinkman(enterpriseVo.getLinkman());
				  display.setRemark(enterpriseVo.getDescription());
				  display.setTell(enterpriseVo.getTell());
				  display.setWeb(enterpriseVo.getWeb());
				  display.setEnterpriseId(enterpriseVo.getId());
				  
				}
			}

			
			
			
		});
	
	
	}
}
