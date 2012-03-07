/**
 * 
 */
package com.chinarewards.gwt.elt.client.gift.editor;

import com.chinarewards.gwt.elt.client.core.ui.Editor;
import com.chinarewards.gwt.elt.client.core.ui.EditorDescriptor;
import com.chinarewards.gwt.elt.client.gift.editor.GiftViewEditor;
import com.chinarewards.gwt.elt.client.gift.model.GiftClient;
import com.chinarewards.gwt.elt.client.gift.plugin.GiftConstants;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author yanrui
 * @since 2012年1月16日 
 */
public class GiftViewEditorDescriptor implements EditorDescriptor {

	final Provider<GiftViewEditor> editProvider;

	@Inject
	GiftViewEditorDescriptor(Provider<GiftViewEditor> editProvider) {
		this.editProvider = editProvider;
	}

	@Override
	public String getEditorId() {
		return GiftConstants.EDITOR_GIFT_VIEW;
	}

	@Override
	public Editor createEditor(String instanceId, Object model) {
		GiftViewEditor e = editProvider.get();
		e.setInstanceId(instanceId);
		
		String name = ((GiftClient) model).getName();
		String subName = name.length() > 6 ? name.substring(0, 6) : name;
		String title =subName+ "-详细" ;
		e.setTitle(title);
	
		e.setModel(instanceId,model);
		return e;
	}

}
