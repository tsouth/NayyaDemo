package com.mindr.pages.calltoactionpage;

import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class CallToActionCompletionModal implements BasePage {
    private final MindrDriver driver;

    private final By sendInvitationsButtonLocator = By.xpath("//button[contains(., 'Send Invitations')]");
    private final By closeRegistrationConfirmationModalXLocator = By.xpath("//button[contains(., '×')]");

    public CallToActionCompletionModal(WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(sendInvitationsButtonLocator));
    }

    public CallToActionPage closeRegistrationModal() {
        WebElement closeButton = driver.wait(ExpectedConditions.elementToBeClickable(
                closeRegistrationConfirmationModalXLocator));
        driver.click(closeButton);

        return PageManager.getInstance().instantiateCurrentPage(CallToActionPage.class);
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }
}
