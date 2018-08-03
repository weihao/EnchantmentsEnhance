/*
 *     Copyright (C) 2017-Present HealPot
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

package org.pixeltime.enchantmentsenhance.chat

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util

/**
 * @author HealPotion
 * @version Feb 9, 2018
 */
class Broadcast {
    companion object {
        @JvmStatic
                /**
                 * Sends a message to all online players.
                 *
                 * @param player
                 * @param item
                 * @param success
                 */
        fun broadcast(player: Player, item: ItemStack, success: String) {
            broadcast(SettingsManager.lang.getString("Annoucer.$success").replace("%player%", player.name).replace("%item%", item.itemMeta.displayName))
        }

        @JvmStatic
        fun broadcast(info: String) {
            Bukkit.broadcastMessage(Util.toColor(SettingsManager.lang.getString("Config.pluginTag") + info))
        }
    }
}
