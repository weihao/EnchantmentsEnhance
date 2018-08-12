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
import org.bukkit.event.inventory.ClickType;
import org.bukkit.scheduler.BukkitRunnable;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract;
import org.pixeltime.enchantmentsenhance.gui.menu.icons.BackIcon;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.mysql.PlayerStat;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.List;

public class ValksMenu extends GUIAbstract {
    private BackIcon back = new BackIcon();
    private int currPage = 1;

    public ValksMenu(Player player) {
        super(player, 54, SettingsManager.lang.getString("valks.gui"));
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
                setItem(Util.getSlot((i % 9) + 1, (i / 9) + 1),
                        CompatibilityManager.glow.addGlow(new ItemBuilder(Material.BOOK, level)
                                .setName(SettingsManager.lang.getString("item.valks") + "+" + level)
                                .addLoreLine(SettingsManager.lang.getString("menu.leftAdviceInfo"))
                                .addLoreLine(SettingsManager.lang.getString("menu.rightInfo"))
                                .toItemStack()), (clickType) -> new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (clickType == ClickType.LEFT) {
                                    if (level > 0) {
                                        if (Main.getApi().getFailstack(player.getName()) == 0) {
                                            Main.getApi().addFailstack(player.getName(), level);
                                            PlayerStat.getPlayerStats(playerName).getValks().remove(index);
                                            Util.sendMessage(SettingsManager.lang.getString(
                                                    "valks.used").replaceAll("%LEVEL%", Integer
                                                    .toString(level)), player);
                                            new BukkitRunnable() {
                                                @Override
                                                public void run() {
                                                    player.closeInventory();
                                                    new MainMenu(player).open();
                                                }
                                            }.runTaskLaterAsynchronously(Main.getMain(), 2L);
                                        } else {
                                            Util.sendMessage(SettingsManager.lang.getString(
                                                    "valks.hasFailstack"), player);
                                        }
                                    } else {
                                        Util.sendMessage(SettingsManager.lang.getString(
                                                "config.invalidNumber"), player);
                                    }
                                }
                                if (clickType == ClickType.RIGHT) {
                                    if (!Util.invFull(player)) {
                                        player.getInventory().addItem(ItemManager.adviceMaterialize(level));
                                        PlayerStat.getPlayerStats(playerName).getValks().remove(index);
                                        update();
                                    } else {
                                        Util.sendMessage(SettingsManager.lang.getString("materialize.inventoryFull"), player);
                                    }
                                }
                            }
                        }.runTaskLaterAsynchronously(Main.getMain(), 2L));

            }
        } catch (IndexOutOfBoundsException ex) {
            // Expected.
        }

        setItem(back.getPosition(), back.getItem(playerName), (clickType) -> new BukkitRunnable() {
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
                            .setName(SettingsManager.lang.getString("menu.gui.next"))
                            .addLoreLine(SettingsManager.lang.getString("menu.lore.next"))
                            .toItemStack(),
                    (clickType) -> {
                        currPage++;
                    });
        }
    }
}
