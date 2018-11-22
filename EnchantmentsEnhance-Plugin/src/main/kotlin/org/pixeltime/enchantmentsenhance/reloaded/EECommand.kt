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

package org.pixeltime.enchantmentsenhance.reloaded

import com.lgou2w.ldk.bukkit.cmd.CommandExecutor
import com.lgou2w.ldk.bukkit.cmd.CommandManager
import com.lgou2w.ldk.bukkit.cmd.CommandRoot
import com.lgou2w.ldk.bukkit.cmd.DefaultCommandManager
import com.lgou2w.ldk.bukkit.cmd.RegisteredCommand
import com.lgou2w.ldk.bukkit.cmd.SimpleCommandFeedback
import com.lgou2w.ldk.bukkit.cmd.StandardCommand
import com.lgou2w.ldk.chat.ChatColor
import com.lgou2w.ldk.chat.toColor
import org.bukkit.command.CommandSender
import org.pixeltime.enchantmentsenhance.reloaded.command.MenuCommand

class EECommand(val plugin: EnchantmentsEnhance) {

    val manager : CommandManager = DefaultCommandManager(plugin)

    init {
        manager.completes.addDefaultCompletes()
        manager.transforms.addDefaultTransforms()
        manager.globalFeedback = EnhanceCommandFeedback()
    }

    @CommandRoot("enhance")
    private inner class EnhanceCommand : StandardCommand()
    private inner class EnhanceCommandFeedback : SimpleCommandFeedback() {
        // TODO Feedback
        override fun onPlayable(sender: CommandSender, name: String, command: RegisteredCommand, executor: CommandExecutor, args: Array<out String>) {
            sendMessage(command, sender, ChatColor.RED + "The console cannot execute this command.")
            sendMessage(command, sender, ChatColor.RED + "控制台不能执行此命令.")
        }
    }

    fun init() {
        val enhance = manager.registerCommand(EnhanceCommand())
        enhance.prefix = "&6● EnchantmentsEnhance &8>> &r".toColor()
        enhance.registerChild(MenuCommand())
        // TODO Other child command
    }
}
