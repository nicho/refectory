package com.chinarewards.elt.service.reward.rule;

import java.util.Date;
import java.util.List;

import com.chinarewards.elt.dao.org.StaffDao;
import com.chinarewards.elt.dao.reward.JudgeDao;
import com.chinarewards.elt.dao.reward.RewardDao;
import com.chinarewards.elt.dao.reward.RewardItemDao;
import com.chinarewards.elt.dao.reward.RewardItemStoreDao;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.base.RewardItemStore;
import com.chinarewards.elt.domain.reward.person.Judge;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.reward.base.JudgeStatus;
import com.chinarewards.elt.util.DateUtil;
import com.google.inject.Inject;

/**
 * The implementation of {@link JudgeLogic}
 * 
 * @author yanxin
 * @since 1.0
 */
public class JudgeLogicImpl implements JudgeLogic {

	private final JudgeDao judgeDao;
	private final RewardItemDao rewardItemDao;
	private final RewardDao rewardDao;
	private final StaffDao staffDao;
	private final RewardItemStoreDao rewardItemStoreDao;
	@Inject
	public JudgeLogicImpl(JudgeDao judgeDao, RewardItemDao rewardItemDao,
			RewardDao rewardDao, StaffDao staffDao,RewardItemStoreDao rewardItemStoreDao) {
		this.judgeDao = judgeDao;
		this.rewardItemDao = rewardItemDao;
		this.rewardDao = rewardDao;
		this.staffDao = staffDao;
		this.rewardItemStoreDao = rewardItemStoreDao;
	}

	@Override
	public void bindJudgesToRewardItem(SysUser caller, String rewardItemId,
			List<String> staffIds) {
		RewardItem rewardItem = rewardItemDao.findById(RewardItem.class,
				rewardItemId);
		Date now = DateUtil.getTime();
		for (String id : staffIds) {
			Judge judge = new Judge();
			Staff staff = staffDao.findById(Staff.class, id);
			judge.setStaff(staff);
			judge.setRewardItem(rewardItem);
			judge.setCreatedAt(now);
			judge.setCreatedBy(caller);
			judge.setLastModifiedAt(now);
			judge.setLastModifiedBy(caller);
			judgeDao.save(judge);
		}
	}
	
	@Override
	public void bindJudgesToRewardItemStore(SysUser caller, String rewardItemStoreId,
			List<String> staffIds) {
		RewardItemStore rewardItemStore = rewardItemStoreDao.findById(RewardItemStore.class,rewardItemStoreId);
		Date now = DateUtil.getTime();
		for (String id : staffIds) {
			Judge judge = new Judge();
			Staff staff = staffDao.findById(Staff.class, id);
			judge.setStaff(staff);
			judge.setRewardItemStore(rewardItemStore);
			judge.setCreatedAt(now);
			judge.setCreatedBy(caller);
			judge.setLastModifiedAt(now);
			judge.setLastModifiedBy(caller);
			judgeDao.save(judge);
		}
	}

	@Override
	public void removeJudgesFromRewardItem(String rewardItemId) {
		List<Judge> judgeList = judgeDao.findJudgesFromRewardItem(rewardItemId);
		for (Judge judge : judgeList) {
			judgeDao.delete(judge);
		}
	}
   
	@Override
	public void removeJudgesFromRewardItemStore(String rewardItemIdStore) {
		List<Judge> judgeList = judgeDao.findJudgesFromRewardItemStore(rewardItemIdStore);
		for (Judge judge : judgeList) {
			judgeDao.delete(judge);
		}
	}
	@Override
	public List<Judge> findJudgesFromRewardItem(String rewardItemId) {
		return judgeDao.findJudgesFromRewardItem(rewardItemId);
	}
	
	@Override
	public List<Judge> findJudgesFromRewardItemStore(String rewardItemStoreId) {
		return judgeDao.findJudgesFromRewardItemStore(rewardItemStoreId);
	}

	@Override
	public List<Judge> findJudgesFromReward(String rewardId) {
		return judgeDao.findJudgesFromReward(rewardId);
	}

	@Override
	public void cloneJudgesFromRewardItemToReward(SysUser caller,
			String fromRewardItemId, String toRewardId) {
		Date now = DateUtil.getTime();
		Reward reward = rewardDao.findById(Reward.class, toRewardId);
		List<Judge> judges = judgeDao
				.findJudgesFromRewardItem(fromRewardItemId);
		for (Judge j : judges) {
			Judge newJudge = new Judge();
			newJudge.setReward(reward);
			newJudge.setStaff(j.getStaff());
			newJudge.setCreatedAt(now);
			newJudge.setCreatedBy(caller);
			newJudge.setLastModifiedAt(now);
			newJudge.setLastModifiedBy(caller);
			newJudge.setStatus(JudgeStatus.NONE);
			judgeDao.save(newJudge);
		}
	}

	@Override
	public void copyJudgeToRewardItem(SysUser caller,String rewardItemStoreId, String rewardItemId) {
		Date now = DateUtil.getTime();
		RewardItem rewardItem = rewardItemDao.findById(RewardItem.class, rewardItemId);
		List<Judge> judges = judgeDao.findJudgesFromRewardItemStore(rewardItemStoreId);
		for (Judge j : judges) {
			Judge newJudge = new Judge();
			newJudge.setRewardItem(rewardItem);
			newJudge.setStaff(j.getStaff());
			newJudge.setCreatedAt(now);
			newJudge.setCreatedBy(caller);
			newJudge.setLastModifiedAt(now);
			newJudge.setLastModifiedBy(caller);
			newJudge.setStatus(JudgeStatus.NONE);
			judgeDao.save(newJudge);
		}
		
	}
}
