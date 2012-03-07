package com.chinarewards.elt.service.org.impl;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.org.CorporationVo;
import com.chinarewards.elt.model.transaction.TransactionUnit;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.org.CorporationLogic;
import com.chinarewards.elt.service.org.CorporationService;
import com.chinarewards.elt.tx.service.TransactionService;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

@Transactional
public class CorporationServiceImpl implements CorporationService {

	private final CorporationLogic corporationLogic;
	private final TransactionService transactionService;
	private static double initBalance = 100000000;
	@Inject
	public CorporationServiceImpl(CorporationLogic corporationLogic,
			TransactionService transactionService) {
		this.corporationLogic = corporationLogic;
		this.transactionService = transactionService;
	}

	public Corporation saveCorporation(SysUser caller,
			CorporationVo corporationVo) {
	if(corporationVo.getId()==null||corporationVo.getId().equals("")){
		String accountId = transactionService.createNewAccount();
		String unitCode = TransactionUnit.BEANPOINTS.toString();
		corporationVo.setTxAccountId(accountId);
		corporationVo.setUnitCode(unitCode);
		// ===============================================================
		// 设置企业积分比例暂时不用-李伟
		 try {
		 transactionService.createNewUnit("缤分", unitCode, 1);
		 } catch (Exception e) {
		 // should not be here
		 }
		// 初始化企业积分给1亿-李伟
		
		   transactionService.deposit(accountId, unitCode, initBalance);
		// =======================================================================
		}
		Corporation corporation = corporationLogic.saveCorporation(caller,corporationVo);
		
		return corporation;
	}

	@Override
	public Corporation findCorporationById(String id) {
		return corporationLogic.findCorporationById(id);
	}

	@Override
	public double callBalance(String corporationId) {
		return corporationLogic.callBalance(corporationId);
	}

	@Override
	public Corporation updateIntegralPrice(UserContext context,
			Corporation corporation) {
		return corporationLogic.updateIntegralPrice(context, corporation);
	}

	@Override
	public Corporation updatePeriod(UserContext context, Corporation corporation) {
		return corporationLogic.updatePeriod(context, corporation);
	}

	@Override
	public int getCorp() {
		return corporationLogic.getCorp();
		
	}
   
}
