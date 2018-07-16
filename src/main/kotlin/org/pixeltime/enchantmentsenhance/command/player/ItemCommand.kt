package org.pixeltime.enchantmentsenhance.command.player

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.event.blackspirit.Lore
import org.pixeltime.enchantmentsenhance.manager.ItemManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class ItemCommand : SubCommand() {

    override val permission: String
        get() = "Enchantmentsenhance.item"

    override fun onCommand(player: Player, args: Array<String>) {
        if (args[0].equals("upgrade", ignoreCase = true)) {
            if (args.size == 2) {
                try {
                    val item = player.itemInHand
                    val level = ItemManager.getItemEnchantLevel(item)
                    val aimingLevel = Integer.parseInt(args[1])
                    if (level < aimingLevel) {
                        for (i in level + 1..aimingLevel) {
                            ItemManager.forgeItem(player, item, i, true)
                        }
                    } else if (aimingLevel < level){
                        for (i in level downTo aimingLevel + 1) {
                            ItemManager.forgeItem(player, item, i, false)
                        }
                    }
                } catch (ex: Exception) {
                    player.sendMessage(SettingsManager.config.getString("Config.invalidCommand"))
                }

            }
        } else if (args[0].equals("setname", ignoreCase = true)) {
            val item = player.itemInHand
            val level = ItemManager.getItemEnchantLevel(item)
            val curr = ItemManager.setName(item, ChatColor.translateAlternateColorCodes('&', args[1]))
            player.inventory.removeItem(item)
            ItemManager.forgeItem(player, curr, level, true)
        } else if (args[0].equals("lore", ignoreCase = true)) {
            if (args.size == 2) {
                when {
                    args[1].equals("unbound", ignoreCase = true) -> Lore.removeLore(player.itemInHand)
                    args[1].equals("tradeable", ignoreCase = true) -> Lore.addLore(player.itemInHand, SettingsManager.lang.getString("Lore.tradeableLore"), true)
                    args[1].equals("untradeable", ignoreCase = true) -> Lore.addLore(player.itemInHand, SettingsManager.lang.getString("Lore.untradeableLore"), false)
                }
            }
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
