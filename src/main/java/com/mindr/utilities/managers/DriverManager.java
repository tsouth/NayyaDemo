package com.mindr.utilities.managers;

import com.mindr.utilities.driver.LocalDriverUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DriverManager {
    private final LocalDriverUtility localDriverUtility = new LocalDriverUtility();
    private final String executionEnvironment = setExecutionEnvironment();
    private final WebDriver driver;

    private enum SUPPORTED_BROWSERS {
        CHROME, FIREFOX, IE
    }

    public DriverManager() {
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        driver = createDriver();
    }

    public void close() {
        if (driver != null && !driver.toString().contains("null")) {
            try {
                driver.quit();
            } catch (WebDriverException ignore) {}
        }
    }

    public void clearCookies() {
        driver.manage().deleteAllCookies();
    }

    public String getSessionID() {
        return ((RemoteWebDriver) driver).getSessionId().toString();
    }

    public final WebDriver getDriver() {
        return driver;
    }

    private WebDriver createDriver() {
        WebDriver driver;

        long timeout = System.currentTimeMillis() + 30000;
        driver = localDriverUtility.createUpLocalDriver(executionEnvironment);
        if (driver == null || driver.toString().contains("null")) {
            throw new WebDriverException("Webdriver Failed to Open");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        if (isSupportedBrowser()) {
            driver.manage().window().maximize();
        }

        return driver;
    }

    public String getExecutionEnvironment() {
        return executionEnvironment;
    }

    public boolean isSupportedBrowser() {
        return isSupportedBrowser(executionEnvironment);
    }

    private boolean isSupportedBrowser(String browser) {
        return Stream.of(SUPPORTED_BROWSERS.values())
                .map(Enum::name)
                .collect(Collectors.toSet())
                .contains(browser);
    }

    private String setExecutionEnvironment() {
        String driver = System.getenv("DRIVER");

        if (driver == null) {
            throw new IllegalArgumentException("No browser or device specified to test with");
        } else if (!isSupportedBrowser(driver.toUpperCase())) {
            throw new IllegalArgumentException(driver + " is not a supported driver");
        }

        return driver;
    }
}
