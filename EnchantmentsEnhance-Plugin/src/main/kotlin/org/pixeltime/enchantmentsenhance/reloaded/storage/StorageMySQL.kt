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

package org.pixeltime.enchantmentsenhance.reloaded.storage

import com.lgou2w.ldk.hikari.ConnectionFactory
import com.lgou2w.ldk.hikari.MySQLConnectionFactory
import com.lgou2w.ldk.hikari.buildConfiguration
import org.pixeltime.enchantmentsenhance.reloaded.model.PlayerStatus
import java.sql.Connection

class StorageMySQL : Storage {

    private var cf : ConnectionFactory? = null

    override fun init() {
        cf = MySQLConnectionFactory(buildConfiguration {
            poolName = "EnchantmentsEnhance-Database"
            address = "localhost:3306"
            database = "ee"
            username = "root"
            password = "password"
            property = "useSSL" to true
            // TODO Database
        })
        cf?.initialize()
    }

    override fun close() {
        cf?.shutdown()
        cf = null
    }

    private fun openConnection(): Connection {
        return cf?.openSession()
               ?: throw IllegalStateException("The connection factory not yet initialized.")
    }

    override fun getStatus(playerName: String): PlayerStatus? {
        TODO()
    }
}
