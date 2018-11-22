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

import com.google.common.base.Charsets
import com.google.common.io.Resources
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.manager.MaterialManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import java.io.IOException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import java.util.*

class Database {

    private val connectionUri : String
    private val username : String
    private val password : String
    private var connection : Connection? = null

    init {
        val hostname = SettingsManager.config.getString("mysql.host")
        val port = SettingsManager.config.getInt("mysql.port")
        val database = SettingsManager.config.getString("mysql.database")

        connectionUri = String.format("jdbc:mysql://%s:%d/%s", hostname, port, database)
        username = SettingsManager.config.getString("mysql.user")
        password = SettingsManager.config.getString("mysql.password")

        try {
            Class.forName("com.mysql.jdbc.Driver")
            connect()

        } catch (sqlException: SQLException) {
            close()
            throw sqlException
        }

    }

    fun getConnection(): Connection = connection!!

    @Throws(SQLException::class)
    private fun connect() {
        if (connection != null) {
            try {
                connection!!.createStatement().execute("SELECT 1;")

            } catch (sqlException: SQLException) {
                if (sqlException.sqlState == "08S01") {
                    try {
                        connection!!.close()

                    } catch (ignored: SQLException) {
                    }

                }
            }

        }

        if (connection == null || connection!!.isClosed) {
            connection = DriverManager.getConnection(connectionUri, username, password)
        }
    }

    private fun close() {
        try {
            if (connection != null && !connection!!.isClosed) {
                connection!!.close()
            }

        } catch (ignored: SQLException) {

        }

        connection = null
    }

    fun checkConnection(): Boolean {
        try {
            connect()
        } catch (sqlException: SQLException) {
            close()
            sqlException.printStackTrace()
            return false
        }

        return true
    }

    @Throws(IOException::class, SQLException::class)
    fun createTables() {
        val resource = Resources.getResource(Main::class.java, "/tables.sql")
        val databaseStructure = Resources.toString(resource, Charsets.UTF_8).split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        if (databaseStructure.isEmpty()) {
            return
        }

        var statement: Statement? = null

        try {
            connection!!.autoCommit = false
            statement = connection!!.createStatement()

            for (query in databaseStructure) {
                val  queryTrim = query.trim { it <= ' ' }

                if (queryTrim.isEmpty()) {
                    continue
                }

                statement!!.execute(queryTrim)
            }

            connection!!.commit()

        } finally {
            connection!!.autoCommit = true

            if (statement != null && !statement.isClosed) {
                statement.close()
            }
        }
    }

    fun doesPlayerExist(playername: String): Boolean {
        if (!checkConnection()) {
            return false
        }

        var count = 0
        var preparedStatement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {
            val queryBuilder = StringBuilder()
            queryBuilder.append("SELECT Count(`playername`) ")
            queryBuilder.append("FROM `enchantmentsenhance` ")
            queryBuilder.append("WHERE `playername` = ? ")
            queryBuilder.append("LIMIT 1;")

            preparedStatement = connection!!.prepareStatement(queryBuilder.toString())
            preparedStatement!!.setString(1, playername)
            resultSet = preparedStatement.executeQuery()

            if (resultSet!!.next()) {
                count = resultSet.getInt(1)
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

        return count > 0
    }

    fun createNewPlayer(name: String) {
        if (!checkConnection()) {
            return
        }

        var preparedStatement: PreparedStatement? = null

        try {
            val queryBuilder = StringBuilder()
            queryBuilder.append("INSERT INTO `enchantmentsenhance` ")
            // Query
            queryBuilder.append("(`id`, `playername`, `failstack`, `items`, `valks`, `grind`) ")
            queryBuilder.append("VALUES ")
            queryBuilder.append("(NULL, ?, 0, ?, ?, ?);")
            preparedStatement = connection!!.prepareStatement(queryBuilder.toString())
            preparedStatement!!.setString(1, name)
            preparedStatement.setString(2, Arrays.toString(IntArray(MaterialManager.stoneTypes.size)))
            preparedStatement.setString(3, ArrayList<Int>().toString())
            preparedStatement.setInt(4, 2)
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
