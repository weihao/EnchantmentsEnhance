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

import com.lgou2w.ldk.bukkit.cmd.*
import com.lgou2w.ldk.bukkit.entity.itemInMainHand
import com.lgou2w.ldk.bukkit.item.isAir
import com.lgou2w.ldk.chat.ChatColor
import com.lgou2w.ldk.chat.toColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.reloaded.enhance.Enhancement
import org.pixeltime.enchantmentsenhance.reloaded.enhance.EnhancementHelper

class EECommand(val plugin: EnchantmentsEnhance) {

    val manager : CommandManager = DefaultCommandManager(plugin)

    init {
        manager.completes.addDefaultCompletes()
        manager.transforms.addDefaultTransforms()
        manager.globalFeedback = EnhanceCommandFeedback()
        manager.transforms
            .addTransform(Enhancement::class.java) { Enhancement.fromName(it) }
        manager.completes
            .addCompleter(Enhancement::class.java) { _, _, value -> Enhancement.enhancementNames.filter { it.startsWith(value) } }
    }

    @CommandRoot("enhance")
    private inner class EnhanceCommand : StandardCommand() {

        @Command("apply")
        @Permission("EnchantmentsEnhance.apply")
        @Playable
        fun apply(
                player: Player,
                @Parameter("enhance")
                enhance: Enhancement,
                @Parameter("level")
                @Optional("1")
                level: Int
        ) {
            val stack = player.itemInMainHand
            if (stack == null || stack.isAir()) {
                player.send("&c请手持一个物品后再应用增强附魔.")
                return
            }
            val result = EnhancementHelper.apply(stack, enhance, level)
            player.send("&a增强附魔应用结果: $result")
        }
    }

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
        // TODO Other child command
    }
}
