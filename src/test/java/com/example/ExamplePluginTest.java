package com.example;

import com.mousebuttonremapper.MouseButtonRemapperPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ExamplePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(MouseButtonRemapperPlugin.class);
		RuneLite.main(args);
	}
}