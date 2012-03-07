package com.chinarewards.elt.service.helper;

import javax.persistence.EntityManager;

import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.service.user.UserLogic;
import com.google.inject.Injector;

/**
 * Help us get a useful {@link SysUser}.
 * 
 * @author yanxin
 * @since 1.0
 */
public class UserHelper {

	private static SysUser defaultUser = null;

	/**
	 * Get the default user.
	 * 
	 * @return
	 */
	public static SysUser getDefaultUser(Injector injector) {
		EntityManager em = injector.getInstance(EntityManager.class);
		if (defaultUser != null
				&& em.find(SysUser.class, defaultUser.getId()) != null)
			return defaultUser;
		// require some services
		UserLogic userLogic = injector.getInstance(UserLogic.class);
		defaultUser = userLogic.getDefaultUser();

		return defaultUser;
	}
	
	public static SysUser getDefaultUserByStaff(Injector injector,Staff staff) {
		EntityManager em = injector.getInstance(EntityManager.class);
		if (defaultUser != null
				&& em.find(SysUser.class, defaultUser.getId()) != null)
			return defaultUser;
		// require some services
		UserLogic userLogic = injector.getInstance(UserLogic.class);
		defaultUser = userLogic.getDefaultUserByStaff(staff);

		return defaultUser;
	}
}
