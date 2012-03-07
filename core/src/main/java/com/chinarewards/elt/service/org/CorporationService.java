package com.chinarewards.elt.service.org;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.org.CorporationVo;
import com.chinarewards.elt.model.user.UserContext;

/**
 * Service of corporation.
 * 
 * @author yanxin
 * @since 1.0
 */
public interface CorporationService {

	/**
	 * Save or update a corporation.
	 * 
	 * @param caller
	 * @param corporation
	 * @return
	 */
	public Corporation saveCorporation(SysUser caller, CorporationVo corporation);
	
	public Corporation updateIntegralPrice(UserContext context,	Corporation corporation);
	
	public Corporation updatePeriod(UserContext context,
			Corporation corporation);

	/**
	 * Find a corporation by the specified id.
	 * 
	 * @param id
	 * @return
	 */
	public Corporation findCorporationById(String id);

	/**
	 * Find the balance of the specified corporation about the specified unit
	 * code.
	 * 
	 * @param corporationId
	 * @return
	 */
	public double callBalance(String corporationId);
	/**
	 * 得到企业的数量用于注册
	 * @return
	 */
	
	public int getCorp();
}
