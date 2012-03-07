package com.chinarewards.elt.service.reward;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.dao.org.OrgPolicyDao;
import com.chinarewards.elt.dao.reward.RewardItemDao;
import com.chinarewards.elt.dao.reward.RewardItemStoreDao;
import com.chinarewards.elt.dao.reward.RewardItemTypeDao;
import com.chinarewards.elt.dao.reward.WeekFrequencyDaysDao;
import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.org.OrgPolicy;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.base.RewardItemStore;
import com.chinarewards.elt.domain.reward.base.RewardItemType;
import com.chinarewards.elt.domain.reward.frequency.Frequency;
import com.chinarewards.elt.domain.reward.frequency.WeekFrequencyDays;
import com.chinarewards.elt.domain.reward.person.Judge;
import com.chinarewards.elt.domain.reward.rule.CandidateRule;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.org.DepartmentPolicyConstants;
import com.chinarewards.elt.model.org.RewardsApprovalPolicyEnum;
import com.chinarewards.elt.model.reward.base.RequireAutoAward;
import com.chinarewards.elt.model.reward.base.RequireAutoGenerate;
import com.chinarewards.elt.model.reward.base.RewardItemParam;
import com.chinarewards.elt.model.reward.search.RewardItemSearchVo;
import com.chinarewards.elt.model.reward.vo.RewardItemStoreVo;
import com.chinarewards.elt.model.reward.vo.RewardItemVo;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.org.DepartmentLogic;
import com.chinarewards.elt.service.reward.acl.RewardAclProcessorFactory;
import com.chinarewards.elt.service.reward.frequency.FrequencyLogic;
import com.chinarewards.elt.service.reward.rule.CandidateRuleLogic;
import com.chinarewards.elt.service.reward.rule.JudgeLogic;
import com.chinarewards.elt.service.user.UserLogic;
import com.chinarewards.elt.util.DateUtil;
import com.chinarewards.elt.util.StringUtil;
import com.google.inject.Inject;

/**
 * The implementation of {@link RewardItemLogic}
 * 
 * @author yanxin
 * @since 1.0
 */
public class RewardItemLogicImpl implements RewardItemLogic {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private final RewardItemTypeDao rewardItemTypeDao;
	private final RewardItemDao rewardItemDao;
	private final FrequencyLogic frequencyLogic;
	private final CandidateRuleLogic candidateRuleLogic;
	private final JudgeLogic judgeLogic;
	private final RewardAclProcessorFactory rewardAclProcessorFactory;
	private final DepartmentLogic deptLogic;
	private final RewardLogic rewardLogic;
	private final UserLogic userLogic;
	private final WeekFrequencyDaysDao weekFrequencyDaysDao;
	private final RewardItemStoreDao rewardItemStoreDao;
	private final OrgPolicyDao orgPolicyDao;

	@Inject
	protected RewardItemLogicImpl(RewardItemTypeDao rewardItemTypeDao,
			RewardItemDao rewardItemDao, FrequencyLogic frequencyLogic,
			CandidateRuleLogic candidateRuleLogic, JudgeLogic judgeLogic,
			RewardAclProcessorFactory rewardAclProcessorFactory,
			RewardItemStoreDao rewardItemStoreDao, DepartmentLogic deptLogic,
			RewardLogic rewardLogic, UserLogic userLogic,
			WeekFrequencyDaysDao weekFrequencyDaysDao, OrgPolicyDao orgPolicyDao) {
		this.rewardItemTypeDao = rewardItemTypeDao;
		this.rewardItemDao = rewardItemDao;
		this.frequencyLogic = frequencyLogic;
		this.candidateRuleLogic = candidateRuleLogic;
		this.judgeLogic = judgeLogic;
		this.rewardAclProcessorFactory = rewardAclProcessorFactory;
		this.deptLogic = deptLogic;
		this.rewardLogic = rewardLogic;
		this.orgPolicyDao = orgPolicyDao;
		this.userLogic = userLogic;
		this.weekFrequencyDaysDao = weekFrequencyDaysDao;
		this.rewardItemStoreDao = rewardItemStoreDao;
	}

