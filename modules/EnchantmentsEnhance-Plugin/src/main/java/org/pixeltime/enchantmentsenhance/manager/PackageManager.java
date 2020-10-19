package org.pixeltime.enchantmentsenhance.manager;

import org.bukkit.Bukkit;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.event.enchantment.axe.Lumberjack;
import org.pixeltime.enchantmentsenhance.event.enchantment.axe.Pumpking;
import org.pixeltime.enchantmentsenhance.event.enchantment.bow.Boom;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Aegis;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Assassin;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Battlecry;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Corruption;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Crits;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Crushing;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Curse;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Demonic;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Divine;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Dodge;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Endless;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Execute;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Eyepatch;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Factory;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Feather;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Flame;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Frosty;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Hex;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Immolation;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Launch;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Lifesteal;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Mischief;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Paralyze;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Petrify;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Plunder;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Purge;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Pyromaniac;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Reborn;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Rekt;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Repel;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Reversal;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Riftslayer;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Shadowstep;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Siphon;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Smite;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Spiked;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Stealth;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Suicide;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Thief;
import org.pixeltime.enchantmentsenhance.event.enchantment.gear.Turmoil;
import org.pixeltime.enchantmentsenhance.event.enchantment.hoe.Plow;
import org.pixeltime.enchantmentsenhance.event.enchantment.misc.Rider;
import org.pixeltime.enchantmentsenhance.event.enchantment.misc.Tamer;
import org.pixeltime.enchantmentsenhance.event.enchantment.misc.Wings;
import org.pixeltime.enchantmentsenhance.event.enchantment.pickaxe.Explosive;
import org.pixeltime.enchantmentsenhance.event.enchantment.pickaxe.Smelt;
import org.pixeltime.enchantmentsenhance.event.enchantment.pickaxe.Touch;
import org.pixeltime.enchantmentsenhance.event.enchantment.shear.Shearer;
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static Set<Class> getEnchClasses() {
        HashSet<Class> set = new HashSet<>();
        set.add(Rider.class);
        set.add(Wings.class);
        set.add(Tamer.class);
        set.add(Smelt.class);
        set.add(Touch.class);
        set.add(Explosive.class);
        set.add(Boom.class);
        set.add(Plow.class);
        set.add(Spiked.class);
        set.add(Smite.class);
        set.add(Thief.class);
        set.add(Reborn.class);
//        set.add(Cure.class);
        set.add(Divine.class);
        set.add(Aegis.class);
//        set.add(Haste.class);
//        set.add(Phoenix.class);
        set.add(Mischief.class);
        set.add(Petrify.class);
        set.add(Dodge.class);
//        set.add(Vitality.class);
        set.add(Frosty.class);
        set.add(Shadowstep.class);
//        set.add(Molten.class);
        set.add(Feather.class);
        set.add(Demonic.class);
        set.add(Rekt.class);
//        set.add(Shield.class);
        set.add(Repel.class);
        set.add(Corruption.class);
        set.add(Pyromaniac.class);
        set.add(Assassin.class);
        set.add(Crits.class);
        set.add(Hex.class);
        set.add(Crushing.class);
        set.add(Plunder.class);
//        set.add(Strength.class);
        set.add(Battlecry.class);
        set.add(Stealth.class);
//        set.add(Jump.class);
//        set.add(Invisible.class);
//        set.add(Blessed.class);
        set.add(Turmoil.class);
        set.add(Riftslayer.class);
//        set.add(Saturation.class);
        set.add(Flame.class);
        set.add(Lifesteal.class);
        set.add(Execute.class);
        set.add(Paralyze.class);
        set.add(Immolation.class);
//        set.add(Speed.class);
//        set.add(Swimmer.class);
        set.add(Reversal.class);
        set.add(Purge.class);
        set.add(Siphon.class);
        set.add(Launch.class);
        set.add(Eyepatch.class);
//        set.add(Reinforced.class);
        set.add(Suicide.class);
        set.add(Factory.class);
//        set.add(Batvision.class);
        set.add(Curse.class);
        set.add(Endless.class);
        set.add(Shearer.class);
        set.add(Pumpking.class);
        set.add(Lumberjack.class);
        return set;
    }

    public static void initializeAll() {
        for (Class enchClass : getEnchClasses()) {
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
