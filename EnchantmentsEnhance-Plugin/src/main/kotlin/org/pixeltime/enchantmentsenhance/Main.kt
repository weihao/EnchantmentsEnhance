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

package org.pixeltime.enchantmentsenhance

import com.lgou2w.ldk.bukkit.version.MinecraftBukkitVersion
import com.lgou2w.ldk.common.isOrLater
import com.sk89q.worldguard.bukkit.WorldGuardPlugin
import org.bstats.bukkit.Metrics
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.pixeltime.enchantmentsenhance.api.API
import org.pixeltime.enchantmentsenhance.chat.*
import org.pixeltime.enchantmentsenhance.gui.GUIListener
import org.pixeltime.enchantmentsenhance.gui.GUIManager
import org.pixeltime.enchantmentsenhance.gui.menu.handlers.MenuHandler
import org.pixeltime.enchantmentsenhance.listener.*
import org.pixeltime.enchantmentsenhance.manager.*
import org.pixeltime.enchantmentsenhance.mysql.DataStorage
import org.pixeltime.enchantmentsenhance.mysql.Database
import org.pixeltime.enchantmentsenhance.mysql.PlayerStat
import org.pixeltime.enchantmentsenhance.util.ActionBarAPI
import org.pixeltime.enchantmentsenhance.util.anvil.RepairListener
import org.pixeltime.enchantmentsenhance.util.events.AnimalBreeding
import org.pixeltime.enchantmentsenhance.version.VersionManager
import java.io.IOException
import java.sql.SQLException
import java.util.*

class Main : JavaPlugin(), Listener {

    companion object {
        private lateinit var compatibility: CompatibilityManager
        private lateinit var database: Database
        private lateinit var main: Main
        private lateinit var api: API
        private lateinit var announcerManager: AnnouncerManager
        private lateinit var notifierManager: NotifierManager
        private lateinit var commandManager: CommandManager
        fun getMain() = main
        fun getDatabase() = database
        fun getApi() = api
        fun getAnnoucerManager() = announcerManager
        fun getNotifierManager() = notifierManager
        fun getCommandManager() = commandManager
        fun getCompatbility() = compatibility
        fun getWorldGuard(): WorldGuardPlugin? {
            val worldguard = Bukkit.getServer().pluginManager.getPlugin("WorldGuard")
            return if (worldguard is WorldGuardPlugin && worldguard.isEnabled) worldguard else null
        }
    }

    /**
     * When the plugin is enabled, execute following tasks.
     */
    override fun onEnable() {
        // Prints logo.
        try {
            val sc = Scanner(javaClass.getResourceAsStream("/logo.txt"))
            while (sc.hasNextLine()) {
                Bukkit.getConsoleSender().sendMessage(sc.nextLine())
            }
        } catch (ex: NullPointerException) {
        }


        // Start time.
        val startTime = System.currentTimeMillis()

        // Objects initialization.
        main = this
        api = API()
        commandManager = CommandManager()
        compatibility = CompatibilityManager()


        // Set up the files.
        SettingsManager.setUp()

        val pm = Bukkit.getPluginManager()
        pm.registerEvents(EnhancedItemListener(), this)
        if (SettingsManager.config.getBoolean("enableLore")) {
            pm.registerEvents(PlayerDeathListener(), this)
        }
        pm.registerEvents(PlayerStreamListener(), this)
        if (SettingsManager.config.getBoolean("enableLifeskill")) {
            pm.registerEvents(LifeskillingListener(), this)
        }
        if (SettingsManager.config.getBoolean("enableAnvilFix")) {
            pm.registerEvents(RepairListener(), this)
        }
        if (SettingsManager.config.getBoolean("enableTableEnchant")) {
            pm.registerEvents(VanillaEnchantListener(), this)
        }
        if (SettingsManager.config.getBoolean("enablePreventFireworkDamage")) {
            pm.registerEvents(FireworkListener(), this)
        }
        if (!(SettingsManager.config.getBoolean("enableAnvil")
                        && SettingsManager.config.getBoolean("enableAnvilRename")
                        && SettingsManager.config.getBoolean("enableAnvilRepair"))) {
            pm.registerEvents(AnvilRestrict(), this)
        }

        try {
            // Checks for update.
            if (VersionManager.isUpToDate()) {
                logger.info(SettingsManager.lang.getString("update.updateToDate"))
            } else {
                logger.warning(SettingsManager.lang.getString("update.outdated"))
                object : BukkitRunnable() {
                    override fun run() {
                        for (p in Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission("Enchantments.*") || p.hasPermission("*") || p.isOp) {
                                Main.getNotifierManager().call(Notification(p, SettingsManager.lang.getString("update.updateToDate")))
                            }
                        }
                    }
                }.runTaskTimer(this, 120L, 36000L)
            }
        } catch (ex: IllegalArgumentException) {
            // Debugging version.
        }


