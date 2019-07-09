package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import com.sk89q.worldguard.bukkit.WGBukkit
import com.sk89q.worldguard.protection.flags.DefaultFlag
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Battlecry : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Chance to remove negative debuffs from yourself while you fight", "当你战斗时有几率移除你的负面状态")
    }

    override fun lang(): Array<String> {
        return arrayOf("战吼")
    }


    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            val player = entityDamageByEntityEvent.damager as Player
            if (entityDamageByEntityEvent.isCancelled) {
                return
            }
            if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(entityDamageByEntityEvent.entity.world).getApplicableRegions(entityDamageByEntityEvent.entity.location).queryState(null, DefaultFlag.PVP) == StateFlag.State.DENY) {
                return
            }
            val level = getLevel(player)
            if (level > 0) {
                if ((player.hasPotionEffect(PotionEffectType.POISON)) || player.hasPotionEffect(PotionEffectType.CONFUSION) || player.hasPotionEffect(PotionEffectType.WITHER) || player.hasPotionEffect(PotionEffectType.WEAKNESS)) {
                    if ((roll(level))) {
                        try {
                            player.removePotionEffect(PotionEffectType.BLINDNESS)
                            player.removePotionEffect(PotionEffectType.POISON)
                            player.removePotionEffect(PotionEffectType.CONFUSION)
                            player.removePotionEffect(PotionEffectType.WEAKNESS)
                            player.removePotionEffect(PotionEffectType.SLOW)
                        } catch (ex: Exception) {
                        }
                    }
                }
            }
        }
    }
}

