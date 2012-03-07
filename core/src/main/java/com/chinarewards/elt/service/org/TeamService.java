package com.chinarewards.elt.service.org;

import com.chinarewards.elt.domain.org.Members;
import com.chinarewards.elt.domain.org.Team;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.org.search.TeamParam;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.model.vo.TeamListVo;

/**
 * Service of corporation.
 * 
 * @author lw
 * @since 1.5
 */
public interface TeamService {

	/**
	 * 保存
	 * @param context
	 * @param team
	 * @return
	 */
	public Team save(UserContext context, TeamParam team);
	/**
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
	 * @param context
	 * @param team
	 * @return
	 */
	public PageStore<TeamListVo> teamList(UserContext context,TeamListVo teamListVo);

	

}
