/**
 * 
 */
package com.chinarewards.elt.model.reward.frequency;

import java.util.Comparator;

/**
 * Current implementation treats Monday as the least (first) element and Sunday
 * as the largest (last) element.
 * 
 * @author cyril
 * @since 0.2.0
 */
public class WeekConstComparator implements Comparator<WeekDays> {

	public int compare(WeekDays o1, WeekDays o2) {

		if (o1.getFlag() == o2.getFlag())
			return 0;

		return o1.getFlag() > o2.getFlag() ? 1 : -1;
	}

}