	private RewardItem assembleRewardItemObject(SysUser caller,
			RewardItemParam param) {
		logger.debug("Invoking method assembleRewardItemObject()");
		Date currTime = DateUtil.getTime();
		RewardItem item = null;
		if (StringUtil.isEmptyString(param.getId())) {
			// Create a new record
			item = new RewardItem();
			item.setEnabled(false);
			item.setDeleted(false);
			item.setCreatedBy(caller);
			item.setCreatedAt(currTime);
		} else {
			// Update a existed record
			item = rewardItemDao.findById(RewardItem.class, param.getId());
		}

		// RewardItemType
		if (!StringUtil.isEmptyString(param.getTypeId())) {
			RewardItemType type = rewardItemTypeDao.findById(
					RewardItemType.class, param.getTypeId());
			item.setType(type);
		}

		// Found build department and account department
		Department builderDept = deptLogic.findDepartmentById(param.getBuilderDeptId());
		Department accountDept = deptLogic.findDepartmentById(param.getAccountDeptId());
		item.setBuilderDept(builderDept);
		item.setAccountDept(accountDept);
		item.setCorporation(builderDept.getCorporation());
		// item.setFrequency(param.getFrequency());
		// Calculate publish date and ahead publish days
		int publishAheadDays = DateUtil.betweenDays(param.getPublishDate(),
				param.getExpectAwardDate());
		item.setPublishDate(param.getPublishDate());
		item.setPublishAheadDays(publishAheadDays);

		// Save expect award date
		item.setExpectAwardDate(param.getExpectAwardDate());

		// Calculate nominate date
		item.setNominateAheadDays(param.getNominateAheadDays());
		Date nominateDate = DateUtil.getDateOfParameterOnDay(
				param.getExpectAwardDate(), -param.getNominateAheadDays());
		item.setNominateDate(nominateDate);

		// calculate RunBatchTime
		if (param.getAutoGenerate() == RequireAutoGenerate.requireCyclic
				|| param.getAutoGenerate() == RequireAutoGenerate.requireOneOff) {
			if (param.getAutoAward() == RequireAutoAward.requireAutoAward) {
				item.setNexRunBatchTime(param.getExpectAwardDate());
			} else {
				item.setNexRunBatchTime(param.getPublishDate());
			}
		}
		item.setStartTime(param.getStartTime());
		item.setName(param.getName());
		item.setStandard(param.getStandard());
		item.setDefinition(param.getDefinition());
		item.setAwardAmt(param.getAwardAmt());
		item.setAwardUnit(param.getAwardUnit());
		item.setHeadcountLimit(param.getHeadcountLimit());
		item.setTotalAmtLimit(param.getTotalAmtLimit());
		item.setAutoGenerate(param.getAutoGenerate());
		item.setAutoAward(param.getAutoAward());
		item.setLastModifiedAt(currTime);
		item.setLastModifiedBy(caller);

		return item;
	}

	@Override
	public RewardItem saveRewardItem(SysUser caller, RewardItemParam param) {
		logger.debug("Invoking method saveRewardItem(), parameter:{}", param);
		RewardItem itemObj = assembleRewardItemObject(caller, param);
		System.out.println("itemObj=" + itemObj.getFrequency());
		if (StringUtil.isEmptyString(itemObj.getId())) {
			// Create
			rewardItemDao.save(itemObj);
		} else {
			// Update

			// clear frequency
			if (itemObj.getFrequency() != null)
				frequencyLogic.removeFrequencyFromRewardItem(itemObj.getId());
			// clear short-list rule
			candidateRuleLogic.removeCandidateRuleFromRewardItem(itemObj
					.getId());
			// clear judge list
			judgeLogic.removeJudgesFromRewardItem(itemObj.getId());
			if (param.getFrequency() == null)
				itemObj.setFrequency(null);
			rewardItemDao.update(itemObj);
		}

		// Add frequency
		if (param.getFrequency() != null)
			frequencyLogic.bindFrequencyToRewardItem(caller, itemObj.getId(),
					param.getFrequency());
		// Add short-list rule
		if (param.getCandidateList() != null
				&& !param.getCandidateList().isEmpty()) {
			candidateRuleLogic.bindDirectCandidateRuleToRewardItem(caller,
					itemObj.getId(), param.getCandidateList());
		}
		if (param.isDob()) {
			candidateRuleLogic.bindDobRuleToRewardItem(caller, itemObj.getId());
		}

		// Add judge list
		if (param.getJudgeIds() != null && !param.getJudgeIds().isEmpty()) {
			judgeLogic.bindJudgesToRewardItem(caller, itemObj.getId(),
					param.getJudgeIds());
		}

		return itemObj;
	}

