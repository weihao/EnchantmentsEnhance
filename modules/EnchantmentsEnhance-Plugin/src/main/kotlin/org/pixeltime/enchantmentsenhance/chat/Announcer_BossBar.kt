package org.pixeltime.enchantmentsenhance.chat

import org.bukkit.Bukkit
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.enums.AnnounceType
import org.pixeltime.enchantmentsenhance.manager.AnnouncerManager
import org.pixeltime.enchantmentsenhance.util.Util


class Announcer_BossBar : Announcer {
    override fun announce(msg: String, type: AnnounceType) {
        when (type) {
            AnnounceType.FAIL -> {
                failed.setTitle(Util.toColor(msg))
                for (p in Bukkit.getOnlinePlayers()) {
                    failed.addPlayer(p)
                }
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), { failed.removeAll() }, AnnouncerManager.DELAY)
            }
            AnnounceType.SUCCESS -> {
                success.setTitle(Util.toColor(msg))
                for (p in Bukkit.getOnlinePlayers()) {
                    success.addPlayer(p)
                }
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), { success.removeAll() }, AnnouncerManager.DELAY)
            }
            else -> {
                info.setTitle(Util.toColor(msg))
                for (p in Bukkit.getOnlinePlayers()) {
                    info.addPlayer(p)
                }
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), { info.removeAll() }, AnnouncerManager.DELAY)
            }
        }
    }

    companion object {
        val failed = Bukkit.createBossBar(Util.toColor(""), BarColor.RED, BarStyle.SOLID)
        val success = Bukkit.createBossBar(Util.toColor(""), BarColor.GREEN, BarStyle.SOLID)
        val info = Bukkit.createBossBar(Util.toColor(""), BarColor.BLUE, BarStyle.SOLID)
    }

}
