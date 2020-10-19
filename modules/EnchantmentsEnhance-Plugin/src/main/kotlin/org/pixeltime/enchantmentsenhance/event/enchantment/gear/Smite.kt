package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import com.sk89q.worldguard.bukkit.WGBukkit
import com.sk89q.worldguard.protection.flags.DefaultFlag
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffect
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Smite : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf(
            "A chance to call down a bolt of lightning, removing any positive buffs the enemy has",
            "攻击别人时有几率召唤闪电并移除他的增益效果"
        )
    }

    override fun lang(): Array<String> {
        return arrayOf("浩劫")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.entity is Player && entityDamageByEntityEvent.damager is Player) {

            val player = entityDamageByEntityEvent.entity as Player
            val victim = entityDamageByEntityEvent.damager as Player
            if (entityDamageByEntityEvent.isCancelled) {
                return
            }
            if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.world)
                    .getApplicableRegions(player.location).queryState(null, DefaultFlag.PVP) == StateFlag.State.DENY
            ) {
                return
            }

            try {
                val level = getLevel(victim)
                if (level > 0 && (roll(level))) {
                    player.world.strikeLightningEffect(player.location)
                    val iterator = player.activePotionEffects.iterator()
                    while (iterator.hasNext()) {
                        player.removePotionEffect((iterator.next() as PotionEffect).type)
                    }
                }
            } catch (ex: Exception) {
            }

        }
    }
}
