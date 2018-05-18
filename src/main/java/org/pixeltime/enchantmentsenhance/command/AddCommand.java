package org.pixeltime.enchantmentsenhance.command;

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.command.console.AddConsoleCommand;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class AddCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        new AddConsoleCommand().onCommand(player, args);
    }


    @Override
    public String name() {
        return "add";
    }


    @Override
    public String info() {
        return "\n&6/enhance add { player } { stone } { amount } &7- "
                + SettingsManager.lang.getString("Help.add");
    }


    @Override
    public String[] aliases() {
        return new String[]{"add", "give", "tianjia", "tj"};
    }


    @Override
    public String getPermission() {
        return "Enchantmentsenhance.add";
    }

}
