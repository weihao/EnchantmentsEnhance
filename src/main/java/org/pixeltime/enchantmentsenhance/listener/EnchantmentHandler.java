package org.pixeltime.enchantmentsenhance.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.pixeltime.enchantmentsenhance.manager.EnchantmentsManager;

public class EnchantmentHandler implements Listener {
    EnchantmentsManager plugin;

    @EventHandler
    public void onQuit(final PlayerQuitEvent playerQuitEvent) {
        final Player player = playerQuitEvent.getPlayer();
        if (EnchantmentsManager.bat.contains(player.getName())) {
            EnchantmentsManager.bat.remove(player.getName());
        }
        if (EnchantmentsManager.speed.contains(player.getName())) {
            EnchantmentsManager.speed.remove(player.getName());
        }
        if (EnchantmentsManager.jump.contains(player.getName())) {
            EnchantmentsManager.jump.remove(player.getName());
        }
        if (EnchantmentsManager.strength.contains(player.getName())) {
            EnchantmentsManager.strength.remove(player.getName());
        }
        if (EnchantmentsManager.health.contains(player.getName())) {
            EnchantmentsManager.health.remove(player.getName());
        }

    }

}
