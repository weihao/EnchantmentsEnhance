package com.github.healpot.plugin.enhancement.me.blackspirit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.github.healpot.plugin.enhancement.me.main.Main;

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

	public int getItemEnchantLevel(Main m, Player player, ItemStack item) {
		if ((getItemEnchantmentType(m, player, item)) != null) {
			return item.getEnchantmentLevel(getItemEnchantmentType(m, player, item));
		} else {
			return 0;
		}
	}

	public boolean getValidationOfItem(Main m, Player player, ItemStack item) {
		if (getItemEnchantmentType(m, player, item) == null) {
			// sendMessage(m.settings.getLang().getString("Config.pluginTag")
			// + m.settings.getLang().getString("Enhance.itemInvalid"), player);
			return false;
		} else if (getItemEnchantLevel(m, player, item) >= 19) {
			// sendMessage(m.settings.getLang().getString("Config.pluginTag")
			// + m.settings.getLang().getString("Enhance.itemMax"), player);
			return false;
		} else {
			return true;
		}
	}

	public void enhanceSuccess(Main m, ItemStack item, Player player, boolean forceEnhanced) {
		Enchantment enchant = getItemEnchantmentType(m, player, item);
		int enchantLevel = getItemEnchantLevel(m, player, item);
		m.renameItem(item, enchantLevel + 1);
		item.addUnsafeEnchantment(enchant, enchantLevel + 1);
		m.compatibility.playsound.playSound(player, "SUCCESS");
		m.spawnFirework.launch(m, player, m.getConfig().getInt("fireworkCount." + enchantLevel),
				m.settings.getConfig().getInt("fireworkRounds." + enchantLevel),
				m.settings.getConfig().getInt("fireworkDelay"));
		if (forceEnhanced) {
			m.sendMessage(m.settings.getLang().getString("Config.pluginTag")
					+ m.settings.getLang().getString("Enhance.forceEnhanceSuccess"), player);
		} else {
			m.failstack.resetLevel(m, player);
			m.sendMessage(m.settings.getLang().getString("Config.pluginTag")
					+ m.settings.getLang().getString("Enhance.enhanceSuccess"), player);
		}
		m.data.addLore(m, item, player, ChatColor.translateAlternateColorCodes('&',
				m.settings.getLang().getString("Lore." + m.settings.getConfig().getString("lore.bound") + "Lore")),
				true);
	}

	public void enhanceFail(Main m, ItemStack item, Player player) {
		Enchantment enchant = getItemEnchantmentType(m, player, item);
		int enchantLevel = getItemEnchantLevel(m, player, item);
		String str = m.settings.getLang().getString("Enhance.enhanceFailed");
		m.compatibility.playsound.playSound(player, "FAILED");
		m.failstack.addLevel(m, player, m.settings.getConfig().getInt("failstackGained." + enchantLevel));
		if (enchantLevel > 15) {
			str += (" " + m.settings.getLang().getString("Enhance.downgraded"));
			m.compatibility.playsound.playSound(player, "DOWNGRADED");
			m.renameItem(item, (enchantLevel - 1));
			item.addUnsafeEnchantment(enchant, enchantLevel - 1);
		}
		m.sendMessage(m.settings.getLang().getString("Config.pluginTag") + str, player);
	}

	public void diceToEnhancement(Main m, ItemStack item, Player player) {
		if (getValidationOfItem(m, player, item)) {
			double random = Math.random();
			double chance;
			int enchantLevel = getItemEnchantLevel(m, player, item);
			chance = m.failstack.getChance(m, player, enchantLevel);
			if (random < chance) {
				if (enchantLevel > 15) {
					m.broadcast.broadcast(m, player, item, enchantLevel, true);
				}
				enhanceSuccess(m, item, player, false);
			} else {
				if (enchantLevel > 15) {
					m.broadcast.broadcast(m, player, item, enchantLevel, false);
				}
				enhanceFail(m, item, player);
			}
		}
	}

	public void forceToEnhancement(Main m, ItemStack item, Player player) {
		if (getValidationOfItem(m, player, item) && m.permissions.commandForce(m, player)) {
			if (this.getItemEnchantLevel(m, player, item) > 15) {
				m.broadcast.broadcast(m, player, item, this.getItemEnchantLevel(m, player, item), true);
			}
			enhanceSuccess(m, item, player, true);
		}
	}

	public void getChance(Main m, ItemStack item, Player player) {
		Enchantment enchant = getItemEnchantmentType(m, player, item);
		if (enchant != null) {
			m.sendMessage(m.settings.getLang().getString("Config.pluginTag")
					+ m.settings.getLang().getString("Enhance.currentFailstack") + m.failstack.getLevel(m, player),
					player);
			String chance = String.format("%.2f",
					m.failstack.getChance(m, player, item.getEnchantmentLevel(enchant)) * 100);
			m.sendMessage(
					m.settings.getLang().getString("Config.pluginTag")
							+ m.settings.getLang().getString("Enhance.successRate").replaceAll("%chance%", chance),
					player);
		} else {
			m.sendMessage(m.settings.getLang().getString("Config.pluginTag")
					+ m.settings.getLang().getString("Enhance.itemInvalid"), player);
		}
	}

	public List<String> getChanceAsList(Main m, ItemStack item, Player player) {
		Enchantment enchant = getItemEnchantmentType(m, player, item);
		ArrayList<String> result = new ArrayList<String>();
		if (enchant != null) {
			String fs = (m.settings.getLang().getString("Enhance.currentFailstack") + m.failstack.getLevel(m, player));
			String placeholder = String.format("%.2f",
					m.failstack.getChance(m, player, item.getEnchantmentLevel(enchant)) * 100);
			String chance = m.settings.getLang().getString("Enhance.successRate").replaceAll("%chance%", placeholder);
			result.add(fs);
			result.add(chance);
			result.add(m.settings.getLang().getString("Menu.lore.stats1"));
			result.add(m.settings.getLang().getString("Menu.lore.stats2"));
			return result;

		} else {
			result.add(m.settings.getLang().getString("Enhance.itemInvalid"));
			return result;
		}
	}
}
