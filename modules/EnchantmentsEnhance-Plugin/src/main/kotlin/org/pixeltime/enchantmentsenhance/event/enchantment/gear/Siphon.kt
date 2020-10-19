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

class Siphon : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("You have chance to get large amount of life steal from PvE (Mobs).", "你在打生物时会吸取它们的很多血量")
    }

    override fun lang(): Array<String> {
        return arrayOf("虹吸")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.damager is Player) {
            try {
                val player = entityDamageByEntityEvent.damager as Player
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
                if (entityDamageByEntityEvent.entity is Player) {
                    return
                }
                val level = getLevel(player)
                if ((level > 0) && (roll(level))) {
                    if (player.health + SettingsManager.enchant.getInt("siphon.$level.health") > 20.0) {
                        player.health = 20.0
                    } else {
                        player.health = player.health + SettingsManager.enchant.getInt("siphon.$level.health")
                    }
                }
            } catch (ex: Exception) {
            }

        }
    }
}
