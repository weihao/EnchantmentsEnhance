package org.pixeltime.enchantmentsenhance.event.enchantment;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.pixeltime.enchantmentsenhance.manager.EnchantmentsManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.events.ArmorEquipEvent;

public class Reinforced implements Listener {
    @EventHandler
    public void onEquip(final ArmorEquipEvent armorEquipEvent) {
        final Player player = armorEquipEvent.getPlayer();
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "reinforced"));
        try {
            if (EnchantmentsManager.chestplates.contains(armorEquipEvent.getNewArmorPiece().getType())) {
                final ItemStack newArmorPiece = armorEquipEvent.getNewArmorPiece();
                if (newArmorPiece.getItemMeta().hasLore()) {
                    if (newArmorPiece.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && player.getHealth() <= SettingsManager.enchant.getInt("reinforced.level_I.required-health")) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, SettingsManager.enchant.getInt("reinforced.level_I.duration") * 20, SettingsManager.enchant.getInt("reinforced.level_I.potion_lvl") - 1));
                    }
                    if (newArmorPiece.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && player.getHealth() <= SettingsManager.enchant.getInt("reinforced.level_II.required-health")) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, SettingsManager.enchant.getInt("reinforced.level_II.duration") * 20, SettingsManager.enchant.getInt("reinforced.level_II.potion_lvl") - 1));
                    }
                    if (newArmorPiece.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && player.getHealth() <= SettingsManager.enchant.getInt("reinforced.level_III.required-health")) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, SettingsManager.enchant.getInt("reinforced.level_III.duration") * 20, SettingsManager.enchant.getInt("reinforced.level_III.potion_lvl") - 1));
                    }
                    if (newArmorPiece.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " IV") && player.getHealth() <= SettingsManager.enchant.getInt("reinforced.level_IV.required-health")) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, SettingsManager.enchant.getInt("reinforced.level_IV.duration") * 20, SettingsManager.enchant.getInt("reinforced.level_IV.potion_lvl") - 1));
                    }
                    if (newArmorPiece.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " V") && player.getHealth() <= SettingsManager.enchant.getInt("reinforced.level_V.required-health")) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, SettingsManager.enchant.getInt("reinforced.level_V.duration") * 20, SettingsManager.enchant.getInt("reinforced.level_V.potion_lvl") - 1));
                    }
                }
            }
        } catch (Exception ex) {
        }
    }
}
