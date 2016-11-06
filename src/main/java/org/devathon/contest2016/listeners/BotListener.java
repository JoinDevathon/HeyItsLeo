package org.devathon.contest2016.listeners;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.devathon.contest2016.bot.Bot;
import org.devathon.contest2016.bot.BotManager;
import org.devathon.contest2016.util.PluginMessenger;

public class BotListener implements Listener
{
    @EventHandler
    @SuppressWarnings({"deprecation"})
    public void onInteract(EntityDamageByEntityEvent event)
    {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof ArmorStand)
        {
            Player attacker = (Player) event.getDamager();
            ArmorStand armorStand = (ArmorStand) event.getEntity();
            
            Bot bot = BotManager.getInstance().getByStand(armorStand);
            
            event.setCancelled(true);
            event.setDamage(0);
            
            if (bot != null)
            {
                ItemStack item = attacker.getEquipment().getItemInMainHand();
                double damage = 5.5d;
                
                if (item != null)
                {
                    Material type = item.getType();
                    
                    if (type == Material.DIAMOND_SWORD)
                    {
                        damage += 22.5d;
                    } else if (type == Material.GOLD_SWORD)
                    {
                        damage += 15.2d;
                    } else if (type == Material.IRON_SWORD)
                    {
                        damage += 9.5d;
                    } else if (type == Material.STONE_SWORD)
                    {
                        damage += 5d;
                    } else if (type == Material.WOOD_SWORD)
                    {
                        damage += 3.5d;
                    } else if (event.getCause() == DamageCause.PROJECTILE
                            && type == Material.BOW)
                    {
                        // bow damage
                        damage += 10d;
                    }
                }
                
                bot.doDamage(damage, attacker);
                
                if (bot.getHealth() > 0)
                {
                    ComponentBuilder dmg = PluginMessenger.prefix()
                            .append("You attacked the Bot ")
                            .color(ChatColor.RED)
                            .append("and did ")
                            .color(ChatColor.GRAY)
                            .append("" + damage + " Damage")
                            .color(ChatColor.AQUA);
                    attacker.spigot().sendMessage(dmg.create());
                }
            }
        }
    }
}
