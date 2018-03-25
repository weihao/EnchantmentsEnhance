package org.pixeltime.healpot.enhancement;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.pixeltime.healpot.enhancement.events.blacksmith.SecretBook;
import org.pixeltime.healpot.enhancement.events.blackspirit.Failstack;
import org.pixeltime.healpot.enhancement.events.blackspirit.Reform;
import org.pixeltime.healpot.enhancement.events.inventory.Inventory;
import org.pixeltime.healpot.enhancement.listeners.ItemDropHandler;
import org.pixeltime.healpot.enhancement.listeners.LifeskillingHandler;
import org.pixeltime.healpot.enhancement.listeners.MenuHandler;
import org.pixeltime.healpot.enhancement.listeners.PlayerDeathHandler;
import org.pixeltime.healpot.enhancement.listeners.PlayerStreamHandler;
import org.pixeltime.healpot.enhancement.manager.CommandManager;
import org.pixeltime.healpot.enhancement.manager.CompatibilityManager;
import org.pixeltime.healpot.enhancement.manager.DataManager;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.manager.UpdateManager;
import org.pixeltime.healpot.enhancement.util.AnimalBreeding;
import org.pixeltime.healpot.enhancement.util.Metrics;

public class Main extends JavaPlugin {
    public static final CompatibilityManager compatibility =
        new CompatibilityManager();
    private static Main main;
    public CommandManager commandManager;


    public static Main getMain() {
        return main;
    }


    public void onEnable() {
        final long startTime = System.currentTimeMillis();
        main = this;
        UpdateManager.versionChecker();
        saveDefaultConfig();
        SettingsManager.setup(this);
        registerCore();
        registerCompatibility();
        Bukkit.getServer().getLogger().info(SettingsManager.lang.getString(
            "Config.onEnable"));
        if (Bukkit.getOnlinePlayers() != null) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Failstack.loadLevels(player);
                SecretBook.loadStorage(player);
                Inventory.loadInventory(player);
            }
        }
        Bukkit.getLogger().info("EnchantmentsEnhance took " + (System
            .currentTimeMillis() - startTime) + "ms to setup.");
    }


    public void onDisable() {
        if (Bukkit.getOnlinePlayers() != null) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Failstack.saveLevels(player, false);
                SecretBook.saveStorageToDisk(player, false);
                Inventory.saveInventoryToDisk(player, false);
            }
        }
        SettingsManager.saveData();
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
        if (getServer().getName().contains("Cauldron") || getServer().getName()
            .contains("MCPC")) {
            getLogger().info(
                "EnchantmentsEnhance runs fine on Cauldron/KCauldron.");
        }
        new AnimalBreeding();
        new DataManager();
        new Metrics(this);
        this.commandManager = new CommandManager();
        commandManager.setup();
    }


    /**
     * Detects the version of the server is currently running.
     */
    private void registerCompatibility() {
        if (compatibility.setupGlow()) {
            getLogger().info("Enhancement Glower setup was successful!");
        }
        else {

            getLogger().severe("Failed to setup Enhancement Glower!");
            getLogger().severe(
                "Error in EnchantmentsEnhance! (Outdated plugin?)");

            Bukkit.getPluginManager().disablePlugin(this);
        }

        if (compatibility.setupSound()) {
            getLogger().info("Enhancement Sound setup was successful!");
        }
        else {

            getLogger().severe("Failed to setup Enhancement Sound!");
            getLogger().severe(
                "Error in EnchantmentsEnhance! (Outdated plugin?)");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        if (compatibility.setupFirework()) {
            getLogger().info("Enhancement Firework setup was successful!");
        }
        else {

            getLogger().severe("Failed to setup Enhancement Firework!");
            getLogger().severe(
                "Error in EnchantmentsEnhance! (Outdated plugin?)");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }
}
