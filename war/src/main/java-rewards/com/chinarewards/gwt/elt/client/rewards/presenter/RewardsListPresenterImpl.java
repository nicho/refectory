package com.chinarewards.gwt.elt.client.rewards.presenter;

import java.util.Comparator;
import java.util.Date;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.awardReward.plugin.AwardRewardConstants;
import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.dataprovider.RewardsListViewAdapter;
import com.chinarewards.gwt.elt.client.detailsOfAward.plugin.DetailsOfAwardConstants;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.nominate.plugin.NominateConstants;
import com.chinarewards.gwt.elt.client.rewards.model.JudgeModelClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsCriteria;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsCriteria.RewardsStatus;
import com.chinarewards.gwt.elt.client.rewards.presenter.RewardsListPresenter.RewardsListDisplay;
import com.chinarewards.gwt.elt.client.rewards.request.DeleteRewardsRequest;
import com.chinarewards.gwt.elt.client.rewards.request.DeleteRewardsResponse;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.ui.HyperLinkCell;
import com.chinarewards.gwt.elt.client.widget.EltNewPager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager.TextLocation;
import com.chinarewards.gwt.elt.client.widget.GetValue;
import com.chinarewards.gwt.elt.client.widget.ListCellTable;
import com.chinarewards.gwt.elt.client.widget.Sorting;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.client.win.confirm.ConfirmHandler;
import com.chinarewards.gwt.elt.model.rewards.RewardPageType;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class RewardsListPresenterImpl extends BasePresenter<RewardsListDisplay>
		implements RewardsListPresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;

	RewardPageType pageType;

	EltNewPager pager;
	ListCellTable<RewardsClient> cellTable;
	RewardsListViewAdapter listViewAdapter;

	private final BreadCrumbsPresenter breadCrumbs;
	@Inject
	public RewardsListPresenterImpl(EventBus eventBus, DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager,
			RewardsListDisplay display, Win win,BreadCrumbsPresenter breadCrumbs) {
		super(eventBus, display);
		this.dispatch = dispatch;
		this.errorHandler = errorHandler;
		this.sessionManager = sessionManager;
		this.win = win;
		this.breadCrumbs=breadCrumbs;

	}

	@Override
	public void bind() {
		breadCrumbs.loadListPage();
		display.setBreadCrumbs(breadCrumbs.getDisplay().asWidget());
		init();
		registerHandler(display.getSearchBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						// win.alert(sessionManager.getSession().getLoginName());
						init();
					}
				}));
	}

	private void init() {	
		buildTable();
		doSearch();
	}

	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<RewardsClient>();

		initTableColumns();
		pager = new EltNewPager(TextLocation.CENTER);
		pager.setDisplay(cellTable);
		cellTable.setWidth(ViewConstants.page_width);
		cellTable.setPageSize(ViewConstants.per_page_number_in_dialog);
		cellTable.getColumn(0).setCellStyleNames("divTextLeft");
		display.getResultPanel().clear();
		display.getResultPanel().add(cellTable);
		display.getResultpage().clear();
		display.getResultpage().add(pager);
		
	}

	private void doSearch() {
		RewardsCriteria criteria = new RewardsCriteria();
		criteria.setName(display.getName().getValue());
		criteria.setDefinition(display.getDefinition().getValue());
		if (display.getNowJudge().getValue()) {
			criteria.setJudgeUserId(sessionManager.getSession().getToken());
		}
		if (pageType == RewardPageType.NOMINATEPAGE) {
			criteria.setStatus(RewardsStatus.PENDING_NOMINATE);
		}
		if (pageType == RewardPageType.AWARDREWARDPAGE) {
			criteria.setStatus(RewardsStatus.NEW);
		}
		if (pageType == RewardPageType.DETAILSOFAWARDPAGE) {
			criteria.setStatus(RewardsStatus.REWARDED);
		}
		listViewAdapter = new RewardsListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager,display);
		listViewAdapter.addDataDisplay(cellTable);

	}

	private void initTableColumns() {
		Sorting<RewardsClient> ref = new Sorting<RewardsClient>() {
			@Override
			public void sortingCurrentPage(Comparator<RewardsClient> comparator) {
				// listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);

			}
		};
		// cellTable.addColumn("奖项编号", new TextCell(),
		// new GetValue<RewardsClient, String>() {
		// @Override
		// public String getValue(RewardsClient rewards) {
		// return "01";
		// }
		// }, ref, "id");

//		cellTable.addColumn("", new TextCell(),
//				new GetValue<RewardsClient, String>() {
//					@Override
//					public String getValue(RewardsClient rewards) {
//						return rewards.getName();
//					}
//				}, ref, "name");
		cellTable.addColumn("奖励名称", new HyperLinkCell(),
				new GetValue<RewardsClient, String>() {
					@Override
					public String getValue(RewardsClient rewards) {
						return rewards.getName();
					}
				}, new FieldUpdater<RewardsClient, String>() {

					@Override
					public void update(int index, RewardsClient o,
							String value) {
						Platform.getInstance()
								.getEditorRegistry()
								.openEditor(
										DetailsOfAwardConstants.EDITOR_DETAILSOFAWARD_SEARCH,
										DetailsOfAwardConstants.EDITOR_DETAILSOFAWARD_SEARCH
												+ o.getId(), o);

					}

				}, ref, "name");
		
		cellTable.addColumn("奖项积分", new TextCell(),
				new GetValue<RewardsClient, String>() {
					@Override
					public String getValue(RewardsClient rewards) {
						int total = (int) (rewards.getTotalAmtLimit());
						return  total+ "";
					}
				}, ref, "totalAmtLimit");
		cellTable.addColumn("说明", new TextCell(),
				new GetValue<RewardsClient, String>() {
					@Override
					public String getValue(RewardsClient rewards) {
						if (rewards.getDefinition().length() > 30) {
							return rewards.getDefinition().substring(0, 30)
									+ "...";
						} else {
							return rewards.getDefinition();
						}
					}
				}, ref, "definition");
		cellTable.addColumn("发起人", new TextCell(),
				new GetValue<RewardsClient, String>() {
					@Override
					public String getValue(RewardsClient rewards) {
						return rewards.getCreatedBy();
					}
				}, ref, "createdBy");
		cellTable.addColumn("颁奖方式", new TextCell(),
				new GetValue<RewardsClient, String>() {
					@Override
					public String getValue(RewardsClient rewards) {
						return "领导提名";
					}
				}, ref, "name");
		cellTable.addColumn("预计提名时间",
				new DateCell(DateTimeFormat.getFormat("yyyy-MM-dd")),
				new GetValue<RewardsClient, Date>() {
					@Override
					public Date getValue(RewardsClient rewards) {
						return rewards.getExpectNominateDate();
					}
				}, ref, "expectNominateDate");
		cellTable.addColumn("状态", new TextCell(),
				new GetValue<RewardsClient, String>() {
					@Override
					public String getValue(RewardsClient rewards) {
						return rewards.getStatus().getDisplayName();
					}
				}, ref, "name");
		if (pageType == RewardPageType.NOMINATEPAGE) {
			cellTable.addColumn("操作", new HyperLinkCell(),
					new GetValue<RewardsClient, String>() {
						@Override
						public String getValue(RewardsClient rewards) {
//							for (JudgeModelClient judge:rewards.getJudgeList()) {
//								if(judge.getStaffId().equals(sessionManager.getSession().getStaffId()))
//								{
//									if("NOMINATED".equals(judge.getStatus()))
//									{
//										return "已提名";
//									}
//									else
//									{
//										return "提名";
//									}
//								}
//							}
//							return "不是提名人";
							return "提名";
												
						}
					}, new FieldUpdater<RewardsClient, String>() {

						@Override
						public void update(int index, RewardsClient o,
								String value) {
							boolean fal=false;
							if(o.getJudgeList()!=null)
							{
								for (JudgeModelClient judge:o.getJudgeList()) {
									if(judge.getStaffId().equals(sessionManager.getSession().getStaffId()))
									{
										fal=true;
										if("NOMINATED".equals(judge.getStatus()))
										{
											win.alert("您已经提名过了!"+"<br><br>您提名了:"+o.getNomineeLot().get(judge.getStaffId()));
										}
										else
										{
											Platform.getInstance()
											.getEditorRegistry()
											.openEditor(
													NominateConstants.EDITOR_NOMINATE_SEARCH,
													NominateConstants.EDITOR_NOMINATE_SEARCH
															+ o.getId(), o);
										}
										break;
									}
								}
								if(fal==false)
									win.alert("您不是提名人!");
							}
							else
							{
								win.alert("该奖项无提名人!");
							}
							

						}

					});
		}
		if (pageType == RewardPageType.AWARDREWARDPAGE) {
			cellTable.addColumn("操作", new HyperLinkCell(),
					new GetValue<RewardsClient, String>() {
						@Override
						public String getValue(RewardsClient rewards) {
							return "颁奖";
						}
					}, new FieldUpdater<RewardsClient, String>() {

						@Override
						public void update(int index, RewardsClient o,
								String value) {
							if ("NEW".equals(o.getStatus().name())
									|| "PENDING_NOMINATE".equals(o.getStatus()
											.name())) {
								Platform.getInstance()
										.getEditorRegistry()
										.openEditor(
												AwardRewardConstants.EDITOR_AWARDREWARD_SEARCH,
												AwardRewardConstants.EDITOR_AWARDREWARD_SEARCH
														+ o.getId(), o);
							} else {
								win.alert("已经颁奖");
								return;
							}
						}

					});
		}
		if (pageType == RewardPageType.DETAILSOFAWARDPAGE) {
			cellTable.addColumn("操作", new HyperLinkCell(),
					new GetValue<RewardsClient, String>() {
						@Override
						public String getValue(RewardsClient rewards) {
							return "颁奖详细";
						}
					}, new FieldUpdater<RewardsClient, String>() {

						@Override
						public void update(int index, RewardsClient o,
								String value) {
							Platform.getInstance()
									.getEditorRegistry()
									.openEditor(
											DetailsOfAwardConstants.EDITOR_DETAILSOFAWARD_SEARCH,
											DetailsOfAwardConstants.EDITOR_DETAILSOFAWARD_SEARCH
													+ o.getId(), o);

						}

					});
		}
		if (pageType == RewardPageType.APPLYREWARDLIST) {
			cellTable.addColumn("操作", new HyperLinkCell(),
					new GetValue<RewardsClient, String>() {
						@Override
						public String getValue(RewardsClient rewards) {
							if (rewards.getStatus() == RewardsStatus.NEW)
								return "颁奖";
							else if (rewards.getStatus() == RewardsStatus.PENDING_NOMINATE)
								{
//								for (JudgeModelClient judge:rewards.getJudgeList()) {
//									if(judge.getStaffId().equals(sessionManager.getSession().getStaffId()))
//									{
//										if("NOMINATED".equals(judge.getStatus()))
//										{
//											return "已提名";
//										}
//										else
//										{
//											return "提名";
//										}
//									}
//								}
//								return "不是提名人";
								return "提名";
								}
							else
								return "";
						}
					}, new FieldUpdater<RewardsClient, String>() {

						@Override
						public void update(int index, RewardsClient o,
								String value) {
							String pageUrl = "";
							if (o.getStatus() == RewardsStatus.NEW)
							{
								pageUrl = AwardRewardConstants.EDITOR_AWARDREWARD_SEARCH;
								Platform.getInstance()
								.getEditorRegistry()
								.openEditor(pageUrl, pageUrl + o.getId(), o);
							}
							else if (o.getStatus() == RewardsStatus.PENDING_NOMINATE)
							{
								pageUrl = NominateConstants.EDITOR_NOMINATE_SEARCH;
								boolean fal=false;
								for (JudgeModelClient judge:o.getJudgeList()) {
									if(judge.getStaffId().equals(sessionManager.getSession().getStaffId()))
									{
										fal=true;
										if("NOMINATED".equals(judge.getStatus()))
										{
											win.alert("您已经提名过了!"+"<br><br>您提名了:"+o.getNomineeLot().get(judge.getStaffId()));
										}
										else
										{
											Platform.getInstance()
											.getEditorRegistry()
											.openEditor(pageUrl, pageUrl + o.getId(), o);

										}
										break;
									}
								}
								if(fal==false)
									win.alert("您不是提名人!");
							}

						}

					});
			cellTable.addColumn("操作", new HyperLinkCell(),
					new GetValue<RewardsClient, String>() {
						@Override
						public String getValue(RewardsClient rewards) {
							return "查看详细";
						}
					}, new FieldUpdater<RewardsClient, String>() {

						@Override
						public void update(int index, RewardsClient o,
								String value) {
							Platform.getInstance()
									.getEditorRegistry()
									.openEditor(
											DetailsOfAwardConstants.EDITOR_DETAILSOFAWARD_SEARCH,
											DetailsOfAwardConstants.EDITOR_DETAILSOFAWARD_SEARCH
													+ o.getId(), o);

						}

					});
		}

		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<RewardsClient, String>() {
					@Override
					public String getValue(RewardsClient rewards) {
						return "删除";
					}
				}, new FieldUpdater<RewardsClient, String>() {

					@Override
					public void update(int index, final RewardsClient o,
							String value) {
						win.confirm("提示", "确定删除?", new ConfirmHandler() {

							@Override
							public void confirm() {
								delteReward(o.getId());

							}
						});
					}

				});
	}

	public void delteReward(String rewardsId) {

		dispatch.execute(new DeleteRewardsRequest(rewardsId, sessionManager
				.getSession().getToken()),
				new AsyncCallback<DeleteRewardsResponse>() {

					@Override
					public void onFailure(Throwable t) {
						win.alert(t.getMessage());
					}

					@Override
					public void onSuccess(DeleteRewardsResponse resp) {
						win.alert("删除成功");
						doSearch();
					}
				});
	}

	@Override
	public void initRewardsList(RewardPageType pageType) {
		this.pageType = pageType;
	}
}
