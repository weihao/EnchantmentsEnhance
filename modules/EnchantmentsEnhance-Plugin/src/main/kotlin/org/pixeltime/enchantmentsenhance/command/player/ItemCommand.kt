package org.pixeltime.enchantmentsenhance.command.player

import org.bukkit.Material
import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.manager.SoulboundManager
import org.pixeltime.enchantmentsenhance.gui.Clickable
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu
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
                    var item = Util.getMainHand(player)
                    var level = ItemManager.getItemEnchantLevel(item)
                    val aimingLevel = Integer.parseInt(args[2])
                    var clicked: Clickable = MainMenu.gear
                    if (args[1].equals("tool", ignoreCase = true)) {
                        clicked = MainMenu.tool
                        level = ItemManager.getToolEnchantLevel(item)
                    }
                    if (level < aimingLevel) {
                        for (i in level + 1..aimingLevel) {
                            item = ItemManager.forgeItem(player, item, i, true, clicked)
                        }
                    } else if (aimingLevel < level) {
                        Util.sendMessage(SettingsManager.lang.getString("config.invalidNumber"), player)
                    }
                } catch (ex: Exception) {
                    Util.sendMessage(SettingsManager.lang.getString("config.invalidCommand"), player)
                }
                args[0].equals("setname", ignoreCase = true) -> {
                    val item = Util.getMainHand(player)
                    if (item.type != Material.AIR) {
                        val level: Int
                        val clicked: Clickable
                        if (ItemManager.getToolEnchantLevel(item) != 0) {
                            level = ItemManager.getToolEnchantLevel(item)
                            clicked = MainMenu.tool
                        } else {
                            level = ItemManager.getItemEnchantLevel(item)
                            clicked = MainMenu.gear
                        }
                        val curr = ItemManager.setName(item, Util.toColor(args[1]))
                        try {
                            ItemManager.forgeItem(player, curr, level, true,
                                    clicked)
                            MainMenu.clearPlayer(player.name)
                        } catch (ex: Exception) {
                            Util.sendMessage(SettingsManager.lang.getString("config.invalidItem"), player)
                        }
                    } else {
                        Util.sendMessage(SettingsManager.lang.getString("config.invalidItem"), player)
                    }
                }
                args[0].equals("lore", ignoreCase = true) -> when {
                    args[1].equals("unbound", ignoreCase = true) -> SoulboundManager.removeLore(Util.getMainHand(player))
                    args[1].equals("tradeable", ignoreCase = true) -> SoulboundManager.addLore(Util.getMainHand(player), true, player)
                    args[1].equals("untradeable", ignoreCase = true) -> SoulboundManager.addLore(Util.getMainHand(player), false, player)
                }
            }
        } else {
            Util.sendMessage(SettingsManager.lang.getString(
                    "config.invalidNumber"), player)
        }
    }


    override fun name(): String {
        return "item"
    }

    override fun usage(): String {
        return "/enhance item { upgrade {type} {level} } | setname {name} | lore { tradeable | untradeable | unbound }"
    }

    override fun aliases(): Array<String> {
        return arrayOf("item", "wupin", "wp")
    }
}
