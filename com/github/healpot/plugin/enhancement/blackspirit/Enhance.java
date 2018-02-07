package com.github.healpot.plugin.enhancement.blackspirit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.github.healpot.plugin.enhancement.main.Main;
import com.github.healpot.plugin.enhancement.main.SettingsManager;
import com.github.healpot.plugin.enhancement.main.util.Util;
import com.github.healpot.plugin.enhancement.player.Inventory;

public class Enhance {

	public Enchantment getItemEnchantmentType(Main m, Player player, ItemStack item) {
		if ((item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.DIAMOND_AXE)) {
			return Enchantment.DAMAGE_ALL;
		} else if ((item.getType() == Material.DIAMOND_CHESTPLATE || item.getType() == Material.DIAMOND_HELMET
				|| item.getType() == Material.DIAMOND_LEGGINGS || item.getType() == Material.DIAMOND_BOOTS)) {
			return Enchantment.PROTECTION_ENVIRONMENTAL;
		}
		return null;
	}

	public int getStoneId(Main m, Player player, ItemStack item, int level) {
		if ((item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.DIAMOND_AXE)) {
			if (level > 13) {
				return 2;
			} else {
				return 0;
			}
		} else if ((item.getType() == Material.DIAMOND_CHESTPLATE || item.getType() == Material.DIAMOND_HELMET
				|| item.getType() == Material.DIAMOND_LEGGINGS || item.getType() == Material.DIAMOND_BOOTS)) {
			if (level > 13) {
				return 3;
			} else {
				return 1;
			}
		}
		return -1;
	}

	public int getItemEnchantLevel(Main m, Player player, ItemStack item) {
		if ((getItemEnchantmentType(m, player, item)) != null) {
			return item.getEnchantmentLevel(getItemEnchantmentType(m, player, item));
		} else {
			return 0;
		}
	}

	public boolean getValidationOfItem(Main m, Player player, ItemStack item) {
		if (getItemEnchantmentType(m, player, item) == null) {
			// sendMessage(SettingsManager.lang.getString("Config.pluginTag")
			// + SettingsManager.lang.getString("Enhance.itemInvalid"), player);
			return false;
		} else if (getItemEnchantLevel(m, player, item) >= 19) {
			// sendMessage(SettingsManager.lang.getString("Config.pluginTag")
			// + SettingsManager.lang.getString("Enhance.itemMax"), player);
			return false;
		} else {
			return true;
		}
	}

