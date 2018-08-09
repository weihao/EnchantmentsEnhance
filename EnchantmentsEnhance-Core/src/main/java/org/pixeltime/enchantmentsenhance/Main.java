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
package org.pixeltime.enchantmentsenhance;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.pixeltime.enchantmentsenhance.api.API;
import org.pixeltime.enchantmentsenhance.gui.GUIListener;
import org.pixeltime.enchantmentsenhance.gui.GUIManager;
import org.pixeltime.enchantmentsenhance.gui.menu.handlers.MenuHandler;
import org.pixeltime.enchantmentsenhance.listener.*;
import org.pixeltime.enchantmentsenhance.manager.*;
import org.pixeltime.enchantmentsenhance.mysql.DataStorage;
import org.pixeltime.enchantmentsenhance.mysql.Database;
import org.pixeltime.enchantmentsenhance.mysql.PlayerStat;
import org.pixeltime.enchantmentsenhance.util.anvil.RepairListener;
import org.pixeltime.enchantmentsenhance.util.events.AnimalBreeding;
import org.pixeltime.enchantmentsenhance.util.metrics.Metrics;
import org.pixeltime.enchantmentsenhance.util.reflection.Reflection_V2;
import org.pixeltime.enchantmentsenhance.version.VersionManager;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;


/**
 * Main plugin class.
 *
 * @author HealPotion
 * @version Mar 30, 2018
 */
public class Main extends JavaPlugin implements Listener {
    private static final CompatibilityManager compatibility =
            new CompatibilityManager();
    private static Database database;
    private static Main main;
    private static API api;
    public CommandManager commandManager;

    /**
     * Default constructor.
     */
    public Main() {
        super();
    }

    /**
     * Mocking constructor.
     *
     * @param loader
     * @param description
     * @param dataFolder
     * @param file
     */
    protected Main(
            JavaPluginLoader loader,
            PluginDescriptionFile description,
            File dataFolder,
            File file) {
        super(loader, description, dataFolder, file);
    }

    public static Database getDb() {
        return database;
    }

    /**
     * Get an instance of this plugin.
     *
     * @return returns an instance of the plugin.
     */
    public static Main getMain() {
        return main;
    }

    public static API getAPI() {
        return api;
    }

    public static WorldGuardPlugin getWorldGuard() {
        Plugin worldguard = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
        if (worldguard != null && worldguard instanceof WorldGuardPlugin && worldguard.isEnabled())
            return (WorldGuardPlugin) worldguard;
        return null;
    }

