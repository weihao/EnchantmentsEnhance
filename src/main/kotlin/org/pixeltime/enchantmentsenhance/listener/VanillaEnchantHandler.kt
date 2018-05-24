package org.pixeltime.enchantmentsenhance.listener

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.pixeltime.enchantmentsenhance.event.menu.Menu
import org.pixeltime.enchantmentsenhance.manager.SettingsManager


class VanillaEnchantHandler : Listener {
    private val disableVanilla = SettingsManager.config.getBoolean("disableVanillaEnchant")

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onOpenEnchantmentTable(event: PlayerInteractEvent) {
        if (disableVanilla && event.action == Action.RIGHT_CLICK_BLOCK && event.clickedBlock.type == Material.ENCHANTMENT_TABLE) {
            event.isCancelled = true
            Menu.showEnhancingMenu(event.player)
        }
    }
}