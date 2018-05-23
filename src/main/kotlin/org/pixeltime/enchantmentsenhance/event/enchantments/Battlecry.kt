package org.pixeltime.enchantmentsenhance.event.enchantment

import com.sk89q.worldguard.bukkit.WGBukkit
import com.sk89q.worldguard.protection.flags.DefaultFlag
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Battlecry : Listener {
    private val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "battlecry"))

    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            val player = entityDamageByEntityEvent.damager as Player
            if (entityDamageByEntityEvent.isCancelled) {
                return
            }
            if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(entityDamageByEntityEvent.entity.world).getApplicableRegions(entityDamageByEntityEvent.entity.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
                return
            }
            val level = KM.getLevel(translateAlternateColorCodes, player.itemInHand.itemMeta.lore)
            if (level > 0) {
                if ((player.hasPotionEffect(PotionEffectType.POISON)) || player.hasPotionEffect(PotionEffectType.CONFUSION) || player.hasPotionEffect(PotionEffectType.WITHER) || player.hasPotionEffect(PotionEffectType.WEAKNESS)) {
                    if ((Math.random() * 100.0).toInt() < SettingsManager.enchant.getInt("battlecry.$level.chance")) {
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

