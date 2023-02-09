package com.wellsaid.pages.WebStudio.ProjectsPage;

import com.wellsaid.pages.WebStudio.StudioPage;
import com.wellsaid.utilities.managers.PageManager;
import com.wellsaid.utilities.page.BasePage;
import com.wellsaid.utilities.page.WellSaidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class CreateNewProjectModal implements BasePage {
    private final WellSaidDriver driver;

    private final By createProjectButtonLocator = By.xpath("//span[text()='Create Project']");
    private final By projectNameFieldLocator = By.id("new-project-name");
    private final By clientNameFieldLocator = By.id("new-project-client");

    public CreateNewProjectModal(WebDriver driver) {
        this.driver = new WellSaidDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(createProjectButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public ProjectsPage createProject() {
        long timestamp = System.currentTimeMillis() / 1000;

        WebElement projectName = driver.wait(ExpectedConditions.visibilityOfElementLocated(projectNameFieldLocator));
        driver.setText(projectName, "QA robot! TimeStamp: " + timestamp);

        WebElement clientNameField = driver.wait(ExpectedConditions.visibilityOfElementLocated(clientNameFieldLocator));
        driver.setText(clientNameField, "Wellsaid Labs QA");

        WebElement createProjectButton = driver.wait(ExpectedConditions.elementToBeClickable(createProjectButtonLocator));
        driver.click(createProjectButton);

        return PageManager.getInstance().instantiateCurrentPage(ProjectsPage.class);
    }
}
