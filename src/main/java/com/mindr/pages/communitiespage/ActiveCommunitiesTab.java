package com.mindr.pages.communitiespage;

import com.mindr.pages.communitypage.CommunityEventsPage;
import com.mindr.pages.profilepage.EditProfilePage;
import com.mindr.utilities.managers.PageManager;
import com.mindr.utilities.page.BasePage;
import com.mindr.utilities.page.MindrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

public class ActiveCommunitiesTab implements BasePage {
    private final MindrDriver driver;

    private final By activeCommunitiesTabLocator = By.xpath("//a[contains(., 'Active Communities')]");
    private final By editProfileLinkLocator =  By.cssSelector("a[href*='/dashboard/profile/edit']");
    private final By campaignLocator = By.xpath("//div[contains(.,'QA Community for Automated Testing')]");


    public ActiveCommunitiesTab (WebDriver driver) {
        this.driver = new MindrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(activeCommunitiesTabLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public CommunityEventsPage community(){
        WebElement campaign;
        campaign = driver.wait(ExpectedConditions.visibilityOfElementLocated(campaignLocator));
        driver.click(campaign);

        return PageManager.getInstance().instantiateCurrentPage(CommunityEventsPage.class);
    }

    public EditProfilePage editProfile() {
        WebElement editProfileLink = driver.wait(ExpectedConditions.elementToBeClickable(
                editProfileLinkLocator));
        driver.click(editProfileLink);

        return PageManager.getInstance().instantiateCurrentPage(EditProfilePage.class);
    }
}
