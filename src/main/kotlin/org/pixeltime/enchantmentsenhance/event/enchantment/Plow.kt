package org.pixeltime.enchantmentsenhance.event.enchantment

import com.sk89q.worldguard.bukkit.WGBukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Plow : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun onInteract(playerInteractEvent: PlayerInteractEvent) {
        if (playerInteractEvent.isCancelled) {
            return
        }
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "plow"))
        val player = playerInteractEvent.player
        if (player.itemInHand != null && player.itemInHand.hasItemMeta() && player.itemInHand.itemMeta.hasLore()) {
            val clickedBlock = playerInteractEvent.clickedBlock
            if (clickedBlock.type != Material.DIRT && clickedBlock.type != Material.GRASS) {
                return
            }
            if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                val n = 0.5
                val location = clickedBlock.location
                var n2 = location.blockX - n
                while (n2 <= location.blockX + n) {
                    var n3 = location.blockZ - n
                    while (n3 <= location.blockZ + n) {
                        val block = location.world.getBlockAt(Location(clickedBlock.world, n2, clickedBlock.y.toDouble(), n3))
                        if (block.type != Material.GRASS && block.type != Material.DIRT) {
                            return
                        }
                        if (SettingsManager.enchant.getBoolean("allow-worldguard") && !WGBukkit.getPlugin().canBuild(player, block)) {
                            return
                        }
                        block.type = Material.SOIL
                        ++n3
                    }
                    ++n2
                }
            }
            if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II")) {
                val n4 = 1.0
                val location2 = clickedBlock.location
                var n5 = location2.blockX - n4
                while (n5 <= location2.blockX + n4) {
                    var n6 = location2.blockZ - n4
                    while (n6 <= location2.blockZ + n4) {
                        val block2 = location2.world.getBlockAt(Location(clickedBlock.world, n5, clickedBlock.y.toDouble(), n6))
                        if (block2.type != Material.GRASS && block2.type != Material.DIRT) {
                            return
                        }
                        if (SettingsManager.enchant.getBoolean("allow-worldguard") && !WGBukkit.getPlugin().canBuild(player, block2)) {
                            return
                        }
                        block2.type = Material.SOIL
                        ++n6
                    }
                    ++n5
                }
            }
            if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III")) {
                val n7 = 1.5
                val location3 = clickedBlock.location
                var n8 = location3.blockX - n7
                while (n8 <= location3.blockX + n7) {
                    var n9 = location3.blockZ - n7
                    while (n9 <= location3.blockZ + n7) {
                        val block3 = location3.world.getBlockAt(Location(clickedBlock.world, n8, clickedBlock.y.toDouble(), n9))
                        if (block3.type != Material.GRASS && block3.type != Material.DIRT) {
                            return
                        }
                        if (SettingsManager.enchant.getBoolean("allow-worldguard") && !WGBukkit.getPlugin().canBuild(player, block3)) {
                            return
                        }
                        block3.type = Material.SOIL
                        ++n9
                    }
                    ++n8
                }
            }
            if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " IV")) {
                val n10 = 2.0
                val location4 = clickedBlock.location
                var n11 = location4.blockX - n10
                while (n11 <= location4.blockX + n10) {
                    var n12 = location4.blockZ - n10
                    while (n12 <= location4.blockZ + n10) {
                        val block4 = location4.world.getBlockAt(Location(clickedBlock.world, n11, clickedBlock.y.toDouble(), n12))
                        if (block4.type != Material.GRASS && block4.type != Material.DIRT) {
                            return
                        }
                        if (SettingsManager.enchant.getBoolean("allow-worldguard") && !WGBukkit.getPlugin().canBuild(player, block4)) {
                            return
                        }
                        block4.type = Material.SOIL
                        ++n12
                    }
                    ++n11
                }
            }
            if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " V")) {
                val n13 = 2.5
                val location5 = clickedBlock.location
                var n14 = location5.blockX - n13
                while (n14 <= location5.blockX + n13) {
                    var n15 = location5.blockZ - n13
                    while (n15 <= location5.blockZ + n13) {
                        val block5 = location5.world.getBlockAt(Location(clickedBlock.world, n14, clickedBlock.y.toDouble(), n15))
                        if (block5.type != Material.GRASS && block5.type != Material.DIRT) {
                            return
                        }
                        if (SettingsManager.enchant.getBoolean("allow-worldguard") && !WGBukkit.getPlugin().canBuild(player, block5)) {
                            return
                        }
                        block5.type = Material.SOIL
                        ++n15
                    }
                    ++n14
                }
            }
        }
    }
}
