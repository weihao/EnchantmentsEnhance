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
package org.pixeltime.enchantmentsenhance;

import com.lgou2w.ldk.bukkit.cmd.CommandFeedback;
import com.lgou2w.ldk.bukkit.cmd.CommandManager;
import com.lgou2w.ldk.bukkit.cmd.DefaultCommandManager;
import com.lgou2w.ldk.bukkit.cmd.SimpleChineseCommandFeedback;
import com.lgou2w.ldk.bukkit.cmd.SimpleCommandFeedback;
import com.lgou2w.ldk.bukkit.version.MinecraftBukkitVersion;
import com.lgou2w.ldk.common.Enums;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.bukkit.scheduler.BukkitRunnable;
import org.pixeltime.enchantmentsenhance.api.API;
import org.pixeltime.enchantmentsenhance.chat.Announcer_ActionBar;
import org.pixeltime.enchantmentsenhance.chat.Announcer_BossBar;
import org.pixeltime.enchantmentsenhance.chat.Announcer_Chat;
import org.pixeltime.enchantmentsenhance.chat.Notification;
import org.pixeltime.enchantmentsenhance.chat.Notifier_Chat;
import org.pixeltime.enchantmentsenhance.chat.Notifier_TitleBar;
import org.pixeltime.enchantmentsenhance.command.EnhanceCommand;
import org.pixeltime.enchantmentsenhance.compatibility.EnchantmentGlow;
import org.pixeltime.enchantmentsenhance.enums.LangType;
import org.pixeltime.enchantmentsenhance.gui.GUIListener;
import org.pixeltime.enchantmentsenhance.gui.GUIManager;
import org.pixeltime.enchantmentsenhance.gui.menu.handlers.MenuHandler;
import org.pixeltime.enchantmentsenhance.listener.AnvilRestrict;
import org.pixeltime.enchantmentsenhance.listener.EnhancedItemListener;
import org.pixeltime.enchantmentsenhance.listener.FireworkListener;
import org.pixeltime.enchantmentsenhance.listener.ItemUseListener;
import org.pixeltime.enchantmentsenhance.listener.LifeskillingListener;
import org.pixeltime.enchantmentsenhance.listener.MVdWPlaceholderAPI;
import org.pixeltime.enchantmentsenhance.listener.PlaceholderListener;
import org.pixeltime.enchantmentsenhance.listener.PlayerDeathListener;
import org.pixeltime.enchantmentsenhance.listener.PlayerStreamListener;
import org.pixeltime.enchantmentsenhance.listener.VanillaEnchantListener;
import org.pixeltime.enchantmentsenhance.locale.LocaleManager;
import org.pixeltime.enchantmentsenhance.manager.AnnouncerManager;
import org.pixeltime.enchantmentsenhance.manager.DataManager;
import org.pixeltime.enchantmentsenhance.manager.DependencyManager;
import org.pixeltime.enchantmentsenhance.manager.DropManager;
import org.pixeltime.enchantmentsenhance.manager.MaterialManager;
import org.pixeltime.enchantmentsenhance.manager.NotifierManager;
import org.pixeltime.enchantmentsenhance.manager.PackageManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.mysql.DataStorage;
import org.pixeltime.enchantmentsenhance.mysql.Database;
import org.pixeltime.enchantmentsenhance.mysql.PlayerStat;
import org.pixeltime.enchantmentsenhance.util.ActionBarAPI;
import org.pixeltime.enchantmentsenhance.util.anvil.RepairListener;
import org.pixeltime.enchantmentsenhance.util.events.AnimalBreeding;
import org.pixeltime.enchantmentsenhance.version.VersionManager;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;


/**
 * Main plugin class.
 */
public class Main extends JavaPlugin implements Listener {
    private static Database database;
    private static Main main;
    private static API api;
    private static AnnouncerManager announcerManager;
    private static NotifierManager notifierManager;
    private static CommandManager commandManager;

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

    public static Database getDatabase() {
        return database;
    }

    public static API getApi() {
        return api;
    }

    /**
     * Get an instance of this plugin.
     *
     * @return returns an instance of the plugin.
     */
    public static Main getMain() {
        return main;
    }


    public static AnnouncerManager getAnnoucerManager() {
        return announcerManager;
    }

    public static NotifierManager getNotifierManager() {
        return notifierManager;
    }

