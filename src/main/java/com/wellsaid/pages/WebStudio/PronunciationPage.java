package com.wellsaid.pages.WebStudio;

import com.wellsaid.utilities.managers.PageManager;
import com.wellsaid.utilities.page.BasePage;
import com.wellsaid.utilities.page.PageNavigation;
import com.wellsaid.utilities.page.WellSaidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class PronunciationPage implements BasePage, PageNavigation {
    private final WellSaidDriver driver;

    private final By phoneticInputFieldLocator = By.id("phonetic-input");
    private final By phoneticReplacementFieldLocator = By.id("phonetic-replacement");
    private final By addPhoneticReplacementButtonLocator = By.xpath("//*[@id=\"page-phonetic\"]/div[2]/form/button");
    private final By removePhoneticReplacementButtonLocator = By.xpath("//button[@aria-label='delete']");

    public PronunciationPage(WebDriver driver) {
        this.driver = new WellSaidDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(addPhoneticReplacementButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    @Override
    public void navigateTo() {
        driver.navigateTo(driver.getWellSaidUrl());
    }

    public PronunciationPage addPhonetics() {
        String input = "Selenium";
        String replacement = "Cell-ANY-um";

        WebElement phoneticInputField = driver.wait(ExpectedConditions.elementToBeClickable(phoneticInputFieldLocator));
        driver.setText(phoneticInputField, input);

        WebElement phoneticReplacementField = driver.wait(ExpectedConditions.elementToBeClickable(phoneticReplacementFieldLocator));
        driver.setText(phoneticReplacementField, replacement);

        WebElement addReplacementButton = driver.wait(ExpectedConditions.elementToBeClickable(addPhoneticReplacementButtonLocator));
        driver.click(addReplacementButton);

        return PageManager.getInstance().instantiateCurrentPage(PronunciationPage.class);
    }

    public void removePhonetics(){
        WebElement removePhoneticReplacementButton = driver.wait(ExpectedConditions.elementToBeClickable(removePhoneticReplacementButtonLocator));
        driver.click(removePhoneticReplacementButton);
    }

    public boolean hasPhonetic() {
        try {
            driver.waitWithTimeout(ExpectedConditions.elementToBeClickable(
                    removePhoneticReplacementButtonLocator), 1);
        } catch (TimeoutException e) {
            return true;
        }
        return false;
    }

}
