package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import com.sk89q.worldguard.bukkit.WGBukkit
import com.sk89q.worldguard.protection.flags.DefaultFlag
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Corruption : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Has a chance to give weakness effect to enemy", "攻击别人有几率造成他虚弱")
    }

    override fun lang(): Array<String> {
        return arrayOf("腐败")
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
                if ((level > 0) && ((roll(level)))) {
                    victim.addPotionEffect(
                        PotionEffect(
                            PotionEffectType.WEAKNESS,
                            SettingsManager.enchant.getInt("corruption.$level.duration") * 20,
                            SettingsManager.enchant.getInt("corruption.$level.potion_lvl") - 1
                        )
                    )
                }
            } catch (ex: Exception) {
            }
        }
    }
}
