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

package org.pixeltime.enchantmentsenhance.manager

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.locale.LocaleManager
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

object SettingsManager {

    lateinit var config: FileConfiguration
    lateinit var cfile: File
    //    public static FileConfiguration data;
    //    public static File dfile;
    lateinit var lang: FileConfiguration
    lateinit var langfile: File
    lateinit var enchant: FileConfiguration
    lateinit var enchantfile: File

    fun setUp() {
        // Makes the folder.
        if (!Main.getMain().dataFolder.exists()) {
            Main.getMain().dataFolder.mkdir()
        }

        // Config file.
        cfile = File(Main.getMain().dataFolder, "config.yml")
        // Copy default.
        if (!cfile.exists()) {
            Main.getMain().saveResource("config.yml", true)
            config = YamlConfiguration.loadConfiguration(cfile)
        } else {
            // Generating missing configuration.
            config = YamlConfiguration.loadConfiguration(cfile)
            try {
                generateConfig()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }


        // Enchant file.
        enchantfile = File(Main.getMain().dataFolder, "enchantments.yml")
        // Copy default.
        if (!enchantfile.exists()) {
            Main.getMain().saveResource("enchantments.yml", true)
            enchant = YamlConfiguration.loadConfiguration(enchantfile)
        } else {
            enchant = YamlConfiguration.loadConfiguration(enchantfile)
            try {
                generateEnchantments()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

        // Language File.
        langfile = File(Main.getMain().dataFolder, "lang.yml")
        // Make an empty file.
        if (!langfile.exists()) {
            try {
                langfile.createNewFile()
            } catch (e: IOException) {
                Bukkit.getServer().logger.severe(ChatColor.RED.toString() + "Could not create lang.yml!")
            }

        }
        lang = YamlConfiguration.loadConfiguration(langfile)

        // Adds the languages.
        LocaleManager.addLocale()
    }

    fun saveConfig() {
        try {
            config.save(cfile)
        } catch (e: IOException) {
            Bukkit.getServer().logger.severe(ChatColor.RED.toString() + "Could not save config.yml!")
        }

    }

    fun reloadConfig() {
        config = YamlConfiguration.loadConfiguration(cfile)
    }


    fun saveLang() {
        try {
            lang.save(langfile)
        } catch (e: IOException) {
            Bukkit.getServer().logger.severe(ChatColor.RED.toString() + "Could not save lang.yml!")
        }

    }

    fun reloadLang() {
        lang = YamlConfiguration.loadConfiguration(langfile)
    }

    fun saveEnchantments() {
        try {
            enchant.save(enchantfile)
        } catch (e: IOException) {
            Bukkit.getServer().logger.severe(ChatColor.RED.toString() + "Could not save enchantments.yml!")
        }

    }

    fun reloadEnchantments() {
        enchant = YamlConfiguration.loadConfiguration(enchantfile)
    }

    @Throws(IOException::class)
    fun generateConfig() {

        val logger = Main.getMain().logger
        logger.info("Updating config to the latest...")
        var settings = 0
        var addedSettings = 0

        val defConfigStream = Main.getMain().getResource("config.yml")

        if (defConfigStream != null) {
            val defConfig = YamlConfiguration.loadConfiguration(InputStreamReader(defConfigStream))
            for (string in defConfig.getKeys(true)) {
                if (!config.contains(string)) {
                    config.set(string, defConfig.get(string))
                    addedSettings++
                }
                if (!config.isConfigurationSection(string))
                    settings++
            }
        }


        logger.info("Found $settings config settings")
        logger.info("Added $addedSettings new config settings")
        if (addedSettings > 0) {
            config.save(cfile)
        }
    }

    @Throws(IOException::class)
    fun generateEnchantments() {
        val logger = Main.getMain().logger
        logger.info("Updating enchantments to the latest...")
        var settings = 0
        var addedSettings = 0

        val defConfigStream = Main.getMain().getResource("enchantments.yml")

        if (defConfigStream != null) {
            val defConfig = YamlConfiguration.loadConfiguration(InputStreamReader(defConfigStream))
            for (string in defConfig.getKeys(true)) {
                if (!enchant.contains(string)) {
                    enchant.set(string, defConfig.get(string))
                    addedSettings++
                }
                if (!enchant.isConfigurationSection(string))
                    settings++
            }
        }

        logger.info("Found $settings enchantment settings")
        logger.info("Added $addedSettings new enchantment settings")
        if (addedSettings > 0) {
            enchant.save(enchantfile)
        }
    }
}
