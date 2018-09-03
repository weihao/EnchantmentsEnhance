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

package org.pixeltime.enchantmentsenhance.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.Inventory;
import org.pixeltime.enchantmentsenhance.manager.DropManager;
import org.pixeltime.enchantmentsenhance.util.Util;
import org.pixeltime.enchantmentsenhance.util.events.AnimalBreeding;

import java.util.Random;

public class LifeskillingListener implements Listener {
    private final Random random = new Random();


    /**
     * Mining gives enhancement stone.
     *
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onMining(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (player.getGameMode() != GameMode.SURVIVAL) {
            return;
        }
        if (DropManager.getMining() .contains(e.getBlock().getType()))
            if (DropManager.miningChance > random.nextDouble()) {
                DropManager.randomDrop(player, DropManager.miningLootTable);
            }
    }


    /**
     * Mining gives enhancement stone.
     *
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onChopping(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (player.getGameMode() != GameMode.SURVIVAL) {
            return;
        }
        if (DropManager.getChopping().contains(e.getBlock().getType()))
            if (DropManager.choppingChance > random.nextDouble()) {
                DropManager.randomDrop(player, DropManager.choppingLootTable);
            }
    }


    /**
     * Fishing gives enhancement stone.
     *
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onFishing(PlayerFishEvent e) {
        // If the fishing is successful
        if (e.getState().equals(State.CAUGHT_FISH)) {
            Player player = e.getPlayer();
            if (DropManager.fishingChance > random.nextDouble()) {
                DropManager.randomDrop(player, DropManager.fishingLootTable);
            }
        }
    }


    /**
     * Killing mobs gives enhancement stone.
     *
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onKilling(EntityDeathEvent e) {
        // If the killed entity is a monster
        if (e.getEntity() instanceof Monster) {
            if (e.getEntity().getKiller() instanceof Player) {
                Player player = e.getEntity().getKiller();
                if (DropManager.killingChance > random.nextDouble()) {
                    DropManager.randomDrop(player, DropManager.killingLootTable);
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
    public void onBreeding(PlayerInteractEntityEvent e) {
        if (AnimalBreeding.breeadableFood.contains(e.getPlayer().getInventory().getItemInMainHand()
                .getType())) {
            if (AnimalBreeding.breeadableAnimals.contains(e.getRightClicked()
                    .getType())) {
                Animals a = (Animals) e.getRightClicked();
                if (a.canBreed()) {
                    if (DropManager.breedingChance > random.nextDouble()) {
                        DropManager.randomDrop(e.getPlayer(), DropManager.breedingLootTable);
                    }
                    a.setBreed(true);
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
    public void onSmelting(FurnaceExtractEvent e) {
        for (int i = 0; i < e.getItemAmount(); i++) {
            if (DropManager.smeltingChance > random.nextDouble()) {
                DropManager.randomDrop(e.getPlayer(), DropManager.smeltingLootTable);
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
    public void onSmelting2(InventoryClickEvent e) {
        Inventory clickedInventory = null;
        Player player = (Player) e.getWhoClicked();
        if (e.getSlot() < 0) {
            clickedInventory = null;
        } else if (e.getView().getTopInventory() != null && e.getSlot() < e
                .getView().getTopInventory().getSize()) {
            clickedInventory = e.getView().getTopInventory();
        } else {
            clickedInventory = e.getView().getBottomInventory();
        }
        if (clickedInventory == null) {
            return;
        }
        if (!clickedInventory.getType().equals(InventoryType.FURNACE)) {
            return;
        }
        FurnaceInventory fi = (FurnaceInventory) clickedInventory;
        boolean click = e.getClick().isShiftClick() || e.getClick()
                .isLeftClick() && e.getRawSlot() == 2;
        boolean item = fi.getResult() != null;
        if (click && item && !Util.invFull(player)) {
            for (int i = 0; i < fi.getResult().getAmount(); i++) {
                if (DropManager.smeltingChance > random.nextDouble()) {
                    DropManager.randomDrop(player, DropManager.smeltingLootTable);
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onHarvesting(PlayerInteractEvent event) {
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
