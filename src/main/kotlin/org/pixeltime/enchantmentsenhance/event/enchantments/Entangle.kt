package org.pixeltime.enchantmentsenhance.event.enchantment

import com.sk89q.worldguard.bukkit.WGBukkit
import com.sk89q.worldguard.protection.flags.DefaultFlag
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.ChatColor
import org.bukkit.entity.Arrow
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.entity.Projectile
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

import java.util.ArrayList

class Entangle : Listener {
    private val special: ArrayList<Projectile>
    private val special2: ArrayList<Projectile>
    private val special3: ArrayList<Projectile>
    private val special4: ArrayList<Projectile>

    init {
        this.special = ArrayList()
        this.special2 = ArrayList()
        this.special3 = ArrayList()
        this.special4 = ArrayList()
    }

    @EventHandler
    fun onProject(projectileLaunchEvent: ProjectileLaunchEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "entangle"))
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
                    if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " IV")) {
                        this.special4.add(arrow)
                    }
                } catch (ex: Exception) {
                }

            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.entity is LivingEntity) {
            val livingEntity = entityDamageByEntityEvent.entity as LivingEntity
            if (entityDamageByEntityEvent.isCancelled) {
                return
            }
            if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(livingEntity.world).getApplicableRegions(livingEntity.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
                return
            }
            if (entityDamageByEntityEvent.damager is Projectile) {
                val projectile = entityDamageByEntityEvent.damager as Projectile
                if (projectile.shooter is Player) {
                    val player = projectile.shooter as Player
                    val n = (Math.random() * 100.0).toInt()
                    if (this.special.contains(projectile) && n < SettingsManager.enchant.getInt("entangle.level_I.chance")) {
                        livingEntity.addPotionEffect(PotionEffect(PotionEffectType.SLOW, SettingsManager.enchant.getInt("entangle.level_I.duration") * 20, SettingsManager.enchant.getInt("entangle.level_I.potion_lvl") - 1))
                    }
                    if (this.special2.contains(projectile) && n < SettingsManager.enchant.getInt("entangle.level_II.chance")) {
                        livingEntity.addPotionEffect(PotionEffect(PotionEffectType.SLOW, SettingsManager.enchant.getInt("entangle.level_II.duration") * 20, SettingsManager.enchant.getInt("entangle.level_II.potion_lvl") - 1))
                    }
                    if (this.special3.contains(projectile) && n < SettingsManager.enchant.getInt("entangle.level_III.chance")) {
                        livingEntity.addPotionEffect(PotionEffect(PotionEffectType.SLOW, SettingsManager.enchant.getInt("entangle.level_III.duration") * 20, SettingsManager.enchant.getInt("entangle.level_III.potion_lvl") - 1))
                    }
                    if (this.special4.contains(projectile) && n < SettingsManager.enchant.getInt("entangle.level_IV.chance")) {
                        livingEntity.addPotionEffect(PotionEffect(PotionEffectType.SLOW, SettingsManager.enchant.getInt("entangle.level_IV.duration") * 20, SettingsManager.enchant.getInt("entangle.level_IV.potion_lvl") - 1))
                    }
                }
            }
        }
    }
}
