package org.pixeltime.enchantmentsenhance.events.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Eyepatch implements Listener {
    @EventHandler
    public void onMove(final PlayerMoveEvent playerMoveEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "eyepatch"));
        final Player player = playerMoveEvent.getPlayer();
        try {
            ItemStack[] armorContents;
            for (int length = (armorContents = player.getInventory().getArmorContents()).length, i = 0; i < length; ++i) {
                final ItemStack itemStack = armorContents[i];
                if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && player.hasPotionEffect(PotionEffectType.BLINDNESS)) {
                    player.removePotionEffect(PotionEffectType.BLINDNESS);
                }
            }
        } catch (Exception ex) {
        }
    }
}
