package com.fmx.pages.requestADemoPages;

import com.fmx.utilities.page.BasePage;
import com.fmx.utilities.page.FMXDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class RequestADemoConfirmationPage implements BasePage {
    private final FMXDriver driver;

    private final By quickCallRequestMessageLocator = By.xpath("//span[@data-test-id='Availability-marketing-title']");

    public RequestADemoConfirmationPage(WebDriver driver) {
        this.driver = new FMXDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(quickCallRequestMessageLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public void verifyAppointmentRequest() {
        while (true) {
            try {
                driver.waitWithTimeout(ExpectedConditions.visibilityOfElementLocated(
                        quickCallRequestMessageLocator), 10);
                break;
            } catch (NoSuchElementException | TimeoutException ignore) {}
        }
    }

}
