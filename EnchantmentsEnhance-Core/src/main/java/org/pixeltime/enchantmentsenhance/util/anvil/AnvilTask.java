/*
 *     Copyright (C) 2017-Present HealPot
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

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

