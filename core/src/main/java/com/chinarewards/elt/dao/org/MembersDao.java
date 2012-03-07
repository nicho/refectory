package com.chinarewards.elt.dao.org;

import java.util.List;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.org.Members;

/**
 * Dao of {@link Members}
 * 
 * @author lw
 * @since 1.0
 */
public class MembersDao extends BaseDao<Members> {

	@SuppressWarnings("unchecked")
	/**
	 * 得到得有人员
	 * @param teamId
	 * @return
	 */
	public List<Members> findMemberByTeam(String teamId) {
		return getEm()
				.createQuery(
						"FROM Members m WHERE m.team.id =:teamId")
				.setParameter("teamId", teamId).getResultList();
	}
	/**
	 * 得到列表要用的负责人
	 * @param teamId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Members> findStaffByTeam(String teamId,int type) {
		return getEm()
				.createQuery("FROM Members m WHERE m.team.id =:teamId and m.memberType =:type")
				.setParameter("teamId", teamId)
				.setParameter("type", type)
				.getResultList();
	}
	/**
	 * 根据员工ID.获取小组IDs
	 * @param teamId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> findTeamIdsListByStaffId(String staffId) {
		return getEm()
				.createQuery("SELECT m.team.id FROM Members m WHERE m.staff.id =:staffId")
				.setParameter("staffId", staffId)
				.getResultList();
	}

}
