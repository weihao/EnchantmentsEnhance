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

package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityShootBowEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerItemDamageEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.KM


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
            IM.getItemList(player)
                    .filter { KM.getLevel(this.name(), it.itemMeta.lore) > 0 }
                    .forEach {
                        it.durability = 0
                    }
        }
        if (entityDamageByEntityEvent.entity is Player) {
            val player = entityDamageByEntityEvent.entity as Player
            IM.getItemList(player)
                    .filter { KM.getLevel(this.name(), it.itemMeta.lore) > 0 }
                    .forEach {
                        it.durability = 0
                    }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun noWeaponBreakDamage(entityShootBowEvent: EntityShootBowEvent) {
        if (entityShootBowEvent.entity is Player) {
            val player = entityShootBowEvent.entity as Player
            IM.getItemList(player)
                    .filter { KM.getLevel(this.name(), it.itemMeta.lore) > 0 }
                    .forEach {
                        it.durability = 0
                    }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun noWeaponBreakDamage(playerInteractEvent: PlayerInteractEvent) {
        val player = playerInteractEvent.player as Player
        IM.getItemList(player)
                .filter { KM.getLevel(this.name(), it.itemMeta.lore) > 0 }
                .forEach {
                    it.durability = 0
                }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onPlayerItemDamage(e: PlayerItemDamageEvent) {
        val item = e.item
        if (item.hasItemMeta() && item.itemMeta.hasLore()) {
            if (KM.getLevel(this.name(), item.itemMeta.lore) > 0) {
                e.isCancelled = true
            }
        }
    }
}
