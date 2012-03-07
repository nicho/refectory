package com.chinarewards.gwt.elt.client.core.presenter;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.EltGinjector;
import com.chinarewards.gwt.elt.client.awardShop.plugin.AwardShopListConstants;
import com.chinarewards.gwt.elt.client.awardShop.request.SearchAwardShopRequest;
import com.chinarewards.gwt.elt.client.awardShop.request.SearchAwardShopResponse;
import com.chinarewards.gwt.elt.client.breadCrumbs.ui.BreadCrumbsMenu;
import com.chinarewards.gwt.elt.client.colleague.plugin.ColleagueListConstants;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.PluginManager;
import com.chinarewards.gwt.elt.client.core.presenter.StaffPresenter.StaffDisplay;
import com.chinarewards.gwt.elt.client.core.request.StaffInitRequest;
import com.chinarewards.gwt.elt.client.core.request.StaffInitResponse;
import com.chinarewards.gwt.elt.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.elt.client.corpBroadcast.plugin.CorpBroadcastConstants;
import com.chinarewards.gwt.elt.client.gift.model.GiftClient;
import com.chinarewards.gwt.elt.client.gift.model.GiftCriteria;
import com.chinarewards.gwt.elt.client.gift.model.GiftCriteria.GiftStatus;
import com.chinarewards.gwt.elt.client.gloryBroadcast.plugin.GloryBroadcastConstants;
import com.chinarewards.gwt.elt.client.login.LastLoginRoleRequest;
import com.chinarewards.gwt.elt.client.login.LastLoginRoleResponse;
import com.chinarewards.gwt.elt.client.login.event.LoginEvent;
import com.chinarewards.gwt.elt.client.message.plugin.MessageListConstants;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.orderHistory.plugin.OrderHistoryConstants;
import com.chinarewards.gwt.elt.client.password.plugin.PasswordConstants;
import com.chinarewards.gwt.elt.client.rewardItem.plugin.RewardsItemConstants;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsGridClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsGridCriteria;
import com.chinarewards.gwt.elt.client.rewards.plugin.RewardsListStaffConstants;
import com.chinarewards.gwt.elt.client.rewards.request.SearchRewardsGridRequest;
import com.chinarewards.gwt.elt.client.rewards.request.SearchRewardsGridResponse;
import com.chinarewards.gwt.elt.client.smallControl.view.SmallRewardItemWindowWidget;
import com.chinarewards.gwt.elt.client.smallControl.view.SmallRewardWindowWidget;
import com.chinarewards.gwt.elt.client.smallControl.view.SmallShopWindowWidget;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.plugin.StaffHeavenIndexConstants;
import com.chinarewards.gwt.elt.client.staffInfo.plugin.StaffInfoConstants;
import com.chinarewards.gwt.elt.client.staffIntegral.plugin.StaffIntegralConstants;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.chinarewards.gwt.elt.model.user.UserRoleVo;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.inject.Inject;

