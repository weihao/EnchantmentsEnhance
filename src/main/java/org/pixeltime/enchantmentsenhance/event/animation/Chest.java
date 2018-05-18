package org.pixeltime.enchantmentsenhance.event.animation;

import org.bukkit.entity.ArmorStand;

public class Chest {
    ArmorStand as;

    public Chest(final ArmorStand armorstand) {
        this.as = armorstand;
    }

    public ArmorStand getArmorStand() {
        return this.as;
    }
}
