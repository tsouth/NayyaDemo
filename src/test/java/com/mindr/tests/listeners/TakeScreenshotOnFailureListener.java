package com.mindr.tests.listeners;

import java.util.Optional;

import com.mindr.utilities.managers.PageManager;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TakeScreenshotOnFailureListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        PageManager.getInstance().takeScreenshot(Optional.of(result.getName()));
    }
}
