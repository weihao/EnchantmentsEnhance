package org.pixeltime.healpot.enhancement.events.blacksmith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.pixeltime.healpot.enhancement.events.blackspirit.Failstack;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.Util;

public class SecretBook {
    private static HashMap<String, List<Integer>> storage =
        new HashMap<String, List<Integer>>();


    public static void addFailstackToStorage(Player player) {
        Util.sendMessage(SettingsManager.lang.getString("Save.createFailstack")
            .replaceAll("%failstack%", Integer.toString(Failstack.getLevel(
                player))), player);
        storage.get(player.getDisplayName()).add(Failstack.getLevel(player));
        Failstack.resetLevel(player);
    }


    public static void loadStorage(Player player) {
        List<Integer> adviceOfValks = new ArrayList<Integer>();
        if (SettingsManager.data.contains(player.getName() + ".advice of valks")
            || storage.containsKey(player.getDisplayName())) {
            String[] temp = SettingsManager.data.getString(player.getName()
                + ".advice of valks").replace("[", "").replace("]", "").split(
                    ", ");
            for (int i = 0; i < temp.length; i++) {
                try {
                    adviceOfValks.add(Integer.parseInt(temp[i]));
                }
                catch (Exception e) {
                    Bukkit.getLogger().severe("Error in loading " + player
                        .getDisplayName() + "'s" + " failstack.");
                }
            }
        }
        storage.put(player.getDisplayName(), adviceOfValks);
    }


    public static void saveStorageToDisk(Player player, boolean save) {
        List<Integer> temp = storage.get(player.getDisplayName());
        SettingsManager.data.set(player.getName() + ".advice of valks", temp
            .toString());
        if (save)
            SettingsManager.saveData();
    }


    /**
     * Displays the current list of the advices.
     * 
     * @param player
     * @param pageNumber
     */
    public static void list(Player player, int pageNumber) {
        List<Integer> adviceOfValks = storage.get(player.getDisplayName());

        if (adviceOfValks.size() <= 0 || adviceOfValks == null) {
            Util.sendMessage(SettingsManager.lang.getString("Save.noFailstack"),
                player);
            return;
        }

        int page = 1;
        if (pageNumber > 1) {
            try {
                page = pageNumber;
            }
            catch (Exception e) {
            }
            if (pageNumber <= 0) {
                page = 1;
            }
        }

        int count = 0;

        Util.sendMessage(SettingsManager.lang.getString("Save.failstackTitle")
            .replaceAll("%page%", Integer.toString(page)), player);
        for (Integer fs : adviceOfValks) {
            count++;
            Util.sendMessage(SettingsManager.lang.getString("Save.listing")
                .replaceAll("%NUMBER%", Integer.toString(count)).replaceAll(
                    "%FAILSTACK%", Integer.toString(fs)), player);

        }

    }


    /**
     * Uses an advice from the list.
     * 
     * @param player
     * @param selectedFailstack
     */
    public static void select(Player player, int selectedFailstack) {
        if (selectedFailstack > 0) {
            if (selectedFailstack <= SecretBook.storage.get(player
                .getDisplayName()).size()) {
                if (Failstack.getLevel(player) == 0) {
                    try {
                        int levelOfAdvice = SecretBook.storage.get(player
                            .getDisplayName()).get(selectedFailstack - 1);
                        Failstack.addLevel(player, levelOfAdvice);
                        SecretBook.storage.get(player.getDisplayName()).remove(
                            selectedFailstack - 1);
                        Util.sendMessage(SettingsManager.lang.getString(
                            "Valks.used").replaceAll("%LEVEL%", Integer
                                .toString(levelOfAdvice)), player);
                    }
                    catch (Exception e) {
                        Util.sendMessage(SettingsManager.lang.getString(
                            "Valks.noAdvice"), player);
                    }
                }

                else {
                    Util.sendMessage(SettingsManager.lang.getString(
                        "Valks.hasFailstack"), player);
                }
            }
            else {
                Util.sendMessage(SettingsManager.lang.getString(
                    "Config.invalidNumber"), player);
            }
        }
        else
            Util.sendMessage(SettingsManager.lang.getString(
                "Config.invalidNumber"), player);
    }
}
