/*
 *     Copyright (C) 2017-Present HealPotion
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.pixeltime.enchantmentsenhance.command.player;

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.command.SubCommand;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Lore;
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
        } else {
            Util.sendMessage(SettingsManager.lang.getString(
                    "Config.invalidCommand"), player);
        }
    }


    @Override
    public String name() {
        return "lore";
    }


    @Override
    public String info() {
        return "&6/enhance lore { addhand | removehand }&7- "
                + SettingsManager.lang.getString("Help.lore");
    }


    @Override
    public String[] aliases() {
        return new String[]{"lore", "shuxing", "sx"};
    }


    @Override
    public String getPermission() {
        return "Enchantmentsenhance.lore";
    }

}
