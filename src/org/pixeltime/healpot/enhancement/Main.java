package org.pixeltime.healpot.enhancement;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.pixeltime.healpot.enhancement.events.Menu;
import org.pixeltime.healpot.enhancement.events.blacksmith.SecretBook;
import org.pixeltime.healpot.enhancement.events.blackspirit.Failstack;
import org.pixeltime.healpot.enhancement.events.inventory.Inventory;
import org.pixeltime.healpot.enhancement.events.inventory.Inventory_Gui;
import org.pixeltime.healpot.enhancement.events.inventory.Inventory_Text;
import org.pixeltime.healpot.enhancement.interfaces.Displayable;
import org.pixeltime.healpot.enhancement.listeners.*;
import org.pixeltime.healpot.enhancement.manager.Compatibility;
import org.pixeltime.healpot.enhancement.manager.DataManager;
import org.pixeltime.healpot.enhancement.manager.Permissions;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.UnsafeGlow;
import org.pixeltime.healpot.enhancement.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class Main extends JavaPlugin {
    public static Compatibility compatibility = new Compatibility();
    public static Displayable InventoryText = new Inventory_Text();
    public static Displayable InventoryGui = new Inventory_Gui();

    private static Main main;


    public static Main getMain() {
        return main;
    }


    public void onEnable() {
        main = this;
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


    public boolean onCommand(
        CommandSender sender,
        Command cmd,
        String commandLabel,
        String[] args) {

        // Checking if the command sender is console
        if (!(sender instanceof Player)) {
            Util.sendMessage(SettingsManager.lang.getString(
                "Config.consoleCommand"), sender);
            return true;
        }

        Player player = (Player)sender;

        // Handling commands
        if (cmd.getName().equalsIgnoreCase("enhance")) {
            // If the command does not have arguments
            if (args.length == 0) {
                Util.printHelp(player);
                return true;
            }
            if ((args[0].equalsIgnoreCase("menu")) && Permissions
                .commandEnhance(player)) {
                Menu.showEnhancingMenu(player);
                return true;
            }
            if ((args[0].equalsIgnoreCase("ver") || args[0].equalsIgnoreCase(
                "version")) && Permissions.commandVersion(player)) {
                Util.sendMessage(SettingsManager.lang.getString(
                    "Config.checkingVersion").replaceAll("%version%",
                        getDescription().getVersion()), player);
                return true;
            }
            if (args[0].equalsIgnoreCase("reload") && Permissions.commandReload(
                player)) {
                SettingsManager.reloadConfig();
                SettingsManager.reloadData();
                SettingsManager.reloadLang();
                Util.sendMessage(SettingsManager.lang.getString(
                    "Config.reload"), player);
                return true;
            }
            if (args[0].equalsIgnoreCase("help") && Permissions.commandHelp(
                player)) {
                Util.printHelp(player);
                return true;
            }
            if (args[0].equalsIgnoreCase("inventory") && Permissions
                .commandEnhance(player)) {
                Main.InventoryText.openInventory(player);
                return true;
            }
            if (args[0].equalsIgnoreCase("add") && Permissions.commandAdd(
                player)) {
                if (args.length == 4) {
                    boolean success = false;
                    Player p = null;
                    int stoneType = -1, level = -1;
                    try {
                        p = Bukkit.getServer().getPlayer(args[1]);
                        success = true;
                    }
                    catch (Exception e) {
                        Util.sendMessage(SettingsManager.lang.getString(
                            "Config.playerNotFound"), player);
                        return true;
                    }
                    if (success) {
                        try {
                            stoneType = Integer.parseInt(args[2]);
                            level = Integer.parseInt(args[3]);
                        }
                        catch (Exception e) {
                            Util.sendMessage(SettingsManager.lang.getString(
                                "Config.invalidNumber"), player);
                            return true;
                        }
                    }
                    if (stoneType != -1 && level != -1 && p != null) {
                        Inventory.addLevel(p, stoneType, level);
                        Util.sendMessage("&2✔", sender);
                    }
                    return true;
                }
                else {
                    Util.sendMessage(SettingsManager.lang.getString(
                        "Example.command.add.guide"), player);
                    return true;
                }
            }
            // If the command has one arguments
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("list")) {
                    SecretBook.list(player, 0);
                    return true;
                }
                if (args[0].equalsIgnoreCase("select")) {
                    SecretBook.select(player, 1);
                    return true;
                }
            }
            // If the command has two arguments
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("list")) {
                    SecretBook.list(player, Integer.parseInt(args[1]));
                    return true;
                }
                if (args[0].equalsIgnoreCase("select")) {
                    try {
                        int selected = Integer.parseInt(args[1]);
                        SecretBook.select(player, selected);
                    }
                    catch (NumberFormatException e) {
                        Util.sendMessage(SettingsManager.lang.getString(
                            "Config.invalidNumber"), sender);
                    }
                    return true;
                }
            }
        }
        // No Commands Found
        Util.sendMessage(SettingsManager.lang.getString(
            "Config.invalidCommand"), player);
        return true;
    }


    /**
     * Includes the initialization of the plugin.
     */
    private void registerCore() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ItemDropHandler(), this);
        pm.registerEvents(new PlayerDeathHandler(this), this);
        pm.registerEvents(new PlayerStreamHandler(this), this);
        pm.registerEvents(new MenuHandler(), this);
        pm.registerEvents(new LifeskillingHandler(), this);
        DataManager DM = new DataManager();
        if (getServer().getName().contains("Cauldron") || getServer().getName()
            .contains("MCPC")) {
            Bukkit.getLogger().log(Level.INFO,
                "Enchantments Enhance runs fine on Cauldron/KCauldron.");
        }
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
                "Error in Enchantments Enhance! (Outdated plugin?)");

            Bukkit.getPluginManager().disablePlugin(this);
        }

        if (compatibility.setupSound()) {
            getLogger().info("Enhancement Sound setup was successful!");
        }
        else {

            getLogger().severe("Failed to setup Enhancement Sound!");
            getLogger().severe(
                "Error in Enchantments Enhance! (Outdated plugin?)");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        if (compatibility.setupFirework()) {
            getLogger().info("Enhancement firework setup was successful!");
        }
        else {

            getLogger().severe("Failed to setup Enhancement Firework!");
            getLogger().severe(
                "Error in Enchantments Enhance! (Outdated plugin?)");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }


    @Override
    public List<String> onTabComplete(
        CommandSender sender,
        Command cmd,
        String commandLabel,
        String[] args) {
        List<String> commands = new ArrayList<String>();
        if (cmd.getName().equalsIgnoreCase("enhance")) {
            Player player = (Player)sender;
            List<String> str = new ArrayList<String>();
            if (Permissions.commandHelp(player)) {
                commands.add("help");
            }
            if (Permissions.commandEnhance(player)) {
                commands.add("menu");
                commands.add("list");
                commands.add("select");
                commands.add("inventory");
            }
            if (Permissions.commandReload(player)) {
                commands.add("reload");
            }
            if (Permissions.commandVersion(player)) {
                commands.add("version");
            }
            if (Permissions.commandAdd(player)) {
                commands.add("add");
            }
            if (Permissions.commandLore(player)) {
                commands.add("lore");
            }
            if (args.length == 0) {
                return commands;
            }
            if (args.length == 1) {
                for (int i = 0; i < commands.size(); i++) {
                    if (commands.get(i).startsWith(args[0])) {
                        str.add(commands.get(i));
                    }
                }
                return str;
            }
            if (args[0].equals("add")) {
                if (args.length == 2) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.getName().startsWith(args[1])) {
                            str.add(p.getName());
                        }
                    }
                    return str;
                }
                if (args.length == 3) {
                    Util.sendMessage(SettingsManager.lang.getString(
                        "Example.command.add.stone"), player);
                    return Arrays.asList("0", "1", "2", "3");
                }
                if (args.length == 4) {
                    Util.sendMessage(SettingsManager.lang.getString(
                        "Example.command.add.guide"), player);
                    return null;
                }

            }
        }
        return commands;
    }
}
