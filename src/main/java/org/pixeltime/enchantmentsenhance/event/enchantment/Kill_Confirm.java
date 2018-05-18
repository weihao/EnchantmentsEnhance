package org.pixeltime.enchantmentsenhance.event.enchantment;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Kill_Confirm implements Listener {
    @EventHandler
    public void onDeath(final PlayerDeathEvent playerDeathEvent) {
        final Player entity = playerDeathEvent.getEntity();
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "kill_confirm"));
        try {
            if (playerDeathEvent.getEntity().getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK) && playerDeathEvent.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
                final Entity damager = ((EntityDamageByEntityEvent) playerDeathEvent.getEntity().getLastDamageCause()).getDamager();
                if (damager instanceof Player) {
                    final Player player = (Player) damager;
                    final int n = (int) (Math.random() * 100.0);
                    if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("kill_confirm.level_I.chance")) {
                        final ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
                        final SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
                        itemMeta.setOwner(entity.getName());
                        itemMeta.setDisplayName("§aSkull of " + entity.getName());
                        itemStack.setItemMeta(itemMeta);
                        player.getWorld().dropItem(entity.getLocation(), itemStack);
                    }
                    if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("kill_confirm.level_II.chance")) {
                        final ItemStack itemStack2 = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
                        final SkullMeta itemMeta2 = (SkullMeta) itemStack2.getItemMeta();
                        itemMeta2.setOwner(entity.getName());
                        itemMeta2.setDisplayName("§aSkull of " + entity.getName());
                        itemStack2.setItemMeta(itemMeta2);
                        player.getWorld().dropItem(entity.getLocation(), itemStack2);
                    }
                    if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && n < SettingsManager.enchant.getInt("kill_confirm.level_III.chance")) {
                        final ItemStack itemStack3 = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
                        final SkullMeta itemMeta3 = (SkullMeta) itemStack3.getItemMeta();
                        itemMeta3.setOwner(entity.getName());
                        itemMeta3.setDisplayName("§aSkull of " + entity.getName());
                        itemStack3.setItemMeta(itemMeta3);
                        player.getWorld().dropItem(entity.getLocation(), itemStack3);
                    }
                }
            }
        } catch (Exception ex) {
        }
    }
}
