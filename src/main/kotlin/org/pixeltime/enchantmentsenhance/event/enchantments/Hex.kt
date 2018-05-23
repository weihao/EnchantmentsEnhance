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
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Hex : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "hex"))
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            try {
                val player = entityDamageByEntityEvent.damager as Player
                val player2 = entityDamageByEntityEvent.entity as Player
                if (entityDamageByEntityEvent.isCancelled) {
                    return
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player2.world).getApplicableRegions(player2.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
                    return
                }
                val n = (Math.random() * 100.0).toInt()
                if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && n < SettingsManager.enchant.getInt("hex.level_I.chance")) {
                    player2.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, SettingsManager.enchant.getInt("hex.level_I.duration") * 20, 0))
                }
                if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && n < SettingsManager.enchant.getInt("hex.level_II.chance")) {
                    player2.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, SettingsManager.enchant.getInt("hex.level_II.duration") * 20, 0))
                }
                if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III") && n < SettingsManager.enchant.getInt("hex.level_III.chance")) {
                    player2.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, SettingsManager.enchant.getInt("hex.level_III.duration") * 20, 0))
                }
            } catch (ex: Exception) {
            }

        }
    }
}
