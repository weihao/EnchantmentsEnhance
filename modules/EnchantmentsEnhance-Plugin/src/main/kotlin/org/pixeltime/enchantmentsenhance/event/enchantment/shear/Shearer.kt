package org.pixeltime.enchantmentsenhance.event.enchantment.shear

import com.lgou2w.ldk.bukkit.compatibility.XMaterial
import org.bukkit.entity.Player
import org.bukkit.entity.Sheep
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerShearEntityEvent
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Shearer : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf(
            "When left clicking the item having this enchant in the air, all Sheep within the radius will be sheared",
            "拿着剪刀对着空中点击，周围的羊都会被剃毛"
        )
    }

    override fun lang(): Array<String> {
        return arrayOf("剪裁")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onInteract(playerInteractEvent: PlayerInteractEvent) {
        val player = playerInteractEvent.player
        if ((playerInteractEvent.action == Action.RIGHT_CLICK_BLOCK) || (playerInteractEvent == Action.RIGHT_CLICK_AIR)) {
            val level = getLevel(player)
            if (level > 0) {
                shear(player, level)
            }
        }
    }

    @EventHandler
    fun playerShearEvent(event: PlayerShearEntityEvent) {
        if (event.entity is Sheep) {
            val player = event.player
            val level = getLevel(player)
            if (level > 0) {
                shear(player, level)
            }
        }
    }

    fun shear(player: Player, level: Int) {
        val int1 = SettingsManager.enchant.getInt("shearer.$level.radius")
        for (entity in player.getNearbyEntities(int1.toDouble(), int1.toDouble(), int1.toDouble())) {
            if (entity is Sheep) {
                if (!entity.isSheared) {
                    entity.isSheared = true
                    entity.world.dropItem(
                        entity.location,
                        ItemStack(XMaterial.WHITE_WOOL.toBukkit(), 1, entity.color!!.woolData.toShort())
                    )
                }
            }
        }
    }
}
