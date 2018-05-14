package org.pixeltime.enchantmentsenhance.events.enchantments;

import com.sk89q.worldguard.bukkit.WGBukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Plow implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onInteract(final PlayerInteractEvent playerInteractEvent) {
        if (playerInteractEvent.isCancelled()) {
            return;
        }
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "plow"));
        final Player player = playerInteractEvent.getPlayer();
        if (player.getItemInHand() != null && player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasLore()) {
            final Block clickedBlock = playerInteractEvent.getClickedBlock();
            if (clickedBlock.getType() != Material.DIRT && clickedBlock.getType() != Material.GRASS) {
                return;
            }
            if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                final double n = 0.5;
                final Location location = clickedBlock.getLocation();
                for (double n2 = location.getBlockX() - n; n2 <= location.getBlockX() + n; ++n2) {
                    for (double n3 = location.getBlockZ() - n; n3 <= location.getBlockZ() + n; ++n3) {
                        final Block block = location.getWorld().getBlockAt(new Location(clickedBlock.getWorld(), n2, (double) clickedBlock.getY(), n3));
                        if (block.getType() != Material.GRASS && block.getType() != Material.DIRT) {
                            return;
                        }
                        if (SettingsManager.enchant.getBoolean("allow-worldguard") && !WGBukkit.getPlugin().canBuild(player, block)) {
                            return;
                        }
                        block.setType(Material.SOIL);
                    }
                }
            }
            if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II")) {
                final double n4 = 1.0;
                final Location location2 = clickedBlock.getLocation();
                for (double n5 = location2.getBlockX() - n4; n5 <= location2.getBlockX() + n4; ++n5) {
                    for (double n6 = location2.getBlockZ() - n4; n6 <= location2.getBlockZ() + n4; ++n6) {
                        final Block block2 = location2.getWorld().getBlockAt(new Location(clickedBlock.getWorld(), n5, (double) clickedBlock.getY(), n6));
                        if (block2.getType() != Material.GRASS && block2.getType() != Material.DIRT) {
                            return;
                        }
                        if (SettingsManager.enchant.getBoolean("allow-worldguard") && !WGBukkit.getPlugin().canBuild(player, block2)) {
                            return;
                        }
                        block2.setType(Material.SOIL);
                    }
                }
            }
            if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III")) {
                final double n7 = 1.5;
                final Location location3 = clickedBlock.getLocation();
                for (double n8 = location3.getBlockX() - n7; n8 <= location3.getBlockX() + n7; ++n8) {
                    for (double n9 = location3.getBlockZ() - n7; n9 <= location3.getBlockZ() + n7; ++n9) {
                        final Block block3 = location3.getWorld().getBlockAt(new Location(clickedBlock.getWorld(), n8, (double) clickedBlock.getY(), n9));
                        if (block3.getType() != Material.GRASS && block3.getType() != Material.DIRT) {
                            return;
                        }
                        if (SettingsManager.enchant.getBoolean("allow-worldguard") && !WGBukkit.getPlugin().canBuild(player, block3)) {
                            return;
                        }
                        block3.setType(Material.SOIL);
                    }
                }
            }
            if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " IV")) {
                final double n10 = 2.0;
                final Location location4 = clickedBlock.getLocation();
                for (double n11 = location4.getBlockX() - n10; n11 <= location4.getBlockX() + n10; ++n11) {
                    for (double n12 = location4.getBlockZ() - n10; n12 <= location4.getBlockZ() + n10; ++n12) {
                        final Block block4 = location4.getWorld().getBlockAt(new Location(clickedBlock.getWorld(), n11, (double) clickedBlock.getY(), n12));
                        if (block4.getType() != Material.GRASS && block4.getType() != Material.DIRT) {
                            return;
                        }
                        if (SettingsManager.enchant.getBoolean("allow-worldguard") && !WGBukkit.getPlugin().canBuild(player, block4)) {
                            return;
                        }
                        block4.setType(Material.SOIL);
                    }
                }
            }
            if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " V")) {
                final double n13 = 2.5;
                final Location location5 = clickedBlock.getLocation();
                for (double n14 = location5.getBlockX() - n13; n14 <= location5.getBlockX() + n13; ++n14) {
                    for (double n15 = location5.getBlockZ() - n13; n15 <= location5.getBlockZ() + n13; ++n15) {
                        final Block block5 = location5.getWorld().getBlockAt(new Location(clickedBlock.getWorld(), n14, (double) clickedBlock.getY(), n15));
                        if (block5.getType() != Material.GRASS && block5.getType() != Material.DIRT) {
                            return;
                        }
                        if (SettingsManager.enchant.getBoolean("allow-worldguard") && !WGBukkit.getPlugin().canBuild(player, block5)) {
                            return;
                        }
                        block5.setType(Material.SOIL);
                    }
                }
            }
        }
    }
}
