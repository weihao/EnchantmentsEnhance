package org.pixeltime.enchantmentsenhance.event.enchantment;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Tamer implements Listener {
    @EventHandler
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "tamer"));
        if (entityDamageByEntityEvent.getDamager() instanceof Player && !(entityDamageByEntityEvent.getEntity() instanceof Player)) {
            try {
                final Player owner = (Player) entityDamageByEntityEvent.getDamager();
                ItemStack[] armorContents = owner.getInventory().getArmorContents();
                for (int length = armorContents.length, i = 0; i < length; ++i) {
                    final ItemStack itemStack = armorContents[i];
                    if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && entityDamageByEntityEvent.getEntity() instanceof Wolf) {
                        entityDamageByEntityEvent.setCancelled(true);
                        entityDamageByEntityEvent.setDamage(0.0);
                        final Wolf wolf = (Wolf) entityDamageByEntityEvent.getEntity();
                        if (wolf.isTamed()) {
                            return;
                        }
                        wolf.setTamed(true);
                        wolf.setOwner(owner);
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}
