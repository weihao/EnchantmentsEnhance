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

package org.pixeltime.enchantmentsenhance.mysql

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.scheduler.BukkitRunnable
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.manager.MaterialManager
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*

class DataStorage private constructor() {

    companion object {
        private var instance : DataStorage? = null
        fun get(): DataStorage {
            if (instance == null)
                instance = DataStorage()
            return instance!!
        }
    }

    fun saveStats(pData: PlayerStat) {
        val sqlEnabled = Main.getMain().config.getBoolean("mysql.enabled")
        if (!sqlEnabled) {
            try {
                val dataDirectory = Main.getMain().dataFolder
                val playerDataDirectory = File(dataDirectory, "player_data")

                if (!playerDataDirectory.exists() && !playerDataDirectory.mkdirs()) {
                    return
                }

                val playerFile = File(playerDataDirectory, pData.playerName + ".yml")
                if (!playerFile.exists()) {
                    Main.getMain().logger.info("File doesn't exist!")
                    return
                }
                copyDefaults(playerFile)
                val fc = YamlConfiguration.loadConfiguration(playerFile)
                // Query
                fc.set("failstack", pData.failstack)
                fc.set("items", Arrays.toString(pData.items))
                fc.set("valks", pData.valks!!.toString())
                fc.set("grind", pData.grind)
                fc.save(playerFile)

            } catch (ioException: IOException) {
                System.out.println("Failed to load faction " + pData.playerName + ": " + ioException.message)
            }

        } else {
            val database = Main.getDatabase()

            if (!database.checkConnection()) {
                return
            }

            val connection = database.getConnection()
            var preparedStatement: PreparedStatement? = null

            try {
                val queryBuilder = StringBuilder()
                queryBuilder.append("UPDATE `enchantmentsenhance` SET ")
                queryBuilder.append("`failstack` = ?, `items` = ?, `valks` = ?, `grind` = ?")
                queryBuilder.append("WHERE `playername` = ?;")

                preparedStatement = connection.prepareStatement(queryBuilder.toString())

                // Query
                preparedStatement!!.setInt(1, pData.failstack)
                preparedStatement.setString(2, Arrays.toString(pData.items))
                preparedStatement.setString(3, pData.valks!!.toString())
                preparedStatement.setInt(4, pData.grind)
                preparedStatement.setString(5, pData.playerName)

                preparedStatement.executeUpdate()

            } catch (sqlException: SQLException) {
                sqlException.printStackTrace()

            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close()
                    } catch (ignored: SQLException) {
                    }

                }
            }
        }
    }

    fun loadStats(pData: PlayerStat) {
        object : BukkitRunnable() {
            override fun run() {
                val sqlEnabled = Main.getMain().config.getBoolean("mysql.enabled")
                if (sqlEnabled) {
                    val database = Main.getDatabase()

                    if (!database.checkConnection()) {
                        return
                    }

                    if (!database.doesPlayerExist(pData.playerName)) {
                        database.createNewPlayer(pData.playerName)
                        pData.failstack = 0
                        pData.items = IntArray(MaterialManager.stoneTypes.size)
                        pData.valks = ArrayList()
                        pData.grind = 2
                    } else {
                        val connection = database.getConnection()
                        var preparedStatement: PreparedStatement? = null
                        var resultSet: ResultSet? = null

                        try {
                            val queryBuilder = StringBuilder()
                            // Query
                            queryBuilder.append("SELECT `failstack`, `items`, `valks`, `grind` ")
                            queryBuilder.append("FROM `enchantmentsenhance` ")
                            queryBuilder.append("WHERE `playername` = ? ")
                            queryBuilder.append("LIMIT 1;")

                            preparedStatement = connection.prepareStatement(queryBuilder.toString())
                            preparedStatement!!.setString(1, pData.playerName)
                            resultSet = preparedStatement.executeQuery()

                            if (resultSet != null && resultSet.next()) {
                                pData.failstack = resultSet.getInt("failstack")

                                val tmp = resultSet.getString("items")
                                    .replace("[", "")
                                    .replace("]", "")
                                    .split(", ".toRegex())
                                    .dropLastWhile { it.isEmpty() }
                                    .map { it.toInt() }
                                val items = Arrays.copyOf(tmp.toIntArray(), MaterialManager.stoneTypes.size)
                                val valks = ArrayList<Int>()
                                for (s in resultSet.getString("valks").replace("[", "").replace("]", "").split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
                                    if (!s.isEmpty()) {
                                        valks.add(Integer.parseInt(s))
                                    }
                                }
                                pData.items = items
                                pData.valks = valks
                                pData.grind = resultSet.getInt("grind")
                            }

                        } catch (sqlException: SQLException) {
                            sqlException.printStackTrace()

                        } finally {
                            if (resultSet != null) {
                                try {
                                    resultSet.close()
                                } catch (ignored: SQLException) {
                                }

                            }

                            if (preparedStatement != null) {
                                try {
                                    preparedStatement.close()
                                } catch (ignored: SQLException) {
                                }

                            }
                        }
                    }
                } else {
                    try {
                        val dataDirectory = Main.getMain().dataFolder
                        val playerDataDirectory = File(dataDirectory, "player_data")

                        if (!playerDataDirectory.exists() && !playerDataDirectory.mkdirs()) {
                        }

                        val playerFile = File(playerDataDirectory, pData.playerName + ".yml")

                        if (!playerFile.exists() && !playerFile.createNewFile()) {
                            Main.getMain().logger.info("Something strange is happening while saving!")
                        }
                        copyDefaults(playerFile)
                        val fc = YamlConfiguration.loadConfiguration(playerFile)
                        pData.failstack = fc.getInt("failstack")
                        val tmp = fc.getString("items")
                            .replace("[", "")
                            .replace("]", "")
                            .split(", ".toRegex())
                            .dropLastWhile { it.isEmpty() }
                            .map { it.toInt() }
                        val items = Arrays.copyOf(tmp.toIntArray(), MaterialManager.stoneTypes.size)
                        val valks = ArrayList<Int>()
                        for (s in fc.getString("valks").replace("[", "").replace("]", "").split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
                            if (!s.isEmpty()) {
                                valks.add(Integer.parseInt(s))
                            }
                        }
                        pData.items = items
                        pData.valks = valks
                        pData.grind = fc.getInt("grind")

                    } catch (ioException: IOException) {
                        System.out.println("Failed to load player " + pData.playerName + ": " + ioException.message)
                    }

                }
            }
        }.runTaskAsynchronously(Main.getMain())
    }

    fun removePlayerData(playername: String) {
        val sqlEnabled = Main.getMain().config.getBoolean("mysql.enabled")
        if (!sqlEnabled) {
            try {
                val dataDirectory = Main.getMain().dataFolder
                val playerDataDirectory = File(dataDirectory, "player_data")

                if (!playerDataDirectory.exists() && !playerDataDirectory.mkdirs()) {
                }

                val playerFile = File(playerDataDirectory, "$playername.yml")

                if (!playerFile.exists() && !playerFile.createNewFile()) {
                }
                playerFile.delete()
            } catch (ioException: IOException) {
                println("Failed to load faction " + playername + ": " + ioException.message)
            }

        } else {
            val database = Main.getDatabase()

            if (!database.checkConnection()) {
                return
            }

            val connection = database.getConnection()
            var preparedStatement: PreparedStatement? = null

            try {
                val queryBuilder = StringBuilder()
                queryBuilder.append("DELETE FROM `enchantmentsenhance` ")
                queryBuilder.append("WHERE `playername` = ?;")

                preparedStatement = connection.prepareStatement(queryBuilder.toString())
                preparedStatement!!.setString(1, playername)
                preparedStatement.executeUpdate()

            } catch (sqlException: SQLException) {
                sqlException.printStackTrace()

            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close()
                    } catch (ignored: SQLException) {
                    }

                }
            }
        }
    }

    private fun copyDefaults(playerFile: File) {
        try {
            val playerConfig = YamlConfiguration.loadConfiguration(playerFile)
            val defConfigStream = InputStreamReader(Main.getMain().getResource("playerdata.yml"))
            val defConfig = YamlConfiguration.loadConfiguration(defConfigStream)
            playerConfig.options().copyDefaults(true)
            playerConfig.defaults = defConfig
            playerConfig.save(playerFile)
        } catch (e: Exception) {
        }

    }
}
