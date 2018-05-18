package org.pixeltime.enchantmentsenhance.event.enchantment;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Auto_Block implements Listener {
    public static void removeItems(final Inventory inventory, final Material material, int n) {
        if (n <= 0) {
            return;
        }
        for (int size = inventory.getSize(), i = 0; i < size; ++i) {
            final ItemStack item = inventory.getItem(i);
            if (item != null) {
                if (material == item.getType()) {
                    final int amount = item.getAmount() - n;
                    if (amount > 0) {
                        item.setAmount(amount);
                        break;
                    }
                    inventory.clear(i);
                    n = -amount;
                    if (n == 0) {
                        break;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBreak(final BlockBreakEvent blockBreakEvent) {
        final Player player = blockBreakEvent.getPlayer();
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "auto_block"));
        if (player.getInventory().getItemInHand() != null && player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasLore() && player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
            this.autoBlock(player);
        }
    }

    public void autoBlock(final Player player) {
        try {
            ItemStack[] contents;
            for (int length = (contents = player.getInventory().getContents()).length, i = 0; i < length; ++i) {
                final ItemStack itemStack = contents[i];
                final int n = itemStack.getAmount() / 9;
                final int n2 = itemStack.getAmount() / 4;
                if (n > 0) {
                    if (itemStack.getType() == Material.EMERALD) {
                        removeItems(player.getInventory(), Material.EMERALD, n * 9);
                        player.getInventory().addItem(new ItemStack[]{new ItemStack(Material.EMERALD_BLOCK, n)});
                    }
                    if (itemStack.getType() == Material.DIAMOND) {
                        removeItems(player.getInventory(), Material.DIAMOND, n * 9);
                        player.getInventory().addItem(new ItemStack[]{new ItemStack(Material.DIAMOND_BLOCK, n)});
                    }
                    if (itemStack.getType() == Material.IRON_INGOT) {
                        removeItems(player.getInventory(), Material.IRON_INGOT, n * 9);
                        player.getInventory().addItem(new ItemStack[]{new ItemStack(Material.IRON_BLOCK, n)});
                    }
                    if (itemStack.getType() == Material.GOLD_INGOT) {
                        removeItems(player.getInventory(), Material.GOLD_INGOT, n * 9);
                        player.getInventory().addItem(new ItemStack[]{new ItemStack(Material.GOLD_BLOCK, n)});
                    }
                    if (itemStack.getType() == Material.INK_SACK && itemStack.getData().getData() == 4) {
                        removeItems(player.getInventory(), Material.INK_SACK, n * 9);
                        player.getInventory().addItem(new ItemStack[]{new ItemStack(Material.LAPIS_BLOCK, n)});
                    }
                    if (itemStack.getType() == Material.COAL) {
                        removeItems(player.getInventory(), Material.COAL, n * 9);
                        player.getInventory().addItem(new ItemStack[]{new ItemStack(Material.COAL_BLOCK, n)});
                    }
                    if (itemStack.getType() == Material.REDSTONE) {
                        removeItems(player.getInventory(), Material.REDSTONE, n * 9);
                        player.getInventory().addItem(new ItemStack[]{new ItemStack(Material.REDSTONE_BLOCK, n)});
                    }
                }
                if (n2 > 0) {
                    if (itemStack.getType() == Material.CLAY_BALL) {
                        removeItems(player.getInventory(), Material.CLAY_BALL, n * 4);
                        player.getInventory().addItem(new ItemStack[]{new ItemStack(Material.CLAY, n)});
                    }
                    if (itemStack.getType() == Material.SNOW_BALL) {
                        removeItems(player.getInventory(), Material.SNOW_BALL, n * 9);
                        player.getInventory().addItem(new ItemStack[]{new ItemStack(Material.SNOW, n)});
                    }
                }
            }
        } catch (Exception ex) {
        }
    }
}
