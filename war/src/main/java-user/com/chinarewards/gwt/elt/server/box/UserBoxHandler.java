package com.chinarewards.gwt.elt.server.box;

import java.util.Date;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.slf4j.Logger;

import com.chinarewards.elt.model.reward.frequency.WeekDays;
import com.chinarewards.elt.service.reward.RewardService;
import com.chinarewards.elt.util.WeekUtil;
import com.chinarewards.gwt.elt.client.box.request.UserBoxRequest;
import com.chinarewards.gwt.elt.client.box.request.UserBoxResponse;
import com.chinarewards.gwt.elt.server.BaseActionHandler;
import com.chinarewards.gwt.elt.server.logger.InjectLogger;
import com.google.inject.Inject;

/**
 * @author lw
 * @since 2012年2月1日 10:51:12
 */
public class UserBoxHandler extends
		BaseActionHandler<UserBoxRequest, UserBoxResponse> {

	@InjectLogger
	Logger logger;

	RewardService rewardService;

	@Inject
	public UserBoxHandler(RewardService rewardService) {
		this.rewardService = rewardService;

	}

	@Override
	public UserBoxResponse execute(UserBoxRequest request,
			ExecutionContext context) throws DispatchException {
	
		String staffId = request.getUserSession().getStaffId();
		int rs = 0;
		WeekDays week=WeekUtil.getCurrTimeWeek(new Date());
		String wk=week.getMessage();
		if(request.getStatus().trim().equals("PENDING_NOMINATE"))//待提名的
			 rs = rewardService.getNominatorByStaffId(staffId);
		if(request.getStatus().trim().equals("NEW"))//待颁奖
			 rs = rewardService.getRewardsByStaffId(staffId);
		
		return new UserBoxResponse(rs,wk);
	}

	@Override
	public Class<UserBoxRequest> getActionType() {
		return UserBoxRequest.class;
	}

	@Override
	public void rollback(UserBoxRequest req, UserBoxResponse resp,
			ExecutionContext cxt) throws DispatchException {
	}

}
