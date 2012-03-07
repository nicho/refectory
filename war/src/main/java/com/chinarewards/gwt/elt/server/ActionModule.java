package com.chinarewards.gwt.elt.server;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

import com.chinarewards.gwt.elt.client.awardReward.request.AwardRewardAddRequest;
import com.chinarewards.gwt.elt.client.awardReward.request.AwardRewardInitRequest;
import com.chinarewards.gwt.elt.client.awardShop.request.SearchAwardShopRequest;
import com.chinarewards.gwt.elt.client.box.request.UserBoxRequest;
import com.chinarewards.gwt.elt.client.broadcastReply.request.BroadcastReplyAddRequest;
import com.chinarewards.gwt.elt.client.broadcastReply.request.SearchBroadcastReplyRequest;
import com.chinarewards.gwt.elt.client.broadcastSave.request.BroadcastSaveRequest;
import com.chinarewards.gwt.elt.client.broadcastSave.request.BroadcastUpdateRequest;
import com.chinarewards.gwt.elt.client.broadcasting.request.SearchBroadcastingListRequest;
import com.chinarewards.gwt.elt.client.budget.request.AddDepartmentBudgetRequest;
import com.chinarewards.gwt.elt.client.budget.request.EditCorpBudgetRequest;
import com.chinarewards.gwt.elt.client.budget.request.InitCorpBudgetByCorpIdRequest;
import com.chinarewards.gwt.elt.client.budget.request.InitCorpBudgetRequest;
import com.chinarewards.gwt.elt.client.budget.request.InitDepartmentRequest;
import com.chinarewards.gwt.elt.client.budget.request.SearchCorpBudgetByCorpIdRequest;
import com.chinarewards.gwt.elt.client.budget.request.SearchDepBudgetRequest;
import com.chinarewards.gwt.elt.client.chooseOrganization.request.ChooseOrganizationRequest;
import com.chinarewards.gwt.elt.client.chooseStaff.request.SearchStaffChooseRequest;
import com.chinarewards.gwt.elt.client.core.request.StaffInitRequest;
import com.chinarewards.gwt.elt.client.department.request.DeleteDepartmentRequest;
import com.chinarewards.gwt.elt.client.department.request.DepartmentLeaderRequest;
import com.chinarewards.gwt.elt.client.department.request.DepartmentManageRequest;
import com.chinarewards.gwt.elt.client.department.request.EditDepartmentRequest;
import com.chinarewards.gwt.elt.client.department.request.MergeDepartmentRequest;
import com.chinarewards.gwt.elt.client.department.request.SearchDepartmentByCorpIdRequest;
import com.chinarewards.gwt.elt.client.department.request.SearchDepartmentByIdRequest;
import com.chinarewards.gwt.elt.client.department.request.SearchDepartmentRequest;
import com.chinarewards.gwt.elt.client.department.request.SearchLeaderRequest;
import com.chinarewards.gwt.elt.client.detailsOfAward.request.DetailsOfAwardInitRequest;
import com.chinarewards.gwt.elt.client.detailsOfBroadcast.request.DetailsOfBroadcastRequest;
import com.chinarewards.gwt.elt.client.detailsOfGift.request.DetailsOfGiftRequest;
import com.chinarewards.gwt.elt.client.enterprise.request.EditIntegralPriceRequest;
import com.chinarewards.gwt.elt.client.enterprise.request.EditPeriodRequest;
import com.chinarewards.gwt.elt.client.enterprise.request.EnterpriseInitRequest;
import com.chinarewards.gwt.elt.client.enterprise.request.EnterpriseRequest;
import com.chinarewards.gwt.elt.client.gift.request.DeleteGiftRequest;
import com.chinarewards.gwt.elt.client.gift.request.EditGiftRequest;
import com.chinarewards.gwt.elt.client.gift.request.SearchGiftByIdRequest;
import com.chinarewards.gwt.elt.client.gift.request.SearchGiftRequest;
import com.chinarewards.gwt.elt.client.gift.request.UpdateGiftStatusRequest;
import com.chinarewards.gwt.elt.client.hrbox.request.HrBoxRewardsRequest;
import com.chinarewards.gwt.elt.client.integralManagement.request.IntegralManagementRequest;
import com.chinarewards.gwt.elt.client.login.LastLoginRoleRequest;
import com.chinarewards.gwt.elt.client.login.LoginRequest;
import com.chinarewards.gwt.elt.client.login.TokenValidRequest;
import com.chinarewards.gwt.elt.client.mail.request.MailRequest;
import com.chinarewards.gwt.elt.client.message.request.SearchMessageListRequest;
import com.chinarewards.gwt.elt.client.messageSave.request.MessageSaveRequest;
import com.chinarewards.gwt.elt.client.nominate.NominateAddRequest;
import com.chinarewards.gwt.elt.client.nominate.NominateInitRequest;
import com.chinarewards.gwt.elt.client.order.request.DeleteOrderRequest;
import com.chinarewards.gwt.elt.client.order.request.OrderBoxRequest;
import com.chinarewards.gwt.elt.client.order.request.OrderViewRequest;
import com.chinarewards.gwt.elt.client.order.request.SearchOrderByIdRequest;
import com.chinarewards.gwt.elt.client.order.request.SearchOrderRequest;
import com.chinarewards.gwt.elt.client.orderConfirmation.request.OrderConfirmationAddRequest;
import com.chinarewards.gwt.elt.client.orderConfirmation.request.OrderConfirmationRequest;
import com.chinarewards.gwt.elt.client.orderHistory.request.OrderHistoryViewRequest;
import com.chinarewards.gwt.elt.client.orderHistory.request.SearchOrderHistoryRequest;
import com.chinarewards.gwt.elt.client.orderSubmit.request.OrderSubmitRequest;
import com.chinarewards.gwt.elt.client.password.request.PasswordRequest;
import com.chinarewards.gwt.elt.client.register.request.RegisterInitRequest;
import com.chinarewards.gwt.elt.client.register.request.RegisterRequest;
import com.chinarewards.gwt.elt.client.registerHr.request.RegisterHrRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.ActivationRewardsItemRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.ActivationRewardsItemStoreRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.CreateRewardsItemRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.DeleteRewardsItemRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchOrganizationRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemByIdRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemStoreRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchRewardsItemViewRequest;
import com.chinarewards.gwt.elt.client.rewardItem.request.SearchStaffRequest;
import com.chinarewards.gwt.elt.client.rewards.request.DeleteRewardsRequest;
import com.chinarewards.gwt.elt.client.rewards.request.SearchRewardsGridRequest;
import com.chinarewards.gwt.elt.client.rewards.request.SearchRewardsRequest;
import com.chinarewards.gwt.elt.client.shopWindow.request.ShopWindowRequest;
import com.chinarewards.gwt.elt.client.staff.request.HrRegisterRequest;
import com.chinarewards.gwt.elt.client.staff.request.LeadTimeRequest;
import com.chinarewards.gwt.elt.client.staffAdd.request.StaffAddRequest;
import com.chinarewards.gwt.elt.client.staffHeavenIndex.request.StaffHeavenIndexRequest;
import com.chinarewards.gwt.elt.client.staffIntegral.request.StaffIntegralRequest;
import com.chinarewards.gwt.elt.client.staffList.request.SearchStaffListRequest;
import com.chinarewards.gwt.elt.client.staffList.request.StaffGenerateUserRequest;
import com.chinarewards.gwt.elt.client.staffList.request.UpdateUserPwdRequest;
import com.chinarewards.gwt.elt.client.staffView.request.StaffViewRequest;
import com.chinarewards.gwt.elt.client.staffView.request.StaffWinRequest;
import com.chinarewards.gwt.elt.client.team.request.DeleteTeamRequest;
import com.chinarewards.gwt.elt.client.team.request.SearchTeamByIdRequest;
import com.chinarewards.gwt.elt.client.team.request.SearchTeamRequest;
import com.chinarewards.gwt.elt.client.team.request.TeamAddRequest;
import com.chinarewards.gwt.elt.client.user.DeleteUserRequest;
import com.chinarewards.gwt.elt.client.user.UserSearchRequest;
import com.chinarewards.gwt.elt.server.awardReward.AwardRewardActionHandler;
import com.chinarewards.gwt.elt.server.awardReward.AwardRewardAddActionHandler;
import com.chinarewards.gwt.elt.server.awardShop.SearchAwardShopHandler;
import com.chinarewards.gwt.elt.server.box.UserBoxHandler;
import com.chinarewards.gwt.elt.server.broadcastReply.BroadcastReplyAddActionHandler;
import com.chinarewards.gwt.elt.server.broadcastReply.SearchBroadcastReplyActionHandler;
import com.chinarewards.gwt.elt.server.broadcastSave.BroadcastUpdateActionHandler;
import com.chinarewards.gwt.elt.server.broadcastSave.BroadcastingSaveActionHandler;
import com.chinarewards.gwt.elt.server.broadcasting.SearchBroadcastingListActionHandler;
import com.chinarewards.gwt.elt.server.budget.AddDepBudgetHandler;
import com.chinarewards.gwt.elt.server.budget.EditCorpBudgetHandler;
import com.chinarewards.gwt.elt.server.budget.InitCorpBudgetByCorpIdHandler;
import com.chinarewards.gwt.elt.server.budget.InitCorpBudgetHandler;
import com.chinarewards.gwt.elt.server.budget.InitDepartmentHandler;
import com.chinarewards.gwt.elt.server.budget.SearchCorpBudgetByCorpIdHandler;
import com.chinarewards.gwt.elt.server.budget.SearchDepBudgetHandler;
import com.chinarewards.gwt.elt.server.chooseOrganization.ChooseOrganizationListActionHandler;
import com.chinarewards.gwt.elt.server.chooseStaff.SearchStaffActionHandler;
import com.chinarewards.gwt.elt.server.core.StaffInitActionHandler;
import com.chinarewards.gwt.elt.server.department.DeleteDepartmentHandler;
import com.chinarewards.gwt.elt.server.department.DepartmentLeaderHandler;
import com.chinarewards.gwt.elt.server.department.DepartmentManageHandler;
import com.chinarewards.gwt.elt.server.department.EditDepartmentHandler;
import com.chinarewards.gwt.elt.server.department.MergeDepartmentHandler;
import com.chinarewards.gwt.elt.server.department.SearchDepartmentByCorpIdHandler;
import com.chinarewards.gwt.elt.server.department.SearchDepartmentByIdHandler;
import com.chinarewards.gwt.elt.server.department.SearchDepartmentHandler;
import com.chinarewards.gwt.elt.server.department.SearchLeaderHandler;
import com.chinarewards.gwt.elt.server.detailsOfAward.DetailsOfAwardActionHandler;
import com.chinarewards.gwt.elt.server.detailsOfBroadcast.DetailsOfBroadcastActionHandler;
import com.chinarewards.gwt.elt.server.detailsOfGift.DetailsOfGiftHandler;
import com.chinarewards.gwt.elt.server.enterprise.EditIntegralPriceHandler;
import com.chinarewards.gwt.elt.server.enterprise.EditPeriodHandler;
import com.chinarewards.gwt.elt.server.enterprise.EnterpriseActionHandler;
import com.chinarewards.gwt.elt.server.enterprise.EnterpriseInitActionHandler;
import com.chinarewards.gwt.elt.server.gift.DeleteGiftHandler;
import com.chinarewards.gwt.elt.server.gift.EditGiftHandler;
import com.chinarewards.gwt.elt.server.gift.SearchGiftByIdHandler;
import com.chinarewards.gwt.elt.server.gift.SearchGiftHandler;
import com.chinarewards.gwt.elt.server.gift.UpdateGiftStatusHandler;
import com.chinarewards.gwt.elt.server.hrbox.HrBoxRewardsHandler;
import com.chinarewards.gwt.elt.server.integralManagement.IntegralManagementHandler;
import com.chinarewards.gwt.elt.server.login.LoginActionHandler;
import com.chinarewards.gwt.elt.server.login.TokenValidActionHandler;
import com.chinarewards.gwt.elt.server.login.UpdatelastLoginRoleActionHandler;
import com.chinarewards.gwt.elt.server.mail.MailSendActionHandler;
import com.chinarewards.gwt.elt.server.message.MessageSaveActionHandler;
import com.chinarewards.gwt.elt.server.message.SearchMessageListActionHandler;
import com.chinarewards.gwt.elt.server.nominate.NominateActionHandler;
import com.chinarewards.gwt.elt.server.nominate.NominateAddActionHandler;
import com.chinarewards.gwt.elt.server.order.DeleteOrderHandler;
import com.chinarewards.gwt.elt.server.order.OrderBoxHandler;
import com.chinarewards.gwt.elt.server.order.OrderViewSubmitHandler;
import com.chinarewards.gwt.elt.server.order.SearchOrderByIdHandler;
import com.chinarewards.gwt.elt.server.order.SearchOrderHandler;
import com.chinarewards.gwt.elt.server.orderConfirmation.AddOrderConfirmationHandler;
import com.chinarewards.gwt.elt.server.orderConfirmation.SearchOrderConfirmationHandler;
import com.chinarewards.gwt.elt.server.orderHistory.OrderHistoryViewHandler;
import com.chinarewards.gwt.elt.server.orderHistory.SearchOrderHistoryHandler;
import com.chinarewards.gwt.elt.server.orderSubmit.AddOrderSubmitHandler;
import com.chinarewards.gwt.elt.server.register.RegisterActionHandler;
import com.chinarewards.gwt.elt.server.register.RegisterHrActionHandler;
import com.chinarewards.gwt.elt.server.register.RegisterInitActionHandler;
import com.chinarewards.gwt.elt.server.rewardItem.ActivationRewardsItemHandler;
import com.chinarewards.gwt.elt.server.rewardItem.ActivationRewardsItemStroeHandler;
import com.chinarewards.gwt.elt.server.rewardItem.CreateRewardsItemHandler;
import com.chinarewards.gwt.elt.server.rewardItem.DeleteRewardsItemHandler;
import com.chinarewards.gwt.elt.server.rewardItem.SearchOrganizationHandler;
import com.chinarewards.gwt.elt.server.rewardItem.SearchRewardsItemByIdHandler;
import com.chinarewards.gwt.elt.server.rewardItem.SearchRewardsItemHandler;
import com.chinarewards.gwt.elt.server.rewardItem.SearchRewardsItemStoreHandler;
import com.chinarewards.gwt.elt.server.rewardItem.SearchRewardsItemViewHandler;
import com.chinarewards.gwt.elt.server.rewardItem.SearchStaffHandler;
import com.chinarewards.gwt.elt.server.rewards.DeleteRewardsHandler;
import com.chinarewards.gwt.elt.server.rewards.SearchRewardsGridHandler;
import com.chinarewards.gwt.elt.server.rewards.SearchRewardsHandler;
import com.chinarewards.gwt.elt.server.shopWindow.SearchShopWindowHandler;
import com.chinarewards.gwt.elt.server.staff.HrRegisterActionHandler;
import com.chinarewards.gwt.elt.server.staff.LeadTimeActionHandler;
import com.chinarewards.gwt.elt.server.staff.SearchStaffIntegralActionHandler;
import com.chinarewards.gwt.elt.server.staff.SearchStaffListActionHandler;
import com.chinarewards.gwt.elt.server.staff.SearchStaffViewActionHandler;
import com.chinarewards.gwt.elt.server.staff.SearchStaffWinActionHandler;
import com.chinarewards.gwt.elt.server.staff.StaffAddActionHandler;
import com.chinarewards.gwt.elt.server.staff.StaffGenerateUserActionHandler;
import com.chinarewards.gwt.elt.server.staffHeavenIndex.StaffHeavenIndexActionHandler;
import com.chinarewards.gwt.elt.server.staffPassword.UpdateStaffPwdActionHandler;
import com.chinarewards.gwt.elt.server.team.SearchTeamByIdHandler;
import com.chinarewards.gwt.elt.server.team.SearchTeamHandler;
import com.chinarewards.gwt.elt.server.team.TeamHandler;
import com.chinarewards.gwt.elt.server.user.DeleteTeamByIdHandler;
import com.chinarewards.gwt.elt.server.user.DeleteUserActionHandler;
import com.chinarewards.gwt.elt.server.user.UpdateUserPwdActionHandler;
import com.chinarewards.gwt.elt.server.user.UserSearchActionHandler;

