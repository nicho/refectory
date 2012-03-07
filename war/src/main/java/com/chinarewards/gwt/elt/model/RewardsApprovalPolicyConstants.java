/**
 * 
 */
package com.chinarewards.gwt.elt.model;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Cream
 * @since 0.2.0
 */
public class RewardsApprovalPolicyConstants implements IsSerializable {

	/**
	 * Rewards approval is required.
	 */
	public final static String REQUIRED = "REQUIRED";

	/**
	 * Rewards approval is not required.
	 */
	public final static String NOT_REQUIRED = "NOT_REQUIRED";

	/**
	 * Does not impose any restriction.
	 */
	public final static String SYSTEM_DEFAULT = "SYSTEM_DEFAULT";
}
