/*
 *     Copyright (C) 2017-Present HealPot
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

package org.pixeltime.enchantmentsenhance.manager

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.command.SubConsoleCommand
import org.pixeltime.enchantmentsenhance.command.console.AddConsoleCommand
import org.pixeltime.enchantmentsenhance.command.console.DebugConsoleCommand
import org.pixeltime.enchantmentsenhance.command.console.HelpConsoleCommand
import org.pixeltime.enchantmentsenhance.command.console.ReloadConsoleCommand
import org.pixeltime.enchantmentsenhance.command.console.VersionConsoleCommand
import org.pixeltime.enchantmentsenhance.command.player.*
import org.pixeltime.enchantmentsenhance.util.Util
import java.util.*

class CommandManager : CommandExecutor {

    val commands = ArrayList<SubCommand>()
    val consoleCommands = ArrayList<SubConsoleCommand>()

    init {
        Main.getMain().getCommand("enhance").executor = this
        this.commands.add(AddCommand())
        this.commands.add(HelpCommand())
        this.commands.add(InventoryCommand())
        this.commands.add(MenuCommand())
        this.commands.add(ReloadCommand())
        this.commands.add(VersionCommand())
        this.commands.add(DebugCommand())
        this.commands.add(ItemCommand())
        this.commands.add(EnchantmentCommand())
        this.commands.add(SetCommand())
        this.consoleCommands.add(AddConsoleCommand())
        this.consoleCommands.add(DebugConsoleCommand())
        this.consoleCommands.add(HelpConsoleCommand())
        this.consoleCommands.add(ReloadConsoleCommand())
        this.consoleCommands.add(VersionConsoleCommand())
    }

    override fun onCommand(
            sender: CommandSender,
            command: Command,
            label: String,
            args: Array<String>
    ): Boolean {
        if (!command.name.equals("enhance", ignoreCase = true)) {
            return true
        }
        if (sender !is Player) {

            if (args.isEmpty()) {
                this.getConsoleCommand("help")!!.onCommand(sender, args)
                return true
            }

            val target = this.getConsoleCommand(args[0])

            if (target == null) {
                Util.sendMessage(SettingsManager.lang.getString(
                        "config.consoleCommand"), sender)
                return true
            }

            try {
                // This removes the first argument.
                target.onCommand(sender, Arrays.copyOfRange(args, 1,
                        args.size))
            } catch (e: Exception) {
                sender.sendMessage(ChatColor.RED.toString() + "An error has occurred.")

                e.printStackTrace()

            }

            return true

        }

        if (args.isEmpty()) {
            this["help"]!!.onCommand(sender, args)
            return true
        }

        val target = this.get(args[0])

        if (target == null) {
            Util.sendMessage(SettingsManager.lang.getString(
                    "config.invalidCommand"), sender)
            return true
        }

        if (!sender.hasPermission(target!!.permission)) {
            Util.sendMessage(SettingsManager.lang.getString("config.noPerm"),
                    sender)
            return true
        }

        try {
            // This removes the first argument.
            target!!.onCommand(sender, Arrays.copyOfRange(args, 1, args.size))
        } catch (e: Exception) {
            sender.sendMessage(ChatColor.RED.toString() + "An error has occurred.")

            e.printStackTrace()

        }


        return true

    }

    /**
     * Get a command by a string.
     *
     * @param name
     * @return
     */
    private operator fun get(name: String): SubCommand? {
        val subcommands = this.commands.iterator()

        while (subcommands.hasNext()) {
            val sc = subcommands.next()
            if (sc.name().equals(name, ignoreCase = true)) {
                return sc
            }

            val aliases: Array<String> = sc.aliases()
            val length = aliases.size

            for (var5 in 0 until length) {
                val alias = aliases[var5]
                if (name.equals(alias, ignoreCase = true)) {
                    return sc
                }

            }
        }
        return null
    }

    private fun getConsoleCommand(name: String): SubConsoleCommand? {
        val subcommands = this.consoleCommands
            .iterator()

        while (subcommands.hasNext()) {
            val sc = subcommands.next()

            if (sc.name().equals(name, ignoreCase = true)) {
                return sc
            }

            val aliases: Array<String> = sc.aliases()
            val length = aliases.size

            for (var5 in 0 until length) {
                val alias = aliases[var5]
                if (name.equals(alias, ignoreCase = true)) {
                    return sc
                }

            }
        }
        return null
    }

    /**
     * Print help for a player.
     *
     * @param player
     */
    fun printHelp(player: Player) {
        val tag = "&b&l&m          &d EnchantmentsEnhance&b&l&m          "
        Util.sendMessage(tag, player, false)
        val subcommands = this.commands.iterator()
        while (subcommands.hasNext()) {
            val sc = subcommands.next()
            if (player.hasPermission(sc.permission)) {
                Util.sendMessage(sc.info(), player, false)
            }
        }
    }

    /**
     * Print enchantments for a player.
     *
     * @param player
     */
    fun printEnchantments(player: Player) {
        val tag = "&b&l&m          &d EnchantmentsEnhance&b&l&m          "
        Util.sendMessage(tag, player, false)
        for (ench in PackageManager.enabled) {
            Util.sendMessage("&b" + ench.name() + " &a" + SettingsManager.lang.getString("descriptions." + ench.javaClass.simpleName.toLowerCase()), player, false)
        }
    }

    /**
     * Print help for console.
     *
     * @param sender
     */
    fun printHelp(sender: CommandSender) {
        val help = StringBuilder("&b&l&m          &d EnchantmentsEnhance&b&l&m          ")
        val subcommands = this.consoleCommands.iterator()
        while (subcommands.hasNext()) {
            val sc = subcommands.next()
            help.append(sc.info())
        }
        Util.sendMessage(help.toString(), sender)
    }
}
