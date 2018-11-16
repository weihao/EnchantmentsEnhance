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

import org.bukkit.Bukkit
import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.event.Enhance
import org.pixeltime.enchantmentsenhance.gui.Clickable
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract
import org.pixeltime.enchantmentsenhance.gui.MenuCoord
import org.pixeltime.enchantmentsenhance.gui.menu.icons.*
import org.pixeltime.enchantmentsenhance.manager.DataManager
import org.pixeltime.enchantmentsenhance.manager.ItemManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.DyeColors
import org.pixeltime.enchantmentsenhance.util.ItemBuilder
import org.pixeltime.enchantmentsenhance.util.Util
import org.pixeltime.enchantmentsenhance.util.XMaterial
import java.util.*

class MainMenu : GUIAbstract {

    constructor(player: Player) : super(player, 54, SettingsManager.lang.getString("menu.gui.title")) {
        update()
    }

    override fun update() {
        inventory.clear()
        actions.clear()
        val player = Bukkit.getPlayer(playerName)

        if (!enhancingMode.containsKey(playerName)) {
            enhancingMode[playerName] = gear
        }

        if (itemOnEnhancingSlot.containsKey(playerName)) {
            // Sets Enhancing item on display.
            setItem(Util.getSlot(8, 4), itemOnEnhancingSlot[playerName]!!)
            // Sets Enhance button.
            if (enhancingMode[playerName] == gear) {
                if (Enhance.getValidationOfItem(itemOnEnhancingSlot[playerName]!!) &&
                    Enhance.getValidationOfPlayer(itemOnEnhancingSlot[playerName]!!, player, enhancingMode[playerName]!!)
                ) {
                    setItem(
                            enhance.position,
                            enhance.getGlowingItem(itemOnEnhancingSlot[playerName]!!)) { clickType ->
                        if (clickType == ClickType.LEFT || clickType == ClickType.DOUBLE_CLICK) {
                            inProgress[playerName] = object : BukkitRunnable() {
                                var count = 0
                                var animate: MutableList<ItemStack> = ArrayList()
                                override fun run() {
                                    if (count == 0) {
                                        setItem(cancel.position, cancel.getGlowingItem(playerName)) {
                                            this.cancel()
                                            update()
                                        }
                                        setItem(force.position, cancel.getGlowingItem(playerName)) {
                                            this.cancel()
                                            update()
                                        }
                                    }
                                    if (count == 5) {
                                        try {
                                            Enhance.diceToEnhancement(itemOnEnhancingSlot[playerName]!!, player, enhancingMode[playerName]!!)
                                        } catch (ex: Exception) {
                                            // Player might not be online.
                                        }

                                        this.cancel()
                                        update()
                                        inProgress.remove(playerName)
                                        return
                                    }
                                    animate.add(randomWool())
                                    if (count >= 0) {
                                        for (i in animate.indices) {
                                            setItem(Util.getSlot(3 + i, 4), animate[animate.size - i - 1])
                                        }
                                    }
                                    count++
                                }
                            }.runTaskTimer(Main.getMain(), 0L, 10L)
                        } else {
                            // Right click.
                            Enhance.diceToEnhancement(
                                    itemOnEnhancingSlot[playerName]!!, player, enhancingMode[playerName]!!)
                        }
                    }
                } else {
                    setItem(enhance.position, enhance.getItem(itemOnEnhancingSlot[playerName]!!)) {
                        val enchantLevel = ItemManager.getItemEnchantLevel(itemOnEnhancingSlot[playerName]!!) + 1
                        val stoneId = Enhance.getStoneId(itemOnEnhancingSlot[playerName]!!, enchantLevel, enhancingMode[playerName]!!)
                        Util.sendMessage(SettingsManager.lang.getString("item.noItem")
                            .replace("%STONE%".toRegex(), SettingsManager.lang.getString(
                                    "item.$stoneId")), player)
                    }
                }
            } else if (enhancingMode[playerName] == tool) {
                if (Enhance.getValidationOfToolItem(itemOnEnhancingSlot[playerName]!!) &&
                    Enhance.getToolValidationOfPlayer(itemOnEnhancingSlot[playerName]!!, player, enhancingMode[playerName]!!)
                ) {
                    setItem(enhance.position, enhance.getGlowingItem(itemOnEnhancingSlot[playerName]!!)) { clickType ->
                        if (clickType == ClickType.LEFT || clickType == ClickType.DOUBLE_CLICK) {
                            inProgress[playerName] = object : BukkitRunnable() {
                                var count = 0
                                var animate: MutableList<ItemStack> = ArrayList()
                                override fun run() {
                                    if (count == 0) {
                                        setItem(cancel.position, cancel.getGlowingItem(playerName)) {
                                            this.cancel()
                                            update()
                                        }
                                        setItem(force.position, cancel.getGlowingItem(playerName)) {
                                            this.cancel()
                                            update()
                                        }
                                    }
                                    if (count == 5) {
                                        try {
                                            Enhance.diceToEnhancement(itemOnEnhancingSlot[playerName]!!, player, enhancingMode[playerName]!!)
                                        } catch (ex: Exception) {
                                            // Player might not be online.
                                        }

                                        this.cancel()
                                        update()
                                        inProgress.remove(playerName)
                                        return
                                    }
                                    animate.add(randomWool())
                                    if (count >= 0) {
                                        for (i in animate.indices) {
                                            setItem(Util.getSlot(3 + i, 4), animate[animate.size - i - 1])
                                        }
                                    }
                                    count++
                                }
                            }.runTaskTimer(Main.getMain(), 0L, 10L)
                        } else {
                            // Right click.
                            Enhance.diceToEnhancement(itemOnEnhancingSlot[playerName]!!, player, enhancingMode[playerName]!!)
                        }
                    }
                } else {
                    setItem(enhance.position, enhance.getItem(itemOnEnhancingSlot[playerName]!!)) {
                        val enchantLevel = ItemManager.getItemEnchantLevel(itemOnEnhancingSlot[playerName]!!) + 1
                        val stoneId = Enhance.getStoneId(itemOnEnhancingSlot[playerName]!!, enchantLevel, enhancingMode[playerName]!!)
                        Util.sendMessage(SettingsManager.lang.getString("item.noItem")
                            .replace("%STONE%".toRegex(), SettingsManager.lang.getString(
                                    "item.$stoneId")), player)
                    }
                }
            }


            if (DataManager.maximumFailstackApplied[ItemManager.getItemEnchantLevel(itemOnEnhancingSlot[playerName]!!) + 1] > 0 &&
                DataManager.costToForceEnchant[ItemManager.getItemEnchantLevel(itemOnEnhancingSlot[playerName]!!) + 1] > 0
            ) {
                if (Enhance.getValidationOfForce(itemOnEnhancingSlot[playerName]!!, player, enhancingMode[playerName]!!)) {
                    setItem(force.position, force.getGlowingItem(itemOnEnhancingSlot[playerName]!!, enhancingMode[playerName]!!)) { Enhance.forceToEnhancement(itemOnEnhancingSlot[playerName]!!, player, enhancingMode[playerName]!!) }
                } else {
                    setItem(force.position, force.getItem(itemOnEnhancingSlot[playerName]!!, enhancingMode[playerName]!!)) {
                        // Current enchant level before enhancing
                        val enchantLevel = ItemManager.getItemEnchantLevel(itemOnEnhancingSlot[playerName]!!) + 1
                        // Finds the stone used in the enhancement
                        val stoneId = Enhance.getStoneId(itemOnEnhancingSlot[playerName]!!, enchantLevel, enhancingMode[playerName]!!)
                        Util.sendMessage(SettingsManager.lang.getString("item.noItem")
                            .replace("%STONE%".toRegex(), SettingsManager.lang.getString(
                                    "item.$stoneId")), player)
                    }
                }
            }

            setItem(remove.position, remove.getGlowingItem(playerName)) {
                clearPlayer(playerName)
                if (MainMenu.inProgress.containsKey(player.name)) {
                    MainMenu.inProgress[player.name]?.cancel()
                }
            }

            setItem(stats.position, stats.getItem(playerName, enhancingMode[playerName]!!))

            setItem(stone.position, stone.getItem(itemOnEnhancingSlot[playerName]!!, player, enhancingMode[playerName]!!))

        } else {
            setItem(Util.getSlot(8, 4), ItemStack(Material.AIR))
            setItem(remove.position, ItemStack(Material.AIR))
            setItem(enhance.position, enhance.getItem(playerName))
            setItem(force.position, force.getItem(playerName))
            setItem(stats.position, stats.getItem(playerName))
        }

        setItem(store.position, if (Main.getApi().getFailstack(player.name) == 0) store.getItem(playerName) else store.getGlowingItem(playerName)) { Main.getApi().addAdvice(player.name) }

        setItem(item.position, item.getItem(player.name)) {
            object : BukkitRunnable() {
                override fun run() {
                    player.closeInventory()
                    ItemMenu(player).open()
                }
            }.runTaskLaterAsynchronously(Main.getMain(), 2L)
        }

        setItem(valks.position, valks.getItem(player)) {
            object : BukkitRunnable() {
                override fun run() {
                    player.closeInventory()
                    ValksMenu(player).open()
                }
            }.runTaskLaterAsynchronously(Main.getMain(), 2L)
        }

        setItem(gear.position, if (enhancingMode.containsKey(playerName) && enhancingMode[playerName] == gear)
            gear.getGlowingItem(playerName)
        else
            gear.getItem(playerName)
        ) {
            enhancingMode[playerName] = gear
            clearPlayer(playerName)
        }

        setItem(tool.position, if (enhancingMode.containsKey(playerName) && enhancingMode[playerName] == tool)
            tool.getGlowingItem(playerName)
        else
            tool.getItem(playerName)
        ) {
            enhancingMode[playerName] = tool
            clearPlayer(playerName)
        }
        setItem(accessory.position, if (enhancingMode.containsKey(playerName) && enhancingMode[playerName] == accessory)
            accessory.getGlowingItem(playerName)
        else
            accessory.getItem(playerName)
        ) {
            enhancingMode[playerName] = accessory
            clearPlayer(playerName)
        }

        for (i in MenuCoord.getPlaceHolderCoords()) {
            setItem(i, ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial()).setDyeColor(DyeColor.BLACK).setName("&0").toItemStack())
        }
    }

    companion object {
        private val WOOL = arrayOf(
                ItemBuilder(XMaterial.BLACK_WOOL.parseMaterial()).setDyeColor(DyeColor.BLACK).toItemStack(),
                ItemBuilder(XMaterial.BLUE_WOOL.parseMaterial()).setDyeColor(DyeColor.BLUE).toItemStack(),
                ItemBuilder(XMaterial.BROWN_WOOL.parseMaterial()).setDyeColor(DyeColor.BROWN).toItemStack(),
                ItemBuilder(XMaterial.CYAN_WOOL.parseMaterial()).setDyeColor(DyeColor.CYAN).toItemStack(),
                ItemBuilder(XMaterial.LIGHT_GRAY_WOOL.parseMaterial()).setDyeColor(DyeColors.LIGHT_GRAY.bukkitDyeColor()).toItemStack(),
                ItemBuilder(XMaterial.GREEN_WOOL.parseMaterial()).setDyeColor(DyeColor.GREEN).toItemStack(),
                ItemBuilder(XMaterial.LIGHT_BLUE_WOOL.parseMaterial()).setDyeColor(DyeColor.LIGHT_BLUE).toItemStack(),
                ItemBuilder(XMaterial.GRAY_WOOL.parseMaterial()).setDyeColor(DyeColor.GRAY).toItemStack(),
                ItemBuilder(XMaterial.LIME_WOOL.parseMaterial()).setDyeColor(DyeColor.LIME).toItemStack(),
                ItemBuilder(XMaterial.MAGENTA_WOOL.parseMaterial()).setDyeColor(DyeColor.MAGENTA).toItemStack(),
                ItemBuilder(XMaterial.ORANGE_WOOL.parseMaterial()).setDyeColor(DyeColor.ORANGE).toItemStack(),
                ItemBuilder(XMaterial.PINK_WOOL.parseMaterial()).setDyeColor(DyeColor.PINK).toItemStack(),
                ItemBuilder(XMaterial.PURPLE_WOOL.parseMaterial()).setDyeColor(DyeColor.PURPLE).toItemStack(),
                ItemBuilder(XMaterial.RED_WOOL.parseMaterial()).setDyeColor(DyeColor.RED).toItemStack(),
                ItemBuilder(XMaterial.WHITE_WOOL.parseMaterial()).setDyeColor(DyeColor.WHITE).toItemStack(),
                ItemBuilder(XMaterial.YELLOW_WOOL.parseMaterial()).setDyeColor(DyeColor.YELLOW).toItemStack()
        )
        var itemOnEnhancingSlot: MutableMap<String, ItemStack> = HashMap()
        var enhancingMode: MutableMap<String, Clickable> = HashMap()
        var inProgress: MutableMap<String, BukkitTask> = HashMap()
        var enhance = EnhanceIcon()
        var force = ForceIcon()
        var remove = RemoveIcon()
        var stats = StatsIcon()
        var store = StoreIcon()
        var stone = StoneIcon()
        var item = ItemIcon()
        var valks = ValksIcon()
        var gear = GearIcon()
        var tool = ToolIcon()
        var accessory = AccessoryIcon()
        var cancel = CancelIcon()

        fun clearPlayer(playerName: String) {
            itemOnEnhancingSlot.remove(playerName)
        }

        fun randomWool(): ItemStack {
            val random = Random()
            return WOOL[random.nextInt(WOOL.size)]
        }
    }
}
