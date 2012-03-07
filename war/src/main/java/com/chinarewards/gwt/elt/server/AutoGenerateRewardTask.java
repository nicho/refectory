package com.chinarewards.gwt.elt.server;

import java.util.Date;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinarewards.elt.service.reward.RewardItemService;
import com.chinarewards.elt.util.DateUtil;

public class AutoGenerateRewardTask extends TimerTask {
	Logger logger = LoggerFactory.getLogger(this.getClass());


	private RewardItemService rewardItemService;
	private static AutoGenerateRewardTask instance;


	private AutoGenerateRewardTask() {
	}

	public static AutoGenerateRewardTask getInstance() {
		if (instance == null) {
			instance = new AutoGenerateRewardTask();
		}

		return instance;
	}


	@Override
	public void run() {
		logger.info(" BEGIN to RUN AutoGenerateRewardTask ");
		Date now = DateUtil.getTime();
		rewardItemService.runAutoRewardGeneratorBatch(now);
	}

	public void setRewardItemService(RewardItemService rewardItemService) {
		this.rewardItemService = rewardItemService;
	}
}
