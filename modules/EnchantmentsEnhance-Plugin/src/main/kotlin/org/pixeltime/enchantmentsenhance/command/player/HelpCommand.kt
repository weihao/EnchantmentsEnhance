package org.pixeltime.enchantmentsenhance.command.player

import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.command.SubCommand

class HelpCommand : SubCommand() {


    override val permission: String
        get() = "Enchantmentsenhance.enhance"

    override fun onCommand(player: Player, args: Array<String>) {
        Main.getCommandManager().printHelp(player)
    }


    override fun name(): String {
        return "help"
    }


    override fun usage(): String {
        return "/enhance help { page }"
    }


    override fun aliases(): Array<String> {
        return arrayOf("help", "hl", "bangzhu", "bz")
    }

}
