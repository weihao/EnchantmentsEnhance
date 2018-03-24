package org.pixeltime.healpot.enhancement;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.pixeltime.healpot.enhancement.commands.CommandManager;
import org.pixeltime.healpot.enhancement.events.blacksmith.SecretBook;
import org.pixeltime.healpot.enhancement.events.blackspirit.Failstack;
import org.pixeltime.healpot.enhancement.events.inventory.Inventory;
import org.pixeltime.healpot.enhancement.listeners.ItemDropHandler;
import org.pixeltime.healpot.enhancement.listeners.LifeskillingHandler;
import org.pixeltime.healpot.enhancement.listeners.MenuHandler;
import org.pixeltime.healpot.enhancement.listeners.PlayerDeathHandler;
import org.pixeltime.healpot.enhancement.listeners.PlayerStreamHandler;
import org.pixeltime.healpot.enhancement.manager.Compatibility;
import org.pixeltime.healpot.enhancement.manager.DataManager;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.AnimalBreeding;
import org.pixeltime.healpot.enhancement.util.Metrics;

public class Main extends JavaPlugin {
    public static final Compatibility compatibility = new Compatibility();
    private static Main main;
    public CommandManager commandManager;


    public static Main getMain() {
        return main;
    }


    public void onEnable() {
        final long startTime = System.currentTimeMillis();
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
        if (getServer().getName().contains("Cauldron") || getServer().getName()
            .contains("MCPC")) {
            getLogger().info(
                "EnchantmentsEnhance runs fine on Cauldron/KCauldron.");
        }
        new AnimalBreeding();
        new DataManager();
        new Metrics(this);
        this.commandManager = new CommandManager();
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


//    @Override
//    public List<String> onTabComplete(
//        CommandSender sender,
//        Command cmd,
//        String commandLabel,
//        String[] args) {
//        List<String> commands = new ArrayList<String>();
//        if (cmd.getName().equalsIgnoreCase("enhance")) {
//            Player player = (Player)sender;
//            List<String> str = new ArrayList<String>();
//            if (Permissions.commandHelp(player)) {
//                commands.add("help");
//            }
//            if (Permissions.commandEnhance(player)) {
//                commands.add("menu");
//                commands.add("list");
//                commands.add("select");
//                commands.add("inventory");
//            }
//            if (Permissions.commandReload(player)) {
//                commands.add("reload");
//            }
//            if (Permissions.commandVersion(player)) {
//                commands.add("version");
//            }
//            if (Permissions.commandAdd(player)) {
//                commands.add("add");
//            }
//            if (Permissions.commandLore(player)) {
//                commands.add("lore");
//            }
//            if (args.length == 0) {
//                return commands;
//            }
//            if (args.length == 1) {
//                for (int i = 0; i < commands.size(); i++) {
//                    if (commands.get(i).startsWith(args[0])) {
//                        str.add(commands.get(i));
//                    }
//                }
//                return str;
//            }
//            if (args[0].equals("add")) {
//                if (args.length == 2) {
//                    for (Player p : Bukkit.getOnlinePlayers()) {
//                        if (p.getName().startsWith(args[1])) {
//                            str.add(p.getName());
//                        }
//                    }
//                    return str;
//                }
//                if (args.length == 3) {
//                    Util.sendMessage(SettingsManager.lang.getString(
//                        "Example.command.add.stone"), player);
//                    return Arrays.asList("0", "1", "2", "3");
//                }
//                if (args.length == 4) {
//                    Util.sendMessage(SettingsManager.lang.getString(
//                        "Example.command.add.guide"), player);
//                    return null;
//                }
//
//            }
//        }
//        return commands;
//    }

}
