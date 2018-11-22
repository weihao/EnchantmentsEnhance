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

import com.lgou2w.ldk.bukkit.chat.ChatFactory
import com.lgou2w.ldk.chat.ChatAction
import com.lgou2w.ldk.chat.ChatSerializer
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.pixeltime.enchantmentsenhance.Main

object ActionBarAPI {

    fun setUp() {
    }

    fun sendActionBar(player: Player, message: String) {
        if (!player.isOnline) {
            return  // Player may have logged out
        }
        val component = ChatSerializer.fromRaw(message)
        ChatFactory.sendChat(player, component, ChatAction.ACTIONBAR)
    }

    fun sendActionBar(player: Player, message: String, duration: Int) {
        var duration = duration
        sendActionBar(player, message)

        if (duration >= 0) {
            // Sends empty message at the end of the duration. Allows messages shorter than 3 seconds, ensures precision.
            object : BukkitRunnable() {
                override fun run() {
                    sendActionBar(player, "")
                }
            }.runTaskLater(Main.getMain(), (duration + 1).toLong())
        }

        // Re-sends the messages every 3 seconds so it doesn't go away from the player's screen.
        while (duration > 40) {
            duration -= 40
            object : BukkitRunnable() {
                override fun run() {
                    sendActionBar(player, message)
                }
            }.runTaskLater(Main.getMain(), duration.toLong())
        }
    }

    fun sendActionBarToAllPlayers(message: String) {
        sendActionBarToAllPlayers(message, -1)
    }

    fun sendActionBarToAllPlayers(message: String, duration: Int) {
        for (p in Bukkit.getOnlinePlayers()) {
            sendActionBar(p, message, duration)
        }
    }
}
