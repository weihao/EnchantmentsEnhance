package org.pixeltime.enchantmentsenhance.listener


import com.lgou2w.ldk.bukkit.compatibility.XMaterial
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu
import org.pixeltime.enchantmentsenhance.manager.SettingsManager


class VanillaEnchantListener : Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onOpenEnchantmentTable(event: PlayerInteractEvent) {
        if (event.clickedBlock != null && event.clickedBlock!!.type == XMaterial.ENCHANTING_TABLE.toBukkit()) {
            if (SettingsManager.config.getString("openMethod")!!.equals("LEFT_CLICK", ignoreCase = true)) {
                if (event.action == Action.LEFT_CLICK_BLOCK) {
                    event.isCancelled = true
                    MainMenu(event.player).open()
                }
            } else if (SettingsManager.config.getString("openMethod")!!.equals("RIGHT_CLICK", ignoreCase = true)) {
                if (event.action == Action.RIGHT_CLICK_BLOCK) {
                    event.isCancelled = true
                    MainMenu(event.player).open()
                }
            } else if (SettingsManager.config.getString("openMethod")!!.equals("SHIFT_AND_LEFT_CLICK", ignoreCase = true)) {
                if (event.action == Action.LEFT_CLICK_BLOCK && event.player.isSneaking) {
                    event.isCancelled = true
                    MainMenu(event.player).open()
                }
            } else if (SettingsManager.config.getString("openMethod")!!.equals("SHIFT_AND_RIGHT_CLICK", ignoreCase = true)) {
                if (event.action == Action.RIGHT_CLICK_BLOCK && event.player.isSneaking) {
                    event.isCancelled = true
                    MainMenu(event.player).open()
                }
            }
        }
    }
}