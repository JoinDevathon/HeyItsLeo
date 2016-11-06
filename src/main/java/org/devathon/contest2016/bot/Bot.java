package org.devathon.contest2016.bot;

import gnu.trove.map.TObjectDoubleMap;
import gnu.trove.map.hash.TObjectDoubleHashMap;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.devathon.contest2016.DevathonPlugin;
import org.devathon.contest2016.combat.Attack;
import org.devathon.contest2016.combat.attacks.Arrows;
import org.devathon.contest2016.combat.attacks.Fireballs;
import org.devathon.contest2016.util.PluginMessenger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Bot extends BukkitRunnable
{
    private final double TARGET_RADIUS = 10D;
    private final double MAX_HEALTH = 1000D;
    
    private final Player player;
    private final World world;
    private final Location playerLocation;
    
    private final Location botLocation;
    private final BotStructure structure;
    
    private final UUID uniqueId;
    
    private final ArrayList<Attack> attacks;
    
    private List<LivingEntity> targets;
    private List<Block> blocks;
    private boolean attack;
    
    private double health = MAX_HEALTH;
    
    public Bot(Player player, BotStructure structure)
    {
        this.uniqueId = UUID.randomUUID();
        this.player = player;
        this.world = player.getWorld();
        this.playerLocation = player.getLocation().add(0, 0, 10);
        this.botLocation = structure.location();
        this.structure = structure;
        this.attack = false;
        
        this.targets = new ArrayList<>();
        this.blocks = new ArrayList<>();
        this.attacks = new ArrayList<>(Arrays.asList(
                new Arrows(this),
                new Fireballs(this)
        ));
    }
    
    public void createBlocks()
    {
        
    }
    
    public void destroyBlocks()
    {
        this.blocks.forEach(b -> this.world.getBlockAt(b.getLocation()).setType(Material.AIR));
    }
    
    public void totallyDestroy()
    {
        cancel();
        
        this.attack = false;
        this.targets = new ArrayList<>();
        
        destroyBlocks();
        structure.remove();
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
    }
    
    public void start()
    {
        this.attack = true;
        
        runTaskTimer(DevathonPlugin.getPlugin(), 10L, 10L);
        
        attack(player);
    }
    
    private void attack(Player player)
    {
        attacks.forEach(Attack::tick);
        
        TObjectDoubleMap<Attack> byChance = new TObjectDoubleHashMap<>();
        
        for (Attack attack : attacks)
        {
            double chance = attack.chance();
            
            if (chance > 0)
                byChance.put(attack, chance);
        }
        
        Attack attack = selectBestAttack(byChance);
        
        if (attack != null)
            attack.execute(player);
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
    
    public double getHealth()
    {
        return health;
    }
    
    public UUID getUniqueId()
    {
        return uniqueId;
    }
    
    private void die()
    {
        this.attack = false;
        
        EnderDragon dragon = world.spawn(botLocation, EnderDragon.class);
        
        dragon.setPassenger(structure.armorStand());
        dragon.setHealth(0);
//        dragon.setGlowing(true);
        dragon.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999, 10, false, false));
        
        dragon.playEffect(EntityEffect.DEATH);
        
        ComponentBuilder msg = PluginMessenger.prefix()
                .append("I'll get you next time...")
                .color(ChatColor.RED);
        player.spigot().sendMessage(msg.create());
        
        this.totallyDestroy();
    }
    
    public void doDamage(double damage, Player player)
    {
        this.health -= damage;
        
        if (this.health <= 0)
        {
            die();
        } else
        {
            attack(player);
        }
        
        structure.armorStand().setCustomName(
                BotStructure.BOT_NAME +
                        ChatColor.WHITE +
                        " [" + ChatColor.AQUA.toString() +
                        health +
                        ChatColor.GRAY +
                        "/" +
                        ChatColor.GREEN.toString() + MAX_HEALTH
                        + ChatColor.WHITE + "]"
        );
    }
    
    public BotStructure getStructure()
    {
        return structure;
    }
    
    private Attack selectBestAttack(TObjectDoubleMap<Attack> map)
    {
        if (map.size() > 0)
        {
            double total = Arrays.stream(map.values())
                    .sum();
            double target = Math.random() * total;
            
            for (Attack attack : map.keySet())
            {
                target -= map.get(attack);
                
                if (target <= 0)
                {
                    return attack;
                }
            }
        }
        
        return null;
    }
}
