package com.chinarewards.gwt.elt.client.rewardItem.presenter;



import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewardItem.plugin.RewardsItemConstants;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemByIdRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemByIdResponse;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class RewardsItemViewPresenterImpl extends
		BasePresenter<RewardsItemViewPresenter.RewardsItemViewDisplay> implements		RewardsItemViewPresenter {
	   String instanceId;//修改时传过来的ID
	
	boolean isItemStore = false;//是奖项的查找还是奖项库的
	private final DispatchAsync dispatcher;
	private final ErrorHandler errorHandler;
	private final BreadCrumbsPresenter breadCrumbs;
	String rewardId ;
	RewardsItemClient param = new RewardsItemClient();
	@Inject
	public RewardsItemViewPresenterImpl(EventBus eventBus,
			RewardsItemViewDisplay display,DispatchAsync dispatcher,BreadCrumbsPresenter breadCrumbs
			,ErrorHandler errorHandler,SessionManager sessionManager	) {
		super(eventBus, display);
		this.dispatcher=dispatcher;
		this.errorHandler = errorHandler;
		this.breadCrumbs = breadCrumbs;
	}
	 @Override
	 public void bind() {
		 registerHandler(display.getBackClick().addClickHandler(
					new ClickHandler() {
						@Override
						public void onClick(ClickEvent arg0) {
							
							Platform.getInstance()
							.getEditorRegistry()
							.openEditor(RewardsItemConstants.EDITOR_REWARDSITEM_List,
									"EDITOR_REWARDSITEM_List_DO_ID", instanceId);

		               }
	
	    }));
		 
		 registerHandler(display.getUpdateClick().addClickHandler(
					new ClickHandler() {
						@Override
						public void onClick(ClickEvent arg0) {
							
							Platform.getInstance()
							.getEditorRegistry()
							.openEditor(
									RewardsItemConstants.EDITOR_REWARDSITEM_ADD,
									"EDITOR_REWARDS_ITEM_ADD"+ rewardId, param);
		               }
	
	    }));
		 //下面为奖项库按钮
		 registerHandler(display.getBackStoreClick().addClickHandler(
				 
					new ClickHandler() {
						@Override
						public void onClick(ClickEvent arg0) {
							 Platform.getInstance()
							.getEditorRegistry()
							.openEditor(RewardsItemConstants.EDITOR_REWARDSITEMSTORE_LIST,
									RewardsItemConstants.EDITOR_REWARDSITEMSTORE, param);

		               }
	
	    }));
		 
		 registerHandler(display.getUpdateStoreClick().addClickHandler(
					new ClickHandler() {
						@Override
						public void onClick(ClickEvent arg0) {
							
							Platform.getInstance()
							.getEditorRegistry()
							.openEditor(
									RewardsItemConstants.EDITOR_REWARDSITEM_ADD,
									RewardsItemConstants.EDITOR_REWARDSITEMSTORE, param);
		               }
	
	    }));
	 }
	   //查看时初始化数据
		@Override
		public void initInstanceId(String instanceId,RewardsItemClient item) {
			this.instanceId = instanceId;
			param = item;//把查看得到的VO保存下来给修改时做为参数用
			initDataToEditRewardsItem( item,instanceId);
		}
		
		private void initDataToEditRewardsItem(final RewardsItemClient item,final String instanceId) {
			rewardId = item.getId();
			
			if(instanceId.equals(RewardsItemConstants.EDITOR_REWARDSITEMSTORE))
			   isItemStore = true;			
			{
				dispatcher.execute(new SearchRewardsItemByIdRequest(rewardId,isItemStore),
				new AsyncCallback<SearchRewardsItemByIdResponse>() {
					@Override
					public void onFailure(Throwable arg0) {
						errorHandler.alert("查询奖项出错!");
						Platform.getInstance()
						.getEditorRegistry()
						.closeEditor(RewardsItemConstants.EDITOR_REWARDSITEM_ADD,instanceId);
					}

					@Override
					public void onSuccess(SearchRewardsItemByIdResponse response) {
						RewardsItemClient item = response.getRewardsItem();
						if(isItemStore==false){
							breadCrumbs.loadChildPage("奖项详细");
						    										    
				        }else{
				        	breadCrumbs.loadChildPage("奖项模板详细");
				       				        	
				        } 
						display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
						display.showRewardsItem(item,isItemStore);//显示奖项还是奖项库
					}

				});
		}
		}
				
				
     }
