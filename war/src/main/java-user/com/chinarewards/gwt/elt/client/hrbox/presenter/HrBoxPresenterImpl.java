package com.chinarewards.gwt.elt.client.hrbox.presenter;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.awardShop.plugin.AwardShopListConstants;
import com.chinarewards.gwt.elt.client.box.request.UserBoxRequest;
import com.chinarewards.gwt.elt.client.box.request.UserBoxResponse;
import com.chinarewards.gwt.elt.client.budget.model.CorpBudgetVo;
import com.chinarewards.gwt.elt.client.budget.model.DepBudgetVo;
import com.chinarewards.gwt.elt.client.budget.request.InitCorpBudgetRequest;
import com.chinarewards.gwt.elt.client.budget.request.InitCorpBudgetResponse;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.presenter.DockPresenter;
import com.chinarewards.gwt.elt.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.gift.model.GiftClient;
import com.chinarewards.gwt.elt.client.hrbox.presenter.HrBoxPresenter.HrBoxDisplay;
import com.chinarewards.gwt.elt.client.hrbox.request.HrBoxRewardsRequest;
import com.chinarewards.gwt.elt.client.hrbox.request.HrBoxRewardsResponse;
import com.chinarewards.gwt.elt.client.hrbox.view.RewardWindowWidget;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.order.model.OrderStatus;
import com.chinarewards.gwt.elt.client.order.request.OrderBoxRequest;
import com.chinarewards.gwt.elt.client.order.request.OrderBoxResponse;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsCriteria;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsCriteria.RewardsStatus;
import com.chinarewards.gwt.elt.client.rewards.plugin.RewardsListConstants;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.ui.HyperLinkCell;
import com.chinarewards.gwt.elt.client.widget.EltNewPager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager.TextLocation;
import com.chinarewards.gwt.elt.client.widget.GetValue;
import com.chinarewards.gwt.elt.client.widget.ListCellTable;
import com.chinarewards.gwt.elt.client.widget.Sorting;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.model.rewards.RewardPageType;
import com.chinarewards.gwt.elt.model.rewards.RewardsPageClient;
import com.chinarewards.gwt.elt.util.DateTool;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.inject.Inject;

