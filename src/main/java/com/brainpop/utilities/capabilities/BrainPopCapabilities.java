package com.brainpop.utilities.capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

public class BrainPopCapabilities {
    private final DesiredCapabilities capabilities = new DesiredCapabilities();

    public BrainPopCapabilities() {
    }

    public BrainPopCapabilities local() {
        capabilities.setCapability("browserstack.customSendKeys", 200);

        return this;
    }

    public BrainPopCapabilities remote(String browser) {
        capabilities.setCapability("browser", browser);
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browser_version", "latest");
        capabilities.setCapability("--disable-dev-shm-usage", true);
        capabilities.setCapability("--whitelisted-ips", true);
        capabilities.setCapability("--verbose", true);

        return this;
    }

    public BrainPopCapabilities chrome() {
        capabilities.setCapability("browser", "Chrome");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browser_version", "latest");

        return this;
    }

    public BrainPopCapabilities desktop() {
        capabilities.setCapability("resolution", "1920x1080");

        return this;
    }

    public DesiredCapabilities get() {
        return capabilities;
    }
}
