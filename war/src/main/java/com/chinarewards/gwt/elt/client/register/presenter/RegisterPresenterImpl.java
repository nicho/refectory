package com.chinarewards.gwt.elt.client.register.presenter;


import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.EltGinjector;
import com.chinarewards.gwt.elt.client.enterprise.model.EnterpriseVo;
import com.chinarewards.gwt.elt.client.login.presenter.AlertErrorWidget;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.register.model.OrgInitVo;
import com.chinarewards.gwt.elt.client.register.presenter.RegisterPresenter.RegisterDisplay;
import com.chinarewards.gwt.elt.client.register.request.RegisterRequest;
import com.chinarewards.gwt.elt.client.register.request.RegisterResponse;
import com.chinarewards.gwt.elt.client.ui.DialogBox;
import com.chinarewards.gwt.elt.client.util.StringUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;
/**
 * 
 * @author lw
 * 企业注册
 */
public class RegisterPresenterImpl extends BasePresenter<RegisterDisplay> implements
		RegisterPresenter {

	private final EltGinjector injector = GWT.create(EltGinjector.class);
	final DispatchAsync dispatchAsync;
	
	EnterpriseVo enterpriseVo = new EnterpriseVo();
	OrgInitVo vo;
	@Inject
	public RegisterPresenterImpl(final EventBus eventBus, RegisterDisplay display,
			DispatchAsync dispatchAsync) {
		super(eventBus, display);
		this.dispatchAsync = dispatchAsync;
		
	}

	@Override
	public void bind() {
	//	initialization();
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
    	//enterpriseVo.setCorporation(display.getCorporation().getValue());
    	enterpriseVo.setEmailAddress(display.getEmail().getValue());
    	enterpriseVo.setName(display.getEnterpriseName().getValue());
    	//enterpriseVo.setFax(display.getFax().getValue());
    	enterpriseVo.setLinkman(display.getLinkman().getValue());
    	enterpriseVo.setDescription(display.getRemark().getValue());
    	enterpriseVo.setTell(display.getTell().getValue());
    	enterpriseVo.setWeb(display.getWeb().getValue());
    	
    	return enterpriseVo;
    }
    
    public void sendService(EnterpriseVo enterprise) {

		if (null == enterprise.getName() || enterprise.getName() .trim().equals("")) {
			final AlertErrorWidget ae = new AlertErrorWidget();
			final DialogBox dialogBoxae = new DialogBox();
			ae.getOkBtn().addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent arg0) {
					dialogBoxae.hide();
				}
			});
			ae.setMessage("企业名称不能为空!");
			dialogBoxae.setWidget(ae);
			dialogBoxae.setGlassEnabled(true);
			dialogBoxae.setAnimationEnabled(true);
			dialogBoxae.setWidth("350px");
			dialogBoxae.setText("提示");
			dialogBoxae.center();
			dialogBoxae.show();
			injector.getMain().init(RootLayoutPanel.get());
			
			return;
		}
		if (StringUtil.isEmpty(display.getEmail().getValue())) {
			final AlertErrorWidget ae = new AlertErrorWidget();
			final DialogBox dialogBoxae = new DialogBox();
			ae.getOkBtn().addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent arg0) {
					dialogBoxae.hide();
				}
			});
			ae.setMessage("电子邮箱不能为空!");
			dialogBoxae.setWidget(ae);
			dialogBoxae.setGlassEnabled(true);
			dialogBoxae.setAnimationEnabled(true);
			dialogBoxae.setWidth("350px");
			dialogBoxae.setText("提示");
			dialogBoxae.center();
			dialogBoxae.show();
			return;
		}
		else if(!StringUtil.isValidEmail(display.getEmail().getValue()))
		{
			final AlertErrorWidget ae = new AlertErrorWidget();
			final DialogBox dialogBoxae = new DialogBox();
			ae.getOkBtn().addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent arg0) {
					dialogBoxae.hide();
				}
			});
			ae.setMessage("电子邮箱格式不正确!");
			dialogBoxae.setWidget(ae);
			dialogBoxae.setGlassEnabled(true);
			dialogBoxae.setAnimationEnabled(true);
			dialogBoxae.setWidth("350px");
			dialogBoxae.setText("提示");
			dialogBoxae.center();
			dialogBoxae.show();
			return;
		}
		if(display.isCheckSee()!=true)
		{
			final AlertErrorWidget ae = new AlertErrorWidget();
			final DialogBox dialogBoxae = new DialogBox();
			ae.getOkBtn().addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent arg0) {
					dialogBoxae.hide();
				}
			});
			ae.setMessage("请确定已阅读注册协议!");
			dialogBoxae.setWidget(ae);
			dialogBoxae.setGlassEnabled(true);
			dialogBoxae.setAnimationEnabled(true);
			dialogBoxae.setWidth("350px");
			dialogBoxae.setText("提示");
			dialogBoxae.center();
			dialogBoxae.show();
			injector.getMain().init(RootLayoutPanel.get());
			return;
		}
		RegisterRequest req = new RegisterRequest(enterprise);
		dispatchAsync.execute(req, new AsyncCallback<RegisterResponse>() {
					public void onFailure(Throwable caught) {
						
						Window.alert("创建失败");
					}
					@Override
					public void onSuccess(RegisterResponse arg) {
						if(!arg.getCorpId().equals("")){
							final AlertErrorWidget ae = new AlertErrorWidget();
							final DialogBox dialogBoxae = new DialogBox();
							ae.getOkBtn().addClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent arg0) {
									dialogBoxae.hide();
								}
							});
							ae.setMessage("创建成功!");
							dialogBoxae.setWidget(ae);
							dialogBoxae.setGlassEnabled(true);
							dialogBoxae.setAnimationEnabled(true);
							dialogBoxae.setWidth("350px");
							dialogBoxae.setText("提示");
							dialogBoxae.center();
							dialogBoxae.show();
							injector.getMain().init(RootLayoutPanel.get());
						    injector.getRegisterPresenter().unbind();
						   
						}else
							Window.alert("创建失败");
					}
				});
	}
    

}
