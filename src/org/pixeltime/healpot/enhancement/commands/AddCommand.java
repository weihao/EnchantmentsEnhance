package org.pixeltime.healpot.enhancement.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.pixeltime.healpot.enhancement.events.inventory.Inventory;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.Util;

public class AddCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length == 3) {
            boolean success = false;
            Player p = null;
            int stoneType = -1;
            int level = -1;
            try {
                p = Bukkit.getServer().getPlayer(args[1]);
                success = true;
            }
            catch (Exception e) {
                Util.sendMessage(SettingsManager.lang.getString(
                    "Config.playerNotFound"), player);
            }
            if (success) {
                try {
                    stoneType = Integer.parseInt(args[2]);
                    level = Integer.parseInt(args[3]);
                }
                catch (Exception e) {
                    Util.sendMessage(SettingsManager.lang.getString(
                        "Config.invalidNumber"), player);
                }
            }
            if (stoneType != -1 && level != -1 && p != null
                && stoneType <= Util.stoneTypes.length) {
                Inventory.addLevel(p, stoneType, level);
                Util.sendMessage(SettingsManager.lang.getString(
                    "Add.successful").replace("%player%", p.getDisplayName())
                    .replace("%number%", Integer.toString(level)).replace(
                        "%stone%", SettingsManager.lang.getString("Item."
                            + stoneType)), player);
            }
            else {
                Util.sendMessage(SettingsManager.lang.getString(
                    "Config.invalidNumber"), player);
            }
        }
    }


    @Override
    public String name() {
        return "add";
    }


    @Override
    public String info() {
        return "\n&6/enhance add &7- " + SettingsManager.lang.getString(
            "Help.add");
    }


    @Override
    public String[] aliases() {
        return new String[] { "add" };
    }


    @Override
    public String getPermission() {
        return "Enchantmentsenhance.add";
    }

}
