package org.pixeltime.enchantmentsenhance.event.enchantment

import com.sk89q.worldguard.bukkit.WGBukkit
import com.sk89q.worldguard.protection.flags.DefaultFlag
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.entity.Wolf
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Wolves : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "wolves"))
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            try {
                if (entityDamageByEntityEvent.isCancelled) {
                    return
                }
                val target = entityDamageByEntityEvent.damager as Player
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(entityDamageByEntityEvent.entity.world).getApplicableRegions(entityDamageByEntityEvent.entity.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
                    return
                }
                val owner = entityDamageByEntityEvent.entity as Player
                val armorContents = owner.inventory.armorContents
                val n = (Math.random() * 100.0).toInt()
                val array: Array<ItemStack>
                val length = (array = armorContents).size
                var i = 0
                while (i < length) {
                    val itemStack = array[i]
                    if (itemStack != null && itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && n < SettingsManager.enchant.getInt("wolves.level_I.chance")) {
                        for (j in 0 until SettingsManager.enchant.getInt("wolves.level_I.wolves")) {
                            val wolf = target.world.spawn<Entity>(target.location, Wolf::class.java as Class<*>) as Wolf
                            wolf.isTamed = true
                            wolf.owner = owner
                            wolf.setAdult()
                            wolf.isAngry = true
                            wolf.target = target
                        }
                    }
                    if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && n < SettingsManager.enchant.getInt("wolves.level_II.chance")) {
                        for (k in 0 until SettingsManager.enchant.getInt("wolves.level_II.wolves")) {
                            val wolf2 = target.world.spawn<Entity>(target.location, Wolf::class.java as Class<*>) as Wolf
                            wolf2.isTamed = true
                            wolf2.owner = owner
                            wolf2.setAdult()
                            wolf2.isAngry = true
                            wolf2.target = target
                        }
                    }
                    if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains("§7Wolves III") && n < SettingsManager.enchant.getInt("wolves.level_III.chance")) {
                        for (l in 0 until SettingsManager.enchant.getInt("wolves.level_III.wolves")) {
                            val wolf3 = target.world.spawn<Entity>(target.location, Wolf::class.java as Class<*>) as Wolf
                            wolf3.isTamed = true
                            wolf3.owner = owner
                            wolf3.setAdult()
                            wolf3.isAngry = true
                            wolf3.target = target
                        }
                    }
                    if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains("§7Wolves IV") && n < SettingsManager.enchant.getInt("wolves.level_IV.chance")) {
                        for (n2 in 0 until SettingsManager.enchant.getInt("wolves.level_IV.wolves")) {
                            val wolf4 = target.world.spawn<Entity>(target.location, Wolf::class.java as Class<*>) as Wolf
                            wolf4.isTamed = true
                            wolf4.owner = owner
                            wolf4.setAdult()
                            wolf4.isAngry = true
                            wolf4.target = target
                        }
                    }
                    if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains("§7Wolves V") && n < SettingsManager.enchant.getInt("wolves.level_V.chance")) {
                        for (n3 in 0 until SettingsManager.enchant.getInt("wolves.level_V.wolves")) {
                            val wolf5 = target.world.spawn<Entity>(target.location, Wolf::class.java as Class<*>) as Wolf
                            wolf5.isTamed = true
                            wolf5.owner = owner
                            wolf5.setAdult()
                            wolf5.isAngry = true
                            wolf5.target = target
                        }
                    }
                    ++i
                }
            } catch (ex: Exception) {
            }

        }
    }
}
