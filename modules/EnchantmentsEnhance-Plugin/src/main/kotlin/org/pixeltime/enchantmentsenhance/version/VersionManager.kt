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
            return try {
                val spigotVer = Version(getLatestVersion())
                val currVer = Version(getCurrentVersion())
                currVer >= spigotVer
            } catch (ex: Exception) {
                true
            }
        }
    }
}
