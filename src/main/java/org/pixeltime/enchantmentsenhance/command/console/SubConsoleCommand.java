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

public abstract class SubConsoleCommand {

    /**
     * /<command> <subcommand> args[0] args[1]
     */
    public SubConsoleCommand() {
    }


    public abstract void onCommand(CommandSender sender, String[] args);


    public abstract String name();


    public abstract String info();


    public abstract String[] aliases();
}
