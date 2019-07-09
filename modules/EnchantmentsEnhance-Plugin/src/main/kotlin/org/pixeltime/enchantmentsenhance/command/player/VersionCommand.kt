package org.pixeltime.enchantmentsenhance.command.player

import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.command.console.VersionConsoleCommand

class VersionCommand : SubCommand() {


    override val permission: String
        get() = "Enchantmentsenhance.version"

    override fun onCommand(player: Player, args: Array<String>) {
        VersionConsoleCommand().onCommand(player, args)
    }


    override fun name(): String {
        return "version"
    }


    override fun usage(): String {
        return "/enhance version"
    }


    override fun aliases(): Array<String> {
        return arrayOf("version", "ver", "banben", "bb")
    }

}
