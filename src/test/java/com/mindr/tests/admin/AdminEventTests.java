package com.mindr.tests.admin;

import com.mindr.pages.communitypages.eventspage.NewEventPage;
import com.mindr.pages.communitypages.eventspage.PublishEventConfirmationModal;
import com.mindr.pages.communitypages.eventspage.UpcomingEventsTab;
import com.mindr.pages.communitypages.eventspage.UploadedEventPhotoModal;
import com.mindr.pages.dashboardpages.communitymanagementpage.ActiveCommunitiesTab;
import com.mindr.pages.dashboardpages.communitysubscriptionspage.CommunitySubscriptionsPage;
import com.mindr.pages.homepage.MyDashboardTab;
import com.mindr.pages.loginpage.LoginPage;
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
        ActiveCommunitiesTab activeCommunitiesTab = myDashboardTab.viewMyProfileAsAnAdmin();
        UpcomingEventsTab upcomingEventsTab = activeCommunitiesTab.selectCommunity();
        NewEventPage newEventPage = upcomingEventsTab.createNewEvent();
        //UploadedEventPhotoModal uploadedEventPhotoModal = newEventPage.uploadEventPhoto();
        //uploadedEventPhotoModal.selectPhoto();
        PublishEventConfirmationModal publishEventConfirmationModal = newEventPage.submitEventDetails();
        publishEventConfirmationModal.publishEvent();
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
