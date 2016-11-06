package org.devathon.contest2016.util;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

public class BotUtil
{
    /**
     * Credit to OscarWoHA for sharing his code with me
     *
     * @param loc
     * @param stand
     */
    public static void updateDirection(Location loc, ArmorStand stand)
    {
        Vector from = new Vector(
                stand.getLocation().getX(),
                stand.getLocation().getY(),
                stand.getLocation().getZ()
        );
        
        Vector to = new Vector(
                loc.getX(),
                loc.getY(),
                loc.getZ()
        );
        
        Vector vec = to.subtract(from);
        Location newLoc = stand.getLocation();
        
        newLoc.setDirection(vec);
        
        stand.teleport(newLoc);
    }
}