	@Override
	public RewardItemStore saveRewardItemStore(SysUser caller,
			RewardItemParam param) {
		logger.debug("Invoking method saveRewardItem(), parameter:{}", param);
		RewardItemStore itemObj = assembleRewardItemStoreObject(caller, param);
		System.out.println("itemObj=" + itemObj.getFrequency());
		if (StringUtil.isEmptyString(itemObj.getId())) {
			// Create
			rewardItemStoreDao.save(itemObj);
		} else {
			// Update

			// clear frequency
			if (itemObj.getFrequency() != null)
				frequencyLogic.removeFrequencyFromRewardItemStore(itemObj
						.getId());
			// clear short-list rule
			candidateRuleLogic.removeCandidateRuleFromRewardItemStore(itemObj
					.getId());
			// clear judge list
			judgeLogic.removeJudgesFromRewardItemStore(itemObj.getId());
			if (param.getFrequency() == null)
				itemObj.setFrequency(null);
			rewardItemStoreDao.update(itemObj);
		}

		// Add frequency
		if (param.getFrequency() != null)
			frequencyLogic.bindFrequencyToRewardItemStore(caller,
					itemObj.getId(), param.getFrequency());
		// Add short-list rule
		if (param.getCandidateList() != null
				&& !param.getCandidateList().isEmpty()) {
			candidateRuleLogic.bindDirectCandidateRuleToRewardItemStore(caller,
					itemObj.getId(), param.getCandidateList());
		}
		if (param.isDob()) {
			candidateRuleLogic.bindDobRuleToRewardItemStore(caller,
					itemObj.getId());
		}

		// Add judge list
		if (param.getJudgeIds() != null && !param.getJudgeIds().isEmpty()) {
			judgeLogic.bindJudgesToRewardItemStore(caller, itemObj.getId(),
					param.getJudgeIds());
		}

		return itemObj;
	}

	private RewardItemStore assembleRewardItemStoreObject(SysUser caller,
			RewardItemParam param) {
		logger.debug("Invoking method assembleRewardItemObject()");
		Date currTime = DateUtil.getTime();
		RewardItemStore item = null;
		if (StringUtil.isEmptyString(param.getId())) {
			// Create a new record
			item = new RewardItemStore();
			item.setEnabled(false);
			item.setDeleted(false);
			item.setCreatedBy(caller);
			item.setCreatedAt(currTime);
		} else {
			// Update a existed record
			item = rewardItemStoreDao.findById(RewardItemStore.class,
					param.getId());
		}

		// RewardItemType
		if (!StringUtil.isEmptyString(param.getTypeId())) {
			RewardItemType type = rewardItemTypeDao.findById(
					RewardItemType.class, param.getTypeId());
			item.setType(type);
		}

		// Found build department and account department
		Department builderDept = deptLogic.findDepartmentById(param
				.getBuilderDeptId());
		Department accountDept = deptLogic.findDepartmentById(param
				.getAccountDeptId());
		item.setBuilderDept(builderDept);
		item.setAccountDept(accountDept);
		item.setCorporation(builderDept.getCorporation());
		// item.setFrequency(param.getFrequency());
		// Calculate publish date and ahead publish days
		int publishAheadDays = DateUtil.betweenDays(param.getPublishDate(),
				param.getExpectAwardDate());
		item.setPublishDate(param.getPublishDate());
		item.setPublishAheadDays(publishAheadDays);

		// Save expect award date
		item.setExpectAwardDate(param.getExpectAwardDate());

		// Calculate nominate date
		item.setNominateAheadDays(param.getNominateAheadDays());
		Date nominateDate = DateUtil.getDateOfParameterOnDay(
				param.getExpectAwardDate(), -param.getNominateAheadDays());
		item.setNominateDate(nominateDate);

		// calculate RunBatchTime
		if (param.getAutoGenerate() == RequireAutoGenerate.requireCyclic
				|| param.getAutoGenerate() == RequireAutoGenerate.requireOneOff) {
			if (param.getAutoAward() == RequireAutoAward.requireAutoAward) {
				item.setNexRunBatchTime(param.getExpectAwardDate());
			} else {
				item.setNexRunBatchTime(param.getPublishDate());
			}
		}
		item.setStartTime(param.getStartTime());
		item.setName(param.getName());
		item.setStandard(param.getStandard());
		item.setDefinition(param.getDefinition());
		item.setAwardAmt(param.getAwardAmt());
		item.setAwardUnit(param.getAwardUnit());
		item.setHeadcountLimit(param.getHeadcountLimit());
		item.setTotalAmtLimit(param.getTotalAmtLimit());
		item.setAutoGenerate(param.getAutoGenerate());
		item.setAutoAward(param.getAutoAward());
		item.setLastModifiedAt(currTime);
		item.setLastModifiedBy(caller);

		return item;
	}

