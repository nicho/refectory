package com.chinarewards.elt.dao.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import com.chinarewards.elt.common.BaseDao;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.user.UserSearchCriteria;
import com.chinarewards.elt.model.user.UserStatus;
import com.chinarewards.elt.util.StringUtil;

public class UserDao extends BaseDao<SysUser> {

	@SuppressWarnings("unchecked")
	public List<SysUser> findUserByUserName(String userName) {
		return getEm()
				.createQuery("FROM SysUser u WHERE u.userName = :userName")
				.setParameter("userName", userName).getResultList();
	}
	
	public String createUser(SysUser user) {
		this.save(user);	
		return user.getId();
	}

	
	public SysUser findUserById(String id) {
		SysUser user = (SysUser) getEm().createQuery("FROM SysUser u WHERE u.id = :id").setParameter("id", id).getSingleResult();
		return user;
	}
	@SuppressWarnings("unchecked")
	public SysUser findUserByNameAndPwd(String userName,String pwd) {
		List<SysUser> userList = getEm().createQuery("FROM SysUser u WHERE u.userName = :userName and u.password=:password").setParameter("userName", userName).setParameter("password", pwd).getResultList();
		SysUser user=null;
		if(userList.size()>0)
			user=userList.get(0);
		
		return user;
	}
	@SuppressWarnings("unchecked")
	public SysUser findUserByStaffId(String staffId) {
		List<SysUser> userList = getEm().createQuery("FROM SysUser u WHERE u.staff.id = :staffId ").setParameter("staffId", staffId).getResultList();
		SysUser user=null;
		if(userList.size()>0)
			user=userList.get(0);
		
		return user;
	}
	

	
	/**
	 * Returns the count about HrAdmin user. The UserType must be {@code
	 * UserType#HR_ADMIN}.
	 * 
	 * @param criteria
	 * @return
	 */
	public int countHrAdminUserByCriteria(UserSearchCriteria criteria) {
		Query q = buildQueryToSearchHrAdminUserPaging(criteria, COUNT);
		return Integer.parseInt(q.getSingleResult().toString());
	}
	/**
	 * Ignore case and fuzzy query when searching.
	 * 
	 * @param criteria
	 * @param method
	 * @return
	 */
	private Query buildQueryToSearchHrAdminUserPaging(UserSearchCriteria criteria,
			String method) {
		StringBuffer eql = new StringBuffer("");
		if (SEARCH.equals(method)) {
			eql.append("FROM SysUser u WHERE u.corporation is not null ");
		} else {
			eql.append("SELECT COUNT(u) FROM SysUser u WHERE u.corporation is not null ");
		}
		Map<String, Object> param = new HashMap<String, Object>();
		if (!StringUtil.isEmptyString(criteria.getAccountName())) {
			eql.append(" AND UPPER(u.userName) like :accountName");
			param.put("accountName", "%"
					+ criteria.getAccountName().toUpperCase() + "%");
		}

		if (!StringUtil.isEmptyString(criteria.getEmail())) {
			eql.append(" AND UPPER(u.staff.email) like :email");
			param.put("email", "%" + criteria.getEmail().toUpperCase() + "%");
		}

		if (!StringUtil.isEmptyString(criteria.getEnterpriseId())) {
			eql.append(" AND u.corporation.id = :enterpriseId");
			param.put("enterpriseId", criteria.getEnterpriseId());
		}
		
		if (!StringUtil.isEmptyString(criteria.getCorporationId())) {
			eql.append(" AND u.corporation.id = :corporationId");
			param.put("corporationId", criteria.getCorporationId());
		}

		if (!StringUtil.isEmptyString(criteria.getMobile())) {
			eql.append(" AND UPPER(u.staff.phone) like :mobile");
			param.put("mobile", "%" + criteria.getMobile().toUpperCase() + "%");
		}

		if (!StringUtil.isEmptyString(criteria.getStatus())) {
			eql.append(" AND u.status = :status");
			param.put("status", UserStatus.valueOf(criteria.getStatus()));
		}

		// eql.append(" AND u.userType = :userType");
		// param.put("userType", UserType.HR_ADMIN);
		Query q = getEm().createQuery(eql.toString());
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			q.setParameter(entry.getKey(), entry.getValue());
		}

		return q;
	}
	
	
	public PageStore<SysUser> queryUserPageAction(UserSearchCriteria criteria) {

		PageStore<SysUser> result = new PageStore<SysUser>();

		result.setResultList(this.userList(criteria));
		result.setResultCount(this.countuser(criteria));

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<SysUser> userList(UserSearchCriteria criteria) {
		List<SysUser> result = new ArrayList<SysUser>();

		Query query = getFetchUserQuery(SEARCH, criteria);

		result = query.getResultList();

		return result;
	}

	public int countuser(UserSearchCriteria criteria) {

		int count = 0;
		Query query = getFetchUserQuery(COUNT, criteria);
		if (query.getSingleResult() != null)
			count = Integer.parseInt(query.getSingleResult().toString());
		logger.debug(" finshed by user method, result count : {}", count);
		return count;
	}

	private Query getFetchUserQuery(String type, UserSearchCriteria vo) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer eql = new StringBuffer();

		if (SEARCH.equals(type)) {
			eql.append(" SELECT o FROM SysUser o  where  1=1 ");
		
		} else if (COUNT.equals(type)) {
			eql.append(" SELECT COUNT(o) FROM SysUser o where  1=1 ");

		}

		if (SEARCH.equals(type)) {
			if (vo.getSortingDetail() != null && vo.getSortingDetail().getSort()!=null && vo.getSortingDetail().getDirection()!=null) {
				eql.append(" user BY o." + vo.getSortingDetail().getSort()
						+ " " + vo.getSortingDetail().getDirection());
			}
		}
		System.out.println("EQL : " + eql);
		Query query = getEm().createQuery(eql.toString());
		if (SEARCH.equals(type)) {
			if (vo.getPaginationDetail() != null
					&& vo.getPaginationDetail().getLimit() != 0) {
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
