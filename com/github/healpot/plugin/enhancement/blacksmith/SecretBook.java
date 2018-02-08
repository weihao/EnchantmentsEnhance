package com.github.healpot.plugin.enhancement.blacksmith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.bukkit.entity.Player;
import com.github.healpot.plugin.enhancement.failstack.Failstack;
import com.github.healpot.plugin.enhancement.main.SettingsManager;
import com.github.healpot.plugin.enhancement.main.util.Util;

public class SecretBook {
    private static HashMap<Player, List<Integer>> storage =
        new HashMap<Player, List<Integer>>();


    public static void addFailstackToStorage(Player player) {
        Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
            + SettingsManager.lang.getString("Save.createFailstack").replaceAll(
                "%failstack%", Integer.toString(Failstack.getLevel(
                    player))), player);
        storage.get(player).add(Failstack.getLevel(player));
        Failstack.resetLevel(player);
    }


    public static void loadStorage(Player player) {
        List<Integer> adviceOfValks = new ArrayList<Integer>();
        if (SettingsManager.data.contains("AdviceOfValks." + player.getName())
            || storage.containsKey(player)) {
            Scanner sc = new Scanner(SettingsManager.data.getString(
                "AdviceOfValks." + player.getName()));
            while (sc.hasNext()) {
                adviceOfValks.add(sc.nextInt());
            }
            sc.close();
        }
        storage.put(player, adviceOfValks);
    }


    public static void saveStorageToDisk(Player player, boolean save) {
        List<Integer> working = storage.get(player);
        String result = "";
        if (working.size() > 0) {
            for (int i = 0; i < working.size(); i++) {
                result += storage.get(player).get(i) + " ";
            }
            SettingsManager.data.set("AdviceOfValks." + player.getName(),
                result);
        }

        if (save)
            SettingsManager.saveData();
    }


    public static void list(Player player, int pageNumber) {
        List<Integer> adviceOfValks = storage.get(player);

        if (adviceOfValks.size() <= 0 || adviceOfValks == null) {
            Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
                + SettingsManager.lang.getString("Save.noFailstack"), player);
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

        Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
            + SettingsManager.lang.getString("Save.failstackTitle").replaceAll(
                "%page%", Integer.toString(page)), player);
        for (Integer fs : adviceOfValks) {
            count++;
            Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
                + SettingsManager.lang.getString("Save.listing").replaceAll(
                    "%NUMBER%", Integer.toString(count)).replaceAll(
                        "%FAILSTACK%", Integer.toString(fs)), player);

        }

    }


    public static void select(Player player, int selectedFailstack) {
        if (selectedFailstack > 0 && Failstack.getLevel(player) == 0) {
            try {
                Failstack.addLevel(player, SecretBook.storage.get(player)
                    .get(selectedFailstack - 1));
                SecretBook.storage.get(player).remove(selectedFailstack - 1);
            }
            catch (Exception e) {
                Util.sendMessage(SettingsManager.lang.getString(
                    "Config.pluginTag") + SettingsManager.lang.getString(
                        "Valks.noAdvice"), player);
            }
        }
        else
            Util.sendMessage(SettingsManager.lang.getString("Valks.noAdvicce")
                + SettingsManager.lang.getString("Valks.hasFailstack"), player);
    }
}
