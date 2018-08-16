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

package org.pixeltime.enchantmentsenhance.manager;

import org.bukkit.Bukkit;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener;
import org.pixeltime.enchantmentsenhance.util.ClassGetter;

import java.util.List;

public class PackageManager {

    private static List<String> disabled = SettingsManager.config.getStringList("disabledEnchantments");

    public static void initializeAll() {
        for (Class enchClass : ClassGetter.getClassesForPackage(Main.getMain(), "org.pixeltime.enchantmentsenhance.event.enchantment")) {
            if (EnchantmentListener.class.isAssignableFrom(enchClass) && !isDisabled(enchClass.getSimpleName())) {
                try {
                    EnchantmentListener enchantmentListener = (EnchantmentListener) enchClass.newInstance();
                    enchantmentListener.addLang();
                    Bukkit.getPluginManager().registerEvents(enchantmentListener, Main.getMain());
                    SettingsManager.lang.options().copyDefaults(true);
                    SettingsManager.saveLang();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean isDisabled(String ench) {
        for (String s : disabled) {
            if (s.equalsIgnoreCase(ench)) {
                return true;
            }
        }
        if (ench.contains("Wing")) {
            return true;
        }
        return false;
    }
}
