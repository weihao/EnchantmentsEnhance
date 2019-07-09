/*
 *     Copyright (C) 2017-Present 25 (https://github.com/25)
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

package org.pixeltime.enchantmentsenhance.manager.modular;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.interfaces.SpawnFirework;

import java.util.Random;

public class SpawnFirework_Safe implements SpawnFirework {

    private static final Random random = new Random();
    private static final Type[] types = {Type.BALL, Type.BALL_LARGE,
            Type.BURST, Type.STAR};
    private static final Color[] colors = {Color.AQUA, Color.BLUE,
            Color.FUCHSIA, Color.GREEN, Color.LIME, Color.MAROON, Color.NAVY,
            Color.OLIVE, Color.ORANGE, Color.PURPLE, Color.RED, Color.SILVER,
            Color.WHITE, Color.TEAL, Color.YELLOW};


    public void launch(Player player, int fireworkCount) {
        for (int i = 0; i < fireworkCount; i++) {
            Firework fw = player.getWorld().spawn(player
                    .getLocation(), Firework.class);
            FireworkMeta fwMeta = fw.getFireworkMeta();
            fwMeta.addEffect(FireworkEffect.builder().flicker(random
                    .nextBoolean()).withColor(colors[random.nextInt(14)]).withFade(
                    colors[random.nextInt(14)]).with(types[random.nextInt(3)])
                    .trail(random.nextBoolean()).build());
            fwMeta.setPower(random.nextInt(3));
            fw.setFireworkMeta(fwMeta);
        }
    }


    public void launch(
            final Player player,
            final int fireworkCount,
            int fireWorkRounds,
            int delay) {
        long delayTicks = Long.parseLong(Integer.toString(delay));
        for (int i = 0; i < fireWorkRounds; i++) {
            Main.getMain().getServer().getScheduler().scheduleSyncDelayedTask(
                    Main.getMain(), new Runnable() {
                        public void run() {
                            launch(player, fireworkCount);
                        }
                    }, delayTicks);
        }
    }
}