/**
 * 
 * @author cyril
 * 
 */
public class ActionModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		// login module
		bindHandler(LoginRequest.class, LoginActionHandler.class);

		// user module
		bindHandler(UserSearchRequest.class, UserSearchActionHandler.class);
		
		//部门列表
		bindHandler(DepartmentManageRequest.class,DepartmentManageHandler.class);
		//Leader部门列表
		bindHandler(DepartmentLeaderRequest.class,DepartmentLeaderHandler.class);
		
		bindHandler(SearchDepartmentRequest.class, SearchDepartmentHandler.class);	   
		bindHandler(SearchDepartmentByIdRequest.class,SearchDepartmentByIdHandler.class);		
		bindHandler(SearchDepartmentByCorpIdRequest.class,SearchDepartmentByCorpIdHandler.class);		
		
		//选择Leader
		bindHandler(SearchLeaderRequest.class,SearchLeaderHandler.class);
		
		//部门编辑
		bindHandler(EditDepartmentRequest.class, EditDepartmentHandler.class); 		
		//部门删除
		bindHandler(DeleteDepartmentRequest.class, DeleteDepartmentHandler.class); 
		//合并部门
		bindHandler(MergeDepartmentRequest.class, MergeDepartmentHandler.class); 	
		
		// staff module
		bindHandler(HrRegisterRequest.class, HrRegisterActionHandler.class);

		//Nominate module
		bindHandler(NominateInitRequest.class, NominateActionHandler.class);
		//Nominate add module
		bindHandler(NominateAddRequest.class, NominateAddActionHandler.class);
		//是否有企业
		bindHandler(RegisterInitRequest.class, RegisterInitActionHandler.class);
		//HR企业注册
		bindHandler(RegisterRequest.class, RegisterActionHandler.class);
		//HR账户初始化
		bindHandler(RegisterHrRequest.class, RegisterHrActionHandler.class);
		//企业注册修改
		bindHandler(EnterpriseRequest.class, EnterpriseActionHandler.class);
		bindHandler(EnterpriseInitRequest.class, EnterpriseInitActionHandler.class);
		
		bindHandler(EditIntegralPriceRequest.class,EditIntegralPriceHandler.class);
		bindHandler(EditPeriodRequest.class, EditPeriodHandler.class);
		
		
		//奖 项
        bindHandler(CreateRewardsItemRequest.class,CreateRewardsItemHandler.class);
        //奖 项修改
        bindHandler(SearchRewardsItemByIdRequest.class,SearchRewardsItemByIdHandler.class);
        //奖项列表
        bindHandler(SearchRewardsItemRequest.class,SearchRewardsItemHandler.class);
		
      //奖项库列表
        bindHandler(SearchRewardsItemStoreRequest.class,SearchRewardsItemStoreHandler.class);
      //奖项详细
        bindHandler(SearchRewardsItemViewRequest.class,SearchRewardsItemViewHandler.class);
        //奖 项快速选人
        bindHandler(SearchOrganizationRequest.class,SearchOrganizationHandler.class);
        //奖 项弹出选择员工
        bindHandler(SearchStaffRequest.class,SearchStaffHandler.class);
      //删除奖项
      	bindHandler(DeleteRewardsItemRequest.class, DeleteRewardsItemHandler.class);
		
		//奖励列表
		bindHandler(SearchRewardsRequest.class, SearchRewardsHandler.class);
		//奖项小控件
		bindHandler(SearchRewardsGridRequest.class, SearchRewardsGridHandler.class);
						
		bindHandler(SearchStaffChooseRequest.class, SearchStaffActionHandler.class);

		//颁奖
		bindHandler(AwardRewardInitRequest.class, AwardRewardActionHandler.class);

		bindHandler(AwardRewardAddRequest.class, AwardRewardAddActionHandler.class);

		//奖励详细
		bindHandler(DetailsOfAwardInitRequest.class, DetailsOfAwardActionHandler.class);
		
		//激活(应用)奖项
		bindHandler(ActivationRewardsItemRequest.class, ActivationRewardsItemHandler.class);
		
		//登录验证token
		bindHandler(TokenValidRequest.class, TokenValidActionHandler.class);
		
		//奖励删除
		bindHandler(DeleteRewardsRequest.class, DeleteRewardsHandler.class);
		
		//礼品列表
		bindHandler(SearchGiftRequest.class, SearchGiftHandler.class);	   
		bindHandler(SearchGiftByIdRequest.class,SearchGiftByIdHandler.class);		
		//礼品编辑
		bindHandler(EditGiftRequest.class, EditGiftHandler.class); 		
		//礼品删除
		bindHandler(DeleteGiftRequest.class, DeleteGiftHandler.class); 
		//礼品修改状态
		bindHandler(UpdateGiftStatusRequest.class, UpdateGiftStatusHandler.class); 
		
		//用户删除(离职)
		bindHandler(DeleteUserRequest.class, DeleteUserActionHandler.class); 
		//奖项库.应用到奖项
		bindHandler(ActivationRewardsItemStoreRequest.class, ActivationRewardsItemStroeHandler.class); 

		//奖品商城查询
		bindHandler(SearchAwardShopRequest.class, SearchAwardShopHandler.class); 

		
		//定单列表
		bindHandler(SearchOrderRequest.class, SearchOrderHandler.class);
		//定单读一条数据
		bindHandler(SearchOrderByIdRequest.class, SearchOrderByIdHandler.class);
		//管理员定单详细操作
		bindHandler(OrderViewRequest.class, OrderViewSubmitHandler.class);
		//管理员定单收件箱
		bindHandler(OrderBoxRequest.class, OrderBoxHandler.class);
		
		//橱窗功能
		bindHandler(ShopWindowRequest.class, SearchShopWindowHandler.class);
		//订单提交-查询礼品
		bindHandler(OrderConfirmationRequest.class, SearchOrderConfirmationHandler.class);
		//订单提交-添加订单
		bindHandler(OrderConfirmationAddRequest.class, AddOrderConfirmationHandler.class);
		//订单确定-修改状态
		bindHandler(OrderSubmitRequest.class, AddOrderSubmitHandler.class);
		//礼品详细
		bindHandler(DetailsOfGiftRequest.class, DetailsOfGiftHandler.class);
		
		//取消订单
		bindHandler(DeleteOrderRequest.class, DeleteOrderHandler.class);
		//记录最后一次登录role
		bindHandler(LastLoginRoleRequest.class, UpdatelastLoginRoleActionHandler.class);
		//兑换历史列表
		bindHandler(SearchOrderHistoryRequest.class, SearchOrderHistoryHandler.class);
		//兑换历史查看
		bindHandler(OrderHistoryViewRequest.class, OrderHistoryViewHandler.class);


		//积分管理
		bindHandler(IntegralManagementRequest.class, IntegralManagementHandler.class);

		//部门预算
		bindHandler(SearchDepBudgetRequest.class, SearchDepBudgetHandler.class);
		
		//部门预算时得到企业预算
		bindHandler(InitCorpBudgetRequest.class, InitCorpBudgetHandler.class);
		//部门预算时得到一级部门
		bindHandler(InitDepartmentRequest.class, InitDepartmentHandler.class);
		//新增部门预算
		bindHandler(AddDepartmentBudgetRequest.class, AddDepBudgetHandler.class);

		bindHandler(EditCorpBudgetRequest.class, EditCorpBudgetHandler.class);
		bindHandler(SearchCorpBudgetByCorpIdRequest.class,SearchCorpBudgetByCorpIdHandler.class);
		bindHandler(InitCorpBudgetByCorpIdRequest.class,InitCorpBudgetByCorpIdHandler.class);
		//员工列表
		bindHandler(SearchStaffListRequest.class,SearchStaffListActionHandler.class);
		//员工添加
		bindHandler(StaffAddRequest.class,StaffAddActionHandler.class);
		//员工详细信息
		bindHandler(StaffViewRequest.class,SearchStaffViewActionHandler.class);
		//查看员工积分
		bindHandler(StaffIntegralRequest.class,SearchStaffIntegralActionHandler.class);
		
		//员工获奖情况
		bindHandler(StaffWinRequest.class,SearchStaffWinActionHandler.class);
		//员工生成用户
		bindHandler(StaffGenerateUserRequest.class,StaffGenerateUserActionHandler.class);
		//员工修改密码
		bindHandler(UpdateUserPwdRequest.class,UpdateUserPwdActionHandler.class);
		//广播列表
		bindHandler(SearchBroadcastingListRequest.class,SearchBroadcastingListActionHandler.class);
		
		//小组列表
		bindHandler(SearchTeamRequest.class,SearchTeamHandler.class);
		//小组增加
		bindHandler(TeamAddRequest.class,TeamHandler.class);
		//小组删除
		bindHandler(DeleteTeamRequest.class,DeleteTeamByIdHandler.class);
		//小组查找BYID
		bindHandler(SearchTeamByIdRequest.class,SearchTeamByIdHandler.class);
		//选择用户/部门/小组/机构
		bindHandler(ChooseOrganizationRequest.class,ChooseOrganizationListActionHandler.class);
		//保存广播
		bindHandler(BroadcastSaveRequest.class,BroadcastingSaveActionHandler.class);
		//广播详细
		bindHandler(DetailsOfBroadcastRequest.class,DetailsOfBroadcastActionHandler.class);
		//广播修改
		bindHandler(BroadcastUpdateRequest.class,BroadcastUpdateActionHandler.class);
		//广播回复
		bindHandler(SearchBroadcastReplyRequest.class,SearchBroadcastReplyActionHandler.class);
		//回复保存
		bindHandler(BroadcastReplyAddRequest.class,BroadcastReplyAddActionHandler.class);
		//员工天地首页
		bindHandler(StaffHeavenIndexRequest.class,StaffHeavenIndexActionHandler.class);
		//员工天地,员工数据加载
		bindHandler(StaffInitRequest.class,StaffInitActionHandler.class);
		//颁奖提前通知时间设置
		bindHandler(LeadTimeRequest.class, LeadTimeActionHandler.class);
		//信息
		bindHandler(SearchMessageListRequest.class,SearchMessageListActionHandler.class);
		//leader收件箱
		bindHandler(UserBoxRequest.class,UserBoxHandler.class);
		//信息添加
		bindHandler(MessageSaveRequest.class,MessageSaveActionHandler.class);
		//leader收件箱奖励查询
		bindHandler(HrBoxRewardsRequest.class,HrBoxRewardsHandler.class);
        
		//发送邮件
		bindHandler(MailRequest.class,MailSendActionHandler.class);
		//员工天地修改密码
		bindHandler(PasswordRequest.class,UpdateStaffPwdActionHandler.class);

	}
}
