package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import com.sk89q.worldguard.bukkit.WGBukkit
import com.sk89q.worldguard.protection.flags.DefaultFlag
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Spiked : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Gives a thorns like effect but a bit more than default", "获得比原版更强大的荆棘效果")
    }

    override fun lang(): Array<String> {
        return arrayOf("尖刺")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {

        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            val player = entityDamageByEntityEvent.damager as Player
            val victim = entityDamageByEntityEvent.entity as Player
            if (entityDamageByEntityEvent.isCancelled) {
                return
            }
            if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(
                    entityDamageByEntityEvent.entity.world
                ).getApplicableRegions(entityDamageByEntityEvent.entity.location)
                    .queryState(null, DefaultFlag.PVP) == StateFlag.State.DENY
            ) {
                return
            }
            try {
                val level = getLevel(victim)
                if (level > 0) {
                    player.damage(SettingsManager.enchant.getDouble("spiked.$level.damage"))
                }
            } catch (ex: Exception) {
            }
        }
    }
}
