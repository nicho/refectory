package com.chinarewards.elt.service.user;

import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.model.user.UserSearchCriteria;

/**
 * Service of corporation.
 * 
 * @author lw
 * @since 1.5
 */
public interface UserService {

	/**
	 * 保存
	 * @param context
	 * @param order
	 * @return
	 */
	public SysUser save(UserContext context, SysUser user);

	
	/**
	 * 用户列表
	 * @param context
	 * @param SysUser
	 * @return
	 */
	public PageStore<SysUser> getUserList(UserContext context,UserSearchCriteria criteria);
	

}
