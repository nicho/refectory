/**
 * 
 */
package com.chinarewards.gwt.elt.client.rewardItem.plugin;


import com.chinarewards.gwt.elt.client.rewardItem.editor.RewardsItemEditor;
import com.chinarewards.gwt.elt.client.rewardItem.editor.RewardsItemEditorDescriptor;
import com.chinarewards.gwt.elt.client.rewardItem.editor.RewardsItemListCompanyOtherEditor;
import com.chinarewards.gwt.elt.client.rewardItem.editor.RewardsItemListCompanyOtherEditorDescriptor;
import com.chinarewards.gwt.elt.client.rewardItem.editor.RewardsItemListEditor;
import com.chinarewards.gwt.elt.client.rewardItem.editor.RewardsItemListEditorDescriptor;
import com.chinarewards.gwt.elt.client.rewardItem.editor.RewardsItemListStaffEditor;
import com.chinarewards.gwt.elt.client.rewardItem.editor.RewardsItemListStaffEditorDescriptor;
import com.chinarewards.gwt.elt.client.rewardItem.editor.RewardsItemStoreListEditor;
import com.chinarewards.gwt.elt.client.rewardItem.editor.RewardsItemStoreListEditorDescriptor;
import com.chinarewards.gwt.elt.client.rewardItem.editor.RewardsItemViewEditor;
import com.chinarewards.gwt.elt.client.rewardItem.editor.RewardsItemViewEditorDescriptor;
import com.chinarewards.gwt.elt.client.rewardItem.editor.RewardsItemViewStaffEditor;
import com.chinarewards.gwt.elt.client.rewardItem.editor.RewardsItemViewStaffEditorDescriptor;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * @author NIcho
 * @since 2011年12月9日 
 */
public class RewardsItemPluginModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(RewardsItemPluginDescriptor.class).in(Singleton.class);
		bind(RewardsItemEditorDescriptor.class).in(Singleton.class);
		bind(RewardsItemEditor.class);
		
		bind(RewardsItemListPluginDescriptor.class).in(Singleton.class);
		bind(RewardsItemListEditorDescriptor.class).in(Singleton.class);
		bind(RewardsItemListEditor.class);
		
		bind(RewardsItemListStaffPluginDescriptor.class).in(Singleton.class);
		bind(RewardsItemListStaffEditorDescriptor.class).in(Singleton.class);
		bind(RewardsItemListStaffEditor.class);
		
		bind(RewardsItemListCompanyOtherPluginDescriptor.class).in(Singleton.class);
		bind(RewardsItemListCompanyOtherEditorDescriptor.class).in(Singleton.class);
		bind(RewardsItemListCompanyOtherEditor.class);
		
		
		bind(RewardsItemViewPluginDescriptor.class).in(Singleton.class);
		bind(RewardsItemViewEditorDescriptor.class).in(Singleton.class);
		bind(RewardsItemViewEditor.class);
		
		bind(RewardsItemViewStaffPluginDescriptor.class).in(Singleton.class);
		bind(RewardsItemViewStaffEditorDescriptor.class).in(Singleton.class);
		bind(RewardsItemViewStaffEditor.class);
		
		bind(RewardsItemStoreListPluginDescriptor.class).in(Singleton.class);
		bind(RewardsItemStoreListEditorDescriptor.class).in(Singleton.class);
		bind(RewardsItemStoreListEditor.class);
	}

}
