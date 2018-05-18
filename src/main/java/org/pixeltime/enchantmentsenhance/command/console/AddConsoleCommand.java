package org.pixeltime.enchantmentsenhance.command.console;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.event.inventory.Inventory;
import org.pixeltime.enchantmentsenhance.manager.MM;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

public class AddConsoleCommand extends SubConsoleCommand {

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        // /enhance add <player> <stone id> <amounts>
        // /cmd subcommand args[0] args[1] args[2]
        if (args.length == 3) {
            Player p = Bukkit.getServer().getPlayer(args[0]);
            int stoneType;
            int level;

            if (p == null) {
                Util.sendMessage(SettingsManager.lang.getString(
                        "Config.playerNotFound"), sender);
                return;
            }

            try {
                stoneType = Integer.parseInt(args[1]);
                level = Integer.parseInt(args[2]);
            } catch (Exception e) {
                Util.sendMessage(SettingsManager.lang.getString(
                        "Config.invalidNumber"), sender);
                return;
            }

            if (stoneType != -1 && level != -1 && p != null
                    && stoneType <= MM.stoneTypes.size()) {
                Inventory.addLevel(p, stoneType, level);
                Util.sendMessage(SettingsManager.lang.getString(
                        "Add.successful").replace("%player%", p.getName()).replace(
                        "%number%", Integer.toString(level)).replace("%stone%",
                        SettingsManager.lang.getString("Item."
                                + stoneType)), sender);
            } else {
                Util.sendMessage(SettingsManager.lang.getString(
                        "Config.invalidNumber"), sender);
            }
        } else {
            Util.sendMessage(SettingsManager.lang.getString(
                    "Config.invalidCommand"), sender);
        }
    }


    @Override
    public String name() {
        return "add";
    }


    @Override
    public String info() {
        return "\n&6/enhance add { player } { stone } { amount } &7- "
                + SettingsManager.lang.getString("Help.add");
    }


    @Override
    public String[] aliases() {
        return new String[]{"add", "give", "tianjia", "tj"};
    }

}
