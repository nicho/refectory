package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 奖励指标
 * 
 * @author yanxin
 * 
 */
public class RewardsTemplateClient implements Serializable,
		Comparable<RewardsClient> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6108321443044249730L;

	private String id;

	private String name;

	private RewardsTypeClient type;

	private String standard;

	private String definition;

	/**
	 * 奖励单位: RMB,BINFEN
	 */
	private String rewardsUnit;

	private String displayRewardsUnit;

	private FrequencyClient frequencyObj;

	private Date createAt;

	private String createBy;

	public RewardsTemplateClient() {

	}

	public RewardsTemplateClient(String id, String name,
			RewardsTypeClient type, String frequency) {
		this(id, name, type, "11", "22", "", "");
	}

	public RewardsTemplateClient(String id, String name,
			RewardsTypeClient type, String standard, String definition,
			String rewardsUnit, String displayRewardsUnit) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.standard = standard;
		this.definition = definition;
		this.rewardsUnit = rewardsUnit;
		this.displayRewardsUnit = displayRewardsUnit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RewardsTypeClient getType() {
		return type;
	}

	public void setType(RewardsTypeClient type) {
		this.type = type;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getRewardsUnit() {
		return rewardsUnit;
	}

	public void setRewardsUnit(String rewardsUnit) {
		this.rewardsUnit = rewardsUnit;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public FrequencyClient getFrequencyObj() {
		return frequencyObj;
	}

	public void setFrequencyObj(FrequencyClient frequencyObj) {
		this.frequencyObj = frequencyObj;
	}

	public String getDisplayRewardsUnit() {
		return displayRewardsUnit;
	}

	public void setDisplayRewardsUnit(String displayRewardsUnit) {
		this.displayRewardsUnit = displayRewardsUnit;
	}

	@Override
	public int compareTo(RewardsClient o) {
		return o == null ? -1 : o.getId().compareTo(id);
	}

}
