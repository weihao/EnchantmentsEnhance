package org.pixeltime.enchantmentsenhance.events.enchantments;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Flame_Cloak implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "flame_cloak"));
        if (entityDamageByEntityEvent.getDamager() instanceof Player && entityDamageByEntityEvent.getEntity() instanceof Player) {
            try {
                final Player player = (Player) entityDamageByEntityEvent.getDamager();
                final Player player2 = (Player) entityDamageByEntityEvent.getEntity();
                if (entityDamageByEntityEvent.isCancelled()) {
                    return;
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player2.getWorld()).getApplicableRegions(player2.getLocation()).queryState(null, new StateFlag[]{DefaultFlag.PVP}) == StateFlag.State.DENY) {
                    return;
                }
                final ItemStack[] armorContents = player2.getInventory().getArmorContents();
                final int n = (int) (Math.random() * 100.0);
                ItemStack[] array;
                for (int length = (array = armorContents).length, i = 0; i < length; ++i) {
                    final ItemStack itemStack = array[i];
                    if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("flame_cloak.level_I.chance")) {
                        player.setFireTicks(SettingsManager.enchant.getInt("flame_cloak.level_I.duration") * 20);
                    }
                    if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("flame_cloak.level_II.chance")) {
                        player.setFireTicks(SettingsManager.enchant.getInt("flame_cloak.level_II.duration") * 20);
                    }
                    if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && n < SettingsManager.enchant.getInt("flame_cloak.level_III.chance")) {
                        player.setFireTicks(SettingsManager.enchant.getInt("flame_cloak.level_III.duration") * 20);
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}
