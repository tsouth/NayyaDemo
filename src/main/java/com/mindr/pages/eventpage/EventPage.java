package com.mindr.pages.eventpage;

import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import com.mindr.utilities.page.ModularURL;
import com.mindr.utilities.page.PageNavigation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.TestException;

public class EventPage implements BasePage, PageNavigation, ModularURL {
    private final MindrDriver driver;

    private String URL = "/ls/click%s";

    private final By registerButtonLocator = By.xpath("//button[contains(., 'Register')]");
    private final By cancelRegistrationButtonLocator = By.xpath("//button[contains(., 'Cancel Registration')]");
    private final By eventTitleLocator = By.xpath("//h1[contains(text(), 'Selenium Testing Event')]");

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

    @Override
    public void navigateTo() {
        driver.navigateTo(driver.getSendGrindUrl() + URL);
    }

    @Override
    public void modifyURL(Object... urlIds) {
        URL = String.format(URL, urlIds);
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

    public EventPage verifyEventEmailCreated() {
        String eventTitle = driver.wait(ExpectedConditions.visibilityOfElementLocated(eventTitleLocator)).getText();
        Assert.assertTrue(eventTitle.contains("Selenium Testing Event"));

        return PageManager.getInstance().instantiateCurrentPage(EventPage.class);
    }

}
