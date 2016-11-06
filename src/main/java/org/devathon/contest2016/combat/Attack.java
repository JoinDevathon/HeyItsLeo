package org.devathon.contest2016.combat;

import org.bukkit.entity.Player;

public interface Attack
{
    double chance();
    
    void execute(Player player);
    
    void tick();
}
