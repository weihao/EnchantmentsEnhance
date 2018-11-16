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
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Petrify : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Has a chance to give slowness effect to your enemy", "攻击别人有几率造成他缓慢")
    }

    override fun lang(): Array<String> {
        return arrayOf("石化")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onHit(entityDamageByEntityEvent: EntityDamageByEntityEvent) {

        if (entityDamageByEntityEvent.entity is Player && entityDamageByEntityEvent.damager is Player) {
            val player = entityDamageByEntityEvent.entity as Player
            val player2 = entityDamageByEntityEvent.damager as Player
            if (entityDamageByEntityEvent.isCancelled) {
                return
            }
            try {
                val level = getLevel(player2)
                if (level > 0 && (roll(level))) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.SLOW, SettingsManager.enchant.getInt("petrify.$level.duration") * 20, SettingsManager.enchant.getInt("petrify.$level.potion_lvl") - 1))

                }
            } catch (ex: Exception) {
            }

        }
    }
}
