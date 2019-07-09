package org.pixeltime.enchantmentsenhance.command.player

import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu

class MenuCommand : SubCommand() {


    override val permission: String
        get() = "Enchantmentsenhance.enhance"

    override fun onCommand(player: Player, args: Array<String>) {
        MainMenu(player).open()
    }


    override fun name(): String {
        return "menu"
    }


    override fun usage(): String {
        return "/enhance menu"
    }


    override fun aliases(): Array<String> {
        return arrayOf("menu", "yemian", "ym")
    }

}
