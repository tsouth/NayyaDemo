package com.wellsaid.pages.HomePage;

import com.wellsaid.pages.WebStudio.ProjectsPage.ProjectsPage;
import com.wellsaid.utilities.managers.PageManager;
import com.wellsaid.utilities.page.BasePage;
import com.wellsaid.utilities.page.WellSaidDriver;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;


public class CreateAccountModal implements BasePage {
    private final WellSaidDriver driver;

    private final By createAccountButtonLocator = By.xpath("//span[text()='Create Account']");
    private final By firstNameFormFieldLocator = By.id("request-access-first-name");
    private final By lastNameFormFieldLocator = By.id("request-access-last-name");
    private final By workEmailFormFieldLocator = By.id("request-access-email");
    private final By passwordFormFieldLocator = By.id("request-access-password");

    public CreateAccountModal (WebDriver driver) {
        this.driver = new WellSaidDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(createAccountButtonLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public ProjectsPage createAccount() {
        long timestamp = System.currentTimeMillis() / 1000;

        WebElement firstNameFormField = driver.wait(ExpectedConditions.elementToBeClickable(firstNameFormFieldLocator));
        driver.setText(firstNameFormField, "QATestFirstName");

        WebElement lastNameFormField = driver.wait(ExpectedConditions.elementToBeClickable(lastNameFormFieldLocator));
        driver.setText(lastNameFormField, "QATestLastName");

        WebElement workEmailFormField = driver.wait(ExpectedConditions.elementToBeClickable(workEmailFormFieldLocator));
        driver.setText(workEmailFormField, "thomas+WellsaidQATestAccount" + timestamp + "@tsouth.me");

        WebElement passwordFormField = driver.wait(ExpectedConditions.elementToBeClickable(passwordFormFieldLocator));
        driver.setText(passwordFormField, "Wellsaid!23");

        WebElement createAccountButton = driver.wait(ExpectedConditions.elementToBeClickable(createAccountButtonLocator));
        driver.click(createAccountButton);

        return PageManager.getInstance().instantiateCurrentPage(ProjectsPage.class);
    }

}
