package org.pixeltime.enchantmentsenhance.event.enchantment

import com.sk89q.worldguard.bukkit.WGBukkit
import com.sk89q.worldguard.protection.flags.DefaultFlag
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.ChatColor
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.pixeltime.enchantmentsenhance.manager.DM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Thief : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "thief"))
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
                if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && n < SettingsManager.enchant.getInt("thief.level_I.chance")) {
                    val n2 = SettingsManager.enchant.getInt("thief.level_I.money-percent") / 100.0 * DM.economy.getBalance(player2 as OfflinePlayer)
                    DM.economy.withdrawPlayer(player2 as OfflinePlayer, n2)
                    DM.economy.depositPlayer(player as OfflinePlayer, n2)
                }
                if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && n < SettingsManager.enchant.getInt("thief.level_II.chance")) {
                    val n3 = SettingsManager.enchant.getInt("thief.level_II.money-percent") / 100.0 * DM.economy.getBalance(player2 as OfflinePlayer)
                    DM.economy.withdrawPlayer(player2 as OfflinePlayer, n3)
                    DM.economy.depositPlayer(player as OfflinePlayer, n3)
                }
                if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III") && n < SettingsManager.enchant.getInt("thief.level_III.chance")) {
                    val n4 = SettingsManager.enchant.getInt("thief.level_III.money-percent") / 100.0 * DM.economy.getBalance(player2 as OfflinePlayer)
                    DM.economy.withdrawPlayer(player2 as OfflinePlayer, n4)
                    DM.economy.depositPlayer(player as OfflinePlayer, n4)
                }
            } catch (ex: Exception) {
            }

        }
    }
}
