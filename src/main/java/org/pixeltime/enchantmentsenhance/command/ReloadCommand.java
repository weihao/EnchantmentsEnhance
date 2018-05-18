package org.pixeltime.enchantmentsenhance.command;

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class ReloadCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        new ReloadCommand().onCommand(player, args);
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


    @Override
    public String getPermission() {
        return "Enchantmentsenhance.reload";
    }

}
