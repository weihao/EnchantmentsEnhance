package org.pixeltime.enchantmentsenhance.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.util.FileUtil;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.locale.LocaleManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static void setUp() {
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
                generateConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        // Enchant file.
        enchantfile = new File(Main.getMain().getDataFolder(), "enchantments.yml");
        // Copy default.
        if (!enchantfile.exists()) {
            Main.getMain().saveResource("enchantments.yml", true);
            enchant = YamlConfiguration.loadConfiguration(enchantfile);
        } else {
            enchant = YamlConfiguration.loadConfiguration(enchantfile);
            try {
                generateEnchantments();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
        LocaleManager.addLocale();
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


    public static void generateConfig() throws IOException {

        Logger logger = Main.getMain().getLogger();
        logger.info("Updating config to the latest...");
        int settings = 0;
        int addedSettings = 0;

        InputStream defConfigStream = Main.getMain().getResource("config.yml");

        backup_config();
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream));
            for (String string : defConfig.getKeys(true)) {
                if (string.startsWith("enhance")) {
                    continue;
                }
                if (!config.contains(string)) {
                    config.set(string, defConfig.get(string));
                    addedSettings++;
                }
                if (!config.isConfigurationSection(string))
                    settings++;
            }
        }


        logger.info("Found " + settings + " config settings");
        logger.info("Added " + addedSettings + " new config settings");
        if (addedSettings > 0) {
            config.save(cfile);
        }
    }

    private static void backup_config() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd@HH:mm:ss");
        String timestamp = formatter.format(date);
        FileUtil.copy(cfile, new File(cfile.getName() + "." + timestamp));
    }

    public static void generateEnchantments() throws IOException {
        Logger logger = Main.getMain().getLogger();
        logger.info("Updating enchantments to the latest...");
        int settings = 0;
        int addedSettings = 0;

        InputStream defConfigStream = Main.getMain().getResource("enchantments.yml");

        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream));
            for (String string : defConfig.getKeys(true)) {
                if (!enchant.contains(string)) {
                    enchant.set(string, defConfig.get(string));
                    addedSettings++;
                }
                if (!enchant.isConfigurationSection(string))
                    settings++;
            }
        }

        logger.info("Found " + settings + " enchantment settings");
        logger.info("Added " + addedSettings + " new enchantment settings");
        if (addedSettings > 0) {
            enchant.save(enchantfile);
        }
    }

}