    /**
     * When the plugin is enabled, execute following tasks.
     */
    public void onEnable() {
        // Prints logo.
        try {
            Scanner sc = new Scanner(getClass().getResourceAsStream("/logo" +
                    ".txt"));
            while (sc.hasNextLine()) {
                Bukkit.getConsoleSender().sendMessage((sc.nextLine()));
            }
        } catch (NullPointerException ex) {
        }

        // Start time.
        final long startTime = System.currentTimeMillis();

        // Objects initialization.
        main = this;
        api = new API();

        // Set up the files.
        SettingsManager.setUp();

        // Command
        LangType langType = Enums.ofOriginNotNull(LangType.class, LocaleManager.getLang());
        CommandFeedback feedback;
        if (langType == LangType.ZH_CN) feedback = new SimpleChineseCommandFeedback();
        else feedback = new SimpleCommandFeedback();
        commandManager = new DefaultCommandManager(this);
        commandManager.getTransforms().addDefaultTransforms();
        commandManager.getCompletes().addDefaultCompletes();
        commandManager.setGlobalFeedback(feedback);
        commandManager.registerCommand(new EnhanceCommand(this));

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
        if (SettingsManager.config.getBoolean("enableTableEnchant")) {
            pm.registerEvents(new VanillaEnchantListener(), this);
        }
        if (SettingsManager.config.getBoolean("enablePreventFireworkDamage")) {
            pm.registerEvents(new FireworkListener(), this);
        }
        if (!(SettingsManager.config.getBoolean("enableAnvil")
                && SettingsManager.config.getBoolean("enableAnvilRename")
                && SettingsManager.config.getBoolean("enableAnvilRepair"))) {
            pm.registerEvents(new AnvilRestrict(), this);
        }

        this.getServer().getScheduler().runTaskAsynchronously(this, () ->
        {
            try {
                // Checks for update.
                if (VersionManager.isUpToDate()) {
                    getLogger().info(SettingsManager.lang.getString("update" +
                            ".updateToDate"));
                } else {
                    getLogger().warning(SettingsManager.lang.getString(
                            "update.outdated"));
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.hasPermission("Enchantments.*") || p.hasPermission("*") || p.isOp()) {
                                    Main.getNotifierManager().call(new Notification(p, SettingsManager.lang.getString("update.updateToDate")));
                                }
                            }
                        }
                    }.runTaskTimer(Main.getMain(), 120L, 36000L);
                }
            } catch (
                    Exception ex) {
                // Debugging version.
            }
        });

        // Notify Cauldron and MCPC users.
        if (getServer().getName().contains("Cauldron") || getServer().getName()
                .contains("MCPC")) {
            getLogger().info(
                    "EnchantmentsEnhance runs fine on Cauldron/KCauldron.");
        }

        try {
            // Start bStats metrics.
            new Metrics(this);
        } catch (Exception ex) {
            getLogger().info("Failed to setup metrics!");
        } catch (ExceptionInInitializerError error) {
            getLogger().info("Debugging mode!");
        }


        Bukkit.getPluginManager().registerEvents(new GUIListener(),
                Main.getMain());
        Bukkit.getPluginManager().registerEvents(new MenuHandler(),
                Main.getMain());
        Bukkit.getPluginManager().registerEvents(new ItemUseListener(),
                Main.getMain());
        // Register all the compatible modules.
        registerCompatibility();


        MaterialManager.setUp();
        ActionBarAPI.setUp();
        DataManager.setUp();
        AnimalBreeding.setUp();
        PackageManager.initializeAll();
        DropManager.setUp();
        MVdWPlaceholderAPI.setUp();


        // When plugin is reloaded, load all the inventory of online players.
        this.getLogger().info(SettingsManager.lang.getString(
                "config.onLoadingInventory"));
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
        // Annoucer setup
        if (SettingsManager.config.getBoolean("enableFancyAnnouncer")) {
            if (MinecraftBukkitVersion.isV19OrLater()) {
                announcerManager =
                        new AnnouncerManager(new Announcer_BossBar());
            } else {
                announcerManager =
                        new AnnouncerManager(new Announcer_ActionBar());
            }
        } else {
            announcerManager = new AnnouncerManager(new Announcer_Chat());
        }

        // Notifier setup
        if (SettingsManager.config.getBoolean("enableFancyNotify")) {
            notifierManager = new NotifierManager(new Notifier_TitleBar());
        } else {
            notifierManager = new NotifierManager(new Notifier_Chat());
        }

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            //Registering placeholder will be use here
            new PlaceholderListener().register();
        }

        // Plugin fully initialized.
        getLogger().info(SettingsManager.lang.getString(
                "config.onEnable"));
        // Display final time at the end of the initialization.
        getLogger().info("EnchantmentsEnhance took " + (System
                .currentTimeMillis() - startTime) + "ms to setup.");


        Bukkit.getPluginManager().registerEvents(this, this);

    }

    /**
     * When the plugin is disabled, execute following tasks.
     */
    public void onDisable() {

        commandManager.unregisterCommands();
        commandManager = null;

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
                "config.onDisable"));
    }


    /**
     * Detects the version of the server is currently running.
     */
    private void registerCompatibility() {
        Main.getMain().getLogger().info("Your server is running version " + MinecraftBukkitVersion.getCURRENT().getVersion());
        Main.getMain().getLogger().info("Your server is running on " + System.getProperty("os.name"));

        try {
            EnchantmentGlow.Factory.init();
            getLogger().info("Enhancement Glower setup was successful!");
        } catch (Exception e) {
            getLogger().severe("Failed to setup Enhancement Glower!");
            getLogger().severe("Error in EnchantmentsEnhance! (Outdated plugin?)");
            getLogger().log(Level.SEVERE, e.getMessage(), e);
            Bukkit.getPluginManager().disablePlugin(this);
        }
        if (SettingsManager.config.getBoolean("enableEconomy") && DependencyManager.setupEconomy()) {
            getLogger().info("Enhancement-Vault Hook was successful!");
        }
    }
}
