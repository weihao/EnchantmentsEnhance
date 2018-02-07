package com.github.healpot.plugin.enhancement.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.healpot.plugin.enhancement.blacksmith.SecretBook;
import com.github.healpot.plugin.enhancement.blackspirit.Enhance;
import com.github.healpot.plugin.enhancement.effect.Broadcast;
import com.github.healpot.plugin.enhancement.effect.SpawnFirework;
import com.github.healpot.plugin.enhancement.failstack.Failstack;
import com.github.healpot.plugin.enhancement.handler.ItemDropHandler;
import com.github.healpot.plugin.enhancement.handler.LifeskillingHandler;
import com.github.healpot.plugin.enhancement.handler.MenuHandler;
import com.github.healpot.plugin.enhancement.handler.PlayerDeathHandler;
import com.github.healpot.plugin.enhancement.handler.PlayerStreamHandler;
import com.github.healpot.plugin.enhancement.lore.Lore;
import com.github.healpot.plugin.enhancement.main.util.Util;
import com.github.healpot.plugin.enhancement.modular.Compatibility;
import com.github.healpot.plugin.enhancement.player.Inventory;
import com.github.healpot.plugin.enhancement.visual.Menu;

public class Main extends JavaPlugin {
    public Failstack failstack = new Failstack();
    public SpawnFirework spawnFirework = new SpawnFirework();
    public Enhance enhance = new Enhance();
    public Menu menu = new Menu();
    public Lore data = new Lore();
    public Compatibility compatibility = new Compatibility();
    public Broadcast broadcast = new Broadcast();
    public SecretBook secretbook = new SecretBook();


    public void onEnable() {
        saveDefaultConfig();
        SettingsManager.setup(this);
        registerCore();
        registerNMS();
        Bukkit.getServer().getLogger().info(SettingsManager.lang.getString(
            "Config.onEnable"));
        if (Bukkit.getOnlinePlayers() != null) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                failstack.loadLevels(player);
                secretbook.loadStorage(player);
                Inventory.loadInventory(player);
            }
        }
    }


    public void onDisable() {
        if (Bukkit.getOnlinePlayers() != null) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                this.failstack.saveLevels(this, player, false);
                this.secretbook.saveStorageToDisk(this, player, false);
                Inventory.saveInventoryToDisk(this, player, false);
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

        if (!(sender instanceof Player)) {
            Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
                + SettingsManager.lang.getString("Config.consoleCommand"),
                sender);
            return true;
        }

        Player player = (Player)sender;

        if (cmd.getName().equalsIgnoreCase("enhance")) {
            if (args.length == 0) {
                Util.printHelp(player);
                return true;
            }

            if ((args[0].equalsIgnoreCase("menu")) && Permissions
                .commandEnhance(player)) {
                menu.showEnhancingMenu(this, player, player.getItemInHand());
                return true;
            }
            if ((args[0].equalsIgnoreCase("ver") || args[0].equalsIgnoreCase(
                "version")) && Permissions.commandVersion(player)) {
                Util.sendMessage(SettingsManager.lang.getString(
                    "Config.pluginTag") + SettingsManager.lang.getString(
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
                    "Config.pluginTag") + SettingsManager.lang.getString(
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
                Inventory.printInventory(player);
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
                        Inventory.addLevel(this, p, stoneType, level);
                    }
                    return true;
                }
                else {
                    Util.sendMessage(SettingsManager.lang.getString(
                        "Config.invalidCommand"), player);
                    return true;
                }
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("list")) {
                    secretbook.list(this, player, 0);
                    return true;
                }
                if (args[0].equalsIgnoreCase("select")) {
                    secretbook.select(this, player, 1);
                    return true;
                }
            }
            if (args.length == 2)
                if (args[0].equalsIgnoreCase("list")) {
                    secretbook.list(this, player, Integer.parseInt(args[1]));
                    return true;
                }
            if (args[0].equalsIgnoreCase("select")) {
                secretbook.select(this, player, Integer.parseInt(args[1]));
                return true;
            }
        }
        Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
            + SettingsManager.lang.getString("Config.invalidCommand"), player);
        return true;
    }



    /**
     * This part includes the initialization of the lore.
     */
    private void registerCore() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ItemDropHandler(), this);
        pm.registerEvents(new PlayerDeathHandler(this), this);
        pm.registerEvents(new PlayerStreamHandler(this), this);
        pm.registerEvents(new MenuHandler(this), this);
        pm.registerEvents(new LifeskillingHandler(), this);
    }


    private void registerNMS() {
        if (compatibility.setupGlow()) {
            getLogger().info("Enhancement Glower setup was successful!");
        }
        else {

            getLogger().severe("Failed to setup Enhancement Glower!");
            getLogger().severe(
                "Your server version is not compatible with this plugin!");

            Bukkit.getPluginManager().disablePlugin(this);
        }

        if (compatibility.setupSound()) {
            getLogger().info("Enhancement Sound setup was successful!");
        }
        else {

            getLogger().severe("Failed to setup Enhancement Sound!");
            getLogger().severe(
                "Your server version is not compatible with this plugin!");
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
