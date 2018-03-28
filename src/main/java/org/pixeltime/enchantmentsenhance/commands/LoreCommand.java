package org.pixeltime.enchantmentsenhance.commands;

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.events.blackspirit.Lore;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

public class LoreCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("addhand")) {
                Lore.addLore(player.getItemInHand(), player,
                    SettingsManager.lang.getString("Lore."
                        + SettingsManager.config.getString("lore.bound")
                        + "Lore"), SettingsManager.config.getBoolean(
                            "lore.bound"));
            }
            if (args[0].equalsIgnoreCase("removehand")) {
                Lore.removeLore(player.getItemInHand(), player);
            }
        }
        else {
            Util.sendMessage(SettingsManager.lang.getString(
                "Config.invalidCommand"), player);
        }
    }


    @Override
    public String name() {
        // TODO Auto-generated method stub
        return "lore";
    }


    @Override
    public String info() {
        // TODO Auto-generated method stub
        return "\n&6/enhance lore { addhand | removehand }&7- " + SettingsManager.lang.getString(
            "Help.lore");
    }


    @Override
    public String[] aliases() {
        return new String[] { "lore" };
    }


    @Override
    public String getPermission() {
        // TODO Auto-generated method stub
        return "Enchantmentsenhance.lore";
    }

}
