package org.pixeltime.enchantmentsenhance.events.enchantments;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Superman_Punch implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamaged(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "superman_punch"));
        if (entityDamageByEntityEvent.getEntity() instanceof Player) {
            try {
                final Entity damager = entityDamageByEntityEvent.getDamager();
                final Player player = (Player) entityDamageByEntityEvent.getEntity();
                if (entityDamageByEntityEvent.isCancelled()) {
                    return;
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).queryState(null, new StateFlag[]{DefaultFlag.PVP}) == StateFlag.State.DENY) {
                    return;
                }
                ItemStack[] armorContents = player.getInventory().getArmorContents();
                for (int length = armorContents.length, i = 0; i < length; ++i) {
                    final ItemStack itemStack = armorContents[i];
                    if (itemStack != null) {
                        if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && (int) (Math.random() * 100.0) < SettingsManager.enchant.getInt("superman_punch.level_I.chance")) {
                            damager.setVelocity(player.getLocation().getDirection().multiply(SettingsManager.enchant.getInt("superman_punch.level_I.power")));
                        }
                        if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && (int) (Math.random() * 100.0) < SettingsManager.enchant.getInt("superman_punch.level_II.chance")) {
                            damager.setVelocity(player.getLocation().getDirection().multiply(SettingsManager.enchant.getInt("superman_punch.level_II.power")));
                        }
                        if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && (int) (Math.random() * 100.0) < SettingsManager.enchant.getInt("superman_punch.level_III.chance")) {
                            damager.setVelocity(player.getLocation().getDirection().multiply(SettingsManager.enchant.getInt("superman_punch.level_III.power")));
                        }
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}
