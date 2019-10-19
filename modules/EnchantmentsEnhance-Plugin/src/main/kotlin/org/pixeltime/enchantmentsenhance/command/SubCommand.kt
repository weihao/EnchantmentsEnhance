package org.pixeltime.enchantmentsenhance.command

import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

/**
 * /<command> <subcommand> args[0] args[1]
</subcommand></command> */
abstract class SubCommand {

    /**
     * @return Command permission.
     */
    abstract val permission: String

    /**
     * Player oncommand.
     *
     * @param player
     * @param args
     */
    abstract fun onCommand(player: Player, args: Array<String>)

    /**
     * @return Command name.
     */
    abstract fun name(): String

    /**
     * @return Command information.
     */
    abstract fun usage(): String

    /**
     * @return Command aliases.
     */
    abstract fun aliases(): Array<String>

    fun info(): String {
        return "&b${usage()} ${SettingsManager.lang.getString("help.${name()}")}"
    }
}
