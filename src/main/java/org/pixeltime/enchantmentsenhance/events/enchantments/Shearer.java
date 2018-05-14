package org.pixeltime.enchantmentsenhance.events.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Shearer implements Listener {
    @EventHandler
    public void onInteract(final PlayerInteractEvent playerInteractEvent) {
        final Player player = playerInteractEvent.getPlayer();
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "shearer"));
        if (playerInteractEvent.getAction() == Action.LEFT_CLICK_AIR && player.getItemInHand() != null && player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasLore() && player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
            final int int1 = SettingsManager.enchant.getInt("shearer.level_I.radius");
            for (final Entity entity : player.getNearbyEntities((double) int1, (double) int1, (double) int1)) {
                if (entity instanceof Sheep) {
                    final Sheep sheep = (Sheep) entity;
                    if (sheep.isSheared()) {
                        continue;
                    }
                    sheep.setSheared(true);
                    sheep.getWorld().dropItem(sheep.getLocation(), new ItemStack(Material.WOOL, 1, (short) sheep.getColor().getWoolData()));
                }
            }
        }
    }
}
