package org.pixeltime.enchantmentsenhance.listener;


import com.lgou2w.ldk.bukkit.compatibility.XMaterial;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract;
import org.pixeltime.enchantmentsenhance.gui.GUIManager;
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;


public class VanillaEnchantListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onOpenEnchantmentTable(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getClickedBlock().getType() == XMaterial.ENCHANTING_TABLE.toBukkit()) {
            if (SettingsManager.config.getString("openMethod").equalsIgnoreCase("LEFT_CLICK")) {
                if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                    event.setCancelled(true);
                    new MainMenu(event.getPlayer()).open();
                }
            } else if (SettingsManager.config.getString("openMethod").equalsIgnoreCase("RIGHT_CLICK")) {
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    event.setCancelled(true);
                    GUIAbstract gui = GUIManager.getMap().get(event.getPlayer().getName());
                    if (gui == null)
                        new MainMenu(event.getPlayer()).open();
                }
            } else if (SettingsManager.config.getString("openMethod").equalsIgnoreCase("SHIFT_AND_LEFT_CLICK")) {
                if (event.getAction() == Action.LEFT_CLICK_BLOCK && event.getPlayer().isSneaking()) {
                    event.setCancelled(true);
                    new MainMenu(event.getPlayer()).open();
                }
            } else if (SettingsManager.config.getString("openMethod").equalsIgnoreCase("SHIFT_AND_RIGHT_CLICK")) {
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getPlayer().isSneaking()) {
                    event.setCancelled(true);
                    GUIAbstract gui = GUIManager.getMap().get(event.getPlayer().getName());
                    if (gui == null)
                        new MainMenu(event.getPlayer()).open();
                }
            }
        }
    }
}