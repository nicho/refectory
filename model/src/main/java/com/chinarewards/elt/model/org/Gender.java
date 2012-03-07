/**
 * 
 */
package com.chinarewards.elt.model.org;

/**
 * List the gender.
 * 
 * @author yanxin
 * @since 1.0
 */
public enum Gender {

	/**
	 * Male.
	 */
	MALE("男"),

	/**
	 * Female.
	 */
	FEMALE("女")

	/**
	 * Unexposed
	 */
	, UNEXPOSED("保密");

	private String message;

	private Gender(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
