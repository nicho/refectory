package com.chinarewards.gwt.elt.client.core.ui.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.chinarewards.gwt.elt.client.awardReward.plugin.AwardRewardConstants;
import com.chinarewards.gwt.elt.client.box.plugin.UserBoxConstants;
import com.chinarewards.gwt.elt.client.breadCrumbs.ui.BreadCrumbsMenu;
import com.chinarewards.gwt.elt.client.broadcasting.plugin.BroadcastingListConstants;
import com.chinarewards.gwt.elt.client.budget.plugin.CorpBudgetConstants;
import com.chinarewards.gwt.elt.client.budget.plugin.CreateBudgetConstants;
import com.chinarewards.gwt.elt.client.core.ui.MenuItem;
import com.chinarewards.gwt.elt.client.core.ui.MenuProcessor;
import com.chinarewards.gwt.elt.client.core.ui.event.MenuClickEvent;
import com.chinarewards.gwt.elt.client.department.plugin.DepartmentLeaderConstants;
import com.chinarewards.gwt.elt.client.department.plugin.DepartmentListConstants;
import com.chinarewards.gwt.elt.client.detailsOfAward.plugin.DetailsOfAwardConstants;
import com.chinarewards.gwt.elt.client.enterprise.plugin.EnterpriseConstants;
import com.chinarewards.gwt.elt.client.gift.plugin.GiftConstants;
import com.chinarewards.gwt.elt.client.gift.plugin.GiftListConstants;
import com.chinarewards.gwt.elt.client.hrbox.plugin.HrBoxConstants;
import com.chinarewards.gwt.elt.client.integralManagement.plugin.IntegralManagementConstants;
import com.chinarewards.gwt.elt.client.mvp.EventBus;
import com.chinarewards.gwt.elt.client.nominate.plugin.NominateConstants;
import com.chinarewards.gwt.elt.client.order.plugin.OrderListConstants;
import com.chinarewards.gwt.elt.client.order.plugin.OrderViewConstants;
import com.chinarewards.gwt.elt.client.rewardItem.plugin.RewardsItemConstants;
import com.chinarewards.gwt.elt.client.rewards.plugin.RewardsListConstants;
import com.chinarewards.gwt.elt.client.staff.plugin.LeadTimeConstants;
import com.chinarewards.gwt.elt.client.staffList.plugin.StaffListConstants;
import com.chinarewards.gwt.elt.client.support.SessionManager;
import com.chinarewards.gwt.elt.client.team.plugin.TeamConstants;
import com.chinarewards.gwt.elt.model.user.UserRoleVo;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class ButtonMenuProcessor implements MenuProcessor {

	final EventBus eventBus;
	final BreadCrumbsMenu breadCrumbsMenu;
	final SessionManager sessionManager;
	List<MenuItem> items = new LinkedList<MenuItem>();
	MenuNode root = new MenuNode(null);
	VerticalPanel grid;

	@Inject
	public ButtonMenuProcessor(EventBus eventBus,
			BreadCrumbsMenu breadCrumbsMenu,SessionManager sessionManager) {
		this.eventBus = eventBus;
		this.breadCrumbsMenu = breadCrumbsMenu;
		this.sessionManager=sessionManager;

	}

	public void add(MenuItem item) {
		items.add(item);
	}

	public MenuItem getMenuItem(String menuId) {
		if (menuId != null) {
			for (MenuItem i : items) {
				if (menuId.endsWith(i.getMenuId())) {
					return i;
				}
			}
		}
		return null;
	}

	public void render(Panel container) {
		Collections.sort(items, new Comparator<MenuItem>() {
			public int compare(MenuItem paramT1, MenuItem paramT2) {
				return paramT1.getMenuId().length()
						- paramT2.getMenuId().length();
			}
		});

		for (MenuItem m : items) {
			root.appendChild(new MenuNode(m));
		}

		String indexMenu="";
		if(sessionManager.getSession().getLastLoginRole()==UserRoleVo.CORP_ADMIN || sessionManager.getSession().getLastLoginRole()==UserRoleVo.DEPT_MGR)
			indexMenu="RewardItem";
		else if(sessionManager.getSession().getLastLoginRole()==UserRoleVo.GIFT)
			indexMenu="Gift";
		
		ScrollPanel menuWrapper = new ScrollPanel(createButtonMenuWidget(indexMenu));
		container.add(menuWrapper);
	}

	/**
	 * 
	 * 
	 * @param parent
	 * @param node
	 */
	private Widget createButtonMenuWidget(String name) {
		breadCrumbsMenu.cleanBreadCrumbsItemAll();
		grid = new VerticalPanel();
		grid.setWidth("100%");
		// int i = 0;
		for (MenuNode node : root.getChildren()) {
			final Anchor button = new Anchor();
			final MenuItem menuItem = node.getValue();
			if (name != null) {
				List<String> items = getMenuItemName(name);
				if (!items.contains(menuItem.getMenuId()))
					continue;
			} else {
				break;
			}

			button.setText(menuItem.getTitle());
			button.setStyleName("menu-link");

			// 判断第一个进入默认样式
			String menuId = menuItem.getMenuId();
			if (menuId.equals(RewardsItemConstants.MENU_REWARDSITEM_List)
					|| menuId
							.equals(RewardsListConstants.MENU_REWARDSLIST_SEARCH)
					|| menuId.equals(DepartmentListConstants.MENU_DEPARTMENTLIST_SEARCH)
					|| menuId.equals(GiftListConstants.MENU_GIFTLIST_SEARCH)
					|| menuId.equals(EnterpriseConstants.MENU_ENTERPRISE_EDIT) 
					|| menuId.equals(IntegralManagementConstants.MENU_INTEGRALMANAGEMENT_SEARCH)
					|| menuId.equals(BroadcastingListConstants.MENU_BROADCASTINGLIST_SEARCH)
					|| menuId.equals(OrderViewConstants.MENU_ORDERBOX_SEARCH)
					|| menuId.equals(UserBoxConstants.MENU_USERBOX_SEARCH)
					|| menuId.equals(HrBoxConstants.MENU_HRBOX_SEARCH)) {

				button.setStyleName("menu-link menu-selected");
				breadCrumbsMenu.cleanBreadCrumbsItemTop();
				if (menuId.equals(RewardsItemConstants.MENU_REWARDSITEM_List))
					breadCrumbsMenu.addBreadCrumbsItemTop("奖项", null);
				else if (menuId
						.equals(RewardsListConstants.MENU_REWARDSLIST_SEARCH))
					breadCrumbsMenu.addBreadCrumbsItemTop("奖项应用", null);
				else if (menuId.equals(DepartmentListConstants.MENU_DEPARTMENTLIST_SEARCH))
					breadCrumbsMenu.addBreadCrumbsItemTop("员工数据", null);
				else if (menuId.equals(GiftListConstants.MENU_GIFTLIST_SEARCH))
					breadCrumbsMenu.addBreadCrumbsItemTop("兑换管理", null);
				else if (menuId.equals(EnterpriseConstants.MENU_ENTERPRISE_EDIT))
					breadCrumbsMenu.addBreadCrumbsItemTop("设置", null);
				else if (menuId.equals(LeadTimeConstants.MENU_LEADTIME_SEARCH))
					breadCrumbsMenu.addBreadCrumbsItemTop("积分管理", null);
				else if (menuId.equals(BroadcastingListConstants.MENU_BROADCASTINGLIST_SEARCH))
					breadCrumbsMenu.addBreadCrumbsItemTop("广播", null);
				else if (menuId.equals(OrderViewConstants.MENU_ORDERBOX_SEARCH))
					breadCrumbsMenu.addBreadCrumbsItemTop("收件箱", null);
				else if (menuId.equals(UserBoxConstants.MENU_USERBOX_SEARCH))
					breadCrumbsMenu.addBreadCrumbsItemTop("收件箱", null);
				else if (menuId.equals(HrBoxConstants.MENU_HRBOX_SEARCH))
					breadCrumbsMenu.addBreadCrumbsItemTop("收件箱", null);
				breadCrumbsMenu.addBreadCrumbsItem(menuItem.getTitle(),
						menuItem.getMenuId());
			}

			button.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent paramClickEvent) {
					button.setStyleName("menu-link menu-selected");
					breadCrumbsMenu.cleanBreadCrumbsItem();
					breadCrumbsMenu.addBreadCrumbsItem(menuItem.getTitle(),
							menuItem.getMenuId());
					eventBus.fireEvent(new MenuClickEvent(menuItem));
					for (int i = 0; i < grid.getWidgetCount(); i++) {
						if (grid.getWidget(i) instanceof Anchor) {
							if (!button.getText().equals(
									((Anchor) grid.getWidget(i)).getText())) {
								grid.getWidget(i).setStyleName("menu-link");
							}
						}
					}
				}
			});
			grid.add(button);
			// i++;
		}

		return grid;
	}

	private List<String> getMenuItemName(String keyname) {
		List<String> items = new ArrayList<String>();
		if ("RewardItem".equals(keyname)) {
			items.add(RewardsItemConstants.MENU_REWARDSITEMSTORE_LIST);
			items.add(RewardsItemConstants.MENU_REWARDSITEM_List);
		} else if ("Reward".equals(keyname)) {
			items.add(NominateConstants.MENU_NOMINATE_SEARCH);
			items.add(AwardRewardConstants.MENU_AWARDREWARD_SEARCH);
			items.add(DetailsOfAwardConstants.MENU_DETAILSOFAWARD_SEARCH);
			items.add(RewardsListConstants.MENU_REWARDSLIST_SEARCH);
		} else if ("Staff".equals(keyname)) {
			items.add(DepartmentListConstants.MENU_DEPARTMENTLIST_SEARCH);
			items.add(DepartmentLeaderConstants.MENU_DEPARTMENTLEADER_SEARCH);
			
			//items.add(UserConstants.MENU_USER_SEARCH);
			items.add(StaffListConstants.MENU_STAFFLIST_SEARCH);
			items.add(TeamConstants.MENU_TEAMLIST_SEARCH);
		} else if ("Setting".equals(keyname)) {
			items.add(EnterpriseConstants.MENU_ENTERPRISE_EDIT);
			items.add(EnterpriseConstants.MENU_INTEGRAL_PRICE_EDIT);
			items.add(EnterpriseConstants.MENU_PERIOD_EDIT);
			items.add(LeadTimeConstants.MENU_LEADTIME_SEARCH);
			items.add(EnterpriseConstants.MENU_MAILSET_EDIT);
		} else if ("Gift".equals(keyname)) {
			items.add(GiftListConstants.MENU_GIFTLIST_SEARCH);
			items.add(GiftConstants.MENU_GIFT_ADD);
			items.add(OrderListConstants.MENU_ORDERLIST_SEARCH);
			
		}else if ("Integral".equals(keyname)) {
			items.add(CorpBudgetConstants.MENU_CORPBUDGET_EDIT);
			items.add(EnterpriseConstants.MENU_INTEGRAL_PRICE_EDIT);
			items.add(EnterpriseConstants.MENU_PERIOD_EDIT);
			items.add(IntegralManagementConstants.MENU_INTEGRALMANAGEMENT_SEARCH);
			items.add(CreateBudgetConstants.MENU_CREATE_BUDGET);
		}else if("Broadcasting".equals(keyname))
		{
			items.add(BroadcastingListConstants.MENU_BROADCASTINGLIST_SEARCH);
		}else if ("Box".equals(keyname)) {
			items.add(OrderViewConstants.MENU_ORDERBOX_SEARCH);
			items.add(UserBoxConstants.MENU_USERBOX_SEARCH);
			items.add(HrBoxConstants.MENU_HRBOX_SEARCH);  
		}
		return items;
	}

	@Override
	public void initrender(Panel container, String name) {
		// organize tree
		// sort according to menuID string length
		Collections.sort(items, new Comparator<MenuItem>() {
			public int compare(MenuItem paramT1, MenuItem paramT2) {
				return paramT1.getMenuId().length()
						- paramT2.getMenuId().length();
			}
		});

		// pack into the MenuNode structure
		// for (MenuItem m : items) {
		// // append children recursively
		// root.appendChild(new MenuNode(m));
		// }

		ScrollPanel menuWrapper = new ScrollPanel(createButtonMenuWidget(name));
		container.clear();
		container.add(menuWrapper);

	}

	@Override
	public void changItemColor(String menuName) {
		for (int i = 0; i < grid.getWidgetCount(); i++) {
			if (grid.getWidget(i) instanceof Anchor) {
				System.out.println(((Anchor) grid.getWidget(i)).getText()+"");
				if (!menuName.equals(((Anchor) grid.getWidget(i)).getText())) {
					grid.getWidget(i).setStyleName("menu-link");
				} else {
					grid.getWidget(i).setStyleName("menu-link menu-selected");
				}
			}
		}

	}

}
