package com.steno.pages.talkToSalesPages;

import com.steno.utilities.page.BasePage;
import com.steno.utilities.page.StenoDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class SalesFormSubmissionConfirmationPage implements BasePage {
    private final StenoDriver driver;

    private final By thankYouMessageLocator = By.xpath("//h1[contains(text(), 'Thank You for Reaching Out!')]");

    public SalesFormSubmissionConfirmationPage(WebDriver driver) {
        this.driver = new StenoDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(thankYouMessageLocator));
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
                        thankYouMessageLocator), 10);
                break;
            } catch (NoSuchElementException | TimeoutException ignore) {}
        }
    }

}