public class HrBoxPresenterImpl extends BasePresenter<HrBoxDisplay>
		implements HrBoxPresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;
	String corpBudgetId="";
    final MenuProcessor menuProcessor;
	final DockPresenter dockPresenter;
	ListCellTable<DepBudgetVo> cellTable;
	DepBudgetListAdapter listViewAdapter;
	EltNewPager pager;
	@Inject
	public HrBoxPresenterImpl(EventBus eventBus, DispatchAsync dispatch,MenuProcessor menuProcessor, DockPresenter dockPresenter,
			ErrorHandler errorHandler, SessionManager sessionManager,HrBoxDisplay display, Win win) {
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
		 buildTable();
		 initYear();	
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
		RewardsCriteria criteria = new RewardsCriteria();
		criteria.setStatus(RewardsStatus.REWARDED);//颁奖完
		criteria.setLastMonth(DateTool.addSomeMonth(new Date(), -1));//上个月的时间
		dispatch.execute(new HrBoxRewardsRequest( criteria,sessionManager.getSession()),
				new AsyncCallback<HrBoxRewardsResponse>() {
					@Override
					public void onFailure(Throwable e) {
						Window.alert(e.getMessage());
					}

					@Override
					public void onSuccess(HrBoxRewardsResponse response) {

						List<RewardsClient> rewardsList = response.getResult();
						int index = 0;
						Grid grid = new Grid(2, 3);

						// Add images to the grid
						int numRows = grid.getRowCount();
						int numColumns = grid.getColumnCount();
						for (int row = 0; row < numRows; row++) {
							for (int col = 0; col < numColumns; col++) {
								if (index < rewardsList.size()) {
									RewardsClient clint = rewardsList.get(index);
									grid.setWidget(row,	col,new RewardWindowWidget(clint.getId(),clint.getName()));
									index++;
									if(index == 5)
										break;
								} else {
									grid.setWidget(row, col,new RewardWindowWidget(null,"无数据"));
								}
							}
						}

						// Return the panel
						grid.ensureDebugId("cwGrid");
						display.getRewardWindow().clear();
						display.getRewardWindow().add(grid);

					}

				});
		  display.getView().addClickHandler(new ClickHandler() {//更多
		   @Override
			public void onClick(ClickEvent event) {
			   dockPresenter.getDisplay().changeTopMenu("Reward");
				dockPresenter.getDisplay().setMenuTitle("应用奖项");
				menuProcessor.initrender(dockPresenter.getDisplay().getMenu(), "Reward");

			//	eventBus.fireEvent(new MenuClickEvent(menuProcessor.getMenuItem(RewardsListConstants.MENU_REWARDSLIST_SEARCH)));
				RewardsPageClient rpc=new RewardsPageClient();
				rpc.setTitleName("已颁奖历史");
				rpc.setPageType(RewardPageType.DETAILSOFAWARDPAGE);
				Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								RewardsListConstants.EDITOR_REWARDSLIST_SEARCH,
								"EDITOR_REWARDSLIST_SEARCH_DO_ID", rpc);
				menuProcessor.changItemColor("已颁奖历史");
				
			}
		});

	}

	@Override
	public void initHrBox() {
		
		{
			//要发货的
			 dispatch.execute(new OrderBoxRequest(sessionManager.getSession(),OrderStatus.NUSHIPMENTS+""),
					new AsyncCallback<OrderBoxResponse>() {
						@Override
						public void onFailure(Throwable arg0) {
							errorHandler.alert("查询出错!");
							
						}
						@Override
						public void onSuccess(OrderBoxResponse response) {
							
							display.setOrder(response.getTotal()+"");
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
							display.setMessage( response.getWeek());
							display.setHrSend(response.getTotal()+"");
						}

					});
		    }		
	}
	 private void initYear(){
		   
		   dispatch.execute(new InitCorpBudgetRequest(sessionManager.getSession()),
					new AsyncCallback<InitCorpBudgetResponse>() {
			          	@Override
						public void onFailure(Throwable arg0) {
							errorHandler.alert("查询财年周期出错!");
						}
						@Override
						public void onSuccess(InitCorpBudgetResponse response) {
							 List<CorpBudgetVo> list = response.getResult();
							 Map<String, String> map = new HashMap<String, String>();
							 map.clear();
							 CorpBudgetVo vo = new CorpBudgetVo();
							 if(list.size()>0){
								 for(int i=0;i<list.size();i++){
									   vo = list.get(i);
									   map.put(vo.getId(), vo.getBudgetTitle());
								 }
								   vo = list.get(0);
								    corpBudgetId = vo.getId();
								  	DepBudgetVo criteria = new DepBudgetVo();
									criteria.setCorpBudgetId(corpBudgetId);
									listViewAdapter = new DepBudgetListAdapter(dispatch, criteria,errorHandler, sessionManager, display);
									listViewAdapter.addDataDisplay(cellTable);

							 }
								
													
						}

					});
		 
	   }
	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<DepBudgetVo>();

		initTableColumns();
		pager = new EltNewPager(TextLocation.CENTER);
		pager.setDisplay(cellTable);
		cellTable.setWidth(ViewConstants.page_width);
		cellTable.setPageSize(ViewConstants.per_page_number_in_dialog);

		display.getResultPanel().clear();
		display.getResultPanel().add(cellTable);
		display.getResultpage().clear();
		display.getResultpage().add(pager);
	}

	

	private void initTableColumns() {
		Sorting<DepBudgetVo> ref = new Sorting<DepBudgetVo>() {
			@Override
			public void sortingCurrentPage(Comparator<DepBudgetVo> comparator) {
				// listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);

			}
		};
		cellTable.addColumn("部门名称", new TextCell(),
				new GetValue<DepBudgetVo, String>() {
					@Override
					public String getValue(DepBudgetVo budgetVo) {
						return budgetVo.getDepartmentName();
					}
				}, ref, "departmentId");
		cellTable.addColumn("总人数", new TextCell(),
				new GetValue<DepBudgetVo, String>() {
					@Override
					public String getValue(DepBudgetVo budgetVo) {
						return budgetVo.getPeople()+"";
					}
				});

		cellTable.addColumn("总积分", new TextCell(),
				new GetValue<DepBudgetVo, String>() {
					@Override
					public String getValue(DepBudgetVo budgetVo) {
						return budgetVo.getBudgetIntegral()+"";
					}
				}, ref, "budgetIntegral");

		
		
		cellTable.addColumn("已用积分", new TextCell(),
				new GetValue<DepBudgetVo, String>() {
					@Override
					public String getValue(DepBudgetVo budgetVo) {
						return budgetVo.getUseIntegeral()+"";
					}
				}, ref, "useIntegeral");
		
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<DepBudgetVo, String>() {
					@Override
					public String getValue(DepBudgetVo arg0) {
						return "颁发历史";
					}
				}, new FieldUpdater<DepBudgetVo, String>() {
					@Override
					public void update(int index, DepBudgetVo giftClient,String value) {
						dockPresenter.getDisplay().changeTopMenu("Reward");
						dockPresenter.getDisplay().setMenuTitle("应用奖项");
						menuProcessor.initrender(dockPresenter.getDisplay().getMenu(), "Reward");

					//	eventBus.fireEvent(new MenuClickEvent(menuProcessor.getMenuItem(RewardsListConstants.MENU_REWARDSLIST_SEARCH)));
						RewardsPageClient rpc=new RewardsPageClient();
						rpc.setTitleName("已颁奖历史");
						rpc.setPageType(RewardPageType.DETAILSOFAWARDPAGE);
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										RewardsListConstants.EDITOR_REWARDSLIST_SEARCH,
										"EDITOR_REWARDSLIST_SEARCH_DO_ID", rpc);
						menuProcessor.changItemColor("已颁奖历史");
					}
				});
			
	      }
       
}
