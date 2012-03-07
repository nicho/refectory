package com.chinarewards.elt.service.reward;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.dao.org.DepartmentDao;
import com.chinarewards.elt.dao.reward.RewardDao;
import com.chinarewards.elt.dao.reward.RewardItemDao;
import com.chinarewards.elt.dao.user.UserDao;
import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.person.Candidate;
import com.chinarewards.elt.domain.reward.person.Judge;
import com.chinarewards.elt.domain.reward.person.NomineeLot;
import com.chinarewards.elt.domain.reward.person.PreWinnerLot;
import com.chinarewards.elt.domain.reward.person.Winner;
import com.chinarewards.elt.domain.reward.rule.CandidateRule;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.broadcast.BroadcastingVo;
import com.chinarewards.elt.model.broadcast.OrganType;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.information.BroadcastingCategory;
import com.chinarewards.elt.model.reward.base.RequireAutoAward;
import com.chinarewards.elt.model.reward.base.RequireAutoGenerate;
import com.chinarewards.elt.model.reward.base.RewardParam;
import com.chinarewards.elt.model.reward.base.RewardStatus;
import com.chinarewards.elt.model.reward.exception.ApproveRewardException;
import com.chinarewards.elt.model.reward.exception.DenyRewardException;
import com.chinarewards.elt.model.reward.exception.JudgeException;
import com.chinarewards.elt.model.reward.exception.NoEffectivePreWinnerException;
import com.chinarewards.elt.model.reward.exception.NominateRewardException;
import com.chinarewards.elt.model.reward.search.CandidateParam;
import com.chinarewards.elt.model.reward.search.JudgeParam;
import com.chinarewards.elt.model.reward.search.RewardQueryVo;
import com.chinarewards.elt.model.reward.search.RewardSearchVo;
import com.chinarewards.elt.model.reward.vo.RewardVo;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.broadcast.BroadcastService;
import com.chinarewards.elt.service.reward.acl.RewardAclProcessorFactory;
import com.chinarewards.elt.service.reward.frequency.FrequencyLogic;
import com.chinarewards.elt.service.reward.rule.AwardApprovalDeterminer;
import com.chinarewards.elt.service.reward.rule.CandidateLogic;
import com.chinarewards.elt.service.reward.rule.CandidateRuleLogic;
import com.chinarewards.elt.service.reward.rule.JudgeLogic;
import com.chinarewards.elt.service.reward.rule.NomineeLogic;
import com.chinarewards.elt.service.reward.rule.PreWinnerLogic;
import com.chinarewards.elt.service.reward.rule.WinnerLogic;
import com.chinarewards.elt.service.staff.StaffLogic;
import com.chinarewards.elt.util.DateUtil;
import com.chinarewards.elt.util.StringUtil;
import com.google.inject.Inject;

/**
 * The implementation of {@link RewardLogic}
 * 
 * @author yanxin
 * @since 1.0
 */
public class RewardLogicImpl implements RewardLogic {

	Logger logger = LoggerFactory.getLogger(getClass());

	private final RewardDao rewardDao;
	private final RewardItemDao rewardItemDao;
	private final DepartmentDao deptDao;
	private final UserDao sysUserDao;
	private final CandidateLogic candidateLogic;
	private final CandidateRuleLogic candidateRuleLogic;
	private final JudgeLogic judgeLogic;
	private final PreWinnerLogic preWinnerLogic;
	private final WinnerLogic winnerLogic;
	private final NomineeLogic nomineeLogic;
	private final AwardApprovalDeterminer awardApprovalDeterminer;
	private final FrequencyLogic frequencyLogic;
	private final RewardAclProcessorFactory rewardAclProcessorFactory;
	private final BroadcastService broadcastService;
	private final StaffLogic staffLogic;

