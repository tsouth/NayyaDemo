package com.mindr.utilities.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class LocalDriverUtility {
    private final String chromedriverPath = System.getProperty("user.dir") + "/bin/chromedriver";
    private final String firefoxDriverPath = System.getProperty("user.dir") + "/bin/geckodriver";


    public LocalDriverUtility() {}

    public WebDriver createUpLocalDriver(String executionEnvironment) {
        WebDriver driver;

        if (executionEnvironment.equalsIgnoreCase("CHROME")) {
            driver = setUpLocalChromeDriver();
        } else if (executionEnvironment.equalsIgnoreCase("FIREFOX")) {
            driver = setUpLocalFirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported local browser or device: " + executionEnvironment);
        }

        return driver;
    }

    private WebDriver setUpLocalChromeDriver() {
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        return new ChromeDriver();
    }

    private WebDriver setUpLocalFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("marionette", true);

        return new FirefoxDriver(options);
    }
}
