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

package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Reborn : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("When you kill a player, you will get Absorption and Regeneration potion effect for limited time", "当你杀人时，你会在短时间内获得伤害吸收和生命恢复")
    }

    override fun lang(): Array<String> {
        return arrayOf("重生")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDeath(playerDeathEvent: PlayerDeathEvent) {

        val entity = playerDeathEvent.entity
        if (entity.killer is Player) {
            val player = entity.killer
            try {
                val level = getLevel(player)
                if (level > 0) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.ABSORPTION, SettingsManager.enchant.getInt("reborn.$level.absorption.duration") * 20, SettingsManager.enchant.getInt("reborn.$level.absorption.potion_lvl") - 1))
                    player.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, SettingsManager.enchant.getInt("reborn.$level.regeneration.duration") * 20, SettingsManager.enchant.getInt("reborn.$level.regeneration.potion_lvl") - 1))
                }
            } catch (ex: Exception) {
            }

        }
    }
}
