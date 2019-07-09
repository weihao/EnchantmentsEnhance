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

class Curse : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Upon hitting a target they will receive the wither damage debuff", "攻击别人会造成他凋零")
    }

    override fun lang(): Array<String> {
        return arrayOf("诅咒")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.entity is Player && entityDamageByEntityEvent.damager is Player) {
            try {
                val player = entityDamageByEntityEvent.entity as Player
                if (entityDamageByEntityEvent.isCancelled) {
                    return
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.world).getApplicableRegions(player.location).queryState(null, DefaultFlag.PVP) == StateFlag.State.DENY) {
                    return
                }
                val level = getLevel(player)
                if ((level > 0) && (roll(level))) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.WITHER, SettingsManager.enchant.getInt("curse.$level.duration") * 20, SettingsManager.enchant.getInt("curse.$level.potion_lvl") - 1))
                }
            } catch (ex: Exception) {
            }

        }
    }
}
