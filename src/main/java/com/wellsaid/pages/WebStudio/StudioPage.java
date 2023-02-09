package com.wellsaid.pages.WebStudio;

import com.wellsaid.utilities.page.BasePage;
import com.wellsaid.utilities.page.WellSaidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class StudioPage implements BasePage {
    private final WellSaidDriver driver;

    private final By projectEditorSubmittButtonLocator = By.xpath("//button[@data-e2e='project-editor-submit']");
    private final By projectEditorTextAreaLocator = By.xpath("//textarea[@data-e2e='project-editor']");
    private final By playbackButtonLocator = By.xpath("//button[@aria-label='playback icon']");

    public StudioPage(WebDriver driver) {
        this.driver = new WellSaidDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(projectEditorSubmittButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public void createTextToSpeechSample() {
        WebElement projectEditorTextArea = driver.wait(ExpectedConditions.elementToBeClickable(projectEditorTextAreaLocator));
        driver.setText(projectEditorTextArea, "Hello Wellsaid Team! I hope you're enjoying this demo. Fun fact! " +
                "\"Selenium\" software is named after the trace element and is known to nullify the effects of the element " +
                "Mercury. Back in the day \"Mercury Interactive\" was a direct competitor to Selenium with products like QTP " +
                "and winrunner. Guess who won?");

        WebElement submitSampleButton = driver.wait(ExpectedConditions.elementToBeClickable(projectEditorSubmittButtonLocator));
        driver.click(submitSampleButton);
    }

    public void playTextToSpeechSample() {
        WebElement playBackButton = driver.wait(ExpectedConditions.elementToBeClickable(playbackButtonLocator));
        driver.click(playBackButton);
    }

}
