package com.mindr.utilities.driver;

import com.mindr.utilities.capabilities.MindrCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.TestException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class RemoteDriverUtility implements DriverUtility {
    private final String seleniumHost = System.getProperty("remote.browser.url", "http://localhost:4444");

    public RemoteDriverUtility() {
    }

    public WebDriver createDriver(Map<String, String> executionEnvironment) {
        RemoteWebDriver remoteWebDriver = null;
        do {
            try {
                remoteWebDriver = new RemoteWebDriver(getSeleniumURL(), getCapabilities(executionEnvironment));
            } catch (UnreachableBrowserException e) {
                System.err.println("UnreachableBrowserException: " + e.getMessage());
            }
        } while (remoteWebDriver == null);
        remoteWebDriver.setFileDetector(new LocalFileDetector());

        return remoteWebDriver;
    }

    private DesiredCapabilities getCapabilities(Map<String, String> executionEnvironment) {
        MindrCapabilities capabilities = new MindrCapabilities().remote();

        return capabilities.get();
    }

    private URL getSeleniumURL() {
        String seleniumHost;
        seleniumHost = this.seleniumHost;

        URL seleniumHostURL;
        try {
            seleniumHostURL = new URL(seleniumHost + "/wd/hub");
        } catch (MalformedURLException e) {
            throw new TestException("Unable to create url from " + seleniumHost);
        }

        return seleniumHostURL;
    }
}
