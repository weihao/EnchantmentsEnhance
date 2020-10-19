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

class Crushing : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("A chance to deal up to 3x damage", "有机率输出三倍伤害")
    }

    override fun lang(): Array<String> {
        return arrayOf("粉碎")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            try {
                val player = entityDamageByEntityEvent.damager as Player
                val victim = entityDamageByEntityEvent.entity as Player
                if (entityDamageByEntityEvent.isCancelled) {
                    return
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(victim.world)
                        .getApplicableRegions(victim.location).queryState(null, DefaultFlag.PVP) == StateFlag.State.DENY
                ) {
                    return
                }
                val level = getLevel(player)
                if ((level > 0) && (roll(level))) {
                    entityDamageByEntityEvent.damage = entityDamageByEntityEvent.damage * 3.0
                }
            } catch (ex: Exception) {
            }
        }
    }
}
