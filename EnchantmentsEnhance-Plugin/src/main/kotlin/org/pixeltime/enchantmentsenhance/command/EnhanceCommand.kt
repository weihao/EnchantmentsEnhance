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

import com.lgou2w.ldk.bukkit.cmd.Command
import com.lgou2w.ldk.bukkit.cmd.CommandHelper
import com.lgou2w.ldk.bukkit.cmd.CommandRoot
import com.lgou2w.ldk.bukkit.cmd.Permission
import com.lgou2w.ldk.bukkit.cmd.PermissionDefaultValue
import com.lgou2w.ldk.bukkit.cmd.PlayerOnly
import com.lgou2w.ldk.bukkit.cmd.StandardCommand
import com.lgou2w.ldk.chat.toColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.permissions.PermissionDefault
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.gui.MainGui
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util

@CommandRoot("enhance", aliases = ["ee", "eenhance"])
class EnhanceCommand(val main: Main) : StandardCommand() {

    override fun initialize() {
        command.mappingExecutorDescriptions(mapOf(
                "enhance" to SettingsManager.lang.getString("help.help"),
                "add" to SettingsManager.lang.getString("help.add"),
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
    fun add(sender: CommandSender, player: Player, stoneId: Int, amount: Int) {
        Main.getApi().addItem(player.name, stoneId, amount)
        Util.sendMessage(SettingsManager.lang.getString(
                "add.successful")!!
                .replace("%player%", player.name)
                .replace("%number%", amount.toString())
                .replace("%number%", amount.toString())
                .replace("%stone%", SettingsManager.lang.getString("item.$stoneId")!!), sender)
        Util.sendMessage(SettingsManager.lang.getString(
                "add.successful")!!
                .replace("%player%", player.name)
                .replace("%number%", amount.toString())
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
        MainGui().open(player)
    }

    @Command("menuold")
    @PlayerOnly
    fun menuOld(player: Player) {
        MainMenu(player).open()
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
    @PlayerOnly
    fun debug(player: Player) {
        Util.sendMessage("hello world!!", player)
    }
}
