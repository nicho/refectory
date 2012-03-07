/**
 * 
 */
package com.chinarewards.gwt.elt.client.gift.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.gift.model.GiftCriteria;
import com.chinarewards.gwt.elt.client.gift.request.SearchGiftResponse;
import com.chinarewards.gwt.elt.model.user.UserRoleVo;

/**
 * @author nicho
 * @since 2012年1月9日 19:00:40
 */
public class SearchGiftRequest implements Action<SearchGiftResponse> {

	private GiftCriteria gift;
	private String corporationId;
	private UserRoleVo[] userRoles;
	private String userId;



	public SearchGiftRequest() {
	}

	public SearchGiftRequest(GiftCriteria gift,String corporationId,UserRoleVo[] userRoles,String userId) {
		this.gift = gift;
		this.corporationId=corporationId;
		this.userRoles=userRoles;
		this.userId=userId;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public GiftCriteria getGift() {
		return gift;
	}

	public void setGift(GiftCriteria gift) {
		this.gift = gift;
	}

	public UserRoleVo[] getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(UserRoleVo[] userRoles) {
		this.userRoles = userRoles;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}




}
