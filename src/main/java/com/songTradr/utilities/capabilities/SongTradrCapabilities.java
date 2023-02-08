package com.songTradr.utilities.capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

public class SongTradrCapabilities {
    private final DesiredCapabilities capabilities = new DesiredCapabilities();

    public SongTradrCapabilities() {
    }

    public SongTradrCapabilities local() {
        capabilities.setCapability("browserstack.customSendKeys", 200);

        return this;
    }

    public SongTradrCapabilities remote(String browser) {
        capabilities.setCapability("browser", browser);
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browser_version", "latest");
        capabilities.setCapability("--disable-dev-shm-usage", true);
        capabilities.setCapability("--whitelisted-ips", true);
        capabilities.setCapability("--verbose", true);

        return this;
    }

    public SongTradrCapabilities chrome() {
        capabilities.setCapability("browser", "Chrome");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browser_version", "latest");

        return this;
    }

    public SongTradrCapabilities firefox() {
        capabilities.setCapability("browser", "Firefox");
        capabilities.setCapability("browserName", "Firefox");
        capabilities.setCapability("browser_version", "81.0");

        return this;
    }

    public SongTradrCapabilities edge() {
        capabilities.setCapability("browser", "Edge");
        capabilities.setCapability("browserName", "Edge");
        capabilities.setCapability("browserVersion", "latest");

        return this;
    }

    public SongTradrCapabilities windows() {
        capabilities.setCapability("os", "Windows");
        capabilities.setCapability("os_version", "10");

        return this;
    }

    public SongTradrCapabilities desktop() {
        capabilities.setCapability("resolution", "1920x1080");

        return this;
    }

    public DesiredCapabilities get() {
        return capabilities;
    }
}
