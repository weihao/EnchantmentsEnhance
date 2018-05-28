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

package org.pixeltime.enchantmentsenhance.command.console;

import org.bukkit.command.CommandSender;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

public class VersionConsoleCommand extends SubConsoleCommand {

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Util.sendMessage(SettingsManager.lang.getString(
                "Config.checkingVersion").replaceAll("%version%", Main.getMain()
                .getDescription().getVersion()), sender);
    }


    @Override
    public String name() {
        return "version";
    }


    @Override
    public String info() {
        return "&6/enhance version &7- " + SettingsManager.lang.getString(
                "Help.version");
    }


    @Override
    public String[] aliases() {
        return new String[]{"version", "ver", "banben", "bb"};
    }

}
