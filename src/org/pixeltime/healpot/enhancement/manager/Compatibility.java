package org.pixeltime.healpot.enhancement.manager;

import org.bukkit.Bukkit;
import org.pixeltime.healpot.enhancement.manager.modular.Glow;
import org.pixeltime.healpot.enhancement.manager.modular.Glow_1_8_R3;
import org.pixeltime.healpot.enhancement.manager.modular.PlaySound;
import org.pixeltime.healpot.enhancement.manager.modular.PlaySound_1_8_R3;

public class Compatibility {
    public Glow glow;
    public PlaySound playsound;


    /**
     * Finds the right version for item glower.
     * 
     * @return
     */
    public boolean setupGlow() {

        String version;

        try {

            version = Bukkit.getServer().getClass().getPackage().getName()
                .replace(".", ",").split(",")[3];

        }
        catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
            return false;
        }

        Bukkit.getServer().getLogger().info("Your server is running version "
            + version);

        if (version.equals("v1_8_R3")) {
            glow = new Glow_1_8_R3();
        }
        else {
            glow = new Glow_1_8_R3();
        }

        return glow != null;
    }


    /**
     * Finds the right version for play sound.
     * 
     * @return
     */
    public boolean setupSound() {

        String version;

        try {

            version = Bukkit.getServer().getClass().getPackage().getName()
                .replace(".", ",").split(",")[3];

        }
        catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
            return false;
        }
        playsound = new PlaySound_1_8_R3();
        return playsound != null;
    }

}
