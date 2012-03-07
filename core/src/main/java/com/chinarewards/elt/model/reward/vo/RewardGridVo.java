package com.chinarewards.elt.model.reward.vo;

import java.util.Date;
import java.util.List;

import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.person.Candidate;
import com.chinarewards.elt.domain.reward.person.Judge;
import com.chinarewards.elt.domain.reward.person.NomineeLot;
import com.chinarewards.elt.domain.reward.person.Winner;
import com.chinarewards.elt.domain.reward.rule.CandidateRule;

/**
 * The data container about RewardGrid 员工 奖励小控件 对象
 * 
 * @author yanrui
 * @since 1.5
 */
public class RewardGridVo {

	private Winner winner = new Winner();
	private Reward reward = new Reward();
	private RewardItem rewardItem = new RewardItem();

	private String winnerId = "";
	// 奖励
	private String rewardId = "";
	private String rewardName = "";
	private Date rewardsDate;
	private String awardName = "";// 颁奖人
	private double awardAmt = Double.valueOf(0);// 每人积分
	private String rewardStatusName = "";

	// 奖项
	private String rewardItemId = "";
	private String rewardItemName = "";
	private String rewardsItemCreateBy;// 奖项创建人
	private String rewardItemPhoto = "";

	private String corporationId = "";

	private String nominateName = "";
	private int nominateCount;

	private CandidateRule candidateRule;
	private List<Candidate> candidateList;
	private List<Judge> judgeList;
	private List<NomineeLot> nomineeLotList;
	private List<Winner> winnerList;

	public String getWinnersName() {
		String winnersName = "";
		if (winnerList != null) {
			for (int i = 0; i < winnerList.size(); i++) {
				Winner winner = winnerList.get(i);
				if (winner != null) {
					winnersName += winner.getStaff().getName() + ",";
				}
			}
		}

		int subIndex = winnersName.lastIndexOf(",");
		if (subIndex > -1) {
			winnersName = winnersName.substring(0, subIndex);
		}

		return winnersName;
	}

	public String getRewardStatusName() {
		return rewardStatusName;
	}

	public void setRewardStatusName(String rewardStatusName) {
		this.rewardStatusName = rewardStatusName;
	}

	public String getRewardsItemCreateBy() {
		return rewardsItemCreateBy;
	}

	public void setRewardsItemCreateBy(String rewardsItemCreateBy) {
		this.rewardsItemCreateBy = rewardsItemCreateBy;
	}

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public Date getRewardsDate() {
		return rewardsDate;
	}

	public void setRewardsDate(Date rewardsDate) {
		this.rewardsDate = rewardsDate;
	}

	public CandidateRule getCandidateRule() {
		return candidateRule;
	}

	public void setCandidateRule(CandidateRule candidateRule) {
		this.candidateRule = candidateRule;
	}

	public List<Candidate> getCandidateList() {
		return candidateList;
	}

	public void setCandidateList(List<Candidate> candidateList) {
		this.candidateList = candidateList;
	}

	public List<Judge> getJudgeList() {
		return judgeList;
	}

	public void setJudgeList(List<Judge> judgeList) {
		this.judgeList = judgeList;
	}

	public List<NomineeLot> getNomineeLotList() {
		return nomineeLotList;
	}

	public void setNomineeLotList(List<NomineeLot> nomineeLotList) {
		this.nomineeLotList = nomineeLotList;
	}

	public List<Winner> getWinnerList() {
		return winnerList;
	}

	public void setWinnerList(List<Winner> winnerList) {
		this.winnerList = winnerList;
	}

	public String getNominateName() {
		if (nomineeLotList != null) {
			for (int i = 0; i < nomineeLotList.size(); i++) {
				NomineeLot nomineeLot = nomineeLotList.get(i);
				if (nomineeLot != null) {
					nominateName += nomineeLot.getCreatedBy().getStaff()
							.getName()
							+ ",";
				}
			}
		}

		int subIndex = nominateName.lastIndexOf(",");
		if (subIndex > -1) {
			nominateName = nominateName.substring(0, subIndex);
		}

		return nominateName;

	}

	public void setNominateName(String nominateName) {
		this.nominateName = nominateName;
	}

	public int getNominateCount() {
		return nominateCount;
	}

	public void setNominateCount(int nominateCount) {
		this.nominateCount = nominateCount;
	}

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	public String getRewardItemPhoto() {
		return rewardItemPhoto;
	}

	public void setRewardItemPhoto(String rewardItemPhoto) {
		this.rewardItemPhoto = rewardItemPhoto;
	}

	public Winner getWinner() {
		return winner;
	}

	public void setWinner(Winner winner) {
		this.winner = winner;
	}

	public String getWinnerId() {
		return winnerId;
	}

	public void setWinnerId(String winnerId) {
		this.winnerId = winnerId;
	}

	public String getRewardId() {
		return rewardId;
	}

	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
	}

	public String getRewardName() {
		return rewardName;
	}

	public void setRewardName(String rewardName) {
		this.rewardName = rewardName;
	}

	public String getRewardItemId() {
		return rewardItemId;
	}

	public void setRewardItemId(String rewardItemId) {
		this.rewardItemId = rewardItemId;
	}

	public String getRewardItemName() {
		return rewardItemName;
	}

	public void setRewardItemName(String rewardItemName) {
		this.rewardItemName = rewardItemName;
	}

	public double getAwardAmt() {
		return awardAmt;
	}

	public void setAwardAmt(double awardAmt) {
		this.awardAmt = awardAmt;
	}

	public Reward getReward() {
		return reward;
	}

	public void setReward(Reward reward) {
		this.reward = reward;
	}

	public RewardItem getRewardItem() {
		return rewardItem;
	}

	public void setRewardItem(RewardItem rewardItem) {
		this.rewardItem = rewardItem;
	}

}
