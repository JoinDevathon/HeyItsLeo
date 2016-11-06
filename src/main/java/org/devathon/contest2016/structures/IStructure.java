package org.devathon.contest2016.structures;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

/**
 * Represents a simple structure that can be created.
 */
public interface IStructure
{
    /**
     * Spawn the structure.
     *
     * @param armorStand
     */
    void spawn(ArmorStand armorStand);
    
    /**
     * Remove the structure.
     */
    void remove();
    
    /**
     * Get the structure's location.
     *
     * @return the structure's location
     */
    Location location();
    
    /**
     * Get the structure's armor stand.
     *
     * @return the structure's armor stand
     */
    ArmorStand armorStand();
}
