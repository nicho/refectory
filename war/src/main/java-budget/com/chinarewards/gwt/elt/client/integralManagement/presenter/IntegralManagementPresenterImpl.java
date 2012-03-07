package com.chinarewards.gwt.elt.client.integralManagement.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.breadCrumbs.ui.BreadCrumbsMenu;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.presenter.DockPresenter;
import com.chinarewards.gwt.elt.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.elt.client.integralManagement.presenter.IntegralManagementPresenter.IntegralManagementDisplay;
import com.chinarewards.gwt.elt.client.integralManagement.request.IntegralManagementRequest;
import com.chinarewards.gwt.elt.client.integralManagement.request.IntegralManagementResponse;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewards.plugin.RewardsListConstants;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.model.rewards.RewardPageType;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class IntegralManagementPresenterImpl extends
		BasePresenter<IntegralManagementDisplay> implements
		IntegralManagementPresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;
	private final BreadCrumbsPresenter breadCrumbs;
	final MenuProcessor menuProcessor;
	final DockPresenter dockPresenter;
	final BreadCrumbsMenu breadCrumbsMenu;
	
	@Inject
	public IntegralManagementPresenterImpl(EventBus eventBus,
			DispatchAsync dispatch, ErrorHandler errorHandler,
			SessionManager sessionManager, IntegralManagementDisplay display,
			Win win,BreadCrumbsPresenter breadCrumbs,MenuProcessor menuProcessor,DockPresenter dockPresenter,BreadCrumbsMenu breadCrumbsMenu) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
		this.breadCrumbs=breadCrumbs;
		this.menuProcessor=menuProcessor;
		this.dockPresenter=dockPresenter;
		this.breadCrumbsMenu=breadCrumbsMenu;

	}

	@Override
	public void bind() {
		breadCrumbs.loadListPage();
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		
		dispatch.execute(new IntegralManagementRequest(sessionManager.getSession().getCorporationId()),
				new AsyncCallback<IntegralManagementResponse>() {
					@Override
					public void onFailure(Throwable e) {
						win.alert(e.getMessage());
					}

					@Override
					public void onSuccess(IntegralManagementResponse response) {
						display.setBudgetIntegral((int)response.getBudgetIntegral()+"");
						display.setUseIntegeral((int)response.getBudgetIntegral()+"");
						display.refresh(response.getResult(),sessionManager.getSession().getCorporationId());
					}
				});
		registerHandler(display.getNominateClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						dockPresenter.getDisplay().changeTopMenu("Reward");
						dockPresenter.getDisplay().setMenuTitle("应用奖项");
						menuProcessor.initrender(dockPresenter.getDisplay().getMenu(), "Reward");

					//	eventBus.fireEvent(new MenuClickEvent(menuProcessor.getMenuItem(RewardsListConstants.MENU_REWARDSLIST_SEARCH)));
					
						RewardsPageClient rpc=new RewardsPageClient();
						rpc.setTitleName("待提名奖项");
						rpc.setPageType(RewardPageType.NOMINATEPAGE);
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										RewardsListConstants.EDITOR_REWARDSLIST_SEARCH,
										"EDITOR_REWARDSLIST_"+RewardPageType.NOMINATEPAGE,rpc);
						menuProcessor.changItemColor("待提名奖项");

					}
				}));
		display.setDockPresenter(dockPresenter);
		display.setMenuProcessor(menuProcessor);
		display.setBreadCrumbsMenu(breadCrumbsMenu);

	 
	}

}
