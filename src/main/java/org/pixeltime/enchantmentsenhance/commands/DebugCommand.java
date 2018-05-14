package org.pixeltime.enchantmentsenhance.commands;

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.events.blackspirit.Enhance;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class DebugCommand extends SubCommand {
    @Override
    public void onCommand(Player p, String[] args) {
        Enhance.enhanceSuccess(p.getItemInHand(), p, false, 19);
    }


    @Override
    public String name() {
        return "debug";
    }


    @Override
    public String info() {
        return "\n&6/enhance debug &7- " + SettingsManager.lang.getString(
                "Help.debug");
    }


    @Override
    public String[] aliases() {
        return new String[]{"debug", "tiaoshi", "ts"};
    }


    @Override
    public String getPermission() {
        return "Enchantmentsenhance.debug";
    }
}
