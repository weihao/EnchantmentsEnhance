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

package org.pixeltime.enchantmentsenhance.manager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.command.SubCommand;
import org.pixeltime.enchantmentsenhance.command.SubConsoleCommand;
import org.pixeltime.enchantmentsenhance.command.console.*;
import org.pixeltime.enchantmentsenhance.command.player.*;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommand> commands = new ArrayList<SubCommand>();
    private ArrayList<SubConsoleCommand> consoleCommands =
            new ArrayList<SubConsoleCommand>();

    /**
     * Register all the command.
     */
    public CommandManager() {
        Main.getMain().getCommand("enhance").setExecutor(this);
        this.commands.add(new AddCommand());
        this.commands.add(new HelpCommand());
        this.commands.add(new InventoryCommand());
        this.commands.add(new MenuCommand());
        this.commands.add(new ReloadCommand());
        this.commands.add(new VersionCommand());
        this.commands.add(new DebugCommand());
        this.commands.add(new ItemCommand());
        this.commands.add(new EnchantmentCommand());
        this.commands.add(new SetCommand());
        this.consoleCommands.add(new AddConsoleCommand());
        this.consoleCommands.add(new DebugConsoleCommand());
        this.consoleCommands.add(new HelpConsoleCommand());
        this.consoleCommands.add(new ReloadConsoleCommand());
        this.consoleCommands.add(new VersionConsoleCommand());
    }

    public ArrayList<SubCommand> getCommands() {
        return commands;
    }

    public ArrayList<SubConsoleCommand> getConsoleCommands() {
        return consoleCommands;
    }

    public boolean onCommand(
            CommandSender sender,
            Command command,
            String s,
            String[] args) {
        if (!command.getName().equalsIgnoreCase("enhance")) {
            return true;
        }
        if (!(sender instanceof Player)) {

            if (args.length == 0) {
                this.getConsoleCommand("help").onCommand(sender, args);
                return true;
            }

            SubConsoleCommand target = this.getConsoleCommand(args[0]);

            if (target == null) {
                Util.sendMessage(SettingsManager.lang.getString(
                        "config.consoleCommand"), sender);
                return true;
            }

            try {
                // This removes the first argument.
                target.onCommand(sender, Arrays.copyOfRange(args, 1,
                        args.length));
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "An error has occurred.");

                e.printStackTrace();

            }

            return true;

        }

        Player player = (Player) sender;

        if (args.length == 0) {
            this.get("help").onCommand(player, args);
            return true;
        }

        SubCommand target = this.get(args[0]);

        if (target == null) {
            Util.sendMessage(SettingsManager.lang.getString(
                    "config.invalidCommand"), player);
            return true;
        }

        if (!player.hasPermission(target.getPermission())) {
            Util.sendMessage(SettingsManager.lang.getString("config.noPerm"),
                    player);
            return true;
        }

        try {
            // This removes the first argument.
            target.onCommand(player, Arrays.copyOfRange(args, 1, args.length));
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "An error has occurred.");

            e.printStackTrace();

        }

        return true;

    }


    /**
     * Get a command by a string.
     *
     * @param name
     * @return
     */
    private SubCommand get(String name) {
        Iterator<SubCommand> subcommands = this.commands.iterator();

        while (subcommands.hasNext()) {
            SubCommand sc = subcommands.next();
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


    private SubConsoleCommand getConsoleCommand(String name) {
        Iterator<SubConsoleCommand> subcommands = this.consoleCommands
                .iterator();

        while (subcommands.hasNext()) {
            SubConsoleCommand sc = subcommands.next();

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


    /**
     * Print help for a player.
     *
     * @param player
     */
    public void printHelp(Player player) {
        String tag = "&b&l&m          &d EnchantmentsEnhance&b&l&m          ";
        Util.sendMessage(tag, player, false);
        Iterator<SubCommand> subcommands = this.commands.iterator();
        while (subcommands.hasNext()) {
            SubCommand sc = subcommands.next();
            if (player.hasPermission(sc.getPermission())) {
                Util.sendMessage(sc.info(), player, false);
            }
        }
    }


    /**
     * Print help for console.
     *
     * @param sender
     */
    public void printHelp(CommandSender sender) {
        StringBuilder help = new StringBuilder("&b&l&m          &d EnchantmentsEnhance&b&l&m          ");
        Iterator<SubConsoleCommand> subcommands = this.consoleCommands.iterator();
        while (subcommands.hasNext()) {
            SubConsoleCommand sc = subcommands.next();
            help.append(sc.info());
        }
        Util.sendMessage(help.toString(), sender);
    }
}
