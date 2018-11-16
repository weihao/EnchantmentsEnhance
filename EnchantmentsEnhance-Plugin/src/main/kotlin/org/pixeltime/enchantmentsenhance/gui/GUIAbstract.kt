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

import com.lgou2w.ldk.common.Consumer
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.util.Util
import java.util.*

abstract class GUIAbstract {

    val playerName: String
    val inventory: Inventory
    val actions: MutableMap<Int, GUIAction>

    constructor(player: Player, invSize: Int, invName: String) {
        this.inventory = Bukkit.createInventory(null, invSize, Util.toColor(invName))
        this.actions = HashMap()
        this.playerName = player.name
        GUIManager.getMap()[player.name] = this
        GUIManager.getSet().add(this)
    }

    fun setItem(slot: Int, stack: ItemStack?, action: GUIAction?) {
        inventory.setItem(slot, stack)
        if (action != null) {
            actions[slot] = action
        }
    }

    fun setItem(slot: Int, stack: ItemStack?, action: Consumer<ClickType>? = null) {
        setItem(slot, stack, object : GUIAction {
            override fun click(clickType: ClickType) {
                action?.invoke(clickType)
            }
        })
    }

    fun setItem(slot: Int, stack: ItemStack?) {
        setItem(slot, stack, null as? GUIAction)
    }

    fun open() {
        Bukkit.getPlayer(playerName).openInventory(inventory)
    }

    abstract fun update()

    @FunctionalInterface
    interface GUIAction {
        fun click(clickType: ClickType)
    }
}
