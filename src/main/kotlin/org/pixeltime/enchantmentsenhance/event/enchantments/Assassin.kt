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
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Assassin : Listener {
    private val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "assassin"))

    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            val player = entityDamageByEntityEvent.damager as Player
            val player2 = entityDamageByEntityEvent.entity as Player
            if (entityDamageByEntityEvent.isCancelled) {
                return
            }
            if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player2.world).getApplicableRegions(player2.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
                return
            }
            if (!player.itemInHand.hasItemMeta() || !player.itemInHand.itemMeta.hasLore()) {
                return
            }
            val level = KM.getLevel(translateAlternateColorCodes, player.itemInHand.itemMeta.lore)
            if (level > 0 && (Math.random() * 100.0).toInt() < SettingsManager.enchant.getInt("assassin.$level.chance")) {
                player2.addPotionEffect(PotionEffect(PotionEffectType.POISON, SettingsManager.enchant.getInt("assassin.$level.duration") * 20, 0))
            }
        }
    }
}
