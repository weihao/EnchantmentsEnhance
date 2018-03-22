package org.pixeltime.healpot.enhancement.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.pixeltime.healpot.enhancement.Main;
import org.pixeltime.healpot.enhancement.events.blacksmith.SecretBook;
import org.pixeltime.healpot.enhancement.events.inventory.Backpack;
import org.pixeltime.healpot.enhancement.events.inventory.Inventory;
import org.pixeltime.healpot.enhancement.events.menu.Menu;
import org.pixeltime.healpot.enhancement.manager.Permissions;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.Util;

public class CommandEnhance implements CommandExecutor {

    @Override
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
            if (args[0].equals("debug")) {
                // TODO
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
                    "Config.checkingVersion").replaceAll("%version%", Main
                        .getMain().getDescription().getVersion()), player);
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
                Backpack.sendInventoryAsText(player);
                return true;
            }
            if (args[0].equalsIgnoreCase("add") && Permissions.commandAdd(
                player)) {
                if (args.length == 4) {
                    boolean success = false;
                    Player p = null;
                    int stoneType = -1;
                    int level = -1;
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
                    if (stoneType != -1 && level != -1 && p != null
                        && stoneType <= Util.stoneTypes.length) {
                        Inventory.addLevel(p, stoneType, level);
                        Util.sendMessage("&2✔", sender);
                    }
                    else {
                        Util.sendMessage(SettingsManager.lang.getString(
                            "Config.invalidNumber"), player);
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
                int num;
                try {
                    num = Integer.parseInt(args[1]);
                }
                catch (NumberFormatException e) {
                    Util.sendMessage(SettingsManager.lang.getString(
                        "Config.invalidNumber"), sender);
                    return true;
                }

                if (args[0].equalsIgnoreCase("list")) {
                    SecretBook.list(player, num);
                    return true;
                }
                if (args[0].equalsIgnoreCase("select")) {
                    SecretBook.select(player, num);
                    return true;
                }
            }
        }
        // No Commands Found
        Util.sendMessage(SettingsManager.lang.getString(
            "Config.invalidCommand"), player);
        return true;
    }

}
