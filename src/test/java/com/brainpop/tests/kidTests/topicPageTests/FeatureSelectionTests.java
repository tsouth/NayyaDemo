package com.brainpop.tests.kidTests.topicPageTests;

import com.brainpop.pages.topics.healthAndSEL.DNA.TopicDashboardPage;
import com.brainpop.utilities.managers.PageManager;
import com.brainpop.utilities.testcase.RetryAnalyzer;
import com.brainpop.utilities.testcase.TestCase;
import org.testng.annotations.*;

public class FeatureSelectionTests implements TestCase {
    @Override
    @Parameters({"environment"})
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testRelatedReadingFeatureSelection() {
        TopicDashboardPage topicDashboardPage = PageManager.getInstance().navigateToPage(TopicDashboardPage.class);
        topicDashboardPage.selectRelatedReadingFeature();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testRandomFeatureSelection() {
        TopicDashboardPage topicDashboardPage = PageManager.getInstance().navigateToPage(TopicDashboardPage.class);
        topicDashboardPage.selectRandomFeature();
    }

    @Override
    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
