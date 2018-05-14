package org.pixeltime.enchantmentsenhance.events.enchantments;

import org.pixeltime.enchantmentsenhance.manager.EnchantmentsManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class MoveEvents implements Listener {
    @EventHandler
    public void onMove(final PlayerMoveEvent playerMoveEvent) {
        final Player player = playerMoveEvent.getPlayer();
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "batvision"));
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                    if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 300, 0));
                    } else {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 300, 0));
                    }
                }
                return;
            } else {
                if ((EnchantmentsManager.bat.contains(player.getName()) && player.getInventory().getHelmet() == null) || !player.getInventory().getHelmet().hasItemMeta() || !player.getInventory().getHelmet().getItemMeta().hasLore() || !player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                    player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    EnchantmentsManager.bat.remove(player.getName());
                    return;
                }
                if (player.getInventory().getHelmet() == null || !player.getInventory().getHelmet().hasItemMeta() || !player.getInventory().getHelmet().getItemMeta().hasLore()) {
                    return;
                }
                if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
                    if (!EnchantmentsManager.bat.contains(player.getName())) {
                        EnchantmentsManager.bat.add(player.getName());
                    }
                }
            }
        } catch (Exception ex) {
        }
        final String translateAlternateColorCodes2 = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "eyepatch"));
        try {
            ItemStack[] armorContents;
            for (int length = (armorContents = player.getInventory().getArmorContents()).length, i = 0; i < length; ++i) {
                final ItemStack itemStack = armorContents[i];
                if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes2) + " I") && player.hasPotionEffect(PotionEffectType.BLINDNESS)) {
                    player.removePotionEffect(PotionEffectType.BLINDNESS);
                }
            }
        } catch (Exception ex2) {
        }
        SettingsManager.lang.getString("enchantments." + "health_boost");
        final String translateAlternateColorCodes3 = ChatColor.translateAlternateColorCodes('&', translateAlternateColorCodes);
        try {
            if ((EnchantmentsManager.health.contains(player.getName()) && player.getInventory().getChestplate() == null) || !player.getInventory().getChestplate().hasItemMeta() || !player.getInventory().getChestplate().getItemMeta().hasLore() || (!player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes3) + " I") && !player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes3) + " II") && !player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes3) + " III"))) {
                player.removePotionEffect(PotionEffectType.HEALTH_BOOST);
                EnchantmentsManager.health.remove(player.getName());
                return;
            }
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes3) + " I")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, SettingsManager.enchant.getInt("health_boost.level_I.potion_lvl") - 1));
                if (!EnchantmentsManager.health.contains(player.getName())) {
                    EnchantmentsManager.health.add(player.getName());
                }
            }
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes3) + " II")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, SettingsManager.enchant.getInt("health_boost.level_II.potion_lvl") - 1));
                if (!EnchantmentsManager.health.contains(player.getName())) {
                    EnchantmentsManager.health.add(player.getName());
                }
            }
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes3) + " III")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, SettingsManager.enchant.getInt("health_boost.level_III.potion_lvl") - 1));
                if (!EnchantmentsManager.health.contains(player.getName())) {
                    EnchantmentsManager.health.add(player.getName());
                }
            }
        } catch (Exception ex3) {
        }
        final String translateAlternateColorCodes4 = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "platemail"));
        final String translateAlternateColorCodes5 = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "reinforced"));
        try {
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes4) + " I")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
                if (!player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes5) + " I") && !player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes5) + " II") && !player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes5) + " III") && !player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes5) + " IV") && !player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes5) + " V")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0));
                }
            }
        } catch (Exception ex4) {
        }
        final String translateAlternateColorCodes6 = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "speed"));
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes6) + " I")) {
                    System.out.println("tt");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, SettingsManager.enchant.getInt("speed.level_I.potion_lvl") - 1));
                }
                if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes6) + " II")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, SettingsManager.enchant.getInt("speed.level_II.potion_lvl") - 1));
                }
                if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes6) + " III")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, SettingsManager.enchant.getInt("speed.level_III.potion_lvl") - 1));
                }
                return;
            }
            if ((EnchantmentsManager.speed.contains(player.getName()) && player.getInventory().getBoots() == null) || !player.getInventory().getBoots().hasItemMeta() || !player.getInventory().getBoots().getItemMeta().hasLore() || (!player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes6) + " I") && !player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes6) + " II") && !player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes6) + " III"))) {
                player.removePotionEffect(PotionEffectType.SPEED);
                EnchantmentsManager.speed.remove(player.getName());
                return;
            }
            if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes6) + " I")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, SettingsManager.enchant.getInt("speed.level_I.potion_lvl") - 1));
                if (!EnchantmentsManager.speed.contains(player.getName())) {
                    EnchantmentsManager.speed.add(player.getName());
                }
            }
            if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes6) + " II")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, SettingsManager.enchant.getInt("speed.level_II.potion_lvl") - 1));
                if (!EnchantmentsManager.speed.contains(player.getName())) {
                    EnchantmentsManager.speed.add(player.getName());
                }
            }
            if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes6) + " III")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, SettingsManager.enchant.getInt("speed.level_III.potion_lvl") - 1));
                if (!EnchantmentsManager.speed.contains(player.getName())) {
                    EnchantmentsManager.speed.add(player.getName());
                }
            }
        } catch (Exception ex5) {
        }
        final String translateAlternateColorCodes7 = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "strength"));
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes7) + " I")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, SettingsManager.enchant.getInt("strength.level_I.potion_lvl") - 1));
                    if (!EnchantmentsManager.strength.contains(player.getName())) {
                        EnchantmentsManager.strength.add(player.getName());
                    }
                }
                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes7) + " II")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, SettingsManager.enchant.getInt("strength.level_II.potion_lvl") - 1));
                    if (!EnchantmentsManager.strength.contains(player.getName())) {
                        EnchantmentsManager.strength.add(player.getName());
                    }
                }
                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes7) + " III")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, SettingsManager.enchant.getInt("strength.level_III.potion_lvl") - 1));
                    if (!EnchantmentsManager.strength.contains(player.getName())) {
                        EnchantmentsManager.strength.add(player.getName());
                    }
                }
                return;
            }
            if ((EnchantmentsManager.strength.contains(player.getName()) && player.getInventory().getChestplate() == null) || !player.getInventory().getChestplate().hasItemMeta() || !player.getInventory().getChestplate().getItemMeta().hasLore() || (!player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes7) + " I") && !player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes7) + " II") && !player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes7) + " III"))) {
                player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                EnchantmentsManager.strength.remove(player.getName());
                return;
            }
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes7) + " I")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, SettingsManager.enchant.getInt("strength.level_I.potion_lvl") - 1));
                if (!EnchantmentsManager.strength.contains(player.getName())) {
                    EnchantmentsManager.strength.add(player.getName());
                }
            }
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes7) + " II")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, SettingsManager.enchant.getInt("strength.level_II.potion_lvl") - 1));
                if (!EnchantmentsManager.strength.contains(player.getName())) {
                    EnchantmentsManager.strength.add(player.getName());
                }
            }
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes7) + " III")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, SettingsManager.enchant.getInt("strength.level_III.potion_lvl") - 1));
                if (!EnchantmentsManager.strength.contains(player.getName())) {
                    EnchantmentsManager.strength.add(player.getName());
                }
            }
        } catch (Exception ex6) {
        }
        final String translateAlternateColorCodes8 = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "swimmer"));
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes8) + " I")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200, SettingsManager.enchant.getInt("swimmer.level_I.potion_lvl") - 1));
                }
                if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes8) + " II")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200, SettingsManager.enchant.getInt("swimmer.level_II.potion_lvl") - 1));
                }
                if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes8) + " III")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200, SettingsManager.enchant.getInt("swimmer.level_III.potion_lvl") - 1));
                }
                return;
            }
            if ((EnchantmentsManager.swimmer.contains(player.getName()) && player.getInventory().getHelmet() == null) || !player.getInventory().getHelmet().hasItemMeta() || !player.getInventory().getHelmet().getItemMeta().hasLore() || (!player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes8) + " I") && !player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes8) + " II") && !player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes8) + " III"))) {
                player.removePotionEffect(PotionEffectType.WATER_BREATHING);
                EnchantmentsManager.swimmer.remove(player.getName());
                return;
            }
            if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes8) + " I")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, SettingsManager.enchant.getInt("swimmer.level_I.potion_lvl") - 1));
                if (!EnchantmentsManager.swimmer.contains(player.getName())) {
                    EnchantmentsManager.swimmer.add(player.getName());
                }
            }
            if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes8) + " II")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, SettingsManager.enchant.getInt("swimmer.level_II.potion_lvl") - 1));
                if (!EnchantmentsManager.swimmer.contains(player.getName())) {
                    EnchantmentsManager.swimmer.add(player.getName());
                }
            }
            if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes8) + " III")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, SettingsManager.enchant.getInt("swimmer.level_III.potion_lvl") - 1));
                if (!EnchantmentsManager.swimmer.contains(player.getName())) {
                    EnchantmentsManager.swimmer.add(player.getName());
                }
            }
        } catch (Exception ex7) {
        }
        final String translateAlternateColorCodes9 = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "molten"));
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes9) + " I")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, SettingsManager.enchant.getInt("molten.level_I.potion_lvl") - 1));
                }
                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes9) + " II")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, SettingsManager.enchant.getInt("molten.level_II.potion_lvl") - 1));
                }
                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes9) + " III")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, SettingsManager.enchant.getInt("nolten.level_III.potion_lvl") - 1));
                }
                return;
            }
            if ((EnchantmentsManager.molten.contains(player.getName()) && player.getInventory().getChestplate() == null) || !player.getInventory().getChestplate().hasItemMeta() || !player.getInventory().getChestplate().getItemMeta().hasLore() || (!player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes9) + " I") && !player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes9) + " II") && !player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes9) + " III"))) {
                player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                EnchantmentsManager.molten.remove(player.getName());
                return;
            }
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes9) + " I")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, SettingsManager.enchant.getInt("molten.level_I.potion_lvl") - 1));
                if (!EnchantmentsManager.molten.contains(player.getName())) {
                    EnchantmentsManager.molten.add(player.getName());
                }
            }
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes9) + " II")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, SettingsManager.enchant.getInt("molten.level_II.potion_lvl") - 1));
                if (!EnchantmentsManager.molten.contains(player.getName())) {
                    EnchantmentsManager.molten.add(player.getName());
                }
            }
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes9) + " III")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, SettingsManager.enchant.getInt("molten.level_III.potion_lvl") - 1));
                if (!EnchantmentsManager.molten.contains(player.getName())) {
                    EnchantmentsManager.molten.add(player.getName());
                }
            }
        } catch (Exception ex8) {
        }
        final String translateAlternateColorCodes10 = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "jump"));
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes10) + " I")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, SettingsManager.enchant.getInt("jump.level_I.potion_lvl") - 1));
                }
                if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes10) + " II")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, SettingsManager.enchant.getInt("jump.level_II.potion_lvl") - 1));
                }
                if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes10) + " III")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, SettingsManager.enchant.getInt("jump.level_III.potion_lvl") - 1));
                }
                return;
            }
            if ((EnchantmentsManager.jump.contains(player.getName()) && player.getInventory().getBoots() == null) || !player.getInventory().getBoots().hasItemMeta() || !player.getInventory().getBoots().getItemMeta().hasLore() || (!player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes10) + " I") && !player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes10) + " II") && !player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes10) + " III"))) {
                player.removePotionEffect(PotionEffectType.JUMP);
                EnchantmentsManager.jump.remove(player.getName());
                return;
            }
            if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes10) + " I")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, SettingsManager.enchant.getInt("jump.level_I.potion_lvl") - 1));
                if (!EnchantmentsManager.jump.contains(player.getName())) {
                    EnchantmentsManager.jump.add(player.getName());
                }
            }
            if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes10) + " II")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, SettingsManager.enchant.getInt("jump.level_II.potion_lvl") - 1));
                if (!EnchantmentsManager.jump.contains(player.getName())) {
                    EnchantmentsManager.jump.add(player.getName());
                }
            }
            if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes10) + " III")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, SettingsManager.enchant.getInt("jump.level_III.potion_lvl") - 1));
                if (!EnchantmentsManager.jump.contains(player.getName())) {
                    EnchantmentsManager.jump.add(player.getName());
                }
            }
        } catch (Exception ex9) {
        }
        final String translateAlternateColorCodes11 = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "haste"));
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes11) + " I")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 0));
                }
                if (player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes11) + " II")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 1));
                }
                if (player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes11) + " III")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 2));
                }
                return;
            }
            if ((EnchantmentsManager.haste.contains(player.getName()) && player.getInventory().getItemInHand() == null) || !player.getInventory().getItemInHand().hasItemMeta() || !player.getInventory().getItemInHand().getItemMeta().hasLore() || (!player.getInventory().getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes11) + " I") && !player.getInventory().getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes11) + " II") && !player.getInventory().getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes11) + " III"))) {
                player.removePotionEffect(PotionEffectType.FAST_DIGGING);
                EnchantmentsManager.haste.remove(player.getName());
                return;
            }
            if (player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes11) + " I")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0));
                if (!EnchantmentsManager.haste.contains(player.getName())) {
                    EnchantmentsManager.haste.add(player.getName());
                }
            }
            if (player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes11) + " II")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
                if (!EnchantmentsManager.haste.contains(player.getName())) {
                    EnchantmentsManager.haste.add(player.getName());
                }
            }
            if (player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes11) + " III")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 2));
                if (!EnchantmentsManager.haste.contains(player.getName())) {
                    EnchantmentsManager.haste.add(player.getName());
                }
            }
        } catch (Exception ex10) {
        }
    }
}