	public void enhanceSuccess(Main m, ItemStack item, Player player, boolean forceEnhanced) {
		Enchantment enchant = getItemEnchantmentType(m, player, item);
		int enchantLevel = getItemEnchantLevel(m, player, item) + 1;
		item.addUnsafeEnchantment(enchant, enchantLevel);
		Util.renameItem(item, enchantLevel);
		m.compatibility.playsound.playSound(player, "SUCCESS");
		m.spawnFirework.launch(m, player, m.getConfig().getInt("fireworkCount." + enchantLevel),
				SettingsManager.config.getInt("fireworkRounds." + enchantLevel),
				SettingsManager.config.getInt("fireworkDelay"));
		if (forceEnhanced) {
			Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
					+ SettingsManager.lang.getString("Enhance.forceEnhanceSuccess"), player);
		} else {
			m.failstack.resetLevel(m, player);
			Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
					+ SettingsManager.lang.getString("Enhance.enhanceSuccess"), player);
		}
		m.data.addLore(m, item, player, ChatColor.translateAlternateColorCodes('&',
				SettingsManager.lang.getString("Lore." + SettingsManager.config.getString("lore.bound") + "Lore")),
				true);
	}

	public void enhanceFail(Main m, ItemStack item, Player player) {
		Enchantment enchant = getItemEnchantmentType(m, player, item);
		int enchantLevel = getItemEnchantLevel(m, player, item);
		String str = SettingsManager.lang.getString("Enhance.enhanceFailed");
		m.compatibility.playsound.playSound(player, "FAILED");
		m.failstack.addLevel(m, player, SettingsManager.config.getInt("failstackGained." + enchantLevel));
		if (enchantLevel > 15) {
			str += (" " + SettingsManager.lang.getString("Enhance.downgraded"));
			m.compatibility.playsound.playSound(player, "DOWNGRADED");
			item.addUnsafeEnchantment(enchant, enchantLevel - 1);
			Util.renameItem(item, (enchantLevel - 1));
		}
		Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag") + str, player);
	}

	public void diceToEnhancement(Main m, ItemStack item, Player player) {
		if (getValidationOfItem(m, player, item)) {
			int enchantLevel = getItemEnchantLevel(m, player, item);
			int stoneId = getStoneId(m, player, item, enchantLevel);
			if (Inventory.getLevel(m, stoneId, player) - 1 >= 0) {
			    Inventory.addLevel(m, player, stoneId, -1);
				Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag") + SettingsManager.lang
						.getString("Item.use").replaceAll("%ITEM%", SettingsManager.lang.getString("Item." + stoneId)),
						player);
				double random = Math.random();
				double chance = m.failstack.getChance(m, player, enchantLevel);
				if (enchantLevel > 15) {
					m.broadcast.broadcast(m, player, item, enchantLevel, random < chance);
				}
				if (random < chance) {
					enhanceSuccess(m, item, player, false);
				} else {
					enhanceFail(m, item, player);
				}
			} else {
				Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
						+ SettingsManager.lang.getString("Item.noItem").replaceAll("%STONE%",
								SettingsManager.lang.getString("Item." + stoneId)),
						player);
			}
		} else {
			Util.sendMessage(
					SettingsManager.lang.getString("Config.pluginTag") + SettingsManager.lang.getString("Item.invalid"),
					player);
		}
	}

	public void forceToEnhancement(Main m, ItemStack item, Player player) {
		if (getValidationOfItem(m, player, item) && m.permissions.commandForce(m, player)) {
			int enchantLevel = getItemEnchantLevel(m, player, item);
			int stoneId = getStoneId(m, player, item, enchantLevel);
			int costToEnhance = SettingsManager.config.getInt("costToForce." + enchantLevel);
			if (Inventory.getLevel(m, stoneId, player) - costToEnhance > 0) {
				Inventory.addLevel(m, player, stoneId, -costToEnhance);
				enhanceSuccess(m, item, player, true);
				if (enchantLevel > 15) {
					m.broadcast.broadcast(m, player, item, enchantLevel, true);
				}
			} else {
				Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
						+ SettingsManager.lang.getString("Item.noItem").replaceAll("%STONE%",
								SettingsManager.lang.getString("Item." + stoneId)),
						player);
			}
		} else {
			Util.sendMessage(
					SettingsManager.lang.getString("Config.pluginTag") + SettingsManager.lang.getString("Item.invalid"),
					player);
		}
	}

	public void getChance(Main m, ItemStack item, Player player) {
		Enchantment enchant = getItemEnchantmentType(m, player, item);
		if (enchant != null) {
			Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
					+ SettingsManager.lang.getString("Enhance.currentFailstack") + m.failstack.getLevel(m, player),
					player);
			String chance = String.format("%.2f",
					m.failstack.getChance(m, player, item.getEnchantmentLevel(enchant)) * 100);
			Util.sendMessage(
					SettingsManager.lang.getString("Config.pluginTag")
							+ SettingsManager.lang.getString("Enhance.successRate").replaceAll("%chance%", chance),
					player);
		} else {
			Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
					+ SettingsManager.lang.getString("Enhance.itemInvalid"), player);
		}
	}

	public List<String> getChanceAsList(Main m, ItemStack item, Player player) {
		Enchantment enchant = getItemEnchantmentType(m, player, item);
		ArrayList<String> result = new ArrayList<String>();
		if (enchant != null) {
			String fs = (SettingsManager.lang.getString("Enhance.currentFailstack") + m.failstack.getLevel(m, player));
			String placeholder = String.format("%.2f",
					m.failstack.getChance(m, player, item.getEnchantmentLevel(enchant)) * 100);
			String chance = SettingsManager.lang.getString("Enhance.successRate").replaceAll("%chance%", placeholder);
			result.add(Util.toColor(fs));
			result.add(Util.toColor(chance));
			result.add(Util.toColor(SettingsManager.lang.getString("Menu.lore.stats1")));
			result.add(Util.toColor(SettingsManager.lang.getString("Menu.lore.stats2")));
			return result;

		} else {
			result.add(SettingsManager.lang.getString("Enhance.itemInvalid"));
			return result;
		}
	}
}
