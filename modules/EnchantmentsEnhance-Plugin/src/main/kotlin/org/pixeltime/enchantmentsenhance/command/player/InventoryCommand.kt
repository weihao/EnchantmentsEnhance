package org.pixeltime.enchantmentsenhance.command.player

import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.gui.menu.ItemMenu

class InventoryCommand : SubCommand() {


    override val permission: String
        get() = "Enchantmentsenhance.enhance"

    override fun onCommand(player: Player, args: Array<String>) {
        ItemMenu(player).open()
    }


    override fun name(): String {
        return "inventory"
    }


    override fun usage(): String {
        return "/enhance inventory"
    }


    override fun aliases(): Array<String> {
        return arrayOf("inv", "inventory", "beibao", "b")
    }

}
