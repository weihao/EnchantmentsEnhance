package org.pixeltime.healpot.enhancement.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.healpot.enhancement.events.Menu;
import org.pixeltime.healpot.enhancement.events.blacksmith.SecretBook;
import org.pixeltime.healpot.enhancement.events.blackspirit.Enhance;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.Util;

public class MenuHandler implements Listener {
    private Map<Player, ItemStack> itemOnEnhancingSlot =
        new HashMap<Player, ItemStack>();


    /**
     * Handles Gui.
     * 
     * @param e
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getSlot() < 0) {
            return;
        }
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        if (e.getCurrentItem().getType() == (Material.AIR)) {
            return;
        }
        Player player = (Player)e.getWhoClicked();
        if (Menu.getScreen() != null) {
            if (e.getInventory().getName().equalsIgnoreCase(Menu.getScreen()
                .getName())) {
                e.setCancelled(true);
                if (e.getCurrentItem().hasItemMeta()) {
                    if (Enhance.getValidationOfItem(player, e.getCurrentItem())
                        && !itemOnEnhancingSlot.containsKey(player)) {
                        itemOnEnhancingSlot.put(player, e.getCurrentItem());
                        Menu.updateInv(e.getCurrentItem(), player,
                            itemOnEnhancingSlot.containsKey(player), true,
                            itemOnEnhancingSlot.get(player));
                    }
                    if (Util.isPluginItem(e.getCurrentItem(),
                        SettingsManager.lang.getString("Menu.gui.enhance"))
                        && itemOnEnhancingSlot.containsKey(player)) {
                        Enhance.diceToEnhancement(itemOnEnhancingSlot.get(
                            player), player);
                        Menu.updateInv(e.getCurrentItem(), player,
                            itemOnEnhancingSlot.containsKey(player), false,
                            itemOnEnhancingSlot.get(player));
                        return;
                    }
                    if (Util.isPluginItem(e.getCurrentItem(),
                        SettingsManager.lang.getString("Menu.gui.remove"))
                        && itemOnEnhancingSlot.containsKey(player)) {
                        itemOnEnhancingSlot.remove(player);
                        Menu.createMenu(player);
                        return;
                    }
                    if (Util.isPluginItem(e.getCurrentItem(),
                        SettingsManager.lang.getString("Menu.gui.force"))
                        && itemOnEnhancingSlot.containsKey(player)) {
                        Enhance.forceToEnhancement(itemOnEnhancingSlot.get(
                            player), player);
                        Menu.updateInv(e.getCurrentItem(), player,
                            itemOnEnhancingSlot.containsKey(player), false,
                            itemOnEnhancingSlot.get(player));
                        return;
                    }
                    if (Util.isPluginItem(e.getCurrentItem(),
                        SettingsManager.lang.getString("Menu.gui.store"))) {
                        SecretBook.addFailstackToStorage(player);
                        if (itemOnEnhancingSlot.get(player) == null) {
                            Menu.createMenu(player);
                        }
                        else {
                            Menu.updateFailstack(itemOnEnhancingSlot.get(
                                player), player);
                        }
                        return;
                    }
                }
                else if (Enhance.getValidationOfItem(player, e.getCurrentItem())
                    && !itemOnEnhancingSlot.containsKey(player)) {
                    itemOnEnhancingSlot.put(player, e.getCurrentItem());
                    Menu.updateInv(e.getCurrentItem(), player,
                        itemOnEnhancingSlot.containsKey(player), true,
                        itemOnEnhancingSlot.get(player));
                }
            }
        }
        if (!itemOnEnhancingSlot.containsKey(player)) {
            List<String> loreList = new ArrayList<String>();
            if ((e.getInventory().getType() != InventoryType.CRAFTING) && (e
                .getInventory().getType() != InventoryType.PLAYER)) {
                if ((e.getClick().equals(ClickType.NUMBER_KEY)) && (e
                    .getWhoClicked().getInventory().getItem(e
                        .getHotbarButton()) != null)) {
                    ItemStack itemMoved = e.getWhoClicked().getInventory()
                        .getItem(e.getHotbarButton());
                    if ((itemMoved.hasItemMeta()) && (itemMoved.getItemMeta()
                        .hasLore())) {
                        int loreSize = itemMoved.getItemMeta().getLore().size();
                        for (int i = 0; i < loreSize; i++) {
                            loreList.add((String)itemMoved.getItemMeta()
                                .getLore().get(i));
                        }
                        if (loreList.contains(ChatColor
                            .translateAlternateColorCodes('&',
                                SettingsManager.lang.getString(
                                    "Lore.UntradeableLore")))) {
                            e.setCancelled(true);
                            Util.sendMessage(SettingsManager.lang.getString(
                                "Config.pluginTag") + SettingsManager.lang
                                    .getString("Messages.NoStorage"), e
                                        .getWhoClicked());
                        }
                    }
                }
                if (e.getCurrentItem() != null) {
                    if ((e.getCurrentItem().hasItemMeta()) && (e
                        .getCurrentItem().getItemMeta().hasLore())) {
                        int loreSize = e.getCurrentItem().getItemMeta()
                            .getLore().size();
                        for (int i = 0; i < loreSize; i++) {
                            loreList.add((String)e.getCurrentItem()
                                .getItemMeta().getLore().get(i));
                        }
                        if (loreList.contains(ChatColor
                            .translateAlternateColorCodes('&',
                                SettingsManager.lang.getString(
                                    "Lore.UntradeableLore")))) {
                            e.setCancelled(true);
                            Util.sendMessage(SettingsManager.lang.getString(
                                "Config.pluginTag") + SettingsManager.lang
                                    .getString("Messages.NoStorage"), e
                                        .getWhoClicked());
                        }
                    }
                }
            }
        }
    }


    /**
     * Removes current item placed on the enhancing slot.
     * 
     * @param e
     */
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (itemOnEnhancingSlot.containsKey(e.getPlayer())) {
            itemOnEnhancingSlot.remove(e.getPlayer());
        }
    }
}
