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
import org.pixeltime.enchantmentsenhance.manager.DatabaseManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.mysql.DataStorage
import org.pixeltime.enchantmentsenhance.util.Util


class PlayerStreamListener : Listener {


    /**
     * Sends a message to greet joined player.
     *
     * @param e
     */
    @EventHandler(priority = EventPriority.MONITOR)
    fun onJoin(e: PlayerJoinEvent) {
        val player = e.player
        if (SettingsManager.config.getBoolean("enableWelcomeMessage")) {
            Util.sendMessage(SettingsManager.lang.getString("config.welcome")!!
                    .replace("%player%".toRegex(), player.name), player)
        }
        DatabaseManager.addPlayerStat(player)
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
        val playerstat = DatabaseManager.getPlayerStat(playername)
        if (playerstat != null) {
            object : BukkitRunnable() {
                override fun run() {
                    try {
                        DataStorage.get().saveStats(playerstat)
                        DatabaseManager.removePlayerStat(playername)
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
        if (DatabaseManager.getPlayerStat(e.player.name) != null) {
            object : BukkitRunnable() {
                override fun run() {
                    DatabaseManager.removePlayerStat(e.player.name)
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
                                "EnchantmentsEnhance")!!.description.version
                                + ".", player)
            }
        }
    }

    companion object {

        val m = Main.getMain()
    }
}
