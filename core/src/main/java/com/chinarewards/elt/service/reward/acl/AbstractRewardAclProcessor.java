package com.chinarewards.elt.service.reward.acl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The abstract implementation of {@link RewardAclProcessor }.
 * 
 * @author yanxin
 * @since 1.0
 */
public abstract class AbstractRewardAclProcessor implements
		RewardAclProcessor {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
