package org.devathon.contest2016.bot;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bot implements Runnable
{
    private final double TARGET_RADIUS = 10D;
    
    private final Player player;
    private final World world;
    private final Location location;
    
    private List<LivingEntity> targets;
    
    public Bot(Player player)
    {
        this.player = player;
        this.world = player.getWorld();
        this.location = player.getLocation().add(0, 0, 10);
        
        this.targets = new ArrayList<>();
    }
    
    @Override
    public void run()
    {
        List<LivingEntity> near = location.getWorld().getLivingEntities()
                .stream()
                .filter(e -> e.getLocation().distance(location) <= TARGET_RADIUS)
                .collect(Collectors.toList());
        this.targets.clear();
        this.targets.addAll(near);
    }
    
    public Player getPlayer()
    {
        return player;
    }
    
    public World getWorld()
    {
        return world;
    }
    
    public Location getLocation()
    {
        return location;
    }
    
    public List<LivingEntity> getTargets()
    {
        return targets;
    }
}
