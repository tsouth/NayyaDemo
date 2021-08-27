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

public class RemoteDriverUtility {
    private final String seleniumHost = System.getenv("SELENIUM_HOST");

    public RemoteDriverUtility() {}

    public WebDriver createRemoteDriver(Map<String, String> executionEnvironment) {
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

    private MindrCapabilities getBrowserCapabilities(MindrCapabilities capabilities, String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            return capabilities.chrome();
        } else if (browser.equalsIgnoreCase("firefox")) {
            return capabilities.firefox();
        } else if (browser.equalsIgnoreCase("edge")) {
            return capabilities.edge();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private MindrCapabilities getDesktopCapabilities(MindrCapabilities capabilities, String os) {
        capabilities = capabilities.desktop();

        if (os.equalsIgnoreCase("windows")) {
            return capabilities.windows();
        } else {
            throw new IllegalArgumentException("Unsupported operating system: " + os);
        }
    }

    private URL getSeleniumURL() {
        String seleniumHost;
        seleniumHost = this.seleniumHost;

        URL seleniumHostURL;
        try {
            seleniumHostURL = new URL(seleniumHost);
        } catch (MalformedURLException e) {
            throw new TestException("Unable to create url from " + seleniumHost);
        }

        return seleniumHostURL;
    }
}
