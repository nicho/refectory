/**
 * 
 */
package com.chinarewards.gwt.elt.client.core.impl;

import com.chinarewards.gwt.elt.model.user.UserRoleVo;

/**
 * 
 * 
 * 
 * @author cyril
 * @since 0.2.0
 */
public class HardCodedMemoryMenuRoleStore extends InMemoryMenuRoleStore {

	/**
	 * 
	 */
	public HardCodedMemoryMenuRoleStore() {
		super();

		initialize();
	}

	protected void initialize() {
		initMenuForSeniorCs();

		initMenuForCorpAdmin();

		initMenuForDeptMgr();
	}

	/**
	 * Initialize menu for senior customer service
	 */
	protected void initMenuForSeniorCs() {

		this.addMenusToRole(UserRoleVo.SENIOR_CS.name(), new String[] {});

	}

	/**
	 * Initialize menu for corporation admin.
	 */
	protected void initMenuForCorpAdmin() {

		this.addMenusToRole(UserRoleVo.CORP_ADMIN.name(), new String[] {});

	}

	protected void initMenuForDeptMgr() {
		this.addMenusToRole(UserRoleVo.DEPT_MGR.name(), new String[] {});

	}

}
