package org.pixeltime.enchantmentsenhance.event.enchantment;

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

public class Shadowstep implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.getEntity() instanceof Player && entityDamageByEntityEvent.getDamager() instanceof Player) {
            final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "shadowstep"));
            try {
                final Player player = (Player) entityDamageByEntityEvent.getEntity();
                final Player player2 = (Player) entityDamageByEntityEvent.getDamager();
                if (entityDamageByEntityEvent.isCancelled()) {
                    return;
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).queryState(null, new StateFlag[]{DefaultFlag.PVP}) == StateFlag.State.DENY) {
                    return;
                }
                final int n = (int) (Math.random() * 100.0);
                ItemStack[] armorContents = player.getInventory().getArmorContents();
                for (int length = armorContents.length, i = 0; i < length; ++i) {
                    final ItemStack itemStack = armorContents[i];
                    if (itemStack != null) {
                        if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("shadowstep.level_I.chance")) {
                            player.teleport(player2.getLocation().add(player2.getLocation().getDirection().multiply(SettingsManager.enchant.getInt("shadowstep.level_I.distance") * -1.0)));
                        }
                        if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("shadowstep.level_II.chance")) {
                            player.teleport(player2.getLocation().add(player2.getLocation().getDirection().multiply(SettingsManager.enchant.getInt("shadowstep.level_II.distance") * -1.0)));
                        }
                        if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && n < SettingsManager.enchant.getInt("shadowstep.level_III.chance")) {
                            player.teleport(player2.getLocation().add(player2.getLocation().getDirection().multiply(SettingsManager.enchant.getInt("shadowstep.level_III.distance") * -1.0)));
                        }
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}
