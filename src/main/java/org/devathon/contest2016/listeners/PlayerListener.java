package org.devathon.contest2016.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.devathon.contest2016.bot.BotManager;

public class PlayerListener implements Listener
{
    @EventHandler
    public void onLeave(PlayerQuitEvent event)
    {
        if (BotManager.getInstance().hasBot(event.getPlayer()))
            BotManager.getInstance().removeBot(event.getPlayer());
    }
}
