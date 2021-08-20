package com.mindr.pages.admindashboardpages.communitysubscriptionspage;

import com.mindr.pages.admindashboardpages.communitymanagementpage.ActiveCommunitiesTab;
import com.mindr.pages.profilepage.EditProfilePage;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class CommunitySubscriptionsPage implements BasePage {
    private final MindrDriver driver;

    private final By communitySubscriptionsHeaderLocator = By.xpath("//p[contains(., 'Community Subscriptions')]");
    private final By communityManagementMenuLocator = By.cssSelector("a[href*='/dashboard/communities']");
    private final By editProfileLinkLocator =  By.cssSelector("a[href*='/dashboard/profile/edit']");

    public CommunitySubscriptionsPage(WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(communitySubscriptionsHeaderLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }
    public ActiveCommunitiesTab communityManagement() {
        WebElement communityManagementMenu = driver.wait(ExpectedConditions.elementToBeClickable(communityManagementMenuLocator));
        driver.click(communityManagementMenu);

        return PageManager.getInstance().instantiateCurrentPage(ActiveCommunitiesTab.class);
    }

    public EditProfilePage editProfile() {
        WebElement editProfileLink = driver.wait(ExpectedConditions.elementToBeClickable(
                editProfileLinkLocator));
        driver.click(editProfileLink);

        return PageManager.getInstance().instantiateCurrentPage(EditProfilePage.class);
    }
}