	@Override
	public String deleteRewardItem(SysUser caller, String rewardItemId) {
		logger.debug("Invoking method deleteRewardItem(), rewardItemId:{}",
				rewardItemId);
		Date now = DateUtil.getTime();
		RewardItem rewardItem = rewardItemDao.findById(RewardItem.class,
				rewardItemId);
		rewardItem.setLastModifiedAt(now);
		rewardItem.setLastModifiedBy(caller);
		rewardItem.setDeleted(true);
		rewardItemDao.update(rewardItem);
		return rewardItem.getName();
	}
	
	@Override
	public String deleteRewardItemStore(SysUser caller,String rewardItemStoreId) {
		logger.debug("Invoking method deleteRewardItem(), rewardItemId:{}",
				rewardItemStoreId);
		Date now = DateUtil.getTime();
		RewardItemStore rewardItemStore = rewardItemStoreDao.findById(RewardItemStore.class,rewardItemStoreId);
		rewardItemStore.setLastModifiedAt(now);
		rewardItemStore.setLastModifiedBy(caller);
		rewardItemStore.setDeleted(true);
		rewardItemStoreDao.update(rewardItemStore);
		return rewardItemStore.getName();
	}

	/**
	 * Convert from {@link RewardItem} to {@link RewardItemVo}. Here have two
	 * choices:
	 * <ul>
	 * <li>1. Just copy from RewardItem, do not need to query more detail
	 * informations.</li>
	 * <li>2. Need to query more detail informations. Maybe it is slow, please
	 * be careful.</li>
	 * </ul>
	 * 
	 * @param item
	 * @param isEntire
	 *            true - get more details according to query database. It is
	 *            slow. <br>
	 *            false - just copy from RewardItem.
	 * @return
	 */
	private RewardItemVo convertFromRewardItemToVo(RewardItem item,
			boolean isEntire) {
		RewardItemVo itemVo = new RewardItemVo();
		if (isEntire) {
			String rewardItemId = item.getId();
			// Get frequency info,判断是否周期
			if (item.getAutoGenerate() == RequireAutoGenerate.requireCyclic) {
				Frequency frequencie = frequencyLogic
						.getFrequencyOfRewardItem(rewardItemId);
				itemVo.setFrequency(frequencie);
			}
			// Get candidate list rule
			CandidateRule candidateRule = candidateRuleLogic
					.findCandidateRuleFromRewardItem(rewardItemId);
			// Get judge list
			List<Judge> judges = judgeLogic
					.findJudgesFromRewardItem(rewardItemId);

			itemVo.setCandidateRule(candidateRule);
			itemVo.setJudgeList(judges);
			
			itemVo.setAwardAmt(item.getAwardAmt());
		}
		itemVo.setItem(item);

		return itemVo;
	}
	

	
	
	@Override
	public RewardItemVo fetchEntireRewardItemById(String rewardItemId) {
		RewardItem rewardItem = rewardItemDao.findById(RewardItem.class,
				rewardItemId);
		RewardItemVo itemVo = convertFromRewardItemToVo(rewardItem, true);
		return itemVo;
	}
	@Override
	public RewardItemStoreVo fetchEntireRewardItemStoreById(String rewardItemStoreId) {
		RewardItemStore rewardItemStore = rewardItemStoreDao.findById(RewardItemStore.class,
				rewardItemStoreId);
		RewardItemStoreVo itemStoreVo = convertFromRewardItemStoreToVo(rewardItemStore, true);
		return itemStoreVo;
	}