	@Inject
	public RewardLogicImpl(RewardDao rewardDao, RewardItemDao rewardItemDao,
			DepartmentDao deptDao, UserDao sysUserDao,
			CandidateLogic candidateLogic,
			CandidateRuleLogic candidateRuleLogic, JudgeLogic judgeLogic,
			PreWinnerLogic preWinnerLogic, WinnerLogic winnerLogic,
			NomineeLogic nomineeLogic,
			AwardApprovalDeterminer awardApprovalDeterminer,
			FrequencyLogic frequencyLogic,
			RewardAclProcessorFactory rewardAclProcessorFactory,BroadcastService broadcastService,StaffLogic staffLogic) {
		this.rewardDao = rewardDao;
		this.rewardItemDao = rewardItemDao;
		this.deptDao = deptDao;
		this.sysUserDao = sysUserDao;
		this.candidateLogic = candidateLogic;
		this.candidateRuleLogic = candidateRuleLogic;
		this.judgeLogic = judgeLogic;
		this.preWinnerLogic = preWinnerLogic;
		this.winnerLogic = winnerLogic;
		this.nomineeLogic = nomineeLogic;
		this.awardApprovalDeterminer = awardApprovalDeterminer;
		this.frequencyLogic = frequencyLogic;
		this.rewardAclProcessorFactory = rewardAclProcessorFactory;
		this.broadcastService=broadcastService;
		this.staffLogic=staffLogic;

	}

	/**
	 * Assemble Reward Object.
	 * 
	 * @param caller
	 * @param param
	 * @return
	 */
	private Reward assembleRewardObj(SysUser caller, RewardParam param) {
		logger.debug("Invoking method assembleRewardObj()", param);
		Reward reward = null;
		Date now = DateUtil.getTime();
		if (StringUtil.isEmptyString(param.getId())) {
			// Creates
			reward = new Reward();
			reward.setCreatedAt(now);
			reward.setCreatedBy(caller);
		} else {
			// Update
			reward = rewardDao.findById(Reward.class, param.getId());
		}
		if (!StringUtil.isEmptyString(param.getRewardItemId())) {
			RewardItem rewardItem = rewardItemDao.findById(RewardItem.class,
					param.getRewardItemId());
			reward.setRewardItem(rewardItem);
		}
		if (!StringUtil.isEmptyString(param.getBuilderDeptId())) {
			Department builderDept = deptDao.findById(Department.class,
					param.getBuilderDeptId());
			reward.setBuilderDept(builderDept);
			reward.setCorporation(builderDept.getCorporation());
		}
		if (!StringUtil.isEmptyString(param.getAccountDeptId())) {
			Department accountDept = deptDao.findById(Department.class,
					param.getAccountDeptId());
			reward.setAccountDept(accountDept);
		}
		reward.setName(param.getName());
		reward.setDefinition(param.getDefinition());
		reward.setStandard(param.getStandard());
		reward.setHeadcountLimit(param.getHeadcountLimit());
		reward.setTotalAmtLimit(param.getTotalAmtLimit());
		reward.setAwardAmt(param.getAwardAmt());
		reward.setRewardUnit(param.getRewardUnit());
		reward.setExpectAwardDate(param.getExpectAwardDate());
		reward.setExpectNominateDate(param.getExpectNominateDate());
		reward.setLastModifiedAt(now);
		reward.setLastModifiedBy(caller);

		return reward;
	}

	@Override
	public Reward awardByHand(UserContext context, RewardParam param) {
		logger.debug("Invoking method saveRewardByHand(), parameter:{}", param);
		SysUser caller = sysUserDao
				.findById(SysUser.class, context.getUserId());
		Reward reward = assembleRewardObj(caller, param);
		if (StringUtil.isEmptyString(reward.getId())) {
			rewardDao.save(reward);
		} else {
			rewardDao.update(reward);
			// XXX here need not do more things.
		}

		return reward;
	}

