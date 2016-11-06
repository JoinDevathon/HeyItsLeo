package org.devathon.contest2016;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class RecipeCreator
{
    public static final String CORE_ITEM_NAME = ChatColor.AQUA.toString() + ChatColor.BOLD + "Bot Core";
    
    /**
     * Register the plugin's recipes.
     */
    public void addRecipes()
    {
        Bukkit.getServer().addRecipe(createBotCore());
    }
    
    private ShapedRecipe createBotCore()
    {
        ItemStack core = new ItemStack(Material.PURPUR_BLOCK, 1);
        ItemMeta meta = core.getItemMeta();
        
        meta.setDisplayName(CORE_ITEM_NAME);
        meta.setLore(Collections.singletonList(ChatColor.GOLD.toString() + ChatColor.ITALIC + "The dangerous bot. Place this and be ready to fight!"));
        
        core.setItemMeta(meta);
        
        return new ShapedRecipe(core)
                .shape(":%:", "%%%", "%:%")
                .setIngredient(':', Material.EMERALD_BLOCK)
                .setIngredient('%', Material.IRON_BLOCK);
    }
}
