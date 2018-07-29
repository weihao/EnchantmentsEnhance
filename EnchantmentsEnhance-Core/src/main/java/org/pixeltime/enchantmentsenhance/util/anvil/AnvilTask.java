package org.pixeltime.enchantmentsenhance.util.anvil;

import org.bukkit.entity.Player;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.pixeltime.enchantmentsenhance.Main;

import java.util.HashMap;

public class AnvilTask extends BukkitRunnable {
    private static HashMap<AnvilInventory, AnvilTask> anvilTasks;

    static {
        AnvilTask.anvilTasks = new HashMap<>();
    }

    private AnvilInventory inv;
    private Player player;

    public AnvilTask(final AnvilInventory inv, final Player player) {
        this.inv = inv;
        this.player = player;
        AnvilTask.anvilTasks.put(inv, this);
        this.runTaskTimer(Main.getMain(), 1L, 3L);
    }

    public static AnvilTask getTask(final AnvilInventory inv) {
        return AnvilTask.anvilTasks.get(inv);
    }

    public void run() {
        if (this.inv.getViewers().size() == 0) {
            this.cancel();
        }
        ColorHandler.getTranslatedItem(this.player, this.inv, this);
    }
}

