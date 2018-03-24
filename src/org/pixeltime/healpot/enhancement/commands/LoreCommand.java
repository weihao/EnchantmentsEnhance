package org.pixeltime.healpot.enhancement.commands;

import org.bukkit.entity.Player;
import org.pixeltime.healpot.enhancement.events.blackspirit.Lore;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;

public class LoreCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("addhand")) {
                Lore.addLore(player.getItemInHand(), player,
                    SettingsManager.lang.getString("Lore."
                        + SettingsManager.config.getString("lore.bound")
                        + "Lore"), SettingsManager.config.getBoolean(
                            "lore.bound"));
            }
            if (args[1].equalsIgnoreCase("removehand")) {
                Lore.removeLore(player.getItemInHand(), player);
            }
        }
    }


    @Override
    public String name() {
        // TODO Auto-generated method stub
        return "Lore";
    }


    @Override
    public String info() {
        // TODO Auto-generated method stub
        return "\n&6/enhance lore &7- " + SettingsManager.lang.getString(
            "Help.lore");
    }


    @Override
    public String[] aliases() {
        return new String[] { "Lore" };
    }


    @Override
    public String getPermission() {
        // TODO Auto-generated method stub
        return "Enchantmentsenhance.lore";
    }

}
