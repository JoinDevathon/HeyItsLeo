package org.devathon.contest2016.bot;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.devathon.contest2016.structures.IStructure;

public class BotStructure extends BukkitRunnable implements IStructure
{
    private final Location location;
    private ArmorStand stand;
    
    public BotStructure(ArmorStand stand)
    {
        this.stand = stand;
        this.location = stand.getLocation();
        
        setupStand();
    }
    
    @Override
    public void run()
    {
    }
    
    @Override
    public void spawn(ArmorStand armorStand)
    {
        setupStand();
    }
    
    private void setupStand()
    {
        stand.setGravity(false);
        stand.setBasePlate(false);
        stand.setCollidable(false);
        stand.setArms(true);
        
        stand.setCustomName(ChatColor.RED.toString() + ChatColor.ITALIC.toString() + "EvilBot");
        stand.setCustomNameVisible(true);
        
        stand.setHelmet(new ItemStack(Material.DRAGONS_BREATH));
        stand.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
    }
    
    @Override
    public void remove()
    {
        stand.remove();
    }
    
    @Override
    public Location location()
    {
        return location;
    }
    
    @Override
    public ArmorStand armorStand()
    {
        return stand;
    }
    
    public static BotStructure create(Location loc)
    {
        return new BotStructure((ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND));
    }
}
