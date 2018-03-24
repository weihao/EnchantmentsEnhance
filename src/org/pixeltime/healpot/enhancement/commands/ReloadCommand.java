package org.pixeltime.healpot.enhancement.commands;

import org.bukkit.entity.Player;
import org.pixeltime.healpot.enhancement.manager.DataManager;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.Util;

public class ReloadCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        SettingsManager.reloadConfig();
        SettingsManager.reloadData();
        SettingsManager.reloadLang();
        new DataManager();
        Util.sendMessage(SettingsManager.lang.getString("Config.reload"),
            player);
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
        return new String[] { "reload", "rel" };
    }


    @Override
    public String getPermission() {
        // TODO Auto-generated method stub
        return "Enchantmentsenhance.reload";
    }

}
