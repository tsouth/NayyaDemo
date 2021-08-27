package com.mindr.utilities.capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

public class MindrCapabilities {
    private final DesiredCapabilities capabilities = new DesiredCapabilities();

    public MindrCapabilities() {
    }

    public MindrCapabilities local() {
        capabilities.setCapability("browserstack.customSendKeys", 200);

        return this;
    }

    public MindrCapabilities remote(String browser) {
        // capabilities.setCapability("headless", "true");
        capabilities.setCapability("browser", browser);
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browser_version", "latest");
        return this;
    }

    public MindrCapabilities chrome() {
        capabilities.setCapability("browser", "Chrome");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browser_version", "latest");

        return this;
    }

    public MindrCapabilities firefox() {
        capabilities.setCapability("browser", "Firefox");
        capabilities.setCapability("browserName", "Firefox");
        capabilities.setCapability("browser_version", "81.0");

        return this;
    }

    public MindrCapabilities edge() {
        capabilities.setCapability("browser", "Edge");
        capabilities.setCapability("browserName", "Edge");
        capabilities.setCapability("browserVersion", "latest");

        return this;
    }

    public MindrCapabilities windows() {
        capabilities.setCapability("os", "Windows");
        capabilities.setCapability("os_version", "10");

        return this;
    }

    public MindrCapabilities desktop() {
        capabilities.setCapability("resolution", "1920x1080");

        return this;
    }

    public DesiredCapabilities get() {
        return capabilities;
    }
}
