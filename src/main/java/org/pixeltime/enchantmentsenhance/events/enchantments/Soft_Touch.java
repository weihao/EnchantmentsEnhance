package org.pixeltime.enchantmentsenhance.events.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Soft_Touch implements Listener {
    @EventHandler
    public void onBlockBreak(final BlockBreakEvent blockBreakEvent) {
        final Player player = blockBreakEvent.getPlayer();
        final Block block = blockBreakEvent.getBlock();
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "soft_touch"));
        try {
            if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && block.getType() == Material.MOB_SPAWNER) {
                final CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();
                final ItemStack itemStack = new ItemStack(creatureSpawner.getType(), 1, (short) block.getData());
                final ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(ChatColor.AQUA + creatureSpawner.getCreatureTypeName());
                itemStack.setItemMeta(itemMeta);
                if (!blockBreakEvent.isCancelled()) {
                    block.getLocation().getWorld().dropItem(block.getLocation(), itemStack);
                }
            }
        } catch (Exception ex) {
        }
    }

    @EventHandler
    public void onPlace(final BlockPlaceEvent blockPlaceEvent) {
        final Block block = blockPlaceEvent.getBlock();
        try {
            if (block.getType() == Material.MOB_SPAWNER) {
                ((CreatureSpawner) block.getState()).setSpawnedType(EntityType.fromName(ChatColor.stripColor(blockPlaceEvent.getItemInHand().getItemMeta().getDisplayName())));
            }
        } catch (Exception ex) {
        }
    }
}
