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
import org.bukkit.event.player.PlayerFishEvent.State
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.FurnaceInventory
import org.bukkit.inventory.Inventory
import org.pixeltime.enchantmentsenhance.manager.DropManager
import org.pixeltime.enchantmentsenhance.util.Util
import org.pixeltime.enchantmentsenhance.util.events.AnimalBreeding
import java.util.Random

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
        if (e.state == State.CAUGHT_FISH) {
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
                    DropManager.randomDrop(player!!, DropManager.killingLootTable)
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
        if (AnimalBreeding.breeadableFood.contains(Util.getMainHand(e.player)
                        .type)) {
            if (AnimalBreeding.breeadableAnimals.contains(e.rightClicked
                            .type)) {
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
        var clickedInventory: Inventory? = null
        val player = e.whoClicked as Player
        if (e.slot < 0) {
            clickedInventory = null
        } else if (e.view.topInventory != null && e.slot < e
                        .view.topInventory.size) {
            clickedInventory = e.view.topInventory
        } else {
            clickedInventory = e.view.bottomInventory
        }
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

        if (click && item && !fi.result!!.type.isFuel && !Util.invFull(player)) {
            for (i in 0 until fi.result!!.amount) {
                if (DropManager.smeltingChance > random.nextDouble()) {
                    DropManager.randomDrop(player, DropManager.smeltingLootTable)
                }
            }
        }
    }
}
