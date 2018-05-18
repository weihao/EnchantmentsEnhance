package org.pixeltime.enchantmentsenhance.event.enchantment;

import org.apache.commons.lang3.ArrayUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.pixeltime.enchantmentsenhance.manager.EnchantmentsManager;
import org.pixeltime.enchantmentsenhance.manager.IM;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Batvision implements Listener {
    @EventHandler
    public void onWalk(final PlayerMoveEvent playerMoveEvent) {
        final Player player = playerMoveEvent.getPlayer();
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "batvision"));
        final ItemStack[] armorContents = ArrayUtils.addAll(player.getInventory().getArmorContents(), IM.getAccessorySlots(player));
        for (int i = 0; i < armorContents.length; ++i) {
            try {
                if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                    if (armorContents[i].getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                        if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 300, 0));
                        } else {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 300, 0));
                        }
                    }
                } else {
                    if ((EnchantmentsManager.bat.contains(player.getName()) && armorContents[i] == null) || !armorContents[i].hasItemMeta() || !armorContents[i].getItemMeta().hasLore() || !armorContents[i].getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                        EnchantmentsManager.bat.remove(player.getName());
                        return;
                    }
                    if (armorContents[i] == null || !armorContents[i].hasItemMeta() || !armorContents[i].getItemMeta().hasLore()) {
                        return;
                    }
                    if (armorContents[i] != null && armorContents[i].getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
                        if (!EnchantmentsManager.bat.contains(player.getName())) {
                            EnchantmentsManager.bat.add(player.getName());
                        }
                    }

                }
            } catch (Exception ex) {
            }
        }
    }
}
