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

package org.pixeltime.enchantmentsenhance.gui.menu

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import java.util.*

class VendorMenu : GUIAbstract {

    constructor(player: Player) : super(player, 54, SettingsManager.lang.getString("vendor.gui")) {
        update()
    }

    override fun update() {
        inventory.clear()
        actions.clear()
        val player = Bukkit.getPlayer(playerName)
    }

    companion object {
        private val COOLDOWNTIME = (10 * 1000).toLong()
        private val cooldownMap = HashMap<String, Long>()
        private fun isInCooldown(playerName: String): Boolean {
            return getCooldown(playerName) > 0
        }
        private fun getCooldown(playerName: String): Int {
            return if (cooldownMap.containsKey(playerName)) {
                if (cooldownMap[playerName]!! + COOLDOWNTIME >= System.currentTimeMillis()) {
                    cooldownMap.remove(playerName)
                    0
                } else {
                    ((cooldownMap[playerName]!! + COOLDOWNTIME) / 1000 - System.currentTimeMillis() / 1000).toInt()
                }
            } else {
                0
            }
        }
        private fun setCoolDown(playerName: String) {
            cooldownMap[playerName] = System.currentTimeMillis()
        }
    }
}