	@Override
	public Reward awardFromRewardItem(SysUser caller, String rewardItemId,
			Date currTime) {
		logger.debug(
				"Invoking method generateRewardFromRewardItem(), parameter:{}",
				rewardItemId);
		Date now = DateUtil.getTime();
		RewardItem item = rewardItemDao
				.findById(RewardItem.class, rewardItemId);
		Reward reward = new Reward();
		if (item.getFrequency() != null) {
			String name = frequencyLogic.calRewardNameFromFrequency(
					item.getName(), item.getFrequency(), currTime);
			reward.setName(name);
		} else {
			reward.setName(item.getName());
		}
		reward.setRewardItem(item);
		reward.setCorporation(item.getCorporation());
		reward.setStatus(RewardStatus.NEW);
		reward.setDefinition(item.getDefinition());
		reward.setStandard(item.getStandard());
		reward.setHeadcountLimit(item.getHeadcountLimit());
		reward.setTotalAmtLimit(item.getTotalAmtLimit());
		reward.setAwardAmt(item.getAwardAmt());
		reward.setBuilderDept(item.getBuilderDept());
		reward.setAccountDept(item.getAccountDept());
		reward.setRewardUnit(item.getAwardUnit());
		reward.setExpectAwardDate(item.getExpectAwardDate());
		reward.setExpectNominateDate(item.getExpectAwardDate());
		reward.setCreatedAt(now);
		reward.setCreatedBy(caller);
		reward.setLastModifiedAt(now);
		reward.setLastModifiedBy(caller);
		rewardDao.save(reward);

		// Add judges
		judgeLogic.cloneJudgesFromRewardItemToReward(caller, rewardItemId,
				reward.getId());

		// Clone candidate rule from RewardItem to Reward
		CandidateRule candidateRule = candidateRuleLogic
				.cloneCandidateRuleFromRewardItemToReward(caller, rewardItemId,
						reward.getId(), now);

		// Add candidate list
		candidateLogic.AddCandidateToReward(caller, reward.getId(),
				candidateRule);

		// Whether require autoAward or nominate mechanism
		if (RequireAutoAward.requireNominate == item.getAutoAward()) {
			reward.setStatus(RewardStatus.PENDING_NOMINATE);
		} else if (RequireAutoAward.requireAutoAward == item.getAutoAward()) {
			// Add pre-winner record
			String lotId = preWinnerLogic.addPreWinnerFromCandidateOfReward(
					caller, reward.getId());
			reward.setStatus(RewardStatus.PENDING_APPLICATION);
			if (!awardApprovalDeterminer.isApprovalRequired(reward.getId())) {
				// if not require approve, award directly.
				// Add winner record
				winnerLogic.approveWinnerFromEffectivePreWinnerLotOfReward(
						caller, lotId, "不需要审核");
				reward.setStatus(RewardStatus.REWARDED);
				winnerLogic.processWinnerAward(reward.getId());
			} else {
				// Entering pending approval status.
				reward.setStatus(RewardStatus.PENDING_APPLICATION);
			}
		}
		rewardDao.update(reward);

		return reward;
	}

	@Override
	public NomineeLot nominateReward(SysUser caller, String rewardId,
			List<String> staffIds) throws NominateRewardException {
		Reward reward = rewardDao.findById(Reward.class, rewardId);
		if (RewardStatus.PENDING_NOMINATE != reward.getStatus()) {
			throw new NominateRewardException(
					"Status is not support this operation!");
		}
		NomineeLot lot;
		try {
			lot = nomineeLogic
					.addNomineeLotToReward(caller, rewardId, staffIds);
		} catch (JudgeException e) {
			throw new NominateRewardException(
					"Can not find correct unnominated judge.");
		}
		return lot;
	}

