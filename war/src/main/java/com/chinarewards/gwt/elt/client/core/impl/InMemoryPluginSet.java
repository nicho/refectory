package com.chinarewards.gwt.elt.client.core.impl;

import java.util.HashSet;
import java.util.Set;

import com.chinarewards.gwt.elt.client.core.PluginDescriptor;
import com.chinarewards.gwt.elt.client.core.PluginSet;

public class InMemoryPluginSet implements PluginSet {

	Set<PluginDescriptor> plugins = new HashSet<PluginDescriptor>();

	public void registerPlugin(PluginDescriptor plugin) {
		plugins.add(plugin);
	}

	public Set<PluginDescriptor> getPlugins() {
		return plugins;
	}

}
