package com.mindr.utilities.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Map;


public class LocalDriverUtility {
    private final String chromedriverPath = System.getProperty("user.dir") + "/bin/chromedriver.exe";
    private final String firefoxDriverPath = System.getProperty("user.dir") + "/bin/geckodriver.exe";
    
    public LocalDriverUtility() {}

    public WebDriver createLocalDriver(Map<String, String> executionEnvironment) {
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
        } else {
            throw new IllegalArgumentException("Unsupported local browser: " + browser);
        }
    }

    private WebDriver setUpChromeDriver() {
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
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
}
