package com.mindr.pages.calltoactionpage;

import com.mindr.pages.eventpage.EventPage;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import com.mindr.utilities.page.ModularURL;
import com.mindr.utilities.page.PageNavigation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class CallToActionPage implements BasePage, PageNavigation, ModularURL {
    private final MindrDriver driver;

    private String URL = "/ls/click%s";

    private final By registerButtonLocator = By.xpath("//button[contains(., 'Register')]");
    private final By cancelRegistrationButtonLocator = By.xpath("//button[contains(., 'Cancel Registration')]");
    private final By callToActionTitleLocator = By.xpath("//h1[contains(text(), 'Selenium Testing')]");

    public CallToActionPage (WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(callToActionTitleLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    @Override
    public void navigateTo() {
        driver.navigateTo(driver.getSendGrindUrl() + URL);
    }

    @Override
    public void modifyURL(Object... urlIds) {
        URL = String.format(URL, urlIds);
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

    public CallToActionPage verifyCallToActionEmailCreated() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(callToActionTitleLocator));

        return PageManager.getInstance().instantiateCurrentPage(CallToActionPage.class);
    }

}
