package com.github.healpot.plugin.enhancement.main.util;

import org.bukkit.Material;

public class Util {
    public static Material[] stoneTypes = new Material[] { Material.GHAST_TEAR,
        Material.GOLD_NUGGET, Material.SUGAR, Material.GLOWSTONE_DUST };


    public static int getSlot(int x, int y) {
        return (y * 9) - (9 - x) - 1;
    }

}
