package org.pixeltime.enchantmentsenhance.commands.console;

import org.bukkit.command.CommandSender;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

public class VersionConsoleCommand extends SubConsoleCommand {

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Util.sendMessage(SettingsManager.lang.getString(
                "Config.checkingVersion").replaceAll("%version%", Main.getMain()
                .getDescription().getVersion()), sender);
    }


    @Override
    public String name() {
        return "version";
    }


    @Override
    public String info() {
        return "\n&6/enhance version &7- " + SettingsManager.lang.getString(
                "Help.version");
    }


    @Override
    public String[] aliases() {
        return new String[]{"version", "ver", "banben", "bb"};
    }

}
