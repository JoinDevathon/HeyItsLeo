package org.devathon.contest2016.bot;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;
import org.devathon.contest2016.structures.IStructure;

public class BotStructure extends BukkitRunnable implements IStructure
{
    private final Location location;
    private ArmorStand stand;
    
    public BotStructure(Location location, ArmorStand stand)
    {
        this.location = location;
        this.stand = stand;
        
        spawn(stand);
    }
    
    @Override
    public void run()
    {
        stand.teleport(location.clone().add(.5, -.7D, .5));
    }
    
    @Override
    public void spawn(ArmorStand armorStand)
    {
        
    }
    
    @Override
    public void remove()
    {
        //
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
}
