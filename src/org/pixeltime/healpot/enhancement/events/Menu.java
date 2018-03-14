package org.pixeltime.healpot.enhancement.events;

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
import org.pixeltime.healpot.enhancement.events.blackspirit.Enhance;
import org.pixeltime.healpot.enhancement.events.blackspirit.Failstack;
import org.pixeltime.healpot.enhancement.manager.DataManager;
import org.pixeltime.healpot.enhancement.manager.Permissions;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.Main;
import org.pixeltime.healpot.enhancement.util.Util;

public class Menu {
    /**
     * This is the inventory.
     */
    private static Inventory screen = null;
    /**
     * These are buttons.
     */
    private static ItemStack enhance, force, stats, remove, store;


    /**
     * Creates a GUI button made of a wool.
     * 
     * @param dc
     * @param name
     * @return
     */
    private static ItemStack createItem(DyeColor dc, String name) {
        ItemStack i = new Wool(dc).toItemStack(1);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        i.setItemMeta(im);
        return i;
    }


    /**
     * Opens GUI to a player.
     * 
     * @param player
     */
    public static void showEnhancingMenu(Player player) {
        screen = Bukkit.getServer().createInventory(null, 27,
            SettingsManager.lang.getString("Menu.gui.title"));
        createMenu(player);
        player.openInventory(screen);
    }


    /**
     * Creates an enhancement gui.
     * 
     * @param player
     */
    public static void createMenu(Player player) {
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
            + Failstack.getLevel(player));
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
        storeim.setDisplayName(ChatColor.DARK_PURPLE + SettingsManager.lang
            .getString("Menu.gui.store"));
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
        if (Failstack.getLevel(player) != 0) {
            screen.setItem(Util.getSlot(6, 1), store);
        }
        else {
            screen.setItem(Util.getSlot(6, 1), null);
        }
    }


    public static Inventory getScreen() {
        return screen;
    }


    /**
     * Updates the inventory.
     * 
     * @param item
     * @param player
     * @param itemReady
     * @param change
     * @param enhancingItem
     */
    public static void updateInv(
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
            updateFailstack(enhancingItem, player);
            screen.setItem(Util.getSlot(5, 1), Main.compatibility.glow.addGlow(
                stats));
            screen.setItem(Util.getSlot(4, 3), Main.compatibility.glow.addGlow(
                enhance));
            updateForce(enhancingItem, player);
            updateEnhance(enhancingItem, player);
            screen.setItem(Util.getSlot(1, 2), stoneVisualized(Enhance
                .getStoneId(player, enhancingItem, Enhance.getItemEnchantLevel(
                    player, enhancingItem)), player));
            screen.setItem(Util.getSlot(9, 3), remove);
        }
    }


    /**
     * Updates the failstack button's desc in the GUI menu.
     * 
     * @param item
     * @param player
     */
    public static void updateFailstack(ItemStack item, Player player) {
        ItemMeta im = stats.getItemMeta();
        im.setLore(Enhance.getChanceAsList(item, player));
        stats.setItemMeta(im);
        screen.setItem(Util.getSlot(5, 1), Main.compatibility.glow.addGlow(
            stats));
        if (Failstack.getLevel(player) != 0) {
            screen.setItem(Util.getSlot(6, 1), store);
        }
        else {
            screen.setItem(Util.getSlot(6, 1), null);
        }
    }


    /**
     * Updates the force button's desc in the GUI menu.
     * 
     * @param item
     * @param player
     */
    public static void updateForce(ItemStack item, Player player) {
        if (Permissions.commandEnhance(player)) {
            int enchantLevel = Enhance.getItemEnchantLevel(player, item);
            int stoneId = Enhance.getStoneId(player, item, enchantLevel);
            int costToEnhance = DataManager.costToForceEnchant[enchantLevel];
            if (DataManager.maximumFailstackApplied[enchantLevel] == -1
                || DataManager.costToForceEnchant[enchantLevel] == -1) {
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
                screen.setItem(Util.getSlot(6, 3), Main.compatibility.glow
                    .addGlow(force));
            }
        }
        else {
            Util.sendMessage(SettingsManager.config.getString("Config.noPerm"),
                player);
        }
    }


    /**
     * Updates the enhance button's desc in the GUI menu.
     * 
     * @param item
     * @param player
     */
    public static void updateEnhance(ItemStack item, Player player) {
        ItemMeta im = enhance.getItemMeta();
        List<String> update = new ArrayList<String>();
        update.add(Util.toColor(SettingsManager.lang.getString(
            "Menu.lore.ifSuccess")));
        if (DataManager.baseChance[Enhance.getItemEnchantLevel(
            player, item)] != 100) {
            update.add(Util.toColor(SettingsManager.lang.getString(
                "Menu.lore.ifFail")));
        }
        if (Enhance.getItemEnchantLevel(player,
            item) > DataManager.downgradePhase) {
            update.add(Util.toColor(SettingsManager.lang.getString(
                "Menu.lore.ifDowngrade")));
        }
        im.setLore(update);
        enhance.setItemMeta(im);
        screen.setItem(Util.getSlot(4, 3), Main.compatibility.glow.addGlow(
            enhance));
    }


    /**
     * Displays enhancement stone as a itemstack on GUI
     * 
     * @param stoneId
     * @param player
     * @return
     */
    public static ItemStack stoneVisualized(int stoneId, Player player) {
        ItemStack stone = new ItemStack(Util.stoneTypes[stoneId]);
        ItemMeta im = stone.getItemMeta();
        im.setDisplayName(Util.toColor(SettingsManager.lang.getString("Item."
            + stoneId)));
        List<String> lore = new ArrayList<String>();
        lore.add(Util.toColor(Main.InventoryText.getOneStoneCountAsString(
            player, stoneId)));
        im.setLore(lore);
        stone.setItemMeta(im);
        return stone;
    }
}
