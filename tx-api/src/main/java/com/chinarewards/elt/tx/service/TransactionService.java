package com.chinarewards.elt.tx.service;

import com.chinarewards.elt.tx.exception.BalanceLackException;
import com.chinarewards.elt.tx.exception.DuplicateUnitCodeException;
import com.chinarewards.elt.tx.model.Unit;

public interface TransactionService {

	public String deposit(String accountId, String unitCode, double amount);

	public String withdraw(String accountId, String unitCode, double amount)
			throws BalanceLackException;

	public String transaction(String fromAccountId, String toAccountId,
			String unitCode, double amount) throws BalanceLackException;

	public double getBalance(String accountId, String unitCode);

	public String createNewAccount();

	public Unit createNewUnit(String name, String unitCode, double rate)
			throws DuplicateUnitCodeException;

	public Unit getUnitInfoByUnitCode(String unitCode);
}