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

import com.lgou2w.ldk.bukkit.compatibility.Sounds
import com.lgou2w.ldk.bukkit.compatibility.XMaterial
import com.lgou2w.ldk.bukkit.compatibility.notEq
import com.lgou2w.ldk.bukkit.gui.GuiBase
import com.lgou2w.ldk.bukkit.gui.GuiFactory
import com.lgou2w.ldk.bukkit.gui.GuiType
import com.lgou2w.ldk.bukkit.item.builder
import com.lgou2w.ldk.chat.toColor
import com.lgou2w.ldk.common.ApplicatorFunction
import com.lgou2w.ldk.common.notNull
import org.bukkit.Material
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
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
    }

    private var isActivated : Boolean
        get() = getPropertyAsNotNull("activated")
        set(value) { setProperty("activated", value) }

    private fun initListeners() {
        onClicked = { _, event ->
            val player = event.whoClicked as? Player
            val enhanceItem = event.currentItem
            if (player != null && event.rawSlot > size &&
                enhanceItem != null && enhanceItem notEq XMaterial.AIR &&
                !isActivated
            ) {
                readyEnhanceItem(player, enhanceItem)
            }
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
        // Enhance icon
        setButton(Icon.Enhance) { stack(player.name) }
        setButton(Icon.Display) { null }
        setButton(Icon.Cancel) { null }
    }

    private fun readyEnhanceItem(player: Player, enhanceItem: ItemStack) {
        if (!isActivated) {
            isActivated = true
            Sounds.NOTE_PIANO.tryPlay(player.location, 0.5f, 2f)
            getButton(Icon.Display).stack = enhanceItem.clone()
            getButton(Icon.Enhance).stack = Icon.Enhance.stack(enhanceItem)
            getButton(Icon.Enhance).onClicked = { startEnhanceItem(player, enhanceItem) }
            getButton(Icon.Cancel).stack = Icon.Cancel.stack(player.name)
            getButton(Icon.Cancel).onClicked = { cancelEnhanceItem(player) }
        } else {
            cancelEnhanceItem(player)
        }
    }

    private fun cancelEnhanceItem(player: Player) {
        isActivated = false
        Sounds.NOTE_SNARE_DRUM.tryPlay(player.location, 0.5f, 0f)
        getButton(Icon.Display).stack = null
        getButton(Icon.Enhance).stack = Icon.Enhance.stack(player.name)
        getButton(Icon.Enhance).onClicked = null
        getButton(Icon.Cancel).stack = null
        getButton(Icon.Cancel).onClicked = null
    }

    private fun startEnhanceItem(player: Player, enhanceItem: ItemStack) {
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
