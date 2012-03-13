package com.chinarewards.elt.service.user.impl;

import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.model.user.UserSearchCriteria;
import com.chinarewards.elt.service.user.UserLogic;
import com.chinarewards.elt.service.user.UserService;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
@Transactional
public class UserServiceImpl implements UserService {
	private final UserLogic userLogic;

	@Inject
	public UserServiceImpl(UserLogic userLogic) {	
		this.userLogic = userLogic;
	}


	@Override
	public SysUser save(UserContext context, SysUser user) {
		return userLogic.save(context, user);
	}


	@Override
	public PageStore<SysUser> getUserList(UserContext context,UserSearchCriteria criteria) {
		return userLogic.getUserList(context,criteria);
	}



}
