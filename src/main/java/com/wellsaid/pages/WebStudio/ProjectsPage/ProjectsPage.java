package com.wellsaid.pages.WebStudio.ProjectsPage;

import com.wellsaid.pages.WebStudio.PronunciationPage;
import com.wellsaid.pages.WebStudio.StudioPage;
import com.wellsaid.utilities.managers.PageManager;
import com.wellsaid.utilities.page.BasePage;
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
    private final By projectCardLocator = By.xpath("//div[@data-e2e='project-card']");
    private final By projectCardActionListLocator = By.xpath("//button[@data-e2e='action-list']");
    private final By actionDeleteProjectLocator = By.xpath("//li[@data-e2e='action-delete-project']");
    private final By confirmDeleteProjectButtonLocator = By.xpath("//button[@data-e2e='action-destructive-dialog-content']");

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

    public StudioPage selectProject(){
        WebElement projectCard = driver.wait(ExpectedConditions.elementToBeClickable(projectCardLocator));
        driver.click(projectCard);

        return PageManager.getInstance().instantiateCurrentPage(StudioPage.class);
    }

    public void deleteProject () {
        WebElement projectCardActionList = driver.wait(ExpectedConditions.elementToBeClickable(projectCardActionListLocator));
        driver.click(projectCardActionList);

        WebElement actionDeleteProject = driver.wait(ExpectedConditions.elementToBeClickable(actionDeleteProjectLocator));
        driver.click(actionDeleteProject);

        WebElement confirmDeleteProjectButton = driver.wait(ExpectedConditions.elementToBeClickable(confirmDeleteProjectButtonLocator));
        driver.click(confirmDeleteProjectButton);
    }

}
