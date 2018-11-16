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

import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.interfaces.PlaySound
import org.pixeltime.enchantmentsenhance.util.Sounds

class PlaySound_Safe : PlaySound {

    override fun playSound(p: Player, type: String) {
        val playingType = Types.valueOf(type.toUpperCase())
        when (playingType) {
            PlaySound_Safe.Types.SUCCESS -> p.playSound(p.location, Sounds.NOTE_PLING.bukkitSound(), 1.0f, 2.0f)
            PlaySound_Safe.Types.FAILED -> p.playSound(p.location, Sounds.ANVIL_BREAK.bukkitSound(), 1.0f, 2.0f)
            PlaySound_Safe.Types.DOWNGRADED -> p.playSound(p.location, Sounds.EXPLODE.bukkitSound(), 1.0f, 2.0f)
        }
    }

    enum class Types {
        SUCCESS, FAILED, DOWNGRADED
    }
}
