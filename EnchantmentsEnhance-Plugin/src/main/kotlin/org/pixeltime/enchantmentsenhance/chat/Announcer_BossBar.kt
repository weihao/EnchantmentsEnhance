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
                failed.title = ChatColor.translateAlternateColorCodes('&', msg)
                for (p in Bukkit.getOnlinePlayers()) {
                    failed.addPlayer(p)
                }
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), { failed.removeAll() }, AnnouncerManager.DELAY)
            }
            AnnounceType.SUCCESS -> {
                success.title = ChatColor.translateAlternateColorCodes('&', msg)
                for (p in Bukkit.getOnlinePlayers()) {
                    success.addPlayer(p)
                }
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), { success.removeAll() }, AnnouncerManager.DELAY)
            }
            else -> {
                info.title = ChatColor.translateAlternateColorCodes('&', msg)
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