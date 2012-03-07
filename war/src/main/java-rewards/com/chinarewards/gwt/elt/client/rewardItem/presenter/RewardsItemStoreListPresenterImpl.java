package com.chinarewards.gwt.elt.client.rewardItem.presenter;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.budget.model.DepartmentVo;
import com.chinarewards.gwt.elt.client.budget.request.InitDepartmentRequest;
import com.chinarewards.gwt.elt.client.budget.request.InitDepartmentResponse;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.dataprovider.RewardsItemStoreListViewAdapter;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.rewardItem.plugin.RewardsItemConstants;
import com.chinarewards.gwt.elt.client.rewardItem.request.ActivationRewardsItemStoreRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.ActivationRewardsItemStroeResponse;
import com.chinarewards.gwt.elt.client.rewardItem.request.DeleteRewardsItemRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.DeleteRewardsItemResponse;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemCriteria;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.ui.HyperLinkCell;
import com.chinarewards.gwt.elt.client.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.widget.EltNewPager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager.TextLocation;
import com.chinarewards.gwt.elt.client.widget.GetValue;
import com.chinarewards.gwt.elt.client.widget.ListCellTable;
import com.chinarewards.gwt.elt.client.widget.Sorting;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.client.win.confirm.ConfirmHandler;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

/**
 * 
 * 
 * @author lw
 * @since 0.2.0 2011-12-23
 */
