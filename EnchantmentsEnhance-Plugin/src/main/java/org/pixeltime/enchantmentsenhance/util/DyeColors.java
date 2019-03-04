/*
 *     Copyright (C) 2017-Present 25 (https://github.com/25)
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

package org.pixeltime.enchantmentsenhance.util;

import org.bukkit.DyeColor;

public enum DyeColors {

    LIGHT_GRAY("LIGHT_GRAY", "SILVER");

    private String pre113dyecolor;
    private String post113dyecolor;
    private DyeColor cached = null;

    DyeColors(String post113dyecolor, String pre113dyecolor) {
        this.pre113dyecolor = post113dyecolor;
        this.post113dyecolor = pre113dyecolor;
    }

    public DyeColor bukkitDyeColor() {
        if (cached != null) return cached;
        try {
            return cached = DyeColor.valueOf(pre113dyecolor);
        } catch (IllegalArgumentException ignore) {
            return cached = DyeColor.valueOf(post113dyecolor);
        }
    }
}
