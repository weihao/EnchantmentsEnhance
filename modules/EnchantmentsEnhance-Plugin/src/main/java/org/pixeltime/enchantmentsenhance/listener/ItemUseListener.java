package org.pixeltime.enchantmentsenhance.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

public class ItemUseListener implements Listener {
    private static void consumeItem(ItemStack item, Player player) {
        int afterConsume = Util.getMainHand(player).getAmount() - 1;
        if (afterConsume == 0) {
            player.getInventory().remove(item);
        } else {
            Util.getMainHand(player).setAmount(afterConsume);
        }
    }

    @EventHandler
    public void onItemClick(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (event.getMaterial() == Material.AIR) {
            return;
        }
        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK) || event.getAction().equals(Action.LEFT_CLICK_AIR)) {
            return;
        }
        final ItemStack item = event.getItem();
        if (!ItemManager.getGive(item).isEmpty()) {
            String[] give = ItemManager.getGive(item).split(":");
            if (give.length == 2) {
                int id = Integer.parseInt(give[0]);
                int amount = Integer.parseInt(give[1]);
                if (id < 0) {
                    Main.getApi().addAdvice(player.getName(), amount);
                    Util.sendMessage(SettingsManager.lang.getString("materialize.adviceSucess")
                            .replace("%level%", Integer.toString(amount)), player);
                } else {
                    Main.getApi().addItem(player.getName(), id, amount);
                    Util.sendMessage(SettingsManager.lang.getString("materialize.success")
                            .replace("%amount%", Integer.toString(amount))
                            .replace("%item%", SettingsManager.lang.getString("item." + id)), player);
                }

                // Consume the item.
                try {
                    if (event.getHand().equals(EquipmentSlot.HAND)) {
                        consumeItem(Util.getMainHand(player), player);
                    } else if (event.getHand().equals(EquipmentSlot.OFF_HAND)) {
                        consumeItem(player.getInventory().getItemInOffHand(), player);
                    }
                } catch (NoSuchMethodError ex) {
                    consumeItem(Util.getMainHand(player), player);
                }
                event.setCancelled(true);
            }
        }
    }
}
