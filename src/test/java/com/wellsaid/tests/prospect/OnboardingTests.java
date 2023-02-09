package com.wellsaid.tests.prospect;

import com.wellsaid.pages.HomePage.CreateAccountModal;
import com.wellsaid.pages.HomePage.HomePage;
import com.wellsaid.pages.WebStudio.ProjectsPage.ProjectsPage;
import com.wellsaid.tests.listeners.TakeScreenshotOnFailureListener;
import com.wellsaid.utilities.managers.PageManager;
import com.wellsaid.utilities.testcase.RetryAnalyzer;
import com.wellsaid.utilities.testcase.TestCase;
import org.testng.annotations.*;

@Listeners(TakeScreenshotOnFailureListener.class)
public class OnboardingTests implements TestCase {

    @Parameters({"environment"})
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void createAccount() {
        HomePage homepage = PageManager.getInstance().navigateToPage(HomePage.class);
        CreateAccountModal createAccountModal =  homepage.createAccount();
        createAccountModal.createAccount();
        /*
        Here we would continue the test by logging to a Wellsaid QA email (imap), find the unread email, retrieve the
        account verification link, set the email as read, and return to the web browser
        to complete the verification process.
         */
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
