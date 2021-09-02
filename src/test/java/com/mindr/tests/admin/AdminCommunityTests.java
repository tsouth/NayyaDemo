package com.mindr.tests.admin;

import com.mindr.pages.admindashboardpages.communitymanagementpage.ActiveCommunitiesTab;
import com.mindr.pages.admindashboardpages.communitymanagementpage.NewCommunityPage;
import com.mindr.pages.admindashboardpages.communitymanagementpage.UploadPhotoModal;
import com.mindr.pages.homepage.MyDashboardTab;
import com.mindr.pages.loginpage.LoginPage;
import com.mindr.tests.listeners.TakeScreenshotOnFailureListener;
import com.mindr.utilities.image.ImageUtility;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.testcase.RetryAnalyzer;
import org.testng.annotations.*;

@Listeners(TakeScreenshotOnFailureListener.class)
public class AdminCommunityTests {
    private String testImagePath;
    private final ImageUtility imageUtility = new ImageUtility();

    @Parameters({ "environment" })
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);

        testImagePath = imageUtility.createImage();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCreateCommunity() {
        LoginPage loginPage = PageManager.getInstance().navigateToPage(LoginPage.class);
        MyDashboardTab myDashboardTab = loginPage.signInAsAnAdmin();
        ActiveCommunitiesTab activeCommunitiesTab = myDashboardTab.viewMyProfileAsAnAdmin();
        NewCommunityPage newCommunityPage = activeCommunitiesTab.createNewCommunity();

        UploadPhotoModal uploadPhotoModal = newCommunityPage.uploadCommunityLogo(testImagePath);
        uploadPhotoModal.selectPhoto();

        newCommunityPage.uploadCommunityThumbnail(testImagePath);
        uploadPhotoModal.selectPhoto();

        newCommunityPage.uploadCommunityBanner(testImagePath);
        uploadPhotoModal.selectPhoto();

        newCommunityPage.uploadFeaturedImageOne(testImagePath);
        uploadPhotoModal.selectPhoto();

        newCommunityPage.uploadFeaturedImageTwo(testImagePath);
        uploadPhotoModal.selectPhoto();

        newCommunityPage.submitCommunityDetails();
        activeCommunitiesTab.verifyCommunityCreated();
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
