package com.chinarewards.gwt.elt.model.rewards;

import java.io.Serializable;

public class RewardsPageClient implements Serializable,
		Comparable<RewardsPageClient> {
	// id
	private String id;
	private String titleName;
	private RewardPageType pageType;

	public RewardPageType getPageType() {
		return pageType;
	}

	public void setPageType(RewardPageType pageType) {
		this.pageType = pageType;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compareTo(RewardsPageClient o) {
		return o == null ? -1 : o.getId().compareTo(this.getId());
	}

}
