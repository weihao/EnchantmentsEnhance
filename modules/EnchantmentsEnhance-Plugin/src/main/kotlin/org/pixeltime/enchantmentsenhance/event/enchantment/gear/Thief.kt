package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import com.sk89q.worldguard.bukkit.WGBukkit
import com.sk89q.worldguard.protection.flags.DefaultFlag
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.DependencyManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Thief : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("While PvPing, you have a chance to steal some % of the money from your opponent", "当你PVP时有几率偷取别人的一部分钱")
    }

    override fun lang(): Array<String> {
        return arrayOf("盗窃")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {

        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            val player = entityDamageByEntityEvent.damager as Player
            val victim = entityDamageByEntityEvent.entity as Player
            if (entityDamageByEntityEvent.isCancelled) {
                return
            }
            if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(victim.world).getApplicableRegions(victim.location).queryState(null, DefaultFlag.PVP) == StateFlag.State.DENY) {
                return
            }
            try {
                val level = getLevel(player)
                if (level > 0 && (roll(level))) {
                    val n2 = SettingsManager.enchant.getInt("thief.$level.money-percent") / 100.0 * (DependencyManager.economy!!.getBalance(victim as OfflinePlayer))
                    DependencyManager.economy!!.withdrawPlayer(victim as OfflinePlayer, n2)
                    DependencyManager.economy!!.depositPlayer(player as OfflinePlayer, n2)
                }
            } catch (ex: Exception) {
            }
        }
    }
}
