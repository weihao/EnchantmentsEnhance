/*
 *     Copyright (C) 2017-Present 25
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.pixeltime.enchantmentsenhance.listener

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.pixeltime.enchantmentsenhance.manager.ItemManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util

class AnvilRestrict : Listener {
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (event.slot != -999) {
            val player = event.whoClicked as Player
            val item = event.currentItem
            if (event.inventory.type == InventoryType.ANVIL && (ItemManager.getItemEnchantLevel(item) > 0 || ItemManager.getToolEnchantLevel(item) > 0)) {
                if (!SettingsManager.config.getBoolean("enableAnvil")) {
                    event.isCancelled = true
                    Util.sendMessage(SettingsManager.lang.getString("anvil.disabled"), player)
                } else if (!SettingsManager.config.getBoolean("enableAnvilRename") && item != null) {
                    var tempname: String
                    if (item.hasItemMeta() && item.itemMeta.hasDisplayName()) {
                        tempname = item.itemMeta.displayName
                        if (event.inventory.getItem(0) != null && tempname != event.inventory.getItem(0).itemMeta.displayName) {
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