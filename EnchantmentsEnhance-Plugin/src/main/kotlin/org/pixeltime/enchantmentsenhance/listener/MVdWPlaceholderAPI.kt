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

package org.pixeltime.enchantmentsenhance.listener

import be.maximvdw.placeholderapi.PlaceholderAPI
import org.bukkit.Bukkit
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.manager.MaterialManager

class MVdWPlaceholderAPI {

    companion object {
        @JvmStatic
        fun setUp() {
            if (Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
                for (Int in 0 until MaterialManager.stoneTypes.size) {
                    PlaceholderAPI.registerPlaceholder(Main.getMain(),
                            "EE_item_$Int"
                    ) { event ->
                        Main.getApi().getItem(event.player.name, Int).toString()
                    }
                }
                PlaceholderAPI.registerPlaceholder(Main.getMain(),
                        "EE_failstack"
                ) { event ->
                    Main.getApi().getFailstack(event.player.displayName).toString()
                }
            }
        }
    }
}
