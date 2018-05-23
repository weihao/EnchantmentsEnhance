package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.entity.TNTPrimed
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.entity.EntityShootBowEvent
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

import java.util.ArrayList

class Tnt_Shooter : Listener {
    private val entity: ArrayList<Entity>

    init {
        this.entity = ArrayList()
    }

    @EventHandler
    fun onShoot(entityShootBowEvent: EntityShootBowEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "tnt_shooter"))
        if (entityShootBowEvent.entity is Player) {
            val player = entityShootBowEvent.entity as Player
            try {
                if (entityShootBowEvent.bow.itemMeta.hasLore() && entityShootBowEvent.bow.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                    val projectile = entityShootBowEvent.projectile.world.spawn<Entity>(entityShootBowEvent.projectile.location, TNTPrimed::class.java as Class<*>) as TNTPrimed
                    projectile.velocity = player.eyeLocation.direction.multiply(entityShootBowEvent.force)
                    entityShootBowEvent.projectile = projectile
                    if (!SettingsManager.enchant.getBoolean("tnt_shooter.level_I.explosion")) {
                        this.entity.add(projectile)
                    }
                }
            } catch (ex: Exception) {
            }

        }
    }

    @EventHandler
    fun onExplode(entityExplodeEvent: EntityExplodeEvent) {
        if (entityExplodeEvent.entity is TNTPrimed) {
            val tntPrimed = entityExplodeEvent.entity as TNTPrimed
            if (this.entity.contains(tntPrimed)) {
                entityExplodeEvent.blockList().clear()
                this.entity.remove(tntPrimed)
            }
        }
    }
}
