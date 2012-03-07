package com.chinarewards.elt.tx.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.tx.dao.AccountBalanceDao;
import com.chinarewards.elt.tx.dao.AccountDao;
import com.chinarewards.elt.tx.dao.TransactionDao;
import com.chinarewards.elt.tx.dao.TransactionDetailDao;
import com.chinarewards.elt.tx.dao.TxUnitDao;
import com.chinarewards.elt.tx.domain.Account;
import com.chinarewards.elt.tx.domain.AccountBalance;
import com.chinarewards.elt.tx.domain.Transaction;
import com.chinarewards.elt.tx.domain.TransactionDetail;
import com.chinarewards.elt.tx.domain.TxUnit;
import com.chinarewards.elt.tx.exception.DuplicateUnitCodeException;
import com.chinarewards.elt.tx.model.TransactionType;
import com.chinarewards.elt.tx.service.TransactionLogic;
import com.google.inject.Inject;

public class TransactionLogicImpl implements TransactionLogic {

	TransactionDao transactionDao;
	TransactionDetailDao transactionDetailDao;
	TxUnitDao txUnitDao;
	AccountDao accountDao;
	AccountBalanceDao accountBalanceDao;

	Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	public TransactionLogicImpl(TransactionDao transactionDao,
			TransactionDetailDao transactionDetailDao, TxUnitDao txUnitDao,
			AccountDao accountDao, AccountBalanceDao accountBalanceDao) {
		this.transactionDao = transactionDao;
		this.transactionDetailDao = transactionDetailDao;
		this.txUnitDao = txUnitDao;
		this.accountBalanceDao = accountBalanceDao;
		this.accountDao = accountDao;
	}

	@Override
	public String deposit(Transaction tx, String accountId, String unitCode,
			double amount, Date transDate) {
		logger.debug(
				"Invoking method deposit(), param[accountId={}, unitCode={}, amount={}, transDate={}]",
				new Object[] { accountId, unitCode, amount, transDate });

		TransactionDetail td = new TransactionDetail();
		td.setTransaction(tx);
		td.setAccountId(accountId);
		td.setType(TransactionType.DEPOSIT);
		td.setUnitCode(unitCode);
		td.setAmount(amount);

		Date expireDate = ExpireDateCalculator.getExpireDate(transDate);

		// change balance
		List<AccountBalance> balances = accountBalanceDao
				.findBalanceByUnitCodeAndExpireDate(accountId, unitCode,
						expireDate);

		if (balances.size() > 1) {
			throw new IllegalStateException(
					"It is not permit to exist more than one balance with the same accountid/unitcode/expiredate!");
		} else if (balances.size() == 1) {
			AccountBalance balance = balances.get(0);
			// XXX here should use bigdecimal better
			double expectBalance = balance.getBalance() + amount;
			balance.setBalance(expectBalance);
			balance.setLastUpdateAt(transDate);
			accountBalanceDao.update(balance);
		} else {
			// exist nothing, create a new
			AccountBalance balance = new AccountBalance();
			logger.debug("accountId={}", accountId);
			Account account = accountDao.findById(Account.class, accountId);
			TxUnit unit = txUnitDao.findUnitByCode(unitCode);
			balance.setAccount(account);
			balance.setUnit(unit);
			balance.setBalance(amount);
			balance.setExpireDate(expireDate);
			balance.setCreateAt(transDate);
			balance.setLastUpdateAt(transDate);
			accountBalanceDao.save(balance);
		}

		return td.getId();
	}

	@Override
	public String withdraw(Transaction tx, String accountId, String unitCode,
			double amount, Date transDate) {
		TransactionDetail td = new TransactionDetail();
		td.setTransaction(tx);
		td.setAccountId(accountId);
		td.setType(TransactionType.WITHDRAW);
		td.setUnitCode(unitCode);
		td.setAmount(amount);

		// change balance
		List<AccountBalance> balances = accountBalanceDao
				.findBalanceByUnitCode(accountId, unitCode);
		logger.debug("The found balance size={}", balances.size());
		Iterator<AccountBalance> it = balances.iterator();
		double remain = amount;
		while (it.hasNext()) {
			AccountBalance balance = it.next();
			logger.debug("balance={}", balance.getBalance());
			if (balance.getBalance() >= remain) {
				double expect = balance.getBalance() - remain;
				balance.setBalance(expect);
				balance.setLastUpdateAt(transDate);
				accountBalanceDao.update(balance);
				remain = 0;
				break;
			} else {
				remain = remain - balance.getBalance();
				accountBalanceDao.delete(balance);
			}
		}

		if (remain > 0) {
			throw new RuntimeException("Should rollback!" + remain);
		}

		return td.getId();
	}

	@Override
	public double getBalance(String accountId, String unitCode) {
		List<AccountBalance> balances = accountBalanceDao
				.findBalanceByUnitCode(accountId, unitCode);
		Iterator<AccountBalance> it = balances.iterator();
		double sum = 0;
		while (it.hasNext()) {
			AccountBalance balance = it.next();
			sum += balance.getBalance();
		}
		return sum;
	}

	@Override
	public Account createNewAccount() {
		Date now = new Date();
		Account account = new Account();
		account.setCreateAt(now);
		accountDao.save(account);
		return account;
	}

	@Override
	public TxUnit createNewUnit(String name, String unitCode, double rate)
			throws DuplicateUnitCodeException {
		TxUnit unit = txUnitDao.findUnitByCode(unitCode);
		if (unit != null) {
			throw new DuplicateUnitCodeException();
		}
		unit = new TxUnit();
		unit.setName(name);
		unit.setCode(unitCode);
		unit.setRate(rate);
		txUnitDao.save(unit);

		return unit;
	}

	@Override
	public TxUnit getTxUnitByCode(String code) {
		return txUnitDao.findUnitByCode(code);
	}
}
