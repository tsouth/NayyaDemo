package com.wellsaid.pages.webstudio.ProjectsPage;

import com.wellsaid.pages.webstudio.PronunciationPage;
import com.wellsaid.pages.webstudio.SignInPage.SignInPage;
import com.wellsaid.utilities.managers.PageManager;
import com.wellsaid.utilities.page.BasePage;
import com.wellsaid.utilities.page.PageNavigation;
import com.wellsaid.utilities.page.WellSaidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class ProjectsPage implements BasePage {
    private final WellSaidDriver driver;

    private final By newProjectCTAButtonLocator = By.cssSelector("button[data-e2e='action-project-create']");
    private final By newProjectConfirmationBannerLocator = By.className("MuiSnackbarContent-message");
    private final By pronunciationPageNavButtonLocator = By.cssSelector("a[href='/dashboard/phonetic']");

    public ProjectsPage(WebDriver driver) {
        this.driver = new WellSaidDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(newProjectCTAButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public CreateNewProjectModal createNewProject(){
        WebElement newProjectCtaButton = driver.wait(ExpectedConditions.elementToBeClickable(newProjectCTAButtonLocator));
        driver.click(newProjectCtaButton);

        return PageManager.getInstance().instantiateCurrentPage(CreateNewProjectModal.class);
    }

    public String getConfirmationBannerText() {
        WebElement newProjectConfirmationBanner = driver.wait(ExpectedConditions.visibilityOfElementLocated(newProjectConfirmationBannerLocator));

        return newProjectConfirmationBanner.getText();
    }

    public PronunciationPage selectPronunciationPage() {
        WebElement pronunciationPageNavButton = driver.wait(ExpectedConditions.elementToBeClickable(pronunciationPageNavButtonLocator));
        driver.click(pronunciationPageNavButton);

        return PageManager.getInstance().instantiateCurrentPage(PronunciationPage.class);
    }

}
