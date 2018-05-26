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
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class VersionCommand extends SubCommand {

    @Override
    public void onCommand(Player player, String[] args) {
        new VersionCommand().onCommand(player, args);
    }


    @Override
    public String name() {
        return "version";
    }


    @Override
    public String info() {
        return "\n&6/enhance version &7- " + SettingsManager.lang.getString(
                "Help.version");
    }


    @Override
    public String[] aliases() {
        return new String[]{"version", "ver", "banben", "bb"};
    }


    @Override
    public String getPermission() {
        return "Enchantmentsenhance.version";
    }

}
