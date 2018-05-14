package org.pixeltime.enchantmentsenhance.events.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Endless implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void noWeaponBreakDamage(final PlayerInteractEvent playerInteractEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "endless"));
        try {
            if (playerInteractEvent.getItem().getItemMeta().hasLore() && playerInteractEvent.getItem().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                playerInteractEvent.getItem().setDurability((short) 1);
            }
        } catch (Exception ex) {
        }
    }

    @EventHandler
    public void noWeaponBreakDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "animal_tamer"));
        try {
            if (entityDamageByEntityEvent.getDamager() instanceof Player) {
                final Player player = (Player) entityDamageByEntityEvent.getDamager();
                if (player.getItemInHand().getItemMeta().hasLore() && player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                    ((Player) entityDamageByEntityEvent.getDamager()).getItemInHand().setDurability((short) 1);
                }
            }
            if (entityDamageByEntityEvent.getEntity() instanceof Player) {
                final Player player2 = (Player) entityDamageByEntityEvent.getEntity();
                if (player2.getInventory().getHelmet() != null) {
                    final ItemStack helmet = player2.getInventory().getHelmet();
                    if (helmet.getItemMeta().hasLore() && helmet.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                        helmet.setDurability((short) 0);
                        player2.getInventory().setHelmet(helmet);
                    }
                }
                if (player2.getInventory().getChestplate() != null) {
                    final ItemStack chestplate = player2.getInventory().getChestplate();
                    if (chestplate.getItemMeta().hasLore() && chestplate.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                        chestplate.setDurability((short) 0);
                        player2.getInventory().setChestplate(chestplate);
                    }
                }
                if (player2.getInventory().getLeggings() != null) {
                    final ItemStack leggings = player2.getInventory().getLeggings();
                    if (leggings.getItemMeta().hasLore() && leggings.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                        leggings.setDurability((short) 0);
                        player2.getInventory().setLeggings(leggings);
                    }
                }
                if (player2.getInventory().getBoots() != null) {
                    final ItemStack boots = player2.getInventory().getBoots();
                    if (boots.getItemMeta().hasLore() && boots.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                        boots.setDurability((short) 0);
                        player2.getInventory().setBoots(boots);
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    @EventHandler
    public void noWeaponBreakDamage(final EntityShootBowEvent entityShootBowEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "animal_tamer"));
        try {
            if (entityShootBowEvent.getEntity() instanceof Player && entityShootBowEvent.getBow().getItemMeta().hasLore() && entityShootBowEvent.getBow().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                entityShootBowEvent.getBow().setDurability((short) 1);
            }
        } catch (Exception ex) {
        }
    }

    @EventHandler
    public void noWeaponBreakDamage(final PlayerItemBreakEvent playerItemBreakEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "animal_tamer"));
        try {
            final ItemStack clone = playerItemBreakEvent.getBrokenItem().clone();
            if (clone.getItemMeta().hasLore() && clone.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                clone.setDurability((short) 0);
            }
            playerItemBreakEvent.getPlayer().getInventory().addItem(new ItemStack[]{clone});
        } catch (Exception ex) {
        }
    }
}