	@Override
	public PageStore<RewardItemVo> fetchRewardItems(UserContext context,
			RewardItemSearchVo criteria) {
		logger.debug("Process in fetchRewardItems method, parameter RewardItemSearchVo.toString:"
				+ criteria);
		PageStore<RewardItem> pageStore = rewardAclProcessorFactory
				.generateRewardAclProcessor(context.getUserRoles())
				.fetchRewardItems(context, criteria);

		List<RewardItem> itemList = pageStore.getResultList();
		// post-process and convert
		List<RewardItemVo> itemVoList = new ArrayList<RewardItemVo>();
		for (RewardItem item : itemList) {
			itemVoList.add(convertFromRewardItemToVo(item, true));
		}
		logger.debug("The result size:{}, total:{}",
				new Object[] { itemVoList.size(), pageStore.getResultCount() });

		PageStore<RewardItemVo> storeVo = new PageStore<RewardItemVo>();
		storeVo.setResultCount(pageStore.getResultCount());
		storeVo.setResultList(itemVoList);

		return storeVo;
	}
	

	@Override
	// 奖项库的列表查询
	public PageStore<RewardItemStoreVo> fetchRewardItemsStore(
			UserContext context, RewardItemSearchVo criteria) {
		logger.debug("Process in fetchRewardItemsStore method, parameter RewardItemSearchVo.toString:"
				+ criteria);
		PageStore<RewardItemStore> pageStore = rewardAclProcessorFactory
				.generateRewardAclProcessor(context.getUserRoles())
				.fetchRewardItemsStore(context, criteria);

		List<RewardItemStore> itemList = pageStore.getResultList();
		// post-process and convert
		List<RewardItemStoreVo> itemVoList = new ArrayList<RewardItemStoreVo>();
		for (RewardItemStore item : itemList) {
			itemVoList.add(convertFromRewardItemStoreToVo(item, true));
		}
		logger.debug("The result size:{}, total:{}",
				new Object[] { itemVoList.size(), pageStore.getResultCount() });

		PageStore<RewardItemStoreVo> storeVo = new PageStore<RewardItemStoreVo>();
		storeVo.setResultCount(pageStore.getResultCount());
		storeVo.setResultList(itemVoList);

		return storeVo;
	}

	private RewardItemStoreVo convertFromRewardItemStoreToVo(
			RewardItemStore item, boolean isEntire) {
		RewardItemStoreVo itemVo = new RewardItemStoreVo();
		if (isEntire) {
			String rewardItemStoreId = item.getId();
			// Get frequency info,判断是否周期
			if (item.getAutoGenerate() == RequireAutoGenerate.requireCyclic) {
				Frequency frequencie = frequencyLogic
						.getFrequencyOfRewardItemStore(rewardItemStoreId);
				itemVo.setFrequency(frequencie);
			}
			// Get candidate list rule
			CandidateRule candidateRule = candidateRuleLogic
					.findCandidateRuleFromRewardItemStore(rewardItemStoreId);
			// Get judge list
			List<Judge> judges = judgeLogic
					.findJudgesFromRewardItemStore(rewardItemStoreId);

			itemVo.setCandidateRule(candidateRule);
			itemVo.setJudgeList(judges);
		}
		itemVo.setItemStore(item);

		return itemVo;
	}

	@Override
	public PageStore<RewardItemVo> fetchRewardItemsNoAcl(
			RewardItemSearchVo criteria) {
		// resolve the department IDs if told to do so.
		if (criteria.isSubDepartmentChosen()
				&& (criteria.getDeptIds() != null && !criteria.getDeptIds()
						.isEmpty())) {
			Set<String> deptIds = new HashSet<String>();
			for (String id : criteria.getDeptIds()) {
				deptIds.addAll(deptLogic.getWholeChildrenIds(id, true));
			}
			// since we have resolved all sub-departments
			criteria.setSubDepartmentChosen(false);
			criteria.setDeptIds(new ArrayList<String>(deptIds));
		}

		// search it
		List<RewardItem> itemList = rewardItemDao.fetchRewardsItems(criteria);
		int count = rewardItemDao.countRewardsItems(criteria);

		// / post-process and convert
		List<RewardItemVo> itemVoList = new ArrayList<RewardItemVo>();
		for (RewardItem item : itemList) {
			itemVoList.add(convertFromRewardItemToVo(item, true));
		}

		PageStore<RewardItemVo> storeVo = new PageStore<RewardItemVo>();
		storeVo.setResultCount(count);
		storeVo.setResultList(itemVoList);

		logger.debug("The result size:{}, total:{}",
				new Object[] { itemVoList.size(), count });
		return storeVo;
	}

