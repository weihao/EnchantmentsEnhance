package org.pixeltime.healpot.enhancement.commands;

import org.bukkit.entity.Player;
import org.pixeltime.healpot.enhancement.events.blacksmith.SecretBook;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.Util;

public class ListCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length == 1) {
            SecretBook.list(player, 0);
        }
        if (args.length == 2) {
            int num = 0;
            try {
                num = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException e) {
                Util.sendMessage(SettingsManager.lang.getString(
                    "Config.invalidNumber"), player);
            }
            SecretBook.list(player, num);
        }
    }


    @Override
    public String name() {
        // TODO Auto-generated method stub
        return "list";
    }


    @Override
    public String info() {
        // TODO Auto-generated method stub
        return "\n&6/enhance list &7- " + SettingsManager.lang.getString(
            "Help.list");
    }


    @Override
    public String[] aliases() {
        // TODO Auto-generated method stub
        return new String[] { "list" };
    }


    @Override
    public String getPermission() {
        // TODO Auto-generated method stub
        return "Enchantmentsenhance.enhance";
    }

}