	@Override
	public String awardReward(SysUser caller, String rewardId,
			List<String> staffIds) {
		Reward reward = rewardDao.findById(Reward.class, rewardId);
		String lotId = preWinnerLogic.addPreWinnerFromOuter(caller, rewardId,
				staffIds);
		// whether need approve
		if (!awardApprovalDeterminer.isApprovalRequired(reward.getId())) {
			// if not require, Add winner record
			winnerLogic.approveWinnerFromEffectivePreWinnerLotOfReward(caller,
					lotId, "不需要审核");
			reward.setStatus(RewardStatus.REWARDED);
			// process transaction
			winnerLogic.processWinnerAward(reward.getId());
			rewardDao.update(reward);
		}
		//获奖加入发送广播
		
		List<String[]> organList=new ArrayList<String[]>();
		
		String staffNames="";
		List <Staff> staffList=staffLogic.findStaffsByStaffIds(staffIds);
		if(staffList.size()>0){
			for (Staff f:staffList) {
				staffNames+=f.getName()+",";
				
				String[] nameAndId = new String[3];
				nameAndId[0] = f.getId();
				nameAndId[1] = f.getName();
				nameAndId[2] = OrganType.STAFF.toString();
				organList.add(nameAndId);
			}
		}

		UserContext context=new UserContext();
		context.setCorporationId(reward.getCorporation().getId());
		context.setUserId(caller.getId());
		
		BroadcastingVo vo=new BroadcastingVo();
		vo.setBroadcastingTimeStart(DateUtil.getTime());
		vo.setBroadcastingTimeEnd(DateUtil.getTime());
		vo.setAllowreplies(true);
		vo.setContent("恭喜 "+staffNames.substring(0,staffNames.length()-1)+" 获得"+reward.getName()+"，获得"+((int)reward.getAwardAmt())+"积分。大家赶快去打劫他吧");
		
		//接收对象为当前人机构(接收人加入获奖人)
		
		String[] nameAndId = new String[3];
		nameAndId[0] = reward.getCorporation().getId();
		nameAndId[1] = reward.getCorporation().getName();
		nameAndId[2] = OrganType.ORG.toString();
		organList.add(nameAndId);
		
		vo.setOrganList(organList);
		broadcastService.createOrUpdateBroadcast(vo, context, BroadcastingCategory.REWARDBROADCAST);
		
		return lotId;
	}

	@Override
	public Reward approveReward(SysUser caller, String rewardId, String reason)
			throws ApproveRewardException {
		Reward reward = rewardDao.findById(Reward.class, rewardId);

		if (RewardStatus.PENDING_APPLICATION != reward.getStatus()) {
			throw new ApproveRewardException(
					"Status can not support this operation!");
		}

		try {
			PreWinnerLot lot = preWinnerLogic
					.getUntreatedPreWinnerLotOfReward(rewardId);
			winnerLogic.approveWinnerFromEffectivePreWinnerLotOfReward(caller,
					lot.getId(), reason);
		} catch (NoEffectivePreWinnerException e) {
			throw new ApproveRewardException(e);
		}

		reward.setStatus(RewardStatus.REWARDED);
		rewardDao.update(reward);

		return reward;
	}

	@Override
	public Reward denyReward(SysUser caller, String rewardId, String reason)
			throws DenyRewardException {
		Reward reward = rewardDao.findById(Reward.class, rewardId);
		if (RewardStatus.PENDING_APPLICATION != reward.getStatus()) {
			throw new DenyRewardException(
					"Status can not support this operation!");
		}

		try {
			PreWinnerLot lot = preWinnerLogic
					.getUntreatedPreWinnerLotOfReward(rewardId);
			preWinnerLogic.denyPreWinnerLot(caller, lot.getId(), reason);
		} catch (NoEffectivePreWinnerException e) {
			throw new DenyRewardException(e);
		}

		reward.setStatus(RewardStatus.DENIED);
		rewardDao.update(reward);

		return reward;
	}

