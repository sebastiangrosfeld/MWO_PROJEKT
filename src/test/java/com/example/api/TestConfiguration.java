package com.example.api;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Getter
public class TestConfiguration {

    private final WebDriver testDriver;

    public TestConfiguration() {
        final var configOptions = new ChromeOptions();
        configOptions.addArguments("--no-sandbox");
        configOptions.addArguments("--disable-dev-shm-usage");
        configOptions.addArguments("--headless");
        configOptions.addArguments("--window-size=1920,1080");
        this.testDriver = new ChromeDriver(configOptions);
    }
}
