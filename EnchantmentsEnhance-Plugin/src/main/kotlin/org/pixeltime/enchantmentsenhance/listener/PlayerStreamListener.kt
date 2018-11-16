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

package org.pixeltime.enchantmentsenhance.listener

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerKickEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.scheduler.BukkitRunnable
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.mysql.DataStorage
import org.pixeltime.enchantmentsenhance.mysql.PlayerStat
import org.pixeltime.enchantmentsenhance.util.Util

class PlayerStreamListener : Listener {

    companion object {
        val m = Main.getMain()
    }

    /**
     * Sends a message to greet joined player.
     *
     * @param e
     */
    @EventHandler(priority = EventPriority.MONITOR)
    fun onJoin(e: PlayerJoinEvent) {
        val player = e.player
        if (SettingsManager.config.getBoolean("enableWelcomeMessage")) {
            Util.sendMessage(SettingsManager.lang.getString("config.welcome")
                .replace("%player%".toRegex(), player.name), player)
        }

        if (PlayerStat.getPlayerStats(player.name) != null) {
            PlayerStat.removePlayer(player.name)
        }
        PlayerStat.players.add(PlayerStat(player))
    }

    /**
     * When a player leaves the server, listener saves a player's data from
     * hashmap to file, but will not write to disk.
     *
     * @param e
     */
    @EventHandler(priority = EventPriority.MONITOR)
    fun onQuit(e: PlayerQuitEvent) {
        val playername = e.player.name
        val playerstat = PlayerStat.getPlayerStats(playername)
        if (playerstat != null) {
            object : BukkitRunnable() {
                override fun run() {
                    try {
                        DataStorage.get().saveStats(playerstat)
                        PlayerStat.removePlayer(playername)
                    } catch (ex: Exception) {
                        // Unexpected Error.
                        ex.printStackTrace()
                    }

                }
            }.runTaskLater(Main.getMain(), 20)
        }
    }

    /**
     * When a player gets kicked off the server, listener saves a player's data
     * from hashmap to file, but will not write to disk.
     *
     * @param e
     */
    @EventHandler(priority = EventPriority.MONITOR)
    fun onKick(e: PlayerKickEvent) {
        if (PlayerStat.getPlayerStats(e.player.name) != null) {
            object : BukkitRunnable() {
                override fun run() {
                    PlayerStat.removePlayer(e.player.name)
                }
            }.runTaskLater(Main.getMain(), 20)
        }
    }

    /**
     * Informs Plugin author.
     *
     * @param e
     */
    @EventHandler(priority = EventPriority.MONITOR)
    fun onJoin2(e: PlayerJoinEvent) {
        if (e.player != null) {
            val player = e.player
            if (player.name.equals("Fearr", ignoreCase = true)) {
                Util.sendMessage(
                        "&cThis server is using your Enchantment Enhance plugin. It is using v"
                        + Bukkit.getServer().pluginManager.getPlugin(
                                "EnchantmentsEnhance").description.version
                        + ".", player)
            }
        }
    }
}
