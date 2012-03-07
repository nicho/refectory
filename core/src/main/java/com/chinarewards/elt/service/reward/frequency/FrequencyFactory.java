package com.chinarewards.elt.service.reward.frequency;

import com.chinarewards.elt.domain.reward.frequency.Frequency;
import com.chinarewards.elt.model.reward.frequency.RewardsFrequency;

/**
 * The factory to get the suitable processor to manipulate given frequency.
 * 
 * @author yanxin
 * @since 1.0
 */
public interface FrequencyFactory {

	/**
	 * Obtains a processor according to the given frequency.
	 * 
	 * @param frequency
	 * @return
	 * @throws IllegalArgumentException
	 *             if no processor can be found.
	 */
	public FrequencyProcessor getProcessor(RewardsFrequency frequency);

	/**
	 * Obtains a processor according to the given frequency.
	 * 
	 * @param frequency
	 * @return
	 * @throws IllegalArgumentException
	 *             if no processor can be found.
	 */
	public FrequencyProcessor getProcessor(Frequency frequency);

}
