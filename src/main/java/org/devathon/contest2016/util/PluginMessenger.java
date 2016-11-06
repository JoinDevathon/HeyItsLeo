package org.devathon.contest2016.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class PluginMessenger
{
    public static final String PREFIX = "EvilBot-3000";
    
    public static ComponentBuilder prefix()
    {
        return new ComponentBuilder("[")
                .color(ChatColor.DARK_GRAY)
                .append(PREFIX)
                .color(ChatColor.GOLD)
                .append("]")
                .color(ChatColor.DARK_GRAY)
                .append(" ")
                .color(ChatColor.WHITE);
    }
    
    public static ComponentBuilder simpleMessage(String msg, ChatColor color)
    {
        return prefix()
                .append(msg)
                .color(color);
    }
}