	@Override
	public RewardItem enableRewardItem(SysUser caller, String rewardItemId) {
		logger.debug("Invoking method enableRewardItem(), rewardItemId:{}",
				rewardItemId);
		Date now = DateUtil.getTime();
		RewardItem rewardItem = rewardItemDao.findById(RewardItem.class,
				rewardItemId);
		rewardItem.setEnabled(true);
		rewardItem.setLastModifiedAt(now);
		rewardItem.setLastModifiedBy(caller);
		rewardItemDao.update(rewardItem);
		return rewardItem;
	}

	@Override
	public RewardItem disableRewardItem(SysUser caller, String rewardItemId) {
		logger.debug("Invoking method disableRewardItem(), rewardItemId:{}",
				rewardItemId);
		Date now = DateUtil.getTime();
		RewardItem rewardItem = rewardItemDao.findById(RewardItem.class,
				rewardItemId);
		rewardItem.setEnabled(false);
		rewardItem.setLastModifiedAt(now);
		rewardItem.setLastModifiedBy(caller);
		rewardItemDao.update(rewardItem);
		return rewardItem;
	}

	@Override
	public List<RewardItem> fetchAutoGenerateRewardItem(Date currTime) {
		logger.debug(
				" Invoking method fetchAutoGenerateRewardItem,para[CurrTime={}]",
				DateUtil.formatData(null, currTime));
		List<RewardItem> res = rewardItemDao
				.findAllNeedToOperatorAutoRewardsItem(currTime);
		logger.debug(" fetchAutoGenerateRewardItem method has return RewardsItem size:"
				+ res.size());
		return res;
	}

	@Override
	public void runAutoRewardGeneratorBatch(Date flagTime) {
		logger.debug(
				"Invoking method runAutoRewardGeneratorBatch(), param[flagTime={}]",
				flagTime);
		List<RewardItem> autoItems = fetchAutoGenerateRewardItem(flagTime);
		logger.debug("This time will run {} rewardItems total automatic!",
				autoItems.size());
	//	SysUser caller = userLogic.getDefaultUser();
		for (RewardItem item : autoItems) {
			// this time to run
			Date thisRunTime = item.getNexRunBatchTime();
			// failure times
			int errorTimes = 0;
			boolean isRunnable = true;
			while (flagTime.after(thisRunTime) && errorTimes < 3 && isRunnable) {
				try {
					Date nextRunTime = calNextRunBatchTime(item.getId());		
					rewardLogic.awardFromRewardItem(item.getCreatedBy(), item.getId(),
							flagTime);
					// update next run batch date
					item.setNexRunBatchTime(nextRunTime);
					// update publish date and expect award date
					// Notice: [1] .If a require auto award RewardItem, the run
					// batch time = expect award time. [2] . If not, the run
					// batch
					// time = next publish time.
					if (RequireAutoAward.requireAutoAward == item
							.getAutoAward()) {
						// Calculate next publish date
						Date nextPublishDate = DateUtil
								.getDateOfParameterOnDay(nextRunTime,
										-item.getPublishAheadDays());
						// Calculate next expect award date
						Date nextExpectAwardDate = nextRunTime;
						// Calculate next nominate date
						Date nextNominateDate = DateUtil
								.getDateOfParameterOnDay(nextRunTime,
										-item.getNominateAheadDays());
						item.setExpectAwardDate(nextExpectAwardDate);
						item.setPublishDate(nextPublishDate);
						item.setNominateDate(nextNominateDate);
					} else {
						// Calculate next publish date
						Date nextPublishDate = nextRunTime;
						// Calculate next expect award date
						Date nextExpectAwardDate = DateUtil
								.getDateOfParameterOnDay(nextPublishDate,
										item.getPublishAheadDays());
						// Calculate next nominate date
						Date nextNominateDate = DateUtil
								.getDateOfParameterOnDay(nextExpectAwardDate,
										-item.getNominateAheadDays());
						item.setExpectAwardDate(nextExpectAwardDate);
						item.setPublishDate(nextPublishDate);
						item.setNominateDate(nextNominateDate);
					}

					// Set it to false which means it can not work again.
					if (RequireAutoGenerate.requireOneOff == item
							.getAutoGenerate()) {
						item.setEnabled(false);
						isRunnable = false;
					}

					rewardItemDao.update(item);
					thisRunTime = nextRunTime;
					errorTimes = 0;
					logger.debug(
							"Reward item id:{},name={}, generate a reward successful, nextRunBatchTime: {}, lastRunBatchTime:{}",
							new Object[] { item.getId(), nextRunTime,
									thisRunTime });
				} catch (RuntimeException e) {
					errorTimes++;
					logger.warn(
							"Rewarditem id:{}, name:{}, generate a reward failure {} Times",
							new Object[] { item.getId(), item.getName(),
									errorTimes });
				}
			}
		}

	}

