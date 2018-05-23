package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.SkullType
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Kill_Confirm : Listener {
    @EventHandler
    fun onDeath(playerDeathEvent: PlayerDeathEvent) {
        val entity = playerDeathEvent.entity
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "kill_confirm"))
        try {
            if (playerDeathEvent.entity.lastDamageCause.cause == EntityDamageEvent.DamageCause.ENTITY_ATTACK && playerDeathEvent.entity.lastDamageCause is EntityDamageByEntityEvent) {
                val damager = (playerDeathEvent.entity.lastDamageCause as EntityDamageByEntityEvent).damager
                if (damager is Player) {
                    val n = (Math.random() * 100.0).toInt()
                    if (damager.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && n < SettingsManager.enchant.getInt("kill_confirm.level_I.chance")) {
                        val itemStack = ItemStack(Material.SKULL_ITEM, 1, SkullType.PLAYER.ordinal.toShort())
                        val itemMeta = itemStack.itemMeta as SkullMeta
                        itemMeta.owner = entity.name
                        itemMeta.displayName = "§aSkull of " + entity.name
                        itemStack.itemMeta = itemMeta
                        damager.world.dropItem(entity.location, itemStack)
                    }
                    if (damager.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && n < SettingsManager.enchant.getInt("kill_confirm.level_II.chance")) {
                        val itemStack2 = ItemStack(Material.SKULL_ITEM, 1, SkullType.PLAYER.ordinal.toShort())
                        val itemMeta2 = itemStack2.itemMeta as SkullMeta
                        itemMeta2.owner = entity.name
                        itemMeta2.displayName = "§aSkull of " + entity.name
                        itemStack2.itemMeta = itemMeta2
                        damager.world.dropItem(entity.location, itemStack2)
                    }
                    if (damager.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III") && n < SettingsManager.enchant.getInt("kill_confirm.level_III.chance")) {
                        val itemStack3 = ItemStack(Material.SKULL_ITEM, 1, SkullType.PLAYER.ordinal.toShort())
                        val itemMeta3 = itemStack3.itemMeta as SkullMeta
                        itemMeta3.owner = entity.name
                        itemMeta3.displayName = "§aSkull of " + entity.name
                        itemStack3.itemMeta = itemMeta3
                        damager.world.dropItem(entity.location, itemStack3)
                    }
                }
            }
        } catch (ex: Exception) {
        }

    }
}
