package ru.geekbrains.config;

import org.junit.Assert;
import org.junit.Test;

public class ConfigFromFileTest {

    public ServerConfig config;

    @Test
    public void testConfigFromFile() {
        config = new ConfigFromFile("../../../server.properties");
        Assert.assertEquals("D:www", config.getWww());
        Assert.assertEquals(8088, config.getPort());
    }
}
