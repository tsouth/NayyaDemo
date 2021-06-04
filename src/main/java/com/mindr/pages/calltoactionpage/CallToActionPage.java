package com.mindr.pages.calltoactionpage;

import com.mindr.pages.eventpage.EventRegistrationCancellationModal;
import com.mindr.pages.eventpage.EventRegistrationConfirmationModal;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.aspectj.weaver.ast.Call;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class CallToActionPage implements BasePage {
    private final MindrDriver driver;

    private final By registerButtonLocator = By.xpath("//button[contains(., 'Register')]");
    private final By cancelRegistrationButtonLocator = By.xpath("//button[contains(., 'Cancel Registration')]");


    public CallToActionPage (WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(registerButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public CallToActionRegistrationConfirmationModal registerForCallToAction() {
        WebElement registerButton = driver.wait(ExpectedConditions.elementToBeClickable(registerButtonLocator));
        driver.click(registerButton);

        return PageManager.getInstance().instantiateCurrentPage(CallToActionRegistrationConfirmationModal.class);
    }

    public CallToActionRegistrationCancellationModal cancelCallToActionRegistration() {
        WebElement cancelRegistrationButton = driver.wait(ExpectedConditions.elementToBeClickable(cancelRegistrationButtonLocator));
        driver.click(cancelRegistrationButton);

        return PageManager.getInstance().instantiateCurrentPage(CallToActionRegistrationCancellationModal.class);
    }
}
