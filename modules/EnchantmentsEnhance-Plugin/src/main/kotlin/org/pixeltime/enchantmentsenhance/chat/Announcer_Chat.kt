package org.pixeltime.enchantmentsenhance.chat

import org.bukkit.Bukkit
import org.pixeltime.enchantmentsenhance.enums.AnnounceType
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util

class Announcer_Chat : Announcer {
    override fun announce(msg: String, type: AnnounceType) {
        Bukkit.broadcastMessage(Util.toColor(SettingsManager.lang.getString("config.pluginTag") + msg))
    }
}