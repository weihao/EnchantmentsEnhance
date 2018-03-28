package org.pixeltime.enchantmentsenhance;

import java.io.File;
import org.bukkit.Server;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

@SuppressWarnings("deprecation")
final class MockPlugin extends JavaPlugin {
    public MockPlugin(Server server) {
        super(new JavaPluginLoader(server), new PluginDescriptionFile(
            "Powermock Plugin", "1.0", MockPlugin.class.getCanonicalName()),
            new File("."), new File("."));
        setEnabled(true);
    }
}
