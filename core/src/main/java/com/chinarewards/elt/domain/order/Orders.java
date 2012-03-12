package com.chinarewards.elt.domain.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.Restaurant;
import com.chinarewards.elt.domain.person.Person;

@Entity
public class Orders implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4076124377833291323L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;	
	/**
	 * 编号
	 */
	private String code;
	/**
	 * 就餐时间
	 */
	private Date reserveTime;
	/**
	 * 就餐人数
	 */
	private int amountOfClient;
	/**
	 * 包间要求
	 */
	private int favoriteRoom;
	/**
	 * 下单时间
	 */
	private Date placeOrderTime;
	/**
	 * 处理时间
	 */
	private Date  handleTime;
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	/**
	 * 完成时间
	 */
	private Date finishTime;
	/**
	 * 备注
	 */
	private String memo;
	/**
	 * 订餐人信息
	 */
	private Person orderPerson;
	/**
	 * 就餐人
	 */
	private Person contactPerson;
	
	private String city;


	private int orderStatus;

	/**
	 * 公司
	 */
	@OneToOne(fetch = FetchType.EAGER)
	private Corporation corporation;
	/**
	 * 餐厅
	 */
	@OneToOne(fetch = FetchType.EAGER)
	private Restaurant restaurant;

	private boolean deleted;


	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}

	public int getAmountOfClient() {
		return amountOfClient;
	}

	public void setAmountOfClient(int amountOfClient) {
		this.amountOfClient = amountOfClient;
	}

	public int getFavoriteRoom() {
		return favoriteRoom;
	}

	public void setFavoriteRoom(int favoriteRoom) {
		this.favoriteRoom = favoriteRoom;
	}

	public Date getPlaceOrderTime() {
		return placeOrderTime;
	}

	public void setPlaceOrderTime(Date placeOrderTime) {
		this.placeOrderTime = placeOrderTime;
	}

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Person getOrderPerson() {
		return orderPerson;
	}

	public void setOrderPerson(Person orderPerson) {
		this.orderPerson = orderPerson;
	}

	public Person getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(Person contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Corporation getCorporation() {
		return corporation;
	}

	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}


	


}
