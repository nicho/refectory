package com.chinarewards.elt.tx.service.impl;

import java.util.Calendar;
import java.util.Date;

public class ExpireDateCalculator {

	public static Date getExpireDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		Calendar expect = Calendar.getInstance();

		// 两年后过期,今天是2011.5.12意味着 2013.6.1过期
		// 如果是今天是2011.5.25也是2013.6.1过期
		expect.set(Calendar.YEAR, c.get(Calendar.YEAR) + 2);
		expect.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
		expect.set(Calendar.DAY_OF_MONTH, 1);

		return expect.getTime();
	}
}
