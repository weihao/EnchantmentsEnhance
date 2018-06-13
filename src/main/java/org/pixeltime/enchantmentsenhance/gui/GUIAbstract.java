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

package org.pixeltime.enchantmentsenhance.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.HashMap;
import java.util.Map;

public abstract class GUIAbstract {
    public String playerName;
    private Inventory inventory;
    private Map<Integer, GUIAction> actions;

    public GUIAbstract(Player player, int invSize, String invName) {
        this.inventory = Bukkit.createInventory(null, invSize, Util.toColor(invName));
        this.actions = new HashMap<>();
        GUIManager.getMap().put(player.getName(), this);
        this.playerName = player.getName();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setItem(int slot, ItemStack stack, GUIAction action) {
        inventory.setItem(slot, stack);
        if (action != null) {
            actions.put(slot, action);
        }
    }

    public void setItem(int slot, ItemStack stack) {
        setItem(slot, stack, null);
    }

    public void open() {
        Bukkit.getPlayer(playerName).openInventory(inventory);
    }

    public Map<Integer, GUIAction> getActions() {
        return actions;
    }

    public abstract void update();

    public interface GUIAction {
        void click();
    }
}
