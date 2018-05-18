package org.pixeltime.enchantmentsenhance.command;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.event.inventory.Backpack;
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
        return "inventory";
    }


    @Override
    public String info() {
        return "\n&6/enhance inventory &7- " + SettingsManager.lang
                .getString("Help.inventory");
    }


    @Override
    public String[] aliases() {
        return new String[]{"inv", "inventory", "beibao", "b"};
    }


    @Override
    public String getPermission() {
        return "Enchantmentsenhance.enhance";
    }

}
