/*
 *     Copyright (C) 2017-Present HealPot
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

package org.pixeltime.enchantmentsenhance.gui.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.api.API;
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract;
import org.pixeltime.enchantmentsenhance.gui.menu.icons.BackIcon;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.mysql.PlayerStat;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.List;

public class ValksMenu extends GUIAbstract {
    private BackIcon back = new BackIcon();
    private int currPage = 1;

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
        try {
            for (int i = 0; i < (inv.size() > 54 ? 54 : inv.size()); i++) {
                final int index = i + ((currPage - 1) * 54);
                final int level = inv.get(index);
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
        } catch (IndexOutOfBoundsException ex) {
            // Expected.
        }

        setItem(back.getPosition(), back.getItem(), () -> new BukkitRunnable() {
            @Override
            public void run() {
                if (currPage == 1) {
                    player.closeInventory();
                    new MainMenu(player).open();
                } else {
                    currPage--;
                }
            }
        }.runTaskLaterAsynchronously(Main.getMain(), 2L));

        if (inv.size() > currPage * 54) {
            setItem(Util.getSlot(9, 6),
                    new ItemBuilder(Material.ARROW)
                            .setName(SettingsManager.lang.getString("Menu.gui.next"))
                            .addLoreLine(SettingsManager.lang.getString("Menu.lore.next"))
                            .toItemStack(),
                    () -> {
                        currPage++;
                    });
        }
    }
}
