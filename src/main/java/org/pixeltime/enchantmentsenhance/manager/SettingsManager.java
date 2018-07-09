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

package org.pixeltime.enchantmentsenhance.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.locale.LM;

import java.io.File;
import java.io.IOException;

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
        cfile = new File(Main.getMain().getDataFolder(), "config.yml");
        config = Main.getMain().getConfig();
        if (!Main.getMain().getDataFolder().exists()) {
            Main.getMain().getDataFolder().mkdir();
        }

//        dfile = new File(Main.getMain().getDataFolder(), "data.yml");
//        if (!dfile.exists()) {
//            try {
//                dfile.createNewFile();
//            } catch (IOException e) {
//                Bukkit.getServer().getLogger().severe(ChatColor.RED
//                        + "Could not create data.yml!");
//            }
//        }
//        data = YamlConfiguration.loadConfiguration(dfile);


        enchantfile = new File(Main.getMain().getDataFolder(), "enchantments.yml");
        if (!enchantfile.exists()) {
            Main.getMain().saveResource("enchantments.yml", true);
        }
        enchant = YamlConfiguration.loadConfiguration(enchantfile);
        langfile = new File(Main.getMain().getDataFolder(), "lang.yml");
        if (!langfile.exists()) {
            try {
                langfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED
                        + "Could not create lang.yml!");
            }
        }
        lang = YamlConfiguration.loadConfiguration(langfile);
        LM.addLocale();
    }


//    public static void saveData() {
//        try {
//            data.save(dfile);
//        } catch (IOException e) {
//            Bukkit.getServer().getLogger().severe(ChatColor.RED
//                    + "Could not save data.yml!");
//        }
//    }


//    public static void reloadData() {
//        data = YamlConfiguration.loadConfiguration(dfile);
//    }


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
}
