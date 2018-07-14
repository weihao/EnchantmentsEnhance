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

import com.sk89q.worldguard.bukkit.WGBukkit
import com.sk89q.worldguard.protection.flags.DefaultFlag
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Battlecry : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Chance to remove negative debuffs from yourself while you fight", "当你战斗时有几率移除你的负面状态")
    }

    override fun lang(): Array<String> {
        return arrayOf("战吼")
    }


    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            val player = entityDamageByEntityEvent.damager as Player
            if (entityDamageByEntityEvent.isCancelled) {
                return
            }
            if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(entityDamageByEntityEvent.entity.world).getApplicableRegions(entityDamageByEntityEvent.entity.location).queryState(null, DefaultFlag.PVP) == StateFlag.State.DENY) {
                return
            }
            val level = IM.getHighestLevel(player, this.name())
            if (level > 0) {
                if ((player.hasPotionEffect(PotionEffectType.POISON)) || player.hasPotionEffect(PotionEffectType.CONFUSION) || player.hasPotionEffect(PotionEffectType.WITHER) || player.hasPotionEffect(PotionEffectType.WEAKNESS)) {
                    if ((roll(level))) {
                        try {
                            player.removePotionEffect(PotionEffectType.BLINDNESS)
                            player.removePotionEffect(PotionEffectType.POISON)
                            player.removePotionEffect(PotionEffectType.CONFUSION)
                            player.removePotionEffect(PotionEffectType.WEAKNESS)
                            player.removePotionEffect(PotionEffectType.SLOW)
                        } catch (ex: Exception) {
                        }
                    }
                }
            }
        }
    }
}

