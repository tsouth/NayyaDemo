package com.brainpop.pages.topics.healthAndSEL.DNA;

import com.brainpop.utilities.page.BasePage;
import com.brainpop.utilities.page.BrainPopDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class RelatedReadingPage implements BasePage {
    private final BrainPopDriver driver;

    private final By trivaTabTitleLocator = By.xpath("//a[@title='Trivia']");

    public RelatedReadingPage(WebDriver driver) {
        this.driver = new BrainPopDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {driver.wait(ExpectedConditions.visibilityOfElementLocated(trivaTabTitleLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

}
