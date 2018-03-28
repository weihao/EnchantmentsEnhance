package org.pixeltime.enchantmentsenhance;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.junit.Assert.assertNotNull;

/**
 * 
 * @author HealPot
 * @version Mar 27, 2018
 *
 */
@PrepareForTest({ JavaPlugin.class })
public class MainTest extends PowerMockTestCase {
    private static JavaPlugin plugin;
    private static Server server;


    @BeforeMethod(
        description = "Mock")
    public void tinker() throws Exception {
        PowerMockito.mockStatic(JavaPlugin.class);

        server = new MockServer();
        plugin = new MockPlugin(server);
        PowerMockito.when(JavaPlugin.getProvidingPlugin(Matchers.eq(
            Main.class))).thenReturn(plugin);
    }


    @Test
    public void testMain() {
        assertNotNull(plugin);
        assertNotNull(server);
    }
}
