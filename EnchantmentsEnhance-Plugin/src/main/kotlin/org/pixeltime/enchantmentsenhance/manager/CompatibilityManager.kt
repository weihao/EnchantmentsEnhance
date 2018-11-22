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

package org.pixeltime.enchantmentsenhance.manager

import org.pixeltime.enchantmentsenhance.compatibility.UnsafeGlow
import org.pixeltime.enchantmentsenhance.interfaces.PlaySound
import org.pixeltime.enchantmentsenhance.interfaces.SpawnFirework
import org.pixeltime.enchantmentsenhance.manager.modular.PlaySound_Safe
import org.pixeltime.enchantmentsenhance.manager.modular.SpawnFirework_Safe

class CompatibilityManager {

    companion object {
        lateinit var glow : UnsafeGlow
        lateinit var playsound: PlaySound
        lateinit var spawnFirework: SpawnFirework
    }

    /**
     * Finds the right version for item glower.
     *
     * @return
     */
    fun setupGlow(): Boolean {
        glow = UnsafeGlow.create()
        return glow != null
    }

    /**
     * Finds the right version for play sound.
     *
     * @return
     */
    fun setupSound(): Boolean {
        playsound = PlaySound_Safe()
        return playsound != null
    }

    /**
     * Finds the right version to spawn fireworks.
     *
     * @return
     */
    fun setupFirework(): Boolean {
        spawnFirework = SpawnFirework_Safe()
        return spawnFirework != null
    }
}
