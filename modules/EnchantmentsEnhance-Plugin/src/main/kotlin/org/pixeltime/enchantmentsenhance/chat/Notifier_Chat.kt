package org.pixeltime.enchantmentsenhance.chat

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.util.Util.pluginTag

class Notifier_Chat : Notifier {
    override fun sendMessage(player: Player, msg: Array<String>) {
        for (str in msg) {
            val message = ChatColor.translateAlternateColorCodes('&', str)
            player.sendMessage(pluginTag() + message)
        }
    }
}