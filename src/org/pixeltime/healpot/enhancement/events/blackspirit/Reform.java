package org.pixeltime.healpot.enhancement.events.blackspirit;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.GUI;
import org.pixeltime.healpot.enhancement.util.Util;

public class Reform extends GUI {
    private static Map<String, ItemStack> itemOnEnhancingSlot =
        new HashMap<String, ItemStack>();


    public Reform() {
        super(27, SettingsManager.lang.getString("Reform.gui.title"));
        setItem(Util.getSlot(5, 3), Util.createButton(DyeColor.BLACK,
            SettingsManager.lang.getString("Reform.reform")), player -> {
                if (itemOnEnhancingSlot.containsKey(player.getDisplayName())) {

                }
            });
    }

}
