/*
 *     Copyright (C) 2017-Present HealPot
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.pixeltime.enchantmentsenhance.listener

import org.bukkit.ChatColor
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import java.io.File
import java.io.IOException
import java.util.*

class PlayerDeathListener : Listener {

    companion object {
        private val m = Main.getMain()
    }

    /**
     * Prevents enhanced item dropped from death.
     *
     * @param e
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerDeath(e: PlayerDeathEvent) {
        val p = e.entity
        val newInventory = ArrayList<ItemStack>()
        val playerFile = File(m.dataFolder.toString() + "/death/" + p.name + ".yml")
        val pFile = YamlConfiguration.loadConfiguration(playerFile)
        pFile.set("PlayerName", p.name)
        if (!e.drops.isEmpty() || e.drops != null) {
            for (i in 0 until e.drops.size) {
                val stack = e.drops[i]
                if (stack.hasItemMeta()) {
                    val meta = stack.itemMeta
                    if (meta.hasLore()) {
                        val lore = meta.lore
                        for (s in lore) {
                            val str = ChatColor.stripColor(s)
                            if (str.contains(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("lore.untradeableLore")))) ||
                                str.contains(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("lore.tradeableLore"))))) {
                                newInventory.add(e.drops[i])
                            }
                        }
                    }
                }
            }
            val newStack = newInventory.toTypedArray()
            pFile.set("Items", newStack)
            try {
                pFile.save(playerFile)
            } catch (e1: IOException) {

            }

            e.drops.removeAll(newInventory)
        }
    }


    /**
     * Returns enhanced item to the player when respawn.
     *
     * @param e
     */
    @EventHandler(priority = EventPriority.MONITOR)
    fun onPlayerRespawn(e: PlayerRespawnEvent) {
        val p = e.player

        val playerFile = File(m.dataFolder.toString() + "/death/" + p.name + ".yml")
        val pFile = YamlConfiguration.loadConfiguration(playerFile)
        if (playerFile.exists()) {
            val content = (pFile.getList("Items") as List<ItemStack>).toTypedArray()
            p.inventory.addItem(*content)

            if (playerFile.delete()) {
                // Delete a file.
            }
        }
    }
}
