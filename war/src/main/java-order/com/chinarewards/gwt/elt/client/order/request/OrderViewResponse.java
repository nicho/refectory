/**
 * 
 */
package com.chinarewards.gwt.elt.client.order.request;

import net.customware.gwt.dispatch.shared.Result;

/**
 * @author lw
 * @since 2012年2月1日 15:07:52
 */
public class OrderViewResponse implements Result {

	private String result;



	  public String getResult() {
		return result;
	}



	public void setResult(String result) {
		this.result = result;
	}

	public OrderViewResponse(){
		
	}

	public OrderViewResponse(String result) {
		this.result = result;
	}




}
