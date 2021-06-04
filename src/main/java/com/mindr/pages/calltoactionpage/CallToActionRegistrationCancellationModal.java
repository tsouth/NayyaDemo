package com.mindr.pages.calltoactionpage;

import com.mindr.pages.eventpage.EventPage;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class CallToActionRegistrationCancellationModal implements BasePage {
    private final MindrDriver driver;

    private final By leaveButtonLocator = By.xpath("//button[contains(., 'Leave')]");
    private final By registrationCancelledConfirmationBanner = By.xpath("//div[contains(., 'cancelled')]");

    public CallToActionRegistrationCancellationModal(WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(leaveButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public CallToActionPage confirmLeave() {
        WebElement leaveButton = driver.findElement(leaveButtonLocator);
        driver.click(leaveButton);
        driver.wait(ExpectedConditions.visibilityOfElementLocated(registrationCancelledConfirmationBanner));

        return PageManager.getInstance().instantiateCurrentPage(CallToActionPage.class);
    }

}
