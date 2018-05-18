package org.pixeltime.enchantmentsenhance.event.enchantment;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Wolves implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "wolves"));
        if (entityDamageByEntityEvent.getDamager() instanceof Player && entityDamageByEntityEvent.getEntity() instanceof Player) {
            try {
                if (entityDamageByEntityEvent.isCancelled()) {
                    return;
                }
                final Player target = (Player) entityDamageByEntityEvent.getDamager();
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(entityDamageByEntityEvent.getEntity().getWorld()).getApplicableRegions(entityDamageByEntityEvent.getEntity().getLocation()).queryState(null, new StateFlag[]{DefaultFlag.PVP}) == StateFlag.State.DENY) {
                    return;
                }
                final Player owner = (Player) entityDamageByEntityEvent.getEntity();
                final ItemStack[] armorContents = owner.getInventory().getArmorContents();
                final int n = (int) (Math.random() * 100.0);
                ItemStack[] array;
                for (int length = (array = armorContents).length, i = 0; i < length; ++i) {
                    final ItemStack itemStack = array[i];
                    if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("wolves.level_I.chance")) {
                        for (int j = 0; j < SettingsManager.enchant.getInt("wolves.level_I.wolves"); ++j) {
                            final Wolf wolf = (Wolf) target.getWorld().spawn(target.getLocation(), (Class) Wolf.class);
                            wolf.setTamed(true);
                            wolf.setOwner(owner);
                            wolf.setAdult();
                            wolf.setAngry(true);
                            wolf.setTarget(target);
                        }
                    }
                    if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("wolves.level_II.chance")) {
                        for (int k = 0; k < SettingsManager.enchant.getInt("wolves.level_II.wolves"); ++k) {
                            final Wolf wolf2 = (Wolf) target.getWorld().spawn(target.getLocation(), (Class) Wolf.class);
                            wolf2.setTamed(true);
                            wolf2.setOwner(owner);
                            wolf2.setAdult();
                            wolf2.setAngry(true);
                            wolf2.setTarget(target);
                        }
                    }
                    if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains("§7Wolves III") && n < SettingsManager.enchant.getInt("wolves.level_III.chance")) {
                        for (int l = 0; l < SettingsManager.enchant.getInt("wolves.level_III.wolves"); ++l) {
                            final Wolf wolf3 = (Wolf) target.getWorld().spawn(target.getLocation(), (Class) Wolf.class);
                            wolf3.setTamed(true);
                            wolf3.setOwner(owner);
                            wolf3.setAdult();
                            wolf3.setAngry(true);
                            wolf3.setTarget(target);
                        }
                    }
                    if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains("§7Wolves IV") && n < SettingsManager.enchant.getInt("wolves.level_IV.chance")) {
                        for (int n2 = 0; n2 < SettingsManager.enchant.getInt("wolves.level_IV.wolves"); ++n2) {
                            final Wolf wolf4 = (Wolf) target.getWorld().spawn(target.getLocation(), (Class) Wolf.class);
                            wolf4.setTamed(true);
                            wolf4.setOwner(owner);
                            wolf4.setAdult();
                            wolf4.setAngry(true);
                            wolf4.setTarget(target);
                        }
                    }
                    if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains("§7Wolves V") && n < SettingsManager.enchant.getInt("wolves.level_V.chance")) {
                        for (int n3 = 0; n3 < SettingsManager.enchant.getInt("wolves.level_V.wolves"); ++n3) {
                            final Wolf wolf5 = (Wolf) target.getWorld().spawn(target.getLocation(), (Class) Wolf.class);
                            wolf5.setTamed(true);
                            wolf5.setOwner(owner);
                            wolf5.setAdult();
                            wolf5.setAngry(true);
                            wolf5.setTarget(target);
                        }
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}
