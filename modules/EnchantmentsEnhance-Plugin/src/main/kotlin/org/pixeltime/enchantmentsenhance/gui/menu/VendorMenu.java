package org.pixeltime.enchantmentsenhance.gui.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

import java.util.HashMap;
import java.util.Map;

public class VendorMenu extends GUIAbstract {

    private static final long COOLDOWNTIME = 10 * 1000;
    private static Map<String, Long> cooldownMap = new HashMap<>();

    public VendorMenu(Player player) {
        super(player, 54, SettingsManager.lang.getString("vendor.gui"));
        update();
    }

    private static boolean isInCooldown(String playerName) {
        return getCooldown(playerName) > 0;
    }

    private static int getCooldown(String playerName) {
        if (cooldownMap.containsKey(playerName)) {
            if (cooldownMap.get(playerName) + COOLDOWNTIME >= System.currentTimeMillis()) {
                cooldownMap.remove(playerName);
                return 0;
            } else {
                return (int) ((cooldownMap.get(playerName) + COOLDOWNTIME) / 1000 - System.currentTimeMillis() / 1000);
            }
        } else {
            return 0;
        }
    }

    private static void setCoolDown(String playerName) {
        cooldownMap.put(playerName, System.currentTimeMillis());
    }


    @Override
    public void update() {
        getInventory().clear();
        getActions().clear();
        Player player = Bukkit.getPlayer(getPlayerName());
    }
}
