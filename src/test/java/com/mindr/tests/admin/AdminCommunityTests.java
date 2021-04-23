package com.mindr.tests.admin;

import com.mindr.pages.communitiespage.ActiveCommunitiesTab;
import com.mindr.pages.homepage.MyDashboardTab;
import com.mindr.pages.loginpage.LoginPage;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.testcase.RetryAnalyzer;
import junit.framework.TestCase;
import org.testng.annotations.*;

public class AdminCommunityTests implements TestCase {

    @Override
    @Parameters({"environment"})
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testAddMatchedListingToCampaign() {
        LoginPage loginPage = PageManager.getInstance().navigateToPage(LoginPage.class);
        MyDashboardTab myDashboardTab = loginPage.signInAsAdmin();
        ActiveCommunitiesTab activeCommunitiesTab = myDashboardTab.viewMyProfile();
        activeCommunitiesTab.community();


        HomePage homePage = PageManager.getInstance().navigateToPage(HomePage.class);
        SignInModal signInModal = homePage.signIn();
        CampaignsTab campaignsPage_campaignsTab = signInModal.signInAsNYCBroker();
        HomeTab campaignPage_homeTab = campaignsPage_campaignsTab.campaign();
        SuggestedListingsPage suggestedListingsPage = campaignPage_homeTab.search();
        MatchSuggestedListingModal addSuggestedListingListingModal = suggestedListingsPage.suggestedListingMatch();
        suggestedListingsPage = addSuggestedListingListingModal.addMatchedListingToCampaign(1);
        SkipSuggestedListingsModal skipSuggestedListingsModal = suggestedListingsPage.skipSuggestedListings();
        SearchTab campaignPage_searchTab = skipSuggestedListingsModal.addSkipListingsFeedback();
        campaignPage_searchTab.addListingsToSurvey(5);
    }


    @Override
    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
