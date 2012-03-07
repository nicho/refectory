package com.chinarewards.elt.service.reward;

import java.util.Date;
import java.util.List;

import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.base.RewardItemStore;
import com.chinarewards.elt.domain.reward.frequency.WeekFrequencyDays;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.reward.base.RewardItemParam;
import com.chinarewards.elt.model.reward.search.RewardItemSearchVo;
import com.chinarewards.elt.model.reward.vo.RewardItemStoreVo;
import com.chinarewards.elt.model.reward.vo.RewardItemVo;
import com.chinarewards.elt.model.user.UserContext;

/**
 * Provides useful methods to deal with {@link RewardItem}.
 * 
 * @author yanxin
 * @since 1.0
 */
public interface RewardItemLogic {

	/**
	 * Add or update a record of RewardItem. See more detail at
	 * {@link RewardItemParam}.
	 * 
	 * @param caller
	 * @param param
	 * @return
	 */
	public RewardItem saveRewardItem(SysUser caller, RewardItemParam param);
	
	/**
	 * Add or update a record of RewardItem. See more detail at
	 * {@link RewardItemParam}.
	 * 奖项库
	 * @param caller
	 * @param param
	 * @return
	 */
	public RewardItemStore saveRewardItemStore(SysUser caller, RewardItemParam param);


	/**
	 * Delete the specified RewardItem. Just delete in a logical way.
	 * 
	 * @param rewardItemId
	 */
	public String deleteRewardItem(SysUser caller,String rewardItemId);

	/**
	 * Fetch the entire information about the specified RewardItem. It contains
	 * all the external informations.
	 * 
	 * @param rewardItemId
	 * @return
	 */
	public RewardItemVo fetchEntireRewardItemById(String rewardItemId);
   
	/**
	 * Delete the specified RewardItemStore. Just delete in a logical way.
	 * 
	 * @param rewardItemStoreId
	 */
	public String deleteRewardItemStore(SysUser caller,String rewardItemStoreId);

	/**
	 * Fetch the entire information about the specified RewardItemStore. It contains
	 * all the external informations.
	 * 
	 * @param rewardItemStoreId
	 * @return
	 */
	public RewardItemStoreVo fetchEntireRewardItemStoreById(String rewardItemStoreId);

	/**
	 * 
	 * Returns a list of RewardsItem matching criteria.
	 * <p>
	 * 
	 * This method respect the caller context and limit the visibility of the
	 * returning data. To return data without data limitation of the caller
	 * context, use {@link #fetchRewardsItemsNoAcl(RewardItemSearchVo)} instead.
	 * 
	 * @param context
	 * @param criteria
	 * @return
	 */
	public PageStore<RewardItemVo> fetchRewardItems(UserContext context,
			RewardItemSearchVo criteria);
	
	
	public PageStore<RewardItemStoreVo> fetchRewardItemsStore(UserContext context,
			RewardItemSearchVo criteria);

	/**
	 * Fetch list of RewardItem by RewardItemSearchVo. All roles would get the
	 * same results.
	 * 
	 * @param criteria
	 * @return
	 */
	public PageStore<RewardItemVo> fetchRewardItemsNoAcl(
			RewardItemSearchVo criteria);

	/**
	 * Fetch all auto-generate RewardItem by current time. Which means find out
	 * those qualified RewardItem list.
	 * 
	 * @param currTime
	 * @return
	 */
	public List<RewardItem> fetchAutoGenerateRewardItem(Date currTime);

	/**
	 * Make the specified RewardItem enabled.
	 * 
	 * @param caller
	 * @param rewardItemId
	 * @return
	 */
	public RewardItem enableRewardItem(SysUser caller, String rewardItemId);

	/**
	 * Make the specified RewardItem disabled.
	 * 
	 * @param caller
	 * @param rewardItemId
	 * @return
	 */
	public RewardItem disableRewardItem(SysUser caller, String rewardItemId);

	/**
	 * Run batch to generate rewards automatic.
	 * 
	 * @param flagTime
	 */
	public void runAutoRewardGeneratorBatch(Date flagTime);

	/**
	 * Run batch to generate rewards automatic.
	 * 
	 * @param flagTime
	 */
	public void runAutoRewardGeneratorByRewardItem(Date flagTime,String RewardItemid);
	
	/**
	 * Calculate the next run batch time of the specified rewarditem.
	 * 
	 * @param rewardItemId
	 * @return
	 */
	public Date calNextRunBatchTime(String rewardItemId);

	//查找星期的频率
	public List<WeekFrequencyDays> findWeekSelectorUnitDataByWSUId(	String weekSelectorUnitId);


	
	/**
	 * 奖项激活后,奖项次数+1
	 */
	public void updateRewardItemCount(String rewardItemId);
	
	public void saveOrgPolicy(Department dep);
	public RewardItemStore findRewardItemStore(String rewardItemStoreId);
	/**
	 * copy 奖项库--创建--奖项
	 * @param context
	 * @param rewardItemId
	 * @return
	 */
	public String copyRewardItenStoreToRewardItem(UserContext context, String rewardItemStoreId);

	/**
	 * 奖项库应用后,奖项模板次数+1
	 */
	public void updateRewardItemStoreCount(String rewardItemStoreId);




}
