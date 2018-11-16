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

package org.pixeltime.enchantmentsenhance.manager.modular

import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.entity.Firework
import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.interfaces.SpawnFirework
import java.util.*

class SpawnFirework_Safe : SpawnFirework {

    companion object {
        private val random = Random()
        private val types = arrayOf(
                FireworkEffect.Type.BALL,
                FireworkEffect.Type.BALL_LARGE,
                FireworkEffect.Type.BURST,
                FireworkEffect.Type.STAR
        )
        private val colors = arrayOf(
                Color.AQUA, Color.BLUE, Color.FUCHSIA,
                Color.GREEN, Color.LIME, Color.MAROON,
                Color.NAVY, Color.OLIVE, Color.ORANGE,
                Color.PURPLE, Color.RED, Color.SILVER,
                Color.WHITE, Color.TEAL, Color.YELLOW
        )
    }

    override fun launch(player: Player, fireworkCount: Int) {
        for (i in 0 until fireworkCount) {
            val fw = player.world.spawn(player
                .location, Firework::class.java)
            val fwMeta = fw.fireworkMeta
            fwMeta.addEffect(FireworkEffect.builder().flicker(random
                .nextBoolean()).withColor(colors[random.nextInt(14)]).withFade(
                    colors[random.nextInt(14)]).with(types[random.nextInt(3)])
                .trail(random.nextBoolean()).build())
            fwMeta.power = random.nextInt(3)
            fw.fireworkMeta = fwMeta
        }
    }

    override fun launch(player: Player, fireworkCount: Int, fireWorkRounds: Int, delay: Int) {
        val delayTicks = delay.toLong()
        for (i in 0 until fireWorkRounds) {
            Main.getMain().server.scheduler.scheduleSyncDelayedTask(
                    Main.getMain(), { launch(player, fireworkCount) }, delayTicks)
        }
    }
}
