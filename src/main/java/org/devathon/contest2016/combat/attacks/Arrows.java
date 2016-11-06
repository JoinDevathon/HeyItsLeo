package org.devathon.contest2016.combat.attacks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.bot.Bot;
import org.devathon.contest2016.combat.Attack;
import org.devathon.contest2016.util.BotUtil;

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
                .filter(p -> p.getEquipment().getItemInMainHand() != null && p.getEquipment().getItemInMainHand().getType() == Material.SHIELD)
                .count();
        
        if (withShield >= 1)
        {
            return .35d;
        }
        
        return .75d;
    }
    
    @Override
    public void execute(Player player)
    {
        ArmorStand armorStand = bot.getStructure().armorStand();
        BotUtil.updateDirection(player.getLocation(), armorStand);
        
        Arrow fireball = armorStand.launchProjectile(Arrow.class);
    
        fireball.setInvulnerable(true);
        fireball.setBounce(true);
        fireball.setCustomName("Magical Arrow");
        fireball.setCustomNameVisible(true);
        fireball.setGlowing(true);
        fireball.setShooter(armorStand);
    
        Bukkit.getServer().getScheduler().runTaskLater(DevathonPlugin.getPlugin(), fireball::remove, 25L);
    }
    
    @Override
    public void tick()
    {
        //
    }
}
