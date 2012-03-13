package com.chinarewards.elt.service.user.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.dao.user.UserDao;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.model.user.UserSearchCriteria;
import com.chinarewards.elt.service.user.UserLogic;
import com.google.inject.Inject;

public class UserLogicImpl implements UserLogic{
	private UserDao UserDao;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Inject
	protected UserLogicImpl(UserDao UserDao){
		this.UserDao = UserDao;

	}
	@Override
	public SysUser save(UserContext context, SysUser user) {
		return UserDao.save(user);
	}
	@Override
	public PageStore<SysUser> getUserList(UserContext context,UserSearchCriteria criteria) {
		return UserDao.queryUserPageAction(criteria);
	}
	
}
