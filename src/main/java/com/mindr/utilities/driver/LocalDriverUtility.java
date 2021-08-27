package com.mindr.utilities.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.Map;

public class LocalDriverUtility implements DriverUtility {
    private final String chromedriverPath = System.getProperty("user.dir") + "/bin/chromedriver-92";
    private final String firefoxDriverPath = System.getProperty("user.dir") + "/bin/geckodriver.exe";
    private final String ieDriverPath = System.getProperty("user.dir") + "/bin/iedriver.exe";

    public LocalDriverUtility() {
    }

    public WebDriver createDriver(Map<String, String> executionEnvironment) {
        if (executionEnvironment.containsKey("os")) {
            return getDesktopDriver(executionEnvironment.get("browser"));
        } else {
            throw new IllegalArgumentException("Unsupported local device or browser");
        }
    }

    private WebDriver getDesktopDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            return setUpChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            return setUpFirefoxDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
            return setUpIEDriver();
        } else {
            throw new IllegalArgumentException("Unsupported local browser: " + browser);
        }
    }

    private WebDriver setUpChromeDriver() {
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        // options.addArguments("--headless");
        options.addArguments("--disable-gpu");

        return new ChromeDriver(options);
    }

    private WebDriver setUpFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("marionette", true);
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");

        return new FirefoxDriver(options);
    }

    private WebDriver setUpIEDriver() {
        System.setProperty("webdriver.ie.driver", ieDriverPath);
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability("--start-maximized", true);

        return new InternetExplorerDriver(options);
    }
}
