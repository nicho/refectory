package com.chinarewards.elt.service.org.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.dao.org.CorporationDao;
import com.chinarewards.elt.dao.org.IndustryDao;
import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.Industry;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.Amount;
import com.chinarewards.elt.model.org.CorporationVo;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.exception.GetMaxConsumeErrorException;
import com.chinarewards.elt.service.org.CorporationLogic;
import com.chinarewards.elt.tx.model.Unit;
import com.chinarewards.elt.tx.service.TransactionService;
import com.chinarewards.elt.util.DateUtil;
import com.chinarewards.elt.util.StringUtil;
import com.google.inject.Inject;

/**
 * The implementation of {@link CorporationLogic}
 * 
 * @author yanxin
 * @since 1.0
 */
public class CorporationLogicImpl implements CorporationLogic {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	IndustryDao industryDao;
	CorporationDao corporationDao;
	TransactionService transactionService;

	@Inject
	public CorporationLogicImpl(IndustryDao industryDao,
			CorporationDao corporationDao, TransactionService transactionService) {
		this.industryDao = industryDao;
		this.corporationDao = corporationDao;
		this.transactionService = transactionService;
	}

	@Override
	public Corporation saveCorporation(SysUser caller, CorporationVo corporation) {
		logger.debug(" Process in saveCorporation method, corporation.toString:"
				+ corporation.toString());
		Corporation corp;
		if (StringUtil.isEmptyString(corporation.getId())) {
			// Create a new corporation
			corp = new Corporation();
			if (!StringUtil.isEmptyString(corporation.getIndustryId())) {
				Industry industry = industryDao.findById(Industry.class,
						corporation.getIndustryId());
				corp.setIndustry(industry);
			}

			// Create the default unit code.

			corp.setDefaultUnitCode(corporation.getUnitCode());
			corp.setTxAccountId(corporation.getTxAccountId());// 产生一个交易账户ID
			Date now = DateUtil.getTime();
			corp.setName(corporation.getName());
			corp.setDescription(corporation.getDescription());
			corp.setDefaultUnitCode(corporation.getUnitCode());
			corp.setAddress(corporation.getAddress());
			corp.setCellphone(corporation.getCellphone());
			corp.setCorporationer(corporation.getCorporation());
			corp.setEmailAddress(corporation.getEmailAddress());
			corp.setFax(corporation.getFax());
			corp.setLinkman(corporation.getLinkman());
			corp.setTell(corporation.getTell());
			corp.setWeb(corporation.getWeb());
			corp.setCreatedAt(now);
			corp.setCreatedBy(caller);
			corp.setLastModifiedAt(now);
			corp.setLastModifiedBy(caller);
			corporationDao.save(corp);
		} else {
			// Edit a existed corporation
			corp = corporationDao.findById(Corporation.class,
					corporation.getId());
			if (!StringUtil.isEmptyString(corporation.getIndustryId())) {
				Industry industry = industryDao.findById(Industry.class,
						corporation.getIndustryId());
				corp.setIndustry(industry);
			}
			Date now = DateUtil.getTime();
			corp.setName(corporation.getName());
			corp.setDescription(corporation.getDescription());
			corp.setTxAccountId(corporation.getTxAccountId());
			corp.setDefaultUnitCode(corporation.getUnitCode());
			corp.setAddress(corporation.getAddress());
			corp.setCellphone(corporation.getCellphone());
			corp.setCorporationer(corporation.getCorporation());
			corp.setEmailAddress(corporation.getEmailAddress());
			corp.setFax(corporation.getFax());
			corp.setLinkman(corporation.getLinkman());
			corp.setTell(corporation.getTell());
			corp.setWeb(corporation.getWeb());
            corp.setMailpwd(corporation.getMailpwd());
            corp.setSmtp(corporation.getSmtp());
			corp.setCreatedAt(now);
			corp.setCreatedBy(caller);
			corp.setLastModifiedAt(now);
			corp.setLastModifiedBy(caller);
			corporationDao.update(corp);
		}

		return corp;
	}

	@Override
	public Corporation updateIntegralPrice(UserContext context,
			Corporation corporation) {
		logger.debug(" Process in updateIntegralPrice method, corporation.toString:"
				+ corporation.toString());
		corporation = corporationDao.update(corporation);
		return corporation;
	}
	
	@Override
	public Corporation updatePeriod(UserContext context, Corporation corporation) {
		logger.debug(" Process in updatePeriod method, corporation.toString:"
				+ corporation.toString());
		corporation = corporationDao.update(corporation);
		return corporation;
	}

	@Override
	public Corporation findCorporationById(String id) {
		logger.debug(" Process in findCorporationById method, parameter id:"
				+ id);
		Corporation res = corporationDao.findById(Corporation.class, id);
		if (res == null || StringUtil.isEmptyString(res.getId())) {
			throw new IllegalArgumentException(
					"has not found Corporation by Id,Corporation.id:" + id);
		}
		return res;
	}

	@Override
	public double callBalance(String corporationId) {
		Corporation corp = corporationDao.findById(Corporation.class,
				corporationId);
		String accountId = corp.getTxAccountId();
		String unitCode = corp.getDefaultUnitCode();

		return transactionService.getBalance(accountId, unitCode);
	}

	@Override
	public Unit getDefaultUnit(String corporationId) {
		Corporation corp = corporationDao.findById(Corporation.class,
				corporationId);
		String unitCode = corp.getDefaultUnitCode();
		return transactionService.getUnitInfoByUnitCode(unitCode);
	}

	@Override
	public Amount getMaxConsume(String corporationId)
			throws GetMaxConsumeErrorException {
		return null;
	}

	@Override
	public int getCorp() {
		return corporationDao.getCorp();
	}


	

}
