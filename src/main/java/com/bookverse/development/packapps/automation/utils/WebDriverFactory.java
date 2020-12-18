package com.sophossolutions.certification.practice.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.io.IOException;

import static com.sophossolutions.certification.practice.utils.Constants.*;

public class WebDriverFactory {

    public static WebDriver goToWeb(String url) {
        WebDriver driver = getDriver();
        driver.get(url);
        driver.manage().window().maximize();

        return driver;
    }

    private static WebDriver getDriver() {

        String currentBrowser = ReadConfiguration.getData(CURRENT_BROWSER);

        switch (currentBrowser) {

            case EDGE:

                EdgeDriverService edgeDriverService = new EdgeDriverService.Builder()
                        .usingDriverExecutable(new File(EDGE_DRIVER))
                        .usingAnyFreePort()
                        .build();

                try {
                    edgeDriverService.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setCapability("InPrivate", true);

                return new EdgeDriver(edgeDriverService, edgeOptions);

            case CHROME:

                ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(CHROME_DRIVER))
                        .usingAnyFreePort()
                        .build();

                try {
                    chromeDriverService.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(
                        "--incognito", "--start-maximized", "--disable-infobars",
                        "enable-automation", "--disable-browser-side-navigation");

                return new ChromeDriver(chromeDriverService, chromeOptions);

            default:
                throw new IllegalArgumentException();
        }
    }
}