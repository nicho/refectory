package com.chinarewards.elt.service.org;

import com.chinarewards.elt.domain.org.OrgInit;

/**
 * Service of corporation.
 * 
 * @author lw
 * @since 1.5
 */
public interface OrgInitService {

	/**
	 * 保存或修改
	 * @param context
	 * @param team
	 * @return
	 */
	public OrgInit save( OrgInit init);
	/**
	/**
	 * 查找初始化数据
	 * @param 
	 * @return
	 */
	public OrgInit getOrgInit();
	
	

}
