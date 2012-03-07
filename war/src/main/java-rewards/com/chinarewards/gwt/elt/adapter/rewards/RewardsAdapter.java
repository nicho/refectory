package com.chinarewards.gwt.elt.adapter.rewards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinarewards.elt.domain.org.Department;
import com.chinarewards.elt.domain.reward.person.Judge;
import com.chinarewards.elt.domain.reward.person.Nominee;
import com.chinarewards.elt.domain.reward.person.NomineeLot;
import com.chinarewards.elt.domain.reward.person.Winner;
import com.chinarewards.elt.model.reward.vo.RewardGridVo;
import com.chinarewards.elt.model.reward.vo.RewardVo;
import com.chinarewards.gwt.elt.client.rewards.model.DepartmentClient;
import com.chinarewards.gwt.elt.client.rewards.model.JudgeModelClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsClient;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsCriteria.RewardsStatus;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsGridClient;
import com.chinarewards.gwt.elt.client.rewards.model.WinnerModelClient;

/**
 * This utility class use to adapter EJB entity to WAR domain.
 * 
 * @author nicho
 * @since 0.2.0
 */
public class RewardsAdapter {

	public static RewardsClient adapter(RewardVo rewards) {
		if (null == rewards) {
			return null;
		}

		RewardsClient result = new RewardsClient();

		result.setAccountDept(rewards.getAccountDept().getId());
		result.setBuilderDept(rewards.getBuilderDept().getId());
		result.setCreateAt(rewards.getCreatedAt());
		result.setDefinition(rewards.getDefinition());
		result.setId(rewards.getId());
		result.setName(rewards.getName());
		result.setRewardsItemId(rewards.getRewardItem().getId());
		result.setStandard(rewards.getStandard());
		result.setRewardsDate(rewards.getAwardDate());
		if (rewards.getCreatedBy() != null
				&& rewards.getCreatedBy().getStaff() != null){
			result.setCreatedBy(rewards.getCreatedBy().getStaff().getName());
		}
		result.setTotalAmtLimit(rewards.getTotalAmtLimit());
		result.setExpectNominateDate(rewards.getExpectNominateDate());
		
		result.setHeadcountLimit(rewards.getHeadcountLimit());
		if (rewards.getStatus() != null) {
			result.setStatus(RewardsStatus.valueOf(rewards.getStatus()
					.toString()));
		}

		if (rewards.getJudges().size() > 0) {
			List<JudgeModelClient> judgeList = new ArrayList<JudgeModelClient>();
			for (Judge judge : rewards.getJudges()) {

				judgeList.add(new JudgeModelClient(judge.getStaff().getId(),
						judge.getStaff().getName(), judge.getStaff()
								.getDepartment().getName(), judge.getStatus()
								.name()));
			}
			result.setJudgeList(judgeList);
		}
		if (rewards.getNomineeLots().size() > 0) {
			Map<String,String> map=new HashMap<String, String>();
			for (NomineeLot lot : rewards.getNomineeLots()) {
				String nameStr="";
				for (Nominee nominee : lot.getNominees()) {
					nameStr+=nominee.getStaff().getName()+";";
				}
				map.put(lot.getJudge().getStaff().getId(), nameStr);

			}
			result.setNomineeLot(map);
		}

		if (rewards.getWinners().size() > 0) {
			List<WinnerModelClient> winnerList=new ArrayList<WinnerModelClient>();
			for (Winner winner : rewards.getWinners()) {
				WinnerModelClient param=new WinnerModelClient();
				param.setStaffId(winner.getStaff().getId());
				param.setName(winner.getStaff().getName());
				winnerList.add(param);
			}
			result.setWinnerList(winnerList);		
		}
		

		return result;
	}

	public static List<RewardsClient> adapter(List<RewardVo> rewardsList) {
		if (null == rewardsList) {
			return null;
		}

		List<RewardsClient> resultList = new ArrayList<RewardsClient>();
		for (RewardVo rewards : rewardsList) {
			resultList.add(adapter(rewards));
		}
		return resultList;
	}

