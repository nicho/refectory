package com.chinarewards.gwt.elt.client.dataprovider;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.client.DispatchAsync;

import com.chinarewards.gwt.elt.client.rewardItem.request.SearchOrganizationRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchOrganizationResponse;
import com.chinarewards.gwt.elt.client.rewards.model.StaffOrDepartmentAC;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.SuggestOracle;

public class OrganizationSuggestOracle extends SuggestOracle {

	private DispatchAsync dispatcher;
	private SessionManager sessionManager ;
	public OrganizationSuggestOracle(DispatchAsync dispatcher,SessionManager sessionManager) {
		this.dispatcher = dispatcher;
		this.sessionManager = sessionManager;
	}

	public static class OrganizationSuggest implements Suggestion,
			IsSerializable {

		public OrganizationSuggest() {

		}

		public OrganizationSuggest(StaffOrDepartmentAC sd) {
			this.sd = sd;
		}

		private StaffOrDepartmentAC sd;

		@Override
		public String getDisplayString() {
			return sd.getDisplayName();
		}

		@Override
		public String getReplacementString() {
			return sd.getName();
		}

		public StaffOrDepartmentAC getSd() {
			return sd;
		}
	}

	@Override
	public void requestSuggestions(final Request request,
			final Callback callback) {
		String query = request.getQuery();
//		if (!GWT.isScript()) {
//			final List<OrganizationSuggest> suggestions = getSuggestions(query);
//			Response response = new Response(suggestions);
//			callback.onSuggestionsReady(request, response);
//		} else {
		
		     
		    String corporationId = sessionManager.getSession().getCorporationId() ;
			dispatcher.execute(new SearchOrganizationRequest(corporationId,query),
					new AsyncCallback<SearchOrganizationResponse>() {
						@Override
						public void onFailure(Throwable arg0) {
							Window.alert("failure!");
						}

						@Override
						public void onSuccess(SearchOrganizationResponse sor) {
							List<OrganizationSuggest> suggestions = new ArrayList<OrganizationSuggestOracle.OrganizationSuggest>();
							List<StaffOrDepartmentAC> list = sor.getList();
							for (StaffOrDepartmentAC sd : list) {
								suggestions.add(new OrganizationSuggest(sd));
							}
							final Response response = new Response(suggestions);
							callback.onSuggestionsReady(request, response);
						}
					});
		}
	//}

	// mock data..
	private List<OrganizationSuggest> getSuggestions(String query) {

		List<OrganizationSuggest> suggestions = new ArrayList<OrganizationSuggestOracle.OrganizationSuggest>();
		if ("张".equals(query)) {
			suggestions.add(new OrganizationSuggest(new StaffOrDepartmentAC(
					"1", "张飒", "张飒(IT部)<zhangsu@example.com>")));
		}
		suggestions.add(new OrganizationSuggest(new StaffOrDepartmentAC("2",
				"鲤鱼", "鲤鱼(人事部)<liyu@example.com>")));
		suggestions.add(new OrganizationSuggest(new StaffOrDepartmentAC("3",
				"产品部", "产品部(13名员工)")));
		return suggestions;
	}
}
