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

package org.pixeltime.enchantmentsenhance.chat

import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.util.TitleAPI

class Notifier_TitleBar : Notifier {
    override fun sendMessage(player: Player, msg: Array<String>) {
        if (msg.size == 1) {
            val line = msg[0].split(" ")
            val half = line.size / 2

            val str1 = StringBuffer()
            for (i in 0..half) {
                str1.append("${line[i]} ")
            }
            val str2 = StringBuffer()
            for (i in half until line.size) {
                str2.append("${line[i]} ")
            }

            TitleAPI.sendTitle(player, fadeIn, stay, fadeOut, str1.toString(), str2.toString())
        }
        if (msg.size == 2) {
            TitleAPI.sendTitle(player, fadeIn, stay, fadeOut, msg[0], msg[1])
        } else {
            val result = StringBuffer()
            for (str in msg) {
                result.append("$str ")
            }
            TitleAPI.sendTitle(player, fadeIn, stay, fadeOut, result.toString(), null)
        }
    }

    companion object {
        val fadeIn = 5
        val stay = 20
        val fadeOut = 5
    }
}