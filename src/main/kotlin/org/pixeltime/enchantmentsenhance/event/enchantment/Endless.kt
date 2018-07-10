/*
 *     Copyright (C) 2017-Present HealPotion
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
import org.bukkit.event.player.PlayerItemBreakEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.KM

class Endless : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Item with this enchantment will never be broken", "这件物品不可被摧毁")
    }

    override fun lang(): Array<String> {
        return arrayOf("无尽")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun noWeaponBreakDamage(playerInteractEvent: PlayerInteractEvent) {
        try {
            if (playerInteractEvent.item.itemMeta.hasLore() && KM.getLevel(this.name(), playerInteractEvent.item.itemMeta.lore) > 0) {
                playerInteractEvent.item.durability = 0.toShort()
            }
        } catch (ex: Exception) {
        }

    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun noWeaponBreakDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        try {
            if (entityDamageByEntityEvent.damager is Player) {
                val player = entityDamageByEntityEvent.damager as Player
                if (player.itemInHand.itemMeta.hasLore() && KM.getLevel(this.name(), player.itemInHand.itemMeta.lore) > 0) {
                    (entityDamageByEntityEvent.damager as Player).itemInHand.durability = 0.toShort()
                }
            }
            if (entityDamageByEntityEvent.entity is Player) {
                val player2 = entityDamageByEntityEvent.entity as Player
                if (player2.inventory.helmet != null) {
                    val helmet = player2.inventory.helmet
                    if (helmet.itemMeta.hasLore() && KM.getLevel(this.name(), helmet.itemMeta.lore) > 0) {
                        helmet.durability = 0.toShort()
                        player2.inventory.helmet = helmet
                    }
                }
                if (player2.inventory.chestplate != null) {
                    val chestplate = player2.inventory.chestplate
                    if (chestplate.itemMeta.hasLore() && KM.getLevel(this.name(), chestplate.itemMeta.lore) > 0) {
                        chestplate.durability = 0.toShort()
                        player2.inventory.chestplate = chestplate
                    }
                }
                if (player2.inventory.leggings != null) {
                    val leggings = player2.inventory.leggings
                    if (leggings.itemMeta.hasLore() && KM.getLevel(this.name(), leggings.itemMeta.lore) > 0) {
                        leggings.durability = 0.toShort()
                        player2.inventory.leggings = leggings
                    }
                }
                if (player2.inventory.boots != null) {
                    val boots = player2.inventory.boots
                    if (boots.itemMeta.hasLore() && KM.getLevel(this.name(), boots.itemMeta.lore) > 0) {
                        boots.durability = 0.toShort()
                        player2.inventory.boots = boots
                    }
                }
            }
        } catch (ex: Exception) {
        }

    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun noWeaponBreakDamage(entityShootBowEvent: EntityShootBowEvent) {
        try {
            if (entityShootBowEvent.entity is Player && entityShootBowEvent.bow.itemMeta.hasLore() && KM.getLevel(this.name(), entityShootBowEvent.bow.itemMeta.lore) > 0) {
                entityShootBowEvent.bow.durability = 0.toShort()
            }
        } catch (ex: Exception) {
        }

    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun noWeaponBreakDamage(playerItemBreakEvent: PlayerItemBreakEvent) {
        try {
            val clone = playerItemBreakEvent.brokenItem.clone()
            if (clone.itemMeta.hasLore() && KM.getLevel(this.name(), clone.itemMeta.lore) > 0) {
                clone.durability = 0.toShort()
            }
            playerItemBreakEvent.player.inventory.addItem(clone)
        } catch (ex: Exception) {
        }

    }
}
