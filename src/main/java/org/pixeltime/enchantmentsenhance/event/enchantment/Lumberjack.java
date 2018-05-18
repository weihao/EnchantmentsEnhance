package org.pixeltime.enchantmentsenhance.event.enchantment;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

import java.util.*;

public class Lumberjack implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBreak(final BlockBreakEvent blockBreakEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "lumberjack"));
        if (blockBreakEvent.isCancelled()) {
            return;
        }
        final Player player = blockBreakEvent.getPlayer();
        if (player.getItemInHand() != null && player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasLore() && player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && blockBreakEvent.getBlock().getType() == Material.LOG) {
            final ArrayList<Material> list = new ArrayList<Material>();
            list.add(Material.LOG);
            list.add(Material.LEAVES);
            final Iterator<Block> iterator = this.getTree(blockBreakEvent.getBlock(), list).iterator();
            while (iterator.hasNext()) {
                iterator.next().breakNaturally();
            }
        }
    }

    private Set<Block> getNearbyBlocks(final Block block, final List<Material> list, final HashSet<Block> set) {
        for (int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                for (int k = -1; k < 2; ++k) {
                    final Block block2 = block.getLocation().clone().add((double) i, (double) j, (double) k).getBlock();
                    if (block2 != null && !set.contains(block2) && list.contains(block2.getType())) {
                        set.add(block2);
                        set.addAll(this.getNearbyBlocks(block2, list, set));
                    }
                }
            }
        }
        return set;
    }

    public Set<Block> getTree(final Block block, final List<Material> list) {
        return this.getNearbyBlocks(block, list, new HashSet<Block>());
    }
}
