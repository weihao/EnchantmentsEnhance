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

package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.entity.Player
import org.bukkit.entity.Wolf
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener

class Tamer : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("By hitting the wolves, they will be tamed", "攻击狼它们会被你驯服")
    }

    override fun lang(): Array<String> {
        return arrayOf("驯兽")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {

        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity !is Player) {
            val player = entityDamageByEntityEvent.damager as Player
            try {
                val level = getLevel(player)
                if (level > 0 && entityDamageByEntityEvent.entity is Wolf) {
                    entityDamageByEntityEvent.isCancelled = true
                    entityDamageByEntityEvent.damage = 0.0
                    val wolf = entityDamageByEntityEvent.entity as Wolf
                    if (wolf.isTamed) {
                        return
                    }
                    wolf.isTamed = true
                    wolf.owner = player
                }
            } catch (ex: Exception) {
            }

        }
    }
}