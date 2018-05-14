package org.pixeltime.enchantmentsenhance.events.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

import java.util.Random;

public class Smelt implements Listener {
    @EventHandler
    public void onBreak(final BlockBreakEvent blockBreakEvent) {
        if (blockBreakEvent.isCancelled()) {
            return;
        }
        final Player player = blockBreakEvent.getPlayer();
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "smelt"));
        if (player.getItemInHand() != null && player.getInventory().getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasLore() && player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
            final Block block = blockBreakEvent.getBlock();
            final int calculateFortune = this.calculateFortune(player, block.getType());
            if (block.getType() != Material.IRON_ORE && block.getType() != Material.GOLD_ORE && block.getType() != Material.DIAMOND_ORE && block.getType() != Material.LAPIS_ORE) {
                block.getType();
                final Material emerald_ORE = Material.EMERALD_ORE;
            }
            if (block.getType() == Material.IRON_ORE) {
                blockBreakEvent.getBlock().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT, calculateFortune));
                block.setType(Material.AIR);
            }
            if (block.getType() == Material.GOLD_ORE) {
                blockBreakEvent.getBlock().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT, calculateFortune));
                block.setType(Material.AIR);
            }
            if (block.getType() == Material.COAL_ORE) {
                blockBreakEvent.getBlock().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), new ItemStack(Material.COAL, calculateFortune));
                block.setType(Material.AIR);
            }
            if (block.getType() == Material.IRON_ORE) {
                blockBreakEvent.getBlock().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT, calculateFortune));
                block.setType(Material.AIR);
            }
            if (block.getType() == Material.DIAMOND_ORE) {
                blockBreakEvent.getBlock().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), new ItemStack(Material.DIAMOND, calculateFortune));
                block.setType(Material.AIR);
            }
            if (block.getType() == Material.EMERALD_ORE) {
                blockBreakEvent.getBlock().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), new ItemStack(Material.EMERALD, calculateFortune));
                block.setType(Material.AIR);
            }
            if (block.getType() == Material.LAPIS_ORE) {
                blockBreakEvent.getBlock().getWorld().dropItemNaturally(blockBreakEvent.getBlock().getLocation(), new ItemStack(Material.INK_SACK, calculateFortune, (short) 4));
                block.setType(Material.AIR);
            }
        }
    }

    public int calculateFortune(final Player player, final Material material) {
        int n = 1;
        if (player.getItemInHand().getEnchantments().containsKey(Enchantment.LOOT_BONUS_BLOCKS)) {
            int n2 = new Random().nextInt(player.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 2) - 1;
            if (n2 <= 0) {
                n2 = 1;
            }
            n = ((material == Material.LAPIS_ORE) ? (4 + new Random().nextInt(5)) : 1) * (n2 + 1);
        }
        return n;
    }
}
