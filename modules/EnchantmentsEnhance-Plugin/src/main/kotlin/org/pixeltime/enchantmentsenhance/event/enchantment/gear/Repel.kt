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

class Repel : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf(
            "While getting damage, you have chance to give a high knockback to your enemy",
            "受到伤害时有几率击退你的敌人很远"
        )
    }

    override fun lang(): Array<String> {
        return arrayOf("驱散")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamaged(entityDamageByEntityEvent: EntityDamageByEntityEvent) {

        if (entityDamageByEntityEvent.entity is Player) {
            val damager = entityDamageByEntityEvent.damager
            val player = entityDamageByEntityEvent.entity as Player
            if (entityDamageByEntityEvent.isCancelled) {
                return
            }
            if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.world)
                    .getApplicableRegions(player.location).queryState(null, DefaultFlag.PVP) == StateFlag.State.DENY
            ) {
                return
            }

            try {
                val level = getLevel(player)
                if (level > 0 && (roll(level))) {
                    damager.velocity =
                        player.location.direction.multiply(SettingsManager.enchant.getInt("repel.$level.power"))
                }
            } catch (ex: Exception) {
            }

        }
    }
}
