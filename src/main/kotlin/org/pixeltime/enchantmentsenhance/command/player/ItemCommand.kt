package org.pixeltime.enchantmentsenhance.command.player

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.event.blackspirit.Enhance
import org.pixeltime.enchantmentsenhance.manager.ItemManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class ItemCommand : SubCommand() {

    override val permission: String
        get() = "Enchantmentsenhance.item"

    override fun onCommand(player: Player, args: Array<String>) {
        if (args[0].equals("upgrade", ignoreCase = true)) {
            if (args.size == 2) {
                try {
                    Enhance.enhanceSuccess(player.itemInHand, player, true, Integer.parseInt(args[1]) - 1)
                } catch (ex: Exception) {
                    player.sendMessage(SettingsManager.config.getString("Config.invalidCommand"))
                }

            }
        } else if (args[0].equals("setname", ignoreCase = true)) {
            val item = player.itemInHand
            val level = ItemManager.getItemEnchantLevel(item)
            val curr = ItemManager.setName(item, ChatColor.translateAlternateColorCodes('&', args[1]))
            player.inventory.removeItem(item)
            ItemManager.forgeItem(player, curr, level)
        }
    }


    override fun name(): String {
        return "item"
    }

    override fun usage(): String {
        return "/enhance item { {upgrade} {level} } | {setname} {name} | {lore} { {tradeable | untradeable | unbound} }"
    }

    override fun aliases(): Array<String> {
        return arrayOf("item", "wupin", "wp")
    }
}
