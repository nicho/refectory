package com.chinarewards.gwt.elt.client.order.presenter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.dataprovider.OrderListViewAdapter;
import com.chinarewards.gwt.elt.client.gift.model.GiftClient;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.order.model.OrderSearchVo;
import com.chinarewards.gwt.elt.client.order.model.OrderStatus;
import com.chinarewards.gwt.elt.client.order.plugin.OrderViewConstants;
import com.chinarewards.gwt.elt.client.order.presenter.OrderListPresenter.OrderListDisplay;
import com.chinarewards.gwt.elt.client.order.request.OrderViewRequest;
import com.chinarewards.gwt.elt.client.order.request.OrderViewResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.ui.HyperLinkCell;
import com.chinarewards.gwt.elt.client.widget.EltNewPager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager.TextLocation;
import com.chinarewards.gwt.elt.client.widget.GetValue;
import com.chinarewards.gwt.elt.client.widget.ListCellTable;
import com.chinarewards.gwt.elt.client.widget.Sorting;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.client.win.confirm.ConfirmHandler;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class OrderListPresenterImpl extends BasePresenter<OrderListDisplay>
		implements OrderListPresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;

	EltNewPager pager;
	ListCellTable<OrderSearchVo> cellTable;
	OrderListViewAdapter listViewAdapter;
	private final BreadCrumbsPresenter breadCrumbs;
	@Inject
	public OrderListPresenterImpl(EventBus eventBus, DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager,BreadCrumbsPresenter breadCrumbs,
			OrderListDisplay display, Win win) {
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
		breadCrumbs.loadListPage();
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		registerHandler(display.getSearchBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						doSearch();
					}
				}));
		
			}

	private void init() {
		initStatus();
		initSource();
		buildTable();
		doSearch();
	}
   private void initStatus(){
	   Map<String, String> map = new HashMap<String, String>();
		map.put("INITIAL", "未付积分");
		map.put("NUSHIPMENTS", " 待发货");
		map.put("SHIPMENTS", "已发货");
		map.put("AFFIRM", "确认发货");
		map.put("ERRORORDER", "问题定单");
		display.initOrderStatus(map);
   }
   private void initSource(){
	   Map<String, String> map = new HashMap<String, String>();
		map.put("inner", "内部直接提供");
		map.put("outter", "外部货品公司提供");
		
		display.initOrderSource(map);
   }
	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<OrderSearchVo>();

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

	private void doSearch() {
		OrderSearchVo criteria = new OrderSearchVo();
		
		GiftClient giftVo = new GiftClient();
		if (!StringUtil.isEmpty(display.getKeyName().getValue()))
			criteria.setName(display.getKeyName().getValue());
		if (!StringUtil.isEmpty(display.getStatus()))
			criteria.setStatus(OrderStatus.valueOf(display.getStatus()));
		
		if (!StringUtil.isEmpty(display.getSource()))
			giftVo.setSource(display.getSource());
		criteria.setGiftvo(giftVo);
       
		listViewAdapter = new OrderListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager, display);
		listViewAdapter.addDataDisplay(cellTable);
	}

	private void initTableColumns() {
		Sorting<OrderSearchVo> ref = new Sorting<OrderSearchVo>() {
			@Override
			public void sortingCurrentPage(Comparator<OrderSearchVo> comparator) {
				// listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);

			}
		};
		cellTable.addColumn("订单编号", new TextCell(),
				new GetValue<OrderSearchVo, String>() {
					@Override
					public String getValue(OrderSearchVo order) {
						return order.getOrderCode();
					}
				}, ref, "orderCode");

		cellTable.addColumn("礼品名称", new TextCell(),
				new GetValue<OrderSearchVo, String>() {
					@Override
					public String getValue(OrderSearchVo order) {
						return order.getGiftvo().getName();
					}
				}, ref, "name");

		
		cellTable.addColumn("数量", new TextCell(),
				new GetValue<OrderSearchVo, String>() {
					@Override
					public String getValue(OrderSearchVo order) {
						return order.getAmount()+"";
					}
				}, ref, "amount");
		cellTable.addColumn("兑换积分", new TextCell(),
				new GetValue<OrderSearchVo, String>() {
					@Override
					public String getValue(OrderSearchVo order) {
						return order.getIntegral()+"";
					}
				}, ref, "integral");
		cellTable.addColumn("兑换员工", new TextCell(),
				new GetValue<OrderSearchVo, String>() {
					@Override
					public String getValue(OrderSearchVo order) {
						return order.getName() + "";
					}
				}, ref, "name");
		cellTable.addColumn("处理状态", new TextCell(),
				new GetValue<OrderSearchVo, String>() {
					@Override
					public String getValue(OrderSearchVo order) {
						return order.getStatus().getDisplayName();
					}
				}, ref, "status");
		cellTable.addColumn("礼品来源", new TextCell(),
				new GetValue<OrderSearchVo, String>() {
					@Override
					public String getValue(OrderSearchVo order) {
						if (order.getGiftvo().getSource() != null) {
							if (StringUtil.trim(order.getGiftvo().getSource()).equals("inner")) {
								return "内部直接提供";
							}
							if (StringUtil.trim(order.getGiftvo().getSource()).equals("outter")) {
								return "外部货品公司提供";
							}
						}
						return "";
					}
				});   
		

		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<OrderSearchVo, String>() {
					@Override
					public String getValue(OrderSearchVo order) {
						return "查看详细";
					}
				}, new FieldUpdater<OrderSearchVo, String>() {
					@Override
					public void update(int index, final OrderSearchVo order,String value) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								OrderViewConstants.EDITOR_ORDERVIEW_SEARCH,
								OrderViewConstants.EDITOR_ORDERVIEW_SEARCH+ order.getId(), order);
					}
				});
		
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<OrderSearchVo, String>() {
					@Override
					public String getValue(OrderSearchVo order) {
						if(order.getStatus()==OrderStatus.NUSHIPMENTS)
						    return "发货";
						else
							return "";
					}
				}, new FieldUpdater<OrderSearchVo, String>() {
					@Override
					public void update(int index, final OrderSearchVo object,	String value) {
						
							win.confirm("操作提示", "确认发货吗？", new ConfirmHandler() {
								
								@Override
								public void confirm() {
									 String status =OrderStatus.SHIPMENTS+"";
										dispatch.execute(new OrderViewRequest(object.getId(),sessionManager.getSession().getToken(),status),
											new AsyncCallback<OrderViewResponse>() {

												@Override
												public void onFailure(Throwable t) {
													errorHandler.alert("操作失败");												}

												@Override
												public void onSuccess(OrderViewResponse resp) {
													win.alert("操作成功!");
													doSearch();
												}
											});
								}
							});
											
					}
				});
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<OrderSearchVo, String>() {
					@Override
					public String getValue(OrderSearchVo order) {
						if(order.getStatus()==OrderStatus.NUSHIPMENTS)
						    return "退回";
						else
							return "";
					}
				}, new FieldUpdater<OrderSearchVo, String>() {
					@Override
					public void update(int index, final OrderSearchVo object,	String value) {
						
							win.confirm("操作提示", "确认退回吗？", new ConfirmHandler() {
								
								@Override
								public void confirm() {
									 String status =OrderStatus.ERRORORDER+"";
										dispatch.execute(new OrderViewRequest(object.getId(),sessionManager.getSession().getToken(),status),
											new AsyncCallback<OrderViewResponse>() {

												@Override
												public void onFailure(Throwable t) {
													errorHandler.alert("操作失败");												}

												@Override
												public void onSuccess(OrderViewResponse resp) {
													win.alert("操作成功!");
													doSearch();
												}
											});
								}
							});
											
					}
				});
			
	      }

	@Override
	public void getBoxOrder(String status) {//收件箱查看和操作时传的参数
		init();
		display.setStatus(status);
		
		doSearch();
		
	}
	


    }
