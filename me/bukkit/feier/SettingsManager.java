package me.bukkit.feier;

import java.io.File;
import java.io.IOException;
 
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
 
public class SettingsManager {
 
        private SettingsManager() { }
       
        static SettingsManager instance = new SettingsManager();
       
        public static SettingsManager getInstance() {
                return instance;
        }
       
        Plugin p;
       
        FileConfiguration config;
        File cfile;
       
        FileConfiguration data;
        File dfile;
       
        FileConfiguration lang;
        File langfile;
        
        public void setup(Plugin p) {
                cfile = new File(p.getDataFolder(), "config.yml");
                config = p.getConfig();
                config.options().copyDefaults(true);
                saveConfig();


                if (!p.getDataFolder().exists()) {
                    p.getDataFolder().mkdir();
                }

                if (!p.getDataFolder().exists()) {
                        p.getDataFolder().mkdir();
                }
               
                dfile = new File(p.getDataFolder(), "data.yml");
                
                if (!dfile.exists()) {
                        try {
                                dfile.createNewFile();
                        }
                        catch (IOException e) {
                                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create data.yml!");
                        }
                }
                
                data = YamlConfiguration.loadConfiguration(dfile);
                
                langfile = new File(p.getDataFolder(), "lang.yml");
                lang = YamlConfiguration.loadConfiguration(langfile);
                Language language = new Language();
                language.addCnDefault(this);
        }
       
        public FileConfiguration getData() {
                return data;
        }
       
        public void saveData() {
                try {
                        data.save(dfile);
                }
                catch (IOException e) {
                        Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save data.yml!");
                }
        }
       
        public void reloadData() {
                data = YamlConfiguration.loadConfiguration(dfile);
        }
       
        public FileConfiguration getConfig() {
                return config;
        }
       
        public void saveConfig() {
                try {
                        config.save(cfile);
                }
                catch (IOException e) {
                        Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
                }
        }
       
        public void reloadConfig() {
                config = YamlConfiguration.loadConfiguration(cfile);
        }
       
        public FileConfiguration getLang() {
            return lang;
        }
   
        public void saveLang() {
            try {
                    lang.save(langfile);
            }
            catch (IOException e) {
                    Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save lang.yml!");
            }
        }
   
        public void reloadLang() {
        	lang = YamlConfiguration.loadConfiguration(langfile);
        }
    
        public PluginDescriptionFile getDesc() {
                return p.getDescription();
        }
}