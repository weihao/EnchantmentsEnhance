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
import com.lgou2w.ldk.bukkit.cmd.Optional
import com.lgou2w.ldk.bukkit.cmd.Parameter
import com.lgou2w.ldk.bukkit.cmd.Permission
import com.lgou2w.ldk.bukkit.cmd.PermissionDefaultValue
import com.lgou2w.ldk.bukkit.cmd.PlayerOnly
import com.lgou2w.ldk.bukkit.cmd.StandardCommand
import com.lgou2w.ldk.chat.toColor
import com.lgou2w.ldk.common.Enums
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.permissions.PermissionDefault
import org.pixeltime.enchantmentsenhance.Main

@CommandRoot("item")
@Permission("Enchantmentsenhance.item")
@PermissionDefaultValue(PermissionDefault.OP)
class EnhanceItemCommand(val main: Main) : StandardCommand() {

    override fun initialize() {
        command.manager.transforms
            .addTransform(LoreType::class.java) {
                Enums.ofName(LoreType::class.java, it.toUpperCase())
            }
        command.manager.completes
            .addCompleter(LoreType::class.java) { _, _, value ->
                LoreType.values()
                    .asSequence()
                    .map { it.name.toLowerCase() }
                    .filter { it.startsWith(value) }
                    .toList()
            }
    }

    @Command("help")
    @Permission("Enchantmentsenhance.item.help")
    @PermissionDefaultValue(PermissionDefault.OP)
    fun help(sender: CommandSender) {
        sender.sendMessage("&b&l&m          &d EnchantmentsEnhance &b&l&m          ".toColor())
        CommandHelper
            .sendSimpleCommandTooltips(sender, command, newLineDesc = true, named = true, permission = true,
                    sorted = CommandHelper.Sorted.DEFAULT)
    }

    @Command("upgrade")
    @Permission("Enchantmentsenhance.item.upgrade")
    @PermissionDefaultValue(PermissionDefault.OP)
    @PlayerOnly
    fun upgrade(player: Player,
                @Parameter("type")
                type: String,
                @Parameter("level")
                @Optional("1")
                level: Int
    ) {
    }

    @Command("setname")
    @Permission("Enchantmentsenhance.item.setname")
    @PermissionDefaultValue(PermissionDefault.OP)
    @PlayerOnly
    fun setName(player: Player, name: String) {
    }

    enum class LoreType {
        UNBOUND,
        TRADEABLE,
        UNTRADEABLE,
        ;
    }

    @Command("lore")
    @Permission("Enchantmentsenhance.item.lore")
    @PermissionDefaultValue(PermissionDefault.OP)
    @PlayerOnly
    fun setLore(player: Player, type: LoreType) {
    }
}
