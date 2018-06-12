package org.pixeltime.enchantmentsenhance.gui.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.event.blacksmith.SecretBook;
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.List;

public class Valks extends GUIAbstract {

    public Valks(Player player) {
        super(player, 54, SettingsManager.lang.getString("Valks.gui"));
        update();
    }

    @Override
    public void update() {
        getInventory().clear();
        getActions().clear();

        Player player = Bukkit.getPlayer(playerName);
        List<Integer> inv = SecretBook.getStorage().get(playerName);
        for (int i = 0; i < (inv.size() > 54 ? 54 : inv.size()); i++) {
            int level = inv.get(i);
            setItem(Util.getSlot((i % 9) + 1, (i / 9) + 1), CompatibilityManager.glow.addGlow(new ItemBuilder(Material.BOOK, level).setName(
                    SettingsManager.lang.getString("Item.valks") + "+" + level).toItemStack()), () -> {
            });
        }
    }
}
