/*
 *     Copyright (C) 2017-Present 25 (https://github.com/25)
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

package org.pixeltime.enchantmentsenhance.version

import org.pixeltime.enchantmentsenhance.Main
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class VersionManager {
    companion object {
        @JvmStatic
        fun getCurrentVersion(): String {
            return Main::class.java.`package`.implementationVersion
        }

        @JvmStatic
        fun getLatestVersion(): String {
            val connection = URL(
                    "https://api.spigotmc.org/legacy/update.php?resource=59555")
                    .openConnection() as HttpURLConnection
            connection.doOutput = true
            connection.requestMethod = "POST"
            connection.outputStream.write("GET".toByteArray(charset("UTF-8")))
            return BufferedReader(InputStreamReader(connection.inputStream)).readLine()
        }

        @JvmStatic
        fun isUpToDate(): Boolean {
            val spigotVer = Version(getLatestVersion())
            val currVer = Version(getCurrentVersion())
            return currVer >= spigotVer
        }
    }
}
