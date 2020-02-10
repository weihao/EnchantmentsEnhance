package org.pixeltime.enchantmentsenhance.command.player

import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.manager.PlayerStatsManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util
import java.text.ParseException

class SetCommand : SubCommand() {
    override val permission: String
        get() = "Enchantmentsenhance.set"

    override fun onCommand(player: Player, args: Array<String>) {
        if (args.size == 1) {
            try {
                val leverage: Int = Integer.parseInt(args[0])
                if (leverage > 1) {
                    PlayerStatsManager.getPlayerStats(player.name).grind = leverage
                    Util.sendMessage(SettingsManager.lang.getString("set.success")
                    !!.replace("%leverage%",
                            Integer.toString(leverage)), player)
                } else {
                    Util.sendMessage(SettingsManager.lang.getString("set.failed"), player)
                }
            } catch (ex: ParseException) {
                Util.sendMessage(SettingsManager.lang.getString("config.invalidNumber"), player
                )
            } catch (ex: NumberFormatException) {
                Util.sendMessage(SettingsManager.lang.getString("config" +
                        ".invalidNumber"), player)
            }
        } else {
            Util.sendMessage(SettingsManager.lang.getString(
                    "config.invalidNumber"), player)
        }
    }

    override fun name(): String {
        return "set"
    }

    override fun usage(): String {
        return "/enhance set { multiplier }"
    }

    override fun aliases(): Array<String> {
        return arrayOf("")
    }


}
