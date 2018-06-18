/*
 *     Copyright (C) 2017-Present HealPotion
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

import org.bukkit.ChatColor
import org.bukkit.event.Listener
import org.pixeltime.enchantmentsenhance.Main

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class VersionManager : Listener {
    companion object {
        @JvmStatic
        fun versionChecker() {
            try {
                val connection = URL(
                        "https://api.spigotmc.org/legacy/update.php?resource=51635")
                        .openConnection() as HttpURLConnection
                connection.doOutput = true
                connection.requestMethod = "POST"
                connection.outputStream.write("GET".toByteArray(charset("UTF-8")))
                val spigotVer = Version(BufferedReader(InputStreamReader(connection.inputStream)).readLine())
                val currVer = Version(Main::class.java.`package`.implementationVersion)

                if (currVer > spigotVer) {
                    Main.getMain().server.consoleSender.sendMessage("[EnchantmentsEnhance] " + ChatColor.GREEN + "Enchantments Enhance is UP-TO-DATE")
                } else {
                    Main.getMain().server.consoleSender.sendMessage("[EnchantmentsEnhance] " + ChatColor.RED + "EnchantmentsEnhance is OUTDATED!")
                }
            } catch (e: IOException) {
                Main.getMain().server.consoleSender.sendMessage("[EnchantmentsEnhance] " + ChatColor.RED + "ERROR: Could not make connection to SpigotMC.org")
            }

        }
    }
}
