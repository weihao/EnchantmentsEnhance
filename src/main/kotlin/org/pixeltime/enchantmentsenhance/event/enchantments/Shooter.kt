package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.block.Block
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.BlockIterator
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

import java.util.ArrayList
import java.util.HashMap
import java.util.UUID

class Shooter : Listener {
    private val temp: HashMap<Projectile, String>
    private val cooldown: ArrayList<UUID>

    init {
        this.temp = HashMap()
        this.cooldown = ArrayList()
    }

    @EventHandler
    fun onInteract(playerInteractEvent: PlayerInteractEvent) {
        if (playerInteractEvent.action != Action.RIGHT_CLICK_AIR && playerInteractEvent.action != Action.RIGHT_CLICK_BLOCK)
            return
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "shooter"))
        val player = playerInteractEvent.player
        if (this.cooldown.contains(player.uniqueId)) {
            return
        }
        if (player.itemInHand != null && player.itemInHand.hasItemMeta() && player.itemInHand.itemMeta.hasLore() && player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
            if (SettingsManager.enchant.getString("shooter.level_I.projectile").equals("snowball", ignoreCase = true)) {
                val throwSnowball = player.throwSnowball()
                throwSnowball.velocity = player.eyeLocation.direction.multiply(4)
                this.temp[throwSnowball] = player.name
                if (SettingsManager.enchant.getBoolean("shooter.level_I.enable-cooldown")) {
                    val n = SettingsManager.enchant.getInt("shooter.level_I.cooldown") * 20
                    this.cooldown.add(player.uniqueId)
                    object : BukkitRunnable() {
                        override fun run() {
                            this@Shooter.cooldown.remove(player.uniqueId)
                        }
                    }.runTaskLater(Main.getMain(), n.toLong())
                }
            } else if (SettingsManager.enchant.getString("shooter.level_I.projectile").equals("fireball", ignoreCase = true)) {
                val fireball = player.launchProjectile<Projectile>(Fireball::class.java as Class<*>) as Fireball
                fireball.setIsIncendiary(false)
                fireball.yield = 0.0f
                fireball.velocity = player.eyeLocation.direction.multiply(4)
                this.temp[fireball] = player.name
                if (SettingsManager.enchant.getBoolean("shooter.level_I.enable-cooldown")) {
                    val n2 = SettingsManager.enchant.getInt("shooter.level_I.cooldown") * 20
                    this.cooldown.add(player.uniqueId)
                    object : BukkitRunnable() {
                        override fun run() {
                            this@Shooter.cooldown.remove(player.uniqueId)
                        }
                    }.runTaskLater(Main.getMain(), n2.toLong())
                }
            } else {
                if (!SettingsManager.enchant.getString("shooter.level_I.projectile").equals("egg", ignoreCase = true)) {
                    return
                }
                val egg = player.launchProjectile<Projectile>(Egg::class.java as Class<*>) as Egg
                egg.velocity = player.eyeLocation.direction.multiply(4)
                this.temp[egg] = player.name
                if (SettingsManager.enchant.getBoolean("shooter.level_I.enable-cooldown")) {
                    val n3 = SettingsManager.enchant.getInt("shooter.level_I.cooldown") * 20
                    this.cooldown.add(player.uniqueId)
                    object : BukkitRunnable() {
                        override fun run() {
                            this@Shooter.cooldown.remove(player.uniqueId)
                        }
                    }.runTaskLater(Main.getMain(), n3.toLong())
                }
            }
        }
    }

    @EventHandler
    fun onHit(projectileHitEvent: ProjectileHitEvent) {
        val entity = projectileHitEvent.entity
        if (this.temp.containsKey(entity)) {
            val player = Bukkit.getPlayer(this.temp[entity]) ?: return
            val blockIterator = BlockIterator(projectileHitEvent.entity.world, projectileHitEvent.entity.location.toVector(), projectileHitEvent.entity.velocity.normalize(), 0.0, 4)
            var next: Block? = null
            while (blockIterator.hasNext()) {
                next = blockIterator.next()
                if (next!!.typeId != 0) {
                    break
                }
            }
            val blockBreakEvent = BlockBreakEvent(next, player)
            Bukkit.getPluginManager().callEvent(blockBreakEvent)
            if (!blockBreakEvent.isCancelled) {
                next!!.breakNaturally()
                this.temp.remove(entity)
            }
        }
    }
}
