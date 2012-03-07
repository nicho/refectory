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
public class CorpBudget implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", nullable = false, updatable = false, length = 50)
	private String id;
	private String budgetTitle;//标题
	private String corporationId ;   //企业ID
	private Date   beginDate;      //财年开始日期
	private Date   endDate;        //财年结束日期
	private double budgetAmount ; //预算金额
	private String moneyType;//货币类型
	private double budgetIntegral;//预算积分
    private double useIntegeral;  //已用积分
    private String recorduser;   //操作人
    private Date   recorddate;   //操作时间
    private int    deleted   ;     //是否删除 
    
	public String getMoneyType() {
		return moneyType;
	}
	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}
    
    
    
	public String getBudgetTitle() {
		return budgetTitle;
	}
	public void setBudgetTitle(String budgetTitle) {
		this.budgetTitle = budgetTitle;
	}
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
	public void setId(String id) {
		this.id = id;
	}
	public String getCorporationId() {
		return corporationId;
	}
	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public double getBudgetAmount() {
		return budgetAmount;
	}
	public void setBudgetAmount(double budgetAmount) {
		this.budgetAmount = budgetAmount;
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
