package org.pixeltime.enchantmentsenhance.listener

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.manager.ItemManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util

class AnvilRestrict : Listener {
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (event.slot != -999) {
            val player = event.whoClicked as Player
            val item : ItemStack? = event.currentItem

            // See: https://github.com/25/EnchantmentsEnhance/issues/174
            if (item == null || item.type == Material.AIR)
                return

            if (event.inventory.type == InventoryType.ANVIL && (ItemManager.getItemEnchantLevel(item) > 0 || ItemManager.getToolEnchantLevel(item) > 0)) {
                if (!SettingsManager.config.getBoolean("enableAnvil")) {
                    event.isCancelled = true
                    Util.sendMessage(SettingsManager.lang.getString("anvil.disabled"), player)
                } else if (!SettingsManager.config.getBoolean("enableAnvilRename") && item != null) {
                    var tempname: String
                    if (item.hasItemMeta() && item.itemMeta!!.hasDisplayName()) {
                        tempname = item.itemMeta!!.displayName
                        if (event.inventory.getItem(0) != null && tempname != event.inventory.getItem(0)!!.itemMeta!!.displayName) {
                            event.isCancelled = true
                            Util.sendMessage(SettingsManager.lang.getString("anvil.renameDisabled"), player)
                        }
                    }
                } else if (!SettingsManager.config.getBoolean("enableAnvilRepair")) {
                    event.isCancelled = true
                    Util.sendMessage(SettingsManager.lang.getString("anvil.repairDisabled"), player)
                }
            }
        }
    }
}
