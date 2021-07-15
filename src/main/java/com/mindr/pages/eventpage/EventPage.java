package com.mindr.pages.eventpage;

import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class EventPage implements BasePage {
    private final MindrDriver driver;

    private final By registerButtonLocator = By.xpath("//button[contains(., 'Register')]");
    private final By cancelRegistrationButtonLocator = By.xpath("//button[contains(., 'Cancel Registration')]");
    private final By eventTitleLocator = By.xpath("//h1[contains(text(), 'Selenium Testing')]");

    public EventPage (WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(eventTitleLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public EventRegistrationConfirmationModal registerForEvent() {
        WebElement registerButton = driver.wait(ExpectedConditions.elementToBeClickable(registerButtonLocator));
        driver.click(registerButton);

        return PageManager.getInstance().instantiateCurrentPage(EventRegistrationConfirmationModal.class);
    }

    public EventRegistrationCancellationModal cancelEventRegistration() {
        WebElement cancelRegistrationButton = driver.wait(ExpectedConditions.elementToBeClickable(cancelRegistrationButtonLocator));
        driver.click(cancelRegistrationButton);

        return PageManager.getInstance().instantiateCurrentPage(EventRegistrationCancellationModal.class);
    }
}
