package com.mindr.tests.admin;

import com.mindr.pages.communitypages.eventspages.NewEventPage;
import com.mindr.pages.communitypages.eventspages.PublishEventConfirmationModal;
import com.mindr.pages.communitypages.eventspages.UpcomingEventsTab;
import com.mindr.pages.communitypages.eventspages.UploadEventPhotoModal;
import com.mindr.pages.dashboardpages.communitymanagementpage.ActiveCommunitiesTab;
import com.mindr.pages.homepage.MyDashboardTab;
import com.mindr.pages.loginpage.LoginPage;
import com.mindr.utilities.image.ImageUtility;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.testcase.RetryAnalyzer;
import org.testng.annotations.*;

public class AdminEventTests {
    private String testImagePath;
    private final ImageUtility imageUtility = new ImageUtility();

    @Parameters({"environment"})
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);

        testImagePath = imageUtility.createImage();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCreateEvent() {
        LoginPage loginPage = PageManager.getInstance().navigateToPage(LoginPage.class);
        MyDashboardTab myDashboardTab = loginPage.signInAsAnAdmin();
        ActiveCommunitiesTab activeCommunitiesTab = myDashboardTab.viewMyProfileAsAnAdmin();
        UpcomingEventsTab upcomingEventsTab = activeCommunitiesTab.selectCommunity();
        NewEventPage newEventPage = upcomingEventsTab.createNewEvent();
        UploadEventPhotoModal uploadEventPhotoModal = newEventPage.uploadEventPhoto(testImagePath);
        uploadEventPhotoModal.selectPhoto();
        PublishEventConfirmationModal publishEventConfirmationModal = newEventPage.submitEventDetails();
        publishEventConfirmationModal.publishEvent();
        upcomingEventsTab.verifyEventCreated();
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
