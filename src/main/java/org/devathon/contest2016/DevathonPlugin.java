package org.devathon.contest2016;

import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.devathon.contest2016.bot.Bot;
import org.devathon.contest2016.bot.BotManager;
import org.devathon.contest2016.bot.BotStructure;
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
        
        for (World world : getServer().getWorlds())
        {
            world.getEntitiesByClass(ArmorStand.class).stream()
                    .filter(armorStand -> BotManager.getInstance().getByStand(armorStand) == null
                            && armorStand.getCustomName() != null
                            && armorStand.getCustomName().startsWith(BotStructure.BOT_NAME))
                    .forEach(Entity::remove);
        }
        
        BotManager.getInstance().getPlayerBotMap().values().forEach(Bot::totallyDestroy);
        BotManager.getInstance().getPlayerBotMap().clear();
        
    }
    
    public static DevathonPlugin getPlugin()
    {
        return plugin;
    }
}