	public static DepartmentClient adapter(Department dept) {
		if (dept == null) {
			return null;
		}

		DepartmentClient ret = null; // return value
		DepartmentClient tmpRet = null; // tmp index on current department

		Department i = dept;
		while (i != null) {
			// convert it
			DepartmentClient converted = doAdapt(i);

			// make the parent hierarchy
			if (ret == null) { // first time to run, save it.
				ret = converted;
				tmpRet = ret; // update the ref where parent will be set to.
			} else {
				tmpRet.setParent(converted);
				tmpRet = converted;
			}

			// loop to the next parent
			i = i.getParent();
		}

		return ret;
	}

	protected static DepartmentClient doAdapt(Department dept) {
		DepartmentClient result = new DepartmentClient();
		result.setId(dept.getId());
		result.setName(dept.getName());
		return result;
	}
	
	public static List<RewardsGridClient> adapterGridList(List<RewardGridVo> rewardsList) {
		if (null == rewardsList) {
			return null;
		}

		List<RewardsGridClient> resultList = new ArrayList<RewardsGridClient>();
		for (RewardGridVo rewards : rewardsList) {
			resultList.add(adapterGrid(rewards));
		}
		return resultList;
	}
	
	
	public static RewardsGridClient adapterGrid(RewardGridVo rewards) {
		if (null == rewards) {
			return null;
		}

		RewardsGridClient result = new RewardsGridClient();

//		result.setAccountDept(rewards.getAccountDept().getId());
//		result.setBuilderDept(rewards.getBuilderDept().getId());
//		result.setCreateAt(rewards.getCreatedAt());
//		result.setDefinition(rewards.getDefinition());
//		result.setId(rewards.getId());
//		result.setName(rewards.getName());
//		result.setRewardsItemId(rewards.getRewardItem().getId());
//		result.setStandard(rewards.getStandard());
//		result.setRewardsDate(rewards.getAwardDate());
//		if (rewards.getCreatedBy() != null
//				&& rewards.getCreatedBy().getStaff() != null){
//			result.setCreatedBy(rewards.getCreatedBy().getStaff().getName());
//		}
//		result.setTotalAmtLimit(rewards.getTotalAmtLimit());
//		result.setExpectNominateDate(rewards.getExpectNominateDate());
//		
//		result.setHeadcountLimit(rewards.getHeadcountLimit());
//		if (rewards.getStatus() != null) {
//			result.setStatus(RewardsStatus.valueOf(rewards.getStatus()
//					.toString()));
//		}
//
//		if (rewards.getJudges().size() > 0) {
//			List<JudgeModelClient> judgeList = new ArrayList<JudgeModelClient>();
//			for (Judge judge : rewards.getJudges()) {
//
//				judgeList.add(new JudgeModelClient(judge.getStaff().getId(),
//						judge.getStaff().getName(), judge.getStaff()
//								.getDepartment().getName(), judge.getStatus()
//								.name()));
//			}
//			result.setJudgeList(judgeList);
//		}
//		if (rewards.getNomineeLots().size() > 0) {
//			Map<String,String> map=new HashMap<String, String>();
//			for (NomineeLot lot : rewards.getNomineeLots()) {
//				String nameStr="";
//				for (Nominee nominee : lot.getNominees()) {
//					nameStr+=nominee.getStaff().getName()+";";
//				}
//				map.put(lot.getJudge().getStaff().getId(), nameStr);
//
//			}
//			result.setNomineeLot(map);
//		}
//
//		if (rewards.getWinners().size() > 0) {
//			List<WinnerModelClient> winnerList=new ArrayList<WinnerModelClient>();
//			for (Winner winner : rewards.getWinners()) {
//				WinnerModelClient param=new WinnerModelClient();
//				param.setStaffId(winner.getStaff().getId());
//				param.setName(winner.getStaff().getName());
//				winnerList.add(param);
//			}
//			result.setWinnerList(winnerList);		
//		}
//		

		return result;
	}

}
