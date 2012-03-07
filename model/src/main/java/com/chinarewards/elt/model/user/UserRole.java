/**
 * 
 */
package com.chinarewards.elt.model.user;

/**
 * Stores a list of well-known roles in the system.
 * 
 * @author cyril
 * @since 0.2.0
 */
public enum UserRole {

	/**
	 * System operator.
	 */
	SYSOP,

	/**
	 * Senior customer service.
	 */
	SENIOR_CS,

	/**
	 * Customer service
	 */
	CUSTOMER_SERVICE,

	/**
	 * Corporation administrator.
	 */
	CORP_ADMIN,

	/**
	 * Sub-corporation administration.
	 */
	SUB_CORP_ADMIN,

	/**
	 * Department manager.
	 */
	DEPT_MGR,

	/**
	 * Ordinary staff.
	 */
	STAFF,
	
	/**
	 * Ordinary gift.
	 */
	GIFT

}
