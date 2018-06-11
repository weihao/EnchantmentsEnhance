package org.pixeltime.enchantmentsenhance.command;

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.api.API;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class EnchantmentCommand extends SubCommand {
    @Override
    public void onCommand(Player player, String[] args) {
        if (args[0].equalsIgnoreCase("add")) {
            if (args.length == 3) {
                try {
                    API.AddCustomEnchant(player.getItemInHand(), args[1], Integer.parseInt(args[2]));
                } catch (Exception ex) {
                    player.sendMessage(SettingsManager.config.getString("Config.invalidCommand"));
                }
            }
        }
    }

    @Override
    public String name() {
        return "enchantment";
    }

    @Override
    public String info() {
        return "&6/enhance item { {upgrade} {level} } | {setname} {name}} &7- "
                + SettingsManager.lang.getString(
                "Help.item");
    }

    @Override
    public String[] aliases() {
        return new String[]{"ench"};
    }

    @Override
    public String getPermission() {
        return "Enchantmentsenhance.ench";
    }
}
