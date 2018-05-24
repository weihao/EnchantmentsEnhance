package org.pixeltime.enchantmentsenhance.event.enchantment

import com.sk89q.worldguard.bukkit.WGBukkit
import com.sk89q.worldguard.protection.flags.DefaultFlag
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerToggleSneakEvent
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Immolation : Listener {
    private val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "immolation"))

    @EventHandler
    fun onSneak(playerToggleSneakEvent: PlayerToggleSneakEvent) {
        val player = playerToggleSneakEvent.player
        if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.world).getApplicableRegions(player.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
            return
        }
        try {
            val armorContents = player.inventory.armorContents + IM.getAccessorySlots(player)
            for (i in armorContents.indices) {
                val itemStack = armorContents[i]
                if (itemStack.hasItemMeta() && itemStack.itemMeta.hasLore()) {
                    val level = KM.getLevel(translateAlternateColorCodes, itemStack.itemMeta.lore)
                    if (level > 0 && (Math.random() * 100.0).toInt() < SettingsManager.enchant.getInt("immolation.$level.chance")) {
                        for (entity in player.getNearbyEntities(SettingsManager.enchant.getDouble("immolation.$level.radius"), SettingsManager.enchant.getDouble("immolation.$level.radius"), SettingsManager.enchant.getDouble("immolation.$level.radius"))) {
                            if (entity is Player) {
                                entity.setFireTicks(SettingsManager.enchant.getInt("immolation.$level.duration") * 20)
                            }
                        }
                    }
                }
            }
        } catch (ex: Exception) {
        }

    }
}
