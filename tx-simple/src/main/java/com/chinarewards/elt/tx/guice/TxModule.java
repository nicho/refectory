package com.chinarewards.elt.tx.guice;

import com.chinarewards.elt.tx.dao.AccountBalanceDao;
import com.chinarewards.elt.tx.dao.AccountDao;
import com.chinarewards.elt.tx.dao.TransactionDao;
import com.chinarewards.elt.tx.dao.TransactionDetailDao;
import com.chinarewards.elt.tx.dao.TxBaseDao;
import com.chinarewards.elt.tx.dao.TxUnitDao;
import com.chinarewards.elt.tx.service.TransactionLogic;
import com.chinarewards.elt.tx.service.TransactionService;
import com.chinarewards.elt.tx.service.impl.TransactionLogicImpl;
import com.chinarewards.elt.tx.service.impl.TransactionServiceImpl;
import com.google.inject.AbstractModule;

public class TxModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TxBaseDao.class);
		bind(TransactionDao.class);
		bind(TxUnitDao.class);
		bind(AccountDao.class);
		bind(AccountBalanceDao.class);
		bind(TransactionDetailDao.class);

		bind(TransactionLogic.class).to(TransactionLogicImpl.class);
		bind(TransactionService.class).to(TransactionServiceImpl.class);
	}

}
