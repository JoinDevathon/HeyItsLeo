package org.devathon.contest2016.combat.attacks;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LargeFireball;
import org.devathon.contest2016.bot.Bot;
import org.devathon.contest2016.combat.Attack;

public class Fireballs implements Attack
{
    private final Bot bot;
    private final World world;
    
    public Fireballs(Bot bot)
    {
        this.bot = bot;
        this.world = bot.getWorld();
    }
    
    @Override
    public double chance()
    {
        return .75f;
    }
    
    @Override
    public void execute()
    {
        Location location = bot.getBotLocation().toVector()
                .add(bot.getBotLocation().toVector().multiply(2))
                .toLocation(world);
        for (int i = 0; i < 3; i++)
        {
            world.spawn(location, LargeFireball.class);
        }
    }
    
    @Override
    public void tick()
    {
        // unused
    }
}
