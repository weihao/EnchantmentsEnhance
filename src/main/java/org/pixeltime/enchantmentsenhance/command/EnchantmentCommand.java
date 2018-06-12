package org.pixeltime.enchantmentsenhance.command;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.api.API;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

public class EnchantmentCommand extends SubCommand {
    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("add")) {
                ItemStack item = player.getItemInHand();
                API.AddCustomEnchant(item, args[1], Integer.parseInt(args[2]));
            } else {
                Util.sendMessage(SettingsManager.lang.getString("Config.invalidCommand"), player);
            }
        } else {
            Util.sendMessage(SettingsManager.lang.getString("Config.invalidCommand"), player);
        }
    }

    @Override
    public String name() {
        return "enchantment";
    }

    @Override
    public String info() {
        return "&6/enhance enchantment {add} {enchantment} {level} &7- "
                + SettingsManager.lang.getString(
                "Help.ench");
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
