package com.chinarewards.elt.guice.sub;

import com.chinarewards.elt.dao.reward.CandidateDao;
import com.chinarewards.elt.dao.reward.CandidateRuleDao;
import com.chinarewards.elt.dao.reward.DayFrequencyDao;
import com.chinarewards.elt.dao.reward.DirectCandidateDataDao;
import com.chinarewards.elt.dao.reward.DirectCandidateRuleDao;
import com.chinarewards.elt.dao.reward.DobRuleDao;
import com.chinarewards.elt.dao.reward.FrequencyDao;
import com.chinarewards.elt.dao.reward.JudgeDao;
import com.chinarewards.elt.dao.reward.MonthFrequencyDao;
import com.chinarewards.elt.dao.reward.NomineeDao;
import com.chinarewards.elt.dao.reward.PreWinnerDao;
import com.chinarewards.elt.dao.reward.PreWinnerLotDao;
import com.chinarewards.elt.dao.reward.RewardDao;
import com.chinarewards.elt.dao.reward.RewardItemDao;
import com.chinarewards.elt.dao.reward.RewardItemTypeDao;
import com.chinarewards.elt.dao.reward.WeekFrequencyDao;
import com.chinarewards.elt.dao.reward.WeekFrequencyDaysDao;
import com.chinarewards.elt.dao.reward.WinnerDao;
import com.chinarewards.elt.dao.reward.YearFrequencyDao;
import com.chinarewards.elt.service.reward.RewardGridLogic;
import com.chinarewards.elt.service.reward.RewardGridLogicImpl;
import com.chinarewards.elt.service.reward.RewardGridService;
import com.chinarewards.elt.service.reward.RewardGridServiceImpl;
import com.chinarewards.elt.service.reward.RewardItemLogic;
import com.chinarewards.elt.service.reward.RewardItemLogicImpl;
import com.chinarewards.elt.service.reward.RewardItemService;
import com.chinarewards.elt.service.reward.RewardItemServiceImpl;
import com.chinarewards.elt.service.reward.RewardLogic;
import com.chinarewards.elt.service.reward.RewardLogicImpl;
import com.chinarewards.elt.service.reward.RewardService;
import com.chinarewards.elt.service.reward.RewardServiceImpl;
import com.chinarewards.elt.service.reward.acl.RewardAclProcessor;
import com.chinarewards.elt.service.reward.acl.RewardAclProcessorDept;
import com.chinarewards.elt.service.reward.acl.RewardAclProcessorFactory;
import com.chinarewards.elt.service.reward.acl.RewardAclProcessorFactoryImpl;
import com.chinarewards.elt.service.reward.acl.RewardAclProcessorHr;
import com.chinarewards.elt.service.reward.frequency.FrequencyFactory;
import com.chinarewards.elt.service.reward.frequency.FrequencyFactoryImpl;
import com.chinarewards.elt.service.reward.frequency.FrequencyLogic;
import com.chinarewards.elt.service.reward.frequency.FrequencyLogicImpl;
import com.chinarewards.elt.service.reward.frequency.FrequencyProcessor;
import com.chinarewards.elt.service.reward.frequency.FrequencyProcessorDay;
import com.chinarewards.elt.service.reward.frequency.FrequencyProcessorMonth;
import com.chinarewards.elt.service.reward.frequency.FrequencyProcessorWeek;
import com.chinarewards.elt.service.reward.frequency.FrequencyProcessorYear;
import com.chinarewards.elt.service.reward.nominee.NomineeService;
import com.chinarewards.elt.service.reward.nominee.NomineeServiceImpl;
import com.chinarewards.elt.service.reward.rule.AwardApprovalDeterminer;
import com.chinarewards.elt.service.reward.rule.CandidateLogic;
import com.chinarewards.elt.service.reward.rule.CandidateLogicImpl;
import com.chinarewards.elt.service.reward.rule.CandidateRuleLogic;
import com.chinarewards.elt.service.reward.rule.CandidateRuleLogicImpl;
import com.chinarewards.elt.service.reward.rule.JudgeLogic;
import com.chinarewards.elt.service.reward.rule.JudgeLogicImpl;
import com.chinarewards.elt.service.reward.rule.NomineeLogic;
import com.chinarewards.elt.service.reward.rule.NomineeLogicImpl;
import com.chinarewards.elt.service.reward.rule.PreWinnerLogic;
import com.chinarewards.elt.service.reward.rule.PreWinnerLogicImpl;
import com.chinarewards.elt.service.reward.rule.SimpleAwardApprovalDeterminer;
import com.chinarewards.elt.service.reward.rule.WinnerLogic;
import com.chinarewards.elt.service.reward.rule.WinnerLogicImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

