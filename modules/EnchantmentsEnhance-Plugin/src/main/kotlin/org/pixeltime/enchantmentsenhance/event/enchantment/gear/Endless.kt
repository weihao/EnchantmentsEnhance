package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerItemDamageEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.InventoryManager
import org.pixeltime.enchantmentsenhance.manager.KotlinManager


class Endless : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Item with this enchantment will never be broken", "这件物品不可被摧毁")
    }

    override fun lang(): Array<String> {
        return arrayOf("无尽")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun noWeaponBreakDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.damager is Player) {
            val player = entityDamageByEntityEvent.damager as Player
            InventoryManager.getItemList(player)
                    .filter { KotlinManager.getLevel(this.name(), it.itemMeta!!.lore!!) > 0 }
                    .forEach {
                        it.durability = 0
                    }
        }
        if (entityDamageByEntityEvent.entity is Player) {
            val player = entityDamageByEntityEvent.entity as Player
            InventoryManager.getItemList(player)
                    .filter { KotlinManager.getLevel(this.name(), it.itemMeta!!.lore!!) > 0 }
                    .forEach {
                        it.durability = 0
                    }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun noWeaponBreakDamage(entityShootBowEvent: EntityShootBowEvent) {
        if (entityShootBowEvent.entity is Player) {
            val player = entityShootBowEvent.entity as Player
            InventoryManager.getItemList(player)
                    .filter { KotlinManager.getLevel(this.name(), it.itemMeta!!.lore!!) > 0 }
                    .forEach {
                        it.durability = 0
                    }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun noWeaponBreakDamage(playerInteractEvent: PlayerInteractEvent) {
        val player = playerInteractEvent.player as Player
        InventoryManager.getItemList(player)
                .filter { KotlinManager.getLevel(this.name(), it.itemMeta!!.lore!!) > 0 }
                .forEach {
                    it.durability = 0
                }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onPlayerItemDamage(e: PlayerItemDamageEvent) {
        val item = e.item
        if (item.hasItemMeta() && item.itemMeta!!.hasLore()) {
            if (KotlinManager.getLevel(this.name(), item.itemMeta!!.lore!!) > 0) {
                e.isCancelled = true
            }
        }
    }
}
