package com.mousebuttonremapper;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("mousebuttonremapper")
public interface MouseButtonRemapperConfig extends Config
{
    @ConfigItem(
            keyName = "enabled",
            name = "Enabled",
            description = "Enable the Mouse Button Remapper plugin"
    )
    default boolean enabled() { return true; }

    @ConfigItem(
            keyName = "keyForMouse4",
            name = "Key for Mouse 4",
            description = "The key that Mouse 4 maps to in OS"
    )
    default String keyForMouse4() { return "F1"; }

    @ConfigItem(
            keyName = "keyForMouse5",
            name = "Key for Mouse 5",
            description = "The key that Mouse 5 maps to in OS"
    )
    default String keyForMouse5() { return "F2"; }

    @ConfigItem(
            keyName = "remapToKeyForMouse4",
            name = "Remap Mouse 4 to Key",
            description = "The keyboard key to simulate when Mouse 4 is pressed"
    )
    default String remapToKeyForMouse4() { return "SPACE"; }

    @ConfigItem(
            keyName = "remapToKeyForMouse5",
            name = "Remap Mouse 5 to Key",
            description = "The keyboard key to simulate when Mouse 5 is pressed"
    )
    default String remapToKeyForMouse5() { return "ENTER"; }
}
