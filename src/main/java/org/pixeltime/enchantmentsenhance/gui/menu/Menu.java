package org.pixeltime.enchantmentsenhance.gui.menu;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Enhance;
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract;
import org.pixeltime.enchantmentsenhance.gui.MenuCoord;
import org.pixeltime.enchantmentsenhance.gui.menu.buttons.*;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.HashMap;
import java.util.Map;

public class Menu extends GUIAbstract implements Listener {
    private static Map<String, ItemStack> itemOnEnhancingSlot = new HashMap<String, ItemStack>();
    EnhanceButton enhance = new EnhanceButton();
    ForceButton force = new ForceButton();
    RemoveButton remove = new RemoveButton();
    StatsButton stats = new StatsButton();
    StoreButton store = new StoreButton();

    public Menu(Player player) {
        super(player, 54, SettingsManager.lang.getString("Menu.gui.title"));
        setItem(enhance.getPosition(), enhance.getItem());
        setItem(force.getPosition(), force.getItem());
        setItem(remove.getPosition(), remove.getItem());
        setItem(stats.getPosition(), stats.getItem(player));
        setItem(store.getPosition(), store.getItem());
        for (int i : MenuCoord.getPlaceHolderCoords()) {
            setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDyeColor(DyeColor.BLACK).toItemStack());
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        // Handles invalid clicks.
        if (e.getSlot() < 0) {
            return;
        }
        // Handles invalid entity.
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        // Handles empty slot.
        if (e.getCurrentItem().getType() == (Material.AIR)) {
            return;
        }
        Player player = (Player) e.getWhoClicked();
        String playerName = player.getName();
        GUIAbstract gui = GUIAbstract.playerMap.get(playerName);
        if (gui != null && gui.getInventory() == e.getInventory()) {
            e.setCancelled(true);
            if (!itemOnEnhancingSlot.containsKey(player.getName())) {
                if (Enhance.getValidationOfItem(e.getCurrentItem())) {
                    itemOnEnhancingSlot.put(player.getName(), e.getCurrentItem());

                    setItem(Util.getSlot(8, 4), e.getCurrentItem());

                    setItem(enhance.getPosition(), enhance.getGlowingItem(), () -> {
                        Enhance.diceToEnhancement(itemOnEnhancingSlot.get(player.getName()), player);
                    });

                    setItem(force.getPosition(), force.getGlowingItem());
                }
            }
        }
    }

    /**
     * Opens GUI to a player.
     *
     * @param player
     */
    public void showEnhancingMenu(Player player) {
        this.open();
        itemOnEnhancingSlot.remove(player.getName());
    }

    /**
     * Removes current item placed on the enhancing slot.
     *
     * @param e
     */
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        System.out.println(itemOnEnhancingSlot.containsKey(player.getName()));
        itemOnEnhancingSlot.remove(player.getName());
    }
}
