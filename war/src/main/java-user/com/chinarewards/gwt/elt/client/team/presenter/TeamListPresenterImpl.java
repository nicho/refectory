package com.chinarewards.gwt.elt.client.team.presenter;
import java.util.Comparator;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.team.dataprovider.TeamListViewAdapter;
import com.chinarewards.gwt.elt.client.team.model.TeamSearchVo;
import com.chinarewards.gwt.elt.client.team.plugin.TeamConstants;
import com.chinarewards.gwt.elt.client.team.presenter.TeamListPresenter.TeamListDisplay;
import com.chinarewards.gwt.elt.client.team.request.DeleteTeamRequest;
import com.chinarewards.gwt.elt.client.team.request.DeleteTeamResponse;
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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class TeamListPresenterImpl extends BasePresenter<TeamListDisplay>
		implements TeamListPresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;

	EltNewPager pager;
	ListCellTable<TeamSearchVo> cellTable;
	TeamListViewAdapter listViewAdapter;
	private final BreadCrumbsPresenter breadCrumbs;
	@Inject
	public TeamListPresenterImpl(EventBus eventBus, DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager,BreadCrumbsPresenter breadCrumbs,
			TeamListDisplay display, Win win) {
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
		
		registerHandler(display.getAddBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						TeamSearchVo vo = new TeamSearchVo();
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(TeamConstants.EDITOR_TEAM_ADD,
								TeamConstants.ACTION_TEAM_ADD, vo);
					}
		 }));
		
		}

	private void init() {
		
		buildTable();
		doSearch();
	}
 
	private void buildTable() {
		// create a CellTable
		cellTable = new ListCellTable<TeamSearchVo>();

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
		TeamSearchVo criteria = new TeamSearchVo();
		
		if (!StringUtil.isEmpty(display.getKeyName().getValue()))
			criteria.setName(display.getKeyName().getValue());
		
       
		listViewAdapter = new TeamListViewAdapter(dispatch, criteria,
				errorHandler, sessionManager, display);
		listViewAdapter.addDataDisplay(cellTable);
	}

	private void initTableColumns() {
		Sorting<TeamSearchVo> ref = new Sorting<TeamSearchVo>() {
			@Override
			public void sortingCurrentPage(Comparator<TeamSearchVo> comparator) {
				// listViewAdapter.sortCurrentPage(comparator);
			}

			@Override
			public void sortingAll(String sorting, String direction) {
				listViewAdapter.sortFromDateBase(sorting, direction);

			}
		};
		/*cellTable.addColumn("小组编号", new TextCell(),
				new GetValue<TeamSearchVo, String>() {
					@Override
					public String getValue(TeamSearchVo team) {
						return team.getCode();
					}
				}, ref, "teamCode");*/

		cellTable.addColumn("名称", new TextCell(),
				new GetValue<TeamSearchVo, String>() {
					@Override
					public String getValue(TeamSearchVo team) {
						return team.getName();
					}
				}, ref, "name");

		cellTable.addColumn("负责人", new TextCell(),
				new GetValue<TeamSearchVo, String>() {
					@Override
					public String getValue(TeamSearchVo team) {
						return team.getManager();
					}
				});

		cellTable.addColumn("总人数", new TextCell(),
				new GetValue<TeamSearchVo, String>() {
					@Override
					public String getValue(TeamSearchVo team) {
						return team.getPeople()+"";
					}
				});
		

		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<TeamSearchVo, String>() {
					@Override
					public String getValue(TeamSearchVo team) {
						return "修改";
					}
				}, new FieldUpdater<TeamSearchVo, String>() {
					@Override
					public void update(int index, final TeamSearchVo team,String value) {
						win.confirm("操作提示", "确认修改？", new ConfirmHandler() {
						@Override
						public void confirm() {
						  Platform.getInstance()
						  .getEditorRegistry()
						  .openEditor(
								TeamConstants.EDITOR_TEAM_ADD,
								TeamConstants.ACTION_TEAM_EDIT, team);
					}
						
					});}
				});
		
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<TeamSearchVo, String>() {
					@Override
					public String getValue(TeamSearchVo team) {
						return "查看";
					}
				}, new FieldUpdater<TeamSearchVo, String>() {
					@Override
					public void update(int index, final TeamSearchVo team,String value) {
						Platform.getInstance()
						.getEditorRegistry()
						.openEditor(
								TeamConstants.EDITOR_TEAM_ADD,
								TeamConstants.ACTION_TEAM_VIEW, team);
					}
				});
		cellTable.addColumn("操作", new HyperLinkCell(),
				new GetValue<TeamSearchVo, String>() {
					@Override
					public String getValue(TeamSearchVo order) {
						return "删除";
					}
				}, new FieldUpdater<TeamSearchVo, String>() {
					@Override
					public void update(int index, final TeamSearchVo object,	String value) {
							win.confirm("操作提示", "确认删除吗？", new ConfirmHandler() {
							@Override
							public void confirm() {
								 
									dispatch.execute(new DeleteTeamRequest(object.getId(),sessionManager.getSession()),
										new AsyncCallback<DeleteTeamResponse>() {

											@Override
											public void onFailure(Throwable t) {
												errorHandler.alert("操作失败");												}

											@Override
											public void onSuccess(DeleteTeamResponse resp) {
												if(!resp.getTotal().equals(""))
												   win.alert("操作成功!");
												if(resp.getTotal().equals(""))
													win.alert("操作失败!");
												doSearch();
											}
										});
							}
						});
											
					}
				});
			
	      }
	

    }
