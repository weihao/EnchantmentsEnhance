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

package org.pixeltime.enchantmentsenhance.chat

import com.lgou2w.ldk.bukkit.chat.ChatFactory
import com.lgou2w.ldk.chat.ChatSerializer
import org.bukkit.entity.Player

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
            sendTitle(player, str1.toString(), str2.toString())
        }
        if (msg.size == 2) {
            sendTitle(player, msg[0], msg[1])
        } else {
            val result = StringBuffer()
            for (str in msg) {
                result.append("$str ")
            }
            sendTitle(player, result.toString(), null)
        }
    }

    companion object {
        const val fadeIn = 5
        const val stay = 20
        const val fadeOut = 5
        fun sendTitle(player: Player, title: String, subTitle: String? = null) {
            val titleComponent = ChatSerializer.fromRaw(title)
            val subTitleComponent = ChatSerializer.fromRawOrNull(subTitle)
            ChatFactory.sendTitle(player, titleComponent, subTitleComponent, fadeIn, stay, fadeOut)
        }
    }
}
