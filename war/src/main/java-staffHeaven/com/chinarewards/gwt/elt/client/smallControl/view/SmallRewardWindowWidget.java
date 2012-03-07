package com.chinarewards.gwt.elt.client.smallControl.view;

import com.chinarewards.gwt.elt.client.core.Platform;
import com.chinarewards.gwt.elt.client.gloryBroadcast.plugin.GloryBroadcastConstants;
import com.chinarewards.gwt.elt.client.rewardItem.plugin.RewardsItemConstants;
import com.chinarewards.gwt.elt.client.rewards.model.RewardsItemClient;
import com.chinarewards.gwt.elt.util.StringUtil;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SmallRewardWindowWidget extends Composite {

	@UiField
	Anchor rewardName;

	private static GloryBroadcastWidgetUiBinder uiBinder = GWT
			.create(GloryBroadcastWidgetUiBinder.class);

	interface GloryBroadcastWidgetUiBinder extends
			UiBinder<Widget, SmallRewardWindowWidget> {
	}

	public SmallRewardWindowWidget(final String nodeId, String nodeName,
			final String thisAction) {
		initWidget(uiBinder.createAndBindUi(this));
		this.rewardName.setText(nodeName);
		if (nodeId != null) {

			this.rewardName.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					if (!StringUtil.isEmpty(thisAction)) {
						if ("Rewards_STAFF_GETED".equals(thisAction)) {
							// rewardsPage = rewardGridService
							// .fetchRewards_STAFF_GETED(uc, searchVo);
						} else if ("Rewards_ALL".equals(thisAction)) {
							Platform.getInstance()
									.getEditorRegistry()
									.openEditor(
											GloryBroadcastConstants.EDITOR_GLORYBROADCAST_SEARCH,
											"EDITOR_GLORYBROADCAST_SEARCH_DO_ID",
											null);// 光荣榜
						} else if ("RewardsItem_STAFF_RUSH".equals(thisAction)) {
							openRewardsItemView(nodeId);
						} else if ("RewardsItem_ALL".equals(thisAction)) {
							openRewardsItemView(nodeId);
						}
					}

				}
			});
		}
	}

	private void openRewardsItemView(String rewardsItemId) {
		RewardsItemClient client = new RewardsItemClient();
		client.setId(rewardsItemId);
		Platform.getInstance()
				.getEditorRegistry()
				.openEditor(RewardsItemConstants.EDITOR_REWARDSITEM_VIEW_STAFF,
						RewardsItemConstants.EDITOR_REWARDSITEM_VIEW_STAFF,
						client);
	}

}
