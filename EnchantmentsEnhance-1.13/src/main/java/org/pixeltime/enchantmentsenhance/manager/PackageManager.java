package org.pixeltime.enchantmentsenhance.manager;

import org.bukkit.Bukkit;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener;
import org.pixeltime.enchantmentsenhance.util.ClassGetter;

public class PackageManager {
    public static void initializeAll() {
        for (Class enchClass : ClassGetter.getClassesForPackage(Main.getMain(), "org.pixeltime.enchantmentsenhance.event.enchantment")) {
            if (EnchantmentListener.class.isAssignableFrom(enchClass) && !SettingsManager.config.getStringList("disabledEnchantments").contains(enchClass.getSimpleName())) {
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
}
