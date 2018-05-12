package org.pixeltime.enchantmentsenhance.commands.console;

import org.bukkit.command.CommandSender;
import org.pixeltime.enchantmentsenhance.manager.DataManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

public class ReloadConsoleCommand extends SubConsoleCommand {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        SettingsManager.reloadConfig();
        SettingsManager.reloadData();
        SettingsManager.reloadLang();
        new DataManager();
        SettingsManager.setup();
        Util.sendMessage(SettingsManager.lang.getString("Config.reload"),
                sender);
    }


    @Override
    public String name() {
        return "reload";
    }


    @Override
    public String info() {
        return "\n&6/enhance reload &7- " + SettingsManager.lang.getString(
                "Help.reload");
    }


    @Override
    public String[] aliases() {
        return new String[]{"reload", "rel", "chongzai", "cz"};
    }

}
