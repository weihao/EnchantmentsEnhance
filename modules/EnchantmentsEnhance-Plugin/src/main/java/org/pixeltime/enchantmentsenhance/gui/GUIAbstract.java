package org.pixeltime.enchantmentsenhance.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
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
        this.playerName = player.getName();
        GUIManager.getMap().put(player.getName(), this);
        GUIManager.getSet().add(this);
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
        void click(ClickType clicktype);
    }
}
