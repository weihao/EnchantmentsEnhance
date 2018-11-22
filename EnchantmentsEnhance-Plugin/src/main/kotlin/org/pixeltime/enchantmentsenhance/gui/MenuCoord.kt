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

package org.pixeltime.enchantmentsenhance.gui

import org.pixeltime.enchantmentsenhance.util.Util

class MenuCoord {

    companion object {
        @JvmStatic
        fun getPlaceHolderCoords(): IntArray {
            return intArrayOf(
                    Util.getSlot(1, 2),
                    Util.getSlot(2, 2),
                    Util.getSlot(3, 2),
                    Util.getSlot(4, 2),
                    Util.getSlot(6, 2),
                    Util.getSlot(7, 2),
                    Util.getSlot(8, 2),
                    Util.getSlot(9, 2),
                    Util.getSlot(1, 3),
                    Util.getSlot(9, 3),
                    Util.getSlot(1, 4),
                    Util.getSlot(9, 4),
                    Util.getSlot(1, 5),
                    Util.getSlot(9, 5),
                    Util.getSlot(1, 6),
                    Util.getSlot(2, 6),
                    Util.getSlot(3, 6),
                    Util.getSlot(4, 6),
                    Util.getSlot(5, 6),
                    Util.getSlot(6, 6),
                    Util.getSlot(7, 6),
                    Util.getSlot(8, 6),
                    Util.getSlot(9, 6)
            )
        }
    }
}
