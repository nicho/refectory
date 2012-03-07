package com.chinarewards.elt.service.reward;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.base.RewardItemStore;
import com.chinarewards.elt.domain.reward.frequency.WeekFrequencyDays;
import com.chinarewards.elt.domain.reward.rule.DirectCandidateData;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.broadcast.BroadcastingVo;
import com.chinarewards.elt.model.broadcast.OrganType;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.information.BroadcastingCategory;
import com.chinarewards.elt.model.reward.base.RequireAutoGenerate;
import com.chinarewards.elt.model.reward.base.RewardItemParam;
import com.chinarewards.elt.model.reward.search.RewardItemSearchVo;
import com.chinarewards.elt.model.reward.vo.RewardItemStoreVo;
import com.chinarewards.elt.model.reward.vo.RewardItemVo;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.model.vo.StaffAndDeptmentAutoCompile;
import com.chinarewards.elt.service.broadcast.BroadcastService;
import com.chinarewards.elt.service.org.OrganizationLogic;
import com.chinarewards.elt.service.reward.rule.CandidateLogic;
import com.chinarewards.elt.service.reward.rule.CandidateRuleLogic;
import com.chinarewards.elt.service.user.UserLogic;
import com.chinarewards.elt.util.DateUtil;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

/**
 * The implementation of {@link RewardItemService}
 * 
 * @author yanxin
 * @since 1.0
 */
@Transactional
public class RewardItemServiceImpl implements RewardItemService {
	private final RewardItemLogic rewardItemLogic;
	private final RewardLogic rewardLogic;
	private final UserLogic userLogic;
	private final BroadcastService broadcastService;
	private final CandidateRuleLogic candidateRuleLogic;
	private final OrganizationLogic organizationLogic;

	@Inject
	public RewardItemServiceImpl(RewardItemLogic rewardItemLogic,
			CandidateRuleLogic candidateRuleLogic, UserLogic userLogic,
			CandidateLogic candidateLogic, OrganizationLogic organizationLogic,
			RewardLogic rewardLogic,BroadcastService broadcastService) {

		this.rewardItemLogic = rewardItemLogic;
		this.userLogic = userLogic;
		this.broadcastService=broadcastService;
		this.candidateRuleLogic = candidateRuleLogic;
		this.organizationLogic = organizationLogic;
		this.rewardLogic = rewardLogic;

	}

	@Override
	public RewardItem saveRewardItem(UserContext context, RewardItemParam param) {

		SysUser caller = userLogic.findUserById(context.getUserId());
		RewardItem rewardItem = rewardItemLogic.saveRewardItem(caller, param);
		if(rewardItem.getBuilderDept()!=null)
		rewardItemLogic.saveOrgPolicy(rewardItem.getBuilderDept());

		return rewardItem;
	}

	@Override
	public RewardItemStore saveRewardItemStore(UserContext context,
			RewardItemParam param) {
		SysUser caller = userLogic.findUserById(context.getUserId());
		RewardItemStore rewardItemStore = rewardItemLogic.saveRewardItemStore(caller, param);
		if(rewardItemStore.getBuilderDept()!=null)
		rewardItemLogic.saveOrgPolicy(rewardItemStore.getBuilderDept());
		return rewardItemStore;
	}

	@Override
	public List<StaffAndDeptmentAutoCompile> staffAndDeptmentAutoCompile(
			String corporationId, String falg, int limit) {
		return organizationLogic.staffAndDeptmentAutoCompile(corporationId,
				falg, limit);
	}

	@Override
	public String deleteRewardItem(UserContext context, String rewardItemId) {
		SysUser caller = userLogic.findUserById(context.getUserId());
		return rewardItemLogic.deleteRewardItem(caller, rewardItemId);

	}

	@Override
	public RewardItemVo fetchEntireRewardItemById(String rewardItemId) {
		// TODO Auto-generated method stub
		return rewardItemLogic.fetchEntireRewardItemById(rewardItemId);
	}
	
	@Override
	public String deleteRewardItemStore(UserContext context,String rewardItemStoreId) {
		 SysUser caller = userLogic.findUserById(context.getUserId());
		return rewardItemLogic.deleteRewardItemStore( caller,rewardItemStoreId);

	}

	@Override
	public RewardItemStoreVo fetchEntireRewardItemStoreById(String rewardItemStoreId) {
		// TODO Auto-generated method stub
		return rewardItemLogic.fetchEntireRewardItemStoreById(rewardItemStoreId);
	}

	@Override
	public PageStore<RewardItemVo> fetchRewardItems(UserContext context,
			RewardItemSearchVo criteria) {

		return rewardItemLogic.fetchRewardItems(context, criteria);
	}
	

