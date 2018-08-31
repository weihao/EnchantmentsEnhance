/*
 *     Copyright (C) 2017-Present HealPot
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.pixeltime.enchantmentsenhance.command.player

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.event.Lore
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
                    var item = player.itemInHand
                    val level = ItemManager.getItemEnchantLevel(item)
                    val aimingLevel = Integer.parseInt(args[1])
                    var clicked: Clickable = MainMenu.gear
                    if (args[1].equals("tool")) {
                        clicked = MainMenu.tool
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
                    val item = player.itemInHand
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
                        val curr = ItemManager.setName(item, ChatColor.translateAlternateColorCodes('&', args[1]))
                        try {
                            player.inventory.removeItem(item)
                            ItemManager.forgeItem(player, curr, level, true, clicked)
                            MainMenu.clearPlayer(player.name)
                        } catch (ex: Exception) {
                            Util.sendMessage(SettingsManager.lang.getString("config.invalidItem"), player)
                        }
                    } else {
                        Util.sendMessage(SettingsManager.lang.getString("config.invalidItem"), player)
                    }
                }
                args[0].equals("lore", ignoreCase = true) -> when {
                    args[1].equals("unbound", ignoreCase = true) -> Lore.removeLore(player.itemInHand)
                    args[1].equals("tradeable", ignoreCase = true) -> Lore.addLore(player.itemInHand, SettingsManager.lang.getString("lore.tradeableLore"), true)
                    args[1].equals("untradeable", ignoreCase = true) -> Lore.addLore(player.itemInHand, SettingsManager.lang.getString("lore.untradeableLore"), false)
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
        return "/enhance item { upgrade {level} } | setname {name} | lore { tradeable | untradeable | unbound }"
    }

    override fun aliases(): Array<String> {
        return arrayOf("item", "wupin", "wp")
    }
}
