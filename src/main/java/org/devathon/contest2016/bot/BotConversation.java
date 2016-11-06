package org.devathon.contest2016.bot;

import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.bot.conversations.BotPrefix;

/**
 * Represents a player's conversation with a bot.
 * This uses the Bukkit Conversation API.
 */
public class BotConversation
{
    private final ConversationFactory factory;
    private Conversation conversation;
    private final Player player;
    
    public BotConversation(Player player)
    {
        this.player = player;
        this.factory = new ConversationFactory(DevathonPlugin.getPlugin())
                .withModality(true)
                .withPrefix(new BotPrefix())
                .thatExcludesNonPlayersWithMessage("You're not a player!");
    }
    
    /**
     * Start the conversation.
     */
    public void start()
    {
        this.conversation = factory.buildConversation(player);
        
        conversation.begin();
    }
    
    /**
     * Stop the conversation.
     */
    public void stop()
    {
        conversation.abandon();
    }
}
