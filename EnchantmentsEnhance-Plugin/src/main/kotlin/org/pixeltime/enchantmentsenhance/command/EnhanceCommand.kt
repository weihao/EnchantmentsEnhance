/*
 *     Copyright (C) 2017-Present 25 (https://github.com/25)
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

package org.pixeltime.enchantmentsenhance.command

import com.lgou2w.ldk.bukkit.cmd.*
import com.lgou2w.ldk.chat.toColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.permissions.PermissionDefault
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util

@CommandRoot("enhance", aliases = ["ee", "eenhance"])
class EnhanceCommand(val main: Main) : StandardCommand() {

    override fun initialize() {
        command.mappingExecutorDescriptions(mapOf(
                "enhance" to SettingsManager.lang.getString("help.help"),
                "help" to SettingsManager.lang.getString("help.help"),
                "inventory" to SettingsManager.lang.getString("help.inventory"),
                "list" to SettingsManager.lang.getString("help.list"),
                "menu" to SettingsManager.lang.getString("help.menu"),
                "reload" to SettingsManager.lang.getString("help.reload"),
                "debug" to SettingsManager.lang.getString("help.debug")
        ))
        command.registerChild(EnhanceItemCommand(main))
    }

    @Command("help")
    @Permission("Enchantmentsenhance.help")
    @PermissionDefaultValue(PermissionDefault.TRUE)
    fun help(sender: CommandSender) {
        sender.sendMessage("&b&l&m          &d EnchantmentsEnhance &b&l&m          ".toColor())
        CommandHelper
                .sendSimpleCommandTooltips(sender, command, newLineDesc = true, named = true, permission = true,
                        sorted = CommandHelper.Sorted.DEFAULT)
    }

    @Command("add")
    @Permission("Enchantmentsenhance.add")
    @PlayerOnly
    fun add(player: Player, stoneId: Int, amount: Int) {
        Main.getApi().addItem(player.name, stoneId, amount)
        Util.sendMessage(SettingsManager.lang.getString(
                "add.successful")!!
                .replace("%player%", player.name)
                .replace("%number%", amount.toString())
                .replace("%stone%", SettingsManager.lang.getString("item.$stoneId")!!), player)

    }

    @Command("inventory")
    @Permission("Enchantmentsenhance.inventory")
    @PlayerOnly
    fun inventory(player: Player) {
    }

    @Command("list")
    @Permission("Enchantmentsenhance.list")
    @PlayerOnly
    fun list(player: Player) {
    }

    @Command("menu")
    @Permission("Enchantmentsenhance.menu")
    @PlayerOnly
    fun menu(player: Player) {
    }

    @Command("reload")
    @Permission("Enchantmentsenhance.reload")
    fun reload(sender: CommandSender) {
    }

    @Command("version")
    @Permission("Enchantmentsenhance.version")
    fun version(sender: CommandSender) {
    }

    @Command("debug")
    @Permission("Enchantmentsenhance.debug")
    fun debug(sender: CommandSender) {
    }
}
