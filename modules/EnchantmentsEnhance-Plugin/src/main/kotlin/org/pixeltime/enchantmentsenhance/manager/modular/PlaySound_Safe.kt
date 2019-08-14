package org.pixeltime.enchantmentsenhance.manager.modular

import com.lgou2w.ldk.bukkit.compatibility.Sounds
import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.interfaces.PlaySound

class PlaySound_Safe : PlaySound {
    override fun playSound(p: Player, type: String) {
        val playingType = Types.valueOf(type.toUpperCase())

        when (playingType) {
            PlaySound_Safe.Types.SUCCESS -> p.playSound(p.location, Sounds.NOTE_PLING.toBukkit(),
                    1.0f, 2.0f)
            PlaySound_Safe.Types.FAILED -> p.playSound(p.location, Sounds.ANVIL_BREAK.toBukkit(),
                    1.0f, 2.0f)
            PlaySound_Safe.Types.DOWNGRADED -> p.playSound(p.location, Sounds.EXPLODE.toBukkit(), 1.0f,
                    2.0f)
            else -> p.playSound(p.location, Sounds.ANVIL_USE.toBukkit(),
                    1.0f, 2.0f)
        }
    }


    enum class Types {
        SUCCESS, FAILED, DOWNGRADED
    }
}
