package com.chinarewards.gwt.elt.client.server.user;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.model.user.UserSearchCriteria;
import com.chinarewards.elt.service.user.UserService;
import com.chinarewards.gwt.elt.client.userList.model.UserListClient;
import com.chinarewards.gwt.elt.client.userList.request.SearchUserListRequest;
import com.chinarewards.gwt.elt.client.userList.request.SearchUserListResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;

public class SearchUserListActionHandler extends
		BaseActionHandler<SearchUserListRequest, SearchUserListResponse> {

	UserService userService;
	
	@Inject
	public SearchUserListActionHandler(UserService userService)
	{
		this.userService=userService;
	}
	public SearchUserListActionHandler() {

	}
	@Override
	public Class<SearchUserListRequest> getActionType() {
		return SearchUserListRequest.class;
	}

	@Override
	public SearchUserListResponse execute(SearchUserListRequest action, ExecutionContext context)
			throws DispatchException {
		SearchUserListResponse rep=new SearchUserListResponse();
		UserContext u=new UserContext();
		UserSearchCriteria criteria=new UserSearchCriteria();
		PageStore<SysUser> userpage= userService.getUserList(u, criteria);
		rep.setTotal(userpage.getResultCount());
		
		List<UserListClient> result=new ArrayList<UserListClient>();
		if(userpage.getResultList()!=null && userpage.getResultList().size()>=0)
		{
			for (SysUser user:userpage.getResultList()) {
				UserListClient r=new UserListClient();
				r.setStaffName(user.getUserName());
				result.add(r);
			}
		}
		rep.setResult(result);
		return rep;
	}

	@Override
	public void rollback(SearchUserListRequest action, SearchUserListResponse result,
			ExecutionContext context) throws DispatchException {

	}

}
