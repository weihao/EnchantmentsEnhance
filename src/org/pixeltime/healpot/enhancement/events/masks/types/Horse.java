package org.pixeltime.healpot.enhancement.events.masks.types;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Horse {

    public void onArmorWear(InventoryClickEvent e) {
        if (e.getSlotType().equals(SlotType.ARMOR)) {
            if (e.getCurrentItem().getType().equals(
                Material.DIAMOND_CHESTPLATE)) {
                e.getWhoClicked().addPotionEffect(new PotionEffect(
                    PotionEffectType.SPEED, 20, 1, true, false));
            }
        }
    }
}
