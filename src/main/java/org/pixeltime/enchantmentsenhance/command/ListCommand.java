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

package org.pixeltime.enchantmentsenhance.command;

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.event.blacksmith.SecretBook;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

public class ListCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        Exception error = null;
        int num = 0;
        try {
            num = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            Util.sendMessage(SettingsManager.lang.getString(
                    "Config.invalidNumber"), player);
        } catch (ArrayIndexOutOfBoundsException e) {
            error = e;
            SecretBook.list(player, 0);
        }
        if (error == null) {
            SecretBook.list(player, num);
        }
    }


    @Override
    public String name() {
        return "list";
    }


    @Override
    public String info() {
        return "&6/enhance list &7- " + SettingsManager.lang.getString(
                "Help.list");
    }


    @Override
    public String[] aliases() {
        return new String[]{"list", "ls", "chakan", "ck"};
    }


    @Override
    public String getPermission() {
        return "Enchantmentsenhance.enhance";
    }

}
