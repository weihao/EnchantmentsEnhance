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

import org.bukkit.Bukkit
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.util.ClassGetter
import java.util.*

object PackageManager {

    var disabled = SettingsManager.config.getStringList("disabledEnchantments")
    var enabled: MutableList<EnchantmentListener> = ArrayList()

    fun initializeAll() {
        for (enchClass in ClassGetter.getClassesForPackage(Main.getMain(), "org.pixeltime.enchantmentsenhance.event.enchantment")) {
            if (EnchantmentListener::class.java.isAssignableFrom(enchClass) && !isDisabled(enchClass.simpleName)) {
                try {
                    val enchantmentListener = enchClass.newInstance() as EnchantmentListener
                    enchantmentListener.addLang()
                    enchantmentListener.addDesc()
                    Bukkit.getPluginManager().registerEvents(enchantmentListener, Main.getMain())
                    enabled.add(enchantmentListener)
                    SettingsManager.lang.options().copyDefaults(true)
                    SettingsManager.saveLang()
                } catch (e: InstantiationException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }

            }
        }
    }

    fun isDisabled(ench: String): Boolean {
        for (s in disabled) {
            if (s.equals(ench, ignoreCase = true)) {
                return true
            }
        }
        return false
    }
}
