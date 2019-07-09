package org.pixeltime.enchantmentsenhance.command.player

import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util

class EnchantmentCommand : SubCommand() {

    override val permission: String
        get() = "Enchantmentsenhance.ench"

    override fun onCommand(player: Player, args: Array<String>) {
        if (args.size >= 2) {
            if (args[0].equals("add", ignoreCase = true)) {
                val item = Util.getMainHand(player)
                if (item == null) {
                    Util.sendMessage(SettingsManager.lang.getString("config.invalidItem"), player)
                    return
                }
                var level = 1
                try {
                    level = Integer.parseInt(args[2])
                } catch (ex: NumberFormatException) {
                    // Expected
                } catch (ex: ArrayIndexOutOfBoundsException) {
                    // Expected
                }
                if (Main.getApi().addCustomEnchant(item, args[1], level)) {
                    Util.sendMessage(SettingsManager.lang.getString("config.success"), player)
                } else {
                    Util.sendMessage(SettingsManager.lang.getString("config.invalidEnchant"), player)
                }
            } else {
                Util.sendMessage(SettingsManager.lang.getString("config.invalidCommand"), player)
            }
        } else {
            Util.sendMessage(SettingsManager.lang.getString("config.invalidCommand"), player)
        }
    }

    override fun name(): String {
        return "enchantment"
    }

    override fun usage(): String {
        return "/enhance enchantment add {enchantment} {level}"
    }

    override fun aliases(): Array<String> {
        return arrayOf("ench")
    }
}
