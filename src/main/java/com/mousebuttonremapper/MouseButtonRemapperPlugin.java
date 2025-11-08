package com.mousebuttonremapper;

import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.input.KeyListener;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;

@PluginDescriptor(
        name = "Mouse Button Remapper",
        description = "Remap configurable keys (including OS-mapped Mouse 4/5) to keyboard presses",
        tags = {"keyboard", "mouse", "remap", "input"}
)
public class MouseButtonRemapperPlugin extends Plugin implements KeyListener
{
    @Inject
    private Client client;

    @Inject
    private KeyManager keyManager;

    @Inject
    private MouseButtonRemapperConfig config;

    private Robot robot;

    @Provides
    MouseButtonRemapperConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(MouseButtonRemapperConfig.class);
    }

    @Override
    protected void startUp() throws Exception
    {
        keyManager.registerKeyListener(this);

        try
        {
            robot = new Robot();
        }
        catch (AWTException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void shutDown() throws Exception
    {
        keyManager.unregisterKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (robot == null || !config.enabled())
        {
            return;
        }

        int keyCode = e.getKeyCode();

        if (keyCode == getKeyCode(config.keyForMouse4()))
        {
            e.consume();
            robot.keyPress(getKeyCode(config.remapToKeyForMouse4()));
            robot.keyRelease(getKeyCode(config.remapToKeyForMouse4()));
        }
        else if (keyCode == getKeyCode(config.keyForMouse5()))
        {
            e.consume();
            robot.keyPress(getKeyCode(config.remapToKeyForMouse5()));
            robot.keyRelease(getKeyCode(config.remapToKeyForMouse5()));
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        // Not used
    }

    private int getKeyCode(String keyName)
    {
        if (keyName == null || keyName.isEmpty())
        {
            return -1;
        }

        keyName = keyName.trim().toUpperCase();

        switch (keyName)
        {
            case "SPACE": return KeyEvent.VK_SPACE;
            case "ESC": case "ESCAPE": return KeyEvent.VK_ESCAPE;
            case "ENTER": return KeyEvent.VK_ENTER;
            case "SHIFT": return KeyEvent.VK_SHIFT;
            case "CTRL": case "CONTROL": return KeyEvent.VK_CONTROL;
            case "ALT": return KeyEvent.VK_ALT;
            case "TAB": return KeyEvent.VK_TAB;
            case "F1": return KeyEvent.VK_F1;
            case "F2": return KeyEvent.VK_F2;
            case "F3": return KeyEvent.VK_F3;
            case "F4": return KeyEvent.VK_F4;
            case "F5": return KeyEvent.VK_F5;
            case "F6": return KeyEvent.VK_F6;
            case "F7": return KeyEvent.VK_F7;
            case "F8": return KeyEvent.VK_F8;
            case "F9": return KeyEvent.VK_F9;
            case "F10": return KeyEvent.VK_F10;
            case "F11": return KeyEvent.VK_F11;
            case "F12": return KeyEvent.VK_F12;
            default:
                if (keyName.length() == 1)
                {
                    return KeyEvent.getExtendedKeyCodeForChar(keyName.charAt(0));
                }
                return -1;
        }
    }
}
