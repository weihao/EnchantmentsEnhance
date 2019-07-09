package org.pixeltime.enchantmentsenhance.manager;

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

    /**
     * Finds the right version for item glower.
     *
     * @return
     */
    public boolean setupGlow() {
        try {
            glow = new GlowItem_Unsafe();
        } catch (Exception e) {
            e.printStackTrace();
        }
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


}
