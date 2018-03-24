package org.pixeltime.healpot.enhancement.commands;

import org.bukkit.entity.Player;
import org.pixeltime.healpot.enhancement.events.blacksmith.SecretBook;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.Util;

public class SelectCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        Exception error = null;
        int num = 1;
        try {
            num = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e) {
            Util.sendMessage(SettingsManager.lang.getString(
                "Config.invalidNumber"), player);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            error = e;
            SecretBook.select(player, num);
        }
        if (error == null) {
            SecretBook.select(player, num);
        }
    }


    @Override
    public String name() {
        // TODO Auto-generated method stub
        return "select";
    }


    @Override
    public String info() {
        return "\n&6/enhance select { n } &7- " + SettingsManager.lang.getString(
            "Help.select");
    }


    @Override
    public String[] aliases() {
        return new String[] { "select", "sl" };
    }


    @Override
    public String getPermission() {
        // TODO Auto-generated method stub
        return "Enchantmentsenhance.enhance";
    }

}
