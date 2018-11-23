package com.ugleh.anvilrestrict

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.pixeltime.enchantmentsenhance.manager.ItemManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util

class AnvilRestrict() : Listener {
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (event.getSlot() != -999) {
            val player = event.whoClicked as Player
            val item = event.currentItem
            if (event.inventory.type == InventoryType.ANVIL) {
                if (!SettingsManager.config.getBoolean("enableAnvil")) {
                    event.isCancelled = true
                    Util.sendMessage(SettingsManager.lang.getString("anvil.disabled"), player)
                } else if (!SettingsManager.config.getBoolean("enableAnvilRename")) {
                    if (item != null) {
                        var tempname = ""
                        if (item.itemMeta.hasDisplayName()) {
                            tempname = item.itemMeta.displayName
                        }
                        if (tempname != event.inventory.getItem(0).itemMeta.displayName) {
                            event.isCancelled = true
                            Util.sendMessage(SettingsManager.lang.getString("anvil.renameDisabled"), player)
                        }
                    }
                } else if (!SettingsManager.config.getBoolean("enableAnvilRepair")) {
                    if (ItemManager.getItemEnchantLevel(item) > 0 || ItemManager.getToolEnchantLevel(item) > 0) {
                        event.isCancelled = true
                        Util.sendMessage(SettingsManager.lang.getString("anvil.repairDisabled"), player)
                    }
                }
            }
        }
    }
}
