package org.pixeltime.enchantmentsenhance.util.enums;

import org.bukkit.inventory.ItemStack;

public enum ArmorType {
    HELMET("HELMET", 0, 5),
    CHESTPLATE("CHESTPLATE", 1, 6),
    LEGGINGS("LEGGINGS", 2, 7),
    BOOTS("BOOTS", 3, 8);

    private final int slot;

    private ArmorType(final String s, final int n, final int slot) {
        this.slot = slot;
    }

    public static final ArmorType matchType(final ItemStack itemStack) {
        if (itemStack == null) {
            return null;
        }
        switch (itemStack.getType()) {
            case LEATHER_HELMET:
            case CHAINMAIL_HELMET:
            case IRON_HELMET:
            case DIAMOND_HELMET:
            case GOLD_HELMET: {
                return ArmorType.HELMET;
            }
            case LEATHER_CHESTPLATE:
            case CHAINMAIL_CHESTPLATE:
            case IRON_CHESTPLATE:
            case DIAMOND_CHESTPLATE:
            case GOLD_CHESTPLATE: {
                return ArmorType.CHESTPLATE;
            }
            case LEATHER_LEGGINGS:
            case CHAINMAIL_LEGGINGS:
            case IRON_LEGGINGS:
            case DIAMOND_LEGGINGS:
            case GOLD_LEGGINGS: {
                return ArmorType.LEGGINGS;
            }
            case LEATHER_BOOTS:
            case CHAINMAIL_BOOTS:
            case IRON_BOOTS:
            case DIAMOND_BOOTS:
            case GOLD_BOOTS: {
                return ArmorType.BOOTS;
            }
            default: {
                return null;
            }
        }
    }

    public int getSlot() {
        return this.slot;
    }
}
