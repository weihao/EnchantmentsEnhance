package org.pixeltime.enchantmentsenhance.listeners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.Main;

public class PlayerDeathHandler implements Listener {
    private static final Main m = Main.getMain();


    public PlayerDeathHandler() {
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
        UUID uuid = p.getUniqueId();

        File playerFile = new File(m.getDataFolder() + "/Data" + "/Players/"
            + uuid.toString() + ".yml");
        FileConfiguration pFile = YamlConfiguration.loadConfiguration(
            playerFile);

        pFile.set("PlayerName", p.getName());
        if(!e.getDrops().isEmpty() || e.getDrops() != null) {
        	for (int i = 0; i < e.getDrops().size(); i++) {
            	ItemStack stack = (ItemStack)e.getDrops().get(i);
                if (stack.hasItemMeta()){
                	ItemMeta meta = stack.getItemMeta();
                    if (meta.hasLore()) {
                        List<String> lore = meta.getLore();
                        for(String s : lore) {
                        	s = ChatColor.stripColor(s);
                        	if(s.contains(ChatColor.stripColor(SettingsManager.lang.getString(
                                    "Lore.UntradeableLore"))) || s.contains(ChatColor.stripColor(SettingsManager.lang.getString(
                                            "Lore.TradeableLore")))) {
                        		newInventory.add((ItemStack)e.getDrops().get(i));
                        	}
                        }
                    }
                }
            }
        	ItemStack[] newStack = newInventory.toArray(new ItemStack[newInventory.size()]);
        	pFile.set("Items", newStack);
        	try {
        	    pFile.save(playerFile);
        	}catch (IOException e1) {
        	
        	}      	
        	e.getDrops().removeAll(newInventory);
        }       
    }


    /**
     * Returns enhanced item to the player when respawn.
     * 
     * @param e
     */
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();

        UUID uuid = p.getUniqueId();

        File playerFile = new File(m.getDataFolder() + "/Data" + "/Players/"
            + uuid.toString() + ".yml");
        FileConfiguration pFile = YamlConfiguration.loadConfiguration(
            playerFile);
        if (playerFile.exists()) {
            ItemStack[] content = (ItemStack[])((List<?>)pFile.get("Items"))
                .toArray(new ItemStack[0]);
            p.getInventory().addItem(content);

            if (playerFile.delete()) {
                // file delete failed.
                Bukkit.getLogger().log(Level.SEVERE,
                    "Unable to delete a file.");
            }
        }
    }
}
