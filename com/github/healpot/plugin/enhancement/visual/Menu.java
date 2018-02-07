package com.github.healpot.plugin.enhancement.visual;

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
import com.github.healpot.plugin.enhancement.main.Main;
import com.github.healpot.plugin.enhancement.main.SettingsManager;
import com.github.healpot.plugin.enhancement.main.util.Util;

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
        screen = Bukkit.getServer().createInventory(null, 27,
            SettingsManager.lang.getString("Menu.gui.title"));
        createMenu(m, player);
        player.openInventory(screen);
    }


    public void createMenu(Main m, Player player) {
        screen.clear();
        enhance = createItem(DyeColor.YELLOW, ChatColor.YELLOW
            + SettingsManager.lang.getString("Menu.gui.enhance"));
        ItemMeta enhanceim = enhance.getItemMeta();
        List<String> enhanceStr = new ArrayList<String>();
        enhanceStr.add(Util.toColor(SettingsManager.lang.getString(
            "Menu.lore.ifSuccess")));
        enhanceStr.add(Util.toColor(SettingsManager.lang.getString(
            "Menu.lore.ifFail")));
        enhanceStr.add(Util.toColor(SettingsManager.lang.getString(
            "Menu.lore.ifDowngrade")));
        enhanceStr.add(Util.toColor(SettingsManager.lang.getString(
            "Menu.lore.ifDestroy")));
        enhanceim.setLore(enhanceStr);
        enhance.setItemMeta(enhanceim);

        force = createItem(DyeColor.PURPLE, ChatColor.RED + SettingsManager.lang
            .getString("Menu.gui.force"));
        ItemMeta forceim = force.getItemMeta();
        List<String> forceStr = new ArrayList<String>();
        forceStr.add(Util.toColor(SettingsManager.lang.getString(
            "Menu.lore.force1")));
        forceim.setLore(forceStr);
        force.setItemMeta(forceim);

        stats = createItem(DyeColor.LIGHT_BLUE, ChatColor.AQUA
            + SettingsManager.lang.getString("Menu.gui.stats"));
        ItemMeta statsim = stats.getItemMeta();
        List<String> statsStr = new ArrayList<String>();
        String fs = (SettingsManager.lang.getString("Enhance.currentFailstack")
            + m.failstack.getLevel(m, player));
        statsStr.add(Util.toColor(fs));
        statsStr.add(Util.toColor(SettingsManager.lang.getString(
            "Menu.lore.stats1")));
        statsStr.add(Util.toColor(SettingsManager.lang.getString(
            "Menu.lore.stats2")));
        statsim.setLore(statsStr);
        stats.setItemMeta(statsim);

        remove = createItem(DyeColor.RED, ChatColor.RED + SettingsManager.lang
            .getString("Menu.gui.remove"));
        ItemMeta removeim = remove.getItemMeta();
        List<String> removeStr = new ArrayList<String>();
        removeStr.add(Util.toColor(SettingsManager.lang.getString(
            "Menu.lore.remove")));
        removeim.setLore(removeStr);
        remove.setItemMeta(removeim);

        store = new ItemStack(Material.BOOK_AND_QUILL);
        ItemMeta storeim = store.getItemMeta();
        storeim.setDisplayName(SettingsManager.lang.getString(
            "Menu.gui.store"));
        List<String> storeStr = new ArrayList<String>();
        storeStr.add(Util.toColor(SettingsManager.lang.getString(
            "Menu.lore.store1")));
        storeStr.add(Util.toColor(SettingsManager.lang.getString(
            "Menu.lore.store2")));
        storeim.setLore(storeStr);
        store.setItemMeta(storeim);

        screen.setItem(Util.getSlot(5, 1), stats);
        screen.setItem(Util.getSlot(4, 3), enhance);
        screen.setItem(Util.getSlot(6, 3), force);
        if (m.failstack.getLevel(m, player) != 0) {
            screen.setItem(Util.getSlot(6, 1), store);
        }
        else {
            screen.setItem(Util.getSlot(6, 1), null);
        }
    }


    public Inventory getScreen() {
        return screen;
    }


    public void updateInv(
        Main m,
        ItemStack item,
        Player player,
        boolean itemReady,
        boolean change,
        ItemStack enhancingItem) {
        if (itemReady) {
            if (change) {
                screen.setItem(Util.getSlot(9, 2), item);
            }
            else {
                screen.setItem(Util.getSlot(9, 2), enhancingItem);
            }
            updateFailstack(m, enhancingItem, player);
            screen.setItem(Util.getSlot(5, 1), m.compatibility.glow.addGlow(
                stats));
            screen.setItem(Util.getSlot(4, 3), m.compatibility.glow.addGlow(
                enhance));
            updateForce(m, enhancingItem, player);
            updateEnhance(m, enhancingItem, player);
            screen.setItem(Util.getSlot(1, 2), stoneVisualized(m, m.enhance
                .getStoneId(m, player, enhancingItem, m.enhance
                    .getItemEnchantLevel(m, player, enhancingItem)), player));
            screen.setItem(Util.getSlot(9, 3), remove);
        }
    }


    public void updateFailstack(Main m, ItemStack item, Player player) {
        ItemMeta im = stats.getItemMeta();
        im.setLore(m.enhance.getChanceAsList(m, item, player));
        stats.setItemMeta(im);
        screen.setItem(Util.getSlot(5, 1), m.compatibility.glow.addGlow(stats));
        if (m.failstack.getLevel(m, player) != 0) {
            screen.setItem(Util.getSlot(6, 1), store);
        }
        else {
            screen.setItem(Util.getSlot(6, 1), null);
        }
    }


    public void updateForce(Main m, ItemStack item, Player player) {
        if (m.permissions.commandForce(m, player)) {
            int enchantLevel = m.enhance.getItemEnchantLevel(m, player, item);
            int stoneId = m.enhance.getStoneId(m, player, item, enchantLevel);
            int costToEnhance = SettingsManager.config.getInt("costToForce."
                + enchantLevel);
            if (enchantLevel < 7 || enchantLevel > 16) {
                screen.setItem(Util.getSlot(6, 3), null);
            }
            else {
                ItemMeta im = force.getItemMeta();
                List<String> update = new ArrayList<String>();
                update.add(Util.toColor(SettingsManager.lang.getString(
                    "Menu.lore.force1")));
                update.add(Util.toColor(SettingsManager.lang.getString(
                    "Menu.lore.force2").replaceAll("%COUNT%", Integer.toString(
                        costToEnhance)).replaceAll("%ITEM%",
                            SettingsManager.lang.getString("Item."
                                + stoneId))));
                im.setLore(update);
                force.setItemMeta(im);
                screen.setItem(Util.getSlot(6, 3), m.compatibility.glow.addGlow(
                    force));
            }
        }
        else {
            Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
                + SettingsManager.config.getString("Config.noPerm"), player);
        }
    }


    public void updateEnhance(Main m, ItemStack item, Player player) {
        ItemMeta im = enhance.getItemMeta();
        List<String> update = new ArrayList<String>();
        update.add(Util.toColor(SettingsManager.lang.getString(
            "Menu.lore.ifSuccess")));
        update.add(Util.toColor(SettingsManager.lang.getString(
            "Menu.lore.ifFail")));
        if (m.enhance.getItemEnchantLevel(m, player, item) > 15) {
            update.add(Util.toColor(SettingsManager.lang.getString(
                "Menu.lore.ifDowngrade")));
        }
        im.setLore(update);
        enhance.setItemMeta(im);
        screen.setItem(Util.getSlot(4, 3), m.compatibility.glow.addGlow(
            enhance));
    }


    public ItemStack stoneVisualized(Main m, int stoneId, Player player) {
        ItemStack stone = new ItemStack(Util.stoneTypes[stoneId]);
        ItemMeta im = stone.getItemMeta();
        im.setDisplayName(Util.toColor(SettingsManager.lang.getString("Item."
            + stoneId)));
        List<String> lore = new ArrayList<String>();
        lore.add(Util.toColor(m.inventory.getOneStoneCountAsString(m, player,
            stoneId)));
        im.setLore(lore);
        stone.setItemMeta(im);
        return stone;
    }
}
