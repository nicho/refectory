package com.chinarewards.gwt.elt.client.budget.presenter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.breadCrumbs.presenter.BreadCrumbsPresenter;
import com.chinarewards.gwt.elt.client.budget.model.CorpBudgetVo;
import com.chinarewards.gwt.elt.client.budget.model.DepBudgetVo;
import com.chinarewards.gwt.elt.client.budget.model.DepartmentVo;
import com.chinarewards.gwt.elt.client.budget.presenter.CreateBudgetPresenter.CreateBudgetDisplay;
import com.chinarewards.gwt.elt.client.budget.provider.DepBudgetListAdapter;
import com.chinarewards.gwt.elt.client.budget.request.AddDepartmentBudgetRequest;
import com.chinarewards.gwt.elt.client.budget.request.AddDepartmentBudgetResponse;
import com.chinarewards.gwt.elt.client.budget.request.InitCorpBudgetRequest;
import com.chinarewards.gwt.elt.client.budget.request.InitCorpBudgetResponse;
import com.chinarewards.gwt.elt.client.budget.request.InitDepartmentRequest;
import com.chinarewards.gwt.elt.client.budget.request.InitDepartmentResponse;
import com.chinarewards.gwt.elt.client.core.view.constant.ViewConstants;
import com.chinarewards.gwt.elt.client.mvp.BasePresenter;
import com.chinarewards.gwt.elt.client.mvp.ErrorHandler;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager;
import com.chinarewards.gwt.elt.client.widget.EltNewPager.TextLocation;
import com.chinarewards.gwt.elt.client.widget.GetValue;
import com.chinarewards.gwt.elt.client.widget.ListCellTable;
import com.chinarewards.gwt.elt.client.widget.Sorting;
import com.chinarewards.gwt.elt.client.win.Win;
import com.chinarewards.gwt.elt.client.win.confirm.ConfirmHandler;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class CreateBudgetPresenterImpl extends BasePresenter<CreateBudgetDisplay>
		implements CreateBudgetPresenter {

	final DispatchAsync dispatch;
	final ErrorHandler errorHandler;
	final SessionManager sessionManager;
	final Win win;
    String corpBudgetId="";
    double remainCount;//剩余的总积分
    double oldCount;//修改时得到旧的预算积分
	EltNewPager pager;
	ListCellTable<DepBudgetVo> cellTable;
	DepBudgetListAdapter listViewAdapter;
	private final BreadCrumbsPresenter breadCrumbs;
	@Inject
	public CreateBudgetPresenterImpl(EventBus eventBus, DispatchAsync dispatch,
			ErrorHandler errorHandler, SessionManager sessionManager,BreadCrumbsPresenter breadCrumbs,
			CreateBudgetDisplay display, Win win) {
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
		registerHandler(display.getSaveBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						saveDepartmentBudget("init");//查看有没有存在数据有的就更新
					}
				}));
		registerHandler(display.getSearchBtnClickHandlers().addClickHandler(
				new ClickHandler() {
					public void onClick(ClickEvent paramClickEvent) {
						if (!validateSearchSubmit()) {
							return;
						}
						toSearch();
					}
				}));
		
			}

	private void init() {
		buildTable();
		
		initDeparts();
		initYear();
		
	}
  
	 private void saveDepartmentBudget(String operate){
		   if (operate.equals("save")&&!validateSubmit()) {//是保存
				return;
			}
		    if (operate.equals("update")&&!validateSubmited()) {//是修改
				return;
			}
		   DepBudgetVo depBudgetVo = new DepBudgetVo();
		   depBudgetVo.setBudgetIntegral(Double.parseDouble(display.getJF().getValue()));
		   depBudgetVo.setCorpBudgetId(display.getYear());
		   depBudgetVo.setDepartmentId(display.getDepart());
		   dispatch.execute(new AddDepartmentBudgetRequest(sessionManager.getSession(),depBudgetVo,operate),
					new AsyncCallback<AddDepartmentBudgetResponse>() {
			          	@Override
						public void onFailure(Throwable arg0) {
							errorHandler.alert("保存部门预算出错!");
							toRefresh();
						}

						@Override
						public void onSuccess(AddDepartmentBudgetResponse response) {
							if(response.getMessage().equals("save")){
								saveDepartmentBudget("save");
							}
							if(response.getMessage().equals("1")){
							  win.alert("保存成功");
							  toRefresh();
							}
							if(response.getMessage().equals("update")){
								oldCount = response.getOldJf();
							 win.confirm("操作提示", "部门预算已存在,确定修改？", new ConfirmHandler() {
								@Override
								public void confirm() {
									 saveDepartmentBudget("update");
								 }
							  });
							}
							 if(response.getMessage().equals("2")){
								 win.alert("修改成功");
								 toRefresh();
							 }
							 
							 
						}      

					});
		 
	   }	
	   // 保存验证方法
		private boolean validateSubmit() {
			boolean flag = true;
			StringBuilder errorMsg = new StringBuilder();
			

			if (display.getDepart() == null|| "".equals(display.getDepart().trim())) {
				errorMsg.append("请选择预算部门!<br>");
				flag = false;
			}
			
			if (display.getJF().getValue() == null|| "".equals(display.getJF().getValue().trim())) {
				errorMsg.append("请填写预算积分!<br>");
				flag = false;
			}
			if (display.getJF().getValue() != null && ! "".equals(display.getJF().getValue().trim())) {
			   try{
				   if(remainCount < Double.parseDouble(display.getJF().getValue())){
					   errorMsg.append("新增时公司预算剩余积分不足!<br>");
						flag = false;
				   }
			   }catch(Exception   e){
				    errorMsg.append("预算积分是大于0数字!<br>");
					flag = false;
			   }
			
				
			}
			if (!flag) {
				win.alert(errorMsg.toString());
			}

			return flag;
		}
		
      // 验证方法提交后
		private boolean validateSubmited() {
			boolean flag = true;
			StringBuilder errorMsg = new StringBuilder();
			if (display.getDepart() == null|| "".equals(display.getDepart().trim())) {
				errorMsg.append("请选择预算部门!<br>");
				flag = false;
			}
			if (display.getJF().getValue() == null|| "".equals(display.getJF().getValue().trim())) {
				errorMsg.append("请填写预算积分!<br>");
				flag = false;
			}
			if (display.getJF().getValue() != null && ! "".equals(display.getJF().getValue().trim())) {
			   try{
				  if((oldCount+remainCount) < Integer.parseInt(display.getJF().getValue())){
					   errorMsg.append("修改时公司预算剩余积分不足!<br>");
						flag = false;
				   }
			   }catch(Exception   e){
				    errorMsg.append("预算积分是大于0数字!<br>");
					flag = false;
			   }
			
				
			}
			if (!flag) {
				win.alert(errorMsg.toString());
			}

			return flag;
		}

		// 验证方法
	private boolean validateSearchSubmit() {
		boolean flag = true;
		StringBuilder errorMsg = new StringBuilder();
		
		if (display.getJF().getValue() != null && ! "".equals(display.getJF().getValue().trim())) {
			   try{
				   Integer.parseInt(display.getJF().getValue());
			   }catch(Exception   e){
				    errorMsg.append("预算积分是大于0数字!<br>");
					flag = false;
			   }
			
				
			}
			if (!flag) {
				win.alert(errorMsg.toString());
			}

			return flag;

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
							  
							   display.setTotalCount(vo.getBudgetIntegral()+"");
							   display.setRemainCount((vo.getBudgetIntegral()-vo.getUseIntegeral())+"");
							   remainCount = vo.getBudgetIntegral()-vo.getUseIntegeral();
								DepBudgetVo criteria = new DepBudgetVo();
								criteria.setCorpBudgetId(corpBudgetId);
								listViewAdapter = new DepBudgetListAdapter(dispatch, criteria,errorHandler, sessionManager, display);
								listViewAdapter.addDataDisplay(cellTable);

						 }
							
							display.initYear(map);
						
					}

				});
	 
   }
   private void toRefresh(){
	   dispatch.execute(new InitCorpBudgetRequest(sessionManager.getSession()),
				new AsyncCallback<InitCorpBudgetResponse>() {
		          	@Override
					public void onFailure(Throwable arg0) {
						errorHandler.alert("查询财年周期出错!");
						
					}

					@Override
					public void onSuccess(InitCorpBudgetResponse response) {
						
						  List<CorpBudgetVo> list = response.getResult();
						  CorpBudgetVo vo = new CorpBudgetVo();
						 if(list.size()>0){//得到下拉框所选择的财年周期
							 for(int i=0;i<list.size();i++){
								 if(list.get(i).getId().equals(display.getYear())){
								   vo = list.get(i);
								 }
								  
							 }
							 //刷新列表和预算总数
							    corpBudgetId = vo.getId();
							    display.setTotalCount(vo.getBudgetIntegral()+"");
							    display.setRemainCount((vo.getBudgetIntegral()-vo.getUseIntegeral())+"");
							    remainCount = vo.getBudgetIntegral()-vo.getUseIntegeral();
								DepBudgetVo criteria = new DepBudgetVo();
								criteria.setCorpBudgetId(corpBudgetId);
								listViewAdapter = new DepBudgetListAdapter(dispatch, criteria,errorHandler, sessionManager, display);
								listViewAdapter.addDataDisplay(cellTable);

						 }
							
													
					}

				});
	   
   }
   private void toSearch(){
	   dispatch.execute(new InitCorpBudgetRequest(sessionManager.getSession()),
				new AsyncCallback<InitCorpBudgetResponse>() {
		          	@Override
					public void onFailure(Throwable arg0) {
						errorHandler.alert("查询财年周期出错!");
						
					}

					@Override
					public void onSuccess(InitCorpBudgetResponse response) {
						
						  List<CorpBudgetVo> list = response.getResult();
						  CorpBudgetVo vo = new CorpBudgetVo();
						 if(list.size()>0){//得到下拉框所选择的财年周期
							 for(int i=0;i<list.size();i++){
								 if(list.get(i).getId().equals(display.getYear())){
								   vo = list.get(i);
								 }
								  
							 }
							 //刷新列表和预算总数
							    corpBudgetId = vo.getId();
							    display.setTotalCount(vo.getBudgetIntegral()+"");
							    display.setRemainCount((vo.getBudgetIntegral()-vo.getUseIntegeral())+"");
							   
								DepBudgetVo criteria = new DepBudgetVo();
								criteria.setCorpBudgetId(corpBudgetId);
								criteria.setDepartmentId(display.getDepart());
								if(!display.getJF().getValue().equals(""))
								criteria.setBudgetIntegral(Double.parseDouble(display.getJF().getValue()));
								listViewAdapter = new DepBudgetListAdapter(dispatch, criteria,errorHandler, sessionManager, display);
								listViewAdapter.addDataDisplay(cellTable);

						 }
							
							//display.initYear(map);
						
					}

				});
	   
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
					public String getValue(DepBudgetVo order) {
						return order.getDepartmentName();
					}
				}, ref, "departmentId");
		cellTable.addColumn("总人数", new TextCell(),
				new GetValue<DepBudgetVo, String>() {
					@Override
					public String getValue(DepBudgetVo order) {
						return order.getPeople()+"";
					}
				});

		cellTable.addColumn("总积分", new TextCell(),
				new GetValue<DepBudgetVo, String>() {
					@Override
					public String getValue(DepBudgetVo order) {
						return order.getBudgetIntegral()+"";
					}
				}, ref, "budgetIntegral");

		
		
		cellTable.addColumn("已用积分", new TextCell(),
				new GetValue<DepBudgetVo, String>() {
					@Override
					public String getValue(DepBudgetVo order) {
						return order.getUseIntegeral()+"";
					}
				}, ref, "useIntegeral");
		
			
	      }




    }
