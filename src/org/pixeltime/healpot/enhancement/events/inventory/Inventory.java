package org.pixeltime.healpot.enhancement.events.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    // int[0] = weapon stone, int[1] = armor stone, int[2] = conc weapon; int[3]
    // =conc armor.
    private static Map<Player, int[]> backpack = new HashMap<Player, int[]>();


    public static void loadInventory(Player player) {
        if (SettingsManager.data.contains(player.getName() + ".backpack")
            || backpack.containsKey(player)) {
            String[] temp = SettingsManager.data.getString(player.getName()
                + ".backpack").replace("[", "").replace("]", "").split(
                    ", ");
            int[] inventory = new int[temp.length];
            for (int i = 0; i < temp.length; i++) {
                try {
                    inventory[i] = Integer.parseInt(temp[i]);
                }
                catch (Exception e) {
                    Bukkit.getLogger().severe("Error in loading " + player
                        .getDisplayName() + "'s" + " inventory.");
                }
            }

            backpack.put(player, inventory);
        }
        else {
            backpack.put(player, new int[] { 0, 0, 0, 0 });
        }
    }


    public static void saveInventoryToDisk(Player player, boolean save) {
        String str = Arrays.toString(backpack.get(player));
        SettingsManager.data.set(player.getName() + ".backpack", str);

        if (save) {
            SettingsManager.saveData();
        }

    }


    public static void setLevel(Player player, int type, int level) {
        backpack.get(player)[type] = level;
    }


    public static void addLevel(Player player, int type, int levelsToAdd) {
        int newLevel = getLevel(type, player) + levelsToAdd;
        setLevel(player, type, newLevel);
    }


    public static int getLevel(int type, Player player) {
        if (backpack.containsKey(player)) {
            return backpack.get(player)[type];
        }
        return 0;
    }


    public static int[] getPlayer(Player player) {
        return backpack.get(player);
    }
}
