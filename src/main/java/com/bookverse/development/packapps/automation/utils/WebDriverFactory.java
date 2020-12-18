package com.bookverse.development.packapps.automation.utils;

import com.bookverse.development.packapps.models.DataSet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

import static com.bookverse.development.packapps.automation.utils.Constants.*;

public class WebDriverFactory {

  public static WebDriver goToWeb(String url) {
    WebDriver driver = getDriver();
    driver.get(url);

    return driver;
  }

  private static WebDriver getDriver() {

    String currentSystem = DataSet.getCurrentSystem();

    String driver =
        (currentSystem.equals(MAC))
            ? CHROME_DRIVER.replace(SYSTEM, MAC)
            : CHROME_DRIVER.replace(SYSTEM, WINDOWS);

    ChromeDriverService chromeDriverService =
        new ChromeDriverService.Builder()
            .usingDriverExecutable(new File(driver))
            .usingAnyFreePort()
            .build();

    try {
      chromeDriverService.start();
    } catch (IOException e) {
      e.printStackTrace();
    }

    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments(
        "--incognito",
        "--start-maximized",
        "--disable-infobars",
        "enable-automation",
        "--disable-browser-side-navigation");

    return new ChromeDriver(chromeDriverService, chromeOptions);
  }
}
