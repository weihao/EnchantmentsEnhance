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
