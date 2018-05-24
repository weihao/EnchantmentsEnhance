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

package org.pixeltime.enchantmentsenhance.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class GUI {
    public static Map<UUID, GUI> inventoriesByUUID = new HashMap<>();
    public static Map<String, UUID> openInventories = new HashMap<>();

    private UUID uuid;
    private Inventory inventory;
    private Map<Integer, GUIAction> actions;


    public GUI(int invSize, String invName) {
        this.uuid = UUID.randomUUID();
        this.inventory = Bukkit.createInventory(null, invSize, invName);
        this.actions = new HashMap<>();
        inventoriesByUUID.put(getUuid(), this);
    }

    public static Map<UUID, GUI> getInventoriesByUUID() {
        return inventoriesByUUID;
    }

    public static Map<String, UUID> getOpenInventories() {
        return openInventories;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public UUID getUuid() {
        return this.uuid;
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

    public void open(Player p) {
        p.openInventory(inventory);
        openInventories.put(p.getName(), getUuid());
    }

    public Map<Integer, GUIAction> getActions() {
        return actions;
    }


    public interface GUIAction {
        void click(Player player);
    }
}
