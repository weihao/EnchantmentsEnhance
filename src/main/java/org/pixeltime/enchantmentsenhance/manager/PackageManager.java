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
        // Generate English Enchantments Wiki.

//        System.out.println("| Enchantments ID| Effect|");
//        System.out.println("| :---:| :---------|");
//        for (Class enchClass : ClassGetter.getClassesForPackage(Main.getMain(), "org.pixeltime.enchantmentsenhance.event.enchantment")) {
//            if (EnchantmentListener.class.isAssignableFrom(enchClass)) {
//                try {
//                    EnchantmentListener enchantmentListener = (EnchantmentListener) enchClass.newInstance();
//                    System.out.println("|" + enchantmentListener.getClass().getSimpleName() + "|" + enchantmentListener.desc()[0] + "|");
//                } catch (InstantiationException | IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        }


        // Generate Chinese Enchantments Wiki.
//        System.out.println("| 附魔ID| 中文名称| 效果|");
//        System.out.println("| :---:|:---:| :--------------------------:|");
//        for (Class enchClass : ClassGetter.getClassesForPackage(Main.getMain(), "org.pixeltime.enchantmentsenhance.event.enchantment")) {
//            if (EnchantmentListener.class.isAssignableFrom(enchClass)) {
//                try {
//                    EnchantmentListener enchantmentListener = (EnchantmentListener) enchClass.newInstance();
//                    System.out.println("|" + enchantmentListener.getClass().getSimpleName() + "|" + enchantmentListener.lang()[0] + "|"+enchantmentListener.desc()[1] + "|");
//                } catch (InstantiationException | IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}
