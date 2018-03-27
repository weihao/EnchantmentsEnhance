package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.pixeltime.healpot.enhancement.Main;
import org.pixeltime.healpot.enhancement.util.Broadcast;

public class ConfigTest {
    private static final Logger LOG = Logger.getLogger("ConfigTest");

    private Broadcast bc;


    @Before
    public void setUp()
        throws FileNotFoundException,
        IOException,
        InvalidConfigurationException {
        Main exPl = Mockito.mock(Main.class);

        File configfile = new File(getClass().getResource("/config.yml")
            .getPath());
        LOG.info("Using config file [" + configfile.getAbsolutePath() + "].");
        YamlConfiguration configuration = new YamlConfiguration();
        configuration.load(configfile.getAbsolutePath());
        Mockito.doReturn(configuration).when(exPl).getConfig();
        this.bc = new Broadcast();
    }


    @Test
    public void testCreateConfig() {
        Assert.assertNotNull(bc);
    }

}
