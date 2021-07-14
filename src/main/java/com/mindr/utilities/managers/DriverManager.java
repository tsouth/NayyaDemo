package com.mindr.utilities.managers;

import com.mindr.utilities.driver.LocalDriverUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DriverManager {
    private final LocalDriverUtility localDriverUtility = new LocalDriverUtility();
    private final Map<String, String> executionEnvironment = setExecutionEnvironment();
    private final WebDriver driver;

    private enum SUPPORTED_BROWSERS {
        CHROME, FIREFOX, SAFARI, EDGE
    }

    private enum SUPPORTED_OPERATING_SYSTEMS {
        OSX, WINDOWS
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
        do {
            driver = localDriverUtility.createLocalDriver(executionEnvironment);
        } while (System.currentTimeMillis() < timeout && (driver == null || driver.toString().contains("null")));

        if (driver == null || driver.toString().contains("null")) {
            throw new WebDriverException("Webdriver Failed to Open");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        if (isSupportedOperatingSystem()) {
            driver.manage().window().maximize();
        }

        return driver;
    }

    public String getBrowser() {
        return executionEnvironment.get("browser");
    }

    public String getOperatingSystem() {
        return executionEnvironment.get("os");
    }

    public String getDevice() {
        return executionEnvironment.get("device");
    }

    public boolean isSupportedBrowser() {
        return isSupportedBrowser(executionEnvironment.get("browser"));
    }

    public boolean isSupportedOperatingSystem() {
        return executionEnvironment.containsKey("os");
    }

    private boolean isSupportedBrowser(String browser) {
        return Stream.of(SUPPORTED_BROWSERS.values())
                .map(Enum::name)
                .collect(Collectors.toSet())
                .contains(browser.toUpperCase());
    }

    private boolean isSupportedOperatingSystem(String operatingSystem) {
        return Stream.of(SUPPORTED_OPERATING_SYSTEMS.values())
                .map(Enum::name)
                .collect(Collectors.toSet())
                .contains(operatingSystem.toUpperCase());
    }

    private Map<String, String> setExecutionEnvironment() {
        String driver = System.getenv("DRIVER");
        Map<String, String> driverMap = new HashMap<>();

        if (driver == null) {
            throw new IllegalArgumentException("No browser or device specified to test with");
        } else {
            ArrayList<String> driverSplit = new ArrayList<>(Arrays.asList(driver.split("\\.")));

            String os = null;
            String browser = null;

            for (String driverPart : driverSplit) {
                if (isSupportedOperatingSystem(driverPart)) {
                    os = driverPart;
                } else if (isSupportedBrowser(driverPart)) {
                    browser = driverPart;
                }
            }

            if (browser != null) {
                if (browser.equalsIgnoreCase("edge") &&
                        (os == null || !os.equalsIgnoreCase("windows"))) {
                    throw new IllegalArgumentException(driver + " is not a supported driver setup");
                } else driverMap.put("os", Objects.requireNonNullElse(os, "osx"));
                driverMap.put("browser", browser);
            } else {
                throw new IllegalArgumentException(driver + " is not a supported driver setup");
            }
        }
        return driverMap;
    }
}