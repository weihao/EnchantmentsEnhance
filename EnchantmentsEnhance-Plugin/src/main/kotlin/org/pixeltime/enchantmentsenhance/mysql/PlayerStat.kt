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

package org.pixeltime.enchantmentsenhance.mysql

import org.bukkit.entity.Player
import java.util.*

class PlayerStat(player: Player) {

    var playerName: String = player.name
    var failstack: Int = 0
    var items: IntArray? = null
    var valks: MutableList<Int>? = null
    var grind: Int = 0

    init {
        DataStorage.get().loadStats(this)
    }

    companion object {
        var players = ArrayList<PlayerStat>()
        fun getPlayerStats(playerName: String): PlayerStat? {
            for (pData in players) {
                if (pData.playerName == playerName) {
                    return pData
                }
            }
            return null
        }

        fun removePlayer(playerName: String) {
            val ps = getPlayerStats(playerName)
            if (ps != null) {
                players.remove(ps)
            }
        }
    }
}
