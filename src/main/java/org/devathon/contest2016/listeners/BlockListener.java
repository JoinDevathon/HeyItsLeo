package org.devathon.contest2016.listeners;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.devathon.contest2016.RecipeCreator;
import org.devathon.contest2016.bot.BotManager;

public class BlockListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event)
    {
        ItemStack block = event.getItemInHand();
        
        if (!block.hasItemMeta())
            return;
        
        ItemMeta meta = block.getItemMeta();
        
        if (meta.getDisplayName().equalsIgnoreCase(RecipeCreator.CORE_ITEM_NAME))
        {
            block.setAmount(1);
    
            BotManager.getInstance().create(event.getPlayer());
//            BotStructure.create(event.getPlayer().getLocation());
            ComponentBuilder builder = new ComponentBuilder("[EvilBot-3000] ")
                    .color(ChatColor.GOLD)
                    .append("You should not have awakened me.")
                    .color(ChatColor.RED);
            event.getPlayer().spigot().sendMessage(builder.create());
        }
    }
}
