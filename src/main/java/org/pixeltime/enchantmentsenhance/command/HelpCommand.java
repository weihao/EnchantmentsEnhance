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
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.CommandManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class HelpCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        Main.getMain().commandManager.printHelp(player);
    }


    @Override
    public String name() {
        return "help";
    }


    @Override
    public String info() {
        return "&6/enhance help &7- " + SettingsManager.lang.getString(
                "Help.help");
    }


    @Override
    public String[] aliases() {
        return new String[]{"help", "hl", "bangzhu", "bz"};
    }


    @Override
    public String getPermission() {
        return "Enchantmentsenhance.enhance";
    }

}
