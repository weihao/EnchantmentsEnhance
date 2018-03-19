package org.pixeltime.healpot.enhancement.listeners;

import java.util.List;
import java.util.Random;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.pixeltime.healpot.enhancement.events.inventory.Inventory;
import org.pixeltime.healpot.enhancement.manager.Permissions;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.AnimalBreeding;
import org.pixeltime.healpot.enhancement.util.Util;

public class LifeskillingHandler implements Listener {
    private final List<String> mining = SettingsManager.config.getStringList(
        "lifeskill.mining");
    private final List<String> chopping = SettingsManager.config.getStringList(
        "lifeskill.chopping");
    private final List<String> fishing = SettingsManager.config.getStringList(
        "lifeskill.fishing");
    private final List<String> killing = SettingsManager.config.getStringList(
        "lifeskill.killing");
    private final List<String> breeding = SettingsManager.config.getStringList(
        "lifeskill.breeding");

    private final int miningChance = SettingsManager.config.getInt(
        "reward.mining.chance");
    private final int choppingChance = SettingsManager.config.getInt(
        "reward.chopping.chance");
    private final int fishingChance = SettingsManager.config.getInt(
        "reward.fishing.chance");
    private final int killingChance = SettingsManager.config.getInt(
        "reward.killing.chance");
    private final int breedingChance = SettingsManager.config.getInt(
        "reward.breeding.chance");

    private final List<Integer> miningLootTable = SettingsManager.config
        .getIntegerList("reward.mining.drops");
    private final List<Integer> choppingLootTable = SettingsManager.config
        .getIntegerList("reward.chopping.drops");
    private final List<Integer> fishingLootTable = SettingsManager.config
        .getIntegerList("reward.fishing.drops");
    private final List<Integer> killingLootTable = SettingsManager.config
        .getIntegerList("reward.killing.drops");
    private final List<Integer> breedingLootTable = SettingsManager.config
        .getIntegerList("reward.breeding.drops");

    private final Random random = new Random();


    /**
     * Mining gives enhancement stone.
     * 
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onMining(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (player.getGameMode() != GameMode.SURVIVAL || !Permissions
            .commandEnhance(player)) {
            return;
        }
        if (mining.contains(e.getBlock().getType().toString()))
            if (miningChance > random.nextDouble()) {
                randomDrop(player, miningLootTable);
            }
    }


    private void randomDrop(Player player, List<Integer> miningLootTable2) {
        int stoneType = miningLootTable.get(random.nextInt(miningLootTable
            .size()));

        Inventory.addLevel(player, stoneType, 1);
        Util.sendMessage(SettingsManager.lang.getString("Item.get")
            + SettingsManager.lang.getString("Item." + stoneType), player);
    }


    /**
     * Mining gives enhancement stone.
     * 
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onChopping(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (player.getGameMode() != GameMode.SURVIVAL || !Permissions
            .commandEnhance(player)) {
            return;
        }
        if (chopping.contains(e.getBlock().getType().toString()))
            if (choppingChance > random.nextDouble()) {
                randomDrop(player, choppingLootTable);
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
            if (fishingChance > random.nextDouble()) {
                randomDrop(player, fishingLootTable);
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
                if (killingChance > random.nextDouble()) {
                    randomDrop(player, killingLootTable);
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
        if (AnimalBreeding.breeadableFood.contains(e.getPlayer().getItemInHand()
            .getType())) {
            if (AnimalBreeding.breeadableAnimals.contains(e.getRightClicked()
                .getType())) {
                Animals a = (Animals)e.getRightClicked();
                if (a.canBreed()) {
                    if (breedingChance > random.nextDouble()) {
                        randomDrop(e.getPlayer(), breedingLootTable);
                    }
                    a.setBreed(false);
                }
            }
        }

    }
}
