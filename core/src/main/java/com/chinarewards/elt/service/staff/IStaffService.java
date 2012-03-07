package com.chinarewards.elt.service.staff;

import java.util.List;

import com.chinarewards.elt.dao.org.StaffDao.QueryStaffPageActionResult;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.staff.StaffProcess;
import com.chinarewards.elt.model.staff.StaffSearchCriteria;
import com.chinarewards.elt.model.staff.StaffWinSearchCriteria;
import com.chinarewards.elt.model.staff.StaffWinVo;
import com.chinarewards.elt.model.user.GeneratedUserConstants;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.model.user.UserRole;
import com.chinarewards.elt.model.vo.WinnersRecordQueryResult;
import com.chinarewards.elt.model.vo.WinnersRecordQueryVo;
import com.chinarewards.gwt.elt.model.staff.StaffUserProcess;


public interface IStaffService {

	public String createStaff(StaffUserProcess staffProcess);
	
	/**
	 * 查询员工得获奖统计信息
	 * 
	 * @param queryVo
	 * @param filterByAcl
	 *            set <code>true</code> to limit data visibility by the 
	 *            caller. 
	 * @return
	 * @author roger
	 * @since 2010-12-27
	 */
	public PageStore<WinnersRecordQueryResult> queryWinnerRecords(WinnersRecordQueryVo queryVo,String corporationId, boolean filterByAcl);
	
	/**
	 * 获取员工账户积分数值
	 * @param staffId
	 * @return
	 */
	public double getBalance(String staffId);
	/**
	 * 查询员工列表
	 * @param criteria
	 * @param context
	 * @return
	 */
	public QueryStaffPageActionResult queryStaffList(StaffSearchCriteria criteria,UserContext context);
	/**
	 * 创建 and 修改..员工
	 * @param staffProcess,context
	 * @return
	 */
	public String createOrUpdateStaff(StaffProcess staff,UserContext context);
	/**
	 * 查询员工信息
	 * @param staffId
	 * @return
	 */
	public Staff findStaffById(String staffId);
	
	/**
	 * 查询员工获奖信息
	 * @param staffId
	 * @return
	 */
	public StaffWinVo findStaffWinReward(StaffWinSearchCriteria criteria);
	
	/**
	 * 根据员工生成账户
	 * @param staffId,context
	 * @return
	 */
	public GeneratedUserConstants generatedUserbyStaff(String staffId,UserContext context);
	
	public String createHrUser(StaffUserProcess staff);
	/**
	 * 更新颁奖通知时间
	 */
	/**
	 * 查询员工信息
	 * @param staffId
	 * @return
	 */
	public Staff updateLeadTime(String staffId,int leadTime);
	
	/**
	 * 查询员工角色
	 * @param staffId
	 * @return
	 */
	public List<UserRole> findUserRoles(String staffId);
}
