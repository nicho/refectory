package com.chinarewards.elt.service.broadcast;

import java.util.List;

import com.chinarewards.elt.domain.information.BroadcastReply;
import com.chinarewards.elt.domain.information.Broadcasting;
import com.chinarewards.elt.domain.information.BroadcastingReceiving;
import com.chinarewards.elt.model.broadcast.BroadcastQueryListCriteria;
import com.chinarewards.elt.model.broadcast.BroadcastQueryListVo;
import com.chinarewards.elt.model.broadcastReply.BroadcastReplyListCriteria;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.information.BroadcastMessage;
import com.chinarewards.elt.model.user.UserContext;

public interface BroadcastLogic {
	/**
	 * 创建 and 修改..广播(信息)
	 * 
	 * @param staffProcess
	 * @return
	 */
	public Broadcasting createOrUpdateBroadcast(Broadcasting broadcast);

	/**
	 * 广播列表
	 * 
	 * @param broadcast
	 * @return
	 */
	public BroadcastQueryListVo queryBroadcastList(
			BroadcastQueryListCriteria criteria);

	/**
	 * 回复数+1
	 * 
	 * @return
	 */
	public void addReplyNumber(Broadcasting broadcasting);

	/**
	 * 回复数-1
	 */
	public void minusReplyNumber(Broadcasting broadcasting);

	/**
	 * 查询广播.根据ID
	 * 
	 * @param broadcastingId
	 */
	public Broadcasting findbroadcastingById(String broadcastingId);
	
	/**
	 * 删除广播
	 * 
	 * @param broadcastingId
	 */
	public void deletebroadcasting(Broadcasting broadcasting);
	
	/**
	 * 清空.广播发送对象数据
	 * @param broadcastingId
	 */
	public void deleteBroadcastReceiving(String broadcastingId);
	
	/**
	 * 获取 发送对象数据
	 * @param broadcastingId
	 */
	public List<BroadcastingReceiving> findBroadcastReceiving(String broadcastingId);
	
	/**
	 * 获取下一个number
	 * @return
	 */
	public String getMaxNumber(BroadcastMessage broadcastMessage);
	
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
