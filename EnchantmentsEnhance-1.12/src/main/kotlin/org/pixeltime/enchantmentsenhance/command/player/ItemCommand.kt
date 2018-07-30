package org.pixeltime.enchantmentsenhance.command.player

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.event.blackspirit.Lore
import org.pixeltime.enchantmentsenhance.manager.ItemManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util

class ItemCommand : SubCommand() {

    override val permission: String
        get() = "Enchantmentsenhance.item"

    override fun onCommand(player: Player, args: Array<String>) {
        if (args.size >= 2) {
            when {
                args[0].equals("upgrade", ignoreCase = true) -> try {
                    var item = player.itemInHand
                    val level = ItemManager.getItemEnchantLevel(item)
                    val aimingLevel = Integer.parseInt(args[1])
                    if (level < aimingLevel) {
                        for (i in level + 1..aimingLevel) {
                            item = ItemManager.forgeItem(player, item, i, true)
                        }
                    } else if (aimingLevel < level) {
                        Util.sendMessage(SettingsManager.lang.getString("Config.invalidNumber"), player)
                    }
                } catch (ex: Exception) {
                    Util.sendMessage(SettingsManager.lang.getString("Config.invalidCommand"), player)
                }
                args[0].equals("setname", ignoreCase = true) -> {
                    val item = player.itemInHand
                    val level = ItemManager.getItemEnchantLevel(item)
                    val curr = ItemManager.setName(item, ChatColor.translateAlternateColorCodes('&', args[1]))
                    player.inventory.removeItem(item)
                    ItemManager.forgeItem(player, curr, level, true)
                }
                args[0].equals("lore", ignoreCase = true) -> when {
                    args[1].equals("unbound", ignoreCase = true) -> Lore.removeLore(player.itemInHand)
                    args[1].equals("tradeable", ignoreCase = true) -> Lore.addLore(player.itemInHand, SettingsManager.lang.getString("Lore.tradeableLore"), true)
                    args[1].equals("untradeable", ignoreCase = true) -> Lore.addLore(player.itemInHand, SettingsManager.lang.getString("Lore.untradeableLore"), false)
                }
            }
        } else {
            Util.sendMessage(SettingsManager.lang.getString(
                    "Config.invalidNumber"), player)
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
