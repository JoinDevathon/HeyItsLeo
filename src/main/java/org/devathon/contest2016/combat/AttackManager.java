package org.devathon.contest2016.combat;

import org.devathon.contest2016.bot.Bot;
import org.devathon.contest2016.combat.attacks.Arrows;

import java.util.ArrayList;
import java.util.List;

public class AttackManager
{
    private static AttackManager instance = new AttackManager();
    
    private List<Attack> attacks;
    
    private AttackManager()
    {
        attacks = new ArrayList<>();
    }
    
    public void setupAttacks(Bot bot)
    {
        attacks.clear();
        attacks.add(new Arrows(bot));
    }
    
    public static AttackManager getInstance()
    {
        return instance;
    }
}
