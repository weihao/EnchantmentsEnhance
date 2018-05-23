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
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Paralyze : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "paralyze"))
        if (entityDamageByEntityEvent.entity is Player && entityDamageByEntityEvent.damager is Player) {
            try {
                val player = entityDamageByEntityEvent.entity as Player
                val player2 = entityDamageByEntityEvent.damager as Player
                if (entityDamageByEntityEvent.isCancelled) {
                    return
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.world).getApplicableRegions(player.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
                    return
                }
                val n = (Math.random() * 100.0).toInt()
                val armorContents = player.inventory.armorContents
                val length = armorContents.size
                var i = 0
                while (i < length) {
                    val itemStack = armorContents[i]
                    if (itemStack != null) {
                        if (itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && n < SettingsManager.enchant.getInt("paralyze.level_I.chance")) {
                            player2.addPotionEffect(PotionEffect(PotionEffectType.getById(4), SettingsManager.enchant.getInt("paralyze.level_I.duration") * 20, SettingsManager.enchant.getInt("paralyze.level_I.potion_lvl") - 1))
                        }
                        if (itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && n < SettingsManager.enchant.getInt("paralyze.level_II.chance")) {
                            player2.addPotionEffect(PotionEffect(PotionEffectType.getById(4), SettingsManager.enchant.getInt("paralyze.level_II.duration") * 20, SettingsManager.enchant.getInt("paralyze.level_II.potion_lvl") - 1))
                        }
                    }
                    ++i
                }
            } catch (ex: Exception) {
            }

        }
    }
}
