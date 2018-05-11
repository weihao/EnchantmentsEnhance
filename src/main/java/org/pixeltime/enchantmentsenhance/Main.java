package org.pixeltime.enchantmentsenhance;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.pixeltime.enchantmentsenhance.events.blacksmith.SecretBook;
import org.pixeltime.enchantmentsenhance.events.blackspirit.Failstack;
import org.pixeltime.enchantmentsenhance.events.blackspirit.Reform;
import org.pixeltime.enchantmentsenhance.events.inventory.Inventory;
import org.pixeltime.enchantmentsenhance.listeners.*;
import org.pixeltime.enchantmentsenhance.manager.*;
import org.pixeltime.enchantmentsenhance.util.AnimalBreeding;
import org.pixeltime.enchantmentsenhance.util.Metrics;
import org.pixeltime.enchantmentsenhance.util.Reflection_V2;

import java.io.File;


/**
 * Main plugin class.
 *
 * @author HealPot
 * @version Mar 30, 2018
 */
public class Main extends JavaPlugin {
    public static final CompatibilityManager compatibility =
            new CompatibilityManager();
    private static Main main;
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

    /**
     * Get an instance of this plugin.
     *
     * @return returns an instance of the plugin.
     */
    public static Main getMain() {
        return main;
    }

    /**
     * When the plugin is enabled, execute following tasks.
     */
    public void onEnable() {
        // Start time.
        final long startTime = System.currentTimeMillis();
        main = this;
        // Checks for update.
        UpdateManager.versionChecker();
        // Save the configuration.
        saveDefaultConfig();
        // Set up the files.
        SettingsManager.setup();
        // Register listeners.
        registerCore();
        // Register all the compatible modules.
        registerCompatibility();
        // When plugin is reloaded, load all the inventory of online players.
        this.getLogger().info(SettingsManager.lang.getString(
                "Config.onLoadingInventory"));
        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Failstack.loadLevels(player);
                SecretBook.loadStorage(player);
                Inventory.loadInventory(player);
            }
        }
        // Plugin fully initialized.
        Bukkit.getServer().getLogger().info(SettingsManager.lang.getString(
                "Config.onEnable"));
        // Display final time at the end of the initialization.
        Bukkit.getLogger().info("EnchantmentsEnhance took " + (System
                .currentTimeMillis() - startTime) + "ms to setup.");
        KM.log();
    }


    /**
     * When the plugin is disabled, execute following tasks.
     */
    public void onDisable() {
        // Write player data to the memory.
        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Failstack.saveLevels(player, false);
                SecretBook.saveStorageToDisk(player, false);
                Inventory.saveInventoryToDisk(player, false);
            }
        }
        // Save all the data to the disk.
        SettingsManager.saveData();
        // Plugin fully disabled.
        Bukkit.getServer().getLogger().info(SettingsManager.lang.getString(
                "Config.onDisable"));
    }


    /**
     * Includes the initialization of the plugin.
     */
    private void registerCore() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ItemDropHandler(), this);
        pm.registerEvents(new PlayerDeathHandler(), this);
        pm.registerEvents(new PlayerStreamHandler(), this);
        pm.registerEvents(new MenuHandler(), this);
        pm.registerEvents(new LifeskillingHandler(), this);
        pm.registerEvents(new Reform(), this);
        // Notify Cauldron and MCPC users.
        if (getServer().getName().contains("Cauldron") || getServer().getName()
                .contains("MCPC")) {
            getLogger().info(
                    "EnchantmentsEnhance runs fine on Cauldron/KCauldron.");
        }
        new AnimalBreeding();
        new DataManager();
        // Start bStats Metrics.
        new Metrics(this);
        commandManager = new CommandManager();
        commandManager.setup();
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
    }
}
