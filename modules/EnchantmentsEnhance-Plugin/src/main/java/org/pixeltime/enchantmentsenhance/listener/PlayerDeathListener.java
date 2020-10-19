package org.pixeltime.enchantmentsenhance.listener;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDeathListener implements Listener {
    private static final Main m = Main.getMain();


    public PlayerDeathListener() {
    }


    /**
     * Prevents enhanced item dropped from death.
     *
     * @param e
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        List<ItemStack> newInventory = new ArrayList<ItemStack>();
        File playerFile = new File(m.getDataFolder() + "/death/" + p.getName() + ".yml");
        FileConfiguration pFile = YamlConfiguration.loadConfiguration(playerFile);
        pFile.set("PlayerName", p.getName());
        if (e.getDrops() != null || !e.getDrops().isEmpty()) {
            for (int i = 0; i < e.getDrops().size(); i++) {
                ItemStack stack = e.getDrops().get(i);
                if (ItemManager.getSoulbound(stack)) {
                    newInventory.add(e.getDrops().get(i));
                }
            }
            ItemStack[] newStack = newInventory.toArray(new ItemStack[newInventory.size()]);
            pFile.set("Items", newStack);
            try {
                pFile.save(playerFile);
            } catch (IOException e1) {

            }
            e.getDrops().removeAll(newInventory);
        }
    }


    /**
     * Returns enhanced item to the player when respawn.
     *
     * @param e
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();

        File playerFile = new File(m.getDataFolder() + "/death/" + p.getName() + ".yml");
        FileConfiguration pFile = YamlConfiguration.loadConfiguration(
                playerFile);
        if (playerFile.exists()) {
            ItemStack[] content = ((List<?>) pFile.get("Items"))
                    .toArray(new ItemStack[0]);
            p.getInventory().addItem(content);

            if (playerFile.delete()) {
                // Delete a file.
            }
        }
    }
}
