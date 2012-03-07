package com.chinarewards.elt.service.helper;

import javax.persistence.EntityManager;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.org.CorporationVo;
import com.chinarewards.elt.model.transaction.TransactionUnit;
import com.chinarewards.elt.service.org.CorporationLogic;
import com.chinarewards.elt.tx.exception.DuplicateUnitCodeException;
import com.chinarewards.elt.tx.service.TransactionService;
import com.google.inject.Injector;

/**
 * Help us to get a useful {@link Corporation}.
 * 
 * @author yanxin
 * @since 1.0
 */
public class CorporationHelper {

	private static Corporation defaultCorp = null;
	private static double initBalance = 10000;

	/**
	 * Get a new Corporation.
	 * 
	 * @param injector
	 * @return
	 */
	public static Corporation getDefaultCorporation(Injector injector) {
		EntityManager em = injector.getInstance(EntityManager.class);
		if (defaultCorp != null
				&& em.find(Corporation.class, defaultCorp.getId()) != null) {
			return defaultCorp;
		}

		// Require some services
		TransactionService transactionService = injector
				.getInstance(TransactionService.class);
		CorporationLogic corporationLogic = injector
				.getInstance(CorporationLogic.class);
		CorporationVo corp = new CorporationVo();
		corp.setName("深圳积享通信息技术有限公司");
		corp.setDescription("一家专业的会员管理和咨询服务公司");
		// Create a new tx account.
		String accountId = transactionService.createNewAccount();

		// Create the default unit code.
		String code = getDefaultTxUnit().toString();
		corp.setUnitCode(code);
		try {
			transactionService.createNewUnit("缤分", code, 0.8);
		} catch (DuplicateUnitCodeException e) {
			// should not be here
		}
		// Deposit a amount of money
		transactionService.deposit(accountId, code, initBalance);
		corp.setTxAccountId(accountId);
		SysUser caller = UserHelper.getDefaultUser(injector);
		defaultCorp = corporationLogic.saveCorporation(caller, corp);

		return defaultCorp;
	}

	/**
	 * Get default tx unit code.
	 * 
	 * @return
	 */
	public static TransactionUnit getDefaultTxUnit() {
		return TransactionUnit.BEANPOINTS;
	}

	/**
	 * Get the initialize balance of the default corporation.
	 * 
	 * @return
	 */
	public static double getInitBalance() {
		return initBalance;
	}
}
