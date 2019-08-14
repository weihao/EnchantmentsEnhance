package org.pixeltime.enchantmentsenhance.gui

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.util.Util

import java.util.HashMap

abstract class GUIAbstract(player: Player, invSize: Int, invName: String) {
    var playerName: String
    val inventory: Inventory = Bukkit.createInventory(null, invSize, Util.toColor(invName)!!)
    private val actions: MutableMap<Int, GUIAction>

    init {
        this.actions = HashMap()
        this.playerName = player.name
        GUIManager.map[player.name] = this
        GUIManager.set.add(this)
    }

    @JvmOverloads
    fun setItem(slot: Int, stack: ItemStack, action: GUIAction? = null) {
        inventory.setItem(slot, stack)
        if (action != null) {
            actions[slot] = action
        }
    }

    fun open() {
        Bukkit.getPlayer(playerName)!!.openInventory(inventory)
    }

    fun getActions(): Map<Int, GUIAction> {
        return actions
    }

    abstract fun update()

    interface GUIAction {
        fun click(clicktype: ClickType)
    }
}
