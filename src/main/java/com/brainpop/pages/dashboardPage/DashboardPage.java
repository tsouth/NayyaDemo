package com.brainpop.pages.dashboardPage;

import com.brainpop.pages.searchResultsPage.SearchResultsPage;
import com.brainpop.utilities.managers.PageManager;
import com.brainpop.utilities.page.BasePage;
import com.brainpop.utilities.page.BrainPopDriver;
import com.brainpop.utilities.page.PageNavigation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.TestException;
import java.util.ArrayList;
import java.util.List;

public class DashboardPage implements BasePage, PageNavigation {
    private final BrainPopDriver driver;

    private final By navigationCategoriesLocator = By.xpath("//nav[@class='categories-container desktop']//a");
    private final By searchInputLocator = By.cssSelector("input[id^='search-input']");
    private final By searchButtonLocator = By.cssSelector("button[id^='search-button']");

    public DashboardPage(WebDriver driver) {
        this.driver = new BrainPopDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        List<String> expectedCategories = List.of(
                "Science", "Social Studies", "English", "Math", "Arts and Music", "Health and SEL",
                "Engineering and Tech", "New and Trending"
        );

        ArrayList<String> categories = new ArrayList<>();
        List<WebElement> navigationCategories = driver.wait(ExpectedConditions.presenceOfAllElementsLocatedBy(
                navigationCategoriesLocator));
        for (WebElement navigationCategory : navigationCategories) {
            categories.add(navigationCategory.getText());
        }

        Assert.assertTrue(categories.containsAll(expectedCategories));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    @Override
    public void navigateTo() {
        driver.navigateTo(driver.getBrainPopUrl());
    }

    public SearchResultsPage submitSearchQuery(String query) {
        WebElement searchInput = driver.findElement(searchInputLocator);
        driver.setText(searchInput, query);

        WebElement searchButton = driver.findElement(searchButtonLocator);
        driver.click(searchButton);

        return PageManager.getInstance().instantiateCurrentPage(SearchResultsPage.class);
    }
}
