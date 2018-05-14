package org.pixeltime.enchantmentsenhance.events.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Farmer implements Listener {
    @EventHandler
    public void onPlace(final BlockPlaceEvent blockPlaceEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "farmer"));
        final Player player = blockPlaceEvent.getPlayer();
        try {
            ItemStack[] armorContents;
            for (int length = (armorContents = player.getInventory().getArmorContents()).length, i = 0; i < length; ++i) {
                final ItemStack itemStack = armorContents[i];
                if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                    if (player.getItemInHand().getType() == Material.CARROT_ITEM) {
                        blockPlaceEvent.getBlockPlaced().setTypeIdAndData(Material.CARROT.getId(), (byte) 7, true);
                    }
                    if (player.getItemInHand().getType() == Material.MELON_SEEDS) {
                        blockPlaceEvent.getBlockPlaced().setType(Material.MELON);
                    }
                    if (player.getItemInHand().getType() == Material.POTATO_ITEM) {
                        blockPlaceEvent.getBlockPlaced().setTypeIdAndData(Material.POTATO.getId(), (byte) 7, true);
                    }
                    if (player.getItemInHand().getType() == Material.SEEDS) {
                        blockPlaceEvent.getBlockPlaced().setTypeIdAndData(Material.CROPS.getId(), (byte) 7, true);
                    }
                    if (player.getItemInHand().getType() == Material.PUMPKIN_SEEDS) {
                        blockPlaceEvent.getBlockPlaced().setType(Material.PUMPKIN);
                    }
                }
            }
        } catch (Exception ex) {
        }
    }
}
