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