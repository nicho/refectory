package com.chinarewards.elt.dao.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.org.Team;
import com.chinarewards.elt.model.vo.TeamListVo;
import com.chinarewards.elt.util.StringUtil;

/**
 * Dao of {@link Team}
 * 
 * @author lw
 * @since 1.0
 */
public class TeamDao extends BaseDao<Team> {

	@SuppressWarnings("unchecked")
	public List<Team> TeamList(TeamListVo TeamVo) {
		List<Team> result = new ArrayList<Team>();

		Query query = getFetchTeamQuery(SEARCH, TeamVo);

		result = query.getResultList();
		
		return result;
	}

	public int countTeam(TeamListVo TeamVo) {
		
		int count = 0;
		Query query = getFetchTeamQuery(COUNT, TeamVo);
		if (query.getSingleResult() != null)
			count = Integer.parseInt(query.getSingleResult().toString());
		logger.debug(" finshed by Team method, result count : {}", count);
		return count;
	}

	private Query getFetchTeamQuery(String type, TeamListVo vo) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer eql = new StringBuffer();

		if (SEARCH.equals(type)) {
			eql.append(" SELECT o FROM Team o  where   o.deleted= 0");
			
		} else if (COUNT.equals(type)) {
			eql.append(" SELECT COUNT(o) FROM Team o where  o.deleted= 0");
			
		}
		
		if (!StringUtil.isEmptyString(vo.getName())) {//查询姓名或编码时
			eql.append(" AND o.name LIKE :name ");
			param.put("name", "%" + vo.getName().trim().toUpperCase()+ "%");
		}
		if (!StringUtil.isEmptyString(vo.getCorpid())) {//
			eql.append(" AND o.corporation.id = :corpId ");
			param.put("corpId",  vo.getCorpid().trim());
		}
		
		if (SEARCH.equals(type)) {
			if (vo.getSortingDetail() != null) {
				eql.append(" ORDER BY o."
						+ vo.getSortingDetail().getSort() + " "
						+ vo.getSortingDetail().getDirection());
			}
		}
		System.out.println("EQL : " + eql);
		Query query = getEm().createQuery(eql.toString());
		if (SEARCH.equals(type)) {
			if (vo.getPaginationDetail() != null) {
				int start = vo.getPaginationDetail().getStart();
				int limit = vo.getPaginationDetail().getLimit();
				
				query.setFirstResult(start);
				query.setMaxResults(limit);
			}
		}
		if (param.size() > 0) {
			Set<String> key = param.keySet();
			for (String s : key) {
				query.setParameter(s, param.get(s));
			}
		}

		return query;
	}
	
	
	
}
