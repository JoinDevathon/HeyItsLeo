package org.devathon.contest2016.combat.attacks;

import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.devathon.contest2016.bot.Bot;
import org.devathon.contest2016.combat.Attack;
import org.devathon.contest2016.util.BotUtil;

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
    public void execute(Player player)
    {
        ArmorStand armorStand = bot.getStructure().armorStand();
        BotUtil.updateDirection(player.getLocation(), armorStand);
        
        Fireball fireball = armorStand.launchProjectile(Fireball.class);
        
        fireball.setIsIncendiary(false);
        fireball.setInvulnerable(true);
        fireball.setBounce(true);
        fireball.setCustomName("Magical Ball of Fire");
        fireball.setCustomNameVisible(true);
        fireball.setGlowing(true);
        fireball.setYield(0F);
        fireball.setShooter(armorStand);
    }
    
    @Override
    public void tick()
    {
        // unused
    }
}
