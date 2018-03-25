package org.pixeltime.healpot.enhancement.events.blackspirit;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.GUI;
import org.pixeltime.healpot.enhancement.util.Util;

public class Reform extends GUI implements Listener{
    private static Map<String, ItemStack> itemOnReformingSlot =
        new HashMap<String, ItemStack>();


    public Reform() {
        super(27, SettingsManager.lang.getString("Reform.gui.title"));
        setItem(Util.getSlot(5, 3), Util.createButton(DyeColor.BLACK,
            SettingsManager.lang.getString("Reform.reform")), player -> {
                if (itemOnReformingSlot.containsKey(player.getDisplayName())) {

                }
            });
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getSlot() < 0) {
            return;
        }
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        if (e.getCurrentItem().getType() == (Material.AIR)) {
            return;
        }
        if (this.getInventory().getName().equals(e.getInventory().getName()))
        {
            e.setCancelled(true);
        }
    }
}
