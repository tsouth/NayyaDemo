package com.fmx.utilities.capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

public class FMXCapabilities {
    private final DesiredCapabilities capabilities = new DesiredCapabilities();

    public FMXCapabilities() {
    }

    public FMXCapabilities local() {
        capabilities.setCapability("browserstack.customSendKeys", 200);

        return this;
    }

    public FMXCapabilities remote(String browser) {
        capabilities.setCapability("browser", browser);
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browser_version", "latest");
        capabilities.setCapability("--disable-dev-shm-usage", true);
        capabilities.setCapability("--whitelisted-ips", true);
        capabilities.setCapability("--verbose", true);

        return this;
    }

    public FMXCapabilities chrome() {
        capabilities.setCapability("browser", "Chrome");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browser_version", "latest");

        return this;
    }

    public FMXCapabilities desktop() {
        capabilities.setCapability("resolution", "1920x1080");

        return this;
    }

    public DesiredCapabilities get() {
        return capabilities;
    }
}
