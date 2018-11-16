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

package org.pixeltime.enchantmentsenhance.util.anvil

import org.bukkit.entity.Player
import org.bukkit.inventory.AnvilInventory
import org.bukkit.scheduler.BukkitRunnable
import org.pixeltime.enchantmentsenhance.Main
import java.util.*

class AnvilTask : BukkitRunnable {

    companion object {
        private var anvilTasks: HashMap<AnvilInventory, AnvilTask> = HashMap()
        fun getTask(inv: AnvilInventory): AnvilTask? {
            return AnvilTask.anvilTasks[inv]
        }
    }

    private val inv: AnvilInventory
    private val player: Player

    constructor(inv: AnvilInventory, player: Player) : super() {
        this.inv = inv
        this.player = player
        AnvilTask.anvilTasks[inv] = this
        this.runTaskTimer(Main.getMain(), 1L, 3L)
    }

    override fun run() {
        if (this.inv.viewers.size == 0) {
            this.cancel()
        }
        ColorHandler.getTranslatedItem(this.player, this.inv, this)
    }
}