	@Override
	public RewardQueryVo fetchEntireRewardQueryVoById(String rewardId) {
		// 获取奖励
		Reward reward = rewardDao.findById(Reward.class, rewardId);
		// 获取被提名人
		List<Candidate> candidateList = candidateLogic
				.getCandidatesFromReward(rewardId);
		// 获取提名人
		List<Judge> judgeList = judgeLogic.findJudgesFromReward(rewardId);

		RewardQueryVo rewardQueryVo = new RewardQueryVo();

		rewardQueryVo.setRewardId(reward.getId());
		rewardQueryVo.setRewardName(reward.getName());
		rewardQueryVo.setRewardItemName(reward.getRewardItem().getName());
		rewardQueryVo.setDefinition(reward.getDefinition());
		rewardQueryVo.setStandard(reward.getStandard());
		rewardQueryVo.setHeadcountLimit(reward.getHeadcountLimit());
		rewardQueryVo.setTotalAmtLimit(reward.getTotalAmtLimit());
		rewardQueryVo.setAwardAmt(reward.getAwardAmt());
		rewardQueryVo.setCreatedAt(reward.getCreatedAt());
		rewardQueryVo.setExpectAwardDate(reward.getExpectAwardDate());
		rewardQueryVo.setExpectNominateDate(reward.getExpectNominateDate());
		rewardQueryVo.setCreatedStaffName(reward.getCreatedBy().getStaff()
				.getName());

		String awardMode = "";
		RequireAutoGenerate rag = reward.getRewardItem().getAutoGenerate();
		if (rag == RequireAutoGenerate.requireCyclic) {
			awardMode = "周期性奖项";
		} else if (rag == RequireAutoGenerate.requireOneOff) {
			awardMode = "一次性奖项";
		} else {
			awardMode = "nothing";
		}
		rewardQueryVo.setAwardMode(awardMode);
		rewardQueryVo.setAwardingStaffName(reward.getCreatedBy().getStaff()
				.getName());// wanting.......same CreateStaff

		List<CandidateParam> candidateListParam = new ArrayList<CandidateParam>();
		for (int i = 0; i < candidateList.size(); i++) {
			Candidate candidate = candidateList.get(i);
			CandidateParam candparam = new CandidateParam();
			candparam.setId(candidate.getId());
			candparam.setName(candidate.getStaff().getName());
			candparam.setNominateCount(candidate.getNominatecount());
			candparam.setStaffid(candidate.getStaff().getId());
			candidateListParam.add(candparam);
		}
		// 设置被提名人
		rewardQueryVo.setCandidateList(candidateListParam);

		List<JudgeParam> JudgeListParam = new ArrayList<JudgeParam>();
		for (int i = 0; i < judgeList.size(); i++) {
			Judge judge = judgeList.get(i);
			JudgeParam judgeParam = new JudgeParam();
			judgeParam.setId(judge.getId());
			judgeParam.setName(judge.getStaff().getName());
			judgeParam.setIsNominate(judge.getStatus().toString());
			judgeParam.setStaffid(judge.getStaff().getId());
			judgeParam.setJudgeStatus(judge.getStatus() + "");
			JudgeListParam.add(judgeParam);
		}

		// 设置提名人
		rewardQueryVo.setJudgeList(JudgeListParam);

		return rewardQueryVo;
	}

	@Override
	public RewardVo fetchEntireRewardById(String rewardId) {
		Reward reward = rewardDao.findById(Reward.class, rewardId);
		return convertFromRewardToVo(reward, true);
	}

