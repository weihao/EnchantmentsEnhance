package com.github.healpot.plugin.enhancement.player;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.bukkit.entity.Player;
import com.github.healpot.plugin.enhancement.main.Main;
import com.github.healpot.plugin.enhancement.main.SettingsManager;

public class Inventory {
    // int[0] = weapon stone, int[1] = armor stone, int[2] = conc weapon; int[3]
    // =conc armor.
    private static Map<Player, int[]> backpack = new HashMap<Player, int[]>();


    public static void loadInventory(Main m, Player player) {
        int[] temp = new int[] { 0, 0, 0, 0 };
        if (SettingsManager.data.contains("backpack." + player.getName())
            || backpack.containsKey(player)) {
            Scanner sc = new Scanner(SettingsManager.data.getString("backpack."
                + player.getName()));
            for (int i = 0; i < temp.length; i++) {
                temp[i] = sc.nextInt();
            }
            sc.close();
            backpack.put(player, temp);
        }
        else {
            backpack.put(player, new int[] { 0, 0, 0, 0 });
        }
    }


    public static void saveInventoryToDisk(Main m, Player player, boolean save) {
        String str = "";
        for (int i = 0; i < 4; i++) {
            str += getLevel(m, i, player) + " ";
        }
        SettingsManager.data.set("backpack." + player.getName(), str);

        if (save) {
            SettingsManager.saveData();
        }

    }


    public static void setLevel(Main m, Player player, int type, int level) {
        backpack.get(player)[type] = level;
    }


    public static void addLevel(Main m, Player player, int type, int levelsToAdd) {
        int newLevel = getLevel(m, type, player) + levelsToAdd;
        setLevel(m, player, type, newLevel);
    }


    public static int getLevel(Main m, int type, Player player) {
        if (backpack.containsKey(player)) {
            return backpack.get(player)[type];
        }
        return 0;
    }



    public static String getOneStoneCountAsString(Main m, Player player, int stoneId) {
        int[] inv = backpack.get(player);
        return (SettingsManager.lang.getString("Item.listing").replaceAll(
            "%ITEM%", SettingsManager.lang.getString("Item." + stoneId))
            .replaceAll("%COUNT%", Integer.toString(inv[stoneId])));

    }
    
    public static int[] getPlayer(Player player)
    {
        return Inventory.backpack.get(player);
    }
}
