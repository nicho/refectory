/**
 * 
 */
package com.chinarewards.gwt.elt.client.view;

import com.chinarewards.gwt.elt.client.rewards.model.OrganicationClient;
import com.chinarewards.gwt.elt.client.widget.SpecialTextArea;
import com.google.gwt.view.client.ProvidesKey;

/**
 * @author Cream
 * @since 0.2.0
 */
public class OrganizationSpecialTextArea extends
		SpecialTextArea<OrganicationClient> {

	private static ProvidesKey<OrganicationClient> keyProvider = new ProvidesKey<OrganicationClient>() {
		@Override
		public Object getKey(OrganicationClient item) {
			return item.getId();
		}
	};

	public OrganizationSpecialTextArea() {
		super.setKeyProvider(keyProvider);
	}

	@Override
	protected void render(OrganicationClient value, StringBuffer sb) {
		sb.append(value.getName());
	}

}
