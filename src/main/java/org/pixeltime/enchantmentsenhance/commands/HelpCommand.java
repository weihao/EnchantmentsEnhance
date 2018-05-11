package org.pixeltime.enchantmentsenhance.commands;

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class HelpCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        Main.getMain().commandManager.printHelp(player);
    }


    @Override
    public String name() {
        return "help";
    }


    @Override
    public String info() {
        return "\n&6/enhance help &7- " + SettingsManager.lang.getString(
                "Help.help");
    }


    @Override
    public String[] aliases() {
        return new String[]{"help", "hl", "bangzhu", "bz"};
    }


    @Override
    public String getPermission() {
        return "Enchantmentsenhance.enhance";
    }

}
