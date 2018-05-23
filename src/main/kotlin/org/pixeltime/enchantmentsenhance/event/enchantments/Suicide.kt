package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Suicide : Listener {
    @EventHandler
    fun onDeath(playerDeathEvent: PlayerDeathEvent) {
        val entity = playerDeathEvent.entity
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "suicide"))
        val n = (Math.random() * 100.0).toInt()
        if (entity.inventory.chestplate != null && entity.inventory.chestplate.hasItemMeta() && entity.inventory.chestplate.itemMeta.hasLore() && entity.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && n < SettingsManager.enchant.getInt("suicide.level_I.chance")) {
            entity.world.createExplosion(entity.location, SettingsManager.enchant.getInt("suicide.level_I.power").toFloat())
        }
    }
}
