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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Paralyze implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "paralyze"));
        if (entityDamageByEntityEvent.getEntity() instanceof Player && entityDamageByEntityEvent.getDamager() instanceof Player) {
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
                        if (itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("paralyze.level_I.chance")) {
                            player2.addPotionEffect(new PotionEffect(PotionEffectType.getById(4), SettingsManager.enchant.getInt("paralyze.level_I.duration") * 20, SettingsManager.enchant.getInt("paralyze.level_I.potion_lvl") - 1));
                        }
                        if (itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("paralyze.level_II.chance")) {
                            player2.addPotionEffect(new PotionEffect(PotionEffectType.getById(4), SettingsManager.enchant.getInt("paralyze.level_II.duration") * 20, SettingsManager.enchant.getInt("paralyze.level_II.potion_lvl") - 1));
                        }
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}
