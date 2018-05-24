/*
 *     Copyright (C) 2017-Present HealPotion
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

package org.pixeltime.enchantmentsenhance.manager.modular;

import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.interfaces.GlowItem;
import org.pixeltime.enchantmentsenhance.util.UnsafeGlow;

public class GlowItem_Unsafe implements GlowItem {
    public ItemStack addGlow(ItemStack item) {
        UnsafeGlow.addGlow(item);
        return item;
    }

}
