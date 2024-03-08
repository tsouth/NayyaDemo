package com.nayya.tests.nayya.prospect;

import com.nayya.pages.homePage.HamburgerMenu;
import com.nayya.pages.homePage.HomePage;
import com.nayya.pages.getDemoPage.DemoConfirmationPage;
import com.nayya.pages.getDemoPage.DemoRequestForm;
import com.nayya.utilities.testcase.TakeScreenshotOnFailureListener;
import com.nayya.utilities.managers.PageManager;
import com.nayya.utilities.testcase.RetryAnalyzer;
import com.nayya.utilities.testcase.TestCase;
import org.testng.annotations.*;

@Listeners(TakeScreenshotOnFailureListener.class)
public class GetADemoTest implements TestCase {

    @Parameters({"environment"})
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testGetADemo() {
        HomePage homePage = PageManager.getInstance().navigateToPage(HomePage.class);
        DemoRequestForm demoRequestForm;
        if (PageManager.getInstance().isSupportedMobileDevice()) {
            HamburgerMenu hamburgerMenu = homePage.openHamburgerMenu();
            demoRequestForm = hamburgerMenu.getDemo();
        } else {
            demoRequestForm = homePage.getDemo();
        }
        DemoConfirmationPage demoConfirmationPage = demoRequestForm.submitDemoRequestForm();
        demoConfirmationPage.verifyAppointmentRequest();
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
