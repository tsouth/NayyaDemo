package com.mindr.tests.admin;

import com.mindr.pages.communitypages.eventspage.NewEventPage;
import com.mindr.pages.communitypages.eventspage.UpcomingEventsTab;
import com.mindr.pages.dashboardpages.communitymanagementpage.ActiveCommunitiesTab;
import com.mindr.pages.dashboardpages.communitysubscriptionspage.CommunitySubscriptionsPage;
import com.mindr.pages.homepage.MyDashboardTab;
import com.mindr.pages.loginpage.LoginPage;
import com.mindr.pages.profilepage.EditProfilePage;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.testcase.RetryAnalyzer;
import org.testng.annotations.*;

public class AdminEventTests {

    @Parameters({"environment"})
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCreateEvent() {
        LoginPage loginPage = PageManager.getInstance().navigateToPage(LoginPage.class);
        MyDashboardTab myDashboardTab = loginPage.signInAsAnAdmin();
        CommunitySubscriptionsPage communitySubscriptionsPage = myDashboardTab.viewMyProfile();
        ActiveCommunitiesTab activeCommunitiesTab = communitySubscriptionsPage.communityManagement();
        UpcomingEventsTab upcomingEventsTab = activeCommunitiesTab.selectActiveCommunity();
        NewEventPage newEventPage = upcomingEventsTab.createNewEvent();

    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
