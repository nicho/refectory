package com.chinarewards.elt.service.broadcast.impl;

import java.util.ArrayList;
import java.util.List;

import com.chinarewards.elt.dao.broadcast.BroadcastDao;
import com.chinarewards.elt.dao.broadcast.BroadcastReplyDao;
import com.chinarewards.elt.dao.broadcast.BroadcastingReceivingDao;
import com.chinarewards.elt.dao.broadcast.ReceivingObjectDao;
import com.chinarewards.elt.dao.org.MembersDao;
import com.chinarewards.elt.dao.user.UserDao;
import com.chinarewards.elt.domain.information.BroadcastReply;
import com.chinarewards.elt.domain.information.Broadcasting;
import com.chinarewards.elt.domain.information.BroadcastingReceiving;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.broadcast.BroadcastQueryListCriteria;
import com.chinarewards.elt.model.broadcast.BroadcastQueryListVo;
import com.chinarewards.elt.model.broadcastReply.BroadcastReplyListCriteria;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.information.BroadcastMessage;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.broadcast.BroadcastLogic;
import com.chinarewards.elt.service.user.UserLogic;
import com.chinarewards.elt.util.DateUtil;
import com.chinarewards.elt.util.StringUtil;
import com.google.inject.Inject;

public class BroadcastLogicImpl implements BroadcastLogic {
	private final BroadcastDao broadcastDao;
	private final BroadcastingReceivingDao broadcastingReceivingDao;
	private final ReceivingObjectDao receivingObjectDao;
	private final BroadcastReplyDao broadcastReplyDao;
	private final UserLogic userLogic;
	private final MembersDao membersDao;
	private final UserDao userDao;

	@Inject
	public BroadcastLogicImpl(BroadcastDao broadcastDao,
			BroadcastingReceivingDao broadcastingReceivingDao,
			ReceivingObjectDao receivingObjectDao,
			BroadcastReplyDao broadcastReplyDao, UserLogic userLogic,
			MembersDao membersDao, UserDao userDao) {
		this.broadcastDao = broadcastDao;
		this.broadcastingReceivingDao = broadcastingReceivingDao;
		this.receivingObjectDao = receivingObjectDao;
		this.broadcastReplyDao = broadcastReplyDao;
		this.userLogic = userLogic;
		this.membersDao = membersDao;
		this.userDao = userDao;
	}

	@Override
	public Broadcasting createOrUpdateBroadcast(Broadcasting broadcast) {
		if (StringUtil.isEmptyString(broadcast.getId())) {
			return broadcastDao.save(broadcast);
		} else {
			return broadcastDao.update(broadcast);
		}
	}

	@Override
	public BroadcastQueryListVo queryBroadcastList(
			BroadcastQueryListCriteria criteria) {
		// 如果传入员工ID,查询userID
		if (!StringUtil.isEmptyString(criteria.getStaffId())) {
			SysUser user = userDao.findUserByStaffId(criteria.getStaffId());
			if (user != null)
				criteria.setCreateUserId(user.getId());
			else
				criteria.setCreateUserId("notUser");
		}
		if (!StringUtil.isEmptyString(criteria.getRecevingStaffId())) {
			SysUser user = userDao.findUserByStaffId(criteria.getRecevingStaffId());
			if (user != null)
				criteria.setReceivingUserId(user.getId());
			else
				criteria.setReceivingUserId("notUser");
		}
		if (!StringUtil.isEmptyString(criteria.getReceivingUserId())) {
			if (!StringUtil.isEmptyString(criteria.getRecevingStaffId())) {
				List<String> broadcastList = broadcastingReceivingDao
						.findBroadcastingReceivingIdList(null, null,
								criteria.getRecevingStaffId(), null,
								criteria.getBroadcastMessagetype());
				if (broadcastList.size() > 0) {
					criteria.setBroadcastList(broadcastList);
				} else {
					List<String> broadcastListnew = new ArrayList<String>();
					broadcastListnew.add("notbroadcast");
					// 没有广播..加入空
					criteria.setBroadcastList(broadcastListnew);
				}
			} else {
				// 查询接收对象的broadcastID LIST
				SysUser user = userLogic.findUserById(criteria
						.getReceivingUserId());
				String deptId = null;
				List<String> teamIds = null;
				if (user.getStaff().getDepartment() != null)
					deptId = user.getStaff().getDepartment().getId();
				List<String> tList = membersDao.findTeamIdsListByStaffId(user
						.getStaff().getId());
				if (tList.size() > 0)
					teamIds = tList;
				List<String> broadcastList = broadcastingReceivingDao
						.findBroadcastingReceivingIdList(user.getCorporation()
								.getId(), deptId, user.getStaff().getId(),
								teamIds, criteria.getBroadcastMessagetype());
				if (broadcastList.size() > 0) {
					criteria.setBroadcastList(broadcastList);
				} else {
					List<String> broadcastListnew = new ArrayList<String>();
					broadcastListnew.add("notbroadcast");
					// 没有广播..加入空
					criteria.setBroadcastList(broadcastListnew);
				}
			}
		}
		return broadcastDao.queryBroadcastPageAction(criteria);
	}

