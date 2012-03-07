package com.chinarewards.gwt.elt.client.ui;


import com.chinarewards.gwt.elt.client.rewards.model.StaffClient;
import com.chinarewards.gwt.elt.client.widget.SpecialTextArea;
import com.google.gwt.view.client.ProvidesKey;

/**
 * @author Cream
 * @since 0.2.0
 */
public class StaffSpecialTextArea extends SpecialTextArea<StaffClient> {

	private static ProvidesKey<StaffClient> keyProvider = new ProvidesKey<StaffClient>() {
		@Override
		public Object getKey(StaffClient item) {
			return item.getId();
		}
	};

	public StaffSpecialTextArea() {
		super.setKeyProvider(keyProvider);
	}

	@Override
	protected void render(StaffClient value, StringBuffer sb) {
		sb.append(value.getName());
	}

}
