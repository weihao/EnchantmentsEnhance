package org.pixeltime.enchantmentsenhance.command;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Enhance;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

public class DebugCommand extends SubCommand {
    private int x = 1;
    private int y = 1;

    @Override
    public void onCommand(Player p, String[] args) {
        if (args[0].equals("upgrade")) {
            Enhance.enhanceSuccess(p.getItemInHand(), p, false, 19);
        } else if (args[0].equals("slots")) {
            x = Integer.parseInt(args[1]);
            y = Integer.parseInt(args[2]);
            ItemStack item = p.getInventory().getItem(Util.getSlot(x, y));
            System.out.println(item.getItemMeta().getDisplayName());
        }
    }


    @Override
    public String name() {
        return "debug";
    }


    @Override
    public String info() {
        return "\n&6/enhance debug &7- " + SettingsManager.lang.getString(
                "Help.debug");
    }


    @Override
    public String[] aliases() {
        return new String[]{"debug", "tiaoshi", "ts"};
    }


    @Override
    public String getPermission() {
        return "Enchantmentsenhance.debug";
    }
}
