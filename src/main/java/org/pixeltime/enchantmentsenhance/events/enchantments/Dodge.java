package org.pixeltime.enchantmentsenhance.events.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Dodge implements Listener {
    @EventHandler
    public void onDamage(final EntityDamageEvent entityDamageEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "dodge"));
        if (entityDamageEvent.getEntity() instanceof Player) {
            final Player player = (Player) entityDamageEvent.getEntity();
            final int n = (int) (Math.random() * 100.0);
            final ItemStack[] armorContents = player.getInventory().getArmorContents();
            try {
                ItemStack[] array;
                for (int length = (array = armorContents).length, i = 0; i < length; ++i) {
                    final ItemStack itemStack = array[i];
                    if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("dodge.level_I.chance")) {
                        entityDamageEvent.setDamage(0.0);
                    }
                }
                ItemStack[] array2;
                for (int length2 = (array2 = armorContents).length, j = 0; j < length2; ++j) {
                    final ItemStack itemStack2 = array2[j];
                    if (itemStack2 != null && itemStack2.hasItemMeta() && itemStack2.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("dodge.level_II.chance")) {
                        entityDamageEvent.setDamage(0.0);
                    }
                }
                ItemStack[] array3;
                for (int length3 = (array3 = armorContents).length, k = 0; k < length3; ++k) {
                    final ItemStack itemStack3 = array3[k];
                    if (itemStack3 != null && itemStack3.hasItemMeta() && itemStack3.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && n < SettingsManager.enchant.getInt("dodge.level_III.chance")) {
                        entityDamageEvent.setDamage(0.0);
                    }
                }
                ItemStack[] array4;
                for (int length4 = (array4 = armorContents).length, l = 0; l < length4; ++l) {
                    final ItemStack itemStack4 = array4[l];
                    if (itemStack4 != null && itemStack4.hasItemMeta() && itemStack4.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " IV") && n < SettingsManager.enchant.getInt("dodge.level_IV.chance")) {
                        entityDamageEvent.setDamage(0.0);
                    }
                }
                ItemStack[] array5;
                for (int length5 = (array5 = armorContents).length, n2 = 0; n2 < length5; ++n2) {
                    final ItemStack itemStack5 = array5[n2];
                    if (itemStack5 != null && itemStack5.hasItemMeta() && itemStack5.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " V") && n < SettingsManager.enchant.getInt("dodge.level_V.chance")) {
                        entityDamageEvent.setDamage(0.0);
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}
