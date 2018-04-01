package org.pixeltime.enchantmentsenhance.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.bukkit.event.Listener;
import org.pixeltime.enchantmentsenhance.Main;
import net.md_5.bungee.api.ChatColor;

public class UpdateManager implements Listener {

    public static void versionChecker() {
        try {
            HttpURLConnection connection = (HttpURLConnection)new URL(
                "https://api.spigotmc.org/legacy/update.php?resource=51635")
                    .openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.getOutputStream().write(("GET").getBytes("UTF-8"));
            String version = new BufferedReader(new InputStreamReader(connection
                .getInputStream())).readLine();
            if (!version.equals(Main.class.getPackage()
                .getImplementationVersion())) {
                for (int i = 0; i < 10; i++) {
                    Main.getMain().getLogger().warning(
                        ChatColor.RED + "EnchantmentsEnhance is OUTDATED!");
                }   
            }
            else {
                Main.getMain().getLogger().info(
                    "Enchantments Enhance is UP-TO-DATE");
            }
        }
        catch (IOException e) {
            Main.getMain().getLogger().warning(
                "ERROR: Could not make connection to SpigotMC.org");
        }
    }
}
