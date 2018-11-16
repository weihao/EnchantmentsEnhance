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

import org.bukkit.GameMode
import org.bukkit.entity.Animals
import org.bukkit.entity.Monster
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.inventory.FurnaceExtractEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.FurnaceInventory
import org.bukkit.inventory.Inventory
import org.pixeltime.enchantmentsenhance.manager.DropManager
import org.pixeltime.enchantmentsenhance.util.Util
import org.pixeltime.enchantmentsenhance.util.events.AnimalBreeding
import java.util.*

class LifeskillingListener : Listener {

    private val random = Random()

    /**
     * Mining gives enhancement stone.
     *
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    fun onMining(e: BlockBreakEvent) {
        val player = e.player
        if (player.gameMode != GameMode.SURVIVAL) {
            return
        }
        if (DropManager.mining.contains(e.block.type))
            if (DropManager.miningChance > random.nextDouble()) {
                DropManager.randomDrop(player, DropManager.miningLootTable)
            }
    }


    /**
     * Mining gives enhancement stone.
     *
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    fun onChopping(e: BlockBreakEvent) {
        val player = e.player
        if (player.gameMode != GameMode.SURVIVAL) {
            return
        }
        if (DropManager.chopping.contains(e.block.type))
            if (DropManager.choppingChance > random.nextDouble()) {
                DropManager.randomDrop(player, DropManager.choppingLootTable)
            }
    }


    /**
     * Fishing gives enhancement stone.
     *
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    fun onFishing(e: PlayerFishEvent) {
        // If the fishing is successful
        if (e.state == PlayerFishEvent.State.CAUGHT_FISH) {
            val player = e.player
            if (DropManager.fishingChance > random.nextDouble()) {
                DropManager.randomDrop(player, DropManager.fishingLootTable)
            }
        }
    }


    /**
     * Killing mobs gives enhancement stone.
     *
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    fun onKilling(e: EntityDeathEvent) {
        // If the killed entity is a monster
        if (e.entity is Monster) {
            if (e.entity.killer is Player) {
                val player = e.entity.killer
                if (DropManager.killingChance > random.nextDouble()) {
                    DropManager.randomDrop(player, DropManager.killingLootTable)
                }
            }
        }
    }


    /**
     * Breeding animals gives enhancement stone.
     *
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    fun onBreeding(e: PlayerInteractEntityEvent) {
        if (AnimalBreeding.breeadableFood.contains(e.player.inventory.itemInMainHand.type)) {
            if (AnimalBreeding.breeadableAnimals.contains(e.rightClicked.type)) {
                val a = e.rightClicked as Animals
                if (a.canBreed()) {
                    if (DropManager.breedingChance > random.nextDouble()) {
                        DropManager.randomDrop(e.player, DropManager.breedingLootTable)
                    }
                    a.setBreed(true)
                }
            }
        }
    }


    /**
     * Smelting items gives enhancement stone.
     *
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    fun onSmelting(e: FurnaceExtractEvent) {
        for (i in 0 until e.itemAmount) {
            if (DropManager.smeltingChance > random.nextDouble()) {
                DropManager.randomDrop(e.player, DropManager.smeltingLootTable)
            }
        }

    }


    /**
     * Fix a bukkit bug where shift click doesn't register.
     *
     * @param e
     * @return
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    fun onSmelting2(e: InventoryClickEvent) {
        val clickedInventory: Inventory? = if (e.slot < 0) {
            null
        } else if (e.view.topInventory != null && e.slot < e.view.topInventory.size) {
            e.view.topInventory
        } else {
            e.view.bottomInventory
        }
        val player = e.whoClicked as Player
        if (clickedInventory == null) {
            return
        }
        if (clickedInventory.type != InventoryType.FURNACE) {
            return
        }
        val fi = clickedInventory as FurnaceInventory?
        val click = e.click.isShiftClick || e.click
            .isLeftClick && e.rawSlot == 2
        val item = fi!!.result != null
        if (click && item && !Util.invFull(player)) {
            for (i in 0 until fi.result.amount) {
                if (DropManager.smeltingChance > random.nextDouble()) {
                    DropManager.randomDrop(player, DropManager.smeltingLootTable)
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    fun onHarvesting(event: PlayerInteractEvent) {
        //        if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
        //
        //        }
        //            if (event.getClickedBlock().getType() == Material.CROPS && event.getClickedBlock().getData() == 7){
        //                Material.POTATO 7
        //                Material.CARROT 7
        //                Material.NETHER_WARTS 3
        //                        Matieral.
        //                if (event.getClickedBlock().getType() == Material.COCOA && event.getClickedBlock().getData() == 8 || event.getClickedBlock().getType() == Material.COCOA && event.getClickedBlock().getData() == 9 || event.getClickedBlock().getType() == Material.COCOA &&event.getClickedBlock().getData() == 10 || event.getClickedBlock().getType() == Material.COCOA && event.getClickedBlock().getData() == 11){
        //
        //                }
        //
        //        }
    }
}