public class RewardsItemStoreListPresenterImpl extends
		BasePresenter<RewardsItemStoreListPresenter.RewardsItemStoreListDisplay>
		implements RewardsItemStoreListPresenter {

	EltNewPager pager;
	ListCellTable<RewardsItemClient> resultTable;
	RewardsItemStoreListViewAdapter listViewAdapter;

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
    final Win win;
	DateTimeFormat dateFormatAll = DateTimeFormat.getFormat(ViewConstants.date_format_all);
	DateTimeFormat dateFormat = DateTimeFormat.getFormat(ViewConstants.date_format);
	private final BreadCrumbsPresenter breadCrumbs;

	// 是否部门管理员
	boolean isHr = false;
	boolean isDepartmentManager = false;

	@Inject

	public RewardsItemStoreListPresenterImpl(EventBus eventBus,Win win,
			RewardsItemStoreListDisplay display, DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager,
			BreadCrumbsPresenter breadCrumbs) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
        this.breadCrumbs = breadCrumbs;
	}

	@Override
	public void bind() {
		init();
		buildTable();
		breadCrumbs.loadListPage();
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		registerHandler(display.getSearchClick().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						doSearch();
					}
				}));
		registerHandler(display.getAddBut().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								RewardsItemConstants.EDITOR_REWARDSITEM_ADD,
								RewardsItemConstants.EDITOR_REWARDSITEMSTORE, RewardsItemConstants.EDITOR_REWARDSITEMSTORE);
			}
		}));
	}

	private void buildTable() {
		resultTable = new ListCellTable<RewardsItemClient>();
		initTableColumns();
		pager = new EltNewPager(TextLocation.CENTER);
		pager.setDisplay(resultTable);
		resultTable.setWidth(ViewConstants.page_width);
		resultTable.setPageSize(ViewConstants.per_page_number_in_dialog);
		listViewAdapter = new RewardsItemStoreListViewAdapter(dispatch,errorHandler, sessionManager,display);
		listViewAdapter.addDataDisplay(resultTable);
		resultTable.getColumn(0).setCellStyleNames("divTextLeft");
		display.getDataContainer().clear();
		display.getDataContainer().add(resultTable);
		display.getDataPager().add(pager);
	}

	// init department name
	private void init() {
		
		initDeparts();
	}
	private void initDeparts(){
		   dispatch.execute(new InitDepartmentRequest(sessionManager.getSession()),
					new AsyncCallback<InitDepartmentResponse>() {
			          	@Override
						public void onFailure(Throwable arg0) {
							errorHandler.alert("查询部门出错!");
							
						}

						@Override
						public void onSuccess(InitDepartmentResponse response) {
							 List<DepartmentVo> list = response.getResult();
							 Map<String, String> map = new HashMap<String, String>();
							 DepartmentVo vo = new DepartmentVo();
							 if(list.size()>0){
								 for(int i=0;i<list.size();i++){
									   vo = list.get(i);
									   map.put(vo.getId(), vo.getDepartmentName());
								 }
							 }
								
								display.initDepart(map);
							
						}

					});
			
	   }
	private void initTableColumns() {
		Sorting<RewardsItemClient> ref = new Sorting<RewardsItemClient>() {
			@Override
			public void sortingCurrentPage(
					Comparator<RewardsItemClient> comparator) {
				listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);
			}
		};

		resultTable.addColumn("奖项名称", new HyperLinkCell(),
				new GetValue<RewardsItemClient, String>() {
					@Override
					public String getValue(RewardsItemClient object) {
						return object.getName();
					}
				}, new FieldUpdater<RewardsItemClient, String>() {
					@Override
					public void update(int index, RewardsItemClient object,
							String value) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								RewardsItemConstants.EDITOR_REWARDSITEM_View,
								RewardsItemConstants.EDITOR_REWARDSITEMSTORE, object);
					
					}
				}, ref, "name");
		resultTable.addColumn("自动", new TextCell(),
				new GetValue<RewardsItemClient, String>() {
					@Override
					public String getValue(RewardsItemClient object) {
						return (object.isAuto()) ? "是" : "否";
					}
				});
		resultTable.addColumn("频率", new TextCell(),
				new GetValue<RewardsItemClient, String>() {
					@Override
					public String getValue(RewardsItemClient object) {
						return (object.isPeriodEnable()) ? "有" : "无";
					}
				});

		resultTable.addColumn("创建时间", new DateCell(dateFormatAll),
				new GetValue<RewardsItemClient, Date>() {
					@Override
					public Date getValue(RewardsItemClient object) {
						return object.getCreateAt();
					}
				});



		resultTable.addColumn("应用次数", new TextCell(),
				new GetValue<RewardsItemClient, String>() {
					@Override
					public String getValue(RewardsItemClient rewards) {
						return rewards.getDegree() + "";
					}
				}, ref, "nexRunBatchTime");
		resultTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<RewardsItemClient, String>() {
					@Override
					public String getValue(RewardsItemClient arg0) {
						return "修改";
					}
				}, new FieldUpdater<RewardsItemClient, String>() {
					@Override
					public void update(int index, final RewardsItemClient object,	String value) {
						
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								RewardsItemConstants.EDITOR_REWARDSITEM_ADD
								,RewardsItemConstants.EDITOR_REWARDSITEMSTORE
								, object);
					

					}
				});
		resultTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<RewardsItemClient, String>() {
					@Override
					public String getValue(RewardsItemClient arg0) {
						return "查看详细";
					}
				}, new FieldUpdater<RewardsItemClient, String>() {
					@Override
					public void update(int index, RewardsItemClient object,
							String value) {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										RewardsItemConstants.EDITOR_REWARDSITEM_View,
										RewardsItemConstants.EDITOR_REWARDSITEMSTORE, object);
					}
				});
		resultTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<RewardsItemClient, String>() {
					@Override
					public String getValue(RewardsItemClient arg0) {
						return "删除";
					}
				}, new FieldUpdater<RewardsItemClient, String>() {
					@Override
					public void update(int index, final RewardsItemClient object,	String value) {
						
						{
							win.confirm("删除提示", "确定删除吗？", new ConfirmHandler() {
								
								@Override
								public void confirm() {
									dispatch.execute(new DeleteRewardsItemRequest(object.getId(),true,sessionManager.getSession()),
											new AsyncCallback<DeleteRewardsItemResponse>() {

												@Override
												public void onFailure(Throwable t) {
													win.alert(t.getMessage());
												}

												@Override
												public void onSuccess(DeleteRewardsItemResponse resp) {
													win.alert(resp.getName() + "已删除!");
													doSearch();
												}
											});
								}
							});
							
						}
					}
				});

		resultTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<RewardsItemClient, String>() {
					@Override
					public String getValue(RewardsItemClient arg0) {
							return "我要用这个";
		
					}
				}, new FieldUpdater<RewardsItemClient, String>() {
					@Override
					public void update(int index,
							final RewardsItemClient object, String value) {
		

							win.confirm("提示", "确定应用？", new ConfirmHandler() {
								@Override
								public void confirm() {
									activationRewardItemStore(object.getId());
								}
							});

					
					}
				});

	}

	public void activationRewardItemStore(String rewardsItemStroeId) {

		dispatch.execute(new ActivationRewardsItemStoreRequest(rewardsItemStroeId,
				sessionManager.getSession().getToken()),
				new AsyncCallback<ActivationRewardsItemStroeResponse>() {

					@Override
					public void onFailure(Throwable t) {
						win.alert(t.getMessage());
					}

					@Override
					public void onSuccess(ActivationRewardsItemStroeResponse resp) {
						win.alert(resp.getName() + "--应用成功!");
						doSearch();
					}
				});
	}

	public void deleteRewardItem(String rewardsItemId) {

		dispatch.execute(new DeleteRewardsItemRequest(rewardsItemId,
				true,sessionManager.getSession()),
				new AsyncCallback<DeleteRewardsItemResponse>() {

					@Override
					public void onFailure(Throwable t) {
						win.alert(t.getMessage());
					}

					@Override
					public void onSuccess(DeleteRewardsItemResponse resp) {
						win.alert(resp.getName() + "已删除!");
						doSearch();
					}
				});
	}

	public void doSearch() {
		RewardsItemCriteria criteria = new RewardsItemCriteria();
		criteria.setDepartmentId(display.getDepart());
		criteria.setSubDepartmentChoose(display.getChooseSubDepartment());
		criteria.setName(display.getSearchName().getValue());
		criteria.setCreateTime(display.getCreateTime().getValue());
		criteria.setCreateTimeEnd(display.getCreateTimeEnd().getValue());
		criteria.setEnabled("false");
		listViewAdapter.setCriteria(criteria);
		listViewAdapter.reloadToFirstPage();
	}
}
