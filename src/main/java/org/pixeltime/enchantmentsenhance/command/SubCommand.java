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

public abstract class SubCommand {

    /**
     * /<command> <subcommand> args[0] args[1]
     */
    public SubCommand() {
    }

    /**
     * Player oncommand.
     *
     * @param player
     * @param args
     */
    public abstract void onCommand(Player player, String[] args);

    /**
     * @return Command name.
     */
    public abstract String name();

    /**
     * @return Command information.
     */
    public abstract String info();

    /**
     * @return Command aliases.
     */
    public abstract String[] aliases();

    /**
     * @return Command permission.
     */
    public abstract String getPermission();
}
