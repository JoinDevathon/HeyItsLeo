package org.devathon.contest2016.bot;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BotManager
{
    private static BotManager instance = new BotManager();
    
    private final Map<UUID, Bot> playerBotMap;
    
    private BotManager()
    {
        this.playerBotMap = new HashMap<>();
    }
    
    /**
     * Create a new bot.
     *
     * @param player
     */
    public void create(Player player)
    {
        Bot bot = new Bot(player, BotStructure.create(player.getLocation()));
        
        bot.createBlocks();
        bot.start();
        
        playerBotMap.put(player.getUniqueId(), bot);
    }
    
    public boolean hasBot(Player player)
    {
        return playerBotMap.containsKey(player.getUniqueId());
    }
    
    public void removeBot(Player player)
    {
        Bot bot = playerBotMap.get(player.getUniqueId());
        
        bot.totallyDestroy();
        
        playerBotMap.remove(player.getUniqueId());
    }
    
    /**
     * Get a bot instance by its associated armor stand instance.
     *
     * @param stand
     * @return
     */
    public Bot getByStand(ArmorStand stand)
    {
        return playerBotMap.values().stream()
                .filter(b -> b.getStructure().armorStand() == stand)
                .findFirst()
                .orElse(null);
    }
    
    public Map<UUID, Bot> getPlayerBotMap()
    {
        return playerBotMap;
    }
    
    public static BotManager getInstance()
    {
        return instance;
    }
}
