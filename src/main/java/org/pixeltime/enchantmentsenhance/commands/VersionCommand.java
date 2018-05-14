package org.pixeltime.enchantmentsenhance.commands;

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class VersionCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        new VersionCommand().onCommand(player, args);
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


    @Override
    public String getPermission() {
        return "Enchantmentsenhance.version";
    }

}
