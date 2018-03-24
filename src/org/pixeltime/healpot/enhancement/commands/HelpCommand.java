package org.pixeltime.healpot.enhancement.commands;

import org.bukkit.entity.Player;
import org.pixeltime.healpot.enhancement.Main;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;

public class HelpCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        Main.getMain().commandManager.printHelp(player);
    }


    @Override
    public String name() {
        return "\n&6/enhance help &7- " + SettingsManager.lang.getString(
            "Help.help");
    }


    @Override
    public String info() {
        return null;
    }


    @Override
    public String[] aliases() {
        return new String[] { "help", "hl" };
    }


    @Override
    public String getPermission() {
        return "Enchantmentsenhance.enhance";
    }

}
