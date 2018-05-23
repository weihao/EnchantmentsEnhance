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

class Zeus : Listener {
    private val special: ArrayList<Projectile>
    private val special2: ArrayList<Projectile>
    private val special3: ArrayList<Projectile>

    init {
        this.special = ArrayList()
        this.special2 = ArrayList()
        this.special3 = ArrayList()
    }

    @EventHandler
    fun onDamage(projectileLaunchEvent: ProjectileLaunchEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "zeus"))
        if (projectileLaunchEvent.entity is Arrow) {
            val arrow = projectileLaunchEvent.entity as Arrow
            if (arrow.shooter is Player) {
                val player = arrow.shooter as Player
                try {
                    if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                        this.special.add(arrow)
                    }
                    if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II")) {
                        this.special2.add(arrow)
                    }
                    if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III")) {
                        this.special3.add(arrow)
                    }
                } catch (ex: Exception) {
                }

            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        val n = (Math.random() * 100.0).toInt()
        if (entityDamageByEntityEvent.damager is Projectile) {
            val projectile = entityDamageByEntityEvent.damager as Projectile
            if (entityDamageByEntityEvent.isCancelled) {
                return
            }
            if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(entityDamageByEntityEvent.entity.world).getApplicableRegions(entityDamageByEntityEvent.entity.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
                return
            }
            if (this.special.contains(projectile) && n < SettingsManager.enchant.getInt("zeus.level_I.chance")) {
                entityDamageByEntityEvent.entity.world.strikeLightningEffect(entityDamageByEntityEvent.entity.location)
            }
            if (this.special2.contains(projectile) && n < SettingsManager.enchant.getInt("zeus.level_II.chance")) {
                entityDamageByEntityEvent.entity.world.strikeLightningEffect(entityDamageByEntityEvent.entity.location)
            }
            if (this.special2.contains(projectile) && n < SettingsManager.enchant.getInt("zeus.level_III.chance")) {
                entityDamageByEntityEvent.entity.world.strikeLightningEffect(entityDamageByEntityEvent.entity.location)
            }
        }
    }
}
