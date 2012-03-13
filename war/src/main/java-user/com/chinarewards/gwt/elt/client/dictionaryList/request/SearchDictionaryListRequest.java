/**
 * 
 */
package com.chinarewards.gwt.elt.client.dictionaryList.request;

import net.customware.gwt.dispatch.shared.Action;

import com.chinarewards.gwt.elt.client.dictionaryList.model.DictionaryListCriteria;
import com.chinarewards.gwt.elt.client.support.UserSession;

/**
 * An action which perform request to search user.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:43
 */
public class SearchDictionaryListRequest implements Action<SearchDictionaryListResponse> {

	private DictionaryListCriteria criteria;
	private UserSession session;


	public SearchDictionaryListRequest() {
	}

	public DictionaryListCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(DictionaryListCriteria criteria) {
		this.criteria = criteria;
	}

	public UserSession getSession() {
		return session;
	}

	public void setSession(UserSession session) {
		this.session = session;
	}

	/**
	 * 
	 * @param DictionaryListVo
	 */
	public SearchDictionaryListRequest(DictionaryListCriteria criteria,UserSession session) {
		this.criteria = criteria;
		this.session=session;
	}


}
