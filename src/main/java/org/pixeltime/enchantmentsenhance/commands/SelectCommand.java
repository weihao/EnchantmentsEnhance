package org.pixeltime.enchantmentsenhance.commands;

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.events.blacksmith.SecretBook;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

public class SelectCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        Exception error = null;
        int num = 1;
        try {
            num = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            Util.sendMessage(SettingsManager.lang.getString(
                    "Config.invalidNumber"), player);
        } catch (ArrayIndexOutOfBoundsException e) {
            error = e;
            SecretBook.select(player, num);
        }
        if (error == null) {
            SecretBook.select(player, num);
        }
    }


    @Override
    public String name() {
        return "select";
    }


    @Override
    public String info() {
        return "\n&6/enhance select { n } &7- " + SettingsManager.lang
                .getString("Help.select");
    }


    @Override
    public String[] aliases() {
        return new String[]{"select", "sl", "xuanze", "xz"};
    }


    @Override
    public String getPermission() {
        return "Enchantmentsenhance.enhance";
    }

}
