package org.pixeltime.enchantmentsenhance.gui.menu.icons;

        import org.bukkit.Material;
        import org.bukkit.inventory.ItemStack;
        import org.pixeltime.enchantmentsenhance.gui.Clickable;
        import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
        import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
        import org.pixeltime.enchantmentsenhance.util.Util;

public class GearIcon extends Clickable {
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.DIAMOND_SWORD)
                .setName("Gear")
                .addLoreLine(SettingsManager.lang.getString("Icon.gear1"))
                .addLoreLine(SettingsManager.lang.getString("Icon.gear2"))
                .toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(1, 1);
    }
}