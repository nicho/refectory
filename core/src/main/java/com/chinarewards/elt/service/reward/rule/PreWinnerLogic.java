package com.chinarewards.elt.service.reward.rule;

import java.util.List;

import com.chinarewards.elt.domain.reward.person.PreWinner;
import com.chinarewards.elt.domain.reward.person.PreWinnerLot;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.reward.exception.NoEffectivePreWinnerException;

/**
 * Provides some methods to deal with {@link PreWinnerLot} and {@link PreWinner}
 * 
 * @author yanxin
 * @since 1.0
 */
public interface PreWinnerLogic {

	/**
	 * Add pre-winner from candidate.
	 * 
	 * @param caller
	 * @param rewardId
	 */
	public String addPreWinnerFromCandidateOfReward(SysUser caller,
			String rewardId);

	/**
	 * Add pre-winner by outer staff id list.
	 * 
	 * @param caller
	 * @param rewardId
	 * @param staffIds
	 * @return
	 */
	public String addPreWinnerFromOuter(SysUser caller, String rewardId,
			List<String> staffIds);

	/**
	 * Get untreated Pre-Winner Lot from the specified Reward.
	 * 
	 * @param rewardId
	 * @return
	 * @throws NoEffectivePreWinnerException
	 *             There is no effective {@link PreWinnerLot}.
	 */
	public PreWinnerLot getUntreatedPreWinnerLotOfReward(String rewardId)
			throws NoEffectivePreWinnerException;

	/**
	 * Get passed Pre-Winner Lot from the specified Reward.
	 * 
	 * @param rewardId
	 * @return
	 * @throws NoEffectivePreWinnerException
	 */
	public PreWinnerLot getPassedPreWinnerOfReward(String rewardId)
			throws NoEffectivePreWinnerException;

	/**
	 * Get list of prewinnerlot from the specified reward.
	 * 
	 * @param rewardId
	 * @return
	 */
	public List<PreWinnerLot> getPreWinnerLotsFromReward(String rewardId);

	/**
	 * Get the list of PreWinner from lot id.
	 * 
	 * @param lotId
	 * @return
	 */
	public List<PreWinner> getPreWinnerListFromLot(String lotId);

	/**
	 * Deny a Pre-Winner lot.
	 * 
	 * @param caller
	 * @param lotId
	 */
	public void denyPreWinnerLot(SysUser caller, String lotId, String reason);
}
