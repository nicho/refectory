package com.chinarewards.elt.model.order.search;

import java.util.Date;

import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.common.SortingDetail;
import com.chinarewards.elt.model.gift.search.GiftListVo;
import com.chinarewards.elt.model.order.search.OrderListVo;

public class OrderListVo {
	
	private GiftListVo giftvo;//订单的VO
	public GiftListVo getGiftvo() {
		return giftvo;
	}
	public void setGiftvo(GiftListVo giftvo) {
		this.giftvo = giftvo;
	}
	
	private PaginationDetail paginationDetail;
	public PaginationDetail getPaginationDetail() {
		return paginationDetail;
	}
	public void setPaginationDetail(PaginationDetail paginationDetail) {
		this.paginationDetail = paginationDetail;
	}
	public SortingDetail getSortingDetail() {
		return sortingDetail;
	}
	public void setSortingDetail(SortingDetail sortingDetail) {
		this.sortingDetail = sortingDetail;
	}

	
	private SortingDetail sortingDetail;
	private String  id;
	private String  orderCode;       //订单编号
    private String    orderId;   //礼品ID
    private String  userId;      //订单用户
    private int     amount;     //数量
    private double integral;   //积分
    private String  name;      //订单用户姓名
	private OrderStatus status;//订单执行状态
	private int deleted;   //删除状态(0 存在,1已删删除)
	private Date    exchangeDate;////交易时间
	 private Date    exchangeDateEnd;   //交易时间止
	public Date getExchangeDateEnd() {
		return exchangeDateEnd;
	}
	public void setExchangeDateEnd(Date exchangeDateEnd) {
		this.exchangeDateEnd = exchangeDateEnd;
	}

	private Date    recorddate;   //最后更新记录时间
	private String    recorduser;   //最后更新记录的人
	 //收货人信息
    private String  tel;
    private String address;
    private String postcode;
    private String receiver;
    private String remarks;
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public Date getExchangeDate() {
		return exchangeDate;
	}
	public void setExchangeDate(Date exchangeDate) {
		this.exchangeDate = exchangeDate;
	}
	public String getRecorduser() {
		return recorduser;
	}
	public void setRecorduser(String recorduser) {
		this.recorduser = recorduser;
	}
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
	public String getOrderId() {
		return orderId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	
	
}
