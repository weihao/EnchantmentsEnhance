package org.pixeltime.enchantmentsenhance.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.commands.AddCommand;
import org.pixeltime.enchantmentsenhance.commands.DebugCommand;
import org.pixeltime.enchantmentsenhance.commands.HelpCommand;
import org.pixeltime.enchantmentsenhance.commands.InventoryCommand;
import org.pixeltime.enchantmentsenhance.commands.ListCommand;
import org.pixeltime.enchantmentsenhance.commands.LoreCommand;
import org.pixeltime.enchantmentsenhance.commands.MenuCommand;
import org.pixeltime.enchantmentsenhance.commands.ReloadCommand;
import org.pixeltime.enchantmentsenhance.commands.SelectCommand;
import org.pixeltime.enchantmentsenhance.commands.SubCommand;
import org.pixeltime.enchantmentsenhance.commands.VersionCommand;
import org.pixeltime.enchantmentsenhance.util.Util;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommand> commands = new ArrayList<SubCommand>();
    private Main plugin = Main.getMain();


    public CommandManager() {
    }

    // Sub Commands
    public String main = "enhance";
    public String add = "add";
    public String help = "help";
    public String inventory = "inventory";
    public String list = "list";
    public String lore = "lore";
    public String menu = "menu";
    public String reload = "reload";
    public String select = "select";
    public String version = "version";
    public String debug = "debug";


    public void setup() {
        plugin.getCommand(main).setExecutor(this);
        this.commands.add(new AddCommand());
        this.commands.add(new HelpCommand());
        this.commands.add(new InventoryCommand());
        this.commands.add(new ListCommand());
        this.commands.add(new LoreCommand());
        this.commands.add(new MenuCommand());
        this.commands.add(new ReloadCommand());
        this.commands.add(new SelectCommand());
        this.commands.add(new VersionCommand());
        this.commands.add(new DebugCommand());
    }


    public boolean onCommand(
        CommandSender sender,
        Command command,
        String s,
        String[] args) {

        if (!(sender instanceof Player)) {

            Util.sendMessage(SettingsManager.lang.getString(
                "Config.consoleCommand"), sender);
            return true;

        }

        Player player = (Player)sender;

        if (command.getName().equalsIgnoreCase(main)) {

            if (args.length == 0) {
                this.get(help).onCommand(player, args);
                return true;
            }

            SubCommand target = this.get(args[0]);

            if (target == null) {
                Util.sendMessage(SettingsManager.lang.getString(
                    "Config.invalidCommand"), player);
                return true;
            }

            if (!player.hasPermission(target.getPermission())) {
                Util.sendMessage(SettingsManager.lang.getString(
                    "Config.noPerm"), player);
                return true;
            }

            try {
                // This removes the first argument.
                target.onCommand(player, Arrays.copyOfRange(args, 1,
                    args.length));
            }
            catch (Exception e) {
                player.sendMessage(ChatColor.RED + "An error has occurred.");

                e.printStackTrace();
            }
        }

        return true;
    }


    private SubCommand get(String name) {
        Iterator<SubCommand> subcommands = this.commands.iterator();

        while (subcommands.hasNext()) {
            SubCommand sc = (SubCommand)subcommands.next();

            if (sc.name().equalsIgnoreCase(name)) {
                return sc;
            }

            String[] aliases;
            int length = (aliases = sc.aliases()).length;

            for (int var5 = 0; var5 < length; ++var5) {
                String alias = aliases[var5];
                if (name.equalsIgnoreCase(alias)) {
                    return sc;
                }

            }
        }
        return null;
    }


    public void printHelp(Player player) {
        String help = "&b&l&m          &d EnchantmentsEnhance&b&l&m          ";
        Iterator<SubCommand> subcommands = this.commands.iterator();
        while (subcommands.hasNext()) {
            SubCommand sc = subcommands.next();
            if (player.hasPermission(sc.getPermission())) {
                help += sc.info();
            }
        }
        Util.sendMessage(help, player);
    }


    /**
     * Print help for console.
     * 
     * @param sender
     */
    public void printHelp(CommandSender sender) {
        String help = "&b&l&m          &d EnchantmentsEnhance&b&l&m          ";
        List<String> consoleCommands = Arrays.asList(new String[] { "help",
            "reload", "version", "add" });

        Iterator<SubCommand> subcommands = this.commands.iterator();
        while (subcommands.hasNext()) {
            SubCommand sc = subcommands.next();
            if (consoleCommands.contains(sc.name())) {
                help += sc.info();
            }
        }
        Util.sendMessage(help, sender, false);
    }
}
