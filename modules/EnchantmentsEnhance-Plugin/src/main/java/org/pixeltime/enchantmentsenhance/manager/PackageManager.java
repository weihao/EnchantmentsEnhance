package org.pixeltime.enchantmentsenhance.manager;

import org.bukkit.Bukkit;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener;
import org.pixeltime.enchantmentsenhance.util.ClassGetter;

import java.util.ArrayList;
import java.util.List;

public class PackageManager {

    private static List<String> disabled = SettingsManager.config.getStringList("disabledEnchantments");
    private static List<EnchantmentListener> enabled = new ArrayList<>();

    public static List<String> getDisabled() {
        return disabled;
    }

    public static void setDisabled(List<String> disabled) {
        PackageManager.disabled = disabled;
    }

    public static List<EnchantmentListener> getEnabled() {
        return enabled;
    }

    public static void setEnabled(List<EnchantmentListener> enabled) {
        PackageManager.enabled = enabled;
    }

    public static void initializeAll() {
        for (Class enchClass : ClassGetter.getClassesForPackage(Main.getMain(), "org.pixeltime.enchantmentsenhance.event.enchantment")) {
            if (EnchantmentListener.class.isAssignableFrom(enchClass) && !isDisabled(enchClass.getSimpleName())) {
                try {
                    EnchantmentListener enchantmentListener = (EnchantmentListener) enchClass.newInstance();
                    enchantmentListener.addLang();
                    enchantmentListener.addDesc();
                    Bukkit.getPluginManager().registerEvents(enchantmentListener, Main.getMain());
                    enabled.add(enchantmentListener);
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
        return false;
    }
}
