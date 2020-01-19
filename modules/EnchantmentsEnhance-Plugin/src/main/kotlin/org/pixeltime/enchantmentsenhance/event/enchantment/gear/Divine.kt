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

class Divine : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("A chance to heal you relative to the damage you inflict upon your enemy without taking their health", "有几率恢复收到的伤害")
    }

    override fun lang(): Array<String> {
        return arrayOf("神圣")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            try {
                val player = entityDamageByEntityEvent.damager as Player
                if (entityDamageByEntityEvent.isCancelled) {
                    return
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(entityDamageByEntityEvent.entity.world).getApplicableRegions(entityDamageByEntityEvent.entity.location).queryState(null, DefaultFlag.PVP) == StateFlag.State.DENY) {
                    return
                }
                val level = getLevel(player)
                if ((level > 0) && (roll(level))) {
                    if (player.health + entityDamageByEntityEvent.damage > player.maxHealth) {
                        player.health = player.maxHealth
                    } else {
                        player.health = player.health + entityDamageByEntityEvent.damage
                    }
                }
            } catch (ex: Exception) {
            }

        }
    }
}
