package org.pixeltime.enchantmentsenhance.gui.menu;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.event.blacksmith.SecretBook;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Enhance;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Failstack;
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract;
import org.pixeltime.enchantmentsenhance.gui.MenuCoord;
import org.pixeltime.enchantmentsenhance.gui.menu.buttons.*;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.HashMap;
import java.util.Map;

public class Menu extends GUIAbstract {
    public static Map<String, ItemStack> itemOnEnhancingSlot = new HashMap<String, ItemStack>();
    EnhanceButton enhance = new EnhanceButton();
    ForceButton force = new ForceButton();
    RemoveButton remove = new RemoveButton();
    StatsButton stats = new StatsButton();
    StoreButton store = new StoreButton();

    public Menu(Player player) {
        super(player, 54, SettingsManager.lang.getString("Menu.gui.title"));
        for (int i : MenuCoord.getPlaceHolderCoords()) {
            setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDyeColor(DyeColor.BLACK).toItemStack());
        }
        update();
    }

    @Override
    public void update() {
        Player player = Bukkit.getPlayer(playerName);
        if (itemOnEnhancingSlot.containsKey(playerName)) {
            ItemStack item = itemOnEnhancingSlot.get(playerName);
            setItem(Util.getSlot(8, 4), item);

            setItem(enhance.getPosition(), enhance.getItem(item), () ->
                    Enhance.diceToEnhancement(item, player));

            setItem(force.getPosition(), force.getItem(item), () ->
                    Enhance.forceToEnhancement(item, player));

            setItem(remove.getPosition(), remove.getGlowingItem(), () ->
                    itemOnEnhancingSlot.remove(playerName));

            setItem(stats.getPosition(), stats.getItem(playerName));

        } else {
            setItem(Util.getSlot(8, 4), new ItemStack(Material.AIR));
            setItem(remove.getPosition(), new ItemStack(Material.AIR));

            setItem(enhance.getPosition(), enhance.getItem());
            setItem(force.getPosition(), force.getItem());
            setItem(stats.getPosition(), stats.getItem(playerName));
        }

        setItem(store.getPosition(), Failstack.getLevel(player) == 0 ? store.getItem() : store.getGlowingItem(), () ->
                SecretBook.addFailstackToStorage(player));
    }

}