	@Override
	public PageStore<RewardItemStoreVo> fetchRewardItemsStore(
			UserContext context, RewardItemSearchVo criteria) {

		return rewardItemLogic.fetchRewardItemsStore(context, criteria);
	}

	@Override
	public PageStore<RewardItemVo> fetchRewardItemsNoAcl(
			RewardItemSearchVo criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RewardItem> fetchAutoGenerateRewardItem(Date currTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enableRewardItem(UserContext context, String rewardItemId) {
		SysUser suser = userLogic.findUserById(context.getUserId());

		// if (em.getTransaction().isActive() != true) {
		// em.getTransaction().begin();
		// }
		RewardItem rewardItem = rewardItemLogic.enableRewardItem(suser,rewardItemId);
		
		//第一次激活发送广播
		if(rewardItem.getDegree()<1)
		{
			List<String[]> organList=new ArrayList<String[]>();
			BroadcastingVo vo=new BroadcastingVo();
			vo.setBroadcastingTimeStart(DateUtil.getTime());
			vo.setBroadcastingTimeEnd(DateUtil.getTime());
			vo.setAllowreplies(true);
			if(rewardItem.getAutoGenerate()==RequireAutoGenerate.requireCyclic)	
				vo.setContent(""+rewardItem.getCreatedBy().getStaff().getName()+" 创建了周期性奖项“"+rewardItem.getName()+"”，大家积极参与夺大奖啊!");
			else
				vo.setContent(""+rewardItem.getCreatedBy().getStaff().getName()+" 创建了奖项“"+rewardItem.getName()+"”，大家积极参与夺大奖啊!");
			
			//接收对象为当前人机构
			
			String[] nameAndId = new String[3];
			nameAndId[0] = rewardItem.getCorporation().getId();
			nameAndId[1] = rewardItem.getCorporation().getName();
			nameAndId[2] = OrganType.ORG.toString();
			organList.add(nameAndId);
			
			vo.setOrganList(organList);
			
			context.setCorporationId(rewardItem.getCorporation().getId());
			broadcastService.createOrUpdateBroadcast(vo, context, BroadcastingCategory.SYSBROADCAST);
		}
		
		Date startDate = rewardItem.getStartTime();
		if (DateUtil.compareData(startDate, DateUtil.getTime())) {

			if (rewardItem.getAutoGenerate() == RequireAutoGenerate.requireOneOff) {
				// 如果开始时间是当前日.调用生成奖励方法
				rewardLogic.awardFromRewardItem(suser, rewardItemId,
						DateUtil.getTime());
				// 如果是一次性,生成完成后设置 disable
				rewardItemLogic.disableRewardItem(suser, rewardItemId);
				// 修改次数
				rewardItemLogic.updateRewardItemCount(rewardItemId);

			} else if (rewardItem.getAutoGenerate() == RequireAutoGenerate.requireCyclic) {
				// 如果是周期性,单独运行这个奖项
				rewardItemLogic.runAutoRewardGeneratorByRewardItem(DateUtil.getTime(), rewardItemId);
				// 修改次数
				rewardItemLogic.updateRewardItemCount(rewardItemId);
			}

		}

		// em.getTransaction().commit();
		return rewardItem.getName();
	}

	@Override
	public String disableRewardItem(UserContext context, String rewardItemId) {
		SysUser suser = new SysUser();
		suser.setId(context.getUserId());
		// if (em.getTransaction().isActive() != true) {
		// em.getTransaction().begin();
		// }
		String name = rewardItemLogic.disableRewardItem(suser, rewardItemId)
				.getName();
		// em.getTransaction().commit();
		return name;
	}

	@Override
	public void runAutoRewardGeneratorBatch(Date flagTime) {
		rewardItemLogic.runAutoRewardGeneratorBatch(flagTime);

	}

	public List<WeekFrequencyDays> findWeekFrequencyDaysByFrequencyId(
			String weekSelectorId) {

		return rewardItemLogic.findWeekSelectorUnitDataByWSUId(weekSelectorId);
	}

	// 得到候选人数据
	public List<DirectCandidateData> findDirectCandidateDataListByDirectRuleId(
			String directRuleId) {
		return candidateRuleLogic
				.findDirectCandidateDataListByDirectRuleId(directRuleId);
		// findDirectCandidateDataListByDirectRuleId
	}

	@Override
	public String copyRewardItenStoreToRewardItem(UserContext context,
			String rewardItemStoreId) {
		return rewardItemLogic.copyRewardItenStoreToRewardItem(context,
				rewardItemStoreId);

	}

	@Override
	public void updateRewardItemStoreCount(UserContext context, String rewardItemStoreId) {
		 rewardItemLogic.updateRewardItemStoreCount(rewardItemStoreId);
		
	}

	

	

}
