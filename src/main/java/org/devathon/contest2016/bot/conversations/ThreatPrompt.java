package org.devathon.contest2016.bot.conversations;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.FixedSetPrompt;
import org.bukkit.conversations.Prompt;
import org.devathon.contest2016.bot.Bot;

public class ThreatPrompt extends FixedSetPrompt
{
    private Bot bot;
    
    public ThreatPrompt(Bot bot)
    {
        super("Yes", "No", "Heck no!", "I'm not sure. Ask again?");
        
        this.bot = bot;
    }
    
    @Override
    protected Prompt acceptValidatedInput(ConversationContext conversationContext, String s)
    {
        if (!s.equalsIgnoreCase("Yes") && !s.equalsIgnoreCase("No"))
        {
            conversationContext.getForWhom().sendRawMessage(ChatColor.GOLD + "Fine then...");
            
            return END_OF_CONVERSATION;
        }
        
        if (s.equalsIgnoreCase("Yes"))
        {
            conversationContext.getForWhom().sendRawMessage(ChatColor.GOLD + "Haha, let's get right into this...");
       
            
        } else if (s.equalsIgnoreCase("No"))
        {
            conversationContext.getForWhom().sendRawMessage(ChatColor.GOLD + "Looks like I'll have to destroy you.");
            
            return END_OF_CONVERSATION;
        }
        
        return END_OF_CONVERSATION;
    }
    
    @Override
    public String getPromptText(ConversationContext conversationContext)
    {
        return null;
    }
}
