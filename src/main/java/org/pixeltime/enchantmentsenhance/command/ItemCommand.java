package org.pixeltime.enchantmentsenhance.command;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Enhance;
import org.pixeltime.enchantmentsenhance.listener.MenuHandler;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class ItemCommand extends SubCommand {
    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("upgrade")) {
                try {
                    Enhance.enhanceSuccess(player.getItemInHand(), player, true, Integer.parseInt(args[1]) - 1);
                } catch (Exception ex) {
                    player.sendMessage(SettingsManager.config.getString("Config.invalidCommand"));
                }
            } else if (args[0].equalsIgnoreCase("setname")) {
                ItemStack item = player.getItemInHand();
                int level = ItemManager.getItemEnchantLevel(item);
                ItemStack curr = ItemManager.setName(item, ChatColor.translateAlternateColorCodes('&', args[1]));
                player.getInventory().removeItem(item);
                ItemManager.forgeItem(player, curr, level);
            }
        }
    }

    @Override
    public String name() {
        return "item";
    }

    @Override
    public String info() {
        return "&6/enhance item { {upgrade} {level} } | {setname} {name}} &7- "
                + SettingsManager.lang.getString(
                "Help.item");
    }

    @Override
    public String[] aliases() {
        return new String[]{"item", "wupin", "wp"};
    }

    @Override
    public String getPermission() {
        return "Enchantmentsenhance.item";
    }
}
