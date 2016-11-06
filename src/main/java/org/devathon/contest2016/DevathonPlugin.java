package org.devathon.contest2016;

import org.bukkit.plugin.java.JavaPlugin;
import org.devathon.contest2016.bot.Bot;
import org.devathon.contest2016.bot.BotManager;
import org.devathon.contest2016.listeners.BlockListener;
import org.devathon.contest2016.listeners.BotListener;
import org.devathon.contest2016.listeners.PlayerListener;

public class DevathonPlugin extends JavaPlugin
{
    private static DevathonPlugin plugin;
    
    @Override
    public void onEnable()
    {
        plugin = this;
        
        getLogger().info("Loading recipes...");
        
        new RecipeCreator().addRecipes();
        
        getLogger().info("Registering stuff...");
        
        getServer().getPluginManager().registerEvents(new BlockListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new BotListener(), this);
    }
    
    @Override
    public void onDisable()
    {
        // put your disable code here
        getLogger().info("Removing bots...");
        
        BotManager.getInstance().getPlayerBotMap().values().forEach(Bot::totallyDestroy);
        BotManager.getInstance().getPlayerBotMap().clear();
    }
    
    public static DevathonPlugin getPlugin()
    {
        return plugin;
    }
}

