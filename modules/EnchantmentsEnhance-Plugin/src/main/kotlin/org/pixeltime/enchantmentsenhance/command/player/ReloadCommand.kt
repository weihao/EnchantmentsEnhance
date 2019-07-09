package org.pixeltime.enchantmentsenhance.command.player

import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.command.console.ReloadConsoleCommand

class ReloadCommand : SubCommand() {


    override val permission: String
        get() = "Enchantmentsenhance.reload"

    override fun onCommand(player: Player, args: Array<String>) {
        ReloadConsoleCommand().onCommand(player, args)
    }


    override fun name(): String {
        return "reload"
    }


    override fun usage(): String {
        return "/enhance reload"
    }


    override fun aliases(): Array<String> {
        return arrayOf("reload", "rel", "chongzai", "cz")
    }

}
