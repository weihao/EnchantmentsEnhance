package org.pixeltime.enchantmentsenhance.command.player

import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.api.API
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util

class EnchantmentCommand : SubCommand() {

    override val permission: String
        get() = "Enchantmentsenhance.ench"

    override fun onCommand(player: Player, args: Array<String>) {
        if (args.size == 3) {
            if (args[0].equals("add", ignoreCase = true)) {
                val item = player.itemInHand
                API.AddCustomEnchant(item, args[1], Integer.parseInt(args[2]))
            } else {
                Util.sendMessage(SettingsManager.lang.getString("Config.invalidCommand"), player)
            }
        } else {
            Util.sendMessage(SettingsManager.lang.getString("Config.invalidCommand"), player)
        }
    }

    override fun name(): String {
        return "enchantment"
    }

    override fun usage(): String {
        return "/enhance enchantment {add} {enchantment} {level}"
    }

    override fun aliases(): Array<String> {
        return arrayOf("ench")
    }
}