        // Notify Cauldron and MCPC users.
        if (server.name.contains("Cauldron") || server.name
                        .contains("MCPC")) {
            logger.info(
                    "EnchantmentsEnhance runs fine on Cauldron/KCauldron.")
        }
        // Start bStats metrics.
        Metrics(this)

        Bukkit.getPluginManager().registerEvents(GUIListener(), Main.getMain())
        Bukkit.getPluginManager().registerEvents(MenuHandler(), Main.getMain())
        Bukkit.getPluginManager().registerEvents(ItemUseListener(), Main.getMain())
        // Register all the compatible modules.
        registerCompatibility()


        MaterialManager.setUp()
        ActionBarAPI.setUp()
        DataManager.setUp()
        AnimalBreeding.setUp()
        PackageManager.initializeAll()
        DropManager.setUp()
        MVdWPlaceholderAPI.setUp()


        // When plugin is reloaded, load all the inventory of online players.
        this.logger.info(SettingsManager.lang.getString(
                "config.onLoadingInventory"))
        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            for (player in Bukkit.getOnlinePlayers()) {
                if (PlayerStat.getPlayerStats(player.name) != null) {
                    PlayerStat.removePlayer(player.name)
                }
                PlayerStat.players.add(PlayerStat(player))
            }
        }

        // MySql setup
        if (SettingsManager.config.getBoolean("mysql.enabled")) {
            try {
                database = Database()
                if (!database.checkConnection()) {
                    return
                }
                Main.getMain().logger.info("MySQL enabled!")
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: SQLException) {
                e.printStackTrace()
            }

            if (!database.checkConnection()) {
                return
            }
            try {
                database.createTables()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: SQLException) {
                e.printStackTrace()
            }

        }
        // Annoucer setup
        if (SettingsManager.config.getBoolean("enableFancyAnnouncer")) {
            if (MinecraftBukkitVersion.CURRENT.isOrLater(MinecraftBukkitVersion.V1_9_R1)) {
                announcerManager = AnnouncerManager(Announcer_BossBar())
            } else {
                announcerManager = AnnouncerManager(Announcer_ActionBar())
            }
        } else {
            announcerManager = AnnouncerManager(Announcer_Chat())
        }

        // Notifier setup
        if (SettingsManager.config.getBoolean("enableFancyNotify")) {
            notifierManager = NotifierManager(Notifier_TitleBar())
        } else {
            notifierManager = NotifierManager(Notifier_Chat())
        }

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            //Registering placeholder will be use here
            PlaceholderListener().register()
        }

        // Plugin fully initialized.
        logger.info(SettingsManager.lang.getString(
                "config.onEnable"))
        // Display final time at the end of the initialization.
        logger.info("EnchantmentsEnhance took " + (System
                .currentTimeMillis() - startTime) + "ms to setup.")


        Bukkit.getPluginManager().registerEvents(this, this)

    }

    /**
     * When the plugin is disabled, execute following tasks.
     */
    override fun onDisable() {
        for (fData in PlayerStat.players) {
            DataStorage.get().saveStats(fData)
        }

        // Write player data to the memory.
        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            for (player in Bukkit.getOnlinePlayers()) {
                if (GUIManager.getMap().containsKey(player.name)) {
                    player.closeInventory()
                }
            }
        }

        // Plugin fully disabled.
        Bukkit.getServer().logger.info(SettingsManager.lang.getString(
                "config.onDisable"))
    }

    /**
     * Detects the version of the server is currently running.
     */
    private fun registerCompatibility() {
        Main.getMain().logger.info("Your server is running version " + MinecraftBukkitVersion.CURRENT.version)
        Main.getMain().logger.info("Your server is running on " + System.getProperty("os.name"))
        if (compatibility.setupGlow()) {
            logger.info("Enhancement Glower setup was successful!")
        } else {

            logger.severe("Failed to setup Enhancement Glower!")
            logger.severe(
                    "Error in EnchantmentsEnhance! (Outdated plugin?)")

            Bukkit.getPluginManager().disablePlugin(this)
        }

        if (compatibility.setupSound()) {
            logger.info("Enhancement Sound setup was successful!")
        } else {

            logger.severe("Failed to setup Enhancement Sound!")
            logger.severe(
                    "Error in EnchantmentsEnhance! (Outdated plugin?)")
            Bukkit.getPluginManager().disablePlugin(this)
        }

        if (compatibility.setupFirework()) {
            logger.info("Enhancement Firework setup was successful!")
        } else {

            logger.severe("Failed to setup Enhancement Firework!")
            logger.severe(
                    "Error in EnchantmentsEnhance! (Outdated plugin?)")
            Bukkit.getPluginManager().disablePlugin(this)
        }

        if (SettingsManager.config.getBoolean("enableEconomy") && DependencyManager.setupEconomy()) {
            logger.info("Enhancement-Vault Hook was successful!")
        }
    }
}
