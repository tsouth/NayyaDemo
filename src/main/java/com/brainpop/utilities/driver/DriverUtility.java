package com.wellsaid.utilities.driver;

import org.openqa.selenium.WebDriver;

import java.util.Map;

public interface DriverUtility {
    public WebDriver createDriver(Map<String, String> executionEnvironment);
}
