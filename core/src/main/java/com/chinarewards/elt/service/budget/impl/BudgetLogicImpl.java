package com.chinarewards.elt.service.budget.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.dao.budget.CorpBudgetDao;
import com.chinarewards.elt.dao.budget.DepartmentBudgetDao;
import com.chinarewards.elt.dao.org.DepartmentDao;
import com.chinarewards.elt.dao.org.StaffDao;
import com.chinarewards.elt.domain.budget.CorpBudget;
import com.chinarewards.elt.domain.budget.DepartmentBudget;
import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.budget.search.DepartmentBudgetVo;
import com.chinarewards.elt.model.budget.search.IntegralManagementVo;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.model.user.UserRole;
import com.chinarewards.elt.model.vo.StaffSearchVo;
import com.chinarewards.elt.service.budget.BudgetLogic;
import com.chinarewards.elt.service.org.DepartmentLogic;
import com.chinarewards.elt.service.user.UserLogic;
import com.chinarewards.elt.util.DateUtil;
import com.chinarewards.elt.util.StringUtil;
import com.google.inject.Inject;

public class BudgetLogicImpl implements BudgetLogic {
	private DepartmentBudgetDao departmentBudgetDao;
	private CorpBudgetDao corpBudgetDao;
	private DepartmentDao departmentDao;
	private DepartmentLogic departmentLogic;
	private UserLogic userLogic;
	private StaffDao   staffDao;
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	protected BudgetLogicImpl(DepartmentBudgetDao departmentBudgetDao,UserLogic userLogic,
			DepartmentDao departmentDao, CorpBudgetDao corpBudgetDao,StaffDao   staffDao,
			DepartmentLogic departmentLogic) {
		this.departmentBudgetDao = departmentBudgetDao;
		this.departmentDao = departmentDao;
		this.corpBudgetDao = corpBudgetDao;
		this.departmentLogic = departmentLogic;
		this.staffDao  = staffDao;
		this.userLogic = userLogic;
	}

	@Override
	public CorpBudget saveCorpBudget(SysUser caller, CorpBudget corpBudget) {
		Date currTime = DateUtil.getTime();

		if (StringUtil.isEmptyString(corpBudget.getId())) {
			// Create
			corpBudget.setDeleted(0);// 正常状态，没有删除为0
			corpBudget.setRecorddate(currTime);
			corpBudget.setRecorduser(caller.getUserName());
			corpBudget.setRecorduser(caller.getUserName());
			corpBudget.setCorporationId(caller.getCorporation().getId());
			corpBudgetDao.save(corpBudget);
		} else {
			// Update
			CorpBudget tempCorpBudget = corpBudgetDao.findById(
					CorpBudget.class, corpBudget.getId());
			tempCorpBudget.setId(corpBudget.getId());
			tempCorpBudget.setBudgetTitle(corpBudget.getBudgetTitle());
			tempCorpBudget.setMoneyType(corpBudget.getMoneyType());
			tempCorpBudget.setBudgetAmount(corpBudget.getBudgetAmount());
			tempCorpBudget.setBudgetIntegral(corpBudget.getBudgetIntegral());
			tempCorpBudget.setBeginDate(corpBudget.getBeginDate());
			tempCorpBudget.setEndDate(corpBudget.getEndDate());

			tempCorpBudget.setRecorddate(currTime);
			tempCorpBudget.setRecorduser(caller.getUserName());
			corpBudgetDao.update(tempCorpBudget);
		}

		return corpBudget;
	}
    
