package org.pixeltime.enchantmentsenhance.commands;

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

public class VersionCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        Util.sendMessage(SettingsManager.lang.getString(
            "Config.checkingVersion").replaceAll("%version%", Main.getMain()
                .getDescription().getVersion()), player);
    }


    @Override
    public String name() {
        return "version";
    }


    @Override
    public String info() {
        return "\n&6/enhance version &7- " + SettingsManager.lang
            .getString("Help.version");
    }


    @Override
    public String[] aliases() {
        return new String[] { "version", "ver" };
    }


    @Override
    public String getPermission() {
        // TODO Auto-generated method stub
        return "Enchantmentsenhance.version";
    }

}
