package com.github.healpot.plugin.enhancement.main;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import com.github.healpot.plugin.enhancement.main.language.Language_Chinese;
import com.github.healpot.plugin.enhancement.main.language.Language_English;

public class SettingsManager {

    private SettingsManager() {
    }

    public static FileConfiguration config;
    public static File cfile;

    public static FileConfiguration data;
    public static File dfile;

    public static FileConfiguration lang;
    public static File langfile;


    public static void setup(Main m) {
        cfile = new File(m.getDataFolder(), "config.yml");
        config = m.getConfig();
        if (!m.getDataFolder().exists()) {
            m.getDataFolder().mkdir();
        }

        dfile = new File(m.getDataFolder(), "data.yml");

        if (!dfile.exists()) {
            try {
                dfile.createNewFile();
            }
            catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED
                    + "Could not create data.yml!");
            }
        }
        data = YamlConfiguration.loadConfiguration(dfile);

        langfile = new File(m.getDataFolder(), "lang.yml");
        lang = YamlConfiguration.loadConfiguration(langfile);

        if (config.getString("language") == "CN") {
            Language_Chinese.addLang();
        }
        else {
            Language_English.addLang();
        }
    }


    public static void saveData() {
        try {
            data.save(dfile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED
                + "Could not save data.yml!");
        }
    }


    public static void reloadData() {
        data = YamlConfiguration.loadConfiguration(dfile);
    }


    public static void saveConfig() {
        try {
            config.save(cfile);
        }
        catch (IOException e) {
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
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED
                + "Could not save lang.yml!");
        }
    }


    public static void reloadLang() {
        lang = YamlConfiguration.loadConfiguration(langfile);
    }
}
