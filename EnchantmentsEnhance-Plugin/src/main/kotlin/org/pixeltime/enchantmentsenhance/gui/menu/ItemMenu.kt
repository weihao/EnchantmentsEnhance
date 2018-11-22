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
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract
import org.pixeltime.enchantmentsenhance.gui.menu.icons.BackIcon
import org.pixeltime.enchantmentsenhance.gui.menu.icons.GrindIcon
import org.pixeltime.enchantmentsenhance.gui.menu.icons.ReblathIcon
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager
import org.pixeltime.enchantmentsenhance.manager.ItemManager
import org.pixeltime.enchantmentsenhance.manager.MaterialManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.mysql.PlayerStat
import org.pixeltime.enchantmentsenhance.util.Util
import java.util.*

class ItemMenu : GUIAbstract {

    private val BUNDLE = 5
    var clickedItem: MutableMap<String, Int> = HashMap()
    private val back = BackIcon()
    private val reblath = ReblathIcon()
    private val grind = GrindIcon()

    constructor(player: Player) : super(player, 54, SettingsManager.lang.getString("item.title")) {
        update()
    }

    override fun update() {
        inventory.clear()
        actions.clear()
        val player = Bukkit.getPlayer(playerName)
        for (i in 0 until MaterialManager.stoneTypes.size) {
            val stoneId = i
            setItem(
                    Util.getSlot((i % 9) + 1, (i / 9) + 1),
                    if (clickedItem.containsKey(playerName) && stoneId == clickedItem[playerName])
                        CompatibilityManager.glow.addGlow(MainMenu.stone.getItem(i, player))
                    else
                        MainMenu.stone.getItem(i, player)) { clickType ->
                if (clickType == ClickType.LEFT) {
                    if (Main.getApi().getItem(playerName, stoneId) > 0) {
                        if (clickedItem.containsKey(playerName) && clickedItem[playerName] == stoneId) {
                            clickedItem.remove(player.name)
                        } else {
                            clickedItem[player.name] = stoneId
                        }
                    }
                }
                if (clickType == ClickType.RIGHT && SettingsManager.config.getBoolean("enableItemMaterialization")) {
                    // If play has enough stones.
                    if (Main.getApi().getItem(playerName, stoneId) > BUNDLE) {
                        if (!Util.invFull(player)) {
                            player.inventory.addItem(ItemManager.itemMaterialize(stoneId, BUNDLE))
                            Main.getApi().addItem(playerName, stoneId, -BUNDLE)
                        } else {
                            Util.sendMessage(SettingsManager.lang.getString("materialize.inventoryFull"), player)
                        }
                    } else {
                        Util.sendMessage(SettingsManager.lang.getString("materialize.notEnoughItem"), player)
                    }
                }
            }

            setItem(back.position, back.getItem(playerName)) {
                Main.getMain().runTaskAsyncLater({
                    player.closeInventory()
                    MainMenu(player).open()
                }, 2L)
            }

            setItem(grind.position, grind.getItem(playerName)) {
                if (clickedItem.containsKey(player.name)) {
                    // If player has item to failstack.
                    if (Main.getApi().getItem(player.name, clickedItem[player.name]!!) > 0) {
                        var locked = 2
                        if (PlayerStat.getPlayerStats(playerName) != null) {
                            locked = PlayerStat.getPlayerStats(playerName)!!.grind
                        }
                        Main.getApi().addItem(player.name, clickedItem[player.name]!!, -1)
                        val random = Random()
                        val num = random.nextDouble()

                        if (num < 1.0 / locked) {
                            // Reward
                            Util.sendMessage(SettingsManager.lang.getString("grind.success")
                                .replace("%amount%", Integer.toString(locked)), player)
                            Main.getApi().addItem(player.name, clickedItem[player.name]!!, locked)
                        } else {
                            // Fail
                            Util.sendMessage(SettingsManager.lang.getString("grind.failed"), player)
                        }
                    } else {
                        Util.sendMessage(SettingsManager.lang.getString("gui.noItem"), player)
                    }
                } else {
                    Util.sendMessage(SettingsManager.lang.getString("gui.missingItem"), player)
                }
            }

            setItem(reblath.position, reblath.getItem(playerName)) {
                if (clickedItem.containsKey(player.name)) {
                    // If player has item to failstack.
                    if (Main.getApi().getItem(player.name, clickedItem[player.name]!!) > 0) {
                        // Roll.
                        if (Math.random() * 100 > reblath.chance) {
                            val levelsToAdd = 1
                            Main.getApi().addFailstack(player.name, levelsToAdd)
                            Main.getApi().addItem(player.name, clickedItem[player.name]!!, -1)
                            Util.sendMessage(SettingsManager.lang.getString("gui.addFailstack")
                                .replace("%level%", Integer.toString(levelsToAdd))
                                .replace("%size%", Integer.toString(Main.getApi().getFailstack(player.name))), player)
                        } else {
                            Util.sendMessage(SettingsManager.lang.getString("gui.resetFailstack").replace("%level%", Integer.toString(Main.getApi().getFailstack(player.name))), player)
                            Main.getApi().resetFailstack(player.name)
                        }
                    } else {
                        Util.sendMessage(SettingsManager.lang.getString("gui.noItem"), player)
                    }
                } else {
                    Util.sendMessage(SettingsManager.lang.getString("gui.missingItem"), player)
                }
            }
        }
    }
}
