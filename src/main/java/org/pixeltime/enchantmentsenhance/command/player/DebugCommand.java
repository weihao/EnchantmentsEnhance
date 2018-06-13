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

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.command.SubCommand;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Enhance;
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu;
import org.pixeltime.enchantmentsenhance.manager.IM;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.Iterator;

public class DebugCommand extends SubCommand {
    @Override
    public void onCommand(Player p, String[] args) {
        if (args[0].equals("upgrade")) {
            Enhance.enhanceSuccess(p.getItemInHand(), p, false, 19);
        } else if (args[0].equals("slots")) {
            IM.getArmorSlots(p);
        } else if (args[0].equals("mysql")) {
        } else if (args[0].equals("format")) {
            Iterator<SubCommand> subcommands = Main.getMain().commandManager.getCommands().iterator();
            while (subcommands.hasNext()) {
                SubCommand sc = subcommands.next();
                String curr = "Command: " + sc.info() + " Permission: " + sc.getPermission();
                Util.sendMessage(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', curr)), p, false);
            }
        } else if (args[0].equalsIgnoreCase("newmenu")) {
            new MainMenu(p).open();
        }
    }


    @Override
    public String name() {
        return "debug";
    }


    @Override
    public String info() {
        return "&6/enhance debug &7- " + SettingsManager.lang.getString(
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
