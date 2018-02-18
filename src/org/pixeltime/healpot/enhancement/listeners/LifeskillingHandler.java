package org.pixeltime.healpot.enhancement.listeners;

import java.util.Random;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.pixeltime.healpot.enhancement.events.inventory.Inventory;
import org.pixeltime.healpot.enhancement.manager.Permissions;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.Util;

public class LifeskillingHandler implements Listener {
    /**
     * Mining or chopping gives enhancement stone.
     * 
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (player.getGameMode() != GameMode.SURVIVAL || !Permissions
            .commandEnhance(player)) {
            return;
        }
        Block block = e.getBlock();
        Random random = new Random();
        if (random.nextInt(2) == 1) {
            for (String listedBlock : SettingsManager.config.getStringList(
                "dropConcWeapon.block")) {
                if (block.getType().toString().equals(listedBlock)) {
                    randomDropConcWeapon(player);
                    return;
                }
            }
        }
        else {
            for (String listedBlock : SettingsManager.config.getStringList(
                "dropConcArmor.block")) {
                if (block.getType().toString().equals(listedBlock)) {
                    randomDropConcArmor(player);
                    return;
                }
            }
        }
    }


    /**
     * Fishing gives enhancement stone.
     * 
     * @param e
     */
    @EventHandler
    public void onFish(PlayerFishEvent e) {
        // If the fishing is successful
        if ((e.getCaught() instanceof Item)) {
            Player player = e.getPlayer();
            String[] stoneType = new String[] { SettingsManager.lang.getString(
                "Item.0"), SettingsManager.lang.getString("Item.1") };
            if (Math.random() < SettingsManager.config.getDouble(
                "dropWeaponNArmor.fishing.ratePerFish")) {
                int dice = new Random().nextInt(2);
                Inventory.addLevel(player, dice, 1);
                Util.sendMessage(SettingsManager.lang.getString(
                    "Config.pluginTag") + SettingsManager.lang.getString(
                        "Item.get").replaceAll("%ITEM%", stoneType[dice]),
                    player);
            }
        }
    }


    /**
     * Killing mobs gives enhancement stone.
     * 
     * @param e
     */
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        // If the killed entity is a monster
        if (e.getEntity() instanceof Monster) {
            if (e.getEntity().getKiller() instanceof Player) {
                String[] stoneType = new String[] { SettingsManager.lang
                    .getString("Item.0"), SettingsManager.lang.getString(
                        "Item.1") };
                Player player = e.getEntity().getKiller();
                if (Math.random() < SettingsManager.config.getDouble(
                    "dropWeaponNArmor.allMob.ratePerKill")) {
                    int dice = new Random().nextInt(2);
                    Inventory.addLevel(player, dice, 1);
                    Util.sendMessage(SettingsManager.lang.getString(
                        "Config.pluginTag") + SettingsManager.lang.getString(
                            "Item.get") + stoneType[dice], player);
                }
            }
        }
    }


    /**
     * Determines whatever it drops concentrated weapon stone or not.
     * 
     * @param player
     */
    public void randomDropConcWeapon(Player player) {
        if (Math.random() < SettingsManager.config.getDouble(
            "dropConcWeapon.ratePerBlock")) {
            Inventory.addLevel(player, 2, 1);
            Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
                + SettingsManager.lang.getString("Item.get")
                + SettingsManager.lang.getString("Item.2"), player);
        }
    }


    /**
     * Determines whatever it drops concentrated armor stone or not.
     * 
     * @param player
     */
    public void randomDropConcArmor(Player player) {
        if (Math.random() < SettingsManager.config.getDouble(
            "dropConcWeapon.ratePerBlock")) {
            Inventory.addLevel(player, 3, 1);
            Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
                + SettingsManager.lang.getString("Item.get")
                + SettingsManager.lang.getString("Item.3"), player);
        }
    }
}