public class StaffPresenterImpl extends BasePresenter<StaffDisplay> implements
		StaffPresenter {
	final PluginManager pluginManager;
	final SessionManager sessionManager;
	final EltGinjector injector;
	final MenuProcessor menuProcessor;
	final DispatchAsync dispatchAsync;
	final BreadCrumbsMenu breadCrumbsMenu;

	@Inject
	public StaffPresenterImpl(EventBus eventBus, StaffDisplay display,
			SessionManager sessionManager, PluginManager pluginManager,
			EltGinjector injector, MenuProcessor menuProcessor,
			DispatchAsync dispatchAsync, BreadCrumbsMenu breadCrumbsMenu) {
		super(eventBus, display);
		this.sessionManager = sessionManager;
		this.pluginManager = pluginManager;
		this.injector = injector;
		this.menuProcessor = menuProcessor;
		this.dispatchAsync = dispatchAsync;
		this.breadCrumbsMenu = breadCrumbsMenu;
	}

	public void bind() {
		List<UserRoleVo> roleslt = new ArrayList<UserRoleVo>();
		UserRoleVo[] roles = sessionManager.getSession().getUserRoles();

		if (roles.length > 0) {
			for (UserRoleVo r : roles) {
				roleslt.add(r);
			}
			if (!roleslt.contains(UserRoleVo.CORP_ADMIN)
					&& !roleslt.contains(UserRoleVo.DEPT_MGR)) {
				display.disableManagementCenter();
			}
			if (!roleslt.contains(UserRoleVo.GIFT)) {
				display.disableGiftExchange();
			}
			if (!roleslt.contains(UserRoleVo.STAFF)) {
				display.disableStaffCorner();
			}
		}
		init();
		registerHandler(display.getlogBtn().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LoginEvent(LoginEvent.LoginStatus.LOGOUT));
			}
		}));
		registerHandler(display.getBtnCollection().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Window.alert("收藏");
					}
				}));

		registerHandler(display.getManagementCenter().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						UserRoleVo role = UserRoleVo.DEPT_MGR;

						for (int i = 0; i < sessionManager.getSession()
								.getUserRoles().length; i++) {
							if (UserRoleVo.CORP_ADMIN == sessionManager
									.getSession().getUserRoles()[i]) {
								role = UserRoleVo.CORP_ADMIN;
							}
						}
						dispatchAsync.execute(new LastLoginRoleRequest(
								sessionManager.getSession().getToken(), role),
								new AsyncCallback<LastLoginRoleResponse>() {

									@Override
									public void onFailure(Throwable e) {
										// Window.alert("系统切换出错");
									}

									@Override
									public void onSuccess(
											LastLoginRoleResponse resp) {
										// 成功
										if ("success".equals(resp.getFal()))
											Window.Location.reload();
										else
											Window.alert("系统切换出错");

									}
								});
					}
				}));
		registerHandler(display.getGiftExchange().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						dispatchAsync.execute(new LastLoginRoleRequest(
								sessionManager.getSession().getToken(),
								UserRoleVo.GIFT),
								new AsyncCallback<LastLoginRoleResponse>() {

									@Override
									public void onFailure(Throwable e) {
										// Window.alert("系统切换出错");
									}

									@Override
									public void onSuccess(
											LastLoginRoleResponse resp) {
										// 成功
										if ("success".equals(resp.getFal()))
											Window.Location.reload();
										else
											Window.alert("系统切换出错");

									}
								});
					}
				}));


		// 查看积分
		registerHandler(display.getViewPoints().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										StaffIntegralConstants.EDITOR_STAFFINTEGRAL_SEARCH,
										"EDITOR_STAFFINTEGRAL_SEARCH_DO_ID",
										null);
					}
				}));
		// 获奖历史
		registerHandler(display.getWinninghistory().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RewardsPageClient client = new RewardsPageClient();
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										RewardsListStaffConstants.EDITOR_REWARDSLIST_STAFF_SEARCH,
										"EDITOR_REWARDSLIST_STAFF_SEARCH_DO_ID",
										client);
					}
				}));

		// 我参与的奖项
		registerHandler(display.getParticipationAwards().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RewardsPageClient client = new RewardsPageClient();
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										RewardsItemConstants.EDITOR_REWARDSITEM_STAFF_LIST,
										"EDITOR_REWARDSITEMLIST_STAFF_SEARCH_DO_ID",
										client);
					}
				}));

		// 公司其他奖项
		registerHandler(display.getOtherAwards().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RewardsPageClient client = new RewardsPageClient();
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										RewardsItemConstants.EDITOR_REWARDSITEM_COMPANYOTHER_LIST,
										"EDITOR_REWARDSITEMLIST_COMPANYOTHER_SEARCH_DO_ID",
										client);
					}
				}));
