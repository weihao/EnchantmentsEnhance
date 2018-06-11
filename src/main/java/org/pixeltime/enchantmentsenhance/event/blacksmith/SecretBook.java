/*
 *     Copyright (C) 2017-Present HealPotion
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.pixeltime.enchantmentsenhance.event.blacksmith;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Failstack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SecretBook {
    private static HashMap<String, List<Integer>> storage =
            new HashMap<String, List<Integer>>();

    public static HashMap<String, List<Integer>> getStorage() {
        return storage;
    }

    /**
     * Adds a player's failstack to the HashMap storage.
     *
     * @param player Targeted player.
     */
    public static void addFailstackToStorage(Player player) {
        List<Integer> temp = storage.get(player.getName());
        int level = Failstack.getLevel(player);
        if (level != 0) {
            temp.add(level);
            Collections.sort(temp);
            Failstack.resetLevel(player);
            Util.sendMessage(SettingsManager.lang.getString("Save.createFailstack")
                    .replaceAll("%failstack%", Integer.toString(Failstack.getLevel(
                            player))), player);
        }
    }


    /**
     * Loads the storage of a player.
     *
     * @param player Targeted player.
     */
    public static void loadStorage(Player player) {
        List<Integer> adviceOfValks = new ArrayList<Integer>();
        if (SettingsManager.data.contains(player.getName() + ".advice of valks")
                || storage.containsKey(player.getName())) {
            String[] temp = SettingsManager.data.getString(player.getName()
                    + ".advice of valks").replace("[", "").replace("]", "").split(
                    ", ");
            if (temp.length > 1) {
                for (int i = 0; i < temp.length; i++) {
                    try {
                        adviceOfValks.add(Integer.parseInt(temp[i]));
                    } catch (Exception e) {
                        Bukkit.getLogger().severe("Error in loading " + player
                                .getName() + "'s" + " failstack.");
                    }
                }
            }
        }
        storage.put(player.getName(), adviceOfValks);
    }


    /**
     * Writes data to the disk.
     *
     * @param player Targeted player.
     * @param save   If true, data will be written in the disk. If false, data will
     *               be stored in the memory.
     */
    public static void saveStorageToDisk(Player player, boolean save) {
        List<Integer> temp = storage.get(player.getName());
        SettingsManager.data.set(player.getName() + ".advice of valks", temp
                .toString());
        if (save) {
            SettingsManager.saveData();
        }
    }


    /**
     * Displays the current list of the advices.
     *
     * @param player     Targeted player.
     * @param pageNumber Selected page number.
     */
    public static void list(Player player, int pageNumber) {
        List<Integer> adviceOfValks = storage.get(player.getName());

        if (adviceOfValks.size() <= 0 || adviceOfValks == null) {
            Util.sendMessage(SettingsManager.lang.getString("Save.noFailstack"),
                    player);
            return;
        }

        int page = 1;
        if (pageNumber > 1) {
            try {
                page = pageNumber;
            } catch (Exception e) {
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
     * @param player            Targeted player.
     * @param selectedFailstack Selected failstack index.
     */
    public static void select(Player player, int selectedFailstack) {
        if (selectedFailstack > 0) {
            if (selectedFailstack <= SecretBook.storage.get(player
                    .getName()).size()) {
                if (Failstack.getLevel(player) == 0) {
                    try {
                        int levelOfAdvice = SecretBook.storage.get(player
                                .getName()).get(selectedFailstack - 1);
                        Failstack.addLevel(player, levelOfAdvice);
                        SecretBook.storage.get(player.getName()).remove(
                                selectedFailstack - 1);
                        Util.sendMessage(SettingsManager.lang.getString(
                                "Valks.used").replaceAll("%LEVEL%", Integer
                                .toString(levelOfAdvice)), player);
                    } catch (Exception e) {
                        Util.sendMessage(SettingsManager.lang.getString(
                                "Valks.noAdvice"), player);
                    }
                } else {
                    Util.sendMessage(SettingsManager.lang.getString(
                            "Valks.hasFailstack"), player);
                }
            } else {
                if (SecretBook.storage.get(player.getName())
                        .size() == 0) {
                    Util.sendMessage(SettingsManager.lang.getString(
                            "Valks.noAdvice"), player);
                } else {
                    Util.sendMessage(SettingsManager.lang.getString(
                            "Config.invalidNumber"), player);
                }
            }
        } else
            Util.sendMessage(SettingsManager.lang.getString(
                    "Config.invalidNumber"), player);
    }

}
