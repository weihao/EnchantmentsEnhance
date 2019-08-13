package org.pixeltime.enchantmentsenhance.chat

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.enums.AnnounceType
import org.pixeltime.enchantmentsenhance.manager.AnnouncerManager


class Announcer_BossBar : Announcer {
    override fun announce(msg: String, type: AnnounceType) {
        when (type) {
            AnnounceType.FAIL -> {
                failed.setTitle(ChatColor.translateAlternateColorCodes('&', msg))
                for (p in Bukkit.getOnlinePlayers()) {
                    failed.addPlayer(p)
                }
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), { failed.removeAll() }, AnnouncerManager.DELAY)
            }
            AnnounceType.SUCCESS -> {
                success.setTitle(ChatColor.translateAlternateColorCodes('&', msg))
                for (p in Bukkit.getOnlinePlayers()) {
                    success.addPlayer(p)
                }
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), { success.removeAll() }, AnnouncerManager.DELAY)
            }
            else -> {
                info.setTitle(ChatColor.translateAlternateColorCodes('&', msg))
                for (p in Bukkit.getOnlinePlayers()) {
                    info.addPlayer(p)
                }
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), { info.removeAll() }, AnnouncerManager.DELAY)
            }
        }
    }

    companion object {
        val failed = Bukkit.createBossBar(ChatColor.translateAlternateColorCodes('&', ""), BarColor.RED, BarStyle.SOLID)
        val success = Bukkit.createBossBar(ChatColor.translateAlternateColorCodes('&', ""), BarColor.GREEN, BarStyle.SOLID)
        val info = Bukkit.createBossBar(ChatColor.translateAlternateColorCodes('&', ""), BarColor.BLUE, BarStyle.SOLID)
    }

}
