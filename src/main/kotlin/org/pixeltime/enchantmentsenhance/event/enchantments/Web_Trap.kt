package org.pixeltime.enchantmentsenhance.event.enchantment

import com.sk89q.worldguard.bukkit.WGBukkit
import com.sk89q.worldguard.protection.flags.DefaultFlag
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.scheduler.BukkitRunnable
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

import java.util.ArrayList

class Web_Trap : Listener {
    private val temp: ArrayList<Block>

    init {
        this.temp = ArrayList()
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "web_trap"))
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
                if (player2.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && n < SettingsManager.enchant.getInt("web_trap.level_I.chance")) {
                    if (SettingsManager.enchant.getBoolean("web_trap.check-wg-flag") && WGBukkit.getRegionManager(player.world).getApplicableRegions(player.location).queryState(null, *arrayOf(DefaultFlag.BUILD)) == StateFlag.State.DENY) {
                        return
                    }
                    val location = player.location
                    val block = location.block
                    this.temp.add(block)
                    location.block.type = Material.WEB
                    Bukkit.getServer().scheduler.scheduleSyncDelayedTask(Main.getMain(), object : BukkitRunnable() {
                        override fun run() {
                            block.type = Material.AIR
                            this@Web_Trap.temp.clear()
                        }
                    }, (SettingsManager.enchant.getInt("web_trap.level_I.duration") * 20).toLong())
                }
                if (player2.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && n < SettingsManager.enchant.getInt("web_trap.level_II.chance")) {
                    if (SettingsManager.enchant.getBoolean("web_trap.check-wg-flag") && WGBukkit.getRegionManager(player.world).getApplicableRegions(player.location).queryState(null, *arrayOf(DefaultFlag.BUILD)) == StateFlag.State.DENY) {
                        return
                    }
                    val location2 = player.location
                    this.temp.add(location2.block)
                    location2.block.type = Material.WEB
                    Bukkit.getServer().scheduler.scheduleSyncDelayedTask(Main.getMain(), object : BukkitRunnable() {
                        override fun run() {
                            location2.block.type = Material.AIR
                            this@Web_Trap.temp.clear()
                        }
                    }, (SettingsManager.enchant.getInt("web_trap.level_II.duration") * 20).toLong())
                }
                if (player2.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III") && n < SettingsManager.enchant.getInt("web_trap.level_III.chance")) {
                    if (SettingsManager.enchant.getBoolean("web_trap.check-wg-flag") && WGBukkit.getRegionManager(player.world).getApplicableRegions(player.location).queryState(null, *arrayOf(DefaultFlag.BUILD)) == StateFlag.State.DENY) {
                        return
                    }
                    val location3 = player.location
                    this.temp.add(location3.block)
                    location3.block.type = Material.WEB
                    Bukkit.getServer().scheduler.scheduleSyncDelayedTask(Main.getMain(), object : BukkitRunnable() {
                        override fun run() {
                            location3.block.type = Material.AIR
                            this@Web_Trap.temp.clear()
                        }
                    }, (SettingsManager.enchant.getInt("web_trap.level_III.duration") * 20).toLong())
                }
            } catch (ex: Exception) {
            }

        }
    }
}
