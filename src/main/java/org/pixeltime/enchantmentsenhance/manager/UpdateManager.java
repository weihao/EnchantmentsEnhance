/*
 *     Copyright (C) 2017-Present HealPotion
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

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.pixeltime.enchantmentsenhance.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateManager implements Listener {

    public static void versionChecker() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(
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
            } else {
                Main.getMain().getLogger().info(
                        "Enchantments Enhance is UP-TO-DATE");
            }
        } catch (IOException e) {
            Main.getMain().getLogger().warning(
                    "ERROR: Could not make connection to SpigotMC.org");
        }
    }
}