	@Override
	public DepartmentBudget saveDepartmentBudget(UserContext context,DepartmentBudget departmentBudget) {
		Date currTime = DateUtil.getTime();
		SysUser caller = userLogic.findUserById(context.getUserId());
		CorpBudget corpBudget= corpBudgetDao.findById(CorpBudget.class,departmentBudget.getCorpBudgetId());
		//Department sonDepartment= departmentDao.findById(Department.class,departmentBudget.getDepartmentId());//得到操作的部门ID
		// Department ParentDepartment= departmentDao.findById(Department.class,sonDepartment.getParent().getId());//得到操作的部门的父部门
		List<UserRole> roleList = Arrays.asList(context.getUserRoles());//得到角色是HR还是leader
		if (StringUtil.isEmptyString(departmentBudget.getId())) {
			// Create
			departmentBudget.setDeleted(0);// 正常状态，没有删除为0
			departmentBudget.setRecorduser(caller.getUserName());
			departmentBudget.setUseIntegeral(0);//已用的积分为0
			departmentBudget = departmentBudgetDao.save(departmentBudget);
			if (roleList.contains(UserRole.CORP_ADMIN)){//是HR就更新企业的财年预算
			 //更新企业财年的已用积分
			  
			  corpBudget.setUseIntegeral(corpBudget.getUseIntegeral()+departmentBudget.getBudgetIntegral());
			  corpBudgetDao.update(corpBudget);
		     }else if (roleList.contains(UserRole.DEPT_MGR)){
		    	  //得到父部门的预算,getCorpBudgetId得到的是父部门的ID
		    	  DepartmentBudget ParentdepartmentBudget= departmentBudgetDao.findById(DepartmentBudget.class,departmentBudget.getCorpBudgetId());
		    	  ParentdepartmentBudget.setUseIntegeral(ParentdepartmentBudget.getUseIntegeral()+departmentBudget.getBudgetIntegral());
		    	//更新父部门财年的已用积分
		    	  departmentBudgetDao.update(ParentdepartmentBudget);
			 }
		} else {
			// Update
			double newBudgetIntegral = departmentBudget.getBudgetIntegral();
			DepartmentBudget depBudget = departmentBudgetDao.findById(DepartmentBudget.class, departmentBudget.getId());
			double oldBudgetIntegral = depBudget.getBudgetIntegral();
			double sc =oldBudgetIntegral-newBudgetIntegral;//得到部门以前的积分与要修改的新设置的积分差
			departmentBudget.setRecorddate(currTime);
			departmentBudget.setRecorduser(caller.getUserName());
			departmentBudget = departmentBudgetDao.findById(DepartmentBudget.class, departmentBudget.getId());
			departmentBudget.setBudgetIntegral(newBudgetIntegral);
			departmentBudgetDao.update(departmentBudget);
			if (roleList.contains(UserRole.CORP_ADMIN)){//是HR就更新企业的财年预算
			   corpBudget.setUseIntegeral(corpBudget.getUseIntegeral()-sc);//修改财年的已使用的积分
			   corpBudgetDao.update(corpBudget);
			}else if (roleList.contains(UserRole.DEPT_MGR)){
		    	  //得到父部门的预算,getCorpBudgetId得到的是父部门的ID
				  DepartmentBudget ParentdepartmentBudget= departmentBudgetDao.findById(DepartmentBudget.class,departmentBudget.getCorpBudgetId());
		    	  ParentdepartmentBudget.setUseIntegeral(ParentdepartmentBudget.getUseIntegeral()-sc);
		    	//更新父部门财年的已用积分
		    	  departmentBudgetDao.update(ParentdepartmentBudget);
			 }
		}

		return departmentBudget;
	}

	@Override
	public CorpBudget findCorpBudgetById(String id) {
		return corpBudgetDao.findById(CorpBudget.class, id);
	}
	@Override
	public List<CorpBudget> findCorpBudget(String corpid) {
		return corpBudgetDao.findCorpBudget(corpid);
	}
	@Override
	public List<DepartmentBudget> findDepartBudget(String depId) {
		return departmentBudgetDao.findDepartBudget(depId);
	}
	@Override
	public CorpBudget findCorpBudgetByCorpId(String corpid) {
		return corpBudgetDao.findByCorpId(corpid);
	}

	@Override
	public DepartmentBudget findDepartmentBudgetById(String id) {

		return departmentBudgetDao.findById(DepartmentBudget.class, id);
	}

	@Override
	public String deleteDepartmentBudget(SysUser caller, String id) {
		Date currTime = DateUtil.getTime();
		DepartmentBudget departmentBudget = departmentBudgetDao.findById(
				DepartmentBudget.class, id);
		departmentBudget.setDeleted(1);
		departmentBudget.setRecorddate(currTime);
		departmentBudget.setRecorduser(caller.getUserName());
		departmentBudget = departmentBudgetDao.update(departmentBudget);
		return departmentBudget.getId();
	}

