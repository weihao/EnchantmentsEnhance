package org.pixeltime.enchantmentsenhance.events.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Feather_Fall implements Listener {
    @EventHandler
    public void onDamage(final EntityDamageEvent entityDamageEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "feather_fall"));
        if (entityDamageEvent.getEntity() instanceof Player) {
            ItemStack[] armorContents;
            for (int length = (armorContents = ((Player) entityDamageEvent.getEntity()).getInventory().getArmorContents()).length, i = 0; i < length; ++i) {
                final ItemStack itemStack = armorContents[i];
                if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore() && entityDamageEvent.getCause() == EntityDamageEvent.DamageCause.FALL && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                    entityDamageEvent.setCancelled(true);
                }
            }
        }
    }
}
