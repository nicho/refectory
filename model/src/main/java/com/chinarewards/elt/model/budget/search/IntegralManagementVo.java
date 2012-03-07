package com.chinarewards.elt.model.budget.search;

public class IntegralManagementVo {
	private String departmentName;    //部门名称
	private String corpBudgetId ;   //企业财年预算ID
	private String departmentId;    //部门ID	
	private double budgetIntegral;//预算积分
    private double useIntegeral;  //已用积分
    private String parentId;  //上级部门ID
    private boolean isLeaf;  //是否子节点
    
	public boolean isLeaf() {
		return isLeaf;
	}
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getCorpBudgetId() {
		return corpBudgetId;
	}
	public void setCorpBudgetId(String corpBudgetId) {
		this.corpBudgetId = corpBudgetId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public double getBudgetIntegral() {
		return budgetIntegral;
	}
	public void setBudgetIntegral(double budgetIntegral) {
		this.budgetIntegral = budgetIntegral;
	}
	public double getUseIntegeral() {
		return useIntegeral;
	}
	public void setUseIntegeral(double useIntegeral) {
		this.useIntegeral = useIntegeral;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
