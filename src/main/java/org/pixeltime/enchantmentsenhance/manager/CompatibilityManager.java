package org.pixeltime.enchantmentsenhance.manager;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.interfaces.GlowItem;
import org.pixeltime.enchantmentsenhance.interfaces.PlaySound;
import org.pixeltime.enchantmentsenhance.interfaces.SpawnFirework;
import org.pixeltime.enchantmentsenhance.manager.modular.GlowItem_Unsafe;
import org.pixeltime.enchantmentsenhance.manager.modular.PlaySound_Safe;
import org.pixeltime.enchantmentsenhance.manager.modular.SpawnFirework_Safe;

public class CompatibilityManager {
    public static GlowItem glow;
    public static PlaySound playsound;
    public static SpawnFirework spawnFirework;
    private static Economy economy;


    /**
     * Finds the right version for item glower.
     *
     * @return
     */
    public boolean setupGlow() {
        /*
         * if (version.equals("v1_8_R3")) {
         * glow = new GlowItem_NBT();
         * }
         * else {
         * glow = new GlowItem_Unsafe();
         * }
         */
        glow = new GlowItem_Unsafe();
        return glow != null;
    }


    /**
     * Finds the right version for play sound.
     *
     * @return
     */
    public boolean setupSound() {
        playsound = new PlaySound_Safe();
        return playsound != null;
    }


    /**
     * Finds the right version to spawn fireworks.
     *
     * @return
     */
    public boolean setupFirework() {
        spawnFirework = new SpawnFirework_Safe();
        return spawnFirework != null;
    }

    /**
     * Setup the Economy.
     *
     * @return
     */
    public boolean setupEconomy() {
        final RegisteredServiceProvider registration = Main.getMain().getServer().getServicesManager().getRegistration((Class) Economy.class);
        if (registration == null) {
            return false;
        }
        CompatibilityManager.economy = (Economy) registration.getProvider();
        return true;
    }


}