	@Override
	public Date calNextRunBatchTime(String rewardItemId) {
		logger.debug(
				"Invoking method calNextRunBatchTime(), param[rewardItemId={}]",
				rewardItemId);
		RewardItem item = rewardItemDao
				.findById(RewardItem.class, rewardItemId);
		return frequencyLogic.calNextAwardTime(item.getFrequency(),
				item.getNexRunBatchTime());
	}

	@Override
	public List<WeekFrequencyDays> findWeekSelectorUnitDataByWSUId(
			String weekSelectorUnitId) {
		logger.debug(" Process in findWeekSelectorUnitDataByWSUId method, parameter weekSelectorUnitId:"
				+ weekSelectorUnitId);
		return weekFrequencyDaysDao
				.findWeekFrequencyDaysByFrequencyId(weekSelectorUnitId);
	}

	@Override
	public void runAutoRewardGeneratorByRewardItem(Date flagTime,
			String RewardItemid) {

	//	SysUser caller = userLogic.getDefaultUser();
		RewardItem item = rewardItemDao
				.findById(RewardItem.class, RewardItemid);
		// this time to run
		Date thisRunTime = item.getNexRunBatchTime();
		// failure times
		int errorTimes = 0;
	//	boolean isRunnable = true;

			try {
				Date nextRunTime = calNextRunBatchTime(item.getId());
				rewardLogic.awardFromRewardItem(item.getCreatedBy(), item.getId(), flagTime);
				// update next run batch date
				item.setNexRunBatchTime(nextRunTime);
				// update publish date and expect award date
				// Notice: [1] .If a require auto award RewardItem, the run
				// batch time = expect award time. [2] . If not, the run
				// batch
				// time = next publish time.
				if (RequireAutoAward.requireAutoAward == item.getAutoAward()) {
					// Calculate next publish date
					Date nextPublishDate = DateUtil.getDateOfParameterOnDay(
							nextRunTime, -item.getPublishAheadDays());
					// Calculate next expect award date
					Date nextExpectAwardDate = nextRunTime;
					// Calculate next nominate date
					Date nextNominateDate = DateUtil.getDateOfParameterOnDay(
							nextRunTime, -item.getNominateAheadDays());
					item.setExpectAwardDate(nextExpectAwardDate);
					item.setPublishDate(nextPublishDate);
					item.setNominateDate(nextNominateDate);
				} else {
					// Calculate next publish date
					Date nextPublishDate = nextRunTime;
					// Calculate next expect award date
					Date nextExpectAwardDate = DateUtil
							.getDateOfParameterOnDay(nextPublishDate,
									item.getPublishAheadDays());
					// Calculate next nominate date
					Date nextNominateDate = DateUtil.getDateOfParameterOnDay(
							nextExpectAwardDate, -item.getNominateAheadDays());
					item.setExpectAwardDate(nextExpectAwardDate);
					item.setPublishDate(nextPublishDate);
					item.setNominateDate(nextNominateDate);
				}

				// Set it to false which means it can not work again.
				if (RequireAutoGenerate.requireOneOff == item.getAutoGenerate()) {
					item.setEnabled(false);
		//			isRunnable = false;
				}

				rewardItemDao.update(item);
				thisRunTime = nextRunTime;
				errorTimes = 0;
				logger.debug(
						"Reward item id:{},name={}, generate a reward successful, nextRunBatchTime: {}, lastRunBatchTime:{}",
						new Object[] { item.getId(), nextRunTime, thisRunTime });
			} catch (RuntimeException e) {
				errorTimes++;
				logger.warn(
						"Rewarditem id:{}, name:{}, generate a reward failure {} Times",
						new Object[] { item.getId(), item.getName(), errorTimes });
			}
		

	}

