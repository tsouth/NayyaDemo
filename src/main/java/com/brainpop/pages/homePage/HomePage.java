package com.wellsaid.pages.HomePage;

import com.wellsaid.pages.WebStudio.SignInPage.SignInPage;
import com.wellsaid.utilities.managers.PageManager;
import com.wellsaid.utilities.page.BasePage;
import com.wellsaid.utilities.page.PageNavigation;
import com.wellsaid.utilities.page.WellSaidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class HomePage implements BasePage, PageNavigation {
    private final WellSaidDriver driver;

    private final By tryForFreeCTAButtonLocator = By.cssSelector("a[href='/auth/request_access']");
    private final By signInButtonLocator = By.xpath("//a[contains(text(), 'SIGN IN')]");

    public HomePage(WebDriver driver) {
        this.driver = new WellSaidDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(tryForFreeCTAButtonLocator));
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

    public SignInPage signIn() {
        WebElement signInButton = driver.wait(ExpectedConditions.elementToBeClickable(signInButtonLocator));
        driver.click(signInButton);

        return PageManager.getInstance().instantiateCurrentPage(SignInPage.class);
    }

    public CreateAccountModal createAccount() {
        WebElement tryForFreeButton = driver.wait(ExpectedConditions.elementToBeClickable(tryForFreeCTAButtonLocator));
        driver.click(tryForFreeButton);

        return PageManager.getInstance().instantiateCurrentPage(CreateAccountModal.class);
    }

}
