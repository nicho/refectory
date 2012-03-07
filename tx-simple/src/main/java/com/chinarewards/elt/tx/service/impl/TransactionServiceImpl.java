package com.chinarewards.elt.tx.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.tx.dao.TransactionDao;
import com.chinarewards.elt.tx.domain.Transaction;
import com.chinarewards.elt.tx.domain.TxUnit;
import com.chinarewards.elt.tx.exception.BalanceLackException;
import com.chinarewards.elt.tx.exception.DuplicateUnitCodeException;
import com.chinarewards.elt.tx.model.Unit;
import com.chinarewards.elt.tx.service.TransactionLogic;
import com.chinarewards.elt.tx.service.TransactionService;
import com.google.inject.Inject;

public class TransactionServiceImpl implements TransactionService {

	Logger logger = LoggerFactory.getLogger(getClass());

	TransactionLogic transactionLogic;
	TransactionDao transactionDao;

	@Inject
	public TransactionServiceImpl(TransactionLogic transactionLogic,
			TransactionDao transactionDao) {
		this.transactionLogic = transactionLogic;
		this.transactionDao = transactionDao;
	}

	@Override
	public String deposit(String accountId, String unitCode, double amount) {
		logger.debug(
				"Invoking deposit, param[accountId={}, unitCode={},amount={}]",
				new Object[] { accountId, unitCode, amount });
		Date now = new Date();
		Transaction tx = new Transaction();
		tx.setTransDate(now);
		transactionDao.save(tx);

		transactionLogic.deposit(tx, accountId, unitCode, amount, now);

		return tx.getId();
	}

	@Override
	public String withdraw(String accountId, String unitCode, double amount)
			throws BalanceLackException {
		logger.debug(
				"Invoking withdraw, param[accountId={}, unitCode={},amount={}]",
				new Object[] { accountId, unitCode, amount });
		Date now = new Date();
		Transaction tx = new Transaction();
		tx.setTransDate(now);
		transactionDao.save(tx);

		try {
			transactionLogic.withdraw(tx, accountId, unitCode, amount, now);
		} catch (RuntimeException e) {
			logger.error("something wrong!", e);
			// FIXME Here should roll back!
			throw new BalanceLackException();
		}

		return tx.getId();
	}

	@Override
	public String transaction(String fromAccountId, String toAccountId,
			String unitCode, double amount) throws BalanceLackException {
		logger.debug(
				"Invoking method transaction(), param[fromAccountId={}, toAccountId={}, unitCode={}, amount={}]",
				new Object[] { fromAccountId, toAccountId, unitCode, amount });

		Date now = new Date();
		Transaction tx = new Transaction();
		tx.setTransDate(now);
		transactionDao.save(tx);

		try {
			transactionLogic.withdraw(tx, fromAccountId, unitCode, amount, now);
		} catch (RuntimeException e) {
			logger.error("something wrong!", e);
			// FIXME Here should roll back!
			throw new BalanceLackException();
		}
		transactionLogic.deposit(tx, toAccountId, unitCode, amount, now);

		return tx.getId();
	}

	@Override
	public double getBalance(String accountId, String unitCode) {
		return transactionLogic.getBalance(accountId, unitCode);
	}

	@Override
	public String createNewAccount() {
		return transactionLogic.createNewAccount().getId();
	}

	@Override
	public Unit createNewUnit(String name, String unitCode, double rate)
			throws DuplicateUnitCodeException {
		TxUnit unit = transactionLogic.createNewUnit(name, unitCode, rate);
		Unit u = new Unit();
		u.setId(unit.getId());
		u.setName(unit.getName());
		u.setRate(unit.getRate());
		u.setCode(unit.getCode());
		return u;
	}

	@Override
	public Unit getUnitInfoByUnitCode(String unitCode) {
		TxUnit unit = transactionLogic.getTxUnitByCode(unitCode);
		Unit u = new Unit();
		u.setId(unit.getId());
		u.setName(unit.getName());
		u.setRate(unit.getRate());
		u.setCode(unit.getCode());
		return u;
	}

}
