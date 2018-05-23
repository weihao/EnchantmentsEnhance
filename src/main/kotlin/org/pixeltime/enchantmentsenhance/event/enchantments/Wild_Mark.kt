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

class Wild_Mark : Listener {
    private val special: ArrayList<Projectile>

    init {
        this.special = ArrayList()
    }

    @EventHandler
    fun onDamage(projectileLaunchEvent: ProjectileLaunchEvent) {
        val translateAlternateColorCodes: String
        translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "wild_mark"))
        if (projectileLaunchEvent.entity is Arrow) {
            val arrow = projectileLaunchEvent.entity as Arrow
            if (arrow.shooter is Player) {
                val player = arrow.shooter as Player
                try {
                    if (player.itemInHand.itemMeta.lore.contains("$translateAlternateColorCodes I")) {
                        this.special.add(arrow)
                    }
                } catch (ex: Exception) {
                }

            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.isCancelled) {
            return
        }
        if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(entityDamageByEntityEvent.entity.world).getApplicableRegions(entityDamageByEntityEvent.entity.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
            return
        }
        if (entityDamageByEntityEvent.damager is Projectile && this.special.contains(entityDamageByEntityEvent.damager)) {
            entityDamageByEntityEvent.damage = entityDamageByEntityEvent.damage * 2.0
        }
    }
}
