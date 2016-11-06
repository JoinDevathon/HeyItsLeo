package org.devathon.contest2016.combat.attacks;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.devathon.contest2016.bot.Bot;
import org.devathon.contest2016.combat.Attack;

public class Arrows implements Attack
{
    private final Bot bot;
    private final World world;
    
    public Arrows(Bot bot)
    {
        this.bot = bot;
        this.world = bot.getWorld();
    }
    
    @Override
    public double chance()
    {
        long withShield = bot.getTargets().stream()
                .filter(e -> e instanceof Player)
                .map(e -> (Player) e)
                .filter(p -> p.getItemOnCursor() != null && p.getItemOnCursor().getType() == Material.SHIELD)
                .count();
        
        if (withShield >= 1)
        {
            return .35d;
        }
        
        return .75d;
    }
    
    @Override
    public void execute()
    {
        for (int i = 0; i < 10; i++)
        {
            world.spawnArrow(bot.getBotLocation(), bot.getBotLocation().toVector().multiply(.5f), 1.2f, .5f);
        }
    }
    
    @Override
    public void tick()
    {
        //
    }
}
