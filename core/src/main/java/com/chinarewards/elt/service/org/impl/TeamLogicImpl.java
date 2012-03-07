package com.chinarewards.elt.service.org.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.dao.org.MembersDao;
import com.chinarewards.elt.dao.org.StaffDao;
import com.chinarewards.elt.dao.org.TeamDao;
import com.chinarewards.elt.domain.org.Members;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.org.Team;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.org.search.TeamParam;
import com.chinarewards.elt.model.vo.TeamListVo;
import com.chinarewards.elt.service.org.TeamLogic;
import com.chinarewards.elt.util.DateUtil;
import com.chinarewards.elt.util.StringUtil;
import com.google.inject.Inject;

/**
 * 
 * 
 * @author lw
 * @since 1.0
 */
public  class TeamLogicImpl implements TeamLogic {



	Logger logger = LoggerFactory.getLogger(getClass());

	TeamDao teamDao;
	MembersDao membersDao;
	StaffDao staffDao;

	@Inject
	public TeamLogicImpl(TeamDao teamDao,MembersDao membersDao,StaffDao staffDao) {
			this.teamDao = teamDao;
			this.membersDao = membersDao;
			this.staffDao = staffDao;
	}

	@Override
	public TeamListVo findTeamById(String id) {
       Team team = teamDao.findById(Team.class, id);
       TeamListVo listvo = convertFromTeamToVo(team);
       return listvo;
	}
	@Override
	public String deleteTeam(String id)	throws Exception {
		Team team = teamDao.findById(Team.class, id);
		teamDao.delete(team);
		removeMembersByTeam(id);//删除人员
		return team.getId();
	}

	
	
	@Override
	public Team save(SysUser caller, TeamParam param) {
		Team itemObj = assembleTeamObject(caller, param);
		Date currTime = DateUtil.getTime();
		String teamCode = DateUtil.formatData("yyyyMMddhhmmss", currTime);
		if (StringUtil.isEmptyString(itemObj.getId())) {
			// Create
			itemObj.setDeleted(0);//正常状态，没有删除为0
			itemObj.setCreatedBy(caller);
			itemObj.setCorporation(caller.getCorporation());
			itemObj.setDepartment(caller.getStaff().getDepartment());
			itemObj.setCreatedAt(currTime);
			itemObj.setCode(teamCode);//用当前时间作为编号
			itemObj = teamDao.save(itemObj);
			
			
		} else {
			// Update
			itemObj = teamDao.findById(Team.class, itemObj.getId());
			itemObj.setCreatedBy(caller);
			itemObj.setCreatedAt(currTime);
			itemObj=teamDao.update(itemObj);
			removeMembersByTeam(itemObj.getId());//修改一律删除人员
		}
		// 增加负责人
		if (param.getFzIds() != null && !param.getFzIds().isEmpty()) {
			  saveMembers(caller, itemObj,param.getFzIds(),1);
		}
		// 增加成员
		if (param.getMembersIds() != null && !param.getMembersIds().isEmpty()) {
			  saveMembers(caller, itemObj,param.getMembersIds(),0);
		}
		return itemObj;
	}
	
	private Team assembleTeamObject(SysUser caller,	TeamParam param) {
		    Team item = new Team();
		if (StringUtil.isEmptyString(param.getId())) {
			item.setId(param.getId());
			item.setCode(param.getCode());//用当前时间作为编号
			item.setName(param.getName());
			item.setDescription(param.getDescription());
			
		} else {
			// Update a existed record
			item = teamDao.findById(Team.class, param.getId());
		}
				
		
		return item;
	}
	@Override
	public void removeMembersByTeam(String teamId) {
		List<Members> membersList = membersDao.findMemberByTeam(teamId);
		for (Members member : membersList) {
			membersDao.delete(member);
		}
	}
	@Override
	public PageStore<TeamListVo> teamList(SysUser caller,TeamListVo teamVo) {
        PageStore<Team> pageStore = new PageStore<Team>();
		
		pageStore.setResultCount(teamDao.countTeam(teamVo));
		List<Team> TeamList = teamDao.TeamList(teamVo);
		List<TeamListVo> TeamVoList = new ArrayList<TeamListVo>();
		for (Team team : TeamList) {
			TeamVoList.add(convertFromTeamToVo(team));
		}
		PageStore<TeamListVo> storeVo = new PageStore<TeamListVo>();
		storeVo.setResultCount(pageStore.getResultCount());
		storeVo.setResultList(TeamVoList);

		return storeVo;
	}

	private TeamListVo convertFromTeamToVo(Team team) {
		TeamListVo teamVo = new TeamListVo();
		teamVo.setId(team.getId());
		teamVo.setName(team.getName());
		teamVo.setCode(team.getCode());
		teamVo.setDescription(team.getDescription());
		List<Members> stafflist = membersDao.findMemberByTeam(team.getId());//查所有人   
		if(stafflist.size()>0){
			teamVo.setPeople(stafflist.size());
		}else{
			teamVo.setPeople(0);
		}
		List<Members> membersList = membersDao.findStaffByTeam(team.getId(), 0);//查成员 修改时显示 
		teamVo.setMembersList(membersList);	
		
		List<Members> managerlist = membersDao.findStaffByTeam(team.getId(), 1);//查负责人   修改时显示
		teamVo.setManagersList(managerlist);
		
		String manager="";
		if(managerlist.size()>0){
			for(int i=0;i<managerlist.size();i++){
				Members member = new Members();
				member =managerlist.get(i);
				manager=member.getStaff().getName()+"   "+manager;
			}
		}
		teamVo.setManager(manager);//得到负责人的名字在列表显示
		return teamVo;
	}

	@Override
	public void saveMembers(SysUser caller,Team team, List<String> staffIds,int type) {
		
		Date now = DateUtil.getTime();
		for (String id : staffIds) {
			Members member = new Members();
			Staff staff = staffDao.findById(Staff.class, id);
			member.setStaff(staff);
			member.setDeleted(0);
			member.setMemberType(type);
			member.setRecorddate(now);
			member.setTeam(team);
			member.setRecorduser(caller.getUserName());
			membersDao.save(member);
		}
				
	}
	
	@Override
	public Team findTeamBoById(String id) {
		return 	teamDao.findById(Team.class, id);
	}

}
