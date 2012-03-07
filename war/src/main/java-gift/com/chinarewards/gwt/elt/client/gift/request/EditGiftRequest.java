/**
 * 
 */
package com.chinarewards.gwt.elt.client.gift.request;

import java.util.List;

import com.chinarewards.gwt.elt.client.gift.model.GiftVo;
import com.chinarewards.gwt.elt.client.gift.request.EditGiftResponse;
import com.chinarewards.gwt.elt.client.support.UserSession;

import net.customware.gwt.dispatch.shared.Action;

/**
 * An action which perform request to search user.
 * 
 * @author yanrui
 */
public class EditGiftRequest implements Action<EditGiftResponse> {

	String giftId;
	String nowUserId;
	private GiftVo giftVo;
	private UserSession userSession;

	List<String> staffIds;

	public EditGiftRequest(GiftVo giftVo, UserSession userSession) {
		this.giftVo = giftVo;
		this.userSession = userSession;
	}

	/**
	 * For serialization
	 */
	public EditGiftRequest() {
	}


	public String getGiftId() {
		return giftId;
	}

	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}

	public String getNowUserId() {
		return nowUserId;
	}

	public void setNowUserId(String nowUserId) {
		this.nowUserId = nowUserId;
	}

	public GiftVo getGiftVo() {
		return giftVo;
	}

	public void setGiftVo(GiftVo giftVo) {
		this.giftVo = giftVo;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public List<String> getStaffIds() {
		return staffIds;
	}

	public void setStaffIds(List<String> staffIds) {
		this.staffIds = staffIds;
	}

}
