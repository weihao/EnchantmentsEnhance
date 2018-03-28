package org.pixeltime.enchantmentsenhance.commands;

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.events.menu.Menu;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class MenuCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        Menu.showEnhancingMenu(player);
    }


    @Override
    public String name() {
        return "menu";
    }


    @Override
    public String info() {
        return "\n&6/enhance menu &7- " + SettingsManager.lang.getString(
            "Help.menu");
    }


    @Override
    public String[] aliases() {
        return new String[] { "menu" };
    }


    @Override
    public String getPermission() {
        // TODO Auto-generated method stub
        return "Enchantmentsenhance.enhance";
    }

}
