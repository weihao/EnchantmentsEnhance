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

class Execute : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("More critical hits and a higher chance to do 2x damage whilst sneaking", "在潜行攻击人时造成两倍的伤害")
    }

    override fun lang(): Array<String> {
        return arrayOf("背刺")
    }


    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
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
            try {
                val level = getLevel(player)
                if (level > 0 && (roll(level)) && player.isSneaking) {
                    entityDamageByEntityEvent.damage =
                        entityDamageByEntityEvent.damage * SettingsManager.enchant.getDouble("execute.$level.multiplier")
                }
            } catch (ex: Exception) {
            }
        }
    }
}
