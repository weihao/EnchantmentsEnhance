package org.pixeltime.enchantmentsenhance.commands;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.EM;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class DebugCommand extends SubCommand {
    public static int animationTask;
    ArmorStand as;

    @Override
    public void onCommand(Player p, String[] args) {
        EM.removeEnchantments(p.getItemInHand());
        //      GraphicsManager gm = new GraphicsManager();
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


    public void log(String s) {
        Main.getMain().getLogger().info("DEBUG >>>" + s);
    }

}
