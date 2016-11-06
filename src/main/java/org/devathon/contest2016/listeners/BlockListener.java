package org.devathon.contest2016.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.devathon.contest2016.RecipeCreator;
import org.devathon.contest2016.bot.BotStructure;

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
            
            new BotStructure(event.getBlock().getLocation(), null);
//            ComponentBuilder builder = new ComponentBuilder("[Bot] ")
//                    .color(ChatColor.GOLD)
//                    .append("The bot is coming soon!")
//                    .color(ChatColor.GRAY)
//                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
//                            new ComponentBuilder("Like, really soon.").color(ChatColor.GREEN).create()));
//            event.getPlayer().spigot().sendMessage(builder.create());
//
//
//
//            event.setCancelled(true);
        }
    }
}