//兑换历史
		registerHandler(display.getExchangeHistory().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										OrderHistoryConstants.EDITOR_ORDERHISTORY_SEARCH,
										"EDITOR_ORDERHISTORY_SEARCH_DO_ID",
										null);
					}
				}));
		// 员工首页
		registerHandler(display.getStaffHeavenIndex().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										StaffHeavenIndexConstants.EDITOR_STAFFHEAVENINDEX_SEARCH,
										"EDITOR_STAFFHEAVENINDEX_SEARCH_DO_ID",
										null);
					}
				}));
		//奖品商城
		registerHandler(display.getAwardShop().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Platform.getInstance()
				.getEditorRegistry()
				.openEditor(
						AwardShopListConstants.EDITOR_AWARDSHOPLIST_SEARCH,
						"EDITOR_AWARDSHOPLIST_SEARCH_DO_ID", null);
			}
		}));

		//公司广播
		registerHandler(display.getCorpBroadcastAnchor().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Platform.getInstance()
				.getEditorRegistry()
				.openEditor(
						CorpBroadcastConstants.EDITOR_CORPBROADCAST_SEARCH,
						"EDITOR_CORPBROADCAST_SEARCH_DO_ID", null);
			}
		}));
		//光荣榜
		registerHandler(display.getGloryAnchor().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Platform.getInstance()
				.getEditorRegistry()
				.openEditor(
						GloryBroadcastConstants.EDITOR_GLORYBROADCAST_SEARCH,
						"EDITOR_GLORYBROADCAST_SEARCH_DO_ID", null);
			}
		}));
		//我的消息
		registerHandler(display.getMyMessage().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Platform.getInstance()
				.getEditorRegistry()
				.openEditor(
						MessageListConstants.EDITOR_MESSAGELIST_SEARCH,
						"EDITOR_MESSAGELIST_SEARCH_DO_ID", null);
			}
		}));
		//我的同事
		registerHandler(display.getStaffAnchor().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Platform.getInstance()
				.getEditorRegistry()
				.openEditor(
						ColleagueListConstants.EDITOR_COLLEAGUELIST_SEARCH,
						"EDITOR_COLLEAGUELIST_SEARCH_DO_ID", null);
			}
		}));
		// 密码修改
		registerHandler(display.getPassword().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RewardsPageClient client = new RewardsPageClient();
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										PasswordConstants.EDITOR_PASSWORD_SEARCH,
										"EDITOR_PASSWORD_SEARCH",client);
					}
				}));
		// 个人资料
		registerHandler(display.getStaffInfo().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
								Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										StaffInfoConstants.EDITOR_STAFFINFO_SEARCH,
										"EDITOR_PASSWORD_SEARCH",null);
					}
				}));
		// 设置
		registerHandler(display.getSettingAnchor().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Window.alert("待实现");
					}
				}));
		
		 //
				registerHandler(display.getAllReward().addClickHandler(
						new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								loadRewardALLPanel();
							}
						}));
				registerHandler(display.getMyWinReward().addClickHandler(
						new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								loadRewardSTAFFPanel();
							}
						}));
				registerHandler(display.getAllRewardItem().addClickHandler(
						new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								loadRewardItemALLPanel();
							}
						}));
				registerHandler(display.getEffortRewardItem().addClickHandler(
						new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								loadRewardItemSTAFFPanel();
							}
						}));

		
	}

	void init()
	{
		//加载员工信息....
		dispatchAsync.execute(new StaffInitRequest(sessionManager.getSession().getStaffId()),
				new AsyncCallback<StaffInitResponse>() {

					@Override
					public void onFailure(Throwable e) {
				
					}

					@Override
					public void onSuccess(StaffInitResponse resp) {
						display.setStaffName(resp.getName());
						display.setDeptName(resp.getDeptName());
						display.setIntegral(resp.getIntegral());
						display.setPhoto(resp.getPhoto());
						display.setStation(resp.getStation());
					}
				});
		//加载小橱窗控件
	
		GiftCriteria criteria = new GiftCriteria();
		criteria.setStatus(GiftStatus.SHELVES);
		// 查询参数....待添加
		dispatchAsync.execute(new SearchAwardShopRequest(criteria, sessionManager
				.getSession().getCorporationId(), sessionManager.getSession()
				.getUserRoles(), sessionManager.getSession().getToken()),
				new AsyncCallback<SearchAwardShopResponse>() {
					@Override
					public void onFailure(Throwable e) {
						Window.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchAwardShopResponse response) {

						List<GiftClient> giftList = response.getResult();
						int index = 0;
						Grid grid = new Grid(3, 2);

						// Add images to the grid
						int numRows = grid.getRowCount();
						int numColumns = grid.getColumnCount();
						for (int row = 0; row < numRows; row++) {
							for (int col = 0; col < numColumns; col++) {
								if (index < giftList.size()) {
									GiftClient clint = giftList.get(index);
									grid.setWidget(
											row,
											col,
											new SmallShopWindowWidget(clint.getId(),clint.getName(),clint.getIntegral()+"",clint.getPhoto()));
									index++;
								} else {
									grid.setWidget(row, col,
											new SmallShopWindowWidget(null,"","0",null));
								}
							}
						}

						// Return the panel
						grid.ensureDebugId("cwGrid");

						display.getSmaillShopWindow().clear();
						display.getSmaillShopWindow().add(grid);

					}

				});
		display.getMore().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Platform.getInstance()
				.getEditorRegistry()
				.openEditor(
						AwardShopListConstants.EDITOR_AWARDSHOPLIST_SEARCH,
						"EDITOR_AWARDSHOPLIST_SEARCH_DO_ID", null);
				
			}
		});
		
		//奖励小控件加载
		loadRewardSTAFFPanel();
		
		loadRewardItemSTAFFPanel();
		
	}
	
	private void loadRewardSTAFFPanel(){
		RewardsGridCriteria criteria = new RewardsGridCriteria();
		criteria.setThisAction("Rewards_STAFF_GETED");
		dispatchAsync.execute(new SearchRewardsGridRequest(criteria,sessionManager
				.getSession()),
				new AsyncCallback<SearchRewardsGridResponse>() {
					@Override
					public void onFailure(Throwable e) {
						Window.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchRewardsGridResponse response) {

						List<RewardsGridClient> giftList = response.getResult();
						int index = 0;
						Grid grid = new Grid(5, 1);

						// Add images to the grid
						int numRows = grid.getRowCount();
						int numColumns = grid.getColumnCount();
						for (int row = 0; row < numRows; row++) {
							for (int col = 0; col < numColumns; col++) {
								if (index < giftList.size()) {
									RewardsGridClient client = giftList.get(index);
									grid.setWidget(
											row,
											col,
											new SmallRewardWindowWidget(client.getRewardsId(),client.getRewardsName(),"Rewards_STAFF_GETED"));
									index++;
								} else {
									break;
								}
							}
						}

						// Return the panel
						grid.ensureDebugId("cwGridreward");

						display.getRewardPanel().clear();
						display.getRewardPanel().add(grid);
						
					}

				});
	}
	
	private void loadRewardALLPanel(){
		RewardsGridCriteria criteria = new RewardsGridCriteria();
		criteria.setThisAction("Rewards_ALL");
		dispatchAsync.execute(new SearchRewardsGridRequest(criteria,sessionManager
				.getSession()),
				new AsyncCallback<SearchRewardsGridResponse>() {
					@Override
					public void onFailure(Throwable e) {
						Window.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchRewardsGridResponse response) {

						List<RewardsGridClient> giftList = response.getResult();
						int index = 0;
						Grid grid = new Grid(5, 1);

						// Add images to the grid
						int numRows = grid.getRowCount();
						int numColumns = grid.getColumnCount();
						for (int row = 0; row < numRows; row++) {
							for (int col = 0; col < numColumns; col++) {
								if (index < giftList.size()) {
									RewardsGridClient client = giftList.get(index);
									grid.setWidget(
											row,
											col,
											new SmallRewardWindowWidget(client.getRewardsId(),client.getRewardsName(),"Rewards_ALL"));
									index++;
								} else {
									break;
								}
							}
						}

						// Return the panel
						grid.ensureDebugId("cwGridreward");

						display.getRewardPanel().clear();
						display.getRewardPanel().add(grid);
						
					}

				});
	}
	
	private void loadRewardItemALLPanel(){
		RewardsGridCriteria criteria = new RewardsGridCriteria();
		criteria.setThisAction("RewardsItem_ALL");
		// 查询参数....待添加
		dispatchAsync.execute(new SearchRewardsGridRequest(criteria,sessionManager
				.getSession()),
				new AsyncCallback<SearchRewardsGridResponse>() {
					@Override
					public void onFailure(Throwable e) {
						Window.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchRewardsGridResponse response) {

						List<RewardsGridClient> giftList = response.getResult();
						int index = 0;
						Grid grid = new Grid(5, 1);

						// Add images to the grid
						int numRows = grid.getRowCount();
						int numColumns = grid.getColumnCount();
						for (int row = 0; row < numRows; row++) {
							for (int col = 0; col < numColumns; col++) {
								if (index < giftList.size()) {
									RewardsGridClient client = giftList.get(index);
									grid.setWidget(
											row,
											col,
											new SmallRewardItemWindowWidget(client.getRewardsItemId(),client.getRewardsItemName(),client.getAwardAmt(),client.getRewardsItemPhoto(),"RewardsItem_ALL"));
									index++;
								} else {
									break;
								}
							}
						}

						// Return the panel
						grid.ensureDebugId("cwGridreward");

						display.getRewardItemPanel().clear();
						display.getRewardItemPanel().add(grid);

					}

				});
	}
	
	private void loadRewardItemSTAFFPanel(){
		RewardsGridCriteria criteria = new RewardsGridCriteria();
		criteria.setThisAction("RewardsItem_STAFF_RUSH");
		// 查询参数....待添加
		dispatchAsync.execute(new SearchRewardsGridRequest(criteria,sessionManager
				.getSession()),
				new AsyncCallback<SearchRewardsGridResponse>() {
					@Override
					public void onFailure(Throwable e) {
						Window.alert(e.getMessage());
					}

					@Override
					public void onSuccess(SearchRewardsGridResponse response) {

						List<RewardsGridClient> giftList = response.getResult();
						int index = 0;
						Grid grid = new Grid(5, 1);

						// Add images to the grid
						int numRows = grid.getRowCount();
						int numColumns = grid.getColumnCount();
						for (int row = 0; row < numRows; row++) {
							for (int col = 0; col < numColumns; col++) {
								if (index < giftList.size()) {
									RewardsGridClient client = giftList.get(index);
									grid.setWidget(
											row,
											col,
											new SmallRewardItemWindowWidget(client.getRewardsItemId(),client.getRewardsItemName(),client.getAwardAmt(),client.getRewardsItemPhoto(),"RewardsItem_STAFF_RUSH"));
									index++;
								} else {
									break;
								}
							}
						}

						// Return the panel
						grid.ensureDebugId("cwGridreward");

						display.getRewardItemPanel().clear();
						display.getRewardItemPanel().add(grid);

					}

				});
	}
	
	

	
	public StaffDisplay getDisplay() {
		return display;
	}

}
