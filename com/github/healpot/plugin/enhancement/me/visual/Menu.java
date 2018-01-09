package com.github.healpot.plugin.enhancement.me.visual;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

import com.github.healpot.plugin.enhancement.me.main.Main;

public class Menu {

	private Inventory screen = null;
	private ItemStack enhance, force, stats, remove, store;

	private ItemStack createItem(DyeColor dc, String name) {
		ItemStack i = new Wool(dc).toItemStack(1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		i.setItemMeta(im);
		return i;
	}

	public void showEnhancingMenu(Main m, Player player, ItemStack item) {
		screen = Bukkit.getServer().createInventory(null, 27, m.settings.getLang().getString("Menu.gui.title"));
		createMenu(m, player);
		player.openInventory(screen);
	}

	public void createMenu(Main m, Player player) {
		screen.clear();
		enhance = createItem(DyeColor.YELLOW, ChatColor.YELLOW + m.settings.getLang().getString("Menu.gui.enhance"));
		ItemMeta enhanceim = enhance.getItemMeta();
		List<String> enhanceStr = new ArrayList<String>();
		enhanceStr.add(m.toColor(m.settings.getLang().getString("Menu.lore.ifSuccess")));
		enhanceStr.add(m.toColor(m.settings.getLang().getString("Menu.lore.ifFail")));
		enhanceStr.add(m.toColor(m.settings.getLang().getString("Menu.lore.ifDowngrade")));
		enhanceStr.add(m.toColor(m.settings.getLang().getString("Menu.lore.ifDestroy")));
		enhanceim.setLore(enhanceStr);
		enhance.setItemMeta(enhanceim);

		force = createItem(DyeColor.PURPLE, ChatColor.RED + m.settings.getLang().getString("Menu.gui.force"));
		ItemMeta forceim = force.getItemMeta();
		List<String> forceStr = new ArrayList<String>();
		forceStr.add(m.toColor(m.settings.getLang().getString("Menu.lore.force1")));
		forceStr.add(m.toColor(m.settings.getLang().getString("Menu.lore.force2")));
		forceim.setLore(forceStr);
		force.setItemMeta(forceim);

		stats = createItem(DyeColor.LIGHT_BLUE, ChatColor.AQUA + m.settings.getLang().getString("Menu.gui.stats"));
		ItemMeta statsim = stats.getItemMeta();
		List<String> statsStr = new ArrayList<String>();
		String fs = (m.settings.getLang().getString("Enhance.currentFailstack") + m.failstack.getLevel(m, player));
		statsStr.add(m.toColor(fs));
		statsStr.add(m.toColor(m.settings.getLang().getString("Menu.lore.stats1")));
		statsStr.add(m.toColor(m.settings.getLang().getString("Menu.lore.stats2")));
		statsim.setLore(statsStr);
		stats.setItemMeta(statsim);

		remove = createItem(DyeColor.RED, ChatColor.RED + m.settings.getLang().getString("Menu.gui.remove"));
		ItemMeta removeim = remove.getItemMeta();
		List<String> removeStr = new ArrayList<String>();
		removeStr.add(m.toColor(m.settings.getLang().getString("Menu.lore.remove")));
		removeim.setLore(removeStr);
		remove.setItemMeta(removeim);

		store = new ItemStack(Material.BOOK_AND_QUILL);
		ItemMeta storeim = store.getItemMeta();
		storeim.setDisplayName(m.settings.getLang().getString("Menu.gui.store"));
		List<String> storeStr = new ArrayList<String>();
		storeStr.add(m.toColor(m.settings.getLang().getString("Menu.lore.store1")));
		storeStr.add(m.toColor(m.settings.getLang().getString("Menu.lore.store2")));
		storeim.setLore(storeStr);
		store.setItemMeta(storeim);

		screen.setItem(getSlot(5, 1), stats);
		screen.setItem(getSlot(4, 3), enhance);
		screen.setItem(getSlot(6, 3), force);
		if (m.failstack.getLevel(m, player) != 0) {
			screen.setItem(getSlot(6, 1), store);
		} else {
			screen.setItem(getSlot(6, 1), null);
		}
	}

	public static int getSlot(int x, int y) {
		return (y * 9) - (9 - x) - 1;
	}

	public Inventory getScreen() {
		return screen;
	}

	public void updateInv(Main m, ItemStack item, Player player, boolean itemReady, boolean change,
			ItemStack enhancingItem) {
		if (itemReady) {
			if (change) {
				screen.setItem(getSlot(9, 2), item);
			} else {
				screen.setItem(getSlot(9, 2), enhancingItem);
			}
			updateFailstack(m, enhancingItem, player);
			screen.setItem(getSlot(5, 1), m.compatibility.glow.addGlow(stats));
			screen.setItem(getSlot(4, 3), m.compatibility.glow.addGlow(enhance));
			if (m.permissions.commandForce(m, player)) {
				screen.setItem(getSlot(6, 3), m.compatibility.glow.addGlow(force));
			} else {
				screen.setItem(getSlot(6, 3), null);
			}
			updateEnhance(m, enhancingItem, player);
			screen.setItem(getSlot(1, 2), stoneVisualized(m, m.enhance.getStoneId(m, player, enhancingItem,
					m.enhance.getItemEnchantLevel(m, player, enhancingItem)), player));
			screen.setItem(getSlot(9, 3), remove);
		}
	}

	public void updateFailstack(Main m, ItemStack item, Player player) {
		ItemMeta im = stats.getItemMeta();
		im.setLore(m.enhance.getChanceAsList(m, item, player));
		stats.setItemMeta(im);
		screen.setItem(getSlot(5, 1), m.compatibility.glow.addGlow(stats));
		if (m.failstack.getLevel(m, player) != 0) {
			screen.setItem(getSlot(6, 1), store);
		} else {
			screen.setItem(getSlot(6, 1), null);
		}
	}

	public void updateEnhance(Main m, ItemStack item, Player player) {
		ItemMeta im = enhance.getItemMeta();
		List<String> update = new ArrayList<String>();
		update.add(m.toColor(m.settings.getLang().getString("Menu.lore.ifSuccess")));
		update.add(m.toColor(m.settings.getLang().getString("Menu.lore.ifFail")));
		if (m.enhance.getItemEnchantLevel(m, player, item) > 16) {
			update.add(m.toColor(m.settings.getLang().getString("Menu.lore.ifDowngrade")));
		}
		im.setLore(update);
		enhance.setItemMeta(im);
		screen.setItem(getSlot(4, 3), m.compatibility.glow.addGlow(enhance));
	}

	public ItemStack stoneVisualized(Main m, int stoneId, Player player) {
		Material[] stoneType = new Material[] { Material.GHAST_TEAR, Material.GOLD_NUGGET, Material.SUGAR,
				Material.GLOWSTONE_DUST };
		ItemStack stone = new ItemStack(stoneType[stoneId]);
		ItemMeta im = stone.getItemMeta();
		im.setDisplayName(m.settings.getLang().getString("Item." + stoneId));
		List<String> lore = new ArrayList<String>();
		lore.add(m.toColor(m.inventory.getOneStoneCountAsString(m, player, stoneId)));
		im.setLore(lore);
		stone.setItemMeta(im);
		return stone;
	}
}