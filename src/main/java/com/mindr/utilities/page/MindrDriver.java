package com.mindr.utilities.page;

import com.mindr.utilities.logger.Logger;
import com.mindr.utilities.managers.PageManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MindrDriver {
    private final String reactTriggerChangePath = System.getProperty("user.dir")
            + "/src/main/resources/javascript/reach-trigger-change.min.js";
    private final WebDriver driver;
    private final Logger log = new Logger(getClass().getName());

    public MindrDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void click(WebElement button) {
        if (PageManager.getInstance().getBrowser().equalsIgnoreCase("ie")
                || PageManager.getInstance().getBrowser().equalsIgnoreCase("safari")) {
            executeScript("arguments[0].click();", button);
        } else {
            button.click();
        }
    }

    public Object executeScript(String script, Object... elements) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        return javascriptExecutor.executeScript(script, elements);
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public String getMindrUrl() {
        return "https://staging.mindrconnect.com";
    }

    public String getSendGrindUrl() {
        return "https://u19402187.ct.sendgrid.net";
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }

    public void moveToElement(WebElement element) {
        new Actions(driver).moveToElement(element);
    }

    public void navigateTo(String URL) {
        driver.navigate().to(URL);
    }

    public void setText(WebElement textField, String text) {
        textField.clear();
        if (PageManager.getInstance().getBrowser().equalsIgnoreCase("ie")) {
            executeScript("arguments[0].value='" + text + "';", textField);
            if (!((boolean) executeScript("return typeof reactTriggerChange === 'function';"))) {
                Scanner scanner;
                try {
                    scanner = new Scanner(new FileInputStream(reactTriggerChangePath));
                } catch (FileNotFoundException e) {
                    throw new TestException(reactTriggerChangePath + " was not found");
                }
                StringBuilder inject = new StringBuilder();
                while (scanner.hasNext()) {
                    String[] temp = scanner.next().split("\r\n");
                    for (String s : temp) {
                        inject.append(s);
                        inject.append(' ');
                    }
                }
                executeScript(inject.toString());
            }
            executeScript("reactTriggerChange(arguments[0]);", textField);
        } else {
            textField.sendKeys(text);
        }
    }

    public void switchTabs(int index) {
        long timeout = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < timeout) {
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            if (tabs.size() >= index + 1) {
                driver.switchTo().window(tabs.get(index));
                return;
            }
        }
        throw new TestException("Requested tab was never opened");
    }

    public <T> T wait(ExpectedCondition<T> expectedCondition) {
        return waitWithTimeout(expectedCondition, 15);
    }

    public <T> T waitWithTimeout(ExpectedCondition<T> expectedCondition, int durationInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds)).until(expectedCondition);
    }

    public boolean pageLoaded() {
        try {
            wait(driver -> executeScript("return document.readyState").equals("complete"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean pageLoadedWithRefresh() {
        for (int i = 0; i < 3; i++) {
            try {
                wait(driver -> executeScript("return document.readyState").equals("complete"));
                return true;
            } catch (TimeoutException e) {
                log.warning("Failed to load page, refreshing...");
                driver.navigate().refresh();
            }
        }
        return false;
    }

    public void zoom(int zoom) {
        executeScript(String.format("document.body.style.zoom='%d%%'", zoom));
    }
}