package com.brainpop.tests.kidTests;

import com.brainpop.pages.dashboardPage.DashboardPage;
import com.brainpop.pages.homePage.HamburgerMenu;
import com.brainpop.pages.homePage.HomePage;
import com.brainpop.pages.loginPage.LoginPage;
import com.brainpop.utilities.testcase.TakeScreenshotOnFailureListener;
import com.brainpop.utilities.managers.PageManager;
import com.brainpop.utilities.testcase.RetryAnalyzer;
import com.brainpop.utilities.testcase.TestCase;
import org.testng.annotations.*;

@Listeners(TakeScreenshotOnFailureListener.class)
public class LoginTests implements TestCase {

    @Parameters({"environment"})
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testLoginAsAKid() {
        HomePage homePage = PageManager.getInstance().navigateToPage(HomePage.class);
        LoginPage loginPage;
        if (PageManager.getInstance().isSupportedMobileDevice()) {
            HamburgerMenu hamburgerMenu = homePage.openHamburgerMenu();
            loginPage = hamburgerMenu.loginAsAKid();
        } else {
            loginPage = homePage.loginAsAKid();
        }
        DashboardPage dashboardPage = loginPage.loginWithKidCredentials();
        dashboardPage.verifyCorrectPage();
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
