package com.chinarewards.elt.tx.model;

import java.io.Serializable;

/**
 * The unit contains some key informations about currency unit.
 * 
 * @author yanxin
 * @since 1.0
 */
public class Unit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6446452911740008882L;

	private String id;

	private String name;

	private String code;

	private double rate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
}
