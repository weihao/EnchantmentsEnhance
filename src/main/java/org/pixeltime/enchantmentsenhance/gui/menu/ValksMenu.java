package org.pixeltime.enchantmentsenhance.gui.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.api.API;
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.mysql.PlayerStat;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.List;

public class ValksMenu extends GUIAbstract {

    public ValksMenu(Player player) {
        super(player, 54, SettingsManager.lang.getString("Valks.gui"));
        update();
    }

    @Override
    public void update() {
        getInventory().clear();
        getActions().clear();

        Player player = Bukkit.getPlayer(playerName);
        List<Integer> inv = PlayerStat.getPlayerStats(playerName).getValks();
        for (int i = 0; i < (inv.size() > 54 ? 54 : inv.size()); i++) {
            int level = inv.get(i);
            final int index = i;
            setItem(Util.getSlot((i % 9) + 1, (i / 9) + 1), CompatibilityManager.glow.addGlow(new ItemBuilder(Material.BOOK, level).setName(
                    SettingsManager.lang.getString("Item.valks") + "+" + level).toItemStack()), () -> new BukkitRunnable() {
                @Override
                public void run() {
                    if (level > 0) {
                        if (API.getFailstack(player.getName()) == 0) {
                            API.addFailstack(player.getName(), level);
                            PlayerStat.getPlayerStats(playerName).getValks().remove(index);
                            Util.sendMessage(SettingsManager.lang.getString(
                                    "Valks.used").replaceAll("%LEVEL%", Integer
                                    .toString(level)), player);
                            player.closeInventory();
                            new MainMenu(player).open();
                        } else {
                            Util.sendMessage(SettingsManager.lang.getString(
                                    "Valks.hasFailstack"), player);
                        }
                    } else {
                        Util.sendMessage(SettingsManager.lang.getString(
                                "Config.invalidNumber"), player);
                    }
                }
            }.runTaskLaterAsynchronously(Main.getMain(), 2L));
        }
    }
}
