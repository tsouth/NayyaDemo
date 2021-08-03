package com.mindr.tests.admin;

import com.mindr.pages.communitypages.callstoactionpages.ActiveCallsToActionTab;
import com.mindr.pages.communitypages.callstoactionpages.NewCallToActionPage;
import com.mindr.pages.communitypages.callstoactionpages.PublishCallToActionConfirmationModal;
import com.mindr.pages.communitypages.callstoactionpages.UploadCallToActionPhotoModal;
import com.mindr.pages.communitypages.eventspages.UpcomingEventsTab;
import com.mindr.pages.dashboardpages.communitymanagementpage.ActiveCommunitiesTab;
import com.mindr.pages.homepage.MyDashboardTab;
import com.mindr.pages.loginpage.LoginPage;
import com.mindr.utilities.image.ImageUtility;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.testcase.RetryAnalyzer;
import org.testng.annotations.*;

public class AdminCallToActionTests {
    private String testImagePath;
    private final ImageUtility imageUtility = new ImageUtility();

    @Parameters({"environment"})
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);

        testImagePath = imageUtility.createImage();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCreateCallToAction() {
        LoginPage loginPage = PageManager.getInstance().navigateToPage(LoginPage.class);
        MyDashboardTab myDashboardTab = loginPage.signInAsAnAdmin();
        ActiveCommunitiesTab activeCommunitiesTab = myDashboardTab.viewMyProfileAsAnAdmin();
        UpcomingEventsTab upcomingEventsTab = activeCommunitiesTab.selectCommunity();
        ActiveCallsToActionTab activeCallsToActionTab = upcomingEventsTab.callsToAction();
        NewCallToActionPage newCallToActionPage = activeCallsToActionTab.createNewCallToAction();
        UploadCallToActionPhotoModal uploadCallToActionPhotoModal = newCallToActionPage.uploadCallToActionPhoto(testImagePath);
        uploadCallToActionPhotoModal.selectPhoto();
        PublishCallToActionConfirmationModal publishCallToActionConfirmationModal = newCallToActionPage.submitCallToActionDetails();
        publishCallToActionConfirmationModal.publishCallToAction();
        activeCallsToActionTab.verifyCallToActionCreated();
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
