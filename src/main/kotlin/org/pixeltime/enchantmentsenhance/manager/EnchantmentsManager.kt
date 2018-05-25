/*
 *     Copyright (C) 2017-Present HealPotion
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

import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.event.enchantment.*
import org.pixeltime.enchantmentsenhance.listener.ArmorHandler

class EM {
    companion object {
        @JvmStatic
        fun setUp() {
            Main.getMain().server.pluginManager.registerEvents(ArmorHandler(null), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Assassin(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Batvision(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Blessed(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Demonic_Aura(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Dodge(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Hex(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Jump(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Lifesteal(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Speed(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Execute(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Crushing(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Aegis(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Stealth(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Purge(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Divine(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Platemail(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Turmoil(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Spiked(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Flame_Cloak(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Corruption(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Battlecry(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Pyromaniac(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Holy_Smite(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Riftslayer(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Curse(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Eyepatch(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Mischief(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Plunder(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Shadowstep(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Tamer(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Repel(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Swimmer(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Reborn(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Health_Boost(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Endless(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Molten(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Immolation(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Strength(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Horse_Rider(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Petrify(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Thief(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Demon_Siphon(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Web_Trap(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Paralyze(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Rekt(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Reinforced(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Shearer(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Farmer(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Haste(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Lumberjack(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Plow(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Smelt(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Auto_Block(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Feather_Fall(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Suicide(), Main.getMain())
            Main.getMain().server.pluginManager.registerEvents(Launch(), Main.getMain())
        }
    }
}
