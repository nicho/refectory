package com.chinarewards.elt.service.org;

import java.util.List;

import com.chinarewards.elt.domain.org.Team;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.org.search.TeamParam;
import com.chinarewards.elt.model.vo.TeamListVo;

/**
 * 
 * 
 * @author lw
 * @since 1.0
 */
public interface TeamLogic {

	/**
	 * 保存
	 * @param user
	 * @param team
	 * @return
	 */
	public Team save(SysUser user, TeamParam team);
   /**
    * 保存成员
    * @param caller
    * @param teamObj
    * @param staffIds
    * @param type
    */
	public void saveMembers(SysUser caller,Team teamObj, List<String> staffIds,int type);
	/**
	 * 查找根据ID
	 * @param id
	 * @return
	 */
	public TeamListVo findTeamById(String id);
	/**
	 * 删除组根据ID
	 * @param id
	 * @return
	 * @throws TeamDeleteException 
	 */
	public String deleteTeam(String id) throws Exception;
	/**
	 * 组列表
	 * @param user
	 * @param team
	 * @return
	 */
	public PageStore<TeamListVo> teamList(SysUser user,TeamListVo teamListVo);

	/**
	 * 根据小组删除人员
	 * @param teamId
	 */
	public void removeMembersByTeam(String teamId) ;
	
	/**
	 * 查找根据ID.返回对象
	 * @param id
	 * @return
	 */
	public Team findTeamBoById(String id);
	
}
