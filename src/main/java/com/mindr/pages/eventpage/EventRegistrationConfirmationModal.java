package com.mindr.pages.eventpage;

import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class EventRegistrationConfirmationModal implements BasePage {
    private final MindrDriver driver;

    private final By submitQuestionsButtonLocator = By.xpath("//button[contains(., 'Submit Questions')]");
    private final By noQuestionsButtonLocator = By.xpath("//button[contains(., 'NO QUESTIONS')]");

    public EventRegistrationConfirmationModal(WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(submitQuestionsButtonLocator));
    }

    public EventPage submitNoQuestions() {
        WebElement noQuestionsButton = driver.wait(ExpectedConditions.visibilityOfElementLocated(noQuestionsButtonLocator));
        driver.click(noQuestionsButton);
        return PageManager.getInstance().instantiateCurrentPage(EventPage.class);
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }
}
