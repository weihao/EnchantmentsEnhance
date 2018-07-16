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

package org.pixeltime.enchantmentsenhance.gui.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.api.API;
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract;
import org.pixeltime.enchantmentsenhance.gui.menu.icons.BackIcon;
import org.pixeltime.enchantmentsenhance.gui.menu.icons.GrindIcon;
import org.pixeltime.enchantmentsenhance.gui.menu.icons.ReblathIcon;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.MM;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HealPotion
 * @version Feb 9, 2018
 */
public class ItemMenu extends GUIAbstract {
    public static Map<String, Integer> clickedItem = new HashMap<>();
    private BackIcon back = new BackIcon();
    private ReblathIcon reblath = new ReblathIcon();
    private GrindIcon grind = new GrindIcon();

    public ItemMenu(Player p) {
        super(p, 54, SettingsManager.lang.getString("Item.title"));
        update();
    }


    @Override
    public void update() {
        getInventory().clear();
        getActions().clear();

        Player player = Bukkit.getPlayer(playerName);
        for (int i = 0; i < MM.stoneTypes.size(); i++) {
            final int stoneId = i;
            if (clickedItem.containsKey(playerName) && stoneId == clickedItem.get(playerName)) {
                setItem(Util.getSlot((i % 9) + 1, (i / 9) + 1),
                        CompatibilityManager.glow.addGlow(MainMenu.stone.getItem(i, player)),
                        () -> {
                            if (API.getItem(playerName, stoneId) > 0) {
                                clickedItem.put(player.getName(), stoneId);
                            }
                        });
            } else {
                setItem(Util.getSlot((i % 9) + 1, (i / 9) + 1), MainMenu.stone.getItem(i, player),
                        () -> {
                            if (API.getItem(playerName, stoneId) > 0) {
                                clickedItem.put(player.getName(), stoneId);
                            }
                        });
            }
        }

        setItem(back.getPosition(), back.getItem(), () -> new BukkitRunnable() {
            @Override
            public void run() {
                player.closeInventory();
                new MainMenu(player).open();
            }
        }.runTaskLaterAsynchronously(Main.getMain(), 2L));

        setItem(grind.getPosition(), grind.getItem());

        setItem(reblath.getPosition(), reblath.getItem(), () ->
        {
            // If player has item to failstack.
            if (API.getItem(player.getName(), 1) > 0) {
                // Roll.
                if ((Math.random() * 100) < reblath.getChance()) {
                    API.addFailstack(player.getName(), 1);
                } else {
                    API.resetFailstack(player.getName());
                }
            }
        });
    }
}
