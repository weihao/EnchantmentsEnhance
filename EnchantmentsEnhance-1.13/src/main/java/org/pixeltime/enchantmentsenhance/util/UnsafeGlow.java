/*
 * Copyright (C) 2017-Present HealPot
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.pixeltime.enchantmentsenhance.util;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;

/**
 * Glowing items that were used by this class will CRASH the game if were put
 * into an anvil.
 * <p>
 * Use it as your glower for your icons or at your cautious.
 *
 * @author HealPotion
 * @version Mar 13, 2018
 */
public class UnsafeGlow extends EnchantmentWrapper {

    private static Enchantment glow;


    public UnsafeGlow(int id) {
        super("eeglow");
    }

    public static Enchantment getGlow() {
        if (glow != null)
            return glow;

        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        glow = new UnsafeGlow(255);
        try {
            Enchantment.registerEnchantment(glow);
        } catch (IllegalArgumentException e) {
        }
        return glow;
    }

    public static void addGlow(ItemStack item) {
        Enchantment glow = getGlow();

        item.addEnchantment(glow, 1);
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

}