	@Override
	public void updateRewardItemCount(String rewardItemId) {
		RewardItem rewardItem = rewardItemDao.findById(RewardItem.class,
				rewardItemId);
		rewardItem.setDegree(rewardItem.getDegree() + 1);
		rewardItemDao.update(rewardItem);
	}

	public void saveOrgPolicy(Department dep) {

		if (orgPolicyDao.findByOrganizationIdPolicyKey(dep.getId(),
				DepartmentPolicyConstants.REWARDS_APPROVAL_POLICY) == null) {
			OrgPolicy policy = new OrgPolicy();
			policy.setKey2(DepartmentPolicyConstants.REWARDS_APPROVAL_POLICY);
			policy.setValue(RewardsApprovalPolicyEnum.NOT_REQUIRED.toString());
			policy.setOwner(dep);
			orgPolicyDao.save(policy);
		}
	}

	@Override
	public RewardItemStore findRewardItemStore(String rewardItemStoreId) {
		return rewardItemStoreDao.findById(RewardItemStore.class,
				rewardItemStoreId);
	}

	@Override
	public String copyRewardItenStoreToRewardItem(UserContext context,
			String rewardItemStoreId) {
		SysUser suser = userLogic.findUserById(context.getUserId());
		RewardItemStore rewardItemStore = findRewardItemStore(rewardItemStoreId);
		RewardItem rewardItem = new RewardItem();
		rewardItem.setName(rewardItemStore.getName());
		rewardItem.setType(rewardItemStore.getType());
		rewardItem.setDefinition(rewardItemStore.getDefinition());
		rewardItem.setStandard(rewardItemStore.getStandard());
		rewardItem.setHeadcountLimit(rewardItemStore.getHeadcountLimit());// 人数
		rewardItem.setTotalAmtLimit(rewardItemStore.getTotalAmtLimit());// 总积分
		rewardItem.setAwardAmt(rewardItemStore.getAwardAmt());// 个人要得的积分
		rewardItem.setBuilderDept(suser.getStaff().getDepartment());
		rewardItem.setAccountDept(suser.getStaff().getDepartment());
		rewardItem.setNominateAheadDays(rewardItemStore.getNominateAheadDays());// 提名提前的天数
		rewardItem.setAutoGenerate(rewardItemStore.getAutoGenerate());
		rewardItem.setAwardUnit(rewardItemStore.getAwardUnit());
		rewardItem.setCorporation(suser.getCorporation());
		rewardItem.setCreatedAt(DateUtil.getTime());
		rewardItem.setCreatedBy(suser);
		rewardItem.setAutoAward(rewardItemStore.getAutoAward());

		rewardItemDao.save(rewardItem);
		// 复制提名人
		judgeLogic.copyJudgeToRewardItem(suser, rewardItemStore.getId(),rewardItem.getId());
		
		// 复制频率
		if(rewardItem.getAutoGenerate()==RequireAutoGenerate.requireCyclic)
			frequencyLogic.copyFrequencyToRewardItem(suser, rewardItemStore.getId(), rewardItem.getId());
		// 复制被提名人
		candidateRuleLogic.copyCandidateRuleToRewardItem(suser,
				rewardItemStore.getId(), rewardItem.getId());

		rewardItemStore.setDegree(rewardItemStore.getDegree()+1);
		rewardItemStoreDao.update(rewardItemStore);
		this.saveOrgPolicy(rewardItem.getBuilderDept());
		return rewardItem.getName();
	}
	
	@Override
	public void updateRewardItemStoreCount(String rewardItemStoreId) {
		RewardItemStore rewardItemStore = rewardItemStoreDao.findById(RewardItemStore.class,	rewardItemStoreId);
		rewardItemStore.setDegree(rewardItemStore.getDegree() + 1);
		rewardItemStoreDao.update(rewardItemStore);
	}
}
