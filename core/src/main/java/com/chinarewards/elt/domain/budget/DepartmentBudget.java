package com.chinarewards.elt.domain.budget;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the gift database table.
 * 
 * 
 */
@Entity
public class DepartmentBudget implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", nullable = false, updatable = false, length = 50)
	private String id;
	private String corpBudgetId ;   //企业财年预算ID
	private String departmentId;    //部门ID	
	private double budgetIntegral;//预算积分
    private double useIntegeral;  //已用积分
    private String recorduser;   //操作人
    private Date   recorddate;   //操作时间
    private int    deleted   ;     //是否删除 
	public String getRecorduser() {
		return recorduser;
	}
	public void setRecorduser(String recorduser) {
		this.recorduser = recorduser;
	}
	public Date getRecorddate() {
		return recorddate;
	}
	public void setRecorddate(Date recorddate) {
		this.recorddate = recorddate;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getId() {
		return id;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCorpBudgetId() {
		return corpBudgetId;
	}
	public void setCorpBudgetId(String corpBudgetId) {
		this.corpBudgetId = corpBudgetId;
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
	
	
	
}