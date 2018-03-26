package api;

import org.bukkit.Bukkit;
import org.pixeltime.healpot.enhancement.Main;

public class EnchantmentsEnhanceAPI {

    private Main API = (Main)Bukkit.getServer().getPluginManager().getPlugin(
        "EnchantmentsEnhance");


    public EnchantmentsEnhanceAPI() {

    }

    public Main getAPI() {
        return API;
    }
}
