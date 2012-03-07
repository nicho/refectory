/**
 * 
 */
package com.chinarewards.gwt.elt.client.orderConfirmation.request;

import net.customware.gwt.dispatch.shared.Action;

/**
 * @author nicho
 * @since 2012年2月1日 10:45:55
 */
public class OrderConfirmationAddRequest implements Action<OrderConfirmationAddResponse> {


	private String giftId;
	private String staffId;
	private String userId;
	private int number;
	private String name;
	private String phone;
	private String address;
	private String zipCode;
	private String orderDefinition;
	private double total;
	private double userBalance;




	public double getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(double userBalance) {
		this.userBalance = userBalance;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public OrderConfirmationAddRequest() {
	}



	public String getGiftId() {
		return giftId;
	}

	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}

	



}