	@Override
	public void addReplyNumber(Broadcasting broadcasting) {
		broadcasting.setReplyNumber(broadcasting.getReplyNumber() + 1);
		broadcastDao.update(broadcasting);

	}

	@Override
	public void minusReplyNumber(Broadcasting broadcasting) {
		broadcasting.setReplyNumber(broadcasting.getReplyNumber() - 1);
		broadcastDao.update(broadcasting);
	}

	@Override
	public Broadcasting findbroadcastingById(String broadcastingId) {
		return broadcastDao.findById(Broadcasting.class, broadcastingId);

	}

	@Override
	public void deletebroadcasting(Broadcasting broadcasting) {
		broadcastDao.delete(broadcasting);
	}

	@Override
	public void deleteBroadcastReceiving(String broadcastingId) {
		List<BroadcastingReceiving> broadcastingList = broadcastingReceivingDao
				.findBroadcastingReceivingList(broadcastingId);
		if (broadcastingList.size() > 0) {
			for (BroadcastingReceiving cast : broadcastingList) {
				broadcastingReceivingDao.delete(cast);
				receivingObjectDao.delete(cast.getReceiving());
			}
		}

	}

	@Override
	public String getMaxNumber(BroadcastMessage broadcastMessage) {
		String strNum;
		int num = (Integer
				.parseInt(broadcastDao.getMaxNumber(broadcastMessage)) + 1);
		if (num < 10)
			strNum = "00" + num;
		else if (num < 100)
			strNum = "0" + num;
		else
			strNum = num + "";

		return strNum;
	}

	@Override
	public List<BroadcastingReceiving> findBroadcastReceiving(
			String broadcastingId) {
		return broadcastingReceivingDao
				.findBroadcastingReceivingList(broadcastingId);
	}

	@Override
	public PageStore<BroadcastReply> findBroadcastReplyList(
			BroadcastReplyListCriteria criteria) {
		return broadcastReplyDao.findBroadcastReplyList(criteria);
	}

	@Override
	public BroadcastReply saveBroadcastReply(String broadcastId,
			String replyContent, UserContext context, String replyParentId) {
		Broadcasting broadcast = broadcastDao.findById(Broadcasting.class,
				broadcastId);
		SysUser nowUser = userLogic.findUserById(context.getUserId());
		BroadcastReply reply = new BroadcastReply();
		reply.setBroadcast(broadcast);
		reply.setReplyUser(nowUser);
		reply.setCorporation(nowUser.getCorporation());
		reply.setReplyContent(replyContent);
		reply.setReplyTime(DateUtil.getTime());
		reply.setLastModifiedAt(DateUtil.getTime());
		reply.setLastModifiedBy(nowUser);
		if (!StringUtil.isEmptyString(replyParentId))
			reply.setParent(broadcastReplyDao.findById(BroadcastReply.class,
					replyParentId));
		addReplyNumber(broadcast);
		return broadcastReplyDao.save(reply);
	}

}
