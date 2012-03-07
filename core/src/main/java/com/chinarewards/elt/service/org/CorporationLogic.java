package com.chinarewards.elt.service.org;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.Amount;
import com.chinarewards.elt.model.org.CorporationVo;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.exception.GetMaxConsumeErrorException;
import com.chinarewards.elt.tx.model.Unit;

/**
 * The logic to do with {@link Corporation}
 * 
 * @author yanxin
 * @since 1.0
 */
public interface CorporationLogic {

	/**
	 * Save or update a corporation.
	 * 
	 * @param caller
	 * @param corporation
	 * @return
	 */
	public Corporation saveCorporation(SysUser caller, CorporationVo corporation);

	/**
	 * Find a corporation by the specified id.
	 * 
	 * @param id
	 * @return
	 */
	public Corporation findCorporationById(String id);

	/**
	 * Find the balance of the specified corporation about the default unit
	 * code.
	 * 
	 * @param corporationId
	 * @return
	 */
	public double callBalance(String corporationId);

	/**
	 * Get the default unit of the specified corporation.
	 * 
	 * @param corporationId
	 */
	public Unit getDefaultUnit(String corporationId);

	/**
	 * Get max consume limitation.
	 * 
	 * @param corporationId
	 * @return
	 * @throws GetMaxConsumeErrorException
	 * @deprecated
	 */
	public Amount getMaxConsume(String corporationId)
			throws GetMaxConsumeErrorException;

	/**
	 * @param context
	 * @param corporation
	 * @return
	 */
	public Corporation updateIntegralPrice(UserContext context,
			Corporation corporation);

	/**
	 * @param context
	 * @param corporation
	 * @return
	 */
	public Corporation updatePeriod(UserContext context, Corporation corporation);
	
	/**
	 * 得到企业的数量用于注册
	 * @return
	 */
	
	public int getCorp();
}
