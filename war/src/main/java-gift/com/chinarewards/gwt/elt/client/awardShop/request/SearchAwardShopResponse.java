/**
 * 
 */
package com.chinarewards.gwt.elt.client.awardShop.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.gift.model.GiftClient;

/**
 * @author nicho
 * @since 2012年1月9日 19:00:32
 */
public class SearchAwardShopResponse implements Result {

	private List<GiftClient> result;
	private int total;


	/**
	 * @return the result
	 */
	public List<GiftClient> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<GiftClient> result) {
		this.result = result;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
}
