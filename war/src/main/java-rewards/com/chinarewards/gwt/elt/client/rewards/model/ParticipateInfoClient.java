package com.chinarewards.gwt.elt.client.rewards.model;

import java.io.Serializable;
import java.util.List;

/**
 * 候选人信息
 * 
 * @author yanxin
 * @since 0.2.0 2011-01-22
 */
public class ParticipateInfoClient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5617050206968828827L;

	// 所有人
	public static class EveryoneClient extends ParticipateInfoClient {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5383573462159464340L;

		public EveryoneClient() {

		}

		public EveryoneClient(String corporationId) {
			this.corporationId = corporationId;
		}

		// 企业id
		private String corporationId;

		public String getCorporationId() {
			return corporationId;
		}
	}

	// 选中的员工
	public static class SomeoneClient extends ParticipateInfoClient {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8473335219600753685L;

		public SomeoneClient() {

		}

		public SomeoneClient(List<OrganicationClient> organizations) {
			this.organizations = organizations;
		}

		// 员工或部门
		private List<OrganicationClient> organizations;

		public List<OrganicationClient> getOrganizations() {
			return organizations;
		}

	}
}
