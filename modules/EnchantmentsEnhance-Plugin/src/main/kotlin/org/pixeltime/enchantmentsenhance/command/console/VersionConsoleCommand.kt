package org.pixeltime.enchantmentsenhance.command.console

import org.bukkit.command.CommandSender
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.command.SubConsoleCommand
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util

class VersionConsoleCommand : SubConsoleCommand() {

    override fun onCommand(sender: CommandSender, args: Array<String>) {
        Util.sendMessage(
            SettingsManager.lang.getString(
                "config.checkingVersion"
            )!!.replace(
                "%version%".toRegex(), Main.getMain()
                    .description.version
            ), sender
        )
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