	/**
	 * Convert from {@link Reward} to {@link RewardVo}. Here have two choices:
	 * <ul>
	 * <li>1. Just copy from Reward, do not need to query more detail
	 * informations.</li>
	 * <li>2. Need to query more detail informations. Maybe it is slow, please
	 * be careful.</li>
	 * </ul>
	 * 
	 * @param reward
	 * @param isEntire
	 *            true - get more details according to query database. It is
	 *            slow. <br>
	 *            false - just copy from Reward.
	 * @return
	 */
	private RewardVo convertFromRewardToVo(Reward reward, boolean isEntire) {
		RewardVo rewardVo = new RewardVo();
		if (isEntire) {
			String rewardId = reward.getId();
			// candidate rule
			CandidateRule candidateRule = candidateRuleLogic
					.findCandidateRuleFromReward(rewardId);
			// candidate list
			List<Candidate> candidates = candidateLogic
					.getCandidatesFromReward(rewardId);
			// Judge list
			List<Judge> judges = judgeLogic.findJudgesFromReward(rewardId);
			// nominee lot
			List<NomineeLot> nomineeLots = nomineeLogic
					.getNomineeLotsFromReward(rewardId);
			// pre-winner
			List<PreWinnerLot> preWinnerLots = preWinnerLogic
					.getPreWinnerLotsFromReward(rewardId);
			// winner
			List<Winner> winners = winnerLogic.getWinnersOfReward(rewardId);

			rewardVo.setReward(reward);
			rewardVo.setCandidateRule(candidateRule);
			rewardVo.setCandidates(candidates);
			rewardVo.setJudges(judges);
			rewardVo.setNomineeLots(nomineeLots);
			rewardVo.setPreWinnerLots(preWinnerLots);
			rewardVo.setWinners(winners);
			rewardVo.setAwardDate(reward.getAwardDate());
		}
		rewardVo.setReward(reward);

		return rewardVo;
	}
	


	@Override
	public PageStore<RewardVo> fetchRewards(UserContext context,
			RewardSearchVo criteria) {
		PageStore<Reward> pageStore = rewardAclProcessorFactory
				.generateRewardAclProcessor(context.getUserRoles())
				.fetchRewards(context, criteria);

		List<Reward> list = pageStore.getResultList();
		// post-process and convert
		List<RewardVo> rewardVoList = new ArrayList<RewardVo>();
		if (list!=null && list.size() > 0) {
			for (Reward reward : list) {
				rewardVoList.add(convertFromRewardToVo(reward, true));
			}

			logger.debug("The result size:{}, total:{}", new Object[] {
					rewardVoList.size(), pageStore.getResultCount() });

			PageStore<RewardVo> storeVo = new PageStore<RewardVo>();
			storeVo.setResultCount(pageStore.getResultCount());
			storeVo.setResultList(rewardVoList);

			return storeVo;
		} else {
			PageStore<RewardVo> storeVo = new PageStore<RewardVo>();
			storeVo.setResultCount(0);
			storeVo.setResultList(rewardVoList);
			return storeVo;
		}
	}


	@Override
	public String deleteReward(String rewardId, UserContext context) {
		Reward rewardbo = rewardDao.findById(Reward.class, rewardId);
		SysUser caller = sysUserDao
				.findById(SysUser.class, context.getUserId());
		rewardbo.setDeleted(true);
		rewardbo.setLastModifiedAt(DateUtil.getTime());
		rewardbo.setLastModifiedBy(caller);
		rewardDao.update(rewardbo);
		return rewardbo.getId();
	}

	@Override
	public int getNominatorByStaffId(String staffId) {
		// TODO Auto-generated method stub
		return rewardDao.getNominatorByStaffId(staffId);
	}

	@Override
	public int getRewardsByStaffId(String staffId) {
		// TODO Auto-generated method stub
		return rewardDao.getRewardsByStaffId(staffId);
	}
	@Override
	public List<RewardVo> getRewardsByHrBox(UserContext context,RewardSearchVo criteria){
		List<Reward> list =rewardDao.hrBoxRewards(context.getCorporationId(),criteria);
		List<RewardVo> rewardVoList = new ArrayList<RewardVo>();
		if (list.size() > 0) {
			for (Reward reward : list) {
				rewardVoList.add(convertFromRewardToVo(reward, false));
			}
		}
		return rewardVoList;
	}
}
