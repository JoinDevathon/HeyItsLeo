package org.devathon.contest2016.bot;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bot extends BukkitRunnable
{
    private final double TARGET_RADIUS = 10D;
    
    private final Player player;
    private final World world;
    private final Location playerLocation;
    
    private final Location botLocation;
    
    private List<LivingEntity> targets;
    private List<Block> blocks;
    private boolean attack;
    
    public Bot(Player player, Location botLocation)
    {
        this.player = player;
        this.world = player.getWorld();
        this.playerLocation = player.getLocation().add(0, 0, 10);
        this.botLocation = botLocation;
        
        this.targets = new ArrayList<>();
        this.blocks = new ArrayList<>();
        this.attack = false;
    }
    
    public void createBlocks()
    {
        Location back = botLocation.subtract(new Vector(0, 0, 3));
        
        getWorld().getBlockAt(back).setType(Material.DIAMOND_BLOCK);
    }
    
    public void destroyBlocks()
    {
        this.blocks.forEach(b -> this.world.getBlockAt(b.getLocation()).setType(Material.AIR));
    }
    
    @Override
    public void run()
    {
        List<LivingEntity> near = botLocation.getWorld().getLivingEntities()
                .stream()
                .filter(e -> e.getLocation().distance(botLocation) <= TARGET_RADIUS)
                .collect(Collectors.toList());
        this.targets.clear();
        this.targets.addAll(near);
        
        if (this.attack)
            attack();
    }
    
    public void start()
    {
        this.attack = true;
    }
    
    private void attack()
    {
        
    }
    
    public Player getPlayer()
    {
        return player;
    }
    
    public World getWorld()
    {
        return world;
    }
    
    public Location getPlayerLocation()
    {
        return playerLocation;
    }
    
    public List<LivingEntity> getTargets()
    {
        return targets;
    }
    
    public Location getBotLocation()
    {
        return botLocation;
    }
}
