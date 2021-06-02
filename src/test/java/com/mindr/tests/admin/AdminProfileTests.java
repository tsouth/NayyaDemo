package com.mindr.tests.admin;

import com.mindr.pages.dashboardpages.communitysubscriptionspage.CommunitySubscriptionsPage;
import com.mindr.pages.homepage.MyDashboardTab;
import com.mindr.pages.loginpage.LoginPage;
import com.mindr.pages.profilepage.EditProfilePage;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.testcase.RetryAnalyzer;
import org.testng.annotations.*;

public class AdminProfileTests {

    @Parameters({"environment"})
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testModifyProfile() {
        LoginPage loginPage = PageManager.getInstance().navigateToPage(LoginPage.class);
        MyDashboardTab myDashboardTab = loginPage.signInAsAdmin();
        CommunitySubscriptionsPage communitySubscriptionsPage = myDashboardTab.viewMyProfile();
        EditProfilePage editProfilePage = communitySubscriptionsPage.editProfile();
        editProfilePage.setProfileFirstName("QA Admin");
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}

