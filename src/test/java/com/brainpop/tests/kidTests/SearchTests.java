package com.brainpop.tests.kid;

import com.brainpop.pages.dashboardPage.DashboardPage;
import com.brainpop.pages.homePage.HamburgerMenu;
import com.brainpop.pages.homePage.HomePage;
import com.brainpop.pages.loginPage.LoginPage;
import com.brainpop.pages.searchResultsPage.SearchResultsPage;
import com.brainpop.pages.topics.healthAndSEL.DNA.TopicPage;
import com.brainpop.utilities.testcase.TakeScreenshotOnFailureListener;
import com.brainpop.utilities.managers.PageManager;
import com.brainpop.utilities.testcase.RetryAnalyzer;
import com.brainpop.utilities.testcase.TestCase;
import org.junit.Assert;
import org.testng.annotations.*;

@Listeners(TakeScreenshotOnFailureListener.class)
public class SearchTests implements TestCase {

    @Parameters({"environment"})
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testConfirmAccurateSearchResultsCount() {
        String query = "Challenge";

        HomePage homePage = PageManager.getInstance().navigateToPage(HomePage.class);
        LoginPage loginPage;
        if (PageManager.getInstance().isSupportedMobileDevice()) {
            HamburgerMenu hamburgerMenu = homePage.openHamburgerMenu();
            loginPage = hamburgerMenu.loginAsAKid();
        } else {
            loginPage = homePage.loginAsAKid();
        }
        DashboardPage dashboardPage = loginPage.loginWithKidCredentials();
        SearchResultsPage searchResultsPage = dashboardPage.submitSearchQuery(query);
        String topicResultsTitleNumber = searchResultsPage.getTopicsResultsTitleNumber();
        int topicsCount = searchResultsPage.getTopicsCount();

        Assert.assertTrue(topicResultsTitleNumber.equals(Integer.toString(topicsCount)));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testSelectTopicFromSearchResults() {
        String query = "Challenge";

        HomePage homePage = PageManager.getInstance().navigateToPage(HomePage.class);
        LoginPage loginPage;
        if (PageManager.getInstance().isSupportedMobileDevice()) {
            HamburgerMenu hamburgerMenu = homePage.openHamburgerMenu();
            loginPage = hamburgerMenu.loginAsAKid();
        } else {
            loginPage = homePage.loginAsAKid();
        }
        DashboardPage dashboardPage = loginPage.loginWithKidCredentials();
        SearchResultsPage searchResultsPage = dashboardPage.submitSearchQuery(query);
        TopicPage topicPage = searchResultsPage.selectDNATopic();
        topicPage.verifyCorrectPage();
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}