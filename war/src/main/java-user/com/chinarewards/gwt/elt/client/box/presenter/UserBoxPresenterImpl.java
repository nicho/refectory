package com.chinarewards.gwt.elt.client.box.presenter;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.box.presenter.UserBoxPresenter.UserBoxDisplay;
import com.chinarewards.gwt.elt.client.box.request.UserBoxRequest;
import com.chinarewards.gwt.elt.client.box.request.UserBoxResponse;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.presenter.DockPresenter;
import com.chinarewards.gwt.elt.client.core.ui.MenuProcessor;
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

public class UserBoxPresenterImpl extends BasePresenter<UserBoxDisplay>
		implements UserBoxPresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;
	String orderId;
	final MenuProcessor menuProcessor;
	final DockPresenter dockPresenter;
	@Inject
	public UserBoxPresenterImpl(EventBus eventBus, DispatchAsync dispatch,MenuProcessor menuProcessor, DockPresenter dockPresenter,
			ErrorHandler errorHandler, SessionManager sessionManager,UserBoxDisplay display, Win win) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
        this.menuProcessor = menuProcessor;
        this.dockPresenter = dockPresenter;
	}

	@Override
	public void bind() {
		
			
		registerHandler(display.getView().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dockPresenter.getDisplay().changeTopMenu("Reward");
				dockPresenter.getDisplay().setMenuTitle("应用奖项");
				menuProcessor.initrender(dockPresenter.getDisplay().getMenu(), "Reward");
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
		registerHandler(display.getOperate().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dockPresenter.getDisplay().changeTopMenu("Reward");
				dockPresenter.getDisplay().setMenuTitle("应用奖项");
				menuProcessor.initrender(dockPresenter.getDisplay().getMenu(), "Reward");
				RewardsPageClient rpc=new RewardsPageClient();
				rpc.setTitleName("待颁奖奖项");
				rpc.setPageType(RewardPageType.AWARDREWARDPAGE);
				Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								RewardsListConstants.EDITOR_REWARDSLIST_SEARCH,
								"EDITOR_REWARDSLIST_"+RewardPageType.AWARDREWARDPAGE,rpc);
				menuProcessor.changItemColor("待颁奖奖项");
			}
		}));

	}

	

	@Override
	public void initUserBox() {
		
		{
			 //待提名
			 dispatch.execute(new UserBoxRequest(sessionManager.getSession(),"PENDING_NOMINATE"),
					new AsyncCallback<UserBoxResponse>() {
						@Override
						public void onFailure(Throwable arg0) {
							errorHandler.alert("查询出错!");
							
						}
						@Override
						public void onSuccess(UserBoxResponse response) {
							display.setMessage( response.getWeek());
							display.setUserBoxNominate(response.getTotal()+"");
						}

					});
			//待颁奖
			 dispatch.execute(new UserBoxRequest(sessionManager.getSession(),"NEW"),
					new AsyncCallback<UserBoxResponse>() {
						@Override
						public void onFailure(Throwable arg0) {
							errorHandler.alert("查询出错!");
							
						}
						@Override
						public void onSuccess(UserBoxResponse response) {
							
							display.setUserSend(response.getTotal()+"");
						}

					});
		    }		
	}



}
