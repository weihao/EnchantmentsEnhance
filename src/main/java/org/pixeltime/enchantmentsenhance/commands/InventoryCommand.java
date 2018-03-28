package org.pixeltime.enchantmentsenhance.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.events.inventory.Backpack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.GUIListener;

public class InventoryCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        Backpack backpack = new Backpack(player);
        Bukkit.getPluginManager().registerEvents(new GUIListener(backpack), Main.getMain());
        backpack.open(player);
    }


    @Override
    public String name() {
        // TODO Auto-generated method stub
        return "inventory";
    }


    @Override
    public String info() {
        // TODO Auto-generated method stub
        return "\n&6/enhance inventory &7- " + SettingsManager.lang
            .getString("Help.inventory");
    }


    @Override
    public String[] aliases() {
        return new String[] { "inv", "inventory" };
    }


    @Override
    public String getPermission() {
        // TODO Auto-generated method stub
        return "Enchantmentsenhance.enhance";
    }

}
