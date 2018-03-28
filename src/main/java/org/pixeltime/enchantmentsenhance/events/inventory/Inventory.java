package org.pixeltime.enchantmentsenhance.events.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    // int[0] = weapon stone, int[1] = armor stone, int[2] = conc weapon; int[3]
    // =conc armor.
    private static Map<String, int[]> backpack = new HashMap<String, int[]>();


    public static void loadInventory(Player player) {
        if (SettingsManager.data.contains(player.getName() + ".backpack")
            || backpack.containsKey(player.getDisplayName())) {
            String[] temp = SettingsManager.data.getString(player.getName()
                + ".backpack").replace("[", "").replace("]", "").split(", ");
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

            backpack.put(player.getDisplayName(), inventory);
        }
        else {
            backpack.put(player.getDisplayName(), new int[] { 0, 0, 0, 0 });
        }
    }


    public static void saveInventoryToDisk(Player player, boolean save) {
        String str = Arrays.toString(backpack.get(player.getDisplayName()));
        SettingsManager.data.set(player.getName() + ".backpack", str);

        if (save) {
            SettingsManager.saveData();
        }

    }


    public static void setLevel(Player player, int type, int level) {
        try {
            backpack.get(player.getDisplayName())[type] = level;
        }
        catch (Exception e) {
            Main.getMain().getLogger().info(
                "Error when setting the player data.");
        }
    }


    public static void addLevel(Player player, int type, int levelsToAdd) {
        int newLevel = getLevel(type, player) + levelsToAdd;
        setLevel(player, type, newLevel);
    }


    public static int getLevel(int type, Player player) {
        if (backpack.containsKey(player.getDisplayName())) {
            return backpack.get(player.getDisplayName())[type];
        }
        return 0;
    }


    public static int[] getPlayer(Player player) {
        return backpack.get(player.getDisplayName());
    }
}
