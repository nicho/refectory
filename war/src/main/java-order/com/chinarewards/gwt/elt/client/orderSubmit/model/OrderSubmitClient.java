package com.chinarewards.gwt.elt.client.orderSubmit.model;

import java.io.Serializable;

public class OrderSubmitClient implements Serializable, Comparable<OrderSubmitClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4934837755724342679L;

/**
 * 订单ID
 */
	private String orderId;
	
	private String giftId;
	private String giftName;
	private String giftImage;
	private double integral;
	private String source;

	private int number;
	private String name;
	private String phone;
	private String address;
	private String zipCode;
	private String orderDefinition;
	private double total;
	private double userBalance;
	private String business;
	private String servicetell;

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getServicetell() {
		return servicetell;
	}

	public void setServicetell(String servicetell) {
		this.servicetell = servicetell;
	}

	public double getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(double userBalance) {
		this.userBalance = userBalance;
	}

	public String getGiftId() {
		return giftId;
	}

	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public String getGiftImage() {
		return giftImage;
	}

	public void setGiftImage(String giftImage) {
		this.giftImage = giftImage;
	}

	public double getIntegral() {
		return integral;
	}

	public void setIntegral(double integral) {
		this.integral = integral;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getOrderDefinition() {
		return orderDefinition;
	}

	public void setOrderDefinition(String orderDefinition) {
		this.orderDefinition = orderDefinition;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public OrderSubmitClient() {

	}

	public OrderSubmitClient(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	@Override
	public int compareTo(OrderSubmitClient o) {
		// TODO Auto-generated method stub
		return 0;
	}


}
