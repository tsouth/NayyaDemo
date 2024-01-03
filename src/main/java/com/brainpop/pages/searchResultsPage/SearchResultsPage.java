package com.brainpop.pages.searchResultsPage;

import com.brainpop.pages.topics.healthAndSEL.DNA.TopicDashboardPage;
import com.brainpop.utilities.managers.PageManager;
import com.brainpop.utilities.page.BasePage;
import com.brainpop.utilities.page.BrainPopDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

import java.util.List;

public class SearchResultsPage implements BasePage {
    private final BrainPopDriver driver;

    private final By searchTitleLocator = By.xpath("//h1[contains(text(), 'Search Results')]");
    private final By topicsLocator = By.xpath("//li[contains(@id, 'topic_result')]");
    private final By topicsResultsNumberLocator = By.xpath("//span[@class='topics_results_number']");
    private final By dnaTopicCardLocator = By.cssSelector("a[href$='/dna/']");

    public SearchResultsPage(WebDriver driver) {
        this.driver = new BrainPopDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(searchTitleLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoaded()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }

    public String getTopicsResultsTitleNumber() {
        WebElement topicsResultsTitleNumber = driver.wait(ExpectedConditions.visibilityOfElementLocated(topicsResultsNumberLocator));

        return topicsResultsTitleNumber.getText();
    }

    public int getTopicsCount() {
        int size;
        try {
            List<WebElement> topics = driver.wait(ExpectedConditions.presenceOfAllElementsLocatedBy(topicsLocator));
            size = topics.size();
        } catch (TimeoutException e) {
            size = 0;
        }

        return size;
    }

    public TopicDashboardPage selectDNATopic() {
        driver.wait(ExpectedConditions.visibilityOfAllElementsLocatedBy(topicsLocator));

        WebElement dnaTopicCard = driver.wait(ExpectedConditions.elementToBeClickable(dnaTopicCardLocator));
        driver.executeScript("arguments[0].scrollIntoView(true);", dnaTopicCard);
        driver.click(dnaTopicCard);

        return PageManager.getInstance().instantiateCurrentPage(TopicDashboardPage.class);
    }


}
