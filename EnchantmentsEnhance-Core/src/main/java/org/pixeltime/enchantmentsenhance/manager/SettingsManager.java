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

package org.pixeltime.enchantmentsenhance.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.locale.LM;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class SettingsManager {

    public static FileConfiguration config;
    public static File cfile;
    //    public static FileConfiguration data;
//    public static File dfile;
    public static FileConfiguration lang;
    public static File langfile;
    public static FileConfiguration enchant;
    public static File enchantfile;

    public SettingsManager() {
    }

    public static void setup() {
        // Makes the folder.
        if (!Main.getMain().getDataFolder().exists()) {
            Main.getMain().getDataFolder().mkdir();
        }

        // Config file.
        cfile = new File(Main.getMain().getDataFolder(), "config.yml");
        // Copy default.
        if (!cfile.exists()) {
            Main.getMain().saveResource("config.yml", true);
            config = YamlConfiguration.loadConfiguration(cfile);
        } else {
            // Generating missing configuration.
            config = YamlConfiguration.loadConfiguration(cfile);
            try {
                generate();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        // Enchant file.
        enchantfile = new File(Main.getMain().getDataFolder(), "enchantments.yml");
        // Copy default.
        if (!enchantfile.exists()) {
            Main.getMain().saveResource("enchantments.yml", true);
        }
        enchant = YamlConfiguration.loadConfiguration(enchantfile);

        // Language File.
        langfile = new File(Main.getMain().getDataFolder(), "lang.yml");
        // Make an empty file.
        if (!langfile.exists()) {
            try {
                langfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED
                        + "Could not create lang.yml!");
            }
        }
        lang = YamlConfiguration.loadConfiguration(langfile);

        // Adds the languages.
        LM.addLocale();
    }


    public static void saveConfig() {
        try {
            config.save(cfile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED
                    + "Could not save config.yml!");
        }
    }

    public static void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(cfile);
    }


    public static void saveLang() {
        try {
            lang.save(langfile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED
                    + "Could not save lang.yml!");
        }
    }


    public static void reloadLang() {
        lang = YamlConfiguration.loadConfiguration(langfile);
    }

    public static void saveEnchantments() {
        try {
            enchant.save(enchantfile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED
                    + "Could not save enchantments.yml!");
        }
    }


    public static void reloadEnchantments() {
        enchant = YamlConfiguration.loadConfiguration(enchantfile);
    }


    public static void generate() throws IOException {

        Logger logger = Main.getMain().getLogger();
        logger.info("Updating config to the latest...");
        int settings = 0;
        int addedSettings = 0;

        InputStream defConfigStream = Main.getMain().getResource("config.yml");

        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream));
            for (String string : defConfig.getKeys(true)) {
                if (!config.contains(string)) {
                    config.set(string, defConfig.get(string));
                    addedSettings++;
                }
                if (!config.isConfigurationSection(string))
                    settings++;
            }
        }


        logger.info("Found " + settings + " settings");
        logger.info("Added " + addedSettings + " new settings");
        if (addedSettings > 0) {
            config.save(cfile);
        }
        logger.info("Iteration completed");
    }
}
