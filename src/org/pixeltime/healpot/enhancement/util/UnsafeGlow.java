package org.pixeltime.healpot.enhancement.util;

import java.lang.reflect.Field;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;

/**
 * Glowing items that were used by this class will CRASH the game if were put
 * into an anvil.
 * 
 * Use it as your glower for your icons or at your cautious.
 * 
 * @author HealPot
 * @version Mar 13, 2018
 *
 */
public class UnsafeGlow extends EnchantmentWrapper {

    private static Enchantment glow;


    public UnsafeGlow(int id) {
        super(id);
    }


    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }


    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }


    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }


    @Override
    public int getMaxLevel() {
        return 10;
    }


    @Override
    public String getName() {
        return "Glow";
    }


    @Override
    public int getStartLevel() {
        return 1;
    }


    public static Enchantment getGlow() {
        if (glow != null)
            return glow;

        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        glow = new UnsafeGlow(255);
        try {
            Enchantment.registerEnchantment(glow);
        }
        catch (IllegalArgumentException e) {
        }
        return glow;
    }


    public static void addGlow(ItemStack item) {
        Enchantment glow = getGlow();

        item.addEnchantment(glow, 1);
    }

}
