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

class Petrify : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun onHit(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "petrify"))
        if (entityDamageByEntityEvent.entity is Player && entityDamageByEntityEvent.damager is Player) {
            try {
                val player = entityDamageByEntityEvent.entity as Player
                val player2 = entityDamageByEntityEvent.damager as Player
                val n = (Math.random() * 100.0).toInt()
                if (entityDamageByEntityEvent.isCancelled) {
                    return
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.world).getApplicableRegions(player.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
                    return
                }
                if (player2.itemInHand.itemMeta.hasLore() && player2.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && n < SettingsManager.enchant.getInt("petrify.level_I.chance")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.SLOW, SettingsManager.enchant.getInt("petrify.level_I.duration") * 20, SettingsManager.enchant.getInt("petrify.level_I.potion_lvl") - 1))
                }
                if (player2.itemInHand.itemMeta.hasLore() && player2.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && n < SettingsManager.enchant.getInt("petrify.level_II.chance")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.SLOW, SettingsManager.enchant.getInt("petrify.level_II.duration") * 20, SettingsManager.enchant.getInt("petrify.level_II.potion_lvl") - 1))
                }
                if (player2.itemInHand.itemMeta.hasLore() && player2.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III") && n < SettingsManager.enchant.getInt("petrify.level_III.chance")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.SLOW, SettingsManager.enchant.getInt("petrify.level_III.duration") * 20, SettingsManager.enchant.getInt("petrify.level_III.potion_lvl") - 1))
                }
            } catch (ex: Exception) {
            }

        }
    }
}
