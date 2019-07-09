package org.pixeltime.enchantmentsenhance.command.player

import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.util.Util


class DebugCommand : SubCommand() {
    override val permission: String
        get() = "Enchantmentsenhance.debug"

    override fun onCommand(player: Player, args: Array<String>) {
        when {
            args[0] == "test" -> {
                Util.sendMessage("hello world!!", player)
            }
        }
    }


    override fun name(): String {
        return "debug"
    }


    override fun usage(): String {
        return "/enhance debug"
    }


    override fun aliases(): Array<String> {
        return arrayOf("debug", "tiaoshi", "ts")
    }
}
