package com.songTradr.pages.webplayer;

import com.songTradr.utilities.managers.PageManager;
import com.songTradr.utilities.page.BasePage;
import com.songTradr.utilities.page.SongTradrDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchTab implements BasePage {
    private final SongTradrDriver driver;

    private final By filterCheckBoxesLocator = By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[3]");
    private final By energyCategoryFilterButtonLocator = By.xpath("//div[contains(text(), 'energy')]");
    private final By filterPillLocator = By.cssSelector("div[class^='styled__PillContent']");
    private final By searchTabHeaderLocator = By.xpath("//h1[text()='Featured tracks']");

    public SearchTab(WebDriver driver) {
        this.driver = new SongTradrDriver(driver);
    }

    @Override
    public void verifyCorrectPage() {
        driver.wait(ExpectedConditions.visibilityOfElementLocated(searchTabHeaderLocator));
    }

    @Override
    public void waitForPageLoad() {
        if (!driver.pageLoadedWithRefresh()) {
            throw new TestException(getClass().getName() + " failed to load!!");
        }
    }



    public SearchTab addEnergyFiltersToSearch (int numberOfFilters){
        WebElement energyCategoryFilterButton = driver.wait(ExpectedConditions.elementToBeClickable(energyCategoryFilterButtonLocator));
        driver.click(energyCategoryFilterButton);
        selectFilters(numberOfFilters);

        return PageManager.getInstance().instantiateCurrentPage(SearchTab.class);
    }

    public void selectFilters (int numberOfFilters) {
        ArrayList<Integer> selectedFilters = new ArrayList<>();
        for (int i = 0; i < numberOfFilters; i++) {
            while (true) {
                try {
                    List<WebElement> filterCheckBoxes = driver.wait(ExpectedConditions.visibilityOfAllElementsLocatedBy(filterCheckBoxesLocator));
                    Integer filterToBeSelected = new Random().nextInt(filterCheckBoxes.size());
                    if (!selectedFilters.contains(filterToBeSelected)) {
                        WebElement filterCheckBox = filterCheckBoxes.get(new Random().nextInt(
                                filterCheckBoxes.size()));
                        driver.executeScript("arguments[0].scrollIntoView(true);", filterCheckBox);
                        driver.click(filterCheckBox);
                        selectedFilters.add(filterToBeSelected);
                    }
                    break;
                } catch (StaleElementReferenceException ignore) {}
            }
        }
    }


    public String getFilterPillText() {
        WebElement pill = driver.wait(ExpectedConditions.visibilityOfElementLocated(filterPillLocator));

        return pill.getText();
    }
}
