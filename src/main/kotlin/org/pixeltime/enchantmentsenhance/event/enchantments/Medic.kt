package org.pixeltime.enchantmentsenhance.event.enchantment

import com.sk89q.worldguard.bukkit.WGBukkit
import com.sk89q.worldguard.protection.flags.DefaultFlag
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.ChatColor
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.entity.Projectile
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

import java.util.ArrayList

class Medic : Listener {
    private val special: ArrayList<Projectile>

    init {
        this.special = ArrayList()
    }

    @EventHandler
    fun onDamage(projectileLaunchEvent: ProjectileLaunchEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "medic"))
        if (projectileLaunchEvent.entity is Arrow) {
            val arrow = projectileLaunchEvent.entity as Arrow
            if (arrow.shooter is Player) {
                val player = arrow.shooter as Player
                try {
                    if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                        this.special.add(arrow)
                    }
                } catch (ex: Exception) {
                }

            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.damager is Projectile) {
            val projectile = entityDamageByEntityEvent.damager as Projectile
            if (this.special.contains(projectile) && entityDamageByEntityEvent.entity is Player) {
                val player = entityDamageByEntityEvent.entity as Player
                if (projectile.shooter is Player) {
                    val player2 = projectile.shooter as Player
                    if (entityDamageByEntityEvent.isCancelled) {
                        return
                    }
                    if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.world).getApplicableRegions(player.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
                        return
                    }
                    if (player.name.equals(player2.name, ignoreCase = true)) {
                        return
                    }
                    player.health = 20.0
                }
            }
        }
    }
}
