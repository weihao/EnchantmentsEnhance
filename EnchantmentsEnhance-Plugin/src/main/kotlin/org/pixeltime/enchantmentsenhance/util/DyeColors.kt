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

package org.pixeltime.enchantmentsenhance.util

import org.bukkit.DyeColor

enum class DyeColors {

    LIGHT_GRAY("LIGHT_GRAY", "SILVER");

    private val pre113dyecolor: String
    private val post113dyecolor: String
    private var cached: DyeColor? = null

    constructor(post113dyecolor: String, pre113dyecolor: String) {
        this.pre113dyecolor = pre113dyecolor
        this.post113dyecolor = post113dyecolor
    }

    fun bukkitDyeColor(): DyeColor {
        if (cached != null) return cached!!
        cached = try {
            DyeColor.valueOf(pre113dyecolor)
        } catch (ignore: IllegalArgumentException) {
            DyeColor.valueOf(post113dyecolor)
        }
        return cached!!
    }
}
