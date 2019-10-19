package org.pixeltime.enchantmentsenhance.command.console

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.command.SubConsoleCommand
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util


class ReloadConsoleCommand : SubConsoleCommand() {
    override fun onCommand(sender: CommandSender, args: Array<String>) {
        Util.sendMessage(SettingsManager.lang.getString("config.reloading"),
                sender)
//        val console = Bukkit.getServer().consoleSender
//        val command = "reload"
//        Bukkit.dispatchCommand(console, command)
        // Set up the files.
        Bukkit.getPluginManager().disablePlugin(Main.getMain())
        Bukkit.getPluginManager().enablePlugin(Main.getMain())

        Util.sendMessage(SettingsManager.lang.getString("config.reload"),
                sender)
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
