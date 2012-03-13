package com.chinarewards.gwt.elt.client.dictionaryList.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.gwt.elt.client.dictionaryList.model.DictionaryListClient;

/**
 * Models the response after user process request.
 * 
 * @author nicho
 * @since 2012年2月14日 10:35:32
 */
public class SearchDictionaryListResponse implements Result {

	private List<DictionaryListClient> result;
	private int total;



	public List<DictionaryListClient> getResult() {
		return result;
	}



	public void setResult(List<DictionaryListClient> result) {
		this.result = result;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public SearchDictionaryListResponse() {

	}
	public SearchDictionaryListResponse(List<DictionaryListClient> result,int total) {
		this.result=result;
		this.total=total;

	}
	

}
