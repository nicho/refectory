package com.chinarewards.gwt.elt.client.order.model;

import java.io.Serializable;
import java.util.Date;

import com.chinarewards.gwt.elt.client.gift.model.GiftClient;
import com.chinarewards.gwt.elt.model.PaginationDetailClient;
import com.chinarewards.gwt.elt.model.SortingDetailClient;


public class OrderSearchVo implements Serializable, Comparable<OrderSearchVo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;

	public OrderSearchVo() {
	}
	private PaginationDetailClient pagination;

	private SortingDetailClient sorting;
	private String  id;
	public PaginationDetailClient getPagination() {
		return pagination;
	}
	public void setPagination(PaginationDetailClient pagination) {
		this.pagination = pagination;
	}
	public SortingDetailClient getSorting() {
		return sorting;
	}
	public void setSorting(SortingDetailClient sorting) {
		this.sorting = sorting;
	}
	private String  orderCode;       //订单编号
    private String    giftId;   //礼品ID
    private String  userId;      //订单用户
    private int     amount;     //数量
    private double integral;   //积分
    private String  name;      //订单用户姓名
	private OrderStatus status;//订单执行状态
	private int deleted;   //删除状态(0 存在,1已删删除)
	private Date    exchangeDate;////交易时间
    private Date    recorddate;   //记录时间
    private Date    exchangeDateEnd;   //交易时间止
  
	public Date getExchangeDate() {
		return exchangeDate;
	}
	public void setExchangeDate(Date exchangeDate) {
		this.exchangeDate = exchangeDate;
	}
	public Date getExchangeDateEnd() {
		return exchangeDateEnd;
	}
	public void setExchangeDateEnd(Date exchangeDateEnd) {
		this.exchangeDateEnd = exchangeDateEnd;
	}
	private GiftClient giftvo;//订单的VO
   
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getGiftId() {
		return giftId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getIntegral() {
		return integral;
	}
	public void setIntegral(double integral) {
		this.integral = integral;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public Date getRecorddate() {
		return recorddate;
	}
	public void setRecorddate(Date recorddate) {
		this.recorddate = recorddate;
	}
	
	public GiftClient getGiftvo() {
		return giftvo;
	}
	public void setGiftvo(GiftClient giftvo) {
		this.giftvo = giftvo;
	}
	@Override
	public int compareTo(OrderSearchVo o) {
		return o == null ? -1 : o.getId().compareTo(id);

	}
}
