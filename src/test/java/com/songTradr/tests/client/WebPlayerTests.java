package com.songTradr.tests.client;

import com.songTradr.pages.webplayer.HomeTab;
import com.songTradr.pages.webplayer.SearchTab;
import com.songTradr.tests.listeners.TakeScreenshotOnFailureListener;
import com.songTradr.utilities.managers.PageManager;
import com.songTradr.utilities.testcase.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TakeScreenshotOnFailureListener.class)
public class WebPlayerTests {

    @Parameters({"environment"})
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testFilterSongSearch() {
        HomeTab homeTab = PageManager.getInstance().navigateToPage(HomeTab.class);
        SearchTab searchTab = homeTab.Search();
        searchTab.addEnergyFiltersToSearch(5);

        String filterPillText = searchTab.getFilterPillText();
        System.out.println("FILTER APPLIED = " + filterPillText);
        Assert.assertNotNull(filterPillText);
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
