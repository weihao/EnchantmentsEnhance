package org.pixeltime.enchantmentsenhance.commands;

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.events.blacksmith.SecretBook;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

public class ListCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        Exception error = null;
        int num = 0;
        try {
            num = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e) {
            Util.sendMessage(SettingsManager.lang.getString(
                "Config.invalidNumber"), player);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            error = e;
            SecretBook.list(player, 0);
        }
        if (error == null) {
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
        return new String[] { "list", "ls"};
    }


    @Override
    public String getPermission() {
        // TODO Auto-generated method stub
        return "Enchantmentsenhance.enhance";
    }

}
