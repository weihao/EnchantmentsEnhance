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

class Paralyze : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf(
            "You have a chance to give your opponent Mining Fatigue effect for few seconds",
            "你有几率给攻击你的人造成挖掘疲劳效果"
        )
    }

    override fun lang(): Array<String> {
        return arrayOf("麻痹")
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
                val level = getLevel(player)
                if (level > 0 && (roll(level))) { // 建议更换为 LDK 的药水效果
                    victim.addPotionEffect(
                        PotionEffect(
                            PotionEffectType.getById(4)!!,
                            SettingsManager.enchant.getInt("paralyze.$level.duration") * 20,
                            SettingsManager.enchant.getInt("paralyze.$level.potion_lvl") - 1
                        )
                    )
                }
            } catch (ex: Exception) {
            }
        }
    }
}

