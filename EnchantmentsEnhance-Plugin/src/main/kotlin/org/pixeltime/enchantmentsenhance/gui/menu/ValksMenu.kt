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

package org.pixeltime.enchantmentsenhance.gui.menu

import com.lgou2w.ldk.bukkit.runTaskAsyncLater
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.scheduler.BukkitRunnable
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract
import org.pixeltime.enchantmentsenhance.gui.menu.icons.BackIcon
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager
import org.pixeltime.enchantmentsenhance.manager.ItemManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.mysql.PlayerStat
import org.pixeltime.enchantmentsenhance.util.ItemBuilder
import org.pixeltime.enchantmentsenhance.util.Util

class ValksMenu : GUIAbstract {

    private val back = BackIcon()
    private var currPage = 1

    constructor(player: Player) : super(player, 54, SettingsManager.lang.getString("valks.gui")) {
        update()
    }

    override fun update() {
        inventory.clear()
        actions.clear()
        val player = Bukkit.getPlayer(playerName)
        val inv = PlayerStat.getPlayerStats(playerName)!!.valks!!
        try {
            for (i in 0 until Math.min(inv.size, 54)) {
                val index = i + (currPage - 1) * 54
                val level = inv[index]
                setItem(
                        Util.getSlot((i % 9) + 1, (i / 9) + 1),
                        CompatibilityManager.glow.addGlow(ItemBuilder(Material.BOOK, level)
                            .setName(SettingsManager.lang.getString("item.valks") + "+" + level)
                            .addLoreLine(SettingsManager.lang.getString("menu.leftAdviceInfo"))
                            .addLoreLine(SettingsManager.lang.getString("menu.rightInfo"))
                            .toItemStack())
                ) { clickType ->
                    Main.getMain().runTaskAsyncLater({
                        if (clickType == ClickType.LEFT) {
                            if (level > 0) {
                                if (Main.getApi().getFailstack(player.name) == 0) {
                                    Main.getApi().addFailstack(player.name, level)
                                    PlayerStat.getPlayerStats(playerName)!!.valks!!.removeAt(index)
                                    Util.sendMessage(SettingsManager.lang.getString(
                                            "valks.used").replace("%LEVEL%".toRegex(), Integer
                                        .toString(level)), player)
                                    object : BukkitRunnable() {
                                        override fun run() {
                                            player.closeInventory()
                                            MainMenu(player).open()
                                        }
                                    }.runTaskLaterAsynchronously(Main.getMain(), 2L)
                                } else {
                                    Util.sendMessage(SettingsManager.lang.getString(
                                            "valks.hasFailstack"), player)
                                }
                            } else {
                                Util.sendMessage(SettingsManager.lang.getString(
                                        "config.invalidNumber"), player)
                            }
                        }
                        if (clickType == ClickType.RIGHT && SettingsManager.config.getBoolean("enableItemMaterialization")) {
                            if (!Util.invFull(player)) {
                                player.inventory.addItem(ItemManager.adviceMaterialize(level))
                                PlayerStat.getPlayerStats(playerName)!!.valks!!.removeAt(index)
                                update()
                            } else {
                                Util.sendMessage(SettingsManager.lang.getString("materialize.inventoryFull"), player)
                            }
                        }
                    }, 2L)
                }
            }
        } catch (ex: IndexOutOfBoundsException) {
            // Expected.
        }

        setItem(back.position, back.getItem(playerName)) {
            Main.getMain().runTaskAsyncLater({
                if (currPage == 1) {
                    player.closeInventory()
                    MainMenu(player).open()
                } else {
                    currPage--
                }
            }, 2L)
        }

        if (inv.size > currPage * 54) {
            setItem(
                    Util.getSlot(9, 6),
                    ItemBuilder(Material.ARROW)
                        .setName(SettingsManager.lang.getString("menu.gui.next"))
                        .addLoreLine(SettingsManager.lang.getString("menu.lore.next"))
                        .toItemStack()
            ) {
                currPage++
            }
        }
    }
}
