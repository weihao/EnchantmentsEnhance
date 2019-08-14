package org.pixeltime.enchantmentsenhance.manager.modular

import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.FireworkEffect.Type
import org.bukkit.entity.Firework
import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.interfaces.SpawnFirework
import java.util.Random

class SpawnFirework_Safe : SpawnFirework {


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


    override fun launch(
            player: Player,
            fireworkCount: Int,
            fireWorkRounds: Int,
            delay: Int) {
        val delayTicks = java.lang.Long.parseLong(Integer.toString(delay))
        for (i in 0 until fireWorkRounds) {
            Main.getMain().server.scheduler.scheduleSyncDelayedTask(
                    Main.getMain(), { launch(player, fireworkCount) }, delayTicks)
        }
    }

    companion object {

        private val random = Random()
        private val types = arrayOf(Type.BALL, Type.BALL_LARGE, Type.BURST, Type.STAR)
        private val colors = arrayOf(Color.AQUA, Color.BLUE, Color.FUCHSIA, Color.GREEN, Color.LIME, Color.MAROON, Color.NAVY, Color.OLIVE, Color.ORANGE, Color.PURPLE, Color.RED, Color.SILVER, Color.WHITE, Color.TEAL, Color.YELLOW)
    }
}
