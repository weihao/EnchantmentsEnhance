
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

package org.pixeltime.enchantmentsenhance.event.enchantment.bow

import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.entity.TNTPrimed
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.plugin.Plugin
import org.pixeltime.enchantmentsenhance.util.Util

class Boom(
        private val plugin: Plugin
) : Listener {

    private val key = "ee-bow-boom"
    private val keyTNT = "ee-bow-boom-tnt"

    private fun canWork(bow: ItemStack) : Boolean {
        return bow.hasItemMeta() && "Boom Bow".equals(bow.itemMeta.displayName, true)
    }

    @EventHandler()
    fun onLanunch(event: ProjectileLaunchEvent) {
        val projectile = event.entity
        if (projectile is Arrow && projectile.shooter is Player) {
            val bow = Util.getMainHand(projectile.shooter as Player)
            if (canWork(bow))
                projectile.setMetadata(key, FixedMetadataValue(plugin, 1))
        }
    }

    @EventHandler
    fun onHit(event: ProjectileHitEvent) {
        val projectile = event.entity
        if (projectile is Arrow && projectile.getMetadata(key).firstOrNull()?.value() == 1) {
            val location = event.hitEntity?.location ?: event.hitBlock?.location
            if (location != null) {
                val tnt = location.world.spawn(location.clone().add(0.0, 1.0, 0.0), TNTPrimed::class.java)
                tnt.setMetadata(keyTNT, FixedMetadataValue(plugin, 1))
                tnt.fuseTicks = 1
            }
            projectile.removeMetadata(key, plugin)
        }
    }

    @EventHandler
    fun onExpload(event: EntityExplodeEvent) {
        val entity = event.entity
        if (entity is TNTPrimed && entity.getMetadata(keyTNT).firstOrNull()?.value() == 1) {
            event.blockList().clear()
            entity.removeMetadata(keyTNT, plugin)
        }
    }
}
