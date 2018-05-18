package org.pixeltime.enchantmentsenhance.event.enchantment;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Pyromaniac implements Listener {
    @EventHandler
    public void onDamage(final EntityDamageEvent entityDamageEvent) {
        if (entityDamageEvent.getEntity() instanceof Player) {
            final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "pyromaniac"));
            final Player player = (Player) entityDamageEvent.getEntity();
            if (entityDamageEvent.getCause() == EntityDamageEvent.DamageCause.FIRE || entityDamageEvent.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                final int n = (int) (Math.random() * 100.0);
                try {
                    ItemStack[] armorContents = player.getInventory().getArmorContents();
                    for (int length = armorContents.length, i = 0; i < length; ++i) {
                        final ItemStack itemStack = armorContents[i];
                        if (itemStack != null) {
                            if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("pyromaniac.level_I.chance")) {
                                player.setHealth(20.0);
                                player.setFoodLevel(20);
                            }
                            if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("pyromaniac.level_II.chance")) {
                                player.setHealth(20.0);
                                player.setFoodLevel(20);
                            }
                            if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && n < SettingsManager.enchant.getInt("pyromaniac.level_III.chance")) {
                                player.setHealth(20.0);
                                player.setFoodLevel(20);
                            }
                            if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " IV") && n < SettingsManager.enchant.getInt("pyromaniac.level_IV.chance")) {
                                player.setHealth(20.0);
                                player.setFoodLevel(20);
                            }
                            if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " V") && n < SettingsManager.enchant.getInt("pyromaniac.level_V.chance")) {
                                player.setHealth(20.0);
                                player.setFoodLevel(20);
                            }
                        }
                    }
                } catch (Exception ex) {
                }
            }
        }
    }
}
