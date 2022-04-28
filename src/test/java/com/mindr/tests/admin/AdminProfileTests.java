package com.mindr.tests.admin;

import com.mindr.pages.admindashboardpages.communitymanagementpage.ActiveCommunitiesTab;
import com.mindr.pages.homepage.MyDashboardTab;
import com.mindr.pages.loginpage.LoginPage;
import com.mindr.pages.profilepage.EditProfilePage;
import com.mindr.tests.listeners.TakeScreenshotOnFailureListener;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.testcase.RetryAnalyzer;
import org.testng.annotations.*;

@Listeners(TakeScreenshotOnFailureListener.class)
public class AdminProfileTests {

    @Parameters({ "environment" })
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testModifyProfile() {
        LoginPage loginPage = PageManager.getInstance().navigateToPage(LoginPage.class);
        MyDashboardTab myDashboardTab = loginPage.signInAsAnAdmin();
        ActiveCommunitiesTab activeCommunitiesTab = myDashboardTab.organizationSettings();
        EditProfilePage editProfilePage = activeCommunitiesTab.editProfile();
        editProfilePage.setProfileFirstName("QA Admin");
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
