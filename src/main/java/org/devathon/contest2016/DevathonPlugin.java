package org.devathon.contest2016;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.devathon.contest2016.bot.Bot;
import org.devathon.contest2016.listeners.BlockListener;

import java.util.HashMap;
import java.util.Map;

public class DevathonPlugin extends JavaPlugin {
    private Map<Player, Bot> currentBots;
    private static DevathonPlugin plugin;
    
    @Override
    public void onEnable() {
        plugin = this;
        
        currentBots = new HashMap<>();

        getLogger().info("Loading recipes...");
        
        new RecipeCreator().addRecipes();
        
        getLogger().info("Registering stuff...");
        
        getServer().getPluginManager().registerEvents(new BlockListener(), this);
    }

    @Override
    public void onDisable() {
        // put your disable code here
    }
    
    public static DevathonPlugin getPlugin()
    {
        return plugin;
    }
}