	@Override
	public PageStore<DepartmentBudgetVo> deptBudgetList(SysUser caller,
			DepartmentBudgetVo departmentBudgetVo) {

		PageStore<DepartmentBudget> pageStore = new PageStore<DepartmentBudget>();

		pageStore.setResultCount(departmentBudgetDao
				.countDepartmentBudget(departmentBudgetVo));
		List<DepartmentBudget> BudgetList = departmentBudgetDao
				.departmentBudgetList(departmentBudgetVo);
		List<DepartmentBudgetVo> BudgetVoList = new ArrayList<DepartmentBudgetVo>();
		for (DepartmentBudget order : BudgetList) {
			BudgetVoList.add(convertFromBudgetToVo(order));
		}
		PageStore<DepartmentBudgetVo> storeVo = new PageStore<DepartmentBudgetVo>();
		storeVo.setResultCount(pageStore.getResultCount());
		storeVo.setResultList(BudgetVoList);

		return storeVo;
	}

	private DepartmentBudgetVo convertFromBudgetToVo(
			DepartmentBudget departmentBudget) {
		DepartmentBudgetVo departmentBudgetVo = new DepartmentBudgetVo();
		departmentBudgetVo.setBudgetIntegral(departmentBudget.getBudgetIntegral());
		departmentBudgetVo.setCorpBudgetId(departmentBudget.getCorpBudgetId());
		departmentBudgetVo.setDeleted(departmentBudget.getDeleted());
		departmentBudgetVo.setDepartmentId(departmentBudget.getDepartmentId());
		departmentBudgetVo.setId(departmentBudget.getId());
		departmentBudgetVo.setUseIntegeral(departmentBudget.getUseIntegeral());
		Department department = departmentDao.findById(Department.class,departmentBudget.getDepartmentId());
		StaffSearchVo searchVo = new StaffSearchVo ();
		int people =0;
		List<Department>  list=departmentLogic.getWholeChildren(departmentBudget.getDepartmentId(),true);//得到子部门及本身
		if(list.size()>0){
			Department depar = new Department();
			for(int i=0;i<list.size();i++){
				depar = list.get(i);
				searchVo.setDeptId(depar.getId());
				 people = staffDao.queryStaffPageActionCount(searchVo)+people;//查找部门人数
				
			}
		}
		departmentBudgetVo.setPeople(people);
		departmentBudgetVo.setDepartmentName(department.getName());

		return departmentBudgetVo;
	}

	@Override

	public DepartmentBudget findByDepAndCorpBudgetId(DepartmentBudget departmentBudget) {
		return departmentBudgetDao.findByDepAndCorpBudgetId(departmentBudget);//是否已经有了数据
	}
	public List<IntegralManagementVo> getIntegralManagementList(String corpId,String corpBudgetId) {
		List<IntegralManagementVo> volist = new ArrayList<IntegralManagementVo>();
		List<Department> department = departmentLogic
				.getWholeDepartmentsOfCorporation(corpId);
		for (Department dep : department) {
			IntegralManagementVo vo = new IntegralManagementVo();
			vo.setDepartmentId(dep.getId());
			vo.setDepartmentName(dep.getName());
			if(dep.getParent()!=null)
			vo.setParentId(dep.getParent().getId());
			vo.setLeaf(departmentLogic.isLeaf(dep));
			DepartmentBudget budget = this
					.findDepartmentBudgetByDepartmentId(dep.getId(),corpBudgetId);
			if (budget != null) {
				vo.setCorpBudgetId(budget.getCorpBudgetId());
				vo.setBudgetIntegral(budget.getBudgetIntegral());
				vo.setUseIntegeral(budget.getUseIntegeral());
			}
			volist.add(vo);
		}

		return volist;
	}

	@Override
	public DepartmentBudget findDepartmentBudgetByDepartmentId(	String departmentId,String corpBudgetId) {
		return departmentBudgetDao
				.findDepartmentBudgetByDepartmentId(departmentId,corpBudgetId);
	}

}
