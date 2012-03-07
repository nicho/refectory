package com.chinarewards.elt.service.broadcast;

import java.util.List;

import com.chinarewards.elt.domain.information.BroadcastReply;
import com.chinarewards.elt.domain.information.Broadcasting;
import com.chinarewards.elt.domain.information.BroadcastingReceiving;
import com.chinarewards.elt.model.broadcast.BroadcastAndReplyQueryListVo;
import com.chinarewards.elt.model.broadcast.BroadcastQueryListCriteria;
import com.chinarewards.elt.model.broadcast.BroadcastQueryListVo;
import com.chinarewards.elt.model.broadcast.BroadcastingVo;
import com.chinarewards.elt.model.broadcastReply.BroadcastReplyListCriteria;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.information.BroadcastingCategory;
import com.chinarewards.elt.model.user.UserContext;

public interface BroadcastService {
	/**
	 * 广播列表
	 * 
	 * @param broadcast
	 * @return
	 */
	public BroadcastQueryListVo queryBroadcastList(
			BroadcastQueryListCriteria criteria);
	/**
	 * 信息列表
	 * 
	 * @param broadcast
	 * @return
	 */
	public BroadcastQueryListVo queryMessageList(
			BroadcastQueryListCriteria criteria);
	/**
	 * 创建 and 修改..广播
	 * 
	 * @param staffProcess
	 * @return
	 */
	public String createOrUpdateBroadcast(BroadcastingVo broadcast,UserContext context,BroadcastingCategory broadcastingCategory);
	
	/**
	 * 创建 and 修改..信息
	 * 
	 * @param staffProcess
	 * @return
	 */
	public String createOrUpdateMessage(BroadcastingVo broadcast,UserContext context);
	/**
	 * 查询广播详细(包括回复)
	 * @param broadcastId
	 * @return
	 */
	public BroadcastAndReplyQueryListVo findBroadcastById(String broadcastId);
	/**
	 * 查询广播详细(不包括回复)
	 * @param broadcastId
	 * @return
	 */
	public Broadcasting findBroadcast2ById(String broadcastId);
	
	/**
	 * 获取 发送对象数据
	 * @param broadcastingId
	 */
	public List<BroadcastingReceiving> findBroadcastReceiving(String broadcastingId);
	
	/**
	 * 查询回复列表
	 * @param broadcastId
	 * @return
	 */
	public PageStore<BroadcastReply> findBroadcastReplyList(BroadcastReplyListCriteria criteria);
	
	/**
	 * 保存广播回复
	 * @param broadcastId
	 * @return
	 */
	public BroadcastReply saveBroadcastReply(String broadcastId,String replyContent,UserContext context,String replyParentId);
}
