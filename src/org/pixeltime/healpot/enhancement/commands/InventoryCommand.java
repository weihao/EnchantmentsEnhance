package org.pixeltime.healpot.enhancement.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.pixeltime.healpot.enhancement.Main;
import org.pixeltime.healpot.enhancement.events.inventory.Backpack;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.GUIListener;

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
