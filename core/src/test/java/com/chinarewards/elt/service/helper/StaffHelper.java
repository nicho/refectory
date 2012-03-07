package com.chinarewards.elt.service.helper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.chinarewards.elt.domain.org.Corporation;
import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.org.StaffVo;
import com.chinarewards.elt.service.staff.StaffLogic;
import com.chinarewards.elt.tx.service.TransactionService;
import com.google.inject.Injector;

/**
 * Help us to get a list of useful {@link Staff}
 * 
 * @author yanxin
 * @since 1.0
 */
public class StaffHelper {

	private static List<Staff> staffList;
	private static List<String> staffListById;

	/**
	 * Get a default list of {@link Staff}
	 * 
	 * @return
	 */
	public static List<Staff> getDefaultStaffList(Injector injector) {
		EntityManager em = injector.getInstance(EntityManager.class);
		if (staffList != null
				&& em.find(Staff.class, staffList.get(0).getId()) != null)
			return staffList;
		staffList = new ArrayList<Staff>();
		// need some services
		StaffLogic staffLogic = injector.getInstance(StaffLogic.class);
		TransactionService transactionService = injector
				.getInstance(TransactionService.class);

		SysUser caller = UserHelper.getDefaultUser(injector);
		Corporation corp = CorporationHelper.getDefaultCorporation(injector);
		Department dept = DepartmentHelper.getDefaultDept(injector);
		StaffVo one = new StaffVo();
		one.setCorpId(corp.getId());
		one.setDeptId(dept.getId());
		one.setName("mervyn");
		// create a tx account
		String accountId = transactionService.createNewAccount();
		one.setTxAccountId(accountId);
		Staff sone = staffLogic.saveStaff(caller, one);
		staffList.add(sone);

		StaffVo two = new StaffVo();
		two.setCorpId(corp.getId());
		two.setDeptId(dept.getId());
		two.setName("damon");
		// create a tx account
		accountId = transactionService.createNewAccount();
		two.setTxAccountId(accountId);
		Staff stwo = staffLogic.saveStaff(caller, two);
		staffList.add(stwo);

		StaffVo three = new StaffVo();
		three.setCorpId(corp.getId());
		three.setDeptId(dept.getId());
		three.setName("rock");
		// create a tx account
		accountId = transactionService.createNewAccount();
		three.setTxAccountId(accountId);
		Staff sthree = staffLogic.saveStaff(caller, three);
		staffList.add(sthree);

		return staffList;
	}
	
	public static List<String> getDefaultStaffListById(Injector injector) {
		EntityManager em = injector.getInstance(EntityManager.class);
		if (staffListById != null
				&& em.find(Staff.class, staffListById.get(0)) != null)
			return staffListById;
		staffListById = new ArrayList<String>();
		// need some services
		StaffLogic staffLogic = injector.getInstance(StaffLogic.class);
		TransactionService transactionService = injector
				.getInstance(TransactionService.class);

		SysUser caller = UserHelper.getDefaultUser(injector);
		Corporation corp = CorporationHelper.getDefaultCorporation(injector);
		Department dept = DepartmentHelper.getDefaultDept(injector);
		StaffVo one = new StaffVo();
		one.setCorpId(corp.getId());
		one.setDeptId(dept.getId());
		one.setName("mervyn");
		// create a tx account
		String accountId = transactionService.createNewAccount();
		one.setTxAccountId(accountId);
		Staff sone = staffLogic.saveStaff(caller, one);
		staffListById.add(sone.getId());

		StaffVo two = new StaffVo();
		two.setCorpId(corp.getId());
		two.setDeptId(dept.getId());
		two.setName("damon");
		// create a tx account
		accountId = transactionService.createNewAccount();
		two.setTxAccountId(accountId);
		Staff stwo = staffLogic.saveStaff(caller, two);
		staffListById.add(stwo.getId());

		StaffVo three = new StaffVo();
		three.setCorpId(corp.getId());
		three.setDeptId(dept.getId());
		three.setName("rock");
		// create a tx account
		accountId = transactionService.createNewAccount();
		three.setTxAccountId(accountId);
		Staff sthree = staffLogic.saveStaff(caller, three);
		staffListById.add(sthree.getId());

		return staffListById;
	}
}
