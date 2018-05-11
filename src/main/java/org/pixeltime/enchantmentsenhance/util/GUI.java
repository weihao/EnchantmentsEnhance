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
