package org.pixeltime.enchantmentsenhance.manager

import com.sk89q.worldedit.entity.Player
import org.pixeltime.enchantmentsenhance.model.PlayerStat
import org.pixeltime.enchantmentsenhance.mysql.DataStorage
import java.util.*

class DatabaseManager {


    fun addPlayerStat(player: Player) {
        DatabaseManager.removePlayerStat(player.name)
        val playerStat = PlayerStat()
        playerStat.playername = player.name
        DataStorage.get().loadStats(playerStat)
        playerStats.add(playerStat)
    }

    companion object {
        private val playerStats = ArrayList<PlayerStat>()

        fun getPlayerStat(playername: String): PlayerStat? {
            for (pData in playerStats) {
                if (pData.playername == playername) {
                    return pData
                }
            }
            return null
        }

        fun removePlayerStat(playername: String) {
            val ps = getPlayerStat(playername)
            if (ps != null) {
                playerStats.remove(ps)
            }
        }
    }

}
