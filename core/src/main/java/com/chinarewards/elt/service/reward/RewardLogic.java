package com.chinarewards.elt.service.reward;

import java.util.Date;
import java.util.List;

import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.reward.person.NomineeLot;
import com.chinarewards.elt.domain.reward.person.PreWinner;
import com.chinarewards.elt.domain.reward.person.Winner;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.reward.base.RewardParam;
import com.chinarewards.elt.model.reward.exception.ApproveRewardException;
import com.chinarewards.elt.model.reward.exception.DenyRewardException;
import com.chinarewards.elt.model.reward.exception.NominateRewardException;
import com.chinarewards.elt.model.reward.search.RewardQueryVo;
import com.chinarewards.elt.model.reward.search.RewardSearchVo;
import com.chinarewards.elt.model.reward.vo.RewardVo;
import com.chinarewards.elt.model.user.UserContext;

/**
 * Provides some useful methods to deal with {@link Reward}.
 * 
 * @author yanxin
 * @since 1.0
 */
public interface RewardLogic {

	/**
	 * Save Reward information by hand. The {@link RewardParam} come from the
	 * manual choose.
	 * 
	 * @param context
	 * @param param
	 * @return
	 * @deprecated Maybe this method do not need any more. Because all of Reward
	 *             would be from RewardItem and Reward can not change after it
	 *             is generated.
	 */
	public Reward awardByHand(UserContext context, RewardParam param);

	/**
	 * Generate Reward from the specified RewardItem.
	 * 
	 * @param caller
	 * @param rewardItemId
	 * @param currTime
	 * @return
	 */
	public Reward awardFromRewardItem(SysUser caller, String rewardItemId,
			Date currTime);

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
	 * The owner of Reward do the award.
	 * 
	 * @param caller
	 * @param rewardId
	 * @param staffIds
	 */
	public String awardReward(SysUser caller, String rewardId,
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
	 * @param rewardItemId
	 * @return
	 */
	public RewardVo fetchEntireRewardById(String rewardId);

	/**
	 * Fetch the entire information about the specified Reward. It contains all
	 * the external informations.
	 * 
	 * @param rewardId
	 * @return
	 */
	public RewardQueryVo fetchEntireRewardQueryVoById(String rewardId);

	/**
	 * Obtain the list of {@link RewardVo} managed by the login user.
	 * 
	 * @param context
	 * @param criteria
	 * @return
	 */
	public PageStore<RewardVo> fetchRewards(UserContext context,
			RewardSearchVo criteria);

	
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
	/**
	 * HR收件箱查询一个月内完成的奖励
	 * 
	 * @param context
	 * @param criteria
	 * @return
	 */
	
	public List<RewardVo> getRewardsByHrBox(UserContext context,RewardSearchVo criteria);
}
