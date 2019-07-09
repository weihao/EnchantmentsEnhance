package org.pixeltime.enchantmentsenhance.command.player

import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.command.console.AddConsoleCommand

class AddCommand : SubCommand() {


    override val permission: String
        get() = "Enchantmentsenhance.add"

    override fun onCommand(player: Player, args: Array<String>) {
        AddConsoleCommand().onCommand(player, args)
    }


    override fun name(): String {
        return "add"
    }


    override fun usage(): String {
        return "/enhance add { player } { stone } { amount }"
    }


    override fun aliases(): Array<String> {
        return arrayOf("add", "give", "tianjia", "tj")
    }

}
