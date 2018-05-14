package org.pixeltime.enchantmentsenhance.events.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Horse_Rider implements Listener {
    @EventHandler
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "horse_rider"));
        if (entityDamageByEntityEvent.getDamager() instanceof Player && !(entityDamageByEntityEvent.getEntity() instanceof Player)) {
            try {
                final Player owner = (Player) entityDamageByEntityEvent.getDamager();
                ItemStack[] armorContents= owner.getInventory().getArmorContents();
                for (int length = armorContents.length, i = 0; i < length; ++i) {
                    final ItemStack itemStack = armorContents[i];
                    if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && entityDamageByEntityEvent.getEntity() instanceof Horse) {
                        entityDamageByEntityEvent.setCancelled(true);
                        entityDamageByEntityEvent.setDamage(0.0);
                        final Horse horse = (Horse) entityDamageByEntityEvent.getEntity();
                        if (horse.isTamed()) {
                            return;
                        }
                        horse.setTamed(true);
                        horse.setOwner(owner);
                        horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}