/**
 * Here is the module file of Reward module to configure the beanss.
 * 
 * @author yanxin
 * @since 1.0
 */
public class RewardModule extends AbstractModule {

	@Override
	protected void configure() {
		// DAO
		bind(CandidateDao.class).in(Singleton.class);
		bind(CandidateRuleDao.class).in(Singleton.class);
		bind(DayFrequencyDao.class).in(Singleton.class);
		bind(DirectCandidateDataDao.class).in(Singleton.class);
		bind(DirectCandidateRuleDao.class).in(Singleton.class);
		bind(DobRuleDao.class).in(Singleton.class);
		bind(FrequencyDao.class).in(Singleton.class);
		bind(JudgeDao.class).in(Singleton.class);
		bind(MonthFrequencyDao.class).in(Singleton.class);
		bind(NomineeDao.class).in(Singleton.class);
		bind(PreWinnerDao.class).in(Singleton.class);
		bind(PreWinnerLotDao.class).in(Singleton.class);
		bind(RewardDao.class).in(Singleton.class);
		bind(RewardItemDao.class).in(Singleton.class);
		bind(RewardItemTypeDao.class).in(Singleton.class);
		bind(WeekFrequencyDao.class).in(Singleton.class);
		bind(WeekFrequencyDaysDao.class).in(Singleton.class);
		bind(WinnerDao.class).in(Singleton.class);
		bind(YearFrequencyDao.class).in(Singleton.class);

		// Service of logic
		bind(NomineeService.class).to(NomineeServiceImpl.class).in(
				Singleton.class);
		bind(RewardService.class).to(RewardServiceImpl.class).in(
				Singleton.class);
		bind(RewardItemService.class).to(RewardItemServiceImpl.class).in(
				Singleton.class);
		bind(RewardItemLogic.class).to(RewardItemLogicImpl.class).in(
				Singleton.class);
		bind(RewardLogic.class).to(RewardLogicImpl.class).in(Singleton.class);
		bind(RewardAclProcessorFactory.class).to(
				RewardAclProcessorFactoryImpl.class).in(Singleton.class);
		bind(RewardAclProcessor.class).annotatedWith(
				Names.named("RewardAclProcessorHr")).to(
				RewardAclProcessorHr.class);
		bind(RewardAclProcessor.class).annotatedWith(
				Names.named("RewardAclProcessorDept")).to(
				RewardAclProcessorDept.class);
		
		bind(RewardGridService.class).to(RewardGridServiceImpl.class).in(
				Singleton.class);
		bind(RewardGridLogic.class).to(RewardGridLogicImpl.class).in(
				Singleton.class);

		bind(FrequencyLogic.class).to(FrequencyLogicImpl.class);
		bind(FrequencyFactory.class).to(FrequencyFactoryImpl.class);
		bind(FrequencyProcessor.class).annotatedWith(
				Names.named("yearFrequencyProcessor")).to(
				FrequencyProcessorYear.class);
		bind(FrequencyProcessor.class).annotatedWith(
				Names.named("weekFrequencyProcessor")).to(
				FrequencyProcessorWeek.class);
		bind(FrequencyProcessor.class).annotatedWith(
				Names.named("monthFrequencyProcessor")).to(
				FrequencyProcessorMonth.class);
		bind(FrequencyProcessor.class).annotatedWith(
				Names.named("dayFrequencyProcessor")).to(
				FrequencyProcessorDay.class);

		bind(CandidateLogic.class).to(CandidateLogicImpl.class).in(
				Singleton.class);
		bind(CandidateRuleLogic.class).to(CandidateRuleLogicImpl.class).in(
				Singleton.class);
		bind(JudgeLogic.class).to(JudgeLogicImpl.class).in(Singleton.class);
		bind(NomineeLogic.class).to(NomineeLogicImpl.class).in(Singleton.class);
		bind(PreWinnerLogic.class).to(PreWinnerLogicImpl.class).in(
				Singleton.class);
		bind(WinnerLogic.class).to(WinnerLogicImpl.class).in(Singleton.class);

		bind(AwardApprovalDeterminer.class).to(
				SimpleAwardApprovalDeterminer.class).in(Singleton.class);
	}

}
