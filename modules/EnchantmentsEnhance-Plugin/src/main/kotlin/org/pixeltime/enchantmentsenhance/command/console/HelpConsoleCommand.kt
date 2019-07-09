package org.pixeltime.enchantmentsenhance.command.console

import org.bukkit.command.CommandSender
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.command.SubConsoleCommand

class HelpConsoleCommand : SubConsoleCommand() {

    override fun onCommand(sender: CommandSender, args: Array<String>) {
        Main.getCommandManager().printHelp(sender)
    }


    override fun name(): String {
        return "help"
    }


    override fun usage(): String {
        return "/enhance help"
    }


    override fun aliases(): Array<String> {
        return arrayOf("help", "hl", "bangzhu", "bz")
    }

}
