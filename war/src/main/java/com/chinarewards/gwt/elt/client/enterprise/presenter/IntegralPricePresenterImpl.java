package com.chinarewards.gwt.elt.client.enterprise.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.budget.model.CorpBudgetVo;
import com.chinarewards.gwt.elt.client.budget.request.SearchCorpBudgetByCorpIdRequest;
import com.chinarewards.gwt.elt.client.budget.request.SearchCorpBudgetByCorpIdResponse;
import com.chinarewards.gwt.elt.client.enterprise.model.EnterpriseVo;
import com.chinarewards.gwt.elt.client.enterprise.presenter.IntegralPricePresenter.IntegralPriceDisplay;
import com.chinarewards.gwt.elt.client.enterprise.request.EditIntegralPriceRequest;
import com.chinarewards.gwt.elt.client.enterprise.request.EditIntegralPriceResponse;
import com.chinarewards.gwt.elt.client.enterprise.request.EnterpriseInitRequest;
import com.chinarewards.gwt.elt.client.enterprise.request.EnterpriseInitResponse;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.win.Win;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

/**
 * 
 * @author yanrui 积分价值设置
 */
public class IntegralPricePresenterImpl extends
		BasePresenter<IntegralPriceDisplay> implements IntegralPricePresenter {
	
	final DispatchAsync dispatcher;
	final Win win;
	private final SessionManager sessionManager;
	List<HandlerRegistration> handlerRegistrations = new ArrayList<HandlerRegistration>();
	private final BreadCrumbsPresenter breadCrumbs;

	@Inject
	public IntegralPricePresenterImpl(final EventBus eventBus,
			IntegralPriceDisplay display, BreadCrumbsPresenter breadCrumbs,
			DispatchAsync dispatcher, SessionManager sessionManager, Win win) {
		super(eventBus, display);
		this.dispatcher = dispatcher;
		this.sessionManager = sessionManager;
		this.win = win;
		this.breadCrumbs = breadCrumbs;
	}

	@Override
	public void bind() {
		breadCrumbs.loadChildPage("积分价值设置");
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
	 * 
	 * @return
	 */
	public EnterpriseVo getEnterprise() {
		EnterpriseVo enterpriseVo = new EnterpriseVo();
		enterpriseVo.setId(display.getEnterpriseId().trim());
		enterpriseVo.setIntegralPrice(Double.valueOf(display.getIntegralPrice()
				.getValue()));

		int selectedIndex = display.getMoneyType().getSelectedIndex();
		enterpriseVo.setMoneyType(display.getMoneyType().getValue(selectedIndex));
//		enterpriseVo.setMoneyType(display.getMoneyType().getItemText(selectedIndex));
		
		return enterpriseVo;

	}

	public void sendService(EnterpriseVo enterpriseVo) {

		EditIntegralPriceRequest req = new EditIntegralPriceRequest(enterpriseVo,
				sessionManager.getSession());
		
		dispatcher.execute(req, new AsyncCallback<EditIntegralPriceResponse>() {
			public void onFailure(Throwable caught) {

				win.alert("操作失败");
			}

			@Override
			public void onSuccess(EditIntegralPriceResponse arg0) {
				win.alert("操作成功");

			}
		});
	}

	/**
	 * 加载初始化数据
	 */
	private void initialization() {		
		 String corporationId = sessionManager.getSession().getCorporationId();
		 
		dispatcher.execute(new EnterpriseInitRequest(
				sessionManager.getSession()), new AsyncCallback<EnterpriseInitResponse>() {
			public void onFailure(Throwable caught) {

				win.alert("初始化失败");
			}

			@Override
			public void onSuccess(EnterpriseInitResponse response) {
				if (response != null) {
					EnterpriseVo enterpriseVo = response.getEnterprise();
					clear();
					display.initEditIntegralPrice(enterpriseVo);
				}
			}
		});
		
		
		dispatcher.execute(new SearchCorpBudgetByCorpIdRequest(
				corporationId), new AsyncCallback<SearchCorpBudgetByCorpIdResponse>() {
			public void onFailure(Throwable caught) {
				win.alert("初始化失败");
			}

			@Override
			public void onSuccess(SearchCorpBudgetByCorpIdResponse response) {
				if (response != null) {
					CorpBudgetVo corpBudgetVo = response.getCorpBudgetVo();
				
					if (corpBudgetVo!=null) {
						if(corpBudgetVo.getId()==null||"".equals(corpBudgetVo.getId())){						
							display.setSaveVisible(true);
						}else{
							display.setSaveVisible(false);
						}
					} else {
						display.setSaveVisible(true);
					}
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
