package com.chinarewards.gwt.elt.client.orderHistory.presenter;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.gift.model.GiftClient;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.order.model.OrderSearchVo;
import com.chinarewards.gwt.elt.client.order.model.OrderStatus;
import com.chinarewards.gwt.elt.client.order.request.DeleteOrderRequest;
import com.chinarewards.gwt.elt.client.order.request.DeleteOrderResponse;
import com.chinarewards.gwt.elt.client.orderHistory.dataprovider.OrderHistoryDataAdapter;
import com.chinarewards.gwt.elt.client.orderHistory.plugin.OrderHistoryConstants;
import com.chinarewards.gwt.elt.client.orderHistory.presenter.OrderHistoryPresenter.OrderHistoryDisplay;
import com.chinarewards.gwt.elt.client.orderHistory.request.OrderHistoryViewRequest;
import com.chinarewards.gwt.elt.client.orderHistory.request.OrderHistoryViewResponse;
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
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class OrderHistoryPresenterImpl extends
		BasePresenter<OrderHistoryDisplay> implements OrderHistoryPresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;

	EltNewPager pager;
	ListCellTable<OrderSearchVo> cellTable;
	OrderHistoryDataAdapter listViewAdapter;
	
	DateTimeFormat dateFormatAll = DateTimeFormat
			.getFormat(ViewConstants.date_format_all);

	@Inject
	public OrderHistoryPresenterImpl(EventBus eventBus, DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager,
			 OrderHistoryDisplay display,
			Win win) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
		
	}

	@Override
	public void bind() {
		init();
//		breadCrumbs.loadListPage();
//		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		registerHandler(display.getSearchBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						doSearch();
					}
				}));

	}

	private void init() {
		initStatus();

		buildTable();
		doSearch();
	}

	private void initStatus() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("INITIAL", "未付积分");
		map.put("NUSHIPMENTS", " 待发货");
		map.put("SHIPMENTS", "已发货");
		map.put("AFFIRM", "确认发货");
		map.put("ERRORORDER", "问题定单");
		display.initOrderStatus(map);
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
		if (!StringUtil.isEmpty(display.getStatus()))
			criteria.setStatus(OrderStatus.valueOf(display.getStatus()));

		criteria.setGiftvo(giftVo);
		criteria.setExchangeDate(display.getCreateTime().getValue());
		criteria.setExchangeDateEnd(display.getCreateTimeEnd().getValue());
		listViewAdapter = new OrderHistoryDataAdapter(dispatch, criteria,
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
		// cellTable.addColumn("订单编号", new TextCell(),
		// new GetValue<OrderSearchVo, String>() {
		// @Override
		// public String getValue(OrderSearchVo order) {
		// return order.getOrderCode();
		// }
		// }, ref, "orderCode");

		cellTable.addColumn("礼品名称", new TextCell(),
				new GetValue<OrderSearchVo, String>() {
					@Override
					public String getValue(OrderSearchVo order) {
						return order.getGiftvo().getName();
					}
				}, ref, "name");

		cellTable.addColumn("下单时间", new DateCell(dateFormatAll),
				new GetValue<OrderSearchVo, Date>() {
					@Override
					public Date getValue(OrderSearchVo object) {
						return object.getExchangeDate();
					}
				}, ref, "exchangeDate");

		cellTable.addColumn("消费积分", new TextCell(),
				new GetValue<OrderSearchVo, String>() {
					@Override
					public String getValue(OrderSearchVo order) {
						return order.getIntegral() + "";
					}
				}, ref, "integral");
		cellTable.addColumn("处理状态", new TextCell(),
				new GetValue<OrderSearchVo, String>() {
					@Override
					public String getValue(OrderSearchVo order) {
						return order.getStatus().getDisplayName();
					}
				}, ref, "status");

		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<OrderSearchVo, String>() {
					@Override
					public String getValue(OrderSearchVo order) {
						return "查看详细";
					}
				}, new FieldUpdater<OrderSearchVo, String>() {
					@Override
					public void update(int index, final OrderSearchVo order,
							String value) {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										OrderHistoryConstants.EDITOR_ORDERHISTORY_VIEW,
										OrderHistoryConstants.EDITOR_ORDERHISTORY_VIEW
												+ order.getId(), order);
					}
				});
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<OrderSearchVo, String>() {
					@Override
					public String getValue(OrderSearchVo order) {
						if(order.getStatus()==OrderStatus.SHIPMENTS)
						    return "确认收货";
						else
							return "";
					}
				}, new FieldUpdater<OrderSearchVo, String>() {
					@Override
					public void update(int index, final OrderSearchVo object,	String value) {
						
							win.confirm("操作提示", "确认收货吗？", new ConfirmHandler() {
								
								@Override
								public void confirm() {
									 String status =OrderStatus.AFFIRM+"";
									 dispatch.execute(new OrderHistoryViewRequest(object.getId(),sessionManager.getSession().getToken(),status),
												new AsyncCallback<OrderHistoryViewResponse>() {

													@Override
													public void onFailure(Throwable t) {
														errorHandler.alert("操作失败");												}

													@Override
													public void onSuccess(OrderHistoryViewResponse resp) {
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
						if(order.getStatus()==OrderStatus.INITIAL)
						    return "付积分";
						else
							return "";
					}
				}, new FieldUpdater<OrderSearchVo, String>() {
					@Override
					public void update(int index, final OrderSearchVo object,	String value) {
						
							win.confirm("操作提示", "确认付积分吗？", new ConfirmHandler() {
								
								@Override
								public void confirm() {
									 String status =OrderStatus.NUSHIPMENTS+"";
										dispatch.execute(new OrderHistoryViewRequest(object.getId(),sessionManager.getSession().getToken(),status),
											new AsyncCallback<OrderHistoryViewResponse>() {

												@Override
												public void onFailure(Throwable t) {
													errorHandler.alert("操作失败");												}

												@Override
												public void onSuccess(OrderHistoryViewResponse resp) {
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
					public String getValue(OrderSearchVo gift) {
						if (gift.getStatus() != null
								&& gift.getStatus() == OrderStatus.INITIAL)
							return "取消定单";

						else
							return "";
					}
				}, new FieldUpdater<OrderSearchVo, String>() {
					@Override
					public void update(int index, final OrderSearchVo o,
							String value) {
						String msgStr = "";
						if (o.getStatus() != null
								&& o.getStatus() == OrderStatus.INITIAL)
							msgStr = "确定取消定单?";
						win.confirm("提示", msgStr, new ConfirmHandler() {
							@Override
							public void confirm() {
								deleteOrder(o.getId());
							}
						});
					}
				});

	}

	public void deleteOrder(String orderId) {

		dispatch.execute(
				new DeleteOrderRequest(orderId, sessionManager.getSession()),
				new AsyncCallback<DeleteOrderResponse>() {

					@Override
					public void onFailure(Throwable t) {
						win.alert(t.getMessage());
					}

					@Override
					public void onSuccess(DeleteOrderResponse resp) {
						if (resp.getTotal() != null
								&& !resp.getTotal().equals(""))
							win.alert("订单取消成功!");
						else
							win.alert("订单取消失败!");

						doSearch();
					}
				});
	}

}
