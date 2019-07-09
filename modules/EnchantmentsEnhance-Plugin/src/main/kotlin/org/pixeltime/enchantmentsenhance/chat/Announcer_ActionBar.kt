package org.pixeltime.enchantmentsenhance.chat

import org.bukkit.ChatColor
import org.pixeltime.enchantmentsenhance.enums.AnnounceType
import org.pixeltime.enchantmentsenhance.manager.AnnouncerManager
import org.pixeltime.enchantmentsenhance.util.ActionBarAPI

class Announcer_ActionBar : Announcer {
    override fun announce(msg: String, type: AnnounceType) {
        ActionBarAPI.sendActionBarToAllPlayers(ChatColor.translateAlternateColorCodes('&', msg), AnnouncerManager.DELAY.toInt())
    }
}