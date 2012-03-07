/**
 * 
 */
package com.chinarewards.gwt.elt.client.team.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.team.model.TeamSearchVo;

/**
 * @author lw
 * @since 2012年1月20日 19:00:32
 */
public class SearchTeamResponse implements Result {

	private List<TeamSearchVo> result;
	private int total;


	/**
	 * @return the result
	 */
	public List<TeamSearchVo> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<TeamSearchVo> result) {

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
