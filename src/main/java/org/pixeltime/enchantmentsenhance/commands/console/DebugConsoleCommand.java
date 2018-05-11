package org.pixeltime.enchantmentsenhance.commands.console;

import org.bukkit.command.CommandSender;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class DebugConsoleCommand extends SubConsoleCommand {

    @Override
    public void onCommand(CommandSender sender, String[] args) {
// log("player custom name? :" + player.getCustomName());
// log("player display name? :" + player.getName());
// log("player entity name? :" + player.getName());
// log("player list name? :" + player.getPlayerListName());
// log("Secret Book null? :" + (SecretBook.storage == null));
// log("Inventory null? :" + (Inventory.backpack == null));
// Exception e1 = null;
// try {
// Inventory.setLevel(player, 0, 233);
// log(Inventory.getLevel(0, player) + "<>");
// }
// catch (NullPointerException e) {
// e1 = e;
// }
// log("Exception null? : " + (e1 == null));

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


    public void log(String s) {
        Main.getMain().getLogger().info("DEBUG >>>" + s);
    }

}
