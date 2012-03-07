package com.chinarewards.elt.service.budget;


import java.util.List;

import com.chinarewards.elt.domain.budget.CorpBudget;
import com.chinarewards.elt.domain.budget.DepartmentBudget;
import com.chinarewards.elt.model.budget.search.DepartmentBudgetVo;
import com.chinarewards.elt.model.budget.search.IntegralManagementVo;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.user.UserContext;

/**
 * Service of corporation.
 * 
 * @author lw
 * @since 1.5
 */
public interface BudgetService {

	/**
	 * 保存企业预算
	 * @param context
	 * @param order
	 * @return
	 */
	public CorpBudget saveCorpBudget(UserContext context, CorpBudget budget);
	
	/**
	 * 保存部门预算
	 * @param context
	 * @param order
	 * @return
	 */
	public DepartmentBudget saveDepartmentBudget(UserContext context, DepartmentBudget budget);


	/**
	 * 查找根据企业财年预算ID
	 * @param id
	 * @return
	 */
	public CorpBudget findCorpBudgetById(String id);
	
	/**
	 * HR查找根据企业财年(增加部门预算时，初始化得到财年的数据用于下拉表选择)
	 * @param id
	 * @return
	 */
	public List<CorpBudget> findCorpBudget(String corpid);
	
	/**
	 * 部门查找根据企业财年(增加部门预算时，初始化得到财年的数据用于下拉表选择)
	 * @param id
	 * @return
	 */
	public List<DepartmentBudget> findDepartBudget(String depId);
    /*
	 * 查找根据企业ID
	 * @param id
	 * @return
	 */
	public CorpBudget findCorpBudgetByCorpId(String corpid);
	

	
	
	/**
	 * 查找根据部门预算ID
	 * @param id
	 * @return
	 */
	public DepartmentBudget findDepartmentBudgetById(String id);
	
	
	/**
	 * 删除部门预算根据ID
	 * @param id
	 * @return
	 */
	public String deleteDepartmentBudget(UserContext context,String id);
	/**
	 * 部门预算列表
	 * @param context
	 * @param CorpBudget
	 * @return
	 */
	public PageStore<DepartmentBudgetVo> deptBudgetList(UserContext context,DepartmentBudgetVo deptBudgetVo);

  /**
   * 是否存在同一财年和部门的数据,如果有就返回ID，没有返回空
   * @param departmentBudget
   * @return
   */
	public DepartmentBudget findByDepAndCorpBudgetId(DepartmentBudget departmentBudget);

	
	/**
	 * 积分管理(预算管理)首页.数据查询
	 * @param corpId
	 * @return
	 */
	public List<IntegralManagementVo> getIntegralManagementList(String corpId,String corpBudgetId);

	/**
	 * @param departmentId
	 * @param corpBudgetId
	 * @return
	 */
	public DepartmentBudget findDepartmentBudgetByDepartmentId(String departmentId,
			String corpBudgetId);

	

}