    /**
     * When the plugin is enabled, execute following tasks.
     */
    public void onEnable() {
        try {
            Scanner sc = new Scanner(getClass().getResourceAsStream("/logo.txt"));
            while (sc.hasNextLine()) {
                Bukkit.getConsoleSender().sendMessage((sc.nextLine()));
            }
        } catch (NullPointerException ex) {
        }

        // Start time.
        final long startTime = System.currentTimeMillis();
        main = this;
        api = new API();
        // Checks for update.
        VersionManager.versionChecker();
        // Save the configuration.
//        saveDefaultConfig();
        // Set up the files.
        SettingsManager.setup();
        // Register listener.
        registerCore();
        // Register all the compatible modules.
        registerCompatibility();
        // Register data.
        commandManager = new CommandManager();
        PluginManager pm = Bukkit.getPluginManager();
        if (SettingsManager.config.getBoolean("enableTableEnchant")) {
            pm.registerEvents(new VanillaEnchantListener(), this);
        }

        // Kotlin setup
        KM.setUp();
        getLogger().info("Kotlin module is enabled: Hello World!");

        DataManager.setUp();
        AnimalBreeding.setUp();

        // When plugin is reloaded, load all the inventory of online players.
        this.getLogger().info(SettingsManager.lang.getString(
                "Config.onLoadingInventory"));
        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (PlayerStat.getPlayerStats(player.getName()) != null) {
                    PlayerStat.removePlayer(player.getName());
                }
                PlayerStat.getPlayers().add(new PlayerStat(player));
            }
        }

        // MySql setup
        if (SettingsManager.config.getBoolean("mysql.enabled")) {
            try {
                database = new Database();
                if (!database.checkConnection()) {
                    return;
                }
                Main.getMain().getLogger().info("MySQL enabled!");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            if (!database.checkConnection()) {
                return;
            }
            try {
                database.createTables();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }

        }


        // Plugin fully initialized.
        getLogger().info(SettingsManager.lang.getString(
                "Config.onEnable"));
        // Display final time at the end of the initialization.
        getLogger().info("EnchantmentsEnhance took " + (System
                .currentTimeMillis() - startTime) + "ms to setup.");

        // Testing
        Bukkit.getPluginManager().registerEvents(this, this);

    }

    /**
     * When the plugin is disabled, execute following tasks.
     */
    public void onDisable() {
        for (PlayerStat fData : PlayerStat.getPlayers()) {
            DataStorage.get().saveStats(fData);
        }

        // Write player data to the memory.
        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (GUIManager.getMap().containsKey(player.getName())) {
                    player.closeInventory();
                }
            }
        }

        // Plugin fully disabled.
        Bukkit.getServer().getLogger().info(SettingsManager.lang.getString(
                "Config.onDisable"));
    }

    /**
     * Includes the initialization of the plugin.
     */
    private void registerCore() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new EnhancedItemListener(), this);
        if (SettingsManager.config.getBoolean("enableLore")) {
            pm.registerEvents(new PlayerDeathListener(), this);
        }
        pm.registerEvents(new PlayerStreamListener(), this);
        if (SettingsManager.config.getBoolean("enableLifeskill")) {
            pm.registerEvents(new LifeskillingListener(), this);
        }
        if (SettingsManager.config.getBoolean("enableAnvilFix")) {
            pm.registerEvents(new RepairListener(), this);
        }
        // Notify Cauldron and MCPC users.
        if (getServer().getName().contains("Cauldron") || getServer().getName()
                .contains("MCPC")) {
            getLogger().info(
                    "EnchantmentsEnhance runs fine on Cauldron/KCauldron.");
        }
        // Start bStats metrics.
        new Metrics(this);
        Bukkit.getPluginManager().registerEvents(new GUIListener(), Main.getMain());
        Bukkit.getPluginManager().registerEvents(new MenuHandler(), Main.getMain());
        Bukkit.getPluginManager().registerEvents(new ItemUseListener(), Main.getMain());
    }

    /**
     * Detects the version of the server is currently running.
     */
    private void registerCompatibility() {
        Main.getMain().getLogger().info("Your server is running version "
                + Reflection_V2.getVERSION());
        Main.getMain().getLogger().info("Your server is running on " + System
                .getProperty("os.name"));
        if (compatibility.setupGlow()) {
            getLogger().info("Enhancement Glower setup was successful!");
        } else {

            getLogger().severe("Failed to setup Enhancement Glower!");
            getLogger().severe(
                    "Error in EnchantmentsEnhance! (Outdated plugin?)");

            Bukkit.getPluginManager().disablePlugin(this);
        }

        if (compatibility.setupSound()) {
            getLogger().info("Enhancement Sound setup was successful!");
        } else {

            getLogger().severe("Failed to setup Enhancement Sound!");
            getLogger().severe(
                    "Error in EnchantmentsEnhance! (Outdated plugin?)");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        if (compatibility.setupFirework()) {
            getLogger().info("Enhancement Firework setup was successful!");
        } else {

            getLogger().severe("Failed to setup Enhancement Firework!");
            getLogger().severe(
                    "Error in EnchantmentsEnhance! (Outdated plugin?)");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        if (SettingsManager.config.getBoolean("enableEconomy") && DM.setupEconomy()) {
            getLogger().info("Enhancement-Vault Hook was successful!");
        }
    }

}
