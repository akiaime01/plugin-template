package com.example;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PluginTemplate
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(com.plugintemplate.PluginTemplate.class);
		RuneLite.main(args);
	}
}