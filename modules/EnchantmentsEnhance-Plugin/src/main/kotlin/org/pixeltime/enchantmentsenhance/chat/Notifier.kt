package org.pixeltime.enchantmentsenhance.chat

import org.bukkit.entity.Player


interface Notifier {
    fun sendMessage(player: Player, msg: Array<String>)
}