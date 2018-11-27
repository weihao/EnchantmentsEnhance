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

package org.pixeltime.enchantmentsenhance.reloaded.enhance.bow

import com.lgou2w.ldk.bukkit.entity.itemInMainHand
import com.lgou2w.ldk.bukkit.event.registerListeners
import org.bukkit.entity.Arrow
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.FixedMetadataValue
import org.pixeltime.enchantmentsenhance.reloaded.EnchantmentsEnhance
import org.pixeltime.enchantmentsenhance.reloaded.enhance.EnhancementHelper

class EnderBow internal constructor() : EnhancementBow() {

    override fun isConflict(stack: ItemStack?): Boolean {
        return EnhancementHelper.has(stack, BoomBow::class.java)
    }

    override fun registered() {
        super.registered()
        EnchantmentsEnhance.main.registerListeners {
            event<ProjectileLaunchEvent> {
                if (entity is Arrow && entity.shooter is Player) {
                    val stackBow = (entity.shooter as Player).itemInMainHand
                    val level = getLevel(stackBow)
                    if (level > 0)
                        entity.setMetadata(PROJECTILE_KEY, FixedMetadataValue(plugin, true))
                }
            }
            event<ProjectileHitEvent> {
                if (entity is Arrow && entity.shooter is Player && entity.getMetadata(PROJECTILE_KEY).firstOrNull()?.asBoolean() == true) {
                    val location = hitEntity?.location ?: hitBlock?.location
                    if (location != null) {
                        val source = entity.shooter as Entity
                        val finalLocation = location.clone().add(.0, 1.0, .0)
                        source.teleport(finalLocation, PlayerTeleportEvent.TeleportCause.PLUGIN)
                    }
                    entity.removeMetadata(PROJECTILE_KEY, plugin)
                }
            }
        }
    }

    companion object {
        const val PROJECTILE_KEY = "EE-EnderBow-Projectile"
    }
}
