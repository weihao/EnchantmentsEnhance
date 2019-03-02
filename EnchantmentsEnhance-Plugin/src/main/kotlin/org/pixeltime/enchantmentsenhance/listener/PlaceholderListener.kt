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

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.version.VersionManager

class PlaceholderListener : PlaceholderExpansion() {

    override fun getIdentifier(): String {
        return "ee"
    }

    override fun getPlugin(): String {
        return "EnchantmentsEnhance"
    }

    override fun getAuthor(): String {
        return "HealPot"
    }

    override fun getVersion(): String {
        return VersionManager.getCurrentVersion()
    }

    override fun onPlaceholderRequest(player: Player?, s: String): String? {
        if (player == null) {
            return ""
        }
        val playerName = player.name
        if (s.startsWith("item_")) {
            return Main.getApi().getItem(playerName, s.split("_")[1].toInt()).toString()
        }
        if (s.equals("failstack", ignoreCase = true)) {
            return Main.getApi().getFailstack(playerName).toString()
        }
        return null
    }
}
