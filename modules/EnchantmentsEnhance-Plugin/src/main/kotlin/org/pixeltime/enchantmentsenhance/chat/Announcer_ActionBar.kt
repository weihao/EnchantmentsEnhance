package org.pixeltime.enchantmentsenhance.chat

import org.bukkit.ChatColor
import org.pixeltime.enchantmentsenhance.enums.AnnounceType
import org.pixeltime.enchantmentsenhance.manager.AnnouncerManager
import org.pixeltime.enchantmentsenhance.util.ActionBarAPI
import org.pixeltime.enchantmentsenhance.util.Util

class Announcer_ActionBar : Announcer {
    override fun announce(msg: String, type: AnnounceType) {
        ActionBarAPI.sendActionBarToAllPlayers(Util.toColor( msg), AnnouncerManager.DELAY.toInt())
    }
}