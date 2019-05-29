/*
 *     Copyright (C) 2017-Present 25 (https://github.com/25)
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

package org.pixeltime.enchantmentsenhance.gui

import com.lgou2w.ldk.bukkit.cancelTask
import com.lgou2w.ldk.bukkit.compatibility.Sounds
import com.lgou2w.ldk.bukkit.compatibility.XMaterial
import com.lgou2w.ldk.bukkit.compatibility.notEq
import com.lgou2w.ldk.bukkit.gui.GuiBase
import com.lgou2w.ldk.bukkit.gui.GuiFactory
import com.lgou2w.ldk.bukkit.gui.GuiType
import com.lgou2w.ldk.bukkit.item.builder
import com.lgou2w.ldk.chat.toColor
import com.lgou2w.ldk.common.ApplicatorFunction
import com.lgou2w.ldk.common.Runnable
import com.lgou2w.ldk.common.notNull
import org.bukkit.Material
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.applyGlow
import org.pixeltime.enchantmentsenhance.event.Enhance
import org.pixeltime.enchantmentsenhance.getStringNotNull
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu
import org.pixeltime.enchantmentsenhance.manager.DataManager
import org.pixeltime.enchantmentsenhance.manager.ItemManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import kotlin.math.max

class MainGui : GuiBase(
        GuiType.CHEST_6,
        SettingsManager.lang.getString("menu.gui.title").notNull().toColor()
) {

    init {
        initBasic()
        initListeners()
    }

    override fun open(human: HumanEntity) {
        val player = human as Player
        initWalls()
        initFunctions(player)
        super.open(player)
        Sounds.LEVEL_UP.tryPlay(human.location, 1f, 2f)
    }

    private fun initBasic() {
        isAllowMove = false
        isActivated = false
        isInProgressing = false
    }

    private var isActivated : Boolean
        get() = getPropertyAsNotNull("activated")
        set(value) { setProperty("activated", value) }

    private var isInProgressing : Boolean
        get() = getPropertyAsNotNull("progressing")
        set(value) { setProperty("progressing", value) }

    private fun initListeners() {
        onClicked = { _, event ->
            val player = event.whoClicked as? Player
            val enhanceItem = event.currentItem
            if (player != null && event.rawSlot > size &&
                enhanceItem != null && enhanceItem notEq XMaterial.AIR
            ) {
                readyEnhanceItem(player, enhanceItem)
            }
        }
        onClosed = { _, event ->
            cancelEnhanceItem(event.player as Player)
        }
    }

    private fun initWalls() {
        setSameButton(intArrayOf(
                  9, 10, 11, 12,       14, 15, 16, 17,
                18,                                              26,
                27,                                              35,
                36,                                              44,
                45, 46, 47, 48, 49, 50, 51, 52, 53
        ), XMaterial.BLACK_STAINED_GLASS_PANE.builder {
            setDisplayName(" ")
        }.build())
    }

    private fun initFunctions(player: Player) {
        setButton(Icon.Enhance) { stack(player.name) }
        setButton(Icon.Display) { null }
        setButton(Icon.Cancel) { null }
    }

    private var progressingTask : BukkitTask? = null
    private val progressingButtonXIndexes = intArrayOf(3, 4, 5, 6, 7)
    private val progressingButtonXIndexesSize = progressingButtonXIndexes.size
    private val progressingButtonY = 4

    private fun enterEnhanceProgressing(
            player: Player,
            enhanceItem: ItemStack,
            onCompleted: Runnable
    ) {
        isInProgressing = true
        progressingTask = object : BukkitRunnable() {
            var life = 0
            override fun run() {
                if (life == 0) {
                    progressingButtonXIndexes
                        .forEach { x -> setButton(x, progressingButtonY) }
                }
                if (life == progressingButtonXIndexesSize) {
                    onCompleted()
                    return
                }
                getButton(progressingButtonXIndexes[life], progressingButtonY)
                    ?.stack = randomWool()
                life++
            }
        }.runTaskTimer(Main.getMain(), 0L, 10L)
    }

    private fun randomWool() = XMaterial.values()
        .asSequence()
        .filter { it.name.endsWith("WOOL") }
        .map { it.createStack(1) }
        .toList()
        .random()
        .applyGlow(true)

    private fun leaveEnhanceProgressing(player: Player) {
        isInProgressing = false
        progressingTask.cancelTask()
        progressingButtonXIndexes
            .forEach { x -> removeButton(x, progressingButtonY) }
    }

    private fun filterCanEnhanceItem(player: Player, enhanceItem: ItemStack): Boolean {
        if (isActivated || isInProgressing)
            return false
        return true
    }

    private fun readyEnhanceItem(player: Player, enhanceItem: ItemStack) {
        if (!filterCanEnhanceItem(player, enhanceItem))
            return
        if (!isActivated) {
            isActivated = true
            Sounds.NOTE_PIANO.tryPlay(player.location, 0.5f, 2f)
            getButton(Icon.Display).stack = enhanceItem
            getButton(Icon.Enhance).stack = Icon.Enhance.stack(enhanceItem)
            getButton(Icon.Enhance).onClicked = { event ->
                val isLeftClick = event.source.isLeftClick
                val isRightClick = event.source.isRightClick
                if (isLeftClick) startEnhanceItem(player, enhanceItem, skipProgressing = false)
                else if (isRightClick) startEnhanceItem(player, enhanceItem, skipProgressing = true)
            }
            getButton(Icon.Cancel).stack = Icon.Cancel.stack(player.name)
            getButton(Icon.Cancel).onClicked = { cancelEnhanceItem(player) }
        } else {
            cancelEnhanceItem(player)
        }
    }

    private fun cancelEnhanceItem(player: Player) {
        Sounds.NOTE_SNARE_DRUM.tryPlay(player.location, 0.5f, 0f)
        isActivated = false
        leaveEnhanceProgressing(player)
        getButton(Icon.Display).stack = null
        getButton(Icon.Enhance).stack = Icon.Enhance.stack(player.name)
        getButton(Icon.Enhance).onClicked = null
        getButton(Icon.Cancel).stack = null
        getButton(Icon.Cancel).onClicked = null
    }

    private fun startEnhanceItem(player: Player, enhanceItem: ItemStack, skipProgressing: Boolean) {
        if (isInProgressing)
            return
        if (skipProgressing) {
            completeEnhanceItem(player, enhanceItem)
        } else {
            enterEnhanceProgressing(player, enhanceItem) {
                leaveEnhanceProgressing(player)
                completeEnhanceItem(player, enhanceItem)
            }
        }
    }

    private fun completeEnhanceItem(player: Player, enhanceItem: ItemStack) {
        Enhance.diceToEnhancement(enhanceItem, player, MainMenu.gear)
    }

    private fun <T : Icon> setButton(icon: T, block: ApplicatorFunction<T, ItemStack?>) = setButton(icon.index, block(icon))
    private fun getButton(icon: Icon) = getButton(icon.index).notNull("Icon has not been set button: ${icon.index}")

    companion object {
        private val AIR = ItemStack(Material.AIR)
    }
    private interface Icon {

        val index : Int

        fun stack(playerName: String): ItemStack
        fun stack(enhanceItem: ItemStack): ItemStack

        object Enhance : Icon {
            override val index = GuiFactory.coordinateToIndex(4, 5)
            override fun stack(playerName: String) = XMaterial.ANVIL.builder {
                setDisplayName(SettingsManager.lang.getString("menu.gui.enhance"))
                addLore(
                        SettingsManager.lang.getStringNotNull("menu.lore.ifSuccess"),
                        SettingsManager.lang.getStringNotNull("menu.lore.ifFail"),
                        SettingsManager.lang.getStringNotNull("menu.lore.ifDowngrade"),
                        SettingsManager.lang.getStringNotNull("menu.lore.ifDestroy"),
                        SettingsManager.lang.getStringNotNull("menu.lore.skip")
                )
            }.build()
            override fun stack(enhanceItem: ItemStack) = XMaterial.ANVIL.builder {
                val level = max(ItemManager.getItemEnchantLevel(enhanceItem),
                        ItemManager.getToolEnchantLevel(enhanceItem))
                setDisplayName(SettingsManager.lang.getString("menu.gui.enhance"))
                addLore(SettingsManager.lang.getStringNotNull("menu.lore.ifSuccess"))
                addLoreIf(SettingsManager.lang.getStringNotNull("menu.lore.ifFail")) {
                    DataManager.baseChance[level] != 100.0 }
                addLoreIf(SettingsManager.lang.getStringNotNull("menu.lore.ifDowngrade")) {
                    DataManager.downgradeChanceIfFail[level] > 0 }
                addLoreIf(SettingsManager.lang.getStringNotNull("menu.lore.ifDestroy")) {
                    DataManager.destroyChanceIfFail[level] > 0 }
                addLore(SettingsManager.lang.getStringNotNull("menu.lore.skip"))
            }.build().applyGlow(true)
        }

        object Display : Icon {
            override val index = GuiFactory.coordinateToIndex(8, 4)
            override fun stack(playerName: String) = AIR
            override fun stack(enhanceItem: ItemStack) = AIR
        }

        object Cancel : Icon {
            override val index = GuiFactory.coordinateToIndex(8, 5)
            override fun stack(playerName: String) = XMaterial.BARRIER.builder {
                setDisplayName(SettingsManager.lang.getString("menu.gui.cancel"))
                addLore(SettingsManager.lang.getStringNotNull("menu.lore.cancel"))
            }.build().applyGlow(true)
            override fun stack(enhanceItem: ItemStack) = AIR
        }
    }
}
