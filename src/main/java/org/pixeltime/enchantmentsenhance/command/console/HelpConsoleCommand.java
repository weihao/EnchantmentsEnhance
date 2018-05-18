package org.pixeltime.enchantmentsenhance.command.console;

import org.bukkit.command.CommandSender;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class HelpConsoleCommand extends SubConsoleCommand {

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Main.getMain().commandManager.printHelp(sender);
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

}
