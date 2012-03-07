package com.chinarewards.elt.service.reward;

import java.util.List;

import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.reward.person.NomineeLot;
import com.chinarewards.elt.domain.reward.person.PreWinner;
import com.chinarewards.elt.domain.reward.person.Winner;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.reward.exception.ApproveRewardException;
import com.chinarewards.elt.model.reward.exception.DenyRewardException;
import com.chinarewards.elt.model.reward.exception.NominateRewardException;
import com.chinarewards.elt.model.reward.search.RewardQueryVo;
import com.chinarewards.elt.model.reward.search.RewardSearchVo;
import com.chinarewards.elt.model.reward.vo.RewardVo;
import com.chinarewards.elt.model.user.UserContext;

/**
 * The interface is to provide Reward services. Some useful methods should be
 * listed here.
 * 
 * @author yanxin
 * @since 1.0
 */
public interface RewardService {

	/**
	 * Generate Reward from the specified RewardItem.
	 * 
	 * @param caller
	 * @param rewardItemId
	 * @return
	 */
	public Reward awardFromRewardItem(SysUser caller, String rewardItemId);

	/**
	 * The caller nominate someone to be nominees. The result is result a record
	 * of {@link NomineeLot}.
	 * 
	 * @param caller
	 * @param rewardId
	 * @param staffIds
	 * @return
	 * @throws NominateRewardException
	 */
	public NomineeLot nominateReward(SysUser caller, String rewardId,
			List<String> staffIds) throws NominateRewardException;

	/**
	 * 颁奖方法,返回颁奖记录ID
	 * 
	 * @param caller
	 * @param rewardId
	 * @param staffIds
	 * @return
	 */
	public String awardReward(String nowUserId, String rewardId,
			List<String> staffIds);

	/**
	 * Approve a Reward means it would award really. The result is generate
	 * {@link Winner} from {@link PreWinner}.
	 * 
	 * @param caller
	 * @param rewardId
	 * @return
	 * @throws ApproveRewardException
	 */
	public Reward approveReward(SysUser caller, String rewardId, String reason)
			throws ApproveRewardException;

	/**
	 * Deny a Reward means it should prepare {@link PreWinner} again.
	 * 
	 * @param caller
	 * @param rewardId
	 * @return
	 * @throws DenyRewardException
	 */
	public Reward denyReward(SysUser caller, String rewardId, String reason)
			throws DenyRewardException;

	/**
	 * Fetch the entire information about the specified Reward. It contains all
	 * the external informations.
	 * 
	 * @param rewardId
	 * @return
	 */
	public RewardQueryVo fetchEntireRewardQueryVoById(String rewardId);

	/**
	 * 获取已颁奖的奖励信息..包括获奖人信息
	 * 
	 * @param rewardId
	 * @return
	 */
	public RewardQueryVo fetchWinRewardQueryVoById(String rewardId);

	/**
	 * 查询奖励列表
	 * 
	 * @param context
	 * @param criteria
	 * @return
	 */
	public PageStore<RewardVo> fetchRewards(UserContext context,RewardSearchVo criteria);
	
	/**
	 * HR收件箱查询一个月内完成的奖励
	 * 
	 * @param context
	 * @param criteria
	 * @return
	 */
	public List<RewardVo> getRewardsByHrBox(UserContext context,RewardSearchVo criteria);
	

	/**
	 * 颁奖时...查询给定员工中有无离职人员
	 * 
	 * @param staffIds
	 * @return staffList(String)
	 */
	public List<String> getIsDeleteStaff(List<String> staffIds);

	/**
	 * 删除奖励...非物理删除
	 * 
	 * @param rewardId
	 * @return
	 */
	public String deleteReward(String rewardId,UserContext context);
	/**
	 * 得到当前人要提名的奖励数
	 * @param staffId
	 * @return
	 */
	public int getNominatorByStaffId(String staffId);
	/**
	 * 得到当前人要进行颁奖的奖励数
	 * @param staffId
	 * @return
	 */
	public int getRewardsByStaffId(String staffId);
}
