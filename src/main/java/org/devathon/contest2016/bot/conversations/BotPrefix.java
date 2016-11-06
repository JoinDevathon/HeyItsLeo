package org.devathon.contest2016.bot.conversations;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationPrefix;

public class BotPrefix implements ConversationPrefix
{
    @Override
    public String getPrefix(ConversationContext conversationContext)
    {
        return ChatColor.GOLD + "[EVIL-BOT 3000]: " + ChatColor.WHITE;
    }
}
