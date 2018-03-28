package org.pixeltime.enchantmentsenhance.commands;

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.events.blacksmith.SecretBook;
import org.pixeltime.enchantmentsenhance.events.inventory.Backpack;
import org.pixeltime.enchantmentsenhance.events.inventory.Inventory;

public class DebugCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
//        log("player custom name? :" + player.getCustomName());
//        log("player display name? :" + player.getName());
//        log("player entity name? :" + player.getName());
//        log("player list name? :" + player.getPlayerListName());
//        log("Secret Book null? :" + (SecretBook.storage == null));
//        log("Inventory null? :" + (Inventory.backpack == null));
//        Exception e1 = null;
//        try {
//            Inventory.setLevel(player, 0, 233);
//            log(Inventory.getLevel(0, player) + "<>");
//        }
//        catch (NullPointerException e) {
//            e1 = e;
//        }
//        log("Exception null? : " + (e1 == null));

    }


    @Override
    public String name() {
        return "debug";
    }


    @Override
    public String info() {
        return "\n&6/enhance debug &7- collects debugging information to developer to fix issues.";
    }


    @Override
    public String[] aliases() {
        return new String[] { "debug" };
    }


    @Override
    public String getPermission() {
        return "Enchantmentsenhance.debug";
    }


    public void log(String s) {
        Main.getMain().getLogger().info("DEBUG >>>" + s);
    }

}
