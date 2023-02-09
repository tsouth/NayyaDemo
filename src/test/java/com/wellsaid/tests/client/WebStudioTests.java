package com.wellsaid.tests.client;

import com.wellsaid.pages.HomePage.HomePage;
import com.wellsaid.pages.WebStudio.ProjectsPage.CreateNewProjectModal;
import com.wellsaid.pages.WebStudio.ProjectsPage.ProjectsPage;
import com.wellsaid.pages.WebStudio.PronunciationPage;
import com.wellsaid.pages.WebStudio.SignInPage.SignInPage;
import com.wellsaid.pages.WebStudio.StudioPage;
import com.wellsaid.tests.listeners.TakeScreenshotOnFailureListener;
import com.wellsaid.utilities.managers.PageManager;
import com.wellsaid.utilities.testcase.RetryAnalyzer;
import com.wellsaid.utilities.testcase.TestCase;
import org.openqa.grid.web.servlet.handler.SeleniumBasedRequest;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TakeScreenshotOnFailureListener.class)
public class WebStudioTests implements TestCase {

    @Parameters({"environment"})
    @BeforeMethod
    public void setup(@Optional("production") String environment) {
        PageManager.getInstance().open(environment);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testAddNewProject() {
        HomePage homePage = PageManager.getInstance().navigateToPage(HomePage.class);
        SignInPage signInPage = homePage.signIn();
        ProjectsPage projectsPage = signInPage.signInAsTrialUser();
        CreateNewProjectModal createNewProjectModal = projectsPage.createNewProject();
        createNewProjectModal.createProject();

        String confirmationBannerText = projectsPage.getConfirmationBannerText();
        System.out.println("Confirmation Text = " + confirmationBannerText);
        Assert.assertNotNull(confirmationBannerText);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testCreateTextToSpeech() throws InterruptedException {
        HomePage homePage = PageManager.getInstance().navigateToPage(HomePage.class);
        SignInPage signInPage = homePage.signIn();
        ProjectsPage projectsPage = signInPage.signInAsTrialUser();
        StudioPage studioPage = projectsPage.selectProject();
        studioPage.createTextToSpeechSample();
        studioPage.playTextToSpeechSample();
        Thread.sleep(25000);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testAddAndRemovePhoneticReplacement() {
        HomePage homepage  = PageManager.getInstance().navigateToPage(HomePage.class);
        ProjectsPage projectsPage = homepage.signIn().signInAsTrialUser();
        PronunciationPage pronunciationPage = projectsPage.selectPronunciationPage();
        if (pronunciationPage.hasPhonetic()) {
            pronunciationPage.removePhonetics();
        }
        pronunciationPage.addPhonetics();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testDeleteProject() {
        HomePage homePage = PageManager.getInstance().navigateToPage(HomePage.class);
        SignInPage signInPage = homePage.signIn();
        ProjectsPage projectsPage = signInPage.signInAsTrialUser();
        projectsPage.deleteProject();
    }

    @AfterMethod
    public void teardown() {
        PageManager.getInstance().close();
    }
}
